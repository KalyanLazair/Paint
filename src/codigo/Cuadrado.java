/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Thomas
 */
public class Cuadrado extends Rectangle2D.Double{
    Color color=null;
    public boolean relleno=false;
    
    public Cuadrado(int _posX, int _posY, int _lado, Color _color, boolean _relleno){
         super();
       //Guardo las coordenadas del inicio del círculo. El this hace referencia al objeto de esta clase, pero no es necesario.
       this.x =_posX;
       this.y =_posY;
       width=_lado;
       height=_lado;
       color=_color;
       relleno=_relleno;
    
    }
    
    public void dibujate(Graphics2D g2, int _posX){  
         //Auxiliar es la posición donde está la elipse. Casteamos a integer con el (int). Restamos porque queremos saber
        //la distancia desde el punto donde hemos hecho click al punto donde estamos haciendo el dragged.
        /*Si hacemos click en X=70 y arrastramos hasta x=90 restamos 90-70 para obtener el tamaño  de la elipse, que
        es el integer radio.*/
        int lado=Math.abs( (int)x-_posX);
        //Declaramos que el ancho y alto del círculo es igual a la fórmula que encontramos en el radio.
        width=lado;
        height=lado;
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
