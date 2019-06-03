/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author terg
 */
public class Segment{
    final Dimension size = new Dimension(20,20);
    Point position;
    Rectangle2D body;
    
    public Segment(){
        position = new Point(0, 0); 
        body = new Rectangle2D.Float(position.x,position.y,size.width,size.height); 
    }
    
    public Segment(Point newPosition){
        position = new Point(newPosition.x, newPosition.y);          
        body = new Rectangle2D.Float(newPosition.x,newPosition.y,size.width,size.height);
    }   
    
    public void move(char direction){
        int tmp_x=0, tmp_y=0;
        switch(direction){
            case 'U':
                tmp_y=20;
                break;
            case 'D':
                tmp_y=-20;
                break;
            case 'L':
                tmp_x=-20;
                break;
            case 'R':
                tmp_x=20;
                break;
        }
        
        Point tmp = position;
        position = new Point(tmp.x+tmp_x, tmp.y-tmp_y);
        body.setRect(position.x,position.y, size.height, size.width);
    }
    
    public void moveToPosition(Point newPosition)  {   
        position = newPosition;
        body.setRect(position.x,position.y, size.height, size.width); 
    }
     
    
    public Point getPosition(){
        return position;
    }
    
}