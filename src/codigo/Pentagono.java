/*
 * Esta clase dibuja pentágonos.
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
public class Pentagono extends Forma{
       
    
   public Pentagono(int _posX, int _posY, Color _color, boolean _relleno){
        super(_posX, _posY, 5, _color, _relleno);
       
    }
    
}
