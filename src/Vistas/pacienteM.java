package Vistas;

import Entidad.paciente;
import Entidad.persona;
import Modelo.genNroHistoria;
import Modelo.pacienteDao;
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

public class pacienteM extends javax.swing.JPanel {

    Modelo.pacienteDao padao = new pacienteDao();
    
    Entidad.persona ep = new persona();
    Entidad.paciente epa = new paciente();
    
    public pacienteM() {
        initComponents();
        titulo();
        txtnrohistoria.setEditable(false);
    }
    
    //aqui nos carga el titulo de la interfaz ya sea para ingresar o modificaar datos
    void titulo(){
        if(pacientes.tipo==1){
            txtTitulo.setText("");
            txtTitulo.setText("Ingresar Paciente");
            txtboton.setText("");
            txtboton.setText("Ingresar");
            generarHistoria();
        }else if(pacientes.tipo==2){
            txtTitulo.setText("");
            txtTitulo.setText("Modificar Paciente");
            txtboton.setText("");
            txtboton.setText("Modificar");
            cargarDatosActualizar();
        }
    }
    
    //aqui generamos el numero de historia del paciente 
    void generarHistoria(){
        String serie = padao.nroHistoria(); // CTA003
        int j;
        if(serie == null){
            txtnrohistoria.setText("000001");
        }else{
            char r1 = serie.charAt(0);
            char r2 = serie.charAt(1);
            char r3 = serie.charAt(2);
            char r4 = serie.charAt(3);
            char r5 = serie.charAt(4);
            char r6 = serie.charAt(5);
            String r="";
            r=""+r1+r2+r3+r4+r5+r6;
            
            j=Integer.parseInt(r);
            Modelo.genNroHistoria gen = new genNroHistoria();
            gen.generar(j);
            serie = ""+gen.serie(); // 004  
            txtnrohistoria.setText(serie); // CTA004
        }
    }
    
    //aqui cargamos todos los datos del paciente para proceder a modificar
    void cargarDatosActualizar(){
        int idpa = pacientes.idpa; 
        
        int idp = padao.buscaridPersona(idpa);
        
        ep = padao.datosPersona(idp,null);
        
        txtdni.setText(ep.getDnip());
        System.out.println("dnip " + ep.getDnip());
        txtnombre.setText(ep.getNombrep());
        txtapellido.setText(ep.getApellidop());
        cbosexo.setSelectedItem(ep.getSexop());
        txtemail.setText(ep.getEmailp());
        txtdireccion.setText(ep.getDireccionp());
        txttelefono.setText(ep.getTelefonop());
        
        epa = padao.datosPaciente(idpa,0);
        
        txtnrohistoria.setText(epa.getNrohistoriapa());
        try {
            SimpleDateFormat formatodeltexto = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = null;
            fecha = (Date) formatodeltexto.parse(epa.getFechanapa());
            txtnacimiento.setDate(fecha);
        } catch (Exception e) {
        }
        
    }
    
