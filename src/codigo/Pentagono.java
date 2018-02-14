/*
 * Esta clase dibuja pentágonos.
 */
package codigo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 *Vamos a extender a la clase Polygon de la librería de java. Para poder crear un polígono le tenemos que dar las coordenadas
 * del polígono, por ejemplo, las coordenadas x-y del vértice más alto del triángulo, las x-y del vértice inferior izquierdo
 * y las x-y del vértice inferior derecho.
 * La clase Polygon tiene un array que se llama xPoints y otro array que se llama yPoints que nos permite guardar las coordenadas
 * de cada uno de los puntos.
 * xPoints-posiciones 0,1,2,3. La x de la posición 0 es la x del vértice más alto. La posición 1 determina la x de la esquina
 * inferior derecha y la posición 2 determina la x de la esquina inferior izquierda.
 * yPoints-posiciones 0,1,2,3.... lo mismo pero con la y.
 */
public class Pentagono extends Polygon{
       Color color=null;
       public boolean relleno=false;
       //coordenadas del centro del pentágono.
       int x=0;
       int y=0;
    
    public Pentagono(int _posX, int _posY, int _altura, Color _color, boolean _relleno){
        super(new int[5], new int[5], 5);
       /*En los triángulos no hay que invocar al super. Hay un constructor implícito que tiene tres puntos. En los polígonos
        sí tenemos que declarar el super. Con los dos arrays de ints vamos a poder inicializar bien el constructor. Usamos los
        dos arrays y le pasamos también el número de puntos. Estos parámetros en el Super nos va a permitir crear el array
        de las dimensiones que necesitamos para poder crear el polígono.
        */
       //Centro del pentágono.
       this.x=_posX;
       this.y=_posY;
       
       npoints=5;
      /* //Radio porque el pentágono se encuentra dentro de un círculo donde sus vértices tocan el borde del círculo. Calculamos
       //los puntos que tocan en la circunferencia en el bucle for.
       int radio=1;
       //El bucle for inicializa cada uno de los puntos.
       for(int i=0; i<4; i++){
           this.xpoints[i]=(int) (140+radio*Math.cos(2*Math.PI*(i+40)/npoints));
           this.ypoints[i]=(int) (320+radio*Math.sin(2*Math.PI*(i+40)/npoints));
       }*/
      
               
       color=_color;
       relleno=_relleno;  
    }
    
        public void dibujate(Graphics2D g2, int _posY){  
         //Redibujamos el pentágono (TODO).
        calculaVertices(y-_posY);
        
        
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
        private void calculaVertices(int _radio){
            
            //Calcula los puntos.
        for(int i=0; i<npoints; i++){
           this.xpoints[i]=(int) (x+_radio*Math.cos(2*Math.PI*i/npoints));
           this.ypoints[i]=(int) (y+_radio*Math.sin(2*Math.PI*i/npoints));
       }
        }
    
}
