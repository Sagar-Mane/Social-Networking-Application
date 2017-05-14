var mongo = require('./mongo');
var ObjectId = require('mongodb').ObjectID;
var url = "mongodb://localhost:27017/Facebook";

exports.addNewFriend=function(req,res){
    console.log("Reporting from addNewFriend ");

    var email = req.param("email");
    var friend_email = req.param("friend_email");
    var friend_name = req.param("friend_name");

    mongo.connect(url, function() {

        var PendingRequests = mongo.collection('PendingRequests');
        var json_response= {};

        var request = {
            email:	email,
            friend_email:	friend_email,
            friend_name : friend_name
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
        });
    });

};

exports.getFriendRequests=function(req,res){
    console.log("Reporting from getFriendRequests");

    var email = req.param("email");

    mongo.connect(url, function() {

        var PendingRequests = mongo.collection('PendingRequests');
        var json_response= {};


        PendingRequests.find({"email":email}).toArray(function(err, requests){
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