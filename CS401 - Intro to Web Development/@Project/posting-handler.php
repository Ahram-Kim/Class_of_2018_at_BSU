<?php

$thispage = "posting";
session_start();

// define variables and set to empty values
$pfname = $_POST['pfname'];
$title = $_POST['title'];
$gender = $_POST['gender'];
$comments = $_POST['comments'];

$errors = array();

// Database Connect
$host = 'us-cdbr-iron-east-05.cleardb.net';
$db = 'heroku_2e2d618df3e2303';
$user = 'b921bc827ee598';
$pass = 'f9c6c7f5';

$mysqli = new mysqli($pfname, $title, $gender, $comments);

// $connection = mysqli_connect($pfname, $title, $gender, $comments);
// $my_query = "";
// $my_query = "SELECT * FROM posting WHERE pfname = '$pfname'";

// $result = mysqli_query($connection, $my_query);

if($mysqli->connect_error) {
	exit('Error connecting to database'); 
}

try{
	$stmt = $mysqli->prepare("SELECT pfname FROM posting WHERE pfname = $pfname");
	$stmt->bind_param("s", $_POST['pfname']);
	$stmt->execute();
	$stmt->store_result();

	if($stmt->num_rows>0) {
		$errors['status'] = "That posting already exists!";
	}

	$stmt->close();
} catch (Exception $e){
	$errors['status'] = "Error Occured";
}

if (empty($pfname)) {
  $errors['pfname'] = "Name is required. You can use your nickname or email.";
} 

if (empty($title)) {
  $errors['title'] = "Title is required. You can use your nickname or email.";
} 

if (empty($gender)) {
  $errors['gender'] = "Gender is required.";
}

if (empty($comments)) {
  $errors['comments'] = "Comment is required.";
}

// if (empty($website)) {
//   $website = "";
// } else {
//   // check if URL address syntax is valid (this regular expression also allows dashes in the URL)
//   if (!preg_match("/\b(?:(?:https?|ftp):\/\/|www\.)[-a-z0-9+&@#\/%?=~_|!:,.;]*[-a-z0-9+&@#\/%=~_|]/i",$website)) {
//     $errors['website'] = "Invalid URL address syntax.";
//   }
// }

//if all valid, then redirect the user to the welcome page.
if(empty($errors)) {
	try{

		// $my_query = "INSERT INTO posting(pfname, title, gender, comments) VALUES ('$pfname', '$title', '$gender', '$comments')";
		// $result = mysqli_query($connection, $my_query);
		// if($result) {
		// 	echo "Account Creaeted";
		// }	


		$stmt = $mysqli->prepare("INSERT INTO posting (pfname, title, gender, comments) VALUES (?, ?, ?, ?)");
		$stmt->bind_param("ssss", $_POST['pfname'], $_POST['title'], $_POST['gender'], $_POST['comments']);
		$stmt->execute();
		header('Location: ' . "http://aboutahram.herokuapp.com/sharing.php");
		$stmt->close();
	} catch (Exception $e){
		$errors['status'] = "oops an error occurred while making your account";
		header('Location: ' . "http://aboutahram.herokuapp.com/posting.php");
		exit;
  	}

} else {
	$_SESSION["errors"] = $errors;
	$_SESSION['presets'] = array('pfname' => htmlspecialchars($pfname), 'title' => htmlspecialchars($title),
									'gender' => htmlspecialchars($gender), 'comments' => htmlspecialchars($comments));
	header('Location: posting.php');
}

?>