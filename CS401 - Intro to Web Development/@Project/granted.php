<?php

session_start();

// if(isset($_SESSION["access_granted"]) && !$_SESSION["access_granted"]) || !isset($_SESSION["access_granted"])) {
//     $_SESSION["status"] = "You need to log in first";
//     header("Location:login-index.php");
// }

// echo "ACCESS GRANTED";
?>

<?php
    require_once('header.php');
?>

        <div class = "content">
            <p>
                <h2>ACCESS GRANTED</h2>
                <hr>
                <br>
                <p> Log in was successful! </p> <br>
                <td> <a href = "home.php"> Go To Home!</a> </td>
                <br>
            </p>
        </div>
    
<?php
    require_once('footer.php');
?>