
import java.awt.Point;
import java.util.ArrayList;


public class Snake
{
    // draw snake with starting position
    final Point startSegments [] = 
                                {   new Point(160, 240), 
                                    new Point(140, 240), 
                                    new Point(120, 240),
                                    new Point(100, 240), 
                                    new Point(80, 240), 
                                    new Point(60, 240) };
    
    ArrayList<Segment> body;
    char direction; // which direction Snake is poiting
    
    public Snake()
    {
        body = new ArrayList<>();
        initSnake();
    }
    
    void initSnake()
    {        
        direction = 'R';
        
        if(body != null)
        {
            // add Snake Segments
            for (int i=0; i<=2; i++)
                { body.add(new Segment(startSegments[i])); }
        }
    }
    
    public void destroySnake()
        { body.clear(); }
    
    public void grow()
    {
        Point current_pos = body.get(body.size()-1).getPosition(); // get current position
        Segment newSegment = new Segment(current_pos); // add new Segment
        body.add(newSegment);    
    }
    
    public void setDirection(char newDirection)
        { direction = newDirection; }
    
    public char getDirection()
        { return direction; }
    
    public void move()
    {
        for(int segment = body.size()-1; segment>=1; segment--)
        {
            Point new_position = body.get(segment-1).getPosition();            
            body.get(segment).moveToPosition(new_position);
        }

        body.get(0).move(direction);    
    }
    
    public Point getHeadPosition()
        { return body.get(0).getPosition(); }    
}
