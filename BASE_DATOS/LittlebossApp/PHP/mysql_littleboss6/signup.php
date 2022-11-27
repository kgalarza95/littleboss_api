<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['Nombres']) && isset($_POST['Apellidos']) && isset($_POST['codigo']) && isset($_POST['username']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("usuario", $_POST['Nombres'], $_POST['Apellidos'], $_POST['codigo'], $_POST['username'], $_POST['password'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
