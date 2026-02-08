<?php
include 'db_connect.php';

$roll_no = $_POST['roll_no'];
$name = $_POST['name'];
$email = $_POST['email'];
$class = $_POST['class'];
$subject1 = $_POST['subject1'];
$subject2 = $_POST['subject2'];
$subject3 = $_POST['subject3'];
$total = $_POST['total'];

$sql = "INSERT INTO students (roll_no, name, email, class, subject1, subject2, subject3, total)
VALUES ('$roll_no', '$name', '$email', '$class', '$subject1', '$subject2', '$subject3', '$total')";

if ($conn->query($sql) === TRUE) {
    echo "<script>alert('Student Record Added Successfully'); window.location='view_students.php';</script>";
} else {
    echo "Error: " . $conn->error;
}

$conn->close();
?>
