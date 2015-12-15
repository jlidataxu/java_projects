import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Tank {
	private static final int XSPEED = 5;
	private static final int YSPEED = 5;
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	private int x;
	private int y;
	TankClient tc;
	
	private boolean bU = false, bD = false, bL = false, bR = false;
	enum Direction {R, RU, RD, L, LU, LD, U, D, STOP}
	private Direction dir = Direction.STOP;
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Tank(int x, int y, TankClient tc) {
		this(x,y);
		this.tc = tc;
	}
	
	void move() {
		switch (dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case R:
			x +=XSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case STOP:
			break;
		}
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		move();
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_RIGHT: 
			bR = true;
			break;
		case KeyEvent.VK_LEFT: 
			bL = true;
			break;
		case KeyEvent.VK_DOWN: 
			bD = true;
			break;
		case KeyEvent.VK_UP: 
			bU = true;
			break;
		}
		
		locateDirection();
	}
	
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_CONTROL:
			tc.m = fire();
			break;
		case KeyEvent.VK_RIGHT: 
			bR = false;
			break;
		case KeyEvent.VK_LEFT: 
			bL = false;
			break;
		case KeyEvent.VK_DOWN: 
			bD = false;
			break;
		case KeyEvent.VK_UP: 
			bU = false;
			break;
		}
		
		locateDirection();
	}
	
	public Missile fire() {
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		
		Missile m = new Missile(x ,y,dir);
		return m;
	}

	public void locateDirection() {
		if (bU && !bD && !bL && !bR) {
			dir = Direction.U;
			
		} else if (!bU && bD && !bL && !bR)  {
			dir = Direction.D;
			
		} else if (!bU && !bD && bL && !bR) {
			dir = Direction.L;
		} else if (!bU && !bD && !bL && bR) {
			dir = Direction.R;
		} else if (bU && !bD && bL && !bR) {
			dir = Direction.LU;
		} else if (bU && !bD && !bL && bR) {
			dir = Direction.RU;
		} else if (!bU && bD && bL && !bR) {
			dir = Direction.LD;
		} else if (!bU && bD && !bL && bR) {
			dir = Direction.RD;
		} else if (!bU && !bD && !bL && !bR) {
			dir = Direction.STOP;
		} 
			
	}
	

}
