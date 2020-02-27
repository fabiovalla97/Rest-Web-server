<?php

if (!isset($_SERVER["HTTP_REFERER"]))
header("location: ../index.php");
session_start();
include("db.php"); // includo il file di connessione al database

$message = "messaggio";
echo "<script type='text/javascript'>alert('$message');</script>";

$username = mysqli_real_escape_string ($conn , $_POST['Username']);
$email = mysqli_real_escape_string ($conn , $_POST['Email']);
$pass = mysqli_real_escape_string ($conn , $_POST['Password']);
$password=sha1($pass);
$birtday = mysqli_real_escape_string ($conn , $_POST['Birtday']);

//genera una stringa casuale
$characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
$charactersLength = strlen($characters);
$randomString = '';
for ($i = 0; $i < 15; $i++) {
    $randomString .= $characters[rand(0, $charactersLength - 1)];
}
$key=crypt($randomString,$username);

if (!email_exist($email)) die ("Email inesistente"); 
if( $username != "" && $password != "" && $email != "" && $birtday!=""){
   
    $sql1= mysqli_query($conn,"LOCK TABLES mytable WRITE;") or die ("query non riuscita".mysqli_error($conn));
    $query_email = mysqli_query ($conn, "SELECT email FROM mytable WHERE email='$email'") or die(mysqli_error($conn));
    $sql1= mysqli_query($conn,"UNLOCK TABLES;") or die ("query non riuscita".mysqli_error($conn));
    if (mysqli_num_rows($query_email) >= 1) die ('Email gia esistente');
    
    $sql1= mysqli_query($conn,"LOCK TABLES mytable WRITE;") or die ("query non riuscita".mysqli_error($conn));
    $query_username = mysqli_query ($conn, "SELECT username FROM mytable WHERE username='$username'") or die(mysqli_error($conn));
    $sql1= mysqli_query($conn,"UNLOCK TABLES;") or die ("query non riuscita".mysqli_error($conn));
    if (mysqli_num_rows($query_username) >= 1) die ('Username gia esistente'); 

    $sql1= mysqli_query($conn,"LOCK TABLES mytable WRITE;") or die ("query non riuscita".mysqli_error($conn));
    $query_registrazione = mysqli_query($conn, "INSERT INTO users (username,password,email,birtday,key)  VALUES ('$username','$password','$email','$birthday','$key')") or die ("query di registrazione non riuscita".mysqli_error($conn)); // se la query fallisce mostrami questo errore
    $sql1= mysqli_query($conn,"UNLOCK TABLES;") or die ("query non riuscita".mysqli_error($conn));

    $_SESSION['key'] = $randomString;
    $_SESSION['username'] = $surname;

    echo "ok";
}else{
    echo "Non hai compilato tutti i campi";
}

?>