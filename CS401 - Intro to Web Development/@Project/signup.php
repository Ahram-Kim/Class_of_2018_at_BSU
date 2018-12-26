<?php

$thispage = 'register';
//require_once('session-helper.php');
session_start();

/**
 * Prints error for given key (if one exists).
 */
function displayError($key) {
	if(isset($_SESSION['errors'][$key])) { ?>
		<span id="<?= $key . "Error" ?>" class="error"><?= $_SESSION['errors'][$key] ?></span>
	<?php }
	unset($_SESSION['errors'][$key]);	
}
?>

<?php
    require_once('header.php');
?>

        <div class = "content">
            <h2>Welcome to Join My Website!</h2>
            <hr>
            <style> .error {color: #FF0000;} </style>
            <p><span class = "error"> * requred field. </span></p>
            <div id = "signup">
                <form method = "POST" action = "signup-handler.php" autocomplete = "off">
                    <fieldset>
                    <p> <?php displayError('status'); ?> </p>
                    <p>
                        <label for = "fullName"> <span class="error">*</span> Your Name : </label>
                        <input type = "text" id = "fullName" name = "fullName" placeholder = "full Name" required>
                        <?php displayError('fullName'); ?>
                    </p>
                    <p>
                        <label for = "email"> <span class="error">*</span> Email : </label>
                        <input type = "email" id = "email" name = "email" placeholder = "Email" required>
                        <?php displayError('email'); ?>
                    </p>
                    <p>
                        <label for = "password"> <span class="error">*</span> Password : </label>
                        <input type = "password" id = "password" name = "pw" placeholder = "Password" required>
                        <?php displayError('password'); ?>
                    </p>
                    <p>
                        <label for = "pw_match"> <span class="error">*</span> Password again : </label>
                        <input type = "password" id = "pw_match" name = "pw_match" placeholder = "Password Again" required>
                        <?php displayError('pw_match'); ?>
                    </p>         
                    <input type = "submit" value = "Register" >
                    <?php displayError('exception'); ?>
                    </fieldset>         
                </form>
            </div>
            <br>
            <hr class = "layout">
        </div>
    
<?php
    require_once('footer.php');
?>

<?php clearErrors(); ?>