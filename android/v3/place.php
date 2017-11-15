<?php
include_once 'API.php';

$username = $_GET['username'];

if (!isset($username)) {
	echo "NULL";
} else {

	$new  = str_replace("%20", " ", $username);
	$api = new API();
	$api->placeAttendance($new);

}

?>