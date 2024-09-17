package org.example.startup.dao;

import org.example.startup.database.DBConnection;
import org.example.startup.model.UserDetails;
import org.example.startup.model.Usuarios;
import org.example.startup.model.Direcciones;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StartupDAO {

    public StartupDAO() {
        ensureDefaultRoles();
    }

    public Usuarios getUserByEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuarios(
                        rs.getInt("id"),
                        rs.getString("correo"),
                        rs.getString("nick"),
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getInt("peso"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getBoolean("isAdmin")

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void ensureDefaultRoles() {
        String sqlCheck = "SELECT COUNT(*) FROM roles WHERE nombre = ?";
        String sqlInsert = "INSERT INTO roles (nombre) VALUES (?)";
        String[] defaultRoles = {"user", "admin"};

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(sqlCheck);
             PreparedStatement insertStmt = conn.prepareStatement(sqlInsert)) {
            for (String role : defaultRoles) {
                checkStmt.setString(1, role);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    insertStmt.setString(1, role);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(Usuarios user, Direcciones direccion) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Insert into usuarios table
            String sqlUsuarios = "INSERT INTO usuarios (correo, nick, nombre, password, peso, is_admin, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";
            pstmt = conn.prepareStatement(sqlUsuarios, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getCorreo());
            pstmt.setString(2, user.getNick());
            pstmt.setString(3, user.getNombre());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getPeso());
            pstmt.setBoolean(6, user.isAdmin());
            pstmt.executeUpdate();

            // Get the generated user ID
            rs = pstmt.getGeneratedKeys();
            int userId = -1;
            if (rs.next()) {
                userId = rs.getInt(1);
                System.out.println("Generated User ID: " + userId); // Logging userId
            }

            // Insert into direcciones table
            String sqlDirecciones = "INSERT INTO direcciones (nombre, numeracion, usuario_id) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sqlDirecciones);
            pstmt.setString(1, direccion.getNombre());
            pstmt.setString(2, direccion.getNumeracion());
            pstmt.setInt(3, userId);
            pstmt.executeUpdate();

            // Insert default role for user
            String sqlRoles = "INSERT INTO roles_usuarios (usuario_id, rol_id) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sqlRoles);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, user.isAdmin() ? 2 : 1); // 2 for admin, 1 for regular user
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected in roles_usuarios: " + rowsAffected); // Logging rowsAffected

            // Commit the transaction
            conn.commit();
            return true;

        } catch (SQLException e) {
            // If there's an error, rollback the transaction
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Usuarios validateUser(String email, String password) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuarios(
                        rs.getInt("id"),
                        rs.getString("correo"),
                        rs.getString("nick"),
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getInt("peso"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getBoolean("isAdmin")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserDetails> getAllUsers() {
        System.out.println("StartupDAO: getAllUsers method called");
        List<UserDetails> userDetailsList = new ArrayList<>();
        String sql = "SELECT u.id, u.correo, u.nick, u.nombre, u.password, u.peso, u.created_at, u.updated_at, " +
                "d.nombre AS direccion_nombre, d.numeracion, r.nombre AS rol_nombre " +
                "FROM usuarios u " +
                "LEFT JOIN direcciones d ON u.id = d.usuario_id " +
                "LEFT JOIN roles_usuarios ru ON u.id = ru.usuario_id " +
                "LEFT JOIN roles r ON ru.rol_id = r.id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("StartupDAO: Query executed");

            while (rs.next()) {
                UserDetails userDetails = new UserDetails();
                userDetails.setUserId(rs.getInt("id"));
                userDetails.setCorreo(rs.getString("correo"));
                userDetails.setNick(rs.getString("nick"));
                userDetails.setNombre(rs.getString("nombre"));
                userDetails.setPassword(rs.getString("password"));
                userDetails.setPeso(rs.getInt("peso"));
                userDetails.setCreatedAt(rs.getTimestamp("created_at"));
                userDetails.setUpdatedAt(rs.getTimestamp("updated_at"));
                userDetails.setDireccionNombre(rs.getString("direccion_nombre"));
                userDetails.setNumeracion(rs.getString("numeracion"));
                userDetails.setRolNombre(rs.getString("rol_nombre"));

                userDetailsList.add(userDetails);
                System.out.println("StartupDAO: Added user: " + userDetails.getUserId());
            }

        } catch (SQLException e) {
            System.out.println("StartupDAO: SQLException: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        System.out.println("StartupDAO: Returning list with " + userDetailsList.size() + " users");
        return userDetailsList;
    }
}
