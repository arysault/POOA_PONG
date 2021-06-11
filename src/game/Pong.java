package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import models.Ball;
import models.Enemy;
import models.Player;


public class Pong extends Canvas implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	public static int HEIGHT = 120;
	public static int WIDTH = 240;
	public static int SCALE = 3;
	public Thread thread;
	public boolean isRunning;
	public BufferedImage image;
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	public static int teste=0;
	public static int enimy_score=0;
	public static int my_Score=0;
	
	//Inicializando janela
	public Pong() {
		//Cria o tamanho da janela
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		this.addKeyListener(this);
		player = new Player(100, HEIGHT - 10);
		enemy = new Enemy(100, 0);
		ball = new Ball(100, HEIGHT/2 -1);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	public void initFrame() {
		teste++;
		
		
		if(teste>0 & teste<2 ) {
			
			
		
		frame = new JFrame("PLAYER:0/IA:0");
		//frame = new JFrame("Game #"+);
		frame.add(this);
		//Não permite que o usuario altere o tamanho da janela
		frame.setResizable(false);
		//Calcula as dimensões corretamente antes de mostrar
		frame.pack();
		//Diz para a janela ser localizada no centro da tela
		frame.setLocationRelativeTo(null);
		//Permite fechar a janela corretamente sem que o jogo continue rodando
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Permite a visibilidade da janela
		frame.setVisible(true);
		}else {
			//System.out.println("perdemo");
		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
		
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Pong game = new Pong();
		game.start();
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		bs.show();	
		
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}			
		}
		stop();
	}


	@Override
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	


}
