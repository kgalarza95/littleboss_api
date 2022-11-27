 <?php
    include 'conexion.php';

     $codigo=$_POST['codigo'];
     $nombreCliente=$_POST['nombreCliente'];
     $apellidoCliente=$_POST['apellidoCliente'];
     $correoCliente=$_POST['correoCliente'];
     $celularCliente=$_POST['celularCliente'];
     $ciudadCliente=$_POST['ciudadCliente'];
     $ubicacionCliente=$_POST['ubicacionCliente'];

    $consulta = "UPDATE cliente SET nombreCliente = '".$nombreCliente."', apellidoCliente = '".$apellidoCliente."', correoCliente = '".$correoCliente."', celularCliente = '".$celularCliente."', ciudadCliente = '".$ciudadCliente."', ubicacionCliente = '".$ubicacionCliente."' WHERE codigo = '".$codigo."'";
    mysqli_query($conexion,$consulta)or die (mysqli_error());
    mysqli_close($conexion);

?>