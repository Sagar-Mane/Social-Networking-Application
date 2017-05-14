var mongo = require('./mongo');
var ObjectId = require('mongodb').ObjectID;
var url = "mongodb://localhost:27017/Facebook";
var bcrypt = require('bcrypt-nodejs');
var nodemailer = require('nodemailer');

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

    // create reusable transporter object using the default SMTP transport
    var transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: 'cmpe277spring@gmail.com',
            pass: 'P@ssword123'
        }
    });
    var val = Math.floor(1000 + Math.random() * 9000);
    console.log(val);

    var mailOptions = {
        from: '277', // sender address
        to: email, // list of receivers
        subject: 'Hello ✔', // Subject line
        text: 'Hello world ?', // plain text body
        html: '<b>Your Validation code: </b>'+ val // html body
    };


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
            password : crp,
            verification_id :val
        };
        Users.findOne({
            "email": email
        }, function(err, user){
            if(user != null){
                console.log("user exists");
                json_responses = {"statusCode" : 401, "message":"email already registered"};
                res.send(json_responses);
            }
            else{
                Users.insert([user1], function (err, result) {
                    if(err)
                    {
                        throw err;
                        db.close();
                    }

                    else if(result.insertedCount ==1)
                    {
                        console.log("found user." + result[0]);
                        //db.close();
                        transporter.sendMail(mailOptions, function(error, info)  {
                            if (error) {
                                return console.log(error);
                            }
                            console.log('Message %s sent: %s', info.messageId, info.response);
                          });

                        response={"statusCode" : 200};
                        res.send(response);
                    }
                });
            }
        });
    });

};

exports.forgotPassword=function(req,res){
	console.log("Reporting from forgot password function");
};

exports.editProfile=function(req,res){
	console.log("Reporting from editProfile function");
    var first_name = req.param("first_name");
    var last_name = req.param("last_name");
    var country_code = req.param("country_code");
    var phone_number = req.param("phone_number");
    var email = req.param("email");
    var age = req.param("age");
    var profession = req.param("profession");
    var bio = req.param("bio");
    var type = req.param("type");
    var interests = req.param("interests");

    var post  = {first_name: first_name, last_name : last_name, country_code: country_code, phone_number: phone_number,
        age:age,profession:profession, bio:bio, type:type, interests:interests};
    mongo.connect(url, function(){
        console.log('Connected too mongo at: ' + url );
        var Users = mongo.collection('Users');
        Users.update({"email" :email},{$set:{
            "first_name": first_name,
            "last_name": last_name,
            "country_code": country_code,
            "phone_number": phone_number,
            "age":age,
            "profession":profession,
            "bio":bio,
            "type":type,
            "interests":interests}
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
