var express = require('express');
var app = express();
var fs = require("fs");

var server = app.listen(5000, function () {
   console.log("Express App running at http://127.0.0.1:5000/");
})

//to get all users
app.get('/', function (req, res) {
   fs.readFile( __dirname + "/" + "users.json", 'utf8', function (err, data) {
      res.end( data );
   });
})

//to get a specific user
app.get('/:id', function (req, res) {
   fs.readFile( __dirname + "/" + "users.json", 'utf8', function (err, data) {
      var users = JSON.parse( data );
	  console.log(users)
      var user = users["user" + req.params.id] 
	  console.log("User: " + req.params.id);
      res.end( JSON.stringify(user));
   });
})

// to add a user
var bodyParser = require('body-parser')
app.use( bodyParser.json() );      
app.use(bodyParser.urlencoded({  extended: true }));

app.post('/', function (req, res) {
   fs.readFile( __dirname + "/" + "users.json", 'utf8', function (err, data) {
      var users = JSON.parse( data );
      var user = req.body.user4;
      users["user"+user.id] = user
      res.end( JSON.stringify(users));
   });
})

//Delete an user
app.delete('/:id', function (req, res) {
   fs.readFile( __dirname + "/" + "users.json", 'utf8', function (err, data) {
      data = JSON.parse( data );
      var id = "user"+req.params.id;
      var user = data[id];
      delete data[ "user"+req.params.id];
      res.end( JSON.stringify(data));
   });
})

//Update user
app.put("/:id", function(req, res) {
   fs.readFile( __dirname + "/" + "users.json", 'utf8', function (err, data) {
      
      var users = JSON.parse( data );
      var id = "user"+req.params.id;      
      users[id]=req.body;
      res.end( JSON.stringify(users));
   })
})