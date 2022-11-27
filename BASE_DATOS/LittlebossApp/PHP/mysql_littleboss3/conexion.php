<?php
    $hostname='localhost';
    $database='mysql_littleboss';
    $username='root';
    $password='';

    $conexion=new mysqli($hostname,$username,$password,$database);
    if($conexion->connect_errno){
        echo "Lo sentimos, el sitio web está experimentando problemas";
    }

?>