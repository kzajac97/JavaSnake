
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import java.util.Random;

public class FoodGenerator
{
    Random positionGenerator;
    Food food;
    
    public FoodGenerator()
    {
        positionGenerator = new Random();  
        generateFood();
    }
    
    public Point getFoodPosition()
    {
        return food.getPosition();
    }
    
    public void generateFood()
    {
        // random coordinates 
        int x = 20 + Math.abs(20 * (positionGenerator.nextInt() % 21));
        int y = 60 + Math.abs(20 * (positionGenerator.nextInt() % 14));
        
        food = new Food( new Point(x,y) );
    }
}
