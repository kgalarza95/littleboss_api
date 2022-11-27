<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['nombre']) && isset($_POST['codigo']) && isset($_POST['precio']) && isset($_POST['cantidad']) && isset($_POST['descripcion'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("articulos", $_POST['nombre'], $_POST['codigo'], $_POST['precio'], $_POST['cantidad'], $_POST['descripcion'])) {
            echo "Registro Exitoso";
        } else echo "Registro Fallido";
    } else echo "Error en la base datos";
} else echo "Debe llenar todos los campos";
?>
