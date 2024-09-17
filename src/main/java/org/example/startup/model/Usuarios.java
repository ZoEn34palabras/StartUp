package org.example.startup.model;

import java.util.Date;

    public class Usuarios {

        private int id;
        private String correo;
        private String nick;
        private String nombre;
        private String password;
        private int peso;
        private java.util.Date createdAt;
        private java.util.Date updatedAt;
        private boolean isAdmin;

        public Usuarios() {}

        public Usuarios(int id, String correo, String nick, String nombre, String password, int peso, boolean isAdmin) {
            this.id = id;
            this.correo = correo;
            this.nick = nick;
            this.nombre = nombre;
            this.password = password;
            this.peso = peso;
            this.isAdmin = isAdmin;

            this.createdAt = new Date();
            this.updatedAt = new Date();
        }

        public Usuarios(int id, String correo, String nick, String nombre, String password, int peso, Date createdAt, Date updatedAt, boolean isAdmin) {
            this.id = id;
            this.correo = correo;
            this.nick = nick;
            this.nombre = nombre;
            this.password = password;
            this.peso = peso;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.isAdmin = isAdmin;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public boolean isAdmin() {
            return isAdmin;
        }

        public void setAdmin(boolean admin) {
            isAdmin = admin;
        }



        @Override
        public String toString() {
            return "Usuarios{" +
                    "id=" + id +
                    ", correo='" + correo + '\'' +
                    ", nick='" + nick + '\'' +
                    ", nombre='" + nombre + '\'' +
                    ", password='" + password + '\'' +
                    ", peso=" + peso +
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    ", isAdmin=" + isAdmin +
                    '}';
        }
    }


