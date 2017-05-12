var mongo = require('./mongo');
var ObjectId = require('mongodb').ObjectID;
var url = "mongodb://localhost:27017/Facebook";

exports.login=function(req,res){
	console.log("Reporting from login function");
	
};

exports.register=function(req,res){
    console.log("Reporting from register new user function");



    mongo.connect(url, function() {

        console.log('CONNECTED TO MONGO IN handle_get_property_list_request');
        var Users = mongo.collection('Users');
        var json_response= {};

        Users.find({ email: 'j.n@gmail.com'}).toArray(

            function(err, user) {
                json_responses = {

                    "Result" : user
                };
                console.log(user);
                res.send({statusCode:200});
            });
    });
};

exports.forgotPassword=function(req,res){
	console.log("Reporting from forgot password function");
};

exports.editProfile=function(req,res){
	console.log("Reporting from editProfile function");
};
