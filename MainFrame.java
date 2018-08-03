package frontframe;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;

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
	
	private static final int DEFFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 800;
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		double leftX = 300;
		double topY = 100;
		double width = 200;
		double height = 150;
		
		Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
		g2.draw(rect);
		
		Ellipse2D ellipse = new Ellipse2D.Double();
		ellipse.setFrame(rect);
		g2.draw(ellipse);
		
		g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));
		
		double centerX = rect.getCenterX();
		double centerY = rect.getCenterY();
		double radius = 150;
		
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
		g2.draw(circle);
		
		String message = "Hello world";
		Font font = new Font("Serif", Font.BOLD, 36);
		g2.setFont(font);
		
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(message, context);
		
		//set(x, y) top left corner of text
		double x = (getWidth() - bounds.getWidth())/2;
		double y = (getHeight() - bounds.getHeight())/2;
		
		double ascent = -bounds.getY();
		double baseY = y + ascent;
		
		g2.drawString(message, (int)x, (int)baseY);
		g2.setPaint(Color.LIGHT_GRAY);
		
		g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));
		Rectangle2D rect2d = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
		g2.draw(rect2d);
	}
	
	public Dimension getPreferredSize() { return new Dimension(DEFFAULT_WIDTH, DEFAULT_HEIGHT); }
}
