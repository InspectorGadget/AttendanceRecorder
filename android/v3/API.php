<?php

class API {

	public $username, $password;

	public function __construct() {

	}

	public function checkIfExists($username): bool {
		$db = new SQLite3("database.db");
		$statement = "SELECT * FROM `users` WHERE `username` = '$username'";
		$result = $db->query($statement);
		if ($row = $result->fetchArray(1)) {
			return true;
		} else {
			return false;
		}
	}

	public function checkIfExistsOnStudent($username): bool {
		$db = new SQLite3("database.db");
		$statement = "SELECT * FROM `students` WHERE `username` = '$username'";
		$result = $db->query($statement);
		if ($row = $result->fetchArray(1)) {
			return true;
		} else {
			return false;
		}
	}	

	public function onLogin($username, $password) {
		$db = new SQLite3("database.db");
		$statement = "SELECT * FROM `users` WHERE `username` = '$username' AND `password` = '$password'";
		$result = $db->query($statement);
		if ($row = $result->fetchArray(1)) {
			echo "yes";
		} else {
			echo "no";
		}
	}

	public function onRegister($username, $password) {
		if ($this->checkIfExists($username) === false) {
			$db = new SQLite3("database.db");
			$statement = "INSERT INTO `users` (username, password) VALUES ('$username', '$password')";
			$result = $db->query($statement);
			if ($result) {
				echo "yes";
			} else {
				echo "no";
			}
		} else {
			echo "Username exists!";
		}
	}

	public function getOldInt($username) {
		$db = new SQLite3("database.db");
		if ($this->checkIfExistsOnStudent($username) === false) {
			return (int) 0;
		} else {
			$statement = "SELECT * FROM `students` WHERE `username` = '$username'";
			$result = $db->query($statement);
			if ($row = $result->fetchArray(1)) {
				return (int) $row['attendance'];
			}
		}
	}

	public function placeAttendance($username) {
		$count = 1;
		$n = $this->getOldInt($username) + 1;
		$new = "$n/31";
		$db = new SQLite3("database.db");
		if ($this->checkIfExistsOnStudent($username) === true) {
			$statement = "UPDATE `students` SET `attendance` = '$new' WHERE `username` = '$username'";
			$result = $db->query($statement);
			if ($result) {
				echo "yes";
			}
		} else {
			$statement = "INSERT INTO `students` (username, attendance) VALUES ('$username', '$new')";
			$result = $db->query($statement);
			if ($result) {
				echo "yes";
			}
		}
	}

}




