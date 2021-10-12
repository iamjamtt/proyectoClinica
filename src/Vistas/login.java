package Vistas;

import Entidad.asistente;
import Entidad.medico;
import Entidad.persona;
import Entidad.user;
import Modelo.userDao;
import java.awt.Color;
import javax.swing.JOptionPane;

public class login extends javax.swing.JFrame {

    Entidad.user eu = new user();
    Modelo.userDao udao = new userDao();
    
    Entidad.asistente ea = new asistente();
    
    Entidad.medico em = new medico();
    
    Entidad.persona ep = new persona();
    
    public static int ida,idm;
    public static String nombrep, apellidop, nombreapellidoAsistente;
    
    public login() {
        initComponents();
        this.setLocationRelativeTo(null);
        placeholders();
        txtUsername.setText("MED001");
        txtPass.setText("123");
    }
    
    void placeholders(){
        TextPrompt a1 = new TextPrompt("Username*", txtUsername);
        TextPrompt a2 = new TextPrompt("Password*", txtPass);
    }

    void validar(){
        String user = txtUsername.getText();
        String pass = txtPass.getText();
        
        if(txtUsername.getText().equals("") || txtPass.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campos de textos vacios");
            txtUsername.requestFocus();
        }else{
            eu = udao.ValidarUser(user, pass);
            if(eu.getUsername() != null && eu.getPassword()!= null){
                int idu = eu.getIdu();
                int idp = eu.getIdp();
                System.out.println("id user " + idu);
                System.out.println("id persona " + idp);
                
                ea = udao.validarAsistente(idp);
                ida = ea.getIda();
                String codasi = ea.getCoda();
                System.out.println("id asistente " + ida);
                System.out.println("cod asistente " + codasi);
                
                em = udao.validarMedico(idp);
                idm = em.getIdm();
                String codmed = em.getCodm();
                System.out.println("id medico " + idm);
                System.out.println("cod medico " + codmed);
                
                ep = udao.validarPersona(idp);
                String dnip = ep.getDnip();
                nombrep = ep.getNombrep();
                apellidop = ep.getApellidop();
                System.out.println("dni persona " + dnip);
                System.out.println("nombre persona " + nombrep);
                
                nombreapellidoAsistente = nombrep + " " + apellidop;
                
                main main = new main();
                main.setVisible(true);
                dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Campos de Textos Incorrectos");
                txtUsername.requestFocus();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtUsername = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtPass = new javax.swing.JPasswordField();
        btnIngresar1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(178, 229, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(300, 600));
        jPanel2.setMinimumSize(new java.awt.Dimension(300, 600));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 600));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hospital.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SF UI Display", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 23, 35));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BIENVENIDO");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 28));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsername.setFont(new java.awt.Font("SF UI Display", 0, 13)); // NOI18N
        txtUsername.setBorder(null);
        jPanel3.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 11, 150, 20));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.setMinimumSize(new java.awt.Dimension(200, 28));
        jPanel4.setPreferredSize(new java.awt.Dimension(200, 28));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPass.setFont(new java.awt.Font("SF UI Display", 0, 13)); // NOI18N
        txtPass.setBorder(null);
        jPanel4.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, -1));

        btnIngresar1.setBackground(new java.awt.Color(0, 23, 35));
        btnIngresar1.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnIngresar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIngresar1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresar1MouseExited(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("SF UI Display", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ingresar");

        javax.swing.GroupLayout btnIngresar1Layout = new javax.swing.GroupLayout(btnIngresar1);
        btnIngresar1.setLayout(btnIngresar1Layout);
        btnIngresar1Layout.setHorizontalGroup(
            btnIngresar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnIngresar1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnIngresar1Layout.setVerticalGroup(
            btnIngresar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnIngresar1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIngresar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnIngresar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/login.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 700, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresar1MouseClicked
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_btnIngresar1MouseClicked

    private void btnIngresar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresar1MouseEntered
        // TODO add your handling code here:
        btnIngresar1.setBackground(new Color(0,56,87));
    }//GEN-LAST:event_btnIngresar1MouseEntered

    private void btnIngresar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresar1MouseExited
        // TODO add your handling code here:
        btnIngresar1.setBackground(new Color(0,23,36));
    }//GEN-LAST:event_btnIngresar1MouseExited

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel btnIngresar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
