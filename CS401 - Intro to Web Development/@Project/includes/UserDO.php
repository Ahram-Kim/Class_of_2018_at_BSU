<?php

class UserDO
{
	private $email;
	private $fullName;
	private $pw;
    
	public function __construct(array $row) {
		$this->email = isset($row['email']) ? $row['email'] : NULL;
		$this->pw = isset($row['pw']) ? $row['pw'] : NULL;
		$this->fullName = isset($row['fullName']) ? $row['fullName'] : NULL;
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
    
	public function asParamArray() {
		return [':email' => $this->email,
				':fullName' => $this->fullName,
				':pw' => $this->pw];
    }   
}
    
?>