    //este metodo me valida los datos del paciente y si todo esta bien me ingresa a la db correspondiente
    void agregarPaciente(){
        String dni = padao.buscarPersonaPacienteDni(txtdni.getText());
        if(txtnombre.getText().equals("") || txtapellido.getText().equals("") || txttelefono.getText().equals("") || txtdni.getText().equals("") || txtdireccion.getText().equals("") ||
                txtemail.getText().equals("") || txtnrohistoria.getText().equals("") || cbosexo.getSelectedIndex()==0 || txtnacimiento.getDate() == null){
            JOptionPane.showMessageDialog(null, "Campos de textos vacios");
            txtdni.requestFocus();
        }else if(txtdni.getText().equals(dni)){
            JOptionPane.showMessageDialog(null, "El dni que intenta ingresar ya existe");
        }else{
            
            String dnip = txtdni.getText();
            String nombrep = txtnombre.getText();
            String apellidop = txtapellido.getText();
            String sexop = String.valueOf(cbosexo.getSelectedItem());
            String emailp = txtemail.getText();
            String direccionp = txtdireccion.getText();
            String telefono = txttelefono.getText();
            DateTimeFormatter fechita = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String fecharegistro = ""+fechita.format(LocalDateTime.now());
            int estadop = 1;

            Object[] ob = new Object[9];

            ob[0] = dnip;
            ob[1] = nombrep;
            ob[2] = apellidop;
            ob[3] = sexop;
            ob[4] = emailp;
            ob[5] = direccionp;
            ob[6] = telefono;
            ob[7] = fecharegistro;
            ob[8] = estadop;

            int r1 = padao.addPaciente(ob);
            
            if(r1>0){
                JOptionPane.showMessageDialog(null, "Datos Persona ingresados correctamente");
            }    
            
            //tabla paciente
            String nrohistoriapa = txtnrohistoria.getText();
            Date f = txtnacimiento.getDate();
            DateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
            String fechanapa = f2.format(f);
            int estadopa = 1;
            int idp = padao.idPersona();
            
            Object[] ob1 = new Object[4];

            ob1[0] = nrohistoriapa;
            ob1[1] = fechanapa;
            ob1[2] = estadopa;
            ob1[3] = idp;
            
            int r2 = padao.addPaciente2(ob1);
            
            if(r2>0){
                JOptionPane.showMessageDialog(null, "Datos Paciente ingresados correctamente");
            }  
            
        
            pacientes paci = new pacientes();

            paci.setSize(new Dimension(900, 600));
            paci.setLocation(0,0);
            panelMain.removeAll();
            panelMain.add(paci,BorderLayout.CENTER);
            panelMain.revalidate();
            panelMain.repaint();
            
        }
    }
    
    // metodo que me valida campos y si todo anda bien me actualiza los datos del paciente
    void actualizarPaciente(){
        if(txtnombre.getText().equals("") || txtapellido.getText().equals("") || txttelefono.getText().equals("") || txtdni.getText().equals("") || txtdireccion.getText().equals("") ||
                txtemail.getText().equals("") || txtnrohistoria.getText().equals("") || cbosexo.getSelectedIndex()==0 || txtnacimiento.getDate() == null){
            JOptionPane.showMessageDialog(null, "Campos de textos vacios");
            txtdni.requestFocus();
        }else{
            int idpa = pacientes.idpa; 
            
            int idp = padao.buscaridPersona(idpa);
            
            String dnip = txtdni.getText();
            String nombrep = txtnombre.getText();
            String apellidop = txtapellido.getText();
            String sexop = String.valueOf(cbosexo.getSelectedItem());
            String emailp = txtemail.getText();
            String direccionp = txtdireccion.getText();
            String telefono = txttelefono.getText();

            Object[] ob = new Object[8];

            ob[0] = dnip;
            ob[1] = nombrep;
            ob[2] = apellidop;
            ob[3] = sexop;
            ob[4] = emailp;
            ob[5] = direccionp;
            ob[6] = telefono;
            ob[7] = idp;

            int r1 = padao.updatePersonaPaciente(ob);
            
            if(r1>0){
                JOptionPane.showMessageDialog(null, "Datos Persona actualizados correctamente");
            }    
            
            //tabla paciente
            Date f = txtnacimiento.getDate();
            DateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
            String fechanapa = f2.format(f);
            
            Object[] ob1 = new Object[2];

            ob1[0] = fechanapa;
            ob1[1] = idpa;
            
            int r2 = padao.updatePaciente(ob1);
            
            if(r2>0){
                JOptionPane.showMessageDialog(null, "Datos Paciente actualizados correctamente");
            }  
            
        
            pacientes paci = new pacientes();

            paci.setSize(new Dimension(900, 600));
            paci.setLocation(0,0);
            panelMain.removeAll();
            panelMain.add(paci,BorderLayout.CENTER);
            panelMain.revalidate();
            panelMain.repaint();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtnrohistoria = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtdni = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        cbosexo = new javax.swing.JComboBox<>();
        btnIngresar = new javax.swing.JPanel();
        txtboton = new javax.swing.JLabel();
        txtnacimiento = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));

