
<?php 

// mysql://b39eec050a4d07:50356f34@us-cdbr-iron-east-05.cleardb.net/heroku_4b241bdcff93b1e?reconnect=true

class Dao {

	const $host = "us-cdbr-iron-east-05.cleardb.net";
	const $db = "heroku_4b241bdcff93b1e";
	const $user = "b39eec050a4d07";
	const $pass = "50356f34";
  //protected $logger;
  
  public function getConnection() {
    return new PDO("mysql:host={$this->host};db={$this->db}", $this->user, $this->pass);
  }

  public function getContents() {
    $conn = $this->getConnection();
    return $conn->query("SELECT email, fullName, pw FROM users");
  }

  public function getContent($email) {
    $conn = $this->getConnection();
    $getQuery = "SELECT email, fullName, pw FROM users WHERE email = :email";
    $q = $conn->prepare($getQuery);
    $q->bindParam(":email", $email);
    $q->execute();
    return reset($q->fetchAll());
  }

  public function saveContent($email, $fullName, $pw) {
    $conn = $this->getConnection();
    $saveQuery = "INSERT INTO users (email, fullName, pw) VALUES (:email, :fullName, :pw)";
    $q = $conn->prepare($saveQuery);
    $q->bindParam(":email", $email);
    $q->bindParam(":fullName", $fullName);
    $q->bindParam(":pw", $pw);
    $q->execute();
  }

	// public function __construct () {
    // 	$this->logger = new KLogger('/Users/crk/projects/cs401/src/www', KLogger::DEBUG);
  	// }
	
  	// private function getConnection () {
    // 	try {
    //   		$conn = new PDO("mysql:host={$this->host};dbname={$this->db}", $this->user, $this->pass);
    //   		$this->logger->logDebug("Established a database connection.");
    //   		return $conn;
    // 	} catch (Exception $e) {
    //   		echo "connection failed: " . $e->getMessage();
    //   		$this->logger->logFatal("The database connection failed.");
    // 	}
  	// }
	  
  	// public function getComments () {
    //  	$conn = $this->getConnection();
    // 	$query = $conn->prepare("SELECT * FROM users");
    //  	$query->setFetchMode(PDO::FETCH_ASSOC);
    //  	$query->execute();
    //  	$results = $query->fetchAll();
    //  	$this->logger->logDebug(__FUNCTION__ . " " . print_r($results,1));
	// 	return $results;
  	// }

  	// public function saveComment ($email, $fullName, $pw ) {
    //  	$conn = $this->getConnection();
    //  	$query = $conn->prepare("INSERT INTO users (email, fullName, pw) VALUES (:email, :fullName, :pw)");
    //  	$query->bindParam(':email', $email);
    //  	$query->bindParam(':fullName', $fullName);
    //  	$query->bindParam(':pw', $pw);
    //  	$this->logger->logDebug(__FUNCTION__ . " name=[{$fullName}] comment=[{$email}]");
    //  	$query->execute();
	// }

  	// public function deleteComment ($email) {
    //  	$conn = $this->getConnection();
    //  	$query = $conn->prepare("DELETE FROM users WHERE email = :email");
    //  	$query->bindParam(':email', $email);
    //  	$query->execute();
    // }
    


}

?>