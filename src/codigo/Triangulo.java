/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class Triangulo extends Polygon{
       Color color=null;
       public boolean relleno=false;
    
    public Triangulo(int _posX, int _posY, int _altura, Color _color, boolean _relleno){
       /*En los Polygon no hay que invocar al super. En círculos y cuadrados ya sabemos cuantos lados tiene, pero en los
        polígonos no. Una propiedad de los array es que no se pueden cambiar las dimensiones una vez declaradas. Hasta que
        no sepamos cuantos puntos va a tener no podemos declararlo. La variable npoints nos va a dar el número de vértices
        que va a tener nuestro polígono.*/
       npoints=3;
       //Vamos a determinar las primeras coordenadas del triángulo. Son las coordenadas donde se inicializa el triángulo.
       this.xpoints[0]= _posX;
       this.ypoints[0]= _posY;
       //Las Y que coinciden con el valor de la altura. Como es equilátero ambos y están en la misma posición.
       this.ypoints[1]= _posY + _altura;
       this.ypoints[2]= _posY + _altura;
       //calculamos con esta fórmula la mitad de la base del triángulo.
       int mediaBase= (int) (_altura/Math.tan(Math.toRadians(60)));
      //La x de la base es la posición x + la mitad de la base para la derecha y x- la mitad de la base para el izquierdo.
       this.xpoints[1]=_posX+mediaBase;
       this.xpoints[2]=_posX-mediaBase;
               
       color=_color;
       relleno=_relleno;  
    }
    
        public void dibujate(Graphics2D g2, int _posY){  
         //Redibujamos el triángulo.
        int altura=Math.abs( (int) ypoints[0]-_posY);
       
        this.ypoints[1]= _posY + altura;
       this.ypoints[2]= _posY + altura;
       //calculamos con esta fórmula la mitad de la base del triángulo.
       int mediaBase= (int) (altura/Math.sin(Math.toRadians(60)));
      //La x de la base es la posición x + la mitad de la base para la derecha y x- la mitad de la base para el izquierdo.
       this.xpoints[1]=this.xpoints[0]+mediaBase;
       this.xpoints[2]=this.xpoints[0]-mediaBase;
        
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
