package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Pong;

public class Player implements KeyListener{
	
	private boolean right, left;
	private int x, y;
	private int width, height;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void tick() {
		if(right) {
			x++;
		} else if(left) {
			x--;
		}
		
		//VERIFICAR COLISÃO NA TELA
		if(x + width > Pong.WIDTH) {
			x = Pong.WIDTH - width;
		}else if(x < 0) {
			x = 0;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
