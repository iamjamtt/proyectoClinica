package Vistas;

import Modelo.especialidadDao;
import Modelo.medicoDao;
import static Vistas.main.panelMain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class medicos extends javax.swing.JPanel {

    DefaultTableModel m = new DefaultTableModel();
    
    Modelo.medicoDao mdao = new medicoDao();
    Modelo.especialidadDao edao = new especialidadDao();
    
    String especialidad = "";
    
    public static String fechac,dniper;
    public static int ide, idm;
    
    public medicos() {
        initComponents();
        cargarComboEspecialidad();
        buscarPaciente("");
        dniper = citas.dnip;
        
    }
    
    void cargarComboEspecialidad(){
        mdao.cargarComboEspecialidad(cboEspecialidad);
    }
    
    void buscarPaciente(String nombree){
        try {
            m = mdao.consultarMedicoInnerJoin(nombree);
            tablamedico.setModel(m);
            
            TableColumn t = tablamedico.getColumn("CODIGO");
            t.setPreferredWidth(80);
            t.setMaxWidth(80);
            t.setMinWidth(80);
            
            TableColumn t2 = tablamedico.getColumn("ID");
            t2.setPreferredWidth(50);
            t2.setMaxWidth(50);
            t2.setMinWidth(50);
            
            tablamedico.setRowHeight(25);
                    
        } catch (Exception e) {
            System.out.println("Error al mostrar medico: " + e);
        }
    }

    void buscarCombo(){
        especialidad = (String) cboEspecialidad.getSelectedItem();
        if(especialidad.equals("Seleccione")){
            especialidad = "";
        }
        System.out.println(especialidad);
        
        buscarPaciente(especialidad);
    }
    
    void seleccionarMedico(){
        
        int fila = tablamedico.getSelectedRow();
        
        if(fechaCita.getDate() == null || cboEspecialidad.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Campo de texto vacios");
        }else if(fila == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }else{
            Date f = fechaCita.getDate();
            DateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
            fechac =  f2.format(f);
            
            String nombree = (String) cboEspecialidad.getSelectedItem();
            ide = edao.idEspecialidad(nombree);
            
            idm = Integer.parseInt(tablamedico.getValueAt(fila, 0).toString());
            
            citas citas = new citas();
        
            citas.setSize(new Dimension(900, 600));
            citas.setLocation(0,0);
            panelMain.removeAll();
            panelMain.add(citas,BorderLayout.CENTER);
            panelMain.revalidate();
            panelMain.repaint();
        }
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnSalir1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        fechaCita = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        cboEspecialidad = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablamedico = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnBuscarMedico = new javax.swing.JButton();

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
        jLabel2.setText("Buscar Medicos");

        jLabel1.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel1.setText("Fecha Cita:");

        fechaCita.setDateFormatString("dd-MM-yyyy");
        fechaCita.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel3.setText("Consultorio:");

        cboEspecialidad.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        cboEspecialidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboEspecialidadMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboEspecialidadMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboEspecialidadMouseReleased(evt);
            }
        });
        cboEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEspecialidadActionPerformed(evt);
            }
        });

        tablamedico.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        tablamedico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablamedico.setGridColor(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(tablamedico);

        btnSeleccionar.setBackground(new java.awt.Color(178, 229, 255));
        btnSeleccionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSeleccionarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSeleccionarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSeleccionarMouseExited(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(0, 23, 35));
        jLabel11.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Seleccionar");

        javax.swing.GroupLayout btnSeleccionarLayout = new javax.swing.GroupLayout(btnSeleccionar);
        btnSeleccionar.setLayout(btnSeleccionarLayout);
        btnSeleccionarLayout.setHorizontalGroup(
            btnSeleccionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSeleccionarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSeleccionarLayout.setVerticalGroup(
            btnSeleccionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSeleccionarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnBuscarMedico.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        btnBuscarMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buscar (1).png"))); // NOI18N
        btnBuscarMedico.setBorderPainted(false);
        btnBuscarMedico.setMaximumSize(new java.awt.Dimension(40, 40));
        btnBuscarMedico.setMinimumSize(new java.awt.Dimension(40, 40));
        btnBuscarMedico.setPreferredSize(new java.awt.Dimension(40, 40));
        btnBuscarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMedicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(fechaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(cboEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(42, 42, 42)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(fechaCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btnBuscarMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalir1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir1MouseClicked
        // TODO add your handling code here:
        panelMain.removeAll();
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnSalir1MouseClicked

    private void btnSeleccionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSeleccionarMouseClicked
        // TODO add your handling code here:
        
        seleccionarMedico();
    }//GEN-LAST:event_btnSeleccionarMouseClicked

    private void btnSeleccionarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSeleccionarMouseEntered
        // TODO add your handling code here:
        btnSeleccionar.setBackground(new Color(229, 246, 255));
    }//GEN-LAST:event_btnSeleccionarMouseEntered

    private void btnSeleccionarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSeleccionarMouseExited
        // TODO add your handling code here:
        btnSeleccionar.setBackground(new Color(179, 229, 255));
    }//GEN-LAST:event_btnSeleccionarMouseExited

    private void cboEspecialidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboEspecialidadMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboEspecialidadMouseClicked

    private void cboEspecialidadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboEspecialidadMousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboEspecialidadMousePressed

    private void cboEspecialidadMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboEspecialidadMouseReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboEspecialidadMouseReleased

    private void cboEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEspecialidadActionPerformed
        // TODO add your handling code here:
        //buscarPaciente((String) cboEspecialidad.getSelectedItem());
    }//GEN-LAST:event_cboEspecialidadActionPerformed

    private void btnBuscarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicoActionPerformed
        // TODO add your handling code here:
        
        buscarCombo();
    }//GEN-LAST:event_btnBuscarMedicoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarMedico;
    private javax.swing.JLabel btnSalir1;
    private javax.swing.JPanel btnSeleccionar;
    private javax.swing.JComboBox<String> cboEspecialidad;
    private com.toedter.calendar.JDateChooser fechaCita;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablamedico;
    // End of variables declaration//GEN-END:variables
}
