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
public class Circulo extends Forma {
    Color color=null;
    public boolean relleno=false;
    
    //creamos el constructor.
    
    public Circulo(int _posX, int _posY, Color _color, boolean _relleno){
        super(_posX, _posY, 360, _color, _relleno);
       
    }
}
