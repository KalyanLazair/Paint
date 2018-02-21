/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;


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
public class Estrella extends Forma{
       
    
   public Estrella(int _posX, int _posY, Color _color, boolean _relleno){
        super(_posX, _posY, 24, _color, _relleno);
       
    }
   
 @Override  //Hay que respetar el public de la clase madre.
 public void calculaVertices(int _radio, double _giro){
       for(int i=0; i<npoints; i++){
           this.xpoints[i]=(int) (x+_radio*Math.cos((2*Math.PI*i+_giro)/npoints));
           this.xpoints[i+1]=(int) (x+_radio/2*Math.cos((2*Math.PI*i+_giro/2)/npoints));
           this.ypoints[i]=(int) (y+_radio*Math.sin((2*Math.PI*i+_giro)/npoints));
           this.ypoints[i+1]=(int) (y+_radio/2*Math.sin((2*Math.PI*i+_giro/2)/npoints));
           i++;
       }
 
 }
 
 /* for(int i=0; i<npoints; i++){
           this.xpoints[i]=(int) (x+_radio*Math.cos((2*Math.PI*i+_giro)/npoints));
           this.ypoints[i]=(int) (y+_radio*Math.sin((2*Math.PI*i+_giro)/npoints));
           i++;
       }*/
    
       
    
    
}
