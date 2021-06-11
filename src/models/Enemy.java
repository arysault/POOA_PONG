package models;

import java.awt.Color;
import java.awt.Graphics;

import game.Pong;

public class Enemy {
	
	private double x, y;
	private int width, height;

	
	public Enemy(double x, double y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void tick() {
		x += (Pong.ball.getX() - x - 6) * 0.05;
		//VERIFICAR COLISÃO NA TELA
		if(x + width > Pong.WIDTH) {
			x = Pong.WIDTH - width;
		}else if(x < 0) {
			x = 0;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
