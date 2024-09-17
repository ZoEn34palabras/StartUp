package org.example.startup.model;

import java.util.Date;

public class UserDetails {
    private int userId;
    private String correo;
    private String nick;
    private String nombre;
    private String password;
    private int peso;
    private Date createdAt;
    private Date updatedAt;
    private String direccionNombre;
    private String numeracion;
    private String rolNombre;

    public UserDetails(){}

    public UserDetails(int userId, String correo, String nick, String nombre, String password, int peso, Date createdAt, Date updatedAt, String direccionNombre, String numeracion, String rolNombre) {
        this.userId = userId;
        this.correo = correo;
        this.nick = nick;
        this.nombre = nombre;
        this.password = password;
        this.peso = peso;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.direccionNombre = direccionNombre;
        this.numeracion = numeracion;
        this.rolNombre = rolNombre;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDireccionNombre() {
        return direccionNombre;
    }

    public void setDireccionNombre(String direccionNombre) {
        this.direccionNombre = direccionNombre;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId=" + userId +
                ", correo='" + correo + '\'' +
                ", nick='" + nick + '\'' +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", peso=" + peso +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", direccionNombre='" + direccionNombre + '\'' +
                ", numeracion='" + numeracion + '\'' +
                ", rolNombre='" + rolNombre + '\'' +
                '}';
    }
}