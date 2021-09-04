package com.alexismiranda.snakegame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	Color colorFondo = Color.gray;
    private int tam;
    private int cant;
    private int res;

    public Panel(int tamMax, int cant) {
        this.cant = cant;
        this.tam = tamMax / cant;
        this.res = tamMax % cant;
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        grphcs.setColor(colorFondo);
        for (int i = 0; i < cant; i++) {
            for (int j = 0; j < cant; j++) {
                grphcs.fillRect(res / 2 + i * tam, res / 2 + j * tam, tam - 1, tam - 1);
            }
        }
    }

}
