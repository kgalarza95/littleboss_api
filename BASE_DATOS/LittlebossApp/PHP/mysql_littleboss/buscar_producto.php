<?php
    include 'conexion.php';

    $codigo=$_GET['codigo'];
    
    $consulta = "SELECT * FROM articulos WHERE codigo='$codigo'";
    $resultado = $conexion -> query($consulta);

    while ($fila=$resultado -> fetch_array()){
        $articulos[] = array_map('utf8_encode', $fila);

    }

    echo json_encode($articulos);
    $resultado -> close();


?>