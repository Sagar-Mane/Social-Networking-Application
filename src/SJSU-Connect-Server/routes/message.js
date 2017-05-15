var mongo = require('./mongo');
var ObjectId = require('mongodb').ObjectID;
//var url = "mongodb://localhost:27017/sjsu-connect";
var url = "mongodb://user1:user1@ds143231.mlab.com:43231/sjsu-connect";

exports.sendMessage=function(req,res){
    console.log("Reporting from sendMessage ");

    var sender_email_id = req.param("sender_email_id");
    var reciever_email_id = req.param("reciever_email_id");
    var sender_name = req.param("sender_name");
    var reciever_name = req.param("reciever_name");
    var text = req.param("text");
    var request ={
        sender_email_id:sender_email_id,
        reciever_email_id:reciever_email_id,
        text:text,
        "date":new Date(),
        view_ind: false,
        sender_name:sender_name,
        reciever_name:reciever_name
    };

    mongo.connect(url, function() {

        var Messages = mongo.collection('Messages');
        var json_response= {};

        Messages.insert(request, function (err, result) {
            if(err)
            {
                response={"statusCode" : 500};
                res.send(response);
            }
            else if(result.insertedCount ==1)
            {
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

exports.getMessages=function(req,res){
    console.log("Reporting from getMessages");

    var email = req.param("email");

    mongo.connect(url, function() {

        var Messages = mongo.collection('Messages');
        var json_response= {};


        Messages.find({"reciever_email_id":email}).toArray(function(err, requests){
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