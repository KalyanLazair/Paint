/*

Autor; Marta Márquez Olalla.
 

Java trata los paneles como si fuesen jpg. Hay dos formas de dibujar en un panel.
La primera es que vamos dibujando y queda todo marcado.
La segunda; Hacemos un click en el panel y a medida que hacemos el mouseDragged se va dibujando
en la pantalla. Cuando se suelta el dragged queda plasmado el cuadrado en el panel.
Hay que utilizar una técnica que se llama "doble buffer". Un buffer es una memoria temporal en la cual
se va almacenando una versión de algo. El doble buffer va a tener la versión anterior del jPanel (cuando
empiezo a pintar el jPanel está en blanco). Al principio dibujamos un cuadrado pequeño. Cuando se activa el
dragged otra vez se dibuja un cuadrado más grande. Nos queda una sombra. Lo que el buffer va a hacer es
repintar el panel entero cada vez que se activa el dragged. En el momento en que está la versión definitiva y
el usuario suelta el ratón, se dibuja la imagen final sobre el jPanel.
 */
package codigo;

import java.awt.Color;
import static java.awt.Color.RED;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Thomas
 */
public class VentanaPaint extends javax.swing.JFrame {

    BufferedImage buffer, buffer2= null;
    //Esta clase nos va a permitir crear un círculo. Forma parte de una librería de Java. Queremos qu e cuando hagamos click
    //se crée esa elipse.
    Forma miForma;
    /*Vamos a declarar un segundo buffer donde vamos a guardar la información que ya hemos dibujado sobre la pantalla. Es decir,
    un buffer coge los puntos intermedios que son pincho, suelto, arrastro, y el segundo buffer guarda la versión final. Los buffer
    son memoria y el jPanel es pantalla.*/
    
    /*En esta variable vamos a guardar el color seleccionado.*/
    Color colorSeleccionado= Color.black;
    

    
   int formaSeleccionada=0; //Si vale 100, pinto círculos.
                            //Si vale 4 pinto cuadrados.
                            //Si vale 5 pinto pentágonos.
                            //Si vale 3 pinto triángulos.
   
   /*Vamos a declarar en instancia la función Graphics, vamos a llamar a cada una acorde al lugar al que afectan.
   bufferGraphics para el primer buffer, buffer2Graphics para el segundo buffer y jPanelGraphics para el jPanel.*/
   Graphics2D bufferGraphics, buffer2Graphics, jPanelGraphics=null;
    
    
    public VentanaPaint() {
        initComponents();
        //Llamamos al método en el constructor de la clase.
        inicializaBuffers();
        //Vamos a poner un tamaño a la ventana de colores.
        jDialog1.setSize(640,450);
        //Esta función hace que, en el menú de cargar/guardar, podamos seleccionar sólo los archivos con esta extensión.
        FileNameExtensionFilter filtro= new FileNameExtensionFilter("JPG y PNG sólo", "jpg", "png");
        jFileChooser1.setFileFilter(filtro);
    }
    
    
    /*Este método va a crear la zona de memoria donde habrá una imagen
    que tendrá el mismo ancho y alto del jPanel.*/
    private void inicializaBuffers(){
        /*Tenemos una clase madre que se llama Image y una clase hija que se llama BufferedImage que extiende a la clase
        madre. Podemos castear tipos. Un objeto que sea de tipo BufferedImage, como el "buffer", le podemos decir a ese
        objeto de clase BufferImage que puede ser de clase Image. Cuando casteamos convertimos tipos en otros tipos
        que extienden esos tipos. Esto lo hacemos para que sean compatibles las clases. En este caso el objeto jPanel tiene
        métodos que permiten tratar objetos como tipo image, y al castearlo como BufferedImage hereda todo lo de la clase 
        Image así como de tipo BufferedImage.*/
       buffer = (BufferedImage) jPanel1.createImage(jPanel1.getWidth(),jPanel1.getHeight());
       //Creo una imagen modificable. Con el buffer guardamos en memoria lo que está pintado en el jPanel. Le estamos
       //dotando a ese área de memoria la capacidad de dibujar cosas.
        bufferGraphics= buffer.createGraphics();
        //Inicializa el buffer para que sea un rectángulo rojo que ocupe todo el jPanel.
        bufferGraphics.setColor(Color.white);
        bufferGraphics.fillRect(0,0, buffer.getWidth(),buffer.getHeight());
        
        //Inicializo el segundo buffer.
        
        buffer2 = (BufferedImage) jPanel1.createImage(jPanel1.getWidth(),jPanel1.getHeight());
        buffer2Graphics= buffer2.createGraphics();
        buffer2Graphics.setColor(Color.white);
        buffer2Graphics.fillRect(0,0, buffer.getWidth(),buffer.getHeight());
        
        //Inicializamos el jPanel.
         jPanelGraphics=(Graphics2D) jPanel1.getGraphics();
        
    }
    
