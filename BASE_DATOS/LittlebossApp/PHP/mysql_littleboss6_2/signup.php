<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['codigo']) && isset($_POST['descripcion']) && isset($_POST['fecharegistrop']) && isset($_POST['fechaEntrega']) && isset($_POST['cantidad']) && isset($_POST['costoEnvio']) && isset($_POST['cliente']) && isset($_POST['pagototal'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("pedido", $_POST['codigo'], $_POST['descripcion'], $_POST['fecharegistrop'], $_POST['fechaEntrega'], $_POST['cantidad'], $_POST['costoEnvio'], $_POST['cliente'], $_POST['pagototal'])) {
            echo "Registro Exitoso";
        } else echo "Registro Fallido";
    } else echo "Error en la base datos";
} else echo "Debe llenar todos los campos";
?>
