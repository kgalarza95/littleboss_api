<?php
    include 'conexion.php';

    $codigo=$_GET['codigo'];
    
    $consulta = "SELECT * FROM cliente WHERE codigo='$codigo'";
    $resultado = $conexion -> query($consulta);

    while ($fila=$resultado -> fetch_array()){
        $cliente[] = array_map('utf8_encode', $fila);

    }

    echo json_encode($cliente);
    $resultado -> close();


?>