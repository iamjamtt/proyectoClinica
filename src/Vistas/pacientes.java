package Vistas;

import Modelo.pacienteDao;
import static Vistas.main.panelMain;
import static Vistas.moduloAsistente.btnPaciente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class pacientes extends javax.swing.JPanel {

    DefaultTableModel m = new DefaultTableModel();
    
    Modelo.pacienteDao padao = new pacienteDao();
    
    public static int tipo,idpa;
    
    public pacientes() {
        initComponents();
        placeholders();
        buscarPaciente();
    }
    
    void placeholders(){
        TextPrompt a1 = new TextPrompt("Buscar Nro de Historia / Dni", txtBuscarPaciente);
    }

    void buscarPaciente(){
        try {
            m = padao.consultarPacienteInnerJoin(txtBuscarPaciente.getText());
            tablaPaciente.setModel(m);
            
            TableColumn t = tablaPaciente.getColumn("DNI");
            t.setPreferredWidth(80);
            t.setMaxWidth(80);
            t.setMinWidth(80);
            
            TableColumn t2 = tablaPaciente.getColumn("ID");
            t2.setPreferredWidth(50);
            t2.setMaxWidth(50);
            t2.setMinWidth(50);
            
            tablaPaciente.setRowHeight(25);
        } catch (Exception e) {
            System.out.println("Error al mostrar paciente: " + e);
        }
    }
    
    void opcionesPaciente(int nro){
        int fila = tablaPaciente.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }else{
            idpa = Integer.parseInt(tablaPaciente.getValueAt(fila, 0).toString());
            
            if(nro == 1){
                pacienteM pa = new pacienteM();

                pa.setSize(new Dimension(900, 600));
                pa.setLocation(0,0);
                panelMain.removeAll();
                panelMain.add(pa,BorderLayout.CENTER);
                panelMain.revalidate();
                panelMain.repaint();
            }else if(nro == 2){
                int input = JOptionPane.showConfirmDialog(null, 
                "¿Esta seguro que desea eliminar?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);

                // 0=yes, 1=no
                if(input == 0){
                    int idp = padao.buscaridPersona(idpa);
                    
                    int r1 = padao.deletePersonaPaciente(idp);

                    if(r1>0){
                        JOptionPane.showMessageDialog(null, "Persona eliminada correctamente");
                    }    

                    //tabla paciente

                    int r2 = padao.deletePaciente(idpa);

                    if(r2>0){
                        JOptionPane.showMessageDialog(null, "Paciente eliminada correctamente");
                    }
                    
                    JOptionPane.showMessageDialog(null, "Paciente eliminado");
                    buscarPaciente();
                }else if(input == 1){
                    JOptionPane.showMessageDialog(null, "Accion cancelada");
                }
            }
        }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Opciones = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPaciente = new javax.swing.JTable();
        panelBuscar = new javax.swing.JPanel();
        txtBuscarPaciente = new javax.swing.JTextField();

        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jMenuItem1.setText("Actualizar Datos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Opciones.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jMenuItem2.setText("Eliminar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Opciones.add(jMenuItem2);

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));

        jPanel1.setBackground(new java.awt.Color(0, 23, 35));

        btnSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cerrar2.png"))); // NOI18N
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cerrar.png"))); // NOI18N
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel2.setBackground(new java.awt.Color(0, 23, 35));
        jLabel2.setFont(new java.awt.Font("SF UI Display", 1, 30)); // NOI18N
        jLabel2.setText("Paciente");

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
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tablaPaciente.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        tablaPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPaciente.setComponentPopupMenu(Opciones);
        tablaPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaPaciente.setGridColor(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(tablaPaciente);

        panelBuscar.setBackground(new java.awt.Color(178, 229, 255));
        panelBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelBuscar.setMinimumSize(new java.awt.Dimension(200, 28));
        panelBuscar.setPreferredSize(new java.awt.Dimension(200, 28));

        txtBuscarPaciente.setBackground(new java.awt.Color(178, 229, 255));
        txtBuscarPaciente.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        txtBuscarPaciente.setForeground(new java.awt.Color(0, 23, 35));
        txtBuscarPaciente.setBorder(null);
        txtBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtBuscarPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarPacienteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(txtBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        panelMain.removeAll();
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnNuevoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseEntered
        // TODO add your handling code here:
        btnNuevo.setBackground(new Color(229, 246, 255));
    }//GEN-LAST:event_btnNuevoMouseEntered

    private void btnNuevoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseExited
        // TODO add your handling code here:
        btnNuevo.setBackground(new Color(179, 229, 255));
    }//GEN-LAST:event_btnNuevoMouseExited

    private void btnNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseClicked
        // TODO add your handling code here:
        tipo = 1;
        
        pacienteM pa = new pacienteM();
        
        pa.setSize(new Dimension(900, 600));
        pa.setLocation(0,0);
        panelMain.removeAll();
        panelMain.add(pa,BorderLayout.CENTER);
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnNuevoMouseClicked

    private void txtBuscarPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyTyped
        // TODO add your handling code here:
        buscarPaciente();
    }//GEN-LAST:event_txtBuscarPacienteKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        tipo = 2;
        int opcion = 1;
        opcionesPaciente(opcion);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        int opcion = 2;
        opcionesPaciente(opcion);
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu Opciones;
    private javax.swing.JPanel btnNuevo;
    private javax.swing.JLabel btnSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JTable tablaPaciente;
    private javax.swing.JTextField txtBuscarPaciente;
    // End of variables declaration//GEN-END:variables
}
