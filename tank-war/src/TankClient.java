import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TankClient extends Frame{
	
	public void launchFrame() {
		this.setLocation(400, 300);
		this.setSize(800, 600);
		this.setTitle("Tank War");
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.setResizable(false);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankClient tc = new TankClient();
		tc.launchFrame();
	}

}
