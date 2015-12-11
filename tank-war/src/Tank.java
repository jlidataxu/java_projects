import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Tank {
	private static final int XSPEED = 5;
	private static final int YSPEED = 5;
	private int x;
	private int y;
	
	private boolean bU = false, bD = false, bL = false, bR = false;
	enum Direction {R, RU, RD, L, LU, LD, U, D, STOP}
	private Direction dir = Direction.STOP;
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
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
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		locateDirection();
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
