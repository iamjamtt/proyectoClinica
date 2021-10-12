package Vistas;

import Modelo.consultaDao;
import static Vistas.main.panelMain;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class consultas extends javax.swing.JPanel {

    DefaultTableModel m = new DefaultTableModel();
    
    Modelo.consultaDao codao = new consultaDao();
    
    public static String nroHistoria;
    public static int idc;
    
    public consultas() {
        initComponents();
        mostrarCita(fechaActual());
    }

    String fechaActual(){
        String fechaactual = "";
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fechaactual = dtf.format(LocalDateTime.now());
        
        return fechaactual;
    }
    
    void mostrarCita(String fechac){
        try {
            int idm = login.idm;
            m = codao.consultaMostrarCitas(idm, fechac);
            tablacitas.setModel(m);
            
            TableColumn t = tablacitas.getColumn("PACIENTE");
            t.setPreferredWidth(250);
            t.setMaxWidth(250);
            t.setMinWidth(250);
            
            TableColumn t2 = tablacitas.getColumn("ID");
            t2.setPreferredWidth(50);
            t2.setMaxWidth(50);
            t2.setMinWidth(50);
            
            tablacitas.setRowHeight(25);
        } catch (Exception e) {
            System.out.println("Error al mostrar citas: " + e);
        }
    }
    
    void opcionesCita(){
        int fila = tablacitas.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }else{
            idc = Integer.parseInt(tablacitas.getValueAt(fila, 0).toString());
            
            nroHistoria = (String) tablacitas.getValueAt(fila, 3);
            
            consultaM consu = new consultaM();

            consu.setSize(new Dimension(900, 600));
            consu.setLocation(0,0);
            panelMain.removeAll();
            panelMain.add(consu,BorderLayout.CENTER);
            panelMain.revalidate();
            panelMain.repaint();
        }
    
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        medu = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel4 = new javax.swing.JPanel();
        btnSalir2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablacitas = new javax.swing.JTable();
        txtFecha = new com.toedter.calendar.JDateChooser();
        btnBuscarMedico = new javax.swing.JButton();

        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        jMenuItem1.setText("Atender Cita");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        medu.add(jMenuItem1);

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));

        jPanel4.setBackground(new java.awt.Color(0, 23, 35));

        btnSalir2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSalir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cerrar2.png"))); // NOI18N
        btnSalir2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cerrar.png"))); // NOI18N
        btnSalir2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalir2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 875, Short.MAX_VALUE)
                .addComponent(btnSalir2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalir2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel3.setBackground(new java.awt.Color(0, 23, 35));
        jLabel3.setFont(new java.awt.Font("SF UI Display", 1, 30)); // NOI18N
        jLabel3.setText("Consultas");

        jLabel1.setFont(new java.awt.Font("SF UI Display", 1, 18)); // NOI18N
        jLabel1.setText("Citas del dia");

        tablacitas.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        tablacitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablacitas.setComponentPopupMenu(medu);
        jScrollPane1.setViewportView(tablacitas);

        txtFecha.setDateFormatString("yyyy-MM-dd");
        txtFecha.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        btnBuscarMedico.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        btnBuscarMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buscar (1).png"))); // NOI18N
        btnBuscarMedico.setBorderPainted(false);
        btnBuscarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMedicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnBuscarMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 151, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalir2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir2MouseClicked
        // TODO add your handling code here:
        panelMain.removeAll();
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnSalir2MouseClicked

    private void btnBuscarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicoActionPerformed
        // TODO add your handling code here:
        if(txtFecha.getDate()==null){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha");
        }else{
            Date f = txtFecha.getDate();
            DateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
            String fechac = f2.format(f);
            mostrarCita(fechac);
        }
    }//GEN-LAST:event_btnBuscarMedicoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
        opcionesCita();
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarMedico;
    private javax.swing.JLabel btnSalir2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu medu;
    private javax.swing.JTable tablacitas;
    private com.toedter.calendar.JDateChooser txtFecha;
    // End of variables declaration//GEN-END:variables
}
