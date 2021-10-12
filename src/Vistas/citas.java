package Vistas;

import Entidad.especialidad;
import Entidad.medico;
import Entidad.paciente;
import Entidad.persona;
import Modelo.citaDao;
import Modelo.especialidadDao;
import Modelo.genCodCita;
import Modelo.genOrden;
import Modelo.medicoDao;
import Modelo.pacienteDao;
import static Vistas.main.panelMain;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;

public class citas extends javax.swing.JPanel {

    Entidad.persona ep = new persona();
    Entidad.persona ep2 = new persona();
    Entidad.persona ep3 = new persona();
    Entidad.paciente epa = new paciente();
    Entidad.medico em = new medico();
    Entidad.especialidad ee = new especialidad();
    
    
    Modelo.pacienteDao padao = new pacienteDao();
    Modelo.medicoDao mdao = new medicoDao();
    Modelo.especialidadDao edao = new especialidadDao();
    Modelo.citaDao cdao = new citaDao();
    
    public static String dnip;
    
    public citas() {
        initComponents();
        txtcodc.setEditable(false);
        txtpaciente.setEditable(false);
        txtsexo.setEditable(false);
        txthistoria.setEditable(false);
        txtconsultorio.setEditable(false);
        txtorden.setEditable(false);
        txtfechacita.setEditable(false);
        txtmedico.setEditable(false);
        cargarDatosPaciente();
        cargarMedico();
        generarCodC();
    }
    
    void cargarDatosPaciente(){
        
        if(medicos.dniper==null || medicos.dniper.equals("")){
            if(txtdni.getText().equals("")){
                
            }else{
                dnip = txtdni.getText();
                
                int resp = padao.buscarPersonaPaciente(dnip);
                
                if(resp > 0){
                    ep = padao.datosPersona(0, dnip);
                    txtpaciente.setText(ep.getNombrep() + " " + ep.getApellidop());
                    txtsexo.setText(ep.getSexop());

                    int idp = ep.getIdp();
                    epa = padao.datosPaciente(0, idp);
                    txthistoria.setText(epa.getNrohistoriapa());
                }else{
                    JOptionPane.showMessageDialog(null, "Dni del paciente no existe");
                    txtpaciente.setText("");
                    txtsexo.setText("");txthistoria.setText("");
                }
            }
            
        }else{
            txtdni.setText(medicos.dniper);
            ep = padao.datosPersona(0, medicos.dniper);
            txtpaciente.setText(ep.getNombrep() + " " + ep.getApellidop());
            txtsexo.setText(ep.getSexop());

            int idp = ep.getIdp();
            epa = padao.datosPaciente(0, idp);
            txthistoria.setText(epa.getNrohistoriapa());
        }
            
    }
    
    void generarOrden(){
        String serie = cdao.maxOrden(medicos.fechac, medicos.idm);
        int j;
        if(serie == null){
            txtorden.setText("01 / 03");
        }else{
            char r1 = serie.charAt(0);
            char r2 = serie.charAt(1);
            String r="";
            r=""+r1+r2;
            
            j=Integer.parseInt(r);
            Modelo.genOrden gen = new genOrden();
            gen.generar(j);
            serie = gen.serie() + " / 03";
            txtorden.setText(serie);
        }
    }
    
    void cargarMedico(){
        if(medicos.idm==0){
            
        }else{
            String horita = "";
            int ordenc = cdao.orden(medicos.fechac, medicos.idm);
            
            if(ordenc < 3){
                switch(ordenc){
                    case 0:
                        horita = "09:00";
                        break;
                    case 1:
                        horita = "12:00";
                        break;
                    case 2:
                        horita = "15:00";
                        break;
                }
                
                em = mdao.datosMedico(medicos.idm);
                int idp = em.getIdp();

                ep2 = mdao.datosPersona(idp);
                txtmedico.setText(ep2.getNombrep() + " " + ep2.getApellidop());

                ee = edao.datosEspecialidad(medicos.ide);
                txtconsultorio.setText(ee.getNombree());
                
                txtfechacita.setText(medicos.fechac);
                
                txthoracita.setText(horita);
                
                generarOrden();
            }else{
                JOptionPane.showMessageDialog(null, "Citas llenas para la fecha " + medicos.fechac + ", seleccione otra fecha.");
            }
        }
    }
    
    void generarCodC(){
        String serie = cdao.consultarCodC();
        int j = 0;
        if(serie.equals(null)){
            txtcodc.setText("CIT001");
        }else{
            char r11 = serie.charAt(3);
            char r22 = serie.charAt(4);
            char r33 = serie.charAt(5);
            String r="";
            r=""+r11+r22+r33;
            
            j=Integer.parseInt(r);
            genCodCita gen = new genCodCita();
            gen.generar(j);
            serie = "CIT"+gen.serie();  
            txtcodc.setText(serie);
        }
    }
    
