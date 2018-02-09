/*
 * 
 */
package codigo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * Extendemos a Ellipse2D.Double.
 */
public class Circulo extends Ellipse2D.Double {
    Color color=null;
    public boolean relleno=false;
    
    //creamos el constructor.
    
    public Circulo(int _posX, int _posY, int _diametro, Color _color, boolean _relleno){
       super();
       //Guardo las coordenadas del inicio del círculo. El this hace referencia al objeto de esta clase, pero no es necesario.
       this.x =_posX;
       this.y =_posY;
       width=_diametro;
       height=_diametro;
       color=_color;
       relleno=_relleno;
    }
    
    //Creamos un método para dibujar al que vamos a llamar desde la clase principal.
    
    public void dibujate(Graphics2D g2, int _posX){
        
         //Auxiliar es la posición donde está la elipse. Casteamos a integer con el (int). Restamos porque queremos saber
        //la distancia desde el punto donde hemos hecho click al punto donde estamos haciendo el dragged.
        /*Si hacemos click en X=70 y arrastramos hasta x=90 restamos 90-70 para obtener el tamaño  de la elipse, que
        es el integer radio.*/
        int diametro=Math.abs( (int)x-_posX);
        //Declaramos que el ancho y alto del círculo es igual a la fórmula que encontramos en el radio.
        width=diametro;
        height=diametro;
        //El g2 aquí está apuntando al buffer. A auxiliar le damos un fill.
        g2.setColor(color);
        //El this, al encontrarnos en la clase círculo, coge el mismo objeto círculo que estamos pintando y lo pinta.
        //Ponemos dos condiciones. Si relleno es verdadero, llena el círculo, si no, sólo pinta la linea.
        if(relleno){
            g2.fill(this);
        }else{
            g2.draw(this);
        }
    
    }
}
