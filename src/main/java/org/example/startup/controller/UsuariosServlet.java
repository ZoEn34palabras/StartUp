package org.example.startup.controller;

import org.example.startup.dao.StartupDAO;
import org.example.startup.model.UserDetails;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/UsuariosServlet")
public class UsuariosServlet extends HttpServlet {
    private StartupDAO startupDAO;

    public void init() {
        startupDAO = new StartupDAO();
        System.out.println("UsuariosServlet: Initialized");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UsuariosServlet: doGet method called");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("isAdmin") == null || !(Boolean) session.getAttribute("isAdmin")) {
            System.out.println("UsuariosServlet: Access denied - user is not an admin.");
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            System.out.println("UsuariosServlet: Before calling startupDAO.getAllUsers()");
            List<UserDetails> userDetails = startupDAO.getAllUsers();
            System.out.println("UsuariosServlet: After calling startupDAO.getAllUsers()");

            if (userDetails != null) {
                System.out.println("UsuariosServlet: userDetails is not null, size: " + userDetails.size());
                for (UserDetails user : userDetails) {
                    System.out.println("UsuariosServlet: User - ID: " + user.getUserId() + ", Email: " + user.getCorreo());
                }
            } else {
                System.out.println("UsuariosServlet: userDetails is null");
            }

            request.setAttribute("userDetails", userDetails);

        } catch (Exception e) {
            System.out.println("UsuariosServlet: Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("UsuariosServlet: Forwarding to allUsers.jsp");
        request.getRequestDispatcher("allUsers.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
