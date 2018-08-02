package frontframe;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
	public MainFrame()
	{
		add(new MainComponent());
		pack();
	}
}

class MainComponent extends JComponent
{
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	
	private static final int DEFFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 600;
	
	public void paintComponent(Graphics g)
	{
		g.drawString("Hello world", MESSAGE_X, MESSAGE_Y);
	}
	
	public Dimension getPreferredSize() { return new Dimension(DEFFAULT_WIDTH, DEFAULT_HEIGHT); }
}
