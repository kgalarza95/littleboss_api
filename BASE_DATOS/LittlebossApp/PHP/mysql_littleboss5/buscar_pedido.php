<?php
    include 'conexion.php';

    $codigo=$_GET['codigo'];
    
    $consulta = "SELECT * FROM pedido WHERE codigo='$codigo'";
    $resultado = $conexion -> query($consulta);

    while ($fila=$resultado -> fetch_array()){
        $pedido[] = array_map('utf8_encode', $fila);

    }

    echo json_encode($pedido);
    $resultado -> close();


?>