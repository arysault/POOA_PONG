package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.Pong;

public class Ball {
	
	public double x, y;
	public int width, height;
	public double dx, dy;
	public double speed = 1.0; 
	public static int enimy_score=0;
	public static int my_Score=0;
	
	public Ball(double x, double y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		
		int angle = new Random().nextInt(120 - 45) + 46;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		//Logica de colisão da bola eixo X
		if(x+(dx*speed) + width >= Pong.WIDTH) {
			dx *= -1;
		} else if(x+(dx*speed) < 0 ) {
			dx *= -1;
		}
		//Logica de colisão da bola eixo Y
		if(y >= Pong.HEIGHT) {
			
			enimy_score++;
			Pong.frame.setTitle("PLAYER:"+my_Score+"/IA:"+enimy_score);
			new Pong();
			
			return;
		} else if(y < 0) {
			
			my_Score++;
			Pong.frame.setTitle("PLAYER:"+my_Score+"/IA:"+enimy_score);
			new Pong();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x + (dx * speed)), (int)(y + (dy * speed)), width, height);
		Rectangle boundsPlayer = new Rectangle(Pong.player.getX(), Pong.player.getY(), Pong.player.getWidth(), Pong.player.getHeight());
		Rectangle boundsEnemy = new Rectangle((int)Pong.enemy.getX(), (int)Pong.enemy.getY(), Pong.enemy.getWidth(), Pong.enemy.getHeight());
		
		//colisão com inimigo e player
		if(bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(120 - 45) + 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy > 0) {
				dy *= -1;
			}
		} else if(bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(120 - 45) + 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy < 0) {
				dy *= -1;
			}
		}
		
		x += dx * speed;
		y += dy * speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, width, height);
	}

}