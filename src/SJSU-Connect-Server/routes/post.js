/**
 * Created by vedant on 5/15/17.
 */
var mongo = require('./mongo');
var url = "mongodb://user1:user1@ds143231.mlab.com:43231/sjsu-connect";

//Updating status
exports.updateStatus = function(req, res) {
    console.log("Inside update status function");

    var email = req.param("email");
    var status = req.param("status");
    var date = Date.now();

    mongo.connect(url, function() {

        var Status = mongo.collection('Status');
        var json_response = {};

        Status.insert({
            "email": email,
            "status": status,
            "date": date
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