/*
 * Autor; Marta Márquez Olalla.

Todas las partes comunes a los objetos que vamos a dibujar.
 */
package codigo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 *
 * La clase Forma es la parte común de todas las formas regulares que dibuja el programa.
 */
public class Forma extends Polygon{
    
    Color color=null;
       public boolean relleno=false;
       //Coordenadas del centro de la forma.
       int x=0;
       int y=0;
       
       public Forma(int _posX, int _posY, int _numLados, Color _color, boolean _relleno){
        super(new int[_numLados], new int[_numLados], _numLados);
       
       //Centro de la forma.
       this.x=_posX;
       this.y=_posY;
               
       color=_color;
       relleno=_relleno;  
    }
       
       
        public void dibujate(Graphics2D g2, int _posY, int _posX){  
         //Redibujamos el pentágono (TODO).
        calculaVertices(y-_posY, x-_posX);
        
        
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
        //Recalcula la posición de los vértices en un polígono regular.
        //Lo ponemos PUBLIC para poder hacer un override en la clase Estrella. De otra manera no se podría.
        public void calculaVertices(int _radio, double _giro){
            
            //Calcula los puntos.
        for(int i=0; i<npoints; i++){
           this.xpoints[i]=(int) (x+_radio*Math.cos((2*Math.PI*i+_giro)/npoints));
           this.ypoints[i]=(int) (y+_radio*Math.sin((2*Math.PI*i+_giro)/npoints));
       }
        }
    
}
