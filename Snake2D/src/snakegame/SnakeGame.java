/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snakegame;

/**
 *
 * @author Raisha Anjum 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.ImageIcon;

public class SnakeGame extends JPanel implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int UNIT_SIZE = 20;
    private static final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 100;
    private final int[] snakeX = new int[GAME_UNITS];
    private final int[] snakeY = new int[GAME_UNITS];
    private int snakeLength;
    private int appleX;
    private int appleY;
    private char direction;
    private boolean isRunning;
    private Timer timer;

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(new MyKeyAdapter());
        

        startGame();
    }

    public void startGame() {
        snakeLength = 3;
        snakeX[0] = UNIT_SIZE * 2;
        snakeY[0] = UNIT_SIZE * 2;
        direction = 'R';
        generateApple();
        isRunning = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void generateApple() {
        appleX = (int) (Math.random() * (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = (int) (Math.random() * (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = snakeLength; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        switch (direction) {
            case 'U':
                snakeY[0] -= UNIT_SIZE;
                break;
            case 'D':
                snakeY[0] += UNIT_SIZE;
                break;
            case 'L':
                snakeX[0] -= UNIT_SIZE;
                break;
            case 'R':
                snakeX[0] += UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if (snakeX[0] == appleX && snakeY[0] == appleY) {
            snakeLength++;
            generateApple();
        }
    }

    public void checkCollisions() {
        // Check if head collides with body
        for (int i = snakeLength; i > 0; i--) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                isRunning = false;
                break;
            }
        }

        // Check if head collides with boundaries
        if (snakeX[0] < 0 || snakeX[0] >= WIDTH || snakeY[0] < 0 || snakeY[0] >= HEIGHT) {
            isRunning = false;
        }

        if (!isRunning) {
            timer.stop();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (isRunning) {
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.GREEN.darker());
                }

                g.fillRect(snakeX[i], snakeY[i], UNIT_SIZE, UNIT_SIZE);
            }
        } else {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", WIDTH / 2 - 120, HEIGHT / 2);
        }
    }

    
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            move();
            checkApple();
            checkCollisions();
        }

        repaint();
    }

   

    private class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

}
