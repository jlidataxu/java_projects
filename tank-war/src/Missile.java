import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Missile {
	/* Member variables
	 * 1. Postion: x, y and Size: Width and Height
	 * 2. Tank.Direction
	 * 3. Speed
	 * 4. TankClient
	 * 5. Boolean live or not
	 */
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	int x;
	int y;
	Tank.Direction dir;
	boolean live = true;
	TankClient tc;
	
	// Get method to check live or not
	public boolean isLive() {
		return live;
	}
	
	// Constructor
	public Missile(int x, int y, Tank.Direction dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public Missile(int x, int y, Tank.Direction dir, TankClient tc) {
		this(x, y, dir);
		this.tc = tc;
	}
	// Draw method
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.white);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		move();
	}
	
	private void move() {
		if (!live) {
			tc.missiles.remove(this);
			return;
		}
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
		}
		
		if (x < 0 || y < 0 || x > tc.GAME_WITDH || y > tc.GAME_HEIGTH) {
			this.live = false;
		}
		
		
	}
	// deal with collision
	public Rectangle getRect() {
		return new Rectangle(x,y,WIDTH, HEIGHT);
	}
	
	public boolean hitTank(Tank t) {
		if (this.getRect().intersects(t.getRect()) && t.isLive()) {
			t.setLive(false);
			this.live = false;
			return true;
		}
		return false;
	}
}