     private void deSelecciona(){
        Component[] components = (Component[]) getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof JToggleButton) {
                ((JToggleButton)comp).setSelected(false);
            }
        } 
    }
    
    /*Tenemos que hacer un método @Override, que significa sobreescribir. Cuando hemos creado clases hemos extendido
    a partir de una clase principal, por lo que hemos creado clases más grandes. Puede darse el caso en el que no nos
    interese extender todos los métodos de una clase. Eso lo hacemos con la técnica de @Override. Vamos a sobre-escribir
    un método que se llama "paint" y que es el método que tiene Java para dibujar en la pantalla. Lo que hace el método
    paint normal, coge lo que se ha dibujado en el jPanel y se pinta en el frame completo. Lo que vamos a hacer es
    sobreescribir el método paint.*/
    
    @Override
    public void paint(Graphics g){
        //Super invoca al método que hay en la clase principal. VentanaPaint extiende a jFrame, por eso
        //tenemos todo lo que tiene un jFrame. El Super.paint llama al método paint en el jFrame, que lo
        //dibuja todo en su sitio. Si no hacemos esto, al override borramos también el contenido de VentanaPaint.
        super.paint(g);
        //Lo más habitual es que añadamos cosas a un método, no que borremos todo lo que contiene un método.
        
        //Vamos a pintar el buffer sobre el jFrame.
        //Primero creamos una variable que apunta al sitio en el que queremos pintar. getGraphics lo que hace es apuntar en qué
        //zona de memoria está jPanel para dibujar encima de él. Graphics2D tiene el método drawImage que usamos a continuación.
        //jPanelGraphics=(Graphics2D) jPanel1.getGraphics();
        
        jPanelGraphics.drawImage(buffer, 0, 0, null);
        
        /*Vamos a ir dibujando cosas sobre el buffer. El método va a coger lo que hay en el buffer y lo va a pintar en el jPanel.
        Vamos a crear una clase para cada objeto que vamos a dibujar (círculos, cuadrados, etc). Para dibujar vamos a aprovechar
        el evento mouseDragged. Cuando se produzca el click quiero crear un objeto, cuando se produzca el dragged quiero que ese
        objeto aumente de tamaño y cuando se produzca el release quiero que se cree la versión final del objeto.*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jColorChooser1 = new javax.swing.JColorChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jButton1.setText("cancelar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jButton2.setText("guardar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(113, 113, 113))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 867, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );

        jCheckBox1.setText("Relleno");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Color");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulo.png"))); // NOI18N
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jToggleButton1MousePressed(evt);
            }
        });

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cuadrado.png"))); // NOI18N
        jToggleButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jToggleButton2MousePressed(evt);
            }
        });

        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/linea.png"))); // NOI18N

        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulo.png"))); // NOI18N
        jToggleButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jToggleButton4MousePressed(evt);
            }
        });

        jToggleButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulo.png"))); // NOI18N
        jToggleButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jToggleButton5MousePressed(evt);
            }
        });

        jToggleButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulo.png"))); // NOI18N
        jToggleButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jToggleButton6MousePressed(evt);
            }
        });

        jMenu1.setText("File");
        jMenu1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jMenu1StateChanged(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem1.setText("Guardar");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Cargar");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        //El dibujado ocurre en el método dragged.
        //Apuntamos al buffer. Pintamos sobre blanco y rellenamos.
        
        //Graphics2D g2=(Graphics2D) buffer.getGraphics();
        //repinto el fondo. La primera vez es blanco, la segunda vez será lo que haya pintado.
        bufferGraphics.drawImage(buffer2, 0,0, null);
        
        miForma.dibujate(bufferGraphics, evt.getY(), evt.getX());

        
        //Dibujar requiere muchos recursos del ordenador. Se tiene que hacer lo mínimo imprescindible posible. Le estamos
        //diciendo a Java que hemos cambiado el tamaño del círculo y que tiene que actualizarlo en pantalla. El método repaint
        //le dice a Java que actualice pero no le mete prisa, por eso no usamos el método paint.
        repaint(0,0,1,1);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        //inicializo la forma que usaré para dibujar en el buffer.
        
        switch(formaSeleccionada){
            case 100: miForma= new Circulo(evt.getX(), evt.getY(), colorSeleccionado, jCheckBox1.isSelected()); break;
            case 4: miForma= new Cuadrado(evt.getX(), evt.getY(), colorSeleccionado, jCheckBox1.isSelected()); break;
            case 3: miForma= new Triangulo(evt.getX(), evt.getY(), colorSeleccionado, jCheckBox1.isSelected()); break;
            case 5: miForma= new Pentagono(evt.getX(), evt.getY(), colorSeleccionado, jCheckBox1.isSelected()); break;
            case 24: miForma= new Estrella(evt.getX(), evt.getY(), colorSeleccionado, jCheckBox1.isSelected()); break;
        }

        // Este evento nos permite inicializar la elipse que vamos a dibujar en el buffer. Tiene que estar en la posición x,y donde
        //hagamos el click.
        //evt.getX nos dice en dónde se ha producido el click, y el getY en la coordenada Y donde ha ocurrido el evento. Le preguntamos
        //al evento co evt. 1,1 son los píxeles con los que se va a crear la elipse, el width y el height.
        //El botón checkbox que hemos metido sirve para poner el relleno.
        //auxiliar= new Circulo(evt.getX(), evt.getY(), 1, colorSeleccionado, jCheckBox1.isSelected());
        
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
        //apunto al buffer2. En el momento del release, lo que hayamos dibujado se pinta sobre el buffer2.
        /*El buffer coge el desarrollo de lo que estamos pintando y el buffer2 guarda el resultado final.*/
        //Graphics2D g2 = (Graphics2D) buffer2.getGraphics();
        //Estas lineas son las que hacen el dibujado.
         miForma.dibujate(buffer2Graphics, evt.getY(), evt.getX());
    }//GEN-LAST:event_jPanel1MouseReleased

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jLabel1MousePressed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        jDialog1.setVisible(false);
        colorSeleccionado = jColorChooser1.getColor();
        jLabel1.setBackground(colorSeleccionado);
    }//GEN-LAST:event_jButton2MousePressed

    private void jToggleButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MousePressed
        //Selecciona los círculos a través del valor numérico de formaSeleccionada.
        formaSeleccionada=100;
        deSelecciona();
    }//GEN-LAST:event_jToggleButton1MousePressed

    private void jToggleButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton2MousePressed
        //cuadrados.
        formaSeleccionada=4;
        deSelecciona();
    }//GEN-LAST:event_jToggleButton2MousePressed

    private void jToggleButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton4MousePressed
        //Triangulo
        formaSeleccionada=3;
        deSelecciona();
        
    }//GEN-LAST:event_jToggleButton4MousePressed

    private void jToggleButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton5MousePressed
        //Pentagono
        formaSeleccionada=5;
        deSelecciona();
    }//GEN-LAST:event_jToggleButton5MousePressed

    private void jToggleButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton6MousePressed
        formaSeleccionada=24;
        deSelecciona();
    }//GEN-LAST:event_jToggleButton6MousePressed

    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed

        //Neceitamos saber si el usuario ha presionado cancelar o guardar.
        int seleccion= jFileChooser1.showSaveDialog(this);
        
        if(seleccion==JFileChooser.APPROVE_OPTION){
          //Si llego aquí es que el usuario ha pulsado en guardar cuando ha salido el menú de jFileChooser.
          //Tenemos la opción de guardar la imagen del buffer sobre un fichero.
          File fichero= jFileChooser1.getSelectedFile();
          String nombre= fichero.getName();
          //Necesitamos saber si es un png o un jpg. La extensión, para poder guardarlo y que no de error.
          String extension=nombre.substring(nombre.lastIndexOf('.')+1);
          if(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")){
            try{
              ImageIO.write(buffer, extension, fichero);
            }
            catch(IOException e){
                
            }
            }
            
                 
          }
        
        
    }//GEN-LAST:event_jMenuItem1MousePressed

    private void jMenu1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jMenu1StateChanged
        //Esto resuelve un bug en el que, al desplegar el menu, se quedaba un rectángulo gris en el buffer.
        //Le decimos al buffer que, cuando se cierre el menú, se tiene que redibujar.
        JMenu menu = (JMenu) evt.getSource();
        if(!menu.isSelected()){
          repaint();
        }
    }//GEN-LAST:event_jMenu1StateChanged

    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        //Guardar.
        int seleccion= jFileChooser1.showOpenDialog(this);
        
        if(seleccion==JFileChooser.APPROVE_OPTION){
          //Si llego aquí es que el usuario ha pulsado en guardar cuando ha salido el menú de jFileChooser.
          //Tenemos la opción de guardar la imagen del buffer sobre un fichero.
          File fichero= jFileChooser1.getSelectedFile();
          String nombre= fichero.getName();
          //Necesitamos saber si es un png o un jpg. La extensión, para poder guardarlo y que no de error.
          String extension=nombre.substring(nombre.lastIndexOf('.')+1);
          BufferedImage imagen=null;
          if(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")){
            try{
              buffer=ImageIO.read(fichero);
              bufferGraphics.drawImage(imagen,0,0, null);
              buffer2Graphics.drawImage(imagen,0,0,null);
              repaint();
            }
            catch(IOException e){
            }
            }
            
                 
          }
    }//GEN-LAST:event_jMenuItem2MousePressed

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
            java.util.logging.Logger.getLogger(VentanaPaint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPaint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPaint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPaint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPaint().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    // End of variables declaration//GEN-END:variables
}
