<?php
include_once 'API.php';

$username = $_GET['username'];
$password = $_GET['password'];

if (!isset($username) || !isset($password)) {
	echo "NULL";
} else {

	$api = new API();
	$api->onLogin($username, $password);

}

?>