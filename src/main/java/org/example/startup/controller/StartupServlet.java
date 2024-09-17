package org.example.startup.controller;

import org.example.startup.dao.StartupDAO;
import org.example.startup.model.UserDetails;
import org.example.startup.model.Usuarios;
import org.example.startup.model.Direcciones;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/StartupServlet")
public class StartupServlet extends HttpServlet {
    private StartupDAO startupDAO;

    public void init() {
        startupDAO = new StartupDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "register":
                    registerUser(request, response);
                    break;
                case "login":
                    loginUser(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String nick = request.getParameter("nick");
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        int peso = Integer.parseInt(request.getParameter("peso"));
        String nombreDireccion = request.getParameter("nombreDireccion");
        String numeracion = request.getParameter("numeracion");

        Usuarios existingUser = startupDAO.getUserByEmail(correo);
        if (existingUser != null) {
            request.setAttribute("messageType", "error");
            request.setAttribute("message", "El usuario ya está registrado en la base de datos.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        } else {
            // Using existing constructor
            Usuarios newUser = new Usuarios(0, correo, nick, nombre, password, peso, false);
            Direcciones newDireccion = new Direcciones(0, nombreDireccion, numeracion, 0);

            boolean registrationSuccess = startupDAO.registerUser(newUser, newDireccion);

            if (registrationSuccess) {
                request.setAttribute("messageType", "success");
                request.setAttribute("message", "Usuario registrado exitosamente.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("messageType", "error");
                request.setAttribute("message", "El registro falló. Por favor, intente nuevamente.");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            }
        }
    }
    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        Usuarios user = startupDAO.validateUser(correo, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("nombre", user.getNombre());
            session.setAttribute("correo", user.getCorreo());
            session.setAttribute("isAdmin", user.isAdmin());
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("messageType", "error");
            request.setAttribute("message", "Correo o contraseña inválidos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAdmin != null && isAdmin) {
            List<UserDetails> users = startupDAO.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("userList.jsp").forward(request, response);
        } else {
            response.sendRedirect("home.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}