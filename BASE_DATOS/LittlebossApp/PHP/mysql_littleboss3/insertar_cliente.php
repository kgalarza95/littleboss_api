<?php
    include 'conexion.php';

    $codigo=$_POST['codigo'];
    $nombreCliente=$_POST['nombreCliente'];
    $apellidoCliente=$_POST['apellidoCliente'];
    $correoCliente=$_POST['correoCliente'];
    $celularCliente=$_POST['celularCliente'];
    $ciudadCliente=$_POST['ciudadCliente'];
    $ubicacionCliente=$_POST['ubicacionCliente'];


    $consulta = "INSERT INTO cliente VALUES('".$codigo."','".$nombreCliente."','".$apellidoCliente."','".$correoCliente."','".$celularCliente."','".$ciudadCliente."','".$ubicacionCliente."')";
    mysqli_query($conexion,$consulta) or die (mysqli_error());
    mysqli_close($conexion);



?>