import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/* Tank class 
 * 1.draw tank objects: cirle and pipe
 * 2.movement: direction and speed
 * 3.event: use key to control direction and fire missle
 */
public class Tank {
	/* member variables
	 * 1. Speed: XSPEED, YSPEED
	 * 2. Size: WIDTH, HEIGHT
	 * 3. Position: int x,y
	 * 4. Associated TankClient
	 * 5. Direction: 
	 * 		boolean values, 
	 * 		enum Direction, 
	 * 		ptDirection, 
	 * 		default tank direction
	 * 		default ptDirection
	 */
	//1. SPEED
	private static final int XSPEED = 5;
	private static final int YSPEED = 5;
	
	//2. Size and 3.Location
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	private int x;
	private int y;
	
	//4. Associated TankClient Object
	TankClient tc;
	
	//5. Directions
	//5.1 boolean values which can associate with arrow keys
	private boolean bU = false, bD = false, bL = false, bR = false;
	//5.2 tank direction
	enum Direction {R, RU, RD, L, LU, LD, U, D, STOP}
	private Direction dir = Direction.STOP;
	Direction ptDir = Direction.R;
	
	// Constructor
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Overloaded Constructor 
	public Tank(int x, int y, TankClient tc) {
		this(x,y);
		this.tc = tc;
	}
	/*
	 *  Draw Method:
	 *  1. Draw circle
	 *  2. Draw pt
	 *  3. run move() method to update location of tank object
	 */
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		switch (ptDir) {
		case L:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y+ Tank.HEIGHT/2);
			break;
		case LU:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y);
			break;
		case LD:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y+ Tank.HEIGHT);
			break;
		case R:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y+ Tank.HEIGHT/2);
			break;
		case RU:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y);
			break;
		case RD:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2,  x + Tank.WIDTH , y+ Tank.HEIGHT);
			break;
		case U:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH/2, y);
			break;
		case D:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH/2, y+ Tank.HEIGHT);
			break;
		}
		move();
	}
	
	/*
	 *  move() method: 
	 *  	1. use x,y variables to control and update location of tank
	 *  	2. update pt direction
	 */
	
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
		
		if(this.dir != Direction.STOP) {
			this.ptDir = this.dir;
		}
		
		// prevent tank moving outside of frame
		if(x < 0) x = 0;
		if(y < 30) y = 30;
		if(x + this.WIDTH > tc.GAME_WITDH) x = tc.GAME_WITDH - this.WIDTH;
		if(y + this.HEIGHT > tc.GAME_HEIGTH) y = tc.GAME_HEIGTH - this.HEIGHT;
	}
	
	
	/* Event and Action
	 * keyPressed: 
	 * 		1.set corresponding boolean value to true
	 * 		2.update tank direction
	 * keyReleased: 
	 * 		1. set corresponding boolean value to false
	 * 		2. run fire() method
	 * 		3. update tank direction
	 */
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
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_CONTROL:
			fire();
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
	// fire method to generate missile object
	public Missile fire() {
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		
		Missile m = new Missile(x ,y,ptDir, this.tc);
		tc.missiles.add(m);
		return m;
	}
	
	// locationDirection() method to update Direction
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
