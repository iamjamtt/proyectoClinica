package Vistas;

import static Vistas.main.panelMain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class moduloAsistente extends javax.swing.JPanel {

    public moduloAsistente() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPaciente = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnCitas = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(178, 229, 255));
        setMaximumSize(new java.awt.Dimension(300, 400));
        setMinimumSize(new java.awt.Dimension(300, 400));
        setPreferredSize(new java.awt.Dimension(300, 400));
        setRequestFocusEnabled(false);

        btnPaciente.setBackground(new java.awt.Color(0, 23, 35));
        btnPaciente.setForeground(new java.awt.Color(255, 255, 255));
        btnPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPacienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPacienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPacienteMouseExited(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("SF UI Display", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Paciente");

        javax.swing.GroupLayout btnPacienteLayout = new javax.swing.GroupLayout(btnPaciente);
        btnPaciente.setLayout(btnPacienteLayout);
        btnPacienteLayout.setHorizontalGroup(
            btnPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnPacienteLayout.setVerticalGroup(
            btnPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCitas.setBackground(new java.awt.Color(0, 23, 35));
        btnCitas.setForeground(new java.awt.Color(255, 255, 255));
        btnCitas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCitasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCitasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCitasMouseExited(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("SF UI Display", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Citas");

        javax.swing.GroupLayout btnCitasLayout = new javax.swing.GroupLayout(btnCitas);
        btnCitas.setLayout(btnCitasLayout);
        btnCitasLayout.setHorizontalGroup(
            btnCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCitasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnCitasLayout.setVerticalGroup(
            btnCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCitasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCitas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPacienteMouseClicked
        // TODO add your handling code here:
        pacientes paci = new pacientes();
        
        paci.setSize(new Dimension(900, 600));
        paci.setLocation(0,0);
        panelMain.removeAll();
        panelMain.add(paci,BorderLayout.CENTER);
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnPacienteMouseClicked

    private void btnPacienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPacienteMouseEntered
        // TODO add your handling code here:
        btnPaciente.setBackground(new Color(0,56,87));
    }//GEN-LAST:event_btnPacienteMouseEntered

    private void btnPacienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPacienteMouseExited
        // TODO add your handling code here:
        btnPaciente.setBackground(new Color(0,23,36));
    }//GEN-LAST:event_btnPacienteMouseExited

    private void btnCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCitasMouseClicked
        // TODO add your handling code here:
        modulocitas citas = new modulocitas();
        
        citas.setSize(new Dimension(900, 600));
        citas.setLocation(0,0);
        panelMain.removeAll();
        panelMain.add(citas,BorderLayout.CENTER);
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnCitasMouseClicked

    private void btnCitasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCitasMouseEntered
        // TODO add your handling code here:
        btnCitas.setBackground(new Color(0,56,87));
    }//GEN-LAST:event_btnCitasMouseEntered

    private void btnCitasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCitasMouseExited
        // TODO add your handling code here:
        btnCitas.setBackground(new Color(0,23,36));
    }//GEN-LAST:event_btnCitasMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel btnCitas;
    public static javax.swing.JPanel btnPaciente;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
