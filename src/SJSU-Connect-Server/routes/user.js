var mongo = require('./mongo');
var ObjectId = require('mongodb').ObjectID;
//var url = "mongodb://localhost:27017/sjsu-connect";
var url = "mongodb://user1:user1@ds143231.mlab.com:43231/sjsu-connect";
var bcrypt = require('bcrypt-nodejs');
var nodemailer = require('nodemailer');
var AWS = require('aws-sdk');
var fs = require('fs');



exports.login=function(req,res){
    console.log("Reporting from login function");

    var email = req.param("email");
    var password = req.param("password");

    mongo.connect(url, function() {

        console.log('CONNECTED TO MONGO IN handle_get_property_list_request');
        var Users = mongo.collection('Users');
        var json_response= {};

        Users.find({email: email}).toArray(function(err,result){
            if(err)
            {
                throw err;
                res.code = "401";
                res.value = "Failed Login";
                //db.close();
                res.send({statusCode: "401"});
            }

            else
            {
                console.log("found user." + result[0]);

                if(bcrypt.compareSync(password, result[0].password) && result[0].active_ind == true)
                {
                    //db.close();
                    //req.session.username = msg.username;
                    res.code = "401";
                    res.value = "Succes Login";
                    res.send({statusCode: "200"});
                }
                else
                {
                    res.send({statusCode: "401", msg:"Account is not verified"});
                }
            }
        });
    });
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


    console.log("FIRSTNAME:- "+first_name);
    console.log("LASTNAME:- "+last_name);
    console.log("COUNTRY:- "+country_code);
    console.log("PHONE:- "+phone_number);
    console.log("EMAIL:- "+email);
    console.log("PASSWORD:- "+password);

    // create reusable transporter object using the default SMTP transport
    var transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: 'cmpe277spring@gmail.com',
            pass: 'P@ssword123'
        }
    });
    var verification_id = Math.floor(1000 + Math.random() * 9000);

    var mailOptions = {
        from: '277', // sender address
        to: email, // list of receivers
        subject: 'SJSU Connect Verification ', // Subject line
        text: 'Welcome aboard !! Please verify your account to start going social on SJSU', // plain text body
        html: '<b>Verification Code: </b>'+ verification_id
    };


    mongo.connect(url, function() {

        console.log('CONNECTED TO MONGO IN register');
        var Users = mongo.collection('Users');
        var json_response= {};

        var user1 = {
            first_name:	first_name,
            last_name:	last_name,
            country_code : country_code,
            phone_number: phone_number,
            email : email,
            password : crp,
            verification_id :verification_id,
            active_ind: false
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


            UpdatePhoto(email, "wonderland", function(err, result)  {
                if (err) {

                    console.log("Update did not succeed");
                    json_responses = {"statusCode" : 401};
                    res.send(json_responses);

                } else if (result){

                    console.log("Update successful");
                    json_responses = {"statusCode" : 200};
                    res.send(json_responses);

                }
            });
        });
    });
};

exports.validate=function(req,res){
    console.log("Reporting from validate function");
    var email = req.param("email");
    var verification_id = parseInt(req.param("verification_id"));

    mongo.connect(url, function(){
        console.log('Connected too mongo at: ' + url );
        var Users = mongo.collection('Users');
        Users.findOne({
            "email": email, "verification_id": verification_id
        }, function(err, user) {
            if (user != null) {
                Users.update({"email": email},
                    {$set: {"active_ind": true}},
                    function (err, user) {
                        var json_responses;
                        if (user.result.nModified == 1) {
                            json_responses = {"statusCode": 200};
                            res.send(json_responses);
                        }
                        else {
                            json_responses = {"statusCode": 500};
                            res.send(json_responses);
                        }
                    });
            }
            else {
                json_responses = {"statusCode": 401, "message": "Invalid authentication code"};
                res.send(json_responses);
            }
        });
    });
};


exports.getProfile=function(req,res){
    console.log("Reporting from getProfile function");

    var email = req.param("email");

    mongo.connect(url, function() {

        console.log('CONNECTED TO MONGO IN getProfile');
        var Users = mongo.collection('Users');

        Users.findOne({
            "email": email
        }, function(err, user){
            if(user != null){
                response={"statusCode" : 200, "data":user};
                res.send(response);
              }
            else{
                response = {"statusCode": 401,"message":"user does not exist"};
                res.send(response);
            }
        });
    });

};
exports.ping = function(req,res)
{
    console.log("CONNECTED TO PING PING")
    var temp = req.param("ping");
    console.log("+++++++++++++++++++++++++++++++++++++++++"+temp+"++++++++++++++++++++++++++++++++++++++++++++++++++++")
}



function UpdatePhoto(username, password, callback)
{
    var info = {user: username, pwd: password};
    AWS.config.loadFromPath('./tsconfig.json');

    AWS.config = new AWS.Config();
    AWS.config.accessKeyId = "AKIAJR43AOEDFYFWX4FQ";
    AWS.config.secretAccessKey = "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";
    AWS.config.region = "us-west-1";
    AWS.config.apiVersions = {
        "s3": "2006-03-01"
    }

    var s3 = new AWS.S3();

    var bodystream = fs.createReadStream('C:\\Users\\jnirg\\Desktop\\PAUL\\test.png');

    var params = {
        'Bucket': 'facebookandroid277project',
        'Key': username+ '/images/' + 'profile.png',
        'Body': bodystream,
        'ContentEncoding': 'base64',
        Metadata: {
            'Content-Type': 'image/jpeg'
        }

    };

    //also tried with s3.putObject
    s3.upload(params, function(err, data){
        console.log('after s3 upload====', err, data);
        callback(err,data);
    })
}