    void ingresarCita(){
        String horacDB = cdao.horaCita(medicos.fechac, medicos.idm, (txthoracita.getText() + ":00"));
        System.out.println("Hora cita db: " + horacDB);
        if(txtdni.getText().equals("") || txthoracita.getText().equals("") || txtpaciente.getText().equals("") || txtmedico.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campos Vacios");
            txtdni.requestFocus();
        }else if((txthoracita.getText() + ":00").equals(horacDB)){
            JOptionPane.showMessageDialog(null, "Hora de la cita ya ocupada");
        }else{
            String fechac = medicos.fechac;
            String horac = txthoracita.getText();
            int idm = medicos.idm;
            String ordenc = txtorden.getText();
            int idpa = padao.idpa(txthistoria.getText());
            int ida = login.ida;
            int estadoc = 1;
            DateTimeFormatter fechitaa = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String fecharegistro = ""+fechitaa.format(LocalDateTime.now());
            String codc = txtcodc.getText();
            
            Object[] ob = new Object[9];

            ob[0] = fechac;
            ob[1] = horac;
            ob[2] = idm;
            ob[3] = ordenc;
            ob[4] = idpa;
            ob[5] = ida;
            ob[6] = estadoc;
            ob[7] = fecharegistro;
            ob[8] = codc;

            int r1 = cdao.addCita(ob);
            
            if(r1>0){
                JOptionPane.showMessageDialog(null, "Datos de Cita ingresados correctamente");
            }    

            try {
                JOptionPane.showMessageDialog(null, "Abriendo Voucher de Venta","Mensaje",1);
                
                //genera el pdf
                pdf(codc, fecharegistro);
                
                //abre el pdf
                abrirPDF(codc);
                
            } catch (Exception e) {
                System.out.println("error al generar el pdf:: " + e);
            }
            
            
            limpiarFormualario();
        }
    }
    
    void limpiarFormualario(){
        txtdni.setText("");
        txtpaciente.setText("");
        txtsexo.setText("");
        txthistoria.setText("");
        txtconsultorio.setText("");
        txtorden.setText("");
        txtfechacita.setText("");
        txtmedico.setText("");
        txthoracita.setText("");

        medicos.ide = 0;
        medicos.idm = 0;
        medicos.dniper = "";
        dnip = "";
        
        generarCodC();
    }

