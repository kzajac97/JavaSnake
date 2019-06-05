
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;


public class Segment{
    final Dimension size = new Dimension(20,20);
    Point position;
    RoundRectangle2D body;
    float arcw = 10;
    float arch = 50;
    
    public Segment(){
        position = new Point(0, 0); 
        body = new RoundRectangle2D.Float(position.x,position.y,size.width,size.height,arcw,arch);
    }

    public Segment(Point newPosition){
        position = new Point(newPosition.x, newPosition.y);          
        body = new RoundRectangle2D.Float(newPosition.x,newPosition.y,size.width,size.height,arcw,arch);
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
        body.setRoundRect(position.x,position.y, size.height, size.width,arcw,arch);
    }
    
    public void moveToPosition(Point newPosition)  {   
        position = newPosition;
        body.setRoundRect(position.x,position.y, size.height, size.width,arcw,arch);
    }
     
    
    public Point getPosition(){
        return position;
    }
    
}