<?php   

$servername = "localhost";
$username = "root";
$password = "";
$DB_name = "sp-server";

//connessione al database  $conn=mysqli_connect($servername, $username, $password, $DB_name);
$conn=mysqli_connect($servername,$username, $password,$DB_name);
mysqli_query($conn, "SET NAMES 'UTF8'"); 

// Check connection
if (!$conn) {
    die ('Non riesco a connettermi: errore '.mysqli_connect_error()); // questo apparirà solo se ci sarà un errore
} 
?> 