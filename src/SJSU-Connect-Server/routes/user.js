var mongo = require('./mongo');
var ObjectId = require('mongodb').ObjectID;
var url = "mongodb://localhost:27017/Facebook";
var bcrypt = require('bcrypt-nodejs');

exports.login=function(req,res){
	console.log("Reporting from login function");
	
};

exports.register=function(req,res){
    console.log("Reporting from register new user function");


    var first_name = req.param("first_name");
    var last_name = req.param("last_name");
    var country_code = req.param("country_code");
    var phone_number = req.param("phone_number");
    var email = req.param("email");
    var password = req.param("password");
    var crp = bcrypt.hashSync(password);

    mongo.connect(url, function() {

        console.log('CONNECTED TO MONGO IN handle_get_property_list_request');
        var Users = mongo.collection('Users');
        var json_response= {};

        var user1 = {
            first_name:	first_name,
            last_name:	last_name,
            country_code : country_code,
            phone_number: phone_number,
            email : email,
            password : crp
        };
        Users.insert([user1], function (err, result) {
            if(err)
            {
                throw err;
                db.close();
            }

            else if(result.length)
            {
                console.log("found user." + result[0]);
                db.close();

                response={"statusCode" : 401};
                res.send(response);
            }
        });
    });
};

exports.forgotPassword=function(req,res){
	console.log("Reporting from forgot password function");
};

exports.editProfile=function(req,res){
	console.log("Reporting from editProfile function");
};
