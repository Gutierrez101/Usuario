/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espew.datos_online;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Cliente {

    //constructor with date
    public Cliente(int Id, String Nombre, String Apellido, String Ciudad, int Edad) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Ciudad = Ciudad;
        this.Edad = Edad;
    }
    

    //cosntructor void
    public Cliente(){
        
    }
    
    int Id;
    String Nombre;
    String Apellido;
    String Ciudad;
    int Edad;
    
    //getter and setter

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }
    
    
    //Methods for aplicate
    public void GuardarInformacion(){
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "INSERT INTO cliente (Id, nombre, apellido ,ciudad ,edad) VALUES (null,?,?,?,?)";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getNombre());
            parametro.setString(2, this.getApellido());
            parametro.setString(3, this.getCiudad());
            parametro.setInt(4, this.getEdad());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void CambiarInformacion(){
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "UPDATE cliente SET nombre=?, apellido=?,ciudad=?,edad=? WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getNombre());
            parametro.setString(2, this.getApellido());
            parametro.setString(3, this.getCiudad());
            parametro.setFloat(4, this.getEdad());
            parametro.setInt(5, this.getId());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Cliente> ObtenerInformacion(){
        Connection conexionDb = ConexionDb.getConnection();
        ResultSet rsClientes;
        
        var Clientes = new ArrayList<Cliente>();
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "SELECT * FROM cliente";
        try {
            //Configurar los parametros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            
            //Ejecutar la sentecia SQL
             rsClientes=parametro.executeQuery();           
            
            while(rsClientes.next()){              
                    Clientes.add(new Cliente(rsClientes.getInt("Id"),rsClientes.getString("nombre"),rsClientes.getString("apellido"),rsClientes.getString("ciudad"),rsClientes.getInt("edad")));

            }
            
            conexionDb.close();
            return Clientes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        
    }
    
    void EliminarInformacion(){
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "DELETE FROM cliente WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setInt(1, this.getId());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}

