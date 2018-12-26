<?php

$thispage = "register";

session_start();

$fullName = $_POST['fullName'];
$email = $_POST['email'];
$pw = $_POST['pw'];
$pw_match = $_POST['pw_match'];

$errors = array();

// Database Connect
$host = 'us-cdbr-iron-east-05.cleardb.net';
$db = 'heroku_2e2d618df3e2303';
$user = 'b921bc827ee598';
$pass = 'f9c6c7f5';

$mysqli = new mysqli($host, $user, $pass, $db);

if($mysqli->connect_error) {
	exit('Error connecting to database'); 
}

try{
	$stmt = $mysqli->prepare("SELECT email FROM users WHERE email = ?");
	$stmt->bind_param("s", $_POST['email']);
	$stmt->execute();
	$stmt->store_result();

	if($stmt->num_rows > 0) {
		$errors['status'] = "That username already exists! please use a different email";
	}

	$stmt->close();
} catch (Exception $e){
	$errors['status'] = "Error Occured";
}

function valid_length($field, $min, $max) {
	$trimmed = trim($field);
	return (strlen($trimmed) >= $min && strlen($trimmed) <= $max);
}

if(!valid_length($fullName, 1, 100)) {
	$errors['fullName'] = "Name is required. Must be less than 100 characters.";
}

if (!preg_match("/^[a-zA-Z ]*$/", $fullName)) {
    $errors['fullName'] = "Only letters and white space allowed";
}

if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
	$errors['email'] = "Must be a valid email address.";
}

if (empty($pw)) {
	$errors['pw'] = "Password is required. Please enter a password of at least 8";
}

if(!valid_length($pw, 8, 50)) {
	$errors['pw'] = "Please enter a password of at least length 10.";
}


if($pw != $pw_match) {
	$errors['pw_match'] = "Passwords do not match.";
}

// echo $fullName, ' ', $email, ' ', $pw, ' ', $pw_match;

//if all valid, then redirect the user to the welcome page.
if(empty($errors)) {
	try{
		$stmt = $mysqli->prepare("INSERT INTO users (email, pw) VALUES (?, ?)");
		$stmt->bind_param("ss", $_POST['email'], $_POST['pw']);
		$stmt->execute();
		$_SESSION['username'] = $_POST['email'];
		header('Location: ' . "http://aboutahram.herokuapp.com/welcome.php");
		$stmt->close();
	} catch (Exception $e){
		$errors['status'] = "oops an error occurred while making your account";
		header('Location: ' . "http://aboutahram.herokuapp.com/signup.php");
		exit;
  	}

} else {
	$_SESSION["errors"] = $errors;
	$_SESSION['presets'] = array('fullName' => htmlspecialchars($fullName), 'email' => htmlspecialchars($email),
									'pw' => htmlspecialchars($pw), 'pw_match' => htmlspecialchars($pw_match));
	header('Location: signup.php');
}

?>
