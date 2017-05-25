/**
 * Created by vedant on 5/15/17.
 */
var mongo = require('./mongo');
//var url = "mongodb://user1:user1@ds143231.mlab.com:43231/sjsu-connect";
var url = "mongodb://sjsuconnectadmin:sagar123@sjsu-connect-shard-00-00-qbrqn.mongodb.net:27017,sjsu-connect-shard-00-01-qbrqn.mongodb.net:27017,sjsu-connect-shard-00-02-qbrqn.mongodb.net:27017/sjsu-connect-primary?ssl=true&replicaSet=sjsu-connect-shard-0&authSource=admin";

//Updating status
exports.updateStatus = function(req, res) {
    console.log("Inside update status function");

    var email = req.param("email");
    var status = req.param("status");
    var first_name = req.param("first_name");
    var last_name = req.param("last_name");
    var date = Date.now();

    mongo.connect(url, function() {

        var Status = mongo.collection('Status');
        var json_response = {};
        /*var d = new Date();
        var curr_date = d.getDate();
        var curr_month = d.getMonth();
        var curr_year = d.getFullYear();
        var date = curr_month + "-" + curr_date + "-" + curr_year;*/

        Status.insert({
            "email": email,
            "status": status,
            "first_name":first_name,
            "last_name":last_name,
            "date":new Date(),
        }, function(err, status) {
            if(status){
                console.log("Status updated");
                json_response = {"statusCode" : 200};
                res.send(json_response);
            } else {
                json_response = {"statusCode" : 401};
                res.send(json_response);
            }

            });
    });

};

exports.getTimeline = function(req, res) {
    console.log("Inside getTimeline function");

    var email = req.param("email");

    mongo.connect(url, function() {

        var Friends = mongo.collection('Friends');
        var json_response = {};
        Friends.find({"email_id":email}, { friend_email_id: 1,_id: 0}).toArray(function(err, friends){
            if(err)
            {
                response={"statusCode" : 501};
                res.send(response);
            }

            else
            {
                var params =[];
                friends.forEach(function callback(currentValue, index, array) {
                    params.push(currentValue.friend_email_id);
                });

                params.push(email);
                if(params.length>0){
                    var Status = mongo.collection('Status');
                    Status.find({"email": {$in: params}}).sort( { "date": -1 } ).toArray(function(err, posts){
                        if(err)
                        {
                            response={"statusCode" : 501};
                            res.send(response);
                        }

                        else
                        {
                            response={"statusCode" : 200, "data":posts};
                            res.send(response);
                        }
                    });
                }
                else {
                    response={"statusCode" : 200, "posts":[]};
                    res.send(response);
                }
            }
        });

    });

};

exports.getPosts = function(req, res) {
    console.log("Inside getPosts function");

    var email = req.param("email");
    mongo.connect(url, function() {
        var json_response = {};
        var Status = mongo.collection('Status');
        Status.find({"email": email}).sort( { "date": -1 } ).toArray(function(err, posts){
            if(err)
            {
                json_response={"statusCode" : 501};
                res.send(json_response);
            }
            else
            {
                json_response={"statusCode" : 200, "data":posts};
                res.send(json_response);
            }
        });
    });
};