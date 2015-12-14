import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TankClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame().launchFrame();
	}
	
}


 class MyFrame extends Frame {
	 public static final int GAME_WITDH = 800;
	 public static final int GAME_HEIGTH = 600;
	 Tank myTank = new Tank(50,50);
	 Missile m = new Missile(50,50,Tank.Direction.R);
	 Image offScreenImage = null;
	 
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
		
		public void paint (Graphics g) {
			m.draw(g);
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
		}
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

