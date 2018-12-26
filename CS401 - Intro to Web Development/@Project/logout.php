<?php
// require_once('session-helper.php');

session_start();
session_destroy();
// If access not granted, update status and direct back to log in page.
// if(!validateSession()) {
//     $_SESSION["status"] = "You must log in.";
//     header("Location: login-index.php");
//     die;
// }

//logoutUser();
//header("Location: login-index.php");

?>

<?php
    require_once('header.php');
?>

        <div class = "content">
            <p>
                <h1>Bye! Hope to see you soon~</h1>
                <hr>
                <h2> Your session is logged out. <?= $user ?></h2>
                <li>
                    <a href = "login.php">LOG IN</a>
                </li>
            </p>
        </div>
    
<?php
    require_once('footer.php');
?>