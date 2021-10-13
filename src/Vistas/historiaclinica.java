package Vistas;

import Modelo.consultaDao;
import Modelo.pacienteDao;
import static Vistas.main.panelMain;
import java.awt.Desktop;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class historiaclinica extends javax.swing.JPanel {

    DefaultTableModel m = new DefaultTableModel();
    
    Modelo.consultaDao codao = new consultaDao();
    
    public historiaclinica() {
        initComponents();
        placeholders();
    }

    //metodo que me muestra un placeholcer en el campo de texto
    void placeholders(){
        TextPrompt a1 = new TextPrompt("Historia del Paciente", txtHC);
    }
    
    //aqui me muestra los datos de las consultas realiadas al paciente y se puede filtrar mediante el numero de historia del paciente
    void mostrarCitas(String nrohistoriapa){
        try {
            m = codao.consultaMostrarConsultas(nrohistoriapa);
            tablaclinica.setModel(m);
            
            TableColumn t = tablaclinica.getColumn("PACIENTE");
            t.setPreferredWidth(200);
            t.setMaxWidth(200);
            t.setMinWidth(200);
            
            TableColumn t4 = tablaclinica.getColumn("SINTOMA");
            t4.setPreferredWidth(200);
            t4.setMaxWidth(200);
            t4.setMinWidth(200);
            
            TableColumn t2 = tablaclinica.getColumn("ID");
            t2.setPreferredWidth(50);
            t2.setMaxWidth(50);
            t2.setMinWidth(50);
            
            TableColumn t3 = tablaclinica.getColumn("CODIGO");
            t3.setPreferredWidth(70);
            t3.setMaxWidth(70);
            t3.setMinWidth(70);
            
            tablaclinica.setRowHeight(25);
        } catch (Exception e) {
            System.out.println("Error al mostrar citas: " + e);
        }
    }
    
    //aqui me abre el pdf generado de la consulta de cada paciente
    public void abrirPDF(String codigo){
        try {
            File path = new File(codigo + ".pdf");
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            System.out.println("Error al abrir el pdf " + e);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel5 = new javax.swing.JPanel();
        btnSalir3 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtHC = new javax.swing.JTextField();
        btnBuscarMedico = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaclinica = new javax.swing.JTable();

        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jMenuItem1.setText("Abrir Ficha");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem1);

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));

        jPanel5.setBackground(new java.awt.Color(0, 23, 35));

        btnSalir3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSalir3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cerrar2.png"))); // NOI18N
        btnSalir3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir3.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cerrar.png"))); // NOI18N
        btnSalir3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalir3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 875, Short.MAX_VALUE)
                .addComponent(btnSalir3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalir3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel3.setBackground(new java.awt.Color(0, 23, 35));
        jLabel3.setFont(new java.awt.Font("SF UI Display", 1, 30)); // NOI18N
        jLabel3.setText("Historia Clinica");

        txtHC.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N

        btnBuscarMedico.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        btnBuscarMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buscar (1).png"))); // NOI18N
        btnBuscarMedico.setBorderPainted(false);
        btnBuscarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMedicoActionPerformed(evt);
            }
        });

        tablaclinica.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        tablaclinica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaclinica.setComponentPopupMenu(menu);
        tablaclinica.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tablaclinica);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtHC, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarMedico, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtHC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalir3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir3MouseClicked
        // TODO add your handling code here:
        panelMain.removeAll();
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnSalir3MouseClicked

    private void btnBuscarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicoActionPerformed
        // TODO add your handling code here:
        if(txtHC.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campo de texto vacio");
        }else{
            mostrarCitas(txtHC.getText());
        }
        
    }//GEN-LAST:event_btnBuscarMedicoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int fila = tablaclinica.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }else{
            String codco = tablaclinica.getValueAt(fila, 1).toString();
        
            abrirPDF(codco);
            
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarMedico;
    private javax.swing.JLabel btnSalir3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu menu;
    private javax.swing.JTable tablaclinica;
    private javax.swing.JTextField txtHC;
    // End of variables declaration//GEN-END:variables
}
