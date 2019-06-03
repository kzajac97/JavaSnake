/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import java.util.Random;

/**
 *
 * @author terg
 */
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
        int x = 20 + Math.abs(20 * (positionGenerator.nextInt() % 20));
        int y = 60 + Math.abs(20 * (positionGenerator.nextInt() % 20));
        
        food = new Food( new Point(x,y) );
    }
}
