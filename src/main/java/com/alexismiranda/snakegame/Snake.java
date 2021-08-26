package com.alexismiranda.snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Snake extends JPanel {

    Color colorSnake = Color.green;
    Color colorComida = Color.red;

    int tamMax, tam, cant, res;
    List<int[]> snake;
    int[] comida = new int[2];
    char direccion = 'R';
    char direccionSig = 'R';

    Thread hilo;
    Runtime runt;

    public Snake(int tamMax, int cant) {
        this.tamMax = tamMax;
        this.cant = cant;
        this.tam = tamMax / cant;
        this.res = tamMax % cant;
        snake = new ArrayList<int[]>();
        int[] a = {cant / 2 - 1, cant / 2 - 1};
        int[] b = {cant / 2, cant / 2 - 1};
        snake.add(a);
        snake.add(b);
        generarComida();
        runt = new Runtime(this);
        hilo = new Thread(runt);
        hilo.start();
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        grphcs.setColor(colorSnake);

        //Dibujando Serpiente
        for (int[] sn : snake) {
            grphcs.fillRect(res / 2 + sn[0] * tam, res / 2 + sn[1] * tam, tam - 1, tam - 1);
        }

        //Dibujando Comida
        grphcs.setColor(colorComida);
        grphcs.fillRect(res / 2 + comida[0] * tam, res / 2 + comida[1] * tam, tam - 1, tam - 1);

    }

    public void avanzar() {
        igualarDireccion();
        int[] ultimo = snake.get(snake.size() - 1);
        int agregarX = 0;
        int agregarY = 0;
        switch (direccion) {
            case 'R':
                agregarX = 1;
                break;
            case 'L':
                agregarX = -1;
                break;
            case 'U':
                agregarY = -1;
                break;
            case 'D':
                agregarY = 1;
                break;
        }
        int[] nuevo = {Math.floorMod(ultimo[0] + agregarX, cant),
            Math.floorMod(ultimo[1] + agregarY, cant)};
        boolean celdaOcupada = false;
        for (int[] sn : snake) {
            if (sn[0] == nuevo[0] && sn[1] == nuevo[1]) {
                celdaOcupada = true;
                break;
            }
        }
        if (celdaOcupada) {
            JOptionPane.showMessageDialog(this, "Perdiste salame.!");
            System.exit(0);
        } else if (comida[0] == nuevo[0] && comida[1] == nuevo[1]) {
            snake.add(nuevo);
            generarComida();
        } else {
            snake.add(nuevo);
            snake.remove(0);

        }

    }

    public void generarComida() {
        int a = (int) (Math.random() * cant);
        int b = (int) (Math.random() * cant);
        boolean celdaOcupada = false;
        for (int[] sn : snake) {
            if (sn[0] == a && sn[1] == b) {
                celdaOcupada = true;
                generarComida();
                break;
            }
        }
        if (!celdaOcupada) {
            this.comida[0] = a;
            this.comida[1] = b;
        }

    }

    public void cambiarDireccion(char dir) {
        boolean esCorrecta
                = (direccion == 'R' || direccion == 'L' && dir == 'U' || dir == 'D')
                || (direccion == 'U' || direccion == 'D' && dir == 'R' || dir == 'L');
        if (esCorrecta) {
            this.direccionSig = dir;
        }
    }

    public void igualarDireccion() {
        this.direccion = direccionSig;
    }

}
