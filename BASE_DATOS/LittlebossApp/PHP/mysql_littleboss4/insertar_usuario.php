<?php
    include 'conexion.php';

    $Nombres=$_POST['Nombres'];
    $Apellidos=$_POST['Apellidos'];
    $codigo=$_POST['codigo'];
    $username=$_POST['username'];
    $password=$_POST['password'];


    $consulta = "INSERT INTO usuario VALUES('".$Nombres."','".$Apellidos."','".$codigo."','".$username."','".$password."')";
    mysqli_query($conexion,$consulta) or die (mysqli_error());
    mysqli_close($conexion);



?>