package com.example.myapp1;

import java.io.Serializable;

public class Usuario implements Serializable{
    private String nombre, apellido;

    public Usuario(String nombre, String apellido){
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public String getNombre(){ return nombre;}
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido(){ return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
}
