package frontframe;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;

public class MainFrame extends JFrame
{
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 600;
	
	private JPanel mainPanel;
	public MainFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		/*JButton yellowButton = new JButton("Yellow");
		JButton blueButton = new JButton("Blue");
		JButton redButton = new JButton("Red");*/
		
		mainPanel = new JPanel();
		
		/*mainPanel.add(yellowButton);
		mainPanel.add(blueButton);
		mainPanel.add(redButton);*/
		makeButton("yellow", Color.YELLOW);
		makeButton("blue", Color.BLUE);
		makeButton("red", Color.RED);
		add(mainPanel);
		
		/*ColorAction yellowAction  = new ColorAction(Color.YELLOW);
		ColorAction blueAction = new ColorAction(Color.BLUE);
		ColorAction redAction = new ColorAction(Color.RED);
		
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);*/
		mainPanel.add(new MainComponent());	
		pack();
	}
	
	/*private class ColorAction implements ActionListener
	{
		private Color backgroundColor;
		
		public ColorAction(Color c)
		{
			backgroundColor = c;
		}
		
		public void actionPerformed(ActionEvent event)
		{
			mainPanel.setBackground(backgroundColor);
		}
	}*/
	public void makeButton(String name, final Color backgroundColor)
	{
		JButton button  = new JButton(name);
		mainPanel.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setBackground(backgroundColor);				
			}
		});
	}
}

class MainComponent extends JComponent
{
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	
	private static final int DEFFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	
	private Image image;
	
	public MainComponent()
	{
		image = new ImageIcon("./flower.gif").getImage();
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		double leftX = 100;
		double topY = 50;
		double width = 100;
		double height = 100;
		
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
		
		if(image == null) return;
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		g2.drawImage(image, 100, 100, imageWidth, imageHeight, null);
	}
	
	public Dimension getPreferredSize() { return new Dimension(DEFFAULT_WIDTH, DEFAULT_HEIGHT); }
}