    public void pdf(String codigo, String fecharegistro) throws FileNotFoundException, DocumentException{
        FileOutputStream archivo = new FileOutputStream(codigo+".pdf");
        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
                
        try {
            Font negrita1 = new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.BLACK);
            Font negrita3 = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD,BaseColor.BLACK);
            Font negrita2 = new Font(Font.FontFamily.HELVETICA,22,Font.BOLD,BaseColor.BLACK);
            Font negrita4 = new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.BLACK);

            PdfPTable encabezado = new PdfPTable(1);
            encabezado.setWidthPercentage(100);
            encabezado.getDefaultCell().setBorder(0);
            float[] clumnasEncabezado = new float[]{100f};
            encabezado.setWidths(clumnasEncabezado);
            encabezado.setHorizontalAlignment(Element.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph("EsSalud",negrita2);
            parrafo.setAlignment(1);
            documento.add(parrafo);
            
            
            Paragraph parrafo2 = new Paragraph("\nH.II PUCALLPA",negrita4);
            parrafo2.setAlignment(1);
            documento.add(parrafo2);
            
            Paragraph parrafo3 = new Paragraph("Telefono: (061) 263219",negrita4);
            parrafo3.setAlignment(1);
            documento.add(parrafo3);
            
            Paragraph titulo = new Paragraph("\nCita",negrita4);
            titulo.setAlignment(1);
            documento.add(titulo);
            Paragraph titulo2 = new Paragraph(codigo,negrita1);
            titulo2.setAlignment(1);
            documento.add(titulo2);
            
            //datos de la cita y medico
            documento.add(new Paragraph("\n_______________________________________________________"));
            documento.add(new Paragraph("\nFecha de la Cita:  " + txtfechacita.getText()));
            documento.add(new Paragraph("Hora de la Cita:    " + txthoracita.getText()));
            documento.add(new Paragraph("Medico:                 " + txtmedico.getText()));
            documento.add(new Paragraph("Consultorio:          " + txtconsultorio.getText()));
            documento.add(new Paragraph("Orden / Total:       " + txtorden.getText()));
            
            //datos del paciente
            documento.add(new Paragraph("\n_______________________________________________________"));
            documento.add(new Paragraph("\nPaciente:              " + txtpaciente.getText()));
            documento.add(new Paragraph("Dni:                      " + txtdni.getText()));
            documento.add(new Paragraph("Historia Clinica:    " + txthistoria.getText()));
            
            //datos del asistente
            documento.add(new Paragraph("\n_______________________________________________________"));
            documento.add(new Paragraph("\nTerminalista:         " + login.nombreapellidoAsistente));
            documento.add(new Paragraph("Fecha:                   " + fecharegistro));
            
            Paragraph footer = new Paragraph("\n\nLA HIGIENE DE MANO, SALVA VIDAS");
            footer.setAlignment(1);
            documento.add(footer);
            
            Paragraph finall = new Paragraph("\n\n\nGracias", negrita3);
            finall.setAlignment(Element.ALIGN_CENTER);
            documento.add(finall);
            
        } catch (BadElementException ex) {
            System.out.println("" + ex);
        }
        documento.close();
        
    }
    
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

        jPanel3 = new javax.swing.JPanel();
        btnSalir1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnGenrar = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtpaciente = new javax.swing.JTextField();
        txtsexo = new javax.swing.JTextField();
        txtdni = new javax.swing.JTextField();
        txthistoria = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtmedico = new javax.swing.JTextField();
        txtconsultorio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtorden = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnBuscarMedico = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtfechacita = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txthoracita = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtcodc = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));

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
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel2.setBackground(new java.awt.Color(0, 23, 35));
        jLabel2.setFont(new java.awt.Font("SF UI Display", 1, 30)); // NOI18N
        jLabel2.setText("Citas");

        jLabel1.setFont(new java.awt.Font("SF UI Display", 1, 18)); // NOI18N
        jLabel1.setText("Paciente");

        btnGenrar.setBackground(new java.awt.Color(178, 229, 255));
        btnGenrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGenrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGenrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGenrarMouseExited(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(0, 23, 35));
        jLabel11.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Generar");

        javax.swing.GroupLayout btnGenrarLayout = new javax.swing.GroupLayout(btnGenrar);
        btnGenrar.setLayout(btnGenrarLayout);
        btnGenrarLayout.setHorizontalGroup(
            btnGenrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnGenrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnGenrarLayout.setVerticalGroup(
            btnGenrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnGenrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel4.setText("Dni:");

        jLabel5.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel5.setText("Sexo:");

        jLabel6.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel6.setText("Nro Historia:");

        txtpaciente.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        txtsexo.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        txtsexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsexoActionPerformed(evt);
            }
        });

        txtdni.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        txthistoria.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buscar (1).png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SF UI Display", 1, 18)); // NOI18N
        jLabel7.setText("Medico");

        jLabel8.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel8.setText("Nombre:");

        txtmedico.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        txtconsultorio.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel9.setText("Consultorio:");

        txtorden.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel10.setText("Orden / Total:");

        btnBuscarMedico.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        btnBuscarMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buscar (1).png"))); // NOI18N
        btnBuscarMedico.setBorderPainted(false);
        btnBuscarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMedicoActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel12.setText("Fecha:");

        txtfechacita.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel13.setText("Hora:");

        txthoracita.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        txthoracita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthoracitaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("SF UI Display", 1, 16)); // NOI18N
        jLabel14.setText("Codigo:");

        txtcodc.setFont(new java.awt.Font("SF UI Display", 1, 16)); // NOI18N
        txtcodc.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtsexo)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(txtpaciente)
                            .addComponent(txthistoria)
                            .addComponent(txtfechacita)
                            .addComponent(txthoracita))
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarMedico)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnGenrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtmedico, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtconsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtorden, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtcodc, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel2)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtcodc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9)
                            .addComponent(txtconsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(txtorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txthistoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(btnBuscarMedico))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtfechacita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txthoracita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnGenrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalir1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir1MouseClicked
        // TODO add your handling code here:
        limpiarFormualario();
        
        modulocitas citas = new modulocitas();
        
        citas.setSize(new Dimension(900, 600));
        citas.setLocation(0,0);
        panelMain.removeAll();
        panelMain.add(citas,BorderLayout.CENTER);
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnSalir1MouseClicked

    private void btnGenrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenrarMouseClicked
        // TODO add your handling code here:
        ingresarCita();
    }//GEN-LAST:event_btnGenrarMouseClicked

    private void btnGenrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenrarMouseEntered
        // TODO add your handling code here:
        btnGenrar.setBackground(new Color(229, 246, 255));
    }//GEN-LAST:event_btnGenrarMouseEntered

    private void btnGenrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenrarMouseExited
        // TODO add your handling code here:
        btnGenrar.setBackground(new Color(179, 229, 255));
    }//GEN-LAST:event_btnGenrarMouseExited

    private void txtsexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsexoActionPerformed

    private void txthoracitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthoracitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthoracitaActionPerformed

    private void btnBuscarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicoActionPerformed
        // TODO add your handling code here:
        medicos med = new medicos();
        
        med.setSize(new Dimension(900, 600));
        med.setLocation(0,0);
        panelMain.removeAll();
        panelMain.add(med,BorderLayout.CENTER);
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnBuscarMedicoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(txtdni.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campo de texto vacios");
            txtdni.requestFocus();
        }else{
            cargarDatosPaciente();
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarMedico;
    private javax.swing.JPanel btnGenrar;
    private javax.swing.JLabel btnSalir1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtcodc;
    private javax.swing.JTextField txtconsultorio;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtfechacita;
    private javax.swing.JTextField txthistoria;
    private javax.swing.JTextField txthoracita;
    private javax.swing.JTextField txtmedico;
    private javax.swing.JTextField txtorden;
    private javax.swing.JTextField txtpaciente;
    private javax.swing.JTextField txtsexo;
    // End of variables declaration//GEN-END:variables
}
