/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cac.tpfinal_cac;

import java.util.ArrayList;

/**
 *
 * @author fagal
 */
public interface Operaciones {
       public boolean agregar(Object obj);
       public boolean modificar(Object obj);
       public boolean eliminar(Object obj);
       public  ArrayList<Object[]> consultar();
       
}
