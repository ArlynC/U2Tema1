package com.example.u2tema1;

public class Cliente {
    private String codigo;
    private String nombre;
    private String Apellido;
    private String Sexo;
    private String celular;
    private String Domicilio;

    public Cliente(String codigo, String nombre, String Apellido, String Sexo,String celular,String Domicilio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.Apellido= Apellido;
        this.Sexo=Sexo;
        this.celular=celular;
        this.Domicilio=Domicilio;
    }
    public String getcodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public String getSexo() {return Sexo;}
    public String getCelular() {return celular;}
    public String getDomicilio() {return Domicilio;}


}
