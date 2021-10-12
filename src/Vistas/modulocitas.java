package Vistas;

import Modelo.citaDao;
import static Vistas.main.panelMain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class modulocitas extends javax.swing.JPanel {

    DefaultTableModel m = new DefaultTableModel();
    
    Modelo.citaDao cdao = new citaDao();
    
    public modulocitas() {
        initComponents();
        placeholders();
        mostrarCitas("", fechaActual());
    }

    void placeholders(){
        TextPrompt a1 = new TextPrompt("Buscar Dni", txtBuscaCitaDni);
    }
    
    String fechaActual(){
        String fechaactual = "";
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fechaactual = dtf.format(LocalDateTime.now());
        
        return fechaactual;
    }
    
    String fecha(){
        Date f = txtfecha.getDate();
        DateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
        String fechac = f2.format(f);
        
        return fechac;
    }
    
    void mostrarCitas(String dnip, String fechac){
        try {
            int idm = login.idm;
            m = cdao.consultaMostrarCitas(dnip, fechac);
            tablaCitas.setModel(m);
            
            TableColumn t = tablaCitas.getColumn("PACIENTE");
            t.setPreferredWidth(150);
            t.setMaxWidth(150);
            t.setMinWidth(150);
            
            TableColumn t4 = tablaCitas.getColumn("MEDICO");
            t4.setPreferredWidth(150);
            t4.setMaxWidth(150);
            t4.setMinWidth(150);
            
            TableColumn t2 = tablaCitas.getColumn("ID");
            t2.setPreferredWidth(50);
            t2.setMaxWidth(50);
            t2.setMinWidth(50);
            
            TableColumn t3 = tablaCitas.getColumn("CODIGO");
            t3.setPreferredWidth(70);
            t3.setMaxWidth(70);
            t3.setMinWidth(70);
            
            tablaCitas.setRowHeight(25);
        } catch (Exception e) {
            System.out.println("Error al mostrar citas: " + e);
        }
    }
    
    void opcionesPaciente(){
        int fila = tablaCitas.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }else{
            int idc = Integer.parseInt(tablaCitas.getValueAt(fila, 0).toString());
            
            int input = JOptionPane.showConfirmDialog(null, 
            "¿Esta seguro que desea eliminar?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);

            // 0=yes, 1=no
            if(input == 0){
                int r1 = cdao.deleteCita(idc);

                if(r1>0){
                    JOptionPane.showMessageDialog(null, "Cita eliminada correctamente");
                }
                
                mostrarCitas(txtBuscaCitaDni.getText(), fecha());
                
            }else if(input == 1){
                JOptionPane.showMessageDialog(null, "Accion cancelada");
            }
            
        }
    
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuOpciones = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        btnSalir1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscarCitaFecha = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCitas = new javax.swing.JTable();
        btnNuevo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtfecha = new com.toedter.calendar.JDateChooser();
        panelBuscar4 = new javax.swing.JPanel();
        txtBuscaCitaDni = new javax.swing.JTextField();

        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jMenuItem1.setForeground(new java.awt.Color(0, 23, 35));
        jMenuItem1.setText("Eliminar Cita");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuOpciones.add(jMenuItem1);

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));

        jPanel3.setBackground(new java.awt.Color(0, 23, 35));

        btnSalir1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cerrar2.png"))); // NOI18N
        btnSalir1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cerrar.png"))); // NOI18N
        btnSalir1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalir1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 875, Short.MAX_VALUE)
                .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel2.setBackground(new java.awt.Color(0, 23, 35));
        jLabel2.setFont(new java.awt.Font("SF UI Display", 1, 30)); // NOI18N
        jLabel2.setText("Citas del día");

        btnBuscarCitaFecha.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        btnBuscarCitaFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buscar (1).png"))); // NOI18N
        btnBuscarCitaFecha.setBorderPainted(false);
        btnBuscarCitaFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCitaFechaActionPerformed(evt);
            }
        });

        tablaCitas.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        tablaCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaCitas.setComponentPopupMenu(menuOpciones);
        jScrollPane1.setViewportView(tablaCitas);

        btnNuevo.setBackground(new java.awt.Color(178, 229, 255));
        btnNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNuevoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevoMouseExited(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 23, 35));
        jLabel3.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nuevo");

        javax.swing.GroupLayout btnNuevoLayout = new javax.swing.GroupLayout(btnNuevo);
        btnNuevo.setLayout(btnNuevoLayout);
        btnNuevoLayout.setHorizontalGroup(
            btnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnNuevoLayout.setVerticalGroup(
            btnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtfecha.setDateFormatString("yyyy-MM-dd");
        txtfecha.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        panelBuscar4.setBackground(new java.awt.Color(178, 229, 255));
        panelBuscar4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelBuscar4.setMinimumSize(new java.awt.Dimension(200, 28));

        txtBuscaCitaDni.setBackground(new java.awt.Color(178, 229, 255));
        txtBuscaCitaDni.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        txtBuscaCitaDni.setForeground(new java.awt.Color(0, 23, 35));
        txtBuscaCitaDni.setBorder(null);
        txtBuscaCitaDni.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtBuscaCitaDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscaCitaDniKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelBuscar4Layout = new javax.swing.GroupLayout(panelBuscar4);
        panelBuscar4.setLayout(panelBuscar4Layout);
        panelBuscar4Layout.setHorizontalGroup(
            panelBuscar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscar4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(txtBuscaCitaDni, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        panelBuscar4Layout.setVerticalGroup(
            panelBuscar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscar4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscaCitaDni, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(panelBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarCitaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelBuscar4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtfecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscarCitaFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalir1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir1MouseClicked
        // TODO add your handling code here:
        panelMain.removeAll();
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnSalir1MouseClicked

    private void btnNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseClicked
        // TODO add your handling code here:
        citas citas = new citas();
        
        citas.setSize(new Dimension(900, 600));
        citas.setLocation(0,0);
        panelMain.removeAll();
        panelMain.add(citas,BorderLayout.CENTER);
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnNuevoMouseClicked

    private void btnNuevoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseEntered
        // TODO add your handling code here:
        btnNuevo.setBackground(new Color(229, 246, 255));
    }//GEN-LAST:event_btnNuevoMouseEntered

    private void btnNuevoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseExited
        // TODO add your handling code here:
        btnNuevo.setBackground(new Color(179, 229, 255));
    }//GEN-LAST:event_btnNuevoMouseExited

    private void btnBuscarCitaFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCitaFechaActionPerformed
        // TODO add your handling code here:
        mostrarCitas("",fecha());
    }//GEN-LAST:event_btnBuscarCitaFechaActionPerformed

    private void txtBuscaCitaDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaCitaDniKeyTyped
        // TODO add your handling code here:
        mostrarCitas(txtBuscaCitaDni.getText(), fecha());
    }//GEN-LAST:event_txtBuscaCitaDniKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        opcionesPaciente();
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCitaFecha;
    private javax.swing.JPanel btnNuevo;
    private javax.swing.JLabel btnSalir1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu menuOpciones;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelBuscar1;
    private javax.swing.JPanel panelBuscar2;
    private javax.swing.JPanel panelBuscar3;
    private javax.swing.JPanel panelBuscar4;
    private javax.swing.JTable tablaCitas;
    private javax.swing.JTextField txtBuscaCitaDni;
    private javax.swing.JTextField txtBuscarPaciente;
    private javax.swing.JTextField txtBuscarPaciente1;
    private javax.swing.JTextField txtBuscarPaciente2;
    private javax.swing.JTextField txtBuscarPaciente3;
    private com.toedter.calendar.JDateChooser txtfecha;
    // End of variables declaration//GEN-END:variables
}
