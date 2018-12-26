<?php

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

/**
 * Prints preset for given SESSION key (if one exists).
 */
function preset($key) {
	if(isset($_SESSION['presets'][$key]) && !empty($_SESSION['presets'][$key])) {
		echo 'value="' . $_SESSION['presets'][$key] . '" ';
	}
}

?>
 
<?php
    require_once('header.php');
?>

        <div class = "content">
            <p> 
                <h2>You Come Back to My Blog!</h2>
                <hr>
                <div id = "login">
                    <style> .error {color: #FF0000;} </style>
                    <p><span class = "error"> * requred field. </span></p>
                    <form method = "POST" action = "login-handler.php" autocomplete = "off">
                        <fieldset>
                        <p> <?php displayError('status'); ?> </p>
                        <p>
                            <label for = "email"> <span class="error">*</span> Email : </label>
                            <input type = "text" name = "email" id = "email" placeholder = "Email" <?php preset('email'); ?> required>
                            <?php displayError('email'); ?>
                            </span> 
                        </p>
                        <p> 
                            <label for = "password"> <span class="error">*</span> Password : </label>
                            <input type = "password" name = "pw" id = "pw" placeholder = "Password" required>
                            <?php displayError('password'); ?> 
                        </p>
                        <p>
                            <input name = "submit" type = "submit" id = "login" value = "Log In" />
                            <?php displayError('exception'); ?>
                        </p>
                        </fieldset>
                    </form>
                </div>
            </p>
            <hr class = "layout">
        </div> 
    
<?php
    require_once('footer.php');
?>
