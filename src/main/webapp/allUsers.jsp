<%@ page import="org.example.startup.model.UserDetails" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>StartUp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="/assets/css/styles.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body class="custom-bg custom-text">
<section>
    <div class="mt-5 mb-3">
        <h1>&LT;FULL USER'S LIST&GT;</h1>
        <div class="d-flex justify-content-center mb-4 mt-4">
            <img src="assets/images/light-alarm-light-red-revolving-danger-security-safety-beacon-thumbnail-removebg-preview.png" width="2%">
        <h3 style="color:red">ADMIN ONLY</h3>
            <img src="assets/images/light-alarm-light-red-revolving-danger-security-safety-beacon-thumbnail-removebg-preview.png" width="2%">
        </div>
        <% List<UserDetails> userDetails = (List<UserDetails>) request.getAttribute("userDetails");
            System.out.println("Debug: UserDetails list received - " + userDetails);
            if (userDetails != null && !userDetails.isEmpty()) {
        %>
        <table class="table table-striped container-fluid" style="width: 95%">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Correo</th>
                <th scope="col">Nick</th>
                <th scope="col">Nombre</th>
                <th scope="col">Password</th>
                <th scope="col">Peso</th>
                <th scope="col">Creado en</th>
                <th scope="col">Actualizado en</th>
                <th scope="col">Dirección</th>
                <th scope="col">Numeración</th>
                <th scope="col">Rol</th>
            </tr>
            </thead>
            <tbody>
            <% for (UserDetails user : userDetails) { %>
            <tr>
                <td><%= user.getUserId() %>
                </td>
                <td><%= user.getCorreo() %>
                </td>
                <td><%= user.getNick() %>
                </td>
                <td><%= user.getNombre() %>
                </td>
                <td><%= user.getPassword() %>
                </td>
                <td><%= user.getPeso() %>
                </td>
                <td><%= user.getCreatedAt() %>
                </td>
                <td><%= user.getUpdatedAt() %>
                </td>
                <td><%= user.getDireccionNombre() %>
                </td>
                <td><%= user.getNumeracion() %>
                </td>
                <td><%= user.getRolNombre() %>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } else { %>
        <p style="color: red;"> No hay usuarios disponibles</p>
        <% } %>
    </div>
</section>
<section class="mt-4 text-center">
    <form action="index.jsp" method="GET">
        <button type="submit" class="btn btn-primary custom-wide-button">Volver</button>
    </form>
</section>

<script src="assets/js/script.js"></script>
</body>
</html>
