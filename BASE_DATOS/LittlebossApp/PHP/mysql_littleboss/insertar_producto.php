<?php
    include 'conexion.php';

    $nombre=$_POST['nombre'];
    $codigo=$_POST['codigo'];
    $precio=$_POST['precio'];
    $cantidad=$_POST['cantidad'];
    $descripcion=$_POST['descripcion'];

    $consulta = "INSERT INTO articulos VALUES('".$nombre."','".$codigo."','".$precio."','".$cantidad."','".$descripcion."')";
    mysqli_query($conexion,$consulta) or die (mysqli_error());
    mysqli_close($conexion);



?>