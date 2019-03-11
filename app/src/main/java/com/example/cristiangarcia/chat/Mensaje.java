package com.example.cristiangarcia.chat;

public class Mensaje {
    Long Codigo;
    String Mensaje;
    String FechaHora;
    String FKCodiUsuario;
    String Pendiente;
    String nombre;

    public Mensaje(Long codigo, String mensaje, String fechaHora, String FKCodiUsuario, String nombre) {
        this.Codigo = codigo;
        this.Mensaje = mensaje;
        this.FechaHora = fechaHora;
        this.FKCodiUsuario = FKCodiUsuario;
        this.nombre = nombre;
    }

    public Mensaje() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCodigo() {
        return Codigo;
    }

    public void setCodigo(Long codigo) {
        Codigo = codigo;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public String getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(String fechaHora) {
        FechaHora = fechaHora;
    }

    public String getFKCodiUsuario() {
        return FKCodiUsuario;
    }

    public void setFKCodiUsuario(String FKCodiUsuario) {
        this.FKCodiUsuario = FKCodiUsuario;
    }

    public String getPendiente() {
        return Pendiente;
    }

    public void setPendiente(String pendiente) {
        Pendiente = pendiente;
    }
}
