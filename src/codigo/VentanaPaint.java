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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Thomas
 */
public class VentanaPaint extends javax.swing.JFrame {

    BufferedImage buffer, buffer2= null;
    //Esta clase nos va a permitir crear un círculo. Forma parte de una librería de Java. Queremos qu e cuando hagamos click
    //se crée esa elipse.
    Circulo auxiliar;
    /*Vamos a declarar un segundo buffer donde vamos a guardar la información que ya hemos dibujado sobre la pantalla. Es decir,
    un buffer coge los puntos intermedios que son pincho, suelto, arrastro, y el segundo buffer guarda la versión final. Los buffer
    son memoria y el jPanel es pantalla.*/
    
    
    public VentanaPaint() {
        initComponents();
        //Llamamos al método en el constructor de la clase.
        inicializaBuffers();
        //Vamos a poner un tamaño a la ventana de colores.
        jDialog1.setSize(640,450);
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
        Graphics2D g2= buffer.createGraphics();
        //Inicializa el buffer para que sea un rectángulo rojo que ocupe todo el jPanel.
        g2.setColor(Color.white);
        g2.fillRect(0,0, buffer.getWidth(),buffer.getHeight());
        
        //Inicializo el segundo buffer.
        
        buffer2 = (BufferedImage) jPanel1.createImage(jPanel1.getWidth(),jPanel1.getHeight());
        g2= buffer2.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0,0, buffer.getWidth(),buffer.getHeight());
        
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
        Graphics2D g2=(Graphics2D) jPanel1.getGraphics();
        g2.drawImage(buffer, 0, 0, null);
        
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
        jPanel1 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();

        jButton1.setText("cancelar");

        jButton2.setText("guardar");

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
            .addGap(0, 736, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );

        jCheckBox1.setText("Relleno");

        jButton3.setText("color");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        //El dibujado ocurre en el método dragged.
        //Apuntamos al buffer. Pintamos sobre blanco y rellenamos.
        
        Graphics2D g2=(Graphics2D) buffer.getGraphics();
        //repinto el fondo. La primera vez es blanco, la segunda vez será lo que haya pintado.
        g2.drawImage(buffer2, 0,0, null);
        
       auxiliar.dibujate(g2, evt.getX());
        
        //Dibujar requiere muchos recursos del ordenador. Se tiene que hacer lo mínimo imprescindible posible. Le estamos
        //diciendo a Java que hemos cambiado el tamaño del círculo y que tiene que actualizarlo en pantalla. El método repaint
        //le dice a Java que actualice pero no le mete prisa, por eso no usamos el método paint.
        repaint(0,0,1,1);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // Este evento nos permite inicializar la elipse que vamos a dibujar en el buffer. Tiene que estar en la posición x,y donde
        //hagamos el click.
        //evt.getX nos dice en dónde se ha producido el click, y el getY en la coordenada Y donde ha ocurrido el evento. Le preguntamos
        //al evento co evt. 1,1 son los píxeles con los que se va a crear la elipse, el width y el height.
        //El botón checkbox que hemos metido sirve para poner el relleno.
        auxiliar= new Circulo(evt.getX(), evt.getY(), 1, Color.blue, jCheckBox1.isSelected());
        
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
        //apunto al buffer2. En el momento del release, lo que hayamos dibujado se pinta sobre el buffer2.
        /*El buffer coge el desarrollo de lo que estamos pintando y el buffer2 guarda el resultado final.*/
        Graphics2D g2 = (Graphics2D) buffer2.getGraphics();
        //Estas lineas son las que hacen el dibujado.
        auxiliar.dibujate(g2, evt.getX());
    }//GEN-LAST:event_jPanel1MouseReleased

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton3MousePressed

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
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
