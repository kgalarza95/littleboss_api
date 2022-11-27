<?php
    include 'conexion.php';

    $codigo=$_POST['codigo'];
    $descripcion=$_POST['descripcion'];
    $fecharegistrop=$_POST['fecharegistrop'];
    $fechaEntrega=$_POST['fechaEntrega'];
    $cantidad=$_POST['cantidad'];
    $costoEnvio=$_POST['costoEnvio'];
    $cliente=$_POST['cliente'];
    $pagototal=$_POST['pagototal'];

    $consulta = "INSERT INTO pedido VALUES('".$codigo."','".$descripcion."','".$fecharegistrop."','".$fechaEntrega."','".$cantidad."','".$costoEnvio."','".$cliente."','".$pagototal."')";
    mysqli_query($conexion,$consulta) or die (mysqli_error());
    mysqli_close($conexion);



?>