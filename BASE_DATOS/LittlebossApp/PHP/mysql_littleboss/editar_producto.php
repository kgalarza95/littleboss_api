 <?php
    include 'conexion.php';

     $nombre=$_POST['nombre'];
     $codigo=$_POST['codigo'];
     $precio=$_POST['precio'];
     $cantidad=$_POST['cantidad'];
     $descripcion=$_POST['descripcion'];

    $consulta = "UPDATE articulos SET nombre = '".$nombre."', precio = '".$precio."', cantidad = '".$cantidad."', descripcion = '".$descripcion."' WHERE codigo = '".$codigo."'";
    mysqli_query($conexion,$consulta)or die (mysqli_error());
    mysqli_close($conexion);

?>