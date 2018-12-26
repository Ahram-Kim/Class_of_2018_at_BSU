<?php   

$thispage = "login";

session_start();

$email = $_POST['email'];
$pw = $_POST['pw'];
// $pw = hash("SHA256", $_POST['psw'] + " BCRYPT");

$errors = array();

// mysql://b921bc827ee598:f9c6c7f5@us-cdbr-iron-east-05.cleardb.net/heroku_2e2d618df3e2303?reconnect=true
// Database Connect
$host = 'us-cdbr-iron-east-05.cleardb.net';
$db = 'heroku_2e2d618df3e2303';
$user = 'b921bc827ee598';
$pass = 'f9c6c7f5';

$connection = mysqli_connect($host, $user, $pass, $db);
$my_query = ""; 
$my_query = "SELECT * FROM users WHERE email = '$email' AND pw = '$pw'";
$result = mysqli_query($connection, $my_query);

if($connection->connect_error) {
	exit("Error connecting to database"); 
}

function valid_length($field, $min, $max) {
	$trimmed = trim($field);
	return (strlen($trimmed) >= $min && strlen($trimmed) <= $max);
}

if(!valid_length($pw, 8, 50)) {
    $errors['pw'] = "Please enter a password of at least length 10.";
}

if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    $errors['email'] = "Must be a valid email address.";
}  

if (empty($pw)) { 
    $errors['pw'] = "Missing a password.";
}

if(empty($errors)) { 

    if(mysqli_num_rows($result) > 0){
        header('Location: ' . "http://aboutahram.herokuapp.com/granted.php");
        exit;
    }
    else{
        $errors['status'] = "Invalid Email or Password. Try again / Create an account.";
    }

} else {
	$_SESSION["errors"] = $errors;
	$_SESSION['presets'] = array('email' => htmlspecialchars($email),'pw' => htmlspecialchars($pw));
	header('Location: login.php');
}

?>