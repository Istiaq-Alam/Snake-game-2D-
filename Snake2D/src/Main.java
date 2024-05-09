

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import snakegame.SnakeGame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Raisha Anjum Risha
 */
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new SnakeGame());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon("gamelogo.png");
        frame.setIconImage(icon.getImage()); // change icon
        
    }
}
    

