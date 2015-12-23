import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


 class TankClient extends Frame {
	 /* member variables
	  * constants: Game Width & Game Height
	  * object instance: tank, missile
	  * misc: offscreen image
	  */
	 public static final int GAME_WITDH = 800;
	 public static final int GAME_HEIGTH = 600;
	 Tank myTank = new Tank(50,50,this);
	 List<Missile> missiles = new ArrayList<Missile> ();
	 Image offScreenImage = null;
	 
	 /* lanuchFrame method
	  * 1) initialize frame parameters: size, location, background color, visible, resizable
	  * 2) add event listener: a. window listener to close window b.key listener
	  * 3) new a thread to repaint in every 100 mseconds
	  *
	  */
	 void launchFrame () {
		 this.setLocation (100,100);
		 this.setSize(GAME_WITDH,GAME_HEIGTH);
		 this.setBackground (Color.GRAY);
		 this.addWindowListener (new WindowAdapter () {
					 public void windowClosing(WindowEvent e) {
						 setVisible(false);
						 System.exit(0);
			 }

		 });
		 new Thread(new PaintThread()).start();
		 this.setVisible (true);
		 this.setResizable (false);
		 this.addKeyListener(new KeyMonitor());
		}
		
	 	/* image painting
	 	 * 1) paint method: paint tank and missile
	 	 * 2) update method: update the whole image
	 	 */
		public void paint (Graphics g) {
			
			for(int i = 0; i < missiles.size(); i++) {
				Missile m = missiles.get(i);
				/*if (!m.isLive()) {
					missiles.remove(m);
				} else {
					m.draw(g);
				}*/
				m.draw(g);
			}
			myTank.draw(g);
		}
		
		public void update(Graphics g) {
			if (offScreenImage == null) {
				offScreenImage = this.createImage(GAME_WITDH, GAME_HEIGTH);
			}
			Graphics gOffScreen = offScreenImage.getGraphics();
			gOffScreen.getColor();
			gOffScreen.setColor(Color.GRAY);
			gOffScreen.fillRect(0, 0, GAME_WITDH, GAME_HEIGTH);
			paint(gOffScreen);
			g.drawImage(offScreenImage, 0, 0, null);
			g.drawString("missiles count " + missiles.size(), 10, 50);
		}
		
		public static void main(String[] args) {
			new TankClient().launchFrame();
		}
		/* private classes
		 * 1) KeyMonitor: use keyReleased and KeyPressed to control tank
		 * 2) PaintThread: implement Runnable to repaint as a thread
		 */
		private class KeyMonitor extends KeyAdapter {

			@Override
			public void keyReleased(KeyEvent e) {
				myTank.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				myTank.keyPressed(e);
			}
			
			
		}
		private class PaintThread implements Runnable {

			@Override
			public void run() {
				while(true) {
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}
}

