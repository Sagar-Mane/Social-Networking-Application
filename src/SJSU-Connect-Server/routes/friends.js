var mongo = require('./mongo');
var ObjectId = require('mongodb').ObjectID;
//var url = "mongodb://localhost:27017/sjsu-connect";
var url = "mongodb://user1:user1@ds143231.mlab.com:43231/sjsu-connect";

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
                response={"statusCode" : 401};
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
                        response = {"statusCode": 500};
                        res.send(response);
                    }

                    else if (result.insertedCount == 1) {
                        response = {"statusCode": 200};
                        res.send(response);
                    }
                });
            }
            else {
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

exports.rejectFriendRequests=function(req,res){
    console.log("Reporting from rejectFriendRequests function");
    var user_email = req.param("email");
    var friend_email = req.param("friend_email");

    mongo.connect(url, function(){
        console.log('Connected too mongo at: ' + url );
        var PendingRequests = mongo.collection('PendingRequests');
        PendingRequests.remove({"friend_email" :user_email, "email": friend_email
        }, function(err, user){
            var json_responses;
            if(err){
                json_responses = {"statusCode" : 401};
                res.send(json_responses);
            }
            else
            {
                json_responses = {"statusCode" : 200};
                res.send(json_responses);
            }
        });
    });
};

exports.approveFriendRequests=function(req,res){
    console.log("Reporting from approveFriendRequests ");

    var email = req.param("email");
    var friend_email = req.param("friend_email");


    mongo.connect(url, function() {
        var PendingRequests = mongo.collection('PendingRequests');
        var json_response= {};

        PendingRequests.findOne({
            "friend_email": email, email:friend_email
        }, function(err, user){
            var temp = user;
            var request = {
                email:	email,
                friend_email:	friend_email,
                first_name : temp.first_name,
                friend_status : "add"
            };
            if(user != null){
                PendingRequests.remove({"friend_email" :email, "email": friend_email
                }, function(err, user){
                    var json_responses;
                    if(err){
                        json_responses = {"statusCode" : 401};
                        res.send(json_responses);
                    }
                    else
                    {
                        var Friends = mongo.collection('Friends');
                        Friends.insert(request, function (err, result) {
                            if (err) {
                                json_responses = {"statusCode" : 500};
                                res.send(json_responses);
                            }

                            else if (result.insertedCount == 1) {
                                console.log("found user." + result[0])
                                response = {"statusCode": 200};
                                res.send(response);
                            }
                        });
                    }
                });

            }
            else {
                json_responses = {"statusCode" : 401,"message":"pending request not found"};
                res.send(json_responses);
            }
        });
    });

};


exports.follow=function(req,res){
    console.log("Reporting from follow ");

    var email = req.param("email");
    var friend_email = req.param("friend_email");
    var first_name = req.param("first_name");

    mongo.connect(url, function() {
        var Friends = mongo.collection('Friends');
        var json_response= {};
        var request = {
            email:	email,
            friend_email:	friend_email,
            first_name : first_name,
            friend_status : "follow"
        };
        Friends.insert(request, function (err, result) {
            if (err) {
                json_responses = {"statusCode" : 500};
                res.send(json_responses);
            }

            else if (result.insertedCount == 1) {
                console.log("found user." + result[0])
                response = {"statusCode": 200};
                res.send(response);
            }
        });
    });

};