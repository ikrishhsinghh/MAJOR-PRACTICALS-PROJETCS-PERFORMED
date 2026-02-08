<?php
$conn = new mysqli("localhost", "root", "", "reportcard");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>
