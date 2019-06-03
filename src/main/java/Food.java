/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeapp;

import java.awt.Point;

/**
 *
 * @author terg
 */
public class Food extends Segment
{
    boolean exists;
    
    public Food(Point position)
    {
        // call parent constructor
        super(position); 
    }
}