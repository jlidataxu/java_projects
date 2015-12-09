import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TankClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame().launchFrame();
	}
	
}


 class MyFrame extends Frame {
	 int x = 50;
	 int y = 50;
	 void launchFrame () {
		 this.setLocation (100,100);
		 this.setSize(600,400);
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
		 
		}
		
		public void paint (Graphics g) {
			Color c= g.getColor();
			g.setColor(Color.red);
			g.fillOval(x, y, 30, 30);
			g.setColor(c);
			y += 5 ;
			System.out.println("paint");
			
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

