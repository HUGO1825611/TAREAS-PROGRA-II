<%-- 
    Document   : index
    Created on : 1/09/2024, 12:34:02 p. m.
    Author     : Programador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Html</title>
    </head>
    <body>
        <a href="index.jsp">Inicio</a>
        <h1>Formulario Empleados</h1>
        
        <form action="#" method="get" > <!-- post -->
               <label for="lbl_id" ><b>ID</b></label>
                <input type="text" name="txt_id" id="txt_id"   value="0"  readonly> 
                <label for="lbl_codigo" ><b>Codigo</b></label>
                <input type="text" name="txt_codigo" id="txt_codigo"  placeholder="Ejemplo: E001" pattern="[E]{1}[0-9]{3}" required>
                <label for="lbl_nombres" ><b>Nombres</b></label>
                <input type="text" name="txt_nombres" id="txt_nombres"  placeholder="Ejemplo: Nombre1 Nombre2" required>
                <label for="lbl_apellidos" ><b>Apellidos</b></label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" placeholder="Ejemplo: Apellido1 Apellido2" required>
                <label for="lbl_direccion" ><b>Direccion</b></label>
                <input type="text"  name="txt_direccion" id="txt_direccion" placeholder="Ejemplo: #Casa calle zona ciudad" required>
                <label for="lbl_telefono" ><b>Telefono</b></label>
                <input type="number" name="txt_telefono" id="txt_telefono" placeholder="Ejemplo: 5555555" required>
                <label for="lbl_fn" ><b>Nacimiento</b></label>
                <input type="date"  name="txt_fn" id="txt_fn"  required>
                <label for="lbl_puesto" ><b>Puesto</b></label>
                <select name="drop_puesto" id="drop_puesto"  >
                    <option value="1">Programador</option>
                    <option value="2">Analista</option>
                </select>
                 <button name="btn_agregar" id="btn_agregar"  value="agregar" >Agregar</button>
                <button name="btn_modificar" id="btn_modificar"  value="modificar" >Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar"  >Eliminar</button>
        </form>
    </body>
</html>
