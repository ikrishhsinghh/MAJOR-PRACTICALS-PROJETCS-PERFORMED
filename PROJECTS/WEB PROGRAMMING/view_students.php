<?php
include 'db_connect.php';
$result = $conn->query("SELECT * FROM students");
?>

<!DOCTYPE html>
<html>
<head>
    <title>View Students</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="navbar">
    <h2>Student Records</h2>
    <div>
        <a href="index.html">Home</a>
        <a href="add_student.html">Add Student</a>
    </div>
</div>

<table>
<tr>
<th>ID</th>
<th>Roll No</th>
<th>Name</th>
<th>Email</th>
<th>Class</th>
<th>Total</th>
<th>Action</th>
</tr>

<?php while($row = $result->fetch_assoc()) { ?>
<tr>
<td><?php echo $row['id']; ?></td>
<td><?php echo $row['roll_no']; ?></td>
<td><?php echo $row['name']; ?></td>
<td><?php echo $row['email']; ?></td>
<td><?php echo $row['class']; ?></td>
<td><?php echo $row['total']; ?></td>
<td>
<a href="delete_student.php?id=<?php echo $row['id']; ?>" onclick="return confirm('Are you sure?')">Delete</a>
</td>
</tr>
<?php } ?>

</table>

</body>
</html>
