<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['codigo']) && isset($_POST['nombreCliente']) && isset($_POST['apellidoCliente']) && isset($_POST['correoCliente']) && isset($_POST['celularCliente']) && isset($_POST['ciudadCliente']) && isset($_POST['ubicacionCliente'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("cliente", $_POST['codigo'], $_POST['nombreCliente'], $_POST['apellidoCliente'], $_POST['correoCliente'], $_POST['celularCliente'], $_POST['ciudadCliente'], $_POST['ubicacionCliente'])) {
            echo "Registro Exitoso";
        } else echo "Registro Fallido";
    } else echo "Error en la base datos";
} else echo "Debe llenar todos los campos";
?>
