
/**
 * Module dependencies.
 */

var express = require('express')
  , routes = require('./routes')
  , user = require('./routes/user')
  , friends = require('./routes/friends')
  , index= require('./routes/index')
  , http = require('http')
  , path = require('path')
  , message = require('./routes/message')
    , post = require('./routes/post');

var app = express();

// all environments
app.set('port', process.env.PORT || 3000);
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

app.get('/', routes.index);

/**
 * Ping Resources
 */
app.get('/get_ping',index.get_ping);
app.post('/post_ping',index.post_ping);

app.post('/login',user.login);
app.post('/register',user.register);
app.post('/forgotPassword',user.forgotPassword);
app.post('/editProfile',user.editProfile);
app.post('/validate', user.validate);
app.post('/addNewFriend', friends.addNewFriend);
app.get('/getFriendRequests', friends.getFriendRequests);
app.post('/addByEmail', friends.addByEmail);
app.get('/browseFriends', friends.browseFriends);
app.post('/rejectFriendRequests',friends.rejectFriendRequests);
app.post('/approveFriendRequests',friends.approveFriendRequests);
app.post('/sendMessage', message.sendMessage);
app.get('/getMessages', message.getMessages);
app.post('/follow',friends.follow);
app.post('/updateStatus', post.updateStatus);
app.get('/getTimeline',post.getTimeline);
app.get('/getPosts',post.getPosts);
app.get('/getProfile',user.getProfile);


http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});
