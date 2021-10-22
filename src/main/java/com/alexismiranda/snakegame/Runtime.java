package com.alexismiranda.snakegame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Runtime implements Runnable {

    Snake snake;
    boolean estado = true;

    public Runtime(Snake snake) {
        this.snake = snake;
    }

    public void run() {
    	try {
        while (true) {
            snake.avanzar();
            snake.repaint();
            Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Logger.getLogger(Runtime.class.getName()).log(Level.SEVERE, null, e);
            // Restore interrupted state...
            Thread.currentThread().interrupt();
            
        }
    }

    public void parar() {
        this.estado = false;
    }

}
