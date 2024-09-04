package comp.model;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import comp.flex.Lexer;
import comp.flex.LexerColor;
import java.awt.Font;

//USO DE COPILER TOOLS  (VARIABLES)
public class Compiler extends javax.swing.JFrame 
{
    private String title;
    private Directory directorio;
    private ArrayList< Token > tokens;
    private ArrayList< ErrorLSSL > errors;
    private ArrayList< TextColor > textsColor;
    private Timer timerKeyReleased;
    private ArrayList< Production > identProd;
    private ArrayList< Production > ifProd;
    private ArrayList< Production > whileProd;  
    private ArrayList< Production > funcProd;
    private HashMap< String, String > identificadores;
    private boolean codeHasBeenCompiled = false;
    private ArrayList< Production > asigProd;
    private ArrayList< Production > asigProdConID;
    private ArrayList< Production > compaProdIzq;
    private ArrayList< Production > compaProdDer;
    private ArrayList< Production > compaProdDoble;
    private ArrayList< Production > operProdIzq;
    private ArrayList< Production > operProdDer;
    private ArrayList< Production > operProdDoble;
    private ArrayList< Production > identProdCopia;

    //constructo principal
    public Compiler() 
    {
        initComponents();
        init();
    }

    private void init() 
    {
        title = "CompilerCOMP IDE 2.0";
        setLocationRelativeTo( null );
        setTitle( title );
        directorio = new Directory( this, jTextPaneSource, title, ".comp" );
        addWindowListener( new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing( WindowEvent e ) 
            {
                directorio.Exit();
                System.exit( 0 );
            }
        } );
        
        Functions.setLineNumberOnJTextComponent( jTextPaneSource );
        timerKeyReleased = new Timer( ( int ) ( 1000 * 0.3 ), ( ActionEvent e ) -> {
            timerKeyReleased.stop();
            int posicion = jTextPaneSource.getCaretPosition();
            jTextPaneSource.setText( jTextPaneSource.getText().replaceAll( "[\r]+", "" ) );
            jTextPaneSource.setCaretPosition( posicion );
            colorAnalysis();
        } );
        
        Functions.insertAsteriskInName( this, jTextPaneSource, () -> {
            timerKeyReleased.restart();
        } );
        
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        ifProd = new ArrayList<>();
        whileProd = new ArrayList<>();
        asigProd = new ArrayList<>();
        asigProdConID = new ArrayList<>();
        compaProdIzq = new ArrayList<>();
        compaProdDer = new ArrayList<>();
        compaProdDoble = new ArrayList<>();
        operProdIzq = new ArrayList<>();
        operProdDer = new ArrayList<>();
        operProdDoble = new ArrayList<>();
        funcProd = new ArrayList<>();
        identificadores = new HashMap<>();
        
