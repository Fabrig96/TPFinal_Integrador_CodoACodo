/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.cac.tpfinal_cac;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fagal
 */
public class VentanaBusqueda extends javax.swing.JDialog {

    /**
     * Creates new form VentanaBusqueda
     */
    private VistaOrador vistaOrador;

    public VentanaBusqueda(JFrame parent, boolean modal, VistaOrador vistaOrador) {
        super(parent, modal);
        this.vistaOrador = vistaOrador;
        this.setTitle("Búsqueda");
        centrarEnPantalla(parent);
        initComponents();
    }

    private void centrarEnPantalla(JFrame parent) {
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = getWidth();
        int height = getHeight();

        int x = parent.getX() + (parent.getWidth() - width) / 2;
        int y = parent.getY() + (parent.getHeight() - height) / 2;

        setLocation(x, y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtIDBusqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 255));

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 204, 0));
        jButton2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("ID Orador:");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setText("Búsqueda de Orador ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIDBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(txtIDBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int idABuscar = Integer.parseInt(this.txtIDBusqueda.getText());

        // Realizar la búsqueda por ID y cargar los datos si se encuentra
        Orador oradorEncontrado = vistaOrador.getOradorDao().buscarPorId(idABuscar);

        if (oradorEncontrado != null) {
            // Mostrar los datos encontrados en los campos correspondientes
            vistaOrador.getTxtID().setText(String.valueOf(oradorEncontrado.getIdOrador()));
            vistaOrador.getTxtNombre().setText(oradorEncontrado.getNombre());
            vistaOrador.getTxtApellido().setText(oradorEncontrado.getApellido());
            vistaOrador.getTxtEmail().setText(oradorEncontrado.getMail());
            vistaOrador.getTxtTema().setText(oradorEncontrado.getTema());
            vistaOrador.getDateAlta().setDate(oradorEncontrado.getFechaAlta());

            vistaOrador.getTDBDatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            //  int rowIndexToSelect = 0;
            //vistaOrador.getTDBDatos().setRowSelectionInterval(rowIndexToSelect, rowIndexToSelect);
            selectRowById(idABuscar);
            JOptionPane.showMessageDialog(this, "Se encontró un Orador de la ID ingresada con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);

        } else {
            // Mostrar un mensaje si el ID no se encuentra
            JOptionPane.showMessageDialog(this, "No se encontró ningún registro con el ID proporcionado.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void selectRowById(int idToSelect) {
        // Obtener el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) vistaOrador.getTDBDatos().getModel();

        // Buscar el índice de la fila con el ID deseado
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            int idInTable = Integer.parseInt(model.getValueAt(i, 0).toString());
            if (idToSelect == idInTable) {
                // Seleccionar la fila
                vistaOrador.getTDBDatos().setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        try {
            // Configuración del aspecto y la sensación (look and feel) de Nimbus
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Crear e inicializar la ventana de búsqueda
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Aquí necesitas tener una instancia válida de VistaOrador
                VistaOrador vistaOrador = new VistaOrador(/* Parámetros de construcción */);

                // Crear una instancia de VentanaBusqueda y pasar la instancia de VistaOrador
                VentanaBusqueda dialog = new VentanaBusqueda(new javax.swing.JFrame(), true, vistaOrador);

                // Agregar un WindowListener para cerrar la aplicación al cerrar la ventana de búsqueda
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });

                // Hacer visible la ventana de búsqueda
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtIDBusqueda;
    // End of variables declaration//GEN-END:variables
}