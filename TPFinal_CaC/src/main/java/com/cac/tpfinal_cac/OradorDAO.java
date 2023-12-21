/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cac.tpfinal_cac;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class OradorDAO implements Operaciones {

    DatabaseConnector db = new DatabaseConnector();
    Orador orador = new Orador();
    Connection con = null;

    public Connection conexion() {

        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error: No se pudo conectar a la base de datos");
        }
        return con;
    }

    @Override
    public boolean agregar(Object obj) {
        orador = (Orador) obj;
        String sql = "INSERT INTO oradores(nombre,apellido,mail,tema,fecha_alta) VALUES(?,?,?,?,?)";
        //Connection con;
        PreparedStatement pst;
        ResultSet generatedKeys = null;

        try {

            con = conexion();
            pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, orador.getNombre());
            pst.setString(2, orador.getApellido());
            pst.setString(3, orador.getMail());
            pst.setString(4, orador.getTema());

            Date fechaAlta = orador.getFechaAlta();
            if (fechaAlta != null) {
                java.sql.Date sqlFechaAlta = new java.sql.Date(fechaAlta.getTime());
                pst.setDate(5, sqlFechaAlta);
            }

            int filas = pst.executeUpdate();

            if (filas > 0) {
                generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    orador.setIdOrador(idGenerado);
                }

                con.close();

                try {
                    XmlManager.insertData(orador.getIdOrador(), orador.getNombre(), orador.getApellido(), orador.getMail(), orador.getFechaAlta(), orador.getTema());
                    System.out.println("Registro agregado con éxito");
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar el archivo XML: " + e.getMessage());

                }

                return true;
            } else {
                con.close();
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {

            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean modificar(Object obj) {
        orador = (Orador) obj;
        String sql = "UPDATE oradores SET nombre = ?, apellido = ?, mail = ?, tema = ?, fecha_alta = ? WHERE id_orador = ?";

        PreparedStatement pst;

        try {

            con = conexion();
            pst = con.prepareStatement(sql);
            pst.setString(1, orador.getNombre());
            pst.setString(2, orador.getApellido());
            pst.setString(3, orador.getMail());
            pst.setString(4, orador.getTema());

            Date fechaAlta = orador.getFechaAlta();
            if (fechaAlta != null) {
                java.sql.Date sqlFechaAlta = new java.sql.Date(fechaAlta.getTime());
                pst.setDate(5, sqlFechaAlta);
            }

            pst.setInt(6, orador.getIdOrador());

            int filas = pst.executeUpdate();

            if (filas > 0) {
                con.close();

                // Continúa con la actualización en el archivo XML utilizando el ID existente
                try {
                    XmlManager.updateData(orador.getIdOrador(), orador.getNombre(), orador.getApellido(), orador.getMail(), orador.getFechaAlta(), orador.getTema());
                    System.out.println("Registro modificado con éxito");
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar el archivo XML: " + e.getMessage());

                }

                return true;
            } else {
                con.close();
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(Object obj) {
        orador = (Orador) obj;
        String sql = "DELETE FROM oradores WHERE id_orador = ?";

        PreparedStatement pst;

        try {

            con = conexion();
            pst = con.prepareStatement(sql);

            pst.setInt(1, orador.getIdOrador());
            int filas = pst.executeUpdate();

            if (filas > 0) {
                con.close();

                // Continúa con la eliminación en el archivo XML utilizando el ID existente
                try {
                    XmlManager.deleteData(orador.getIdOrador());
                    System.out.println("Registro eliminado con éxito");
                } catch (IOException | ParserConfigurationException | TransformerException | SAXException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar el archivo XML: " + e.getMessage());

                }

                return true;
            } else {
                con.close();
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Object[]> consultar() {
        String sql = "SELECT * FROM oradores";

        PreparedStatement pst;
        ResultSet rs;
        ResultSetMetaData meta;
        ArrayList<Object[]> datos = new ArrayList<>();
        try {

            con = conexion();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            meta = rs.getMetaData();
            while (rs.next()) {
                Object[] fila = new Object[meta.getColumnCount()];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                datos.add(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            System.err.println("Error al consultar en la base de datos");
        }
        return datos;

    }

    //Buscar por ID
    public Orador buscarPorId(int id) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        Orador oradorEncontrado = null;

        try {
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPass());
            pst = con.prepareStatement("SELECT * FROM oradores WHERE id_orador = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                oradorEncontrado = new Orador();
                oradorEncontrado.setIdOrador(rs.getInt("id_orador"));
                oradorEncontrado.setNombre(rs.getString("nombre"));
                oradorEncontrado.setApellido(rs.getString("apellido"));
                oradorEncontrado.setMail(rs.getString("mail"));
                oradorEncontrado.setTema(rs.getString("tema"));
                oradorEncontrado.setFechaAlta(rs.getDate("fecha_alta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

        return oradorEncontrado;
    }

}
