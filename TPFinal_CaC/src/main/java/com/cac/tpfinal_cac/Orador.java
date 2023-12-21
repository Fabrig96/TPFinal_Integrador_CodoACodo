
package com.cac.tpfinal_cac;

import java.util.Date;
import java.util.Objects;

public class Orador {
    private int idOrador;
    private String nombre;
    private String apellido;
    private String mail;
    private String tema;
    private Date fechaAlta;

    // Constructores, getters y setters

    public Orador(int idOrador, String nombre, String apellido, String mail, String tema, Date fechaAlta) {
        this.idOrador = idOrador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.tema = tema;
        this.fechaAlta = fechaAlta;
    }
    
    public Orador(){
    }

    public int getIdOrador() {
        return idOrador;
    }

    public void setIdOrador(int idOrador) {
        this.idOrador = idOrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idOrador;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.apellido);
        hash = 79 * hash + Objects.hashCode(this.mail);
        hash = 79 * hash + Objects.hashCode(this.tema);
        hash = 79 * hash + Objects.hashCode(this.fechaAlta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Orador other = (Orador) obj;
        if (this.idOrador != other.idOrador) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.tema, other.tema)) {
            return false;
        }
        return Objects.equals(this.fechaAlta, other.fechaAlta);
    }

    @Override
    public String toString() {
        return "Orador{" + "idOrador=" + idOrador + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", tema=" + tema + ", fechaAlta=" + fechaAlta + '}';
    }
    
    
}
