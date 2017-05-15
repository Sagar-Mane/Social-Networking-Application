
/*
 * GET home page.
 */

exports.index = function(req, res){
  res.render('index', { title: 'Express' });
};

exports.get_ping=function(req,res){
	console.log("Reporting from get ping function : Ping Succesful")
	var response={"message":"Get Ping Successful"};
	res.send(response);
}


exports.post_ping=function(req,res){
	console.log("Reporting from post ping function : Ping Succesful")
	console.log(req.param("hi"));
	
	var response={"message":"Post Ping Successful"};
	res.send(response);
};