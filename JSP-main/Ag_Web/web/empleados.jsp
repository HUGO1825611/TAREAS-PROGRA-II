<%-- 
    Document   : con
    Created on : 6/09/2024, 5:53:32 p. m.
    Author     : Programador
--%>
<%@page import="java.util.HashMap"%>
<%@page import="modelo.Puesto" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Empleados</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    </head>
    <body>
           <header>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">Inicio</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        
        <li class="nav-item">
            <a class="nav-link" href="https://umg.edu.gt/" target="_blank" >UMG</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Menu
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="sin.jsp">Html</a></li>
            <li><a class="dropdown-item" href="empleados.jsp">Empleados</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Algo Nuevo</a></li>
          </ul>
        </li>
        
      </ul>
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
        </header>
        <h1>Formulario Empleados</h1>
        <div class="container" >
        <form action="#" method="get" class="form-group needs-validation" novalidate > <!-- post -->
               <label for="lbl_id"  class="form-label" ><b>ID</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control"    value="0"  readonly> 
                <label for="lbl_codigo"  class="form-label"><b>Codigo</b></label>
                <input type="text" name="txt_codigo" id="txt_codigo" class="form-control"  placeholder="Ejemplo: E001" pattern="[E]{1}[0-9]{3}" required>
                <label for="lbl_nombres" class="form-label" ><b>Nombres</b></label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Ejemplo: Nombre1 Nombre2" required>
                <label for="lbl_apellidos"  class="form-label" ><b>Apellidos</b></label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Ejemplo: Apellido1 Apellido2" required>
                <label for="lbl_direccion"  class="form-label"><b>Direccion</b></label>
                <input type="text"  name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Ejemplo: #Casa calle zona ciudad" required>
                <label for="lbl_telefono"  class="form-label"><b>Telefono</b></label>
                <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 5555555" required>
                <label for="lbl_fn"  class="form-label"><b>Nacimiento</b></label>
                <input type="date"  name="txt_fn" id="txt_fn" class="form-control"  required>
                <label for="lbl_puesto"  class="form-label"><b>Puesto</b></label>
                <select name="drop_puesto" id="drop_puesto" class="form-select" required  >
                    <option selected disabled value="">Seleccione...</option>
                    <% 
                        Puesto puesto = new Puesto();
                        HashMap<String,String> drop = puesto.drop_sangre();
                         for (String i:drop.keySet()){
                             out.println("<option value='" + i + "'>" + drop.get(i) + "</option>");
                         }
                         
                    
                    %>
                </select>
                <br>
                 <button name="btn_agregar" id="btn_agregar" class="btn btn-primary"  value="agregar" ><i class="bi bi-floppy"></i> Agregar</button>
                <button name="btn_modificar" id="btn_modificar" class="btn btn-warning"  value="modificar" ><i class="bi bi-pen"></i> Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" class="btn btn-danger" value="eliminar"  ><i class="bi bi-trash"></i> Eliminar</button>
        </form>
            
  <table class="table table-hover table-bordered border-primary">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">Codigo</th>
      <th scope="col">Nombres</th>
      <th scope="col">Apellidos</th>
      <th scope="col">Direccion</th>
      <th scope="col">Telefono</th>
      <th scope="col">Nacimiento</th>
      <th scope="col">Puesto</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>1</td>
      <td>E001</td>
      <td>Nombre Nombre</td>
      <td>Apellido Apellido</td>
      <td>Direccion Guatemala</td>
      <td>5555555</td>
      <td>1990-01-01</td>
      <td>Programador</td>
    </tr>
    <tr>
      <td>2</td>
      <td>E002</td>
      <td>Nombre Nombre2</td>
      <td>Apellido Apellido2</td>
      <td>Direccion2 Guatemala</td>
      <td>222222</td>
      <td>1998-08-08</td>
      <td>Programador</td>
    </tr>
  </tbody>
</table>
        </div>
        <script>
            (() => {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
})()
        </script>
        <script src="https://code.jquery.com/jquery-3.7.1.slim.min.js" integrity="sha256-kmHvs0B+OpCW5GVHUNjv9rOmY0IvSIRcf7zGUDTDQM8=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    </body>
</html>
