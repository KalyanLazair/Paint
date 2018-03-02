/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Thomas
 */
public class Linea extends Line2D.Double{
     int x1=0;
     int y1=0;
     int x2=0;
     int y2=0;
     Color color=null;
     boolean relleno;
    
    
    public Linea(int _posX, int _posY, Color _color){
        super();
        this.x1=_posX;
        this.y1=_posY;
        this.x2=_posX;
        this.y2=_posY;
        
        
        color=_color;
       
    }
    
    public void dibujaLinea(Graphics2D g2, int _posX, int _posY){
             x1=_posX;
             y1=_posY;
             
             x2=x1+_posX;
             y2=y1+_posY;
        //El g2 aquí está apuntando al buffer. A auxiliar le damos un fill.
        g2.setColor(color);
        //El this, al encontrarnos en la clase círculo, coge el mismo objeto círculo que estamos pintando y lo pinta.
        //Ponemos dos condiciones. Si relleno es verdadero, llena el círculo, si no, sólo pinta la linea.
        
        g2.draw(this);
        
    }
    
    
    
    
    
}
