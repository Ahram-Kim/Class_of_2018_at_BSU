<?php
    require_once('header.php');
?>

        <div class = "content">
            <p>
                <h1>Welcome!</h1>
                <hr>
                <h2> Welcome! <?= $user ?></h2>
                <br>
                <td> <a href = "home.php"> Go To Home!</a> </td>
                <br>
            </p>
        </div>
    
<?php
    require_once('footer.php');
?>