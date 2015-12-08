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
		 this.setVisible (true);
		 this.setResizable (false);

		}
		
		public void paint (Graphics g) {
			Color c= g.getColor();
			g.setColor(Color.red);
			g.fillOval(50, 50, 30, 30);
			g.setColor(c);
			System.out.println("paint");
			
		}
}

