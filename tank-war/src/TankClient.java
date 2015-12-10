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
	 int x = 50;
	 int y = 50;
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
			Color c= g.getColor();
			g.setColor(Color.red);
			g.fillOval(x, y, 30, 30);
			g.setColor(c);
			//y += 5 ;
			//System.out.println("paint");
			
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
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				switch(key) {
				case KeyEvent.VK_RIGHT: 
					x += 5;
					break;
				case KeyEvent.VK_LEFT: 
					x -= 5;
					break;
				case KeyEvent.VK_DOWN: 
					y += 5;
					break;
				case KeyEvent.VK_UP: 
					y -= 5;
					break;
				}
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

