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
        while (true) {
            snake.avanzar();
            snake.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Logger.getLogger(Runtime.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void parar() {
        this.estado = false;
    }

}
