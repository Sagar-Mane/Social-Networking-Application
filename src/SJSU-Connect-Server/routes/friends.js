var mongo = require('./mongo');
var ObjectId = require('mongodb').ObjectID;
var url = "mongodb://localhost:27017/Facebook";

exports.addNewFriend=function(req,res){
    console.log("Reporting from addNewFriend ");

    var email = req.param("email");
    var friend_email = req.param("friend_email");
    var first_name = req.param("first_name");

    mongo.connect(url, function() {

        var PendingRequests = mongo.collection('PendingRequests');
        var json_response= {};

        var request = {
            email:	email,
            friend_email:	friend_email,
            first_name : first_name
        };
        PendingRequests.insert(request, function (err, result) {
            if(err)
            {
                throw err;
                //db.close();
            }
            else if(result.insertedCount ==1)
            {
                console.log("found user." + result[0])
                response={"statusCode" : 200};
                res.send(response);
            }
            else{
                response={"statusCode" : 400};
                res.send(response);
            }
        });
    });

};

exports.getFriendRequests=function(req,res){
    console.log("Reporting from getFriendRequests");

    var email = req.param("email");

    mongo.connect(url, function() {

        var PendingRequests = mongo.collection('PendingRequests');
        var json_response= {};


        PendingRequests.find({"friend_email":email}).toArray(function(err, requests){
            if(err)
            {
                throw err;
                response={"statusCode" : 501};
                res.send(response);
            }

            else
            {
                response={"statusCode" : 200, "data":requests};
                res.send(response);
            }
        });
    });

};


exports.addByEmail=function(req,res){
    console.log("Reporting from addByEmail ");

    var email = req.param("email");
    var friend_email = req.param("friend_email");
    var first_name = req.param("first_name");


    mongo.connect(url, function() {
        var Users = mongo.collection('Users');
        var PendingRequests = mongo.collection('PendingRequests');
        var json_response= {};

        var request = {
            email:	email,
            friend_email:	friend_email,
            first_name : first_name
        };
        Users.findOne({
            "email": friend_email, active_ind:true
        }, function(err, user){
            if(user != null){
                PendingRequests.insert(request, function (err, result) {
                    if (err) {
                        throw err;
                        //db.close();
                    }

                    else if (result.insertedCount == 1) {
                        console.log("found user." + result[0])
                        response = {"statusCode": 200};
                        res.send(response);
                    }
                });
            }
            else {
                console.log("user does not exist");
                json_responses = {"statusCode" : 201, "message":"email not registered/user inactive"};
                res.send(json_responses);
            }
        });
    });

};

exports.browseFriends = function(req,res)
{
    console.log("Reporting from browseFriends");

    var email = req.param("email");

    mongo.connect(url, function() {

        var Friends = mongo.collection('Friends');
        var json_response= {};


        Friends.find({"email":email}).toArray(function(err, requests){
            if(err)
            {
                throw err;
                response={"statusCode" : 501};
                res.send(response);
            }

            else
            {
                response={"statusCode" : 200, "data":requests};
                res.send(response);
            }
        });
    });

};