        Functions.setAutocompleterJTextComponent( new String[]{}, jTextPaneSource, () -> {
            timerKeyReleased.restart();
        } );
    }
    
    public static void unused( Object evt ) {}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        jButtonOpen = new javax.swing.JButton();
        jButtonNew = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonSaveAs = new javax.swing.JButton();
        jButtonCompile = new javax.swing.JButton();
        jButtonExecute = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneSource = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaOutput = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableTokens = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuNewFile = new javax.swing.JMenuItem();
        jMenuOpenFile = new javax.swing.JMenuItem();
        jMenuSave = new javax.swing.JMenuItem();
        jMenuSaveAs = new javax.swing.JMenuItem();
        jMenuExit = new javax.swing.JMenuItem();
        jMenuRun = new javax.swing.JMenu();
        jMenuRunProject = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        rootPanel.setBackground(new java.awt.Color(51, 51, 51));
        rootPanel.setName("COPILADOR_19130920 AEGL"); // NOI18N

        buttonsFilePanel.setBackground(new java.awt.Color(102, 102, 102));

        jButtonOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/comp/icons/open.png"))); // NOI18N
        jButtonOpen.setToolTipText("Open File...");
        jButtonOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenActionPerformed(evt);
            }
        });

        jButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/comp/icons/new.png"))); // NOI18N
        jButtonNew.setToolTipText("New File...");
        jButtonNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/comp/icons/save.png"))); // NOI18N
        jButtonSave.setToolTipText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonSaveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/comp/icons/save_as.png"))); // NOI18N
        jButtonSaveAs.setToolTipText("Save as...");
        jButtonSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAsActionPerformed(evt);
            }
        });

        jButtonCompile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/comp/icons/compile.png"))); // NOI18N
        jButtonCompile.setToolTipText("Compile");
        jButtonCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompileActionPerformed(evt);
            }
        });

        jButtonExecute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/comp/icons/execute.png"))); // NOI18N
        jButtonExecute.setToolTipText("Execute");
        jButtonExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecuteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonOpen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jButtonNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSaveAs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCompile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExecute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonNew)
                .addGap(18, 18, 18)
                .addComponent(jButtonOpen)
                .addGap(18, 18, 18)
                .addComponent(jButtonSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSaveAs)
                .addGap(26, 26, 26)
                .addComponent(jButtonCompile)
                .addGap(18, 18, 18)
                .addComponent(jButtonExecute)
                .addContainerGap())
        );

        jTextPaneSource.setBackground(new java.awt.Color(32, 32, 32));
        jTextPaneSource.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Source", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jTextPaneSource.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        jScrollPane1.setViewportView(jTextPaneSource);

        jTextAreaOutput.setEditable(false);
        jTextAreaOutput.setBackground(new java.awt.Color(61, 61, 60));
        jTextAreaOutput.setColumns(20);
        jTextAreaOutput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextAreaOutput.setForeground(new java.awt.Color(171, 178, 191));
        jTextAreaOutput.setRows(5);
        jTextAreaOutput.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jScrollPane2.setViewportView(jTextAreaOutput);

        jTableTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente Léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableTokens);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addComponent(buttonsFilePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(rootPanel);

        jMenuFile.setText("File");

        jMenuNewFile.setText("New File...");
        jMenuNewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNewFileActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuNewFile);

        jMenuOpenFile.setText("Open File...");
        jMenuOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOpenFileActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuOpenFile);

        jMenuSave.setText("Save");
        jMenuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSaveActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuSave);

        jMenuSaveAs.setText("Save as...");
        jMenuSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSaveAsActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuSaveAs);

        jMenuExit.setText("Exit");
        jMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuExit);

        jMenuBar1.add(jMenuFile);

        jMenuRun.setText("Run");

        jMenuRunProject.setText("Run Project");
        jMenuRunProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuRunProjectActionPerformed(evt);
            }
        });
        jMenuRun.add(jMenuRunProject);

        jMenuBar1.add(jMenuRun);

        jMenuHelp.setText("Help");

        jMenuAbout.setText("About");
        jMenuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuAbout);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        directorio.New();
        clearFields();
        unused( evt );
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenActionPerformed
        if( directorio.Open() ) 
        {
            colorAnalysis();
            clearFields();
        }
        
        unused( evt );
    }//GEN-LAST:event_jButtonOpenActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        if( directorio.Save() ) 
        {
            clearFields();
        }
        
        unused( evt );
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAsActionPerformed
        if( directorio.SaveAs() ) 
        {
            clearFields();
        }
        
        unused( evt );
    }//GEN-LAST:event_jButtonSaveAsActionPerformed

    private void jButtonCompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompileActionPerformed
        if( getTitle().contains( "*" ) || getTitle().equals( title ) ) 
        {
            if( directorio.Save() ) 
            {
                compile();
            }
        } 
        else 
        {
            compile();
        }
        
        unused( evt );
    }//GEN-LAST:event_jButtonCompileActionPerformed

    private void jButtonExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecuteActionPerformed
        jButtonCompile.doClick();
        
            if( codeHasBeenCompiled ) 
            {
                if( !errors.isEmpty() ) 
                {
                    JOptionPane.showMessageDialog( null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                                                   "Error en la compilación", JOptionPane.ERROR_MESSAGE );
                } 
                else 
                {
                    try 
                    {
                        String sourceCode = jTextPaneSource.getText();
                        float a = extractValue( sourceCode, "a" );
                        float b = extractValue( sourceCode, "b" );
                        float c = extractValue( sourceCode, "c" );

                        float temp1 = -b;
                        float temp2 = ( float )( Math.pow( b, 2 ) - 4 * a * c );
                        float temp3 = ( float )Math.sqrt( temp2 );
                        float temp4 = 2 * a;
                        float root1 = temp1 + temp3;
                        float root2 = temp1 - temp3;

                        StringBuilder result = new StringBuilder();
                        result.append( "temp1 = " ).append( temp1 ).append( "\n" );
                        result.append( "temp2 = " ).append( temp2 ).append( "\n" );
                        result.append( "temp3 = " ).append( temp3 ).append( "\n" );
                        result.append( "temp4 = " ).append( temp4 ).append( "\n" );
                        result.append( "root1 = " ).append( root1 ).append( "\n" );
                        result.append( "root2 = " ).append( root2 ).append( "\n" );

                        if( temp2 < 0 ) 
                        {
                            String imaginario = "Las Raices son imaginarias";
                            result.append( imaginario ).append( "\n" );
                        } 
                        else 
                        {
                            float x1 = root1 / temp4;
                            float x2 = root2 / temp4;
                            result.append( "x1 = " ).append(x1 ).append( "\n" );
                            result.append( "x2 = " ).append(x2 ).append( "\n" );
                        }
                        
                        jTextAreaOutput.setFont( new Font( "Monospaced", 0, 14 ) );
                        jTextAreaOutput.setText(result.toString());
                    } 
                    catch( Exception e ) 
                    {
                        printConsole();
                    }
                }
            }
            
        unused( evt );
    }//GEN-LAST:event_jButtonExecuteActionPerformed

    private void jMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExitActionPerformed
        dispose();
        unused( evt );
    }//GEN-LAST:event_jMenuExitActionPerformed

    private void jMenuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAboutActionPerformed
        String about = "<html><div style='text-align: center;'>" +
                            "<h2 style='color: rgb(0, 51, 102);'>Compiler COMP IDE 2.0</h2>" +
                            "<p>Edited by:</p>" +
                            "<p>Diego Muñoz Rede (21130893)</p>" +
                            "<p>Leonardo Zavala Gonzalez (21130874)</p>" +
                            "<p>Natanael Heriberto Esparza Pineda (211308900)</p>" +
                            "<p>Sandra Hermione Ortiz Sandoval (21130912)</p>" +
                            "<p>Carlos Alberto Medina de la Torre (21130910)</p>" +
                          "</div></html>";
        
        JOptionPane.showMessageDialog( null, about, "About", JOptionPane.INFORMATION_MESSAGE );
        
        unused( evt );
    }//GEN-LAST:event_jMenuAboutActionPerformed

    private void jMenuRunProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuRunProjectActionPerformed
        if( getTitle().contains( "*" ) || getTitle().equals( title ) ) 
        {
            if( directorio.Save() ) 
            {
                compile();
            }
        } 
        else 
        {
            compile();
        }
        
        unused( evt );
    }//GEN-LAST:event_jMenuRunProjectActionPerformed

    private void jMenuNewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNewFileActionPerformed
        directorio.New();
        clearFields();
        unused( evt );
    }//GEN-LAST:event_jMenuNewFileActionPerformed

    private void jMenuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSaveActionPerformed
        if( directorio.Save() ) 
        {
            clearFields();
        }
        
        unused( evt );
    }//GEN-LAST:event_jMenuSaveActionPerformed

    private void jMenuSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSaveAsActionPerformed
        if( directorio.SaveAs() ) 
        {
            clearFields();
        }
        
        unused( evt );
    }//GEN-LAST:event_jMenuSaveAsActionPerformed

    private void jMenuOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuOpenFileActionPerformed
        if( directorio.Open() ) 
        {
            colorAnalysis();
            clearFields();
        }
        
        unused( evt );
    }//GEN-LAST:event_jMenuOpenFileActionPerformed
    private float extractValue( String sourceCode, String variableName ) throws Exception 
    {
        String pattern = variableName + "\\s*=\\s*([+-]?\\d*\\.?\\d+)";
        Pattern r = Pattern.compile( pattern );
        Matcher m = r.matcher( sourceCode );
        if( m.find() ) 
        {
            return Float.parseFloat( m.group( 1 ) );
        } 
        else 
        {
            throw new Exception( "Variable " + variableName + " no encontrada en el código fuente." );
        }
    }
    
    public void archivoT( String ruta, String Objeto ) 
    {
        try 
        {
            String contenido = Objeto;
            File file = new File( ruta );
            // Si el archivo no existe es creado
            if( !file.exists() ) 
            {
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter( file );
            try( BufferedWriter bw = new BufferedWriter( fw ) ) 
            {
                bw.write( contenido );
            }
        } 
        catch( IOException e ) {}
    }

    public void abrirArchivo( String archivo )
    {
        try 
        {
            File objetofile = new File( archivo );
            Desktop.getDesktop().open( objetofile );
        }
        catch( IOException ex ) 
        {
            System.out.println( ex );
        }   
    }
    
    private void compile() 
    {
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        semanticAnalysis();
        printConsole();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() 
    {
        // Extraer tokens
        Lexer lexer;
        
        try 
        {
            File codigo = new File( "code.encrypter" );
            FileOutputStream output = new FileOutputStream( codigo );
            byte[] bytesText = jTextPaneSource.getText().getBytes();
            output.write( bytesText );
            BufferedReader entrada = new BufferedReader( new InputStreamReader( new FileInputStream( codigo ), "UTF8" ) );
            lexer = new Lexer( entrada );
            while( true ) 
            {
                Token token = lexer.yylex();
                if( token == null ) 
                {
                    break;
                }
                
                tokens.add( token );
            }
        } 
        catch( FileNotFoundException ex ) 
        {
            System.out.println( "El archivo no pudo ser encontrado... " + ex.getMessage() );
        }
        catch( IOException ex ) 
        {
            System.out.println( "Error al escribir en el archivo... " + ex.getMessage() );
        }
    }

    private void syntacticAnalysis() 
    {
        Grammar gramatica = new Grammar( tokens, errors );

        /*ELIMINACION DE ERRORES*/
        gramatica.delete( new String[]{"ERROR"}, 1 );
        /* Mostrar gramáticas */        
        gramatica.group( "REAL", "NUMERO PUNTO NUMERO" );
         
                
        //-------------OPERACION---------------
        //FORMAS DE CREAR UNA FUNCION CORRECTAMENTE   
        gramatica.group( "OPERACION", "REAL (SUMA|RESTA|MULTIPLICACION|DIVISION) REAL" );
        gramatica.group( "OPERACION", "REAL (SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO" );
        gramatica.group( "OPERACION", "REAL (SUMA|RESTA|MULTIPLICACION|DIVISION) ID" );
        gramatica.group( "OPERACION", "NUMERO (SUMA|RESTA|MULTIPLICACION|DIVISION) REAL" );
        gramatica.group( "OPERACION", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO",operProdIzq );
        gramatica.group( "OPERACION", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION) REAL" );
        gramatica.group( "OPERACION", "NUMERO (SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO" );
        gramatica.group( "OPERACION", "NUMERO (SUMA|RESTA|MULTIPLICACION|DIVISION) ID",operProdDer );
        gramatica.group( "OPERACION", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO",operProdIzq );
        gramatica.group( "OPERACION", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION) ID",operProdDoble );
        gramatica.group( "OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) REAL" );
        gramatica.group( "OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO" );
        gramatica.group( "OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) ID",operProdDer );
        gramatica.group( "OPERACION", "REAL (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION" );
        gramatica.group( "OPERACION", "NUMERO (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION" );
        gramatica.group( "OPERACION", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION",operProdIzq );
        
        String operacion = "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION";
        for( int i = 0; i < 100; i++ ) 
        {
            gramatica.group( "OPERACION", operacion );
        }
        
        //ERRORES operacion
        gramatica.group( "OPERACION_ER", "NUMERO (SUMA|RESTA|MULTIPLICACION|DIVISION)", 2, "ERROR_SINTACTICO: se necesita un minimo de 2 valores para ralizar la operacion [#, %]" );
        gramatica.group( "OPERACION_ER", "(SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO", 2, "ERROR SINTACTICO: se necesita un minimo de 2 valores para ralizar la operacion [#, %]" );
        gramatica.group( "OPERACION_ER", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION)", 2, "ERROR SINTACTICO: se necesita un minimo de 2 valores para ralizar la operacion [#, %]" );
        gramatica.group( "OPERACION_ER", "(SUMA|RESTA|MULTIPLICACION|DIVISION) ID", 2, "ERROR SINTACTICO: se necesita un minimo de 2 valores para ralizar la operacion [#, %]" );
       
        //FORMA CORRECTA DE DECLARAR UNA VARIABLE------------------------------------------------------------
        gramatica.group( "DECL_FLOAT", "FLOAT ID PUNTOCOMA", identProd );
        gramatica.group( "DECL_FLOAT", "FLOAT ID ASIGNACION ID PUNTOCOMA", identProd );
        gramatica.group( "DECL_FLOAT", "FLOAT ID ASIGNACION REAL PUNTOCOMA", identProd );
        gramatica.group( "DECL_FLOAT", "FLOAT ID ASIGNACION OPERACION PUNTOCOMA", identProd );
        
        gramatica.group( "DECL_INT", "INT ID PUNTOCOMA", identProd );
        gramatica.group( "DECL_INT", "INT ID ASIGNACION ID PUNTOCOMA", identProd );
        gramatica.group( "DECL_INT", "INT ID ASIGNACION NUMERO PUNTOCOMA", identProd );
        gramatica.group( "DECL_INT", "INT ID ASIGNACION OPERACION PUNTOCOMA", identProd );
        
        gramatica.group( "DECL_BOOL", "BOOLEAN ID PUNTOCOMA", identProd );
        gramatica.group( "DECL_BOOL", "BOOLEAN ID ASIGNACION ID PUNTOCOMA", identProd );
        gramatica.group( "DECL_BOOL", "BOOLEAN ID ASIGNACION (TRUE|FALSE) PUNTOCOMA", identProd );
                
        gramatica.group( "DECL_STRING", "STRING ID PUNTOCOMA", identProd );
        gramatica.group( "DECL_STRING", "STRING ID ASIGNACION ID PUNTOCOMA", identProd );
        gramatica.group( "DECL_STRING", "STRING ID ASIGNACION CADENA PUNTOCOMA", identProd );
        
        //ERRORES SINTACTICOS---------------------------------------------------------------------------
        //POSIBLES ERRORES AL DECLARAR UNA VARIABLE INT O FLOAT  
        gramatica.group( "DECL_INT", "INT ID ASIGNACION PUNTOCOMA", 2, "ERROR_SINTACTICO: FALTA ASIGNAR UN VALOR A LA VARIABLE [#, %]" );
        gramatica.group( "DECL_INT", "INT ID NUMERO PUNTOCOMA", 2, "ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_INT", "INT ID ID PUNTOCOMA", 2, "ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_INT", "INT ID NUMERO", 2, "ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_INT", "INT ID ID", 2, "ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_INT", "INT ID OPERACION PUNTOCOMA", 2, "ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_INT", "INT ID OPERACION", 2, "ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_INT", "INT ID ASIGNACION ID", 2, "ERROR_SINTACTICO: PUNTOCOMA(;) NO AGREGADO EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_INT", "INT ID ASIGNACION REAL", 2, "ERROR_SINTACTICO: VALOR NO ENTERO [#, %]" );

        gramatica.group("DECL_INT", "INT ID ASIGNACION NUMERO", 2, "ERROR_SINTACTICO: PUNTOCOMA(;) NO AGREGADO EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_INT", "INT ID ASIGNACION OPERACION", 2, "ERROR_SINTACTICO: PUNTOCOMA(;) NO AGREGADO EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_INT", "INT  ASIGNACION NUMERO PUNTOCOMA", 2, "ERROR_SINTACTICO: ID NO AGREGADO EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_INT", "INT  ASIGNACION ID PUNTOCOMA", 2, "ERROR_SINTACTICO: ID NO AGREGADO EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_INT", "INT  ASIGNACION OPERACION PUNTOCOMA", 2, "ERROR_SINTACTICO: ID NO AGREGADO EN LA DECLARACION [#, %]" );
        
        //POSIBLES ERRORES AL DECLARAR UN FLOAT
        gramatica.group( "DECL_FLOAT", "FLOAT ID ASIGNACION PUNTOCOMA", 2, "ERROR_SINTACTICO: FALTA ASIGNAR UN VALOR A LA VARIABLE [#, %]" );
        gramatica.group( "DECL_FLOAT", "FLOAT ID REAL PUNTOCOMA", 2, "ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]" );        
        gramatica.group( "DECL_FLOAT", "FLOAT ID REAL", 2, "ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_FLOAT", "FLOAT ID ASIGNACION REAL", 2, "ERROR_SINTACTICO: PUNTOCOMA(;) NO AGREGADO EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_FLOAT", "FLOAT ASIGNACION REAL PUNTOCOMA", 2, "ERROR_SINTACTICO: ID NO AGREGADO EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_FLOAT", "FLOAT ID ASIGNACION NUMERO PUNTOCOMA", 2, "Error sintáctico: Valor float sin punto decimal [#, %]" );

        //POSIBLES ERRORES AL DECLARAR UN FLOAT
        gramatica.group( "DECL_STRING", "STRING ID ASIGNACION PUNTOCOMA", 2, "ERROR_SINTACTICO: FALTA ASIGNAR UN VALOR A LA VARIABLE [#, %]" );
        gramatica.group( "DECL_STRING", "STRING ID CADENA PUNTOCOMA", 2, "ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]" );        
        gramatica.group( "DECL_STRING", "STRING ID CADENA", 2, "ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_STRING", "STRING ID ASIGNACION CADENA", 2, "ERROR_SINTACTICO: PUNTOCOMA(;) NO AGREGADO EN LA DECLARACION [#, %]" );
        gramatica.group( "DECL_STRING", "STRING ASIGNACION CADENA PUNTOCOMA", 2, "ERROR_SINTACTICO: ID NO AGREGADO EN LA DECLARACION [#, %]" );
        
        //ERRORES SEMANTICOS DE VARIABLES -------------------------------------------------------------
        gramatica.group( "RESERV_INDEB", "(STRING|INT|FLOAT|BOOLEAN) (IMPORT|DEF|CLASS|IF|ELSE|FOR|IN|WHILE|RETURN)", 2,  "ERROR SEMANTICO \\{}: USO INDEBIDO DE PALABRAS RESERVADAS [#,%]" );
       
        gramatica.group( "ERROR_OP_STRING", "(SUMA|RESTA|MULTIPLICACION|DIVISION) CADENA", 2, "ERROR SEMANTICO \\{}: OPERACION NO PERMITIDA PARA CADENA [#,%]" );
        gramatica.group( "ERROR_OP_STRING", "CADENA (SUMA|RESTA|MULTIPLICACION|DIVISION)", 2, "ERROR SEMANTICO \\{}: OPERACION NO PERMITIDA PARA CADENA [#,%]" );
        gramatica.group( "ERROR_OP_BOOLEAN", "(SUMA|RESTA|MULTIPLICACION|DIVISION) (TRUE|FALSE)", 2, "ERROR SEMANTICO \\{}: OPERACION NO PERMITIDA PARA BOOLEANO [#,%]" );
        gramatica.group( "ERROR_OP_BOOLEAN", "(TRUE|FALSE) (SUMA|RESTA|MULTIPLICACION|DIVISION)", 2, "ERROR SEMANTICO \\{}: OPERACION NO PERMITIDA PARA BOOLEANO [#,%]" );

        gramatica.group( "DECL_INT", "(INT ID ASIGNACION REAL PUNTOCOMA)", 2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES ENTERO [#,%]" );
        gramatica.group( "DECL_INT", "(INT ID ASIGNACION CADENA)", 2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES ENTERO [#,%]" );
        gramatica.group( "DECL_INT", "(INT ID ASIGNACION (TRUE|FALSE))", 2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES ENTERO [#,%]" );
        gramatica.group( "DECL_INT", "INT", 2, "ERROR" );
        
        gramatica.group( "DECL_FLOAT", "(FLOAT ID ASIGNACION CADENA)", 2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES DECIMAL [#,%]" );
        gramatica.group( "DECL_FLOAT", "(FLOAT ID ASIGNACION (TRUE|FALSE))", 2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES DECIMAL [#,%]" );
        gramatica.group( "DECL_FLOAT", "(FLOAT ID ASIGNACION NUMERO PUNTOCOMA)", 2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES DECIMAL [#,%]" );
        gramatica.group( "ERROR_FLOAT", "FLOAT", 2, "ERROR" );
        
        gramatica.group( "DECL_STRING", "(STRING ID ASIGNACION NUMERO)", 2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES CADENA [#,%]" );
        gramatica.group( "DECL_STRING", "(STRING ID ASIGNACION (TRUE|FALSE))", 2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES CADENA [#,%]" );
        gramatica.group( "DECL_STRING", "STRING", 2, "ERROR");
        
        gramatica.group( "ERROR_ASIG_BOOL", "(BOOLEAN ID ASIGNACION NUMERO)", 2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES BOOLEANO [#,%]" );
        gramatica.group( "ERROR_ASIG_BOOL", "(BOOLEAN ID ASIGNACION (TRUE|FALSE))", 2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES BOOLEANO [#,%]" );
        gramatica.group( "ERROR_ASIG_BOOL", "(BOOLEAN ID ASIGNACION CADENA)", 2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES BOOLEANO [#,%]" );
        
        //ASIGNACION DE UN ID
        gramatica.group( "PROD_ASIG", "ID ASIGNACION (CADENA|REAL|NUMERO|TRUE|FALSE) PUNTOCOMA",asigProd );
        gramatica.group( "PROD_ASIG", "ID ASIGNACION OPERACION PUNTOCOMA",asigProd );
        gramatica.group( "PROD_ASIG_ID", "ID ASIGNACION ID PUNTOCOMA",asigProdConID );
        
        //-------------CONDICION--------------------------
        //FORMAS CORRECTAS DE CREAR UNA CONDICION
        gramatica.group( "CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) NUMERO" );
        gramatica.group( "CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) OPERACION" );
        gramatica.group( "CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) OPERACION" );
        gramatica.group( "CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) NUMERO" );
        gramatica.group( "CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) REAL" );
        gramatica.group( "CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) REAL" );
        gramatica.group( "CONDICION", "(TRUE|FALSE) (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) (TRUE|FALSE)" );
        gramatica.group( "CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ID",compaProdDer );
        gramatica.group( "CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ID",compaProdDer );
        gramatica.group( "CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) NUMERO",compaProdIzq );
        gramatica.group( "CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) OPERACION",compaProdIzq );
        gramatica.group( "CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) REAL",compaProdIzq );
        gramatica.group( "CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ID",compaProdDoble );
        gramatica.group( "CONDICION", "(TRUE|FALSE) (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ID",compaProdDer );
        gramatica.group( "CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) (TRUE|FALSE)",compaProdIzq );
        gramatica.group( "CONDICION", "CONDICION (AND|OR) CONDICION");
        
        //ERRORES SEMANTICOS
        gramatica.group( "CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) CADENA", 2, "ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]" );
        gramatica.group( "CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) CADENA", 2, "ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]" );
        gramatica.group( "CONDICION", "CADENA (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) NUMERO", 2, "ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]" );
        gramatica.group( "CONDICION", "CADENA (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) REAL", 2, "ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]" );
        gramatica.group( "CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) (TRUE|FALSE)", 2, "ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]" );
        gramatica.group( "CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) (TRUE|FALSE)", 2, "ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]" );
        
        //----------------WHILE Y IF-----------------------
        //FORMAS CORRECTAS DE DECLARAR UN IF
        gramatica.group( "INSTR_IF", "IF PARENTESISABIERTO CONDICION PARENTESISCERRADO LLAVEABIERTO", true, ifProd );

        //FORMAS CORRECTAS DE DECLARAR UN WHILE
        gramatica.group( "INSTR_WHILE", "WHILE PARENTESISABIERTO CONDICION PARENTESISCERRADO LLAVEABIERTO", whileProd );

        //POSIBLES ERRORES AL DECLARAR UN IF
        gramatica.group( "INSTR_IF", "IF PARENTESISABIERTO PARENTESISCERRADO LLAVEABIERTO", true, 4, "ERROR_SINTACTICO: FALTA LA CONDICION [#, %]" );
        gramatica.group( "INSTR_IF", "IF CONDICION PARENTESISCERRADO LLAVEABIERTO", true, 4, "ERROR_SINTACTICO: FALTA EL PARENTESIS ABIERTO EN LA CONDICION [#, %]" );
        gramatica.group( "INSTR_IF", "IF PARENTESISABIERTO CONDICION PARENTESISCERRADO", true, 4, "ERROR_SINTACTICO: FALTA DE LLAVE DE APERTURA [#, %]" );
        gramatica.finalLineColumn();
        gramatica.group( "INSTR_IF", "IF PARENTESISABIERTO CONDICION", true, 4, "ERROR_SINTACTICO: ERROR EN LA CONDICION O FALTA DEL PARENTESIS [#, %]" );
        gramatica.initialLineColumn();
        gramatica.group( "INSTR_IF", "IF", 2, "ERROR" );
        
        //POSIBLES ERRORES DE WHILE
        gramatica.group( "INSTR_WHILE", "WHILE PARENTESISABIERTO PARENTESISCERRADO", true, 4, "ERROR_SINTACTICO: FALTA LA CONDICION [#, %]" );
        gramatica.group( "INSTR_WHILE", "WHILE CONDICION PARENTESISCERRADO", true, 4, "ERROR_SINTACTICO: FALTA EL PARENTESIS ABIERTO EN LA CONDICION [#, %]" );
        gramatica.group( "INSTR_WHILE", "WHILE PARENTESISABIERTO CONDICION PARENTESISCERRADO", true, 4, "ERROR_SINTACTICO: FALTA DE LLAVE DE APERTURA [#, %]" );        
        gramatica.finalLineColumn();
        gramatica.group( "INSTR_WHILE", "WHILE PARENTESISABIERTO CONDICION", true, 4, "ERROR_SINTACTICO: ERROR EN LA CONDICION O FALTA DEL PARENTESIS [#, %]" );
        gramatica.initialLineColumn();
        gramatica.group( "INSTR_WHILE", "WHILE", 2, "ERROR WHILE" );
        //POSIBLES ERRORES EN LAS CODICIONES
        gramatica.group( "CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ", 2, "ERROR_SINTACTICO: ERROR EN LA CONDICION [#, %]" );
        gramatica.group( "CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ", 2, "ERROR_SINTACTICO: ERROR EN LA CONDICION [#, %]" );
        gramatica.group( "CONDICION", " (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) NUMERO ", 2, "ERROR_SINTACTICO: ERROR EN LA CONDICION [#, %]" );
        gramatica.group( "CONDICION", " (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ID ", 2, "ERROR_SINTACTICO: ERROR EN LA CONDICION [#, %]" );       
        gramatica.group( "CONDICION", "CONDICION (AND|OR)", 2, "ERROR_SINTACTICO: ERROR EN LA CONDICION [#, %]" );
        //------------------------------------------------------------------------

        //------------------------------------------------------------
        //INPUT-------------------------------------------------------
        //FORMA CORRECTA 
        gramatica.group( "INSTR_INPUT", "INPUT PARENTESISABIERTO CADENA PARENTESISCERRADO PUNTOCOMA ",funcProd );
        gramatica.group( "INSTR_INPUT", "INPUT PARENTESISABIERTO ID PARENTESISCERRADO PUNTOCOMA ",funcProd );
        
        gramatica.group( "INSTR_OUTPUT", "OUTPUT PARENTESISABIERTO CADENA PARENTESISCERRADO PUNTOCOMA ",funcProd );
        gramatica.group( "INSTR_OUTPUT", "OUTPUT PARENTESISABIERTO ID PARENTESISCERRADO PUNTOCOMA ",funcProd );
        
        //ERRORES SINTACTICOS 
        gramatica.group( "INSTR_INPUT", "INPUT PARENTESISABIERTO CADENA PARENTESISCERRADO", 2, "ERROR_SINTACTICO \\{}: FALTA DEL TOKEN (;) [#,%]" );
        gramatica.group( "INSTR_INPUT", "INPUT CADENA PARENTESISCERRADO PUNTOCOMA", 2, "ERROR_SINTACTICO \\{}: FALTA DEL PARENTESIS ABIERTO [#,%]" );
        
        //ERROR SEMANTICO
        gramatica.group( "INSTR_INPUT", "INPUT PARENTESISABIERTO (NUMERO|REAL) PARENTESISCERRADO PUNTOCOMA ", 2, "ERROR SEMANTICO \\{}: VALOR INVALIDO EN INPUT[#,%]" );
        gramatica.group( "INSTR_INPUT", "INPUT", 2, "ERROR INPUT" );    
        //ERRORES SINTACTICOS 
        gramatica.group( "INSTR_INPUT", "OUTPUT PARENTESISABIERTO CADENA PARENTESISCERRADO", 2, "ERROR_SINTACTICO \\{}: FALTA DEL TOKEN (;) [#,%]" );
        gramatica.group( "INSTR_INPUT", "OUTPUT CADENA PARENTESISCERRADO PUNTOCOMA", 2, "ERROR_SINTACTICO \\{}: FALTA DEL PARENTESIS ABIERTO [#,%]" );
        
        //ERROR SEMANTICO
        gramatica.group( "INSTR_INPUT", "OUTPUT PARENTESISABIERTO (NUMERO|REAL) PARENTESISCERRADO PUNTOCOMA ", 2, "ERROR SEMANTICO \\{}: VALOR INVALIDO EN INPUT[#,%]" );
        gramatica.group( "INSTR_INPUT", "OUTPUT", 2, "ERROR INPUT" ); 
        
        //------------------------------------------------------------
        
        //FORMAS DE CREAR UNA FUNCION CORRECTAMENTE
        gramatica.group( "PARAMETROS", "ID (COMA ID)+" );
        gramatica.group( "PARAMETRO", "ID" );        
        gramatica.group( "FUNCION", "DEF ID PARENTESISABIERTO (PARAMETRO | PARAMETROS)? PARENTESISCERRADO ", true );
        
        gramatica.group( "LLAMAR_FUNCION", "ID PARENTESISABIERTO (PARAMETRO | PARAMETROS)? PARENTESISCERRADO ", true );

         //posibles errores al declarar una funcion        
        gramatica.group( "FUNCION", "DEF ID (PARAMETRO | PARAMETROS)? PARENTESISCERRADO ", true, 3, "ERROR_SINTACTICO: FALTA PARENTESIS ABIERTO [#, %]" );
        gramatica.group( "FUNCION", "DEF ID PARENTESISABIERTO (PARAMETRO | PARAMETROS)? ", true, 3, "ERROR_SINTACTICO: FALTA PARENTESIS CERRADO O UN PARAMETRO ESTA MAL DECLARADO [#, %]" );
        gramatica.group( "FUNCION", "DEF PARENTESISABIERTO (PARAMETRO | PARAMETROS)? PARENTESISCERRADO", true, 3, "ERROR_SINTACTICO: FALTA NOMBRAR LA FUNCION [#, %]" );

        gramatica.show();
    }

    private void semanticAnalysis() 
    {
        HashMap< String, String > tiposDatos = new HashMap<>();
        tiposDatos.put( "NUMERO", "INT" );
        tiposDatos.put( "REAL", "FLOAT" );
        tiposDatos.put( "CADENA", "STRING" );
        tiposDatos.put( "TRUE", "BOOLEAN" );
        tiposDatos.put( "FALSE", "BOOLEAN" );
        int i = 0;
        for( Production id: identProd )
        {
            //System.out.println(id.lexemeRank(0,-1)); //int x = 4 ;
            //System.out.println(id.lexemeRank(1)); //x
            //System.out.println(id.lexicalCompRank(0,-1)); //INT ID ASIGNACION NUMERO PUNTOCOMA
            if( !identificadores.containsKey( id.lexemeRank( 1 ) ) )
            {
                identificadores.put( id.lexemeRank( 1 ), id.lexicalCompRank( 0 ) );
                i++;
            }
            else 
            {
                errors.add( new ErrorLSSL( 1, "Error semántico: Ya existe un identificador llamado " + id.lexemeRank( 1 ), id, true ) );
            }
        }
        
        System.out.println( Arrays.asList( identificadores ) ); // muestra identificadores
        for( Production id: asigProd )
        {
            if( !identificadores.containsKey( id.lexemeRank( 0 ) ) )
            {
                errors.add( new ErrorLSSL( 1, "Error semántico: Variable \"" + id.lexemeRank( 0 ) + "\" no declarada. [#, %]", id, true ) );
            }
            else
            {
                if( !identificadores.get( id.lexemeRank( 0 ) ).equals( tiposDatos.get( id.lexicalCompRank( 2 ) ) ) )
                {
                    errors.add( new ErrorLSSL( 1,"Error semántico : Variable \"" + id.lexemeRank( 0 ) + "\" es de tipo " + identificadores.get( id.lexemeRank( 0 ) ) + " [#, %]", id, true ) );
                }
            } 
        }
        
        for( Production id: asigProdConID )
        {
            if( !identificadores.containsKey( id.lexemeRank( 0 ) ) || !identificadores.containsKey( id.lexemeRank( 2 ) ) )
            {
                errors.add( new ErrorLSSL( 1, "Error semántico: Variable no declarada. [#, %]", id, true ) );
            }
            else
            {
                if( !identificadores.get( id.lexemeRank( 0 ) ).equals( identificadores.get( id.lexemeRank( 2 ) ) ) )
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 0 ) + "\" es de tipo " + identificadores.get( id.lexemeRank( 0 ) ) +  " [#, %]", id, true ) );
                }
            }
        }
        
        //comparacion cuando ID está en la izquierda
        for( Production id: compaProdIzq )
        {
            if( !identificadores.containsKey( id.lexemeRank( 0 ) ) )
            {
                errors.add( new ErrorLSSL( 1,"Error semántico: Variable " + id.lexemeRank( 0 ) + " no declarada. [#, %]", id, true ) );
            }
            else
            {
                if( identificadores.get( id.lexemeRank( 0 ) ).matches( "STRING" ) ) 
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 0 ) + "\" es de tipo STRING, imposible comparar [#, %]", id, true ) );
                }
                if( identificadores.get( id.lexemeRank( 0 ) ).matches( "BOOLEAN" ) && !id.lexicalCompRank( 1 ).matches( "IGUAL|DIFERENTE" ) )
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 0 ) + "\" es de tipo BOOLEAN, sólo posible comparar con operadores IGUAL y DIFERENTE [#, %]", id, true ) );
                }
                if( identificadores.get( id.lexemeRank( 0 ) ).matches( "BOOLEAN" ) && !id.lexicalCompRank( 2 ).matches( "TRUE|FALSE" ) )
                {
                    errors.add(new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 0 ) + "\" es de tipo BOOLEAN, sólo posible comparar con valores booleanos [#, %]", id,true ) );
                }
                if( identificadores.get( id.lexemeRank( 0 ) ).matches( "INT|FLOAT" ) )
                {
                    if( !id.lexicalCompRank( 2 ).matches( "NUMERO|REAL|ID" ) )
                    {
                        errors.add( new ErrorLSSL( 1, "Error semántico : Valor numérico de variable \"" + id.lexemeRank( 0 ) + "\" no se puede comparar con valor no numérico [#, %]", id, true ) );
                    }
                }
            }
        }
        
        for( Production id: compaProdDer )
        {
            if( !identificadores.containsKey( id.lexemeRank( 2 ) ) )
            {
                errors.add( new ErrorLSSL( 1, "Error semántico: Variable " + id.lexemeRank( 2 ) + " no declarada. [#, %]", id, true ) );
            }
            else
            {
                if( identificadores.get( id.lexemeRank( 2 ) ).matches( "STRING" ) )
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 2 ) + "\" es de tipo STRING, imposible comparar [#, %]", id, true ) );
                }
                if( identificadores.get( id.lexemeRank( 2 ) ).matches( "BOOLEAN" ) && !id.lexicalCompRank( 1 ).matches( "IGUAL|DIFERENTE" ) ) 
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 2 ) + "\" es de tipo BOOLEAN, sólo posible comparar con operadores IGUAL y DIFERENTE [#, %]", id, true ) );
                }
                if( identificadores.get( id.lexemeRank( 2 ) ).matches( "BOOLEAN" ) && !id.lexicalCompRank( 0 ).matches( "TRUE|FALSE" ) ) 
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 2 ) + "\" es de tipo BOOLEAN, sólo posible comparar con valores booleanos [#, %]", id , true ) );
                }
                if( identificadores.get( id.lexemeRank( 2 ) ).matches( "INT|FLOAT" ) )
                {
                    if( !id.lexicalCompRank( 0 ).matches( "NUMERO|REAL" ) ) 
                    {
                        errors.add( new ErrorLSSL( 1, "Error semántico : Valor numérico de variable \"" + id.lexemeRank( 2 ) + "\" no se puede comparar con valor no numérico [#, %]", id, true ) );
                    }
                }
            }
        }
        
        for( Production id: compaProdDoble )
        {
            if( !identificadores.containsKey( id.lexemeRank( 0 ) ) || !identificadores.containsKey( id.lexemeRank( 2 ) ) )
            {
                errors.add( new ErrorLSSL( 1,"Error semántico: Variable " + id.lexemeRank( 0 ) + " no declarada. [#, %]", id, true ) );
            }
            else
            {
                if( identificadores.get( id.lexemeRank( 0 ) ).matches( "STRING" ) || identificadores.get( id.lexemeRank( 2 ) ).matches( "STRING" ) ) 
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 0 ) + "\" es de tipo STRING, imposible comparar [#, %]", id, true ) );
                }
                if( identificadores.get( id.lexemeRank( 0 ) ).matches( "BOOLEAN" ) && !id.lexicalCompRank( 1 ).matches( "IGUAL|DIFERENTE" ) )
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 0 ) + "\" es de tipo BOOLEAN, sólo posible comparar con operadores IGUAL y DIFERENTE [#, %]", id, true ) );
                }
                if( identificadores.get( id.lexemeRank( 2 ) ).matches( "BOOLEAN" ) && !id.lexicalCompRank( 1 ).matches( "IGUAL|DIFERENTE" ) ) 
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 2 ) + "\" es de tipo BOOLEAN, sólo posible comparar con operadores IGUAL y DIFERENTE [#, %]", id, true ) );
                }
                if( identificadores.get( id.lexemeRank( 0 ) ).matches( "BOOLEAN" ) && !identificadores.get( id.lexemeRank( 2 ) ).matches( "BOOLEAN" ) )
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 0 ) + "\" es de tipo BOOLEAN, sólo posible comparar con valores booleanos [#, %]", id, true ) );
                }
                if( identificadores.get( id.lexemeRank( 2 ) ).matches( "BOOLEAN" ) && !identificadores.get( id.lexemeRank( 0 ) ).matches( "BOOLEAN" ) )
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 2 ) + "\" es de tipo BOOLEAN, sólo posible comparar con valores booleanos [#, %]", id, true ) );
                }
                if( identificadores.get( id.lexemeRank( 0 ) ).matches( "INT|FLOAT" ) )
                {
                    if( !identificadores.get( id.lexemeRank( 2 ) ).matches( "INT|FLOAT" ) )
                    {
                        errors.add( new ErrorLSSL( 1, "Error semántico : Valor numérico de variable \"" + id.lexemeRank( 0 ) + "\" no se puede comparar con valor no numérico [#, %]", id, true ) );
                    }
                }
                if( identificadores.get( id.lexemeRank( 2 ) ).matches( "INT|FLOAT" ) )
                {
                    if( !identificadores.get( id.lexemeRank( 0 ) ).matches( "INT|FLOAT" ) ) 
                    {
                        errors.add( new ErrorLSSL( 1, "Error semántico : Valor numérico de variable \"" + id.lexemeRank( 0 ) + "\" no se puede comparar con valor no numérico [#, %]", id, true ) );
                    }
                }
            }
        }
        
        for( Production id: operProdIzq )
        {
            if( !identificadores.containsKey( id.lexemeRank( 0 ) ) )
            {
                errors.add( new ErrorLSSL( 1, "Error semántico: Variable " + id.lexemeRank( 0 ) + " no declarada. [#, %]", id, true ) );
            }
            else
            {
                if( identificadores.get( id.lexemeRank( 0 ) ).matches( "STRING|BOOLEAN" ) ) 
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 0 ) + "\" es de tipo " + identificadores.get( id.lexemeRank( 0 ) ) + ", imposible hacer operaciones aritméticas [#, %]", id, true ) );
                }
      
            }
            
            if( identificadores.get( id.lexemeRank( 0 ) ).matches( "INT" ) && id.lexicalCompRank( 1 ).matches( "DIVISION" ) )
            {
                errors.add( new ErrorLSSL( 1, "Error semántico : División en valor entero [#, %]", id, true ) );
            }
        }
        
        for( Production id: operProdDer )
        {
            if( !identificadores.containsKey( id.lexemeRank( 2 ) ) )
            {
                errors.add( new ErrorLSSL( 1, "Error semántico: Variable " + id.lexemeRank( 2 ) + " no declarada. [#, %]", id, true ) );
            }
            else
            {
                if( identificadores.get( id.lexemeRank( 2 ) ).matches( "STRING|BOOLEAN" ) )
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 2 ) + "\" es de tipo " + identificadores.get( id.lexemeRank( 0 ) ) + ", imposible hacer operaciones aritméticas [#, %]", id, true ) );
                }
            }  
        }
        
        for( Production id: operProdDoble )
        {
            if( !identificadores.containsKey( id.lexemeRank( 0 ) ) || !identificadores.containsKey( id.lexemeRank( 2 ) ) )
            {
                errors.add( new ErrorLSSL( 1, "Error semántico: Variable " + id.lexemeRank( 0 ) + " no declarada. [#, %]", id, true ) );
            }
            else
            {
                if( identificadores.get( id.lexemeRank( 0 ) ).matches( "STRING|BOOLEAN" ) || identificadores.get( id.lexemeRank( 2 ) ).matches( "STRING|BOOLEAN" ) )
                {
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 0 ) + "\" es de tipo " + identificadores.get( id.lexemeRank( 0 ) ) + ", imposible hacer operaciones aritméticas [#, %]", id, true ) );
                    errors.add( new ErrorLSSL( 1, "Error semántico : Variable \"" + id.lexemeRank( 2 ) + "\" es de tipo " + identificadores.get( id.lexemeRank( 2 ) ) + ", imposible hacer operaciones aritméticas [#, %]", id, true ) );
                }
            }
        }
    }      
    
    private void colorAnalysis() 
    {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        
        try 
        {
            File codigo = new File( "color.encrypter" );
            FileOutputStream output = new FileOutputStream( codigo );
            byte[] bytesText = jTextPaneSource.getText().getBytes();
            output.write( bytesText );
            BufferedReader entrada = new BufferedReader( new InputStreamReader( new FileInputStream( codigo ), "UTF8" ) );
            lexerColor = new LexerColor( entrada );
            while( true ) 
            {
                TextColor textColor = lexerColor.yylex();
                if( textColor == null ) 
                {
                    break;
                }
                textsColor.add( textColor );
            }
        } 
        catch( FileNotFoundException ex ) 
        {
            System.out.println( "El archivo no pudo ser encontrado... " + ex.getMessage() );
        } 
        catch( IOException ex ) 
        {
            System.out.println( "Error al escribir en el archivo... " + ex.getMessage() );
        }
        
        Functions.colorTextPane(textsColor, jTextPaneSource, new Color(171, 178, 191 ) );
    }

    private void fillTableTokens() 
    {
        tokens.forEach( token -> { Object[] data = new Object[]{ token.getLexicalComp(), 
                                                                 token.getLexeme(), "[" + 
                                                                 token.getLine() + ", " + 
                                                                 token.getColumn() + "]" }; 
                                                                 Functions.addRowDataInTable( jTableTokens, data ); 
        } );
    }

    private void printConsole() 
    {
        int sizeErrors = errors.size();
        if( sizeErrors > 0 ) 
        {
            Functions.sortErrorsByLineAndColumn( errors );
            String strErrors = "\n";
            
            for( ErrorLSSL error : errors ) 
            {
                String strError = String.valueOf( error );
                strErrors += strError + "\n";
            }
            
            jTextAreaOutput.setText( "Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores..." );
            jTextAreaOutput.setForeground( Color.red );    
        } 
        else 
        {
            jTextAreaOutput.setText( "Compilación exitosa..." );
            jTextAreaOutput.setForeground( Color.green );
        }
        
        jTextAreaOutput.setCaretPosition( 0 );
    }

    private void clearFields() 
    {
        Functions.clearDataInTable( jTableTokens );
        jTextAreaOutput.setText( "" );
        tokens.clear();
        errors.clear();
        
        List< List< ? > > productions = Arrays.asList( identProdCopia, identProd, asigProd, asigProdConID, compaProdIzq,
                                                       compaProdDer, compaProdDoble, operProdIzq, operProdDer,
                                                       operProdDoble, funcProd, ifProd, whileProd );

        for( List<?> prod : productions ) 
        {
            if( prod != null ) 
            {
                prod.clear();
            }
        }
        
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] ) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() ) 
            {
                if ( "Nimbus".equals( info.getName() ) ) 
                {
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        } 
        catch( ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex ) 
        {
            java.util.logging.Logger.getLogger(Compiler.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {

            //llamamos el metodo setlookandfeel,  y pasaremos la clase FlatIntelliJLaf
            //edita el diseño de los botones y de nuestro editor
            try 
            {
                UIManager.setLookAndFeel( new FlatIntelliJLaf() );
            } 
            catch( UnsupportedLookAndFeelException ex ) 
            {
                System.out.println( "LookAndFeel no soportado: " + ex );
            }
            
            //ponemos nuestro editor en visible
            new Compiler().setVisible( true );
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JButton jButtonCompile;
    private javax.swing.JButton jButtonExecute;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonOpen;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSaveAs;
    private javax.swing.JMenuItem jMenuAbout;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuExit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuNewFile;
    private javax.swing.JMenuItem jMenuOpenFile;
    private javax.swing.JMenu jMenuRun;
    private javax.swing.JMenuItem jMenuRunProject;
    private javax.swing.JMenuItem jMenuSave;
    private javax.swing.JMenuItem jMenuSaveAs;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableTokens;
    private javax.swing.JTextArea jTextAreaOutput;
    private javax.swing.JTextPane jTextPaneSource;
    private javax.swing.JPanel rootPanel;
    // End of variables declaration//GEN-END:variables
}