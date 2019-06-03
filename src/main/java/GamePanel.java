/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author terg
 */
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

        graphics.setColor(Color.red);
        graphics.fill(sceneBorder);
        graphics.setColor(new Color(0,255,0));
        graphics.fill(scene);
        
        for (int i=0; i<snake.body.size(); i++)
        {
            graphics.setColor(new Color(0,150,150));
            graphics.fill(snake.body.get(i).body);
            graphics.setColor(Color.red);
            graphics.draw(snake.body.get(i).body);  
        }            

        graphics.setColor(Color.black);
        graphics.fill(generator.food.body);
    }   
}