        jPanel2.setBackground(new java.awt.Color(0, 23, 35));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 875, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtTitulo.setBackground(new java.awt.Color(0, 23, 35));
        txtTitulo.setFont(new java.awt.Font("SF UI Display", 1, 30)); // NOI18N
        txtTitulo.setText("Paciente");

        jLabel1.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel1.setText("Dni:");

        jLabel3.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel4.setText("Apellido:");

        jLabel5.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel5.setText("Nro Historia:");

        jLabel6.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel6.setText("Sexo:");

        jLabel7.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel7.setText("Direccion:");

        jLabel8.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel8.setText("Email:");

        jLabel9.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel9.setText("telefono:");

        jLabel10.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel10.setText("Nacimineto:");

        txtnrohistoria.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        txtnombre.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        txtemail.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });

        txtdni.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        txtdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdniKeyTyped(evt);
            }
        });

        txtapellido.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        txtdireccion.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        txttelefono.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        txttelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoActionPerformed(evt);
            }
        });
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });

        cbosexo.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        cbosexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Masculino", "Femenino" }));

        btnIngresar.setBackground(new java.awt.Color(178, 229, 255));
        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIngresarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresarMouseExited(evt);
            }
        });

        txtboton.setBackground(new java.awt.Color(0, 23, 35));
        txtboton.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        txtboton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtboton.setText("Ingresar");

        javax.swing.GroupLayout btnIngresarLayout = new javax.swing.GroupLayout(btnIngresar);
        btnIngresar.setLayout(btnIngresarLayout);
        btnIngresarLayout.setHorizontalGroup(
            btnIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnIngresarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtboton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnIngresarLayout.setVerticalGroup(
            btnIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnIngresarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtboton, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtnacimiento.setBackground(new java.awt.Color(255, 255, 255));
        txtnacimiento.setDateFormatString("dd-MM-yyyy");
        txtnacimiento.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtnacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel1))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbosexo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 247, Short.MAX_VALUE)
                                    .addComponent(txtemail)
                                    .addComponent(txtdni))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnrohistoria)
                            .addComponent(txtdireccion)
                            .addComponent(txttelefono)
                            .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(76, 76, 76))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtTitulo)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cbosexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtnrohistoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10))
                    .addComponent(txtnacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        pacientes paci = new pacientes();

        paci.setSize(new Dimension(900, 600));
        paci.setLocation(0,0);
        panelMain.removeAll();
        panelMain.add(paci,BorderLayout.CENTER);
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnSalirMouseClicked

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    private void btnIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseClicked
        // TODO add your handling code here:
        if(pacientes.tipo==1){
            agregarPaciente();
        }else if(pacientes.tipo==2){
            actualizarPaciente();
        }
    }//GEN-LAST:event_btnIngresarMouseClicked

    private void btnIngresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseEntered
        // TODO add your handling code here:
        btnIngresar.setBackground(new Color(229, 246, 255));
    }//GEN-LAST:event_btnIngresarMouseEntered

    private void btnIngresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseExited
        // TODO add your handling code here:
        btnIngresar.setBackground(new Color(179, 229, 255));
    }//GEN-LAST:event_btnIngresarMouseExited

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoActionPerformed

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0' || c>'9') evt.consume();
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtdniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0' || c>'9') evt.consume();
    }//GEN-LAST:event_txtdniKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnIngresar;
    private javax.swing.JLabel btnSalir;
    private javax.swing.JComboBox<String> cbosexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel txtTitulo;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JLabel txtboton;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtemail;
    private com.toedter.calendar.JDateChooser txtnacimiento;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnrohistoria;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
