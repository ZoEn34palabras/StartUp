
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>StartUp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="/assets/css/styles.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body class="custom-bg custom-text">
<section>
    <div>
        <%
            String message = (String) request.getAttribute("message");
            String messageType = (String) request.getAttribute("messageType");
            if (message != null && messageType != null) {
        %>
        <div class="alert <%= messageType.equals("error") ? "alert-danger" : "alert-success" %>" role="alert">
            <%= message %>
        </div>
        <%
            }
        %>
        <form action="StartupServlet" method="POST" onsubmit="return validateForm()">
            <input type="hidden" name="action" value="register">
            <div class="data-form bg-tertiary mt-3">
                <h3>Ingrese sus datos</h3>
                <hr class="container">
                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="correo" class="form-label">Correo</label>
                        <input type="email" class="form-control" id="correo" name="correo" required>
                    </div>
                    <div class="col-md-6">
                        <label for="nick" class="form-label">Nick</label>
                        <input type="text" class="form-control" id="nick" name="nick" required>
                    </div>
                    <div class="col-md-6">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <div class="col-md-6">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <div class="row">  <div class="col-md-6">
                        <label for="nombreDireccion" class="form-label">Nombre Dirección</label>
                        <input type="text" class="form-control" id="nombreDireccion" name="nombreDireccion" required>
                    </div>
                        <div class="col-md-6">  <label for="numeracion" class="form-label">Numeración</label>
                            <input type="text" class="form-control" id="numeracion" name="numeracion" required>
                        </div>
                    </div>
                    <div class="col-md-6 mx-auto">
                        <label for="peso" class="form-label">Peso</label>
                        <input type="number" class="form-control" id="peso" name="peso" required>
                    </div>
                </div>
            </div>
            <hr class="container mt-2">
            <button type="submit" class="btn btn-primary custom-wide-button">Guardar</button>
            <div class="text-center">
                <a href="index.jsp" class="btn btn-primary custom-wide-button mx-auto mt-3">Volver</a>
            </div>
        </form>
    </div>
</section>
<script src="assets/js/script.js"></script>
</body>
</html>
