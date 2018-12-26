<?php

class UserDO
{
	private $email;
	private $pw;
    private $fullName;
    
	public function __construct(array $row) {
		$email = $row['email'];
		$pw = $row['pw'];
		$fullName = $row['fullName'];
	}

	public function setEmail($email) {
		$this->email = $email;
    }
    
	public function getEmail() {
		return $this->email;
    }
    
	public function setPw($pw) {
		$this->pw = $pw;
    }
    
	public function getPw() {
		return $this->pw;
    }
    
	public function setfullName($fullName) {
		$this->fullName = $fullName;
    }
    
	public function getfullName() {
		return $this->fullName;
    }
    
}

?>