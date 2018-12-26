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

?>

<?php
    require_once('header.php');
?>

        <div class = "content">
            <h2> Share Your Story!</h2>
            <hr>
            <style> .error {color: #FF0000;} </style>
            <p><span class = "error"> * requred field. </span></p>
            <div id = "posting">
                <form method="post" action="posting-handler.php">
                <fieldset>
                    <p> <?php displayError('status'); ?> </p>
                    <p>
                        <label for = "name"> <span class="error">*</span> Username or Email : </label>
                        <input type = "text" id = "name" name = "name" placeholder = "prefer name">
                        <?php displayError('name'); ?>
                    </p>
					<p>
                        <label for = "title"> <span class="error">*</span> Title : </label>
                        <input type = "text" id = "title" name = "title" placeholder = "Title">
                        <?php displayError('title'); ?>
                    </p>
                    <p>
                        <label for = "gender"> <span class="error">* </span>  Gender: </label>
                        <input type="radio" name="gender" <?php if (isset($gender) && $gender=="female") echo "checked";?> value="female">Female
                        <input type="radio" name="gender" <?php if (isset($gender) && $gender=="male") echo "checked";?> value="male">Male
                        <input type="radio" name="gender" <?php if (isset($gender) && $gender=="other") echo "checked";?> value="other">Other 
                    </p>
                    <p> 
                        <label for = "comments" runat = "server"> <span class="error">*</span> Comment: </label> 
                        <textarea name="comment" width = "800px" height = "1000px" style = "margin: 0px 110px; padding:15px 20px">   

                        </textarea>
                        <?php displayError('comment'); ?>
                    </p>         
                    <input type="submit" name="submit" value="Submit"> <br>
                    </fieldset> 
                </form>
            </div>
            <br>
            <hr class = "layout">
        </div>
    
<?php
    require_once('footer.php');
?>
