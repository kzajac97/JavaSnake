
import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
    Snake snake;
    FoodGenerator generator;
    
    public GamePanel()
    {
        snake = new Snake();
        generator = new FoodGenerator();
    }
    
    public void setSnakeDirection(char direction)
        { snake.setDirection(direction); }
        
    @Override
    public void paint(Graphics g) 
    {
        Graphics2D graphics = (Graphics2D)g;

        Rectangle2D scene = new Rectangle2D.Float(20, 60, 480, 320);
        Rectangle2D sceneBorder = new Rectangle2D.Float(0,40,680,400);

        graphics.setColor(new Color(68, 152, 255));
        graphics.fill(sceneBorder);
        graphics.setPaint(new GradientPaint(0, 0, new Color(187, 237, 61),480, 320,
                new Color(120, 255, 39)));
        graphics.fill(scene);
        
        for (int i=0; i<snake.body.size(); i++)
        {
            if(i == 0) graphics.setColor(new Color(115, 0, 18));
            else
                {
                if (i % 2 == 1) graphics.setColor(new Color(255, 228, 7));
                else graphics.setColor(new Color(214, 0, 34));
            }


            graphics.fill(snake.body.get(i).body);
            graphics.setColor(new Color(0,155,0));
            graphics.draw(snake.body.get(i).body);  
        }            

        graphics.setColor(Color.black);
        graphics.fill(generator.food.body);
    }   
}
