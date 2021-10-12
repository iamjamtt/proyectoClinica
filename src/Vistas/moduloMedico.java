package Vistas;

import static Vistas.main.panelMain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class moduloMedico extends javax.swing.JPanel {

    public moduloMedico() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnConsultas = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnHC = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(178, 229, 255));
        setMaximumSize(new java.awt.Dimension(300, 530));
        setMinimumSize(new java.awt.Dimension(300, 530));

        btnConsultas.setBackground(new java.awt.Color(0, 23, 35));
        btnConsultas.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConsultasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultasMouseExited(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("SF UI Display", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Consultas");

        javax.swing.GroupLayout btnConsultasLayout = new javax.swing.GroupLayout(btnConsultas);
        btnConsultas.setLayout(btnConsultasLayout);
        btnConsultasLayout.setHorizontalGroup(
            btnConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnConsultasLayout.setVerticalGroup(
            btnConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnHC.setBackground(new java.awt.Color(0, 23, 35));
        btnHC.setForeground(new java.awt.Color(255, 255, 255));
        btnHC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnHC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHCMouseExited(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("SF UI Display", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Historia Clinica");

        javax.swing.GroupLayout btnHCLayout = new javax.swing.GroupLayout(btnHC);
        btnHC.setLayout(btnHCLayout);
        btnHCLayout.setHorizontalGroup(
            btnHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnHCLayout.setVerticalGroup(
            btnHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnConsultas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(367, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultasMouseClicked
        // TODO add your handling code here:
        consultas con = new consultas();
        
        con.setSize(new Dimension(900, 600));
        con.setLocation(0,0);
        panelMain.removeAll();
        panelMain.add(con,BorderLayout.CENTER);
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnConsultasMouseClicked

    private void btnConsultasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultasMouseEntered
        // TODO add your handling code here:
        btnConsultas.setBackground(new Color(0,56,87));
    }//GEN-LAST:event_btnConsultasMouseEntered

    private void btnConsultasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultasMouseExited
        // TODO add your handling code here:
        btnConsultas.setBackground(new Color(0,23,36));
    }//GEN-LAST:event_btnConsultasMouseExited

    private void btnHCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHCMouseClicked
        // TODO add your handling code here:
        historiaclinica his = new historiaclinica();
        
        his.setSize(new Dimension(900, 600));
        his.setLocation(0,0);
        panelMain.removeAll();
        panelMain.add(his,BorderLayout.CENTER);
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnHCMouseClicked

    private void btnHCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHCMouseEntered
        // TODO add your handling code here:
        btnHC.setBackground(new Color(0,56,87));
    }//GEN-LAST:event_btnHCMouseEntered

    private void btnHCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHCMouseExited
        // TODO add your handling code here:
        btnHC.setBackground(new Color(0,23,36));
    }//GEN-LAST:event_btnHCMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel btnConsultas;
    public static javax.swing.JPanel btnHC;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
