package Vistas;

import Entidad.especialidad;
import Entidad.medico;
import Entidad.paciente;
import Entidad.persona;
import Modelo.consultaDao;
import Modelo.especialidadDao;
import Modelo.genCodCita;
import Modelo.medicoDao;
import Modelo.pacienteDao;
import static Vistas.main.panelMain;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class consultaM extends javax.swing.JPanel {

    Modelo.pacienteDao padao = new pacienteDao();
    Modelo.consultaDao codao = new consultaDao();
    Modelo.medicoDao mdao = new medicoDao();
    Modelo.especialidadDao edao = new especialidadDao();
    
    Entidad.paciente epa = new paciente();
    Entidad.persona ep = new persona();
    Entidad.persona ep2 = new persona();
    Entidad.medico em = new medico();
    Entidad.especialidad ee = new especialidad();
    
    DefaultTableModel m = new DefaultTableModel();
    
    public consultaM() {
        initComponents();
        placeholders();
        cargarPaciente();
        tablatratamiento.setRowHeight(25);
    }

    //metodo para cargar un placeholder
    void placeholders(){
        TextPrompt a1 = new TextPrompt("Codigo", txtbuscarmedicamento);
    }
    
    //metodo para cargar los datos del paciente en los campo de texto
    void cargarPaciente(){
        String nrohistoriapa = consultas.nroHistoria;
        
        int idpa = padao.idpa(nrohistoriapa);
        
        epa = padao.datosPaciente(idpa, 0);
        
        ep = padao.datosPersona(epa.getIdp(), null);
        
        txthistoria.setText(nrohistoriapa);
        txtpaciente.setText(ep.getNombrep() + " " + ep.getApellidop());
    }
    
    //metodo para cargar los medicamentos en el combobox
    void cargarCombo(){
        codao.cargarComboMedicamentos(cbomedicamento, txtbuscarmedicamento.getText());
    }
    
    //metodo para agregar el medicamento y la descripcion del medicamento a la tabla tratamiento
    void agregar(){
        if(txtbuscarmedicamento.getText().equals("") || txtsintoma.getText().equals("") || txtdescripcionmedicamento.getText().equals("") || cbomedicamento.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Campo de textos vacios");
        }else{    
            m = (DefaultTableModel) tablatratamiento.getModel();
            
            int idme = codao.idme((String) cbomedicamento.getSelectedItem());
            String nombreme = (String) cbomedicamento.getSelectedItem();
            String descripcionme = txtdescripcionmedicamento.getText();
            
            ArrayList lis = new ArrayList();
            
            lis.add(idme);
            lis.add(nombreme);
            lis.add(descripcionme);
            
            Object[] o = new Object[3];
            o[0] = lis.get(0);
            o[1] = lis.get(1);
            o[2] = lis.get(2);
            
            m.addRow(o);
            tablatratamiento.setModel(m);
            
            txtbuscarmedicamento.setText("");
            txtdescripcionmedicamento.setText("");
            cbomedicamento.setSelectedIndex(-1);
        }
    }
    
    //metodo que me genera el codigo de la consulta
    String generarCodC(){
        String codco = "";
        String serie = codao.consultarCodConsulta();
        int j = 0;
        if(serie == null){
            codco = "CON001";
        }else{
            char r11 = serie.charAt(3);
            char r22 = serie.charAt(4);
            char r33 = serie.charAt(5);
            String r="";
            r=""+r11+r22+r33;
            
            j=Integer.parseInt(r);
            genCodCita gen = new genCodCita();
            gen.generar(j);
            serie = "CON" + gen.serie();  
            codco = serie;
        }
        
        return codco;
    }
    
    //metodo para agregar todos los datos de la consulta en la tabla
    void addConsulta(){
        
        if(txtsintoma.getText().equals("") || tablatratamiento.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Campo de textos vacios");
        }else{    
            String codco = generarCodC();
            DateTimeFormatter fechita = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String fechaco = ""+fechita.format(LocalDateTime.now());
            int idm = login.idm;
            int idpa = padao.idpa(consultas.nroHistoria);
            int estadoco = 1;
            String descripcionco = txtsintoma.getText();

            Object[] ob = new Object[6];

            ob[0] = codco;
            ob[1] = fechaco;
            ob[2] = idm;
            ob[3] = idpa;
            ob[4] = estadoco;
            ob[5] = descripcionco;

            int r1 = codao.addConsulta(ob);

            if(r1>0){
                JOptionPane.showMessageDialog(null, "Datos Consulta ingresado exitosamente","Mensaje",1);
            }   
            
            addDetalleConsulta();
            
            //finalizamos la cita con estadoc=3
            int idc = consultas.idc;
            
            int r2 = codao.finalizarCita(idc);
            
            if(r2>0){
                JOptionPane.showMessageDialog(null, "Cita Finaliada exitosamente","Mensaje",1);
            } 
            
            em = mdao.datosMedico(idm);
            ep2 = mdao.datosPersona(em.getIdp());
            String medico = ep2.getNombrep() + " " + ep2.getApellidop();
            ee = edao.datosEspecialidad(em.getIde());
            String especialidad = ee.getNombree();
            
            JOptionPane.showMessageDialog(null, "Abriendo Reseta de Consulta","Mensaje",1);
            try {
                pdf(codco, fechaco, medico, especialidad);
            } catch (Exception e) {
            }
            //abre el pdf generado
            abrirPDF(codco);
            
            txtbuscarmedicamento.setText("");
            cbomedicamento.setSelectedIndex(-1);
            txtdescripcionmedicamento.setText("");
            txtsintoma.setText("");
            
            limpiarTabla();
            
            JOptionPane.showMessageDialog(null, "Cita Finalizada","Mensaje",1);
            int input = JOptionPane.showConfirmDialog(null, 
                "¿Desea salir de la ventana?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);

                // 0=yes, 1=no
                if(input == 0){
                    consultas consu = new consultas();

                    consu.setSize(new Dimension(900, 600));
                    consu.setLocation(0,0);
                    panelMain.removeAll();
                    panelMain.add(consu,BorderLayout.CENTER);
                    panelMain.revalidate();
                    panelMain.repaint();
                }else if(input == 1){
                    btnAgregar.setEnabled(false);
                }
        }
    }
    
    //metodo para limpiar la tabla tratamiento
    void limpiarTabla(){
        for (int i = 0; i < m.getRowCount(); i++) {
            m.removeRow(i);
            i -= 1;
        }
    }
    
    //metodo para ingresar todos los datos en la tabla detalle de la consulta
    void addDetalleConsulta(){
        for(int i=0;i<tablatratamiento.getRowCount();i++){
            int idco = codao.obtIdConsulta();
            int idme = codao.idme((String) tablatratamiento.getValueAt(i, 1));
            String descripciond = (String) tablatratamiento.getValueAt(i, 2);

            Object[] ob = new Object[3];

            ob[0] = idco;
            ob[1] = idme;
            ob[2] = descripciond;

            codao.addDetalleConsulta(ob);
        }
        JOptionPane.showMessageDialog(null, "Datos Detalle Consulta ingresado exitosamente","Mensaje",1); 
    }
    
    //metodo que me crea un pdf de la consulta
    public void pdf(String codigo, String fecharegistro, String medico, String especialidad) throws FileNotFoundException, DocumentException{
        FileOutputStream archivo = new FileOutputStream(codigo+".pdf");
        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
                
        try {
            Font negrita1 = new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.BLACK);
            Font negrita3 = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD,BaseColor.BLACK);
            Font negrita5 = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD,BaseColor.WHITE);
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
            
            Paragraph titulo = new Paragraph("\nConsulta",negrita4);
            titulo.setAlignment(1);
            documento.add(titulo);
            Paragraph titulo2 = new Paragraph(codigo,negrita1);
            titulo2.setAlignment(1);
            documento.add(titulo2);
            
            //datos de la cita y medico
            documento.add(new Paragraph("\n_______________________________________________________"));
            documento.add(new Paragraph("Fecha de consulta:  " + fecharegistro));
            documento.add(new Paragraph("Medico:                 " + medico));
            documento.add(new Paragraph("Consultorio:          " + especialidad));
            
            //datos del paciente
            documento.add(new Paragraph("\n_______________________________________________________"));
            documento.add(new Paragraph("Paciente:              " + txtpaciente.getText()));
            documento.add(new Paragraph("Historia Clinica:    " + txthistoria.getText()));
            
            Paragraph titulo5 = new Paragraph("\nTratamiento",negrita4);
            titulo5.setAlignment(1);
            documento.add(titulo5);
            
            //Tratamiento
            documento.add(new Paragraph("\n_______________________________________________________"));
            documento.add(new Paragraph("Sintoma:              " + txtsintoma.getText()));
            
            //peliculas
            documento.add(new Paragraph("\nReceta",negrita3));
            documento.add(new Paragraph("\n"));
            PdfPTable tablaP = new PdfPTable(3);
            tablaP.setWidthPercentage(100);
            tablaP.getDefaultCell().setBorder(0);
            float[] clumnasP = new float[]{15f, 40f, 60f};
            tablaP.setWidths(clumnasP);
            tablaP.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell p1 = new  PdfPCell(new Phrase("Nro",negrita5));
            PdfPCell p2 = new  PdfPCell(new Phrase("Medicamento",negrita5));
            PdfPCell p3 = new  PdfPCell(new Phrase("Descripcion",negrita5));
            p1.setBorder(0);
            p2.setBorder(0);
            p3.setBorder(0);
            p1.setBackgroundColor(BaseColor.DARK_GRAY);
            p2.setBackgroundColor(BaseColor.DARK_GRAY);
            p3.setBackgroundColor(BaseColor.DARK_GRAY);
            tablaP.addCell(p1);
            tablaP.addCell(p2);
            tablaP.addCell(p3);
            for(int i=0;i<tablatratamiento.getRowCount();i++){
                String medi = tablatratamiento.getValueAt(i, 1).toString();
                String descr = tablatratamiento.getValueAt(i, 2).toString();
                int nroo= i+1;
                String nro = "" + nroo;
                
                tablaP.addCell(nro);
                tablaP.addCell(medi);
                tablaP.addCell(descr);
            }
            
            documento.add(tablaP);
            
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
    
    //metodo que me abre el pdf generado
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
        jPanel4 = new javax.swing.JPanel();
        btnSalir2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txthistoria = new javax.swing.JTextField();
        txtpaciente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtsintoma = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtbuscarmedicamento = new javax.swing.JTextField();
        cbomedicamento = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdescripcionmedicamento = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablatratamiento = new javax.swing.JTable();
        btnGenrar = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btnBusca = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jMenuItem1.setText("Eliminar");
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

        jLabel1.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel1.setText("Historia Clinica:");

        jLabel2.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel2.setText("Paciente:");

        txthistoria.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        txthistoria.setBorder(null);

        txtpaciente.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        txtpaciente.setBorder(null);

        jLabel4.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel4.setText("Sintoma:");

        txtsintoma.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("SF UI Display", 1, 20)); // NOI18N
        jLabel5.setText("Tratamiento");

        txtbuscarmedicamento.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        txtbuscarmedicamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarmedicamentoKeyTyped(evt);
            }
        });

        cbomedicamento.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N

        txtdescripcionmedicamento.setColumns(20);
        txtdescripcionmedicamento.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        txtdescripcionmedicamento.setRows(5);
        jScrollPane1.setViewportView(txtdescripcionmedicamento);

        jLabel6.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel6.setText("Medicamento: ");

        tablatratamiento.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        tablatratamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NRO", "MEDICAMENTO", "DESCRIPCION"
            }
        ));
        tablatratamiento.setComponentPopupMenu(menu);
        jScrollPane2.setViewportView(tablatratamiento);
        if (tablatratamiento.getColumnModel().getColumnCount() > 0) {
            tablatratamiento.getColumnModel().getColumn(0).setMinWidth(50);
            tablatratamiento.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablatratamiento.getColumnModel().getColumn(0).setMaxWidth(50);
            tablatratamiento.getColumnModel().getColumn(1).setMinWidth(200);
            tablatratamiento.getColumnModel().getColumn(1).setPreferredWidth(200);
            tablatratamiento.getColumnModel().getColumn(1).setMaxWidth(200);
        }

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
        jLabel11.setText("Guardar");

        javax.swing.GroupLayout btnGenrarLayout = new javax.swing.GroupLayout(btnGenrar);
        btnGenrar.setLayout(btnGenrarLayout);
        btnGenrarLayout.setHorizontalGroup(
            btnGenrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnGenrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnGenrarLayout.setVerticalGroup(
            btnGenrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnGenrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAgregar.setBackground(new java.awt.Color(178, 229, 255));
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarMouseExited(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(0, 23, 35));
        jLabel15.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Agregar");

        javax.swing.GroupLayout btnAgregarLayout = new javax.swing.GroupLayout(btnAgregar);
        btnAgregar.setLayout(btnAgregarLayout);
        btnAgregarLayout.setHorizontalGroup(
            btnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAgregarLayout.setVerticalGroup(
            btnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnBusca.setFont(new java.awt.Font("SF UI Display", 0, 14)); // NOI18N
        btnBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buscar (1).png"))); // NOI18N
        btnBusca.setBorderPainted(false);
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SF UI Display", 1, 14)); // NOI18N
        jLabel7.setText("Recomendaciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtbuscarmedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBusca)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbomedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtsintoma))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txtpaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthistoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGenrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txthistoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtpaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsintoma)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbomedicamento)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBusca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtbuscarmedicamento, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGenrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalir2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir2MouseClicked
        // TODO add your handling code here:
        panelMain.removeAll();
        panelMain.revalidate();
        panelMain.repaint();
    }//GEN-LAST:event_btnSalir2MouseClicked

    private void btnGenrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenrarMouseClicked
        // TODO add your handling code here:
        addConsulta();
    }//GEN-LAST:event_btnGenrarMouseClicked

    private void btnGenrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenrarMouseEntered
        // TODO add your handling code here:
        btnGenrar.setBackground(new Color(229, 246, 255));
    }//GEN-LAST:event_btnGenrarMouseEntered

    private void btnGenrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenrarMouseExited
        // TODO add your handling code here:
        btnGenrar.setBackground(new Color(179, 229, 255));
    }//GEN-LAST:event_btnGenrarMouseExited

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        // TODO add your handling code here:
        agregar();
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarMouseEntered

    private void btnAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarMouseExited

    private void txtbuscarmedicamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarmedicamentoKeyTyped
        // TODO add your handling code here:
        cargarCombo();
    }//GEN-LAST:event_txtbuscarmedicamentoKeyTyped

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        // TODO add your handling code here:
        cargarCombo();
        
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        m = (DefaultTableModel) tablatratamiento.getModel();
        
        int fila = tablatratamiento.getSelectedRow();
        
        if(fila>=0){
            m.removeRow(fila);
        }else{
            JOptionPane.showMessageDialog(null, "Selecciones una fila");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAgregar;
    private javax.swing.JButton btnBusca;
    private javax.swing.JPanel btnGenrar;
    private javax.swing.JLabel btnSalir2;
    private javax.swing.JComboBox<String> cbomedicamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu menu;
    private javax.swing.JTable tablatratamiento;
    private javax.swing.JTextField txtbuscarmedicamento;
    private javax.swing.JTextArea txtdescripcionmedicamento;
    private javax.swing.JTextField txthistoria;
    private javax.swing.JTextField txtpaciente;
    private javax.swing.JTextField txtsintoma;
    // End of variables declaration//GEN-END:variables
}
