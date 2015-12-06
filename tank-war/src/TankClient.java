import java.awt.Frame;


public class TankClient extends Frame{
	
	public void launchFrame() {
		this.setLocation(400, 300);
		this.setSize(800, 600);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankClient tc = new TankClient();
		tc.launchFrame();
	}

}
