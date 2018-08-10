package frontframe;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.awt.event.*;
import java.util.*;

public class MainFrame extends JFrame
{
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 600;
	private static final int TEXTAREA_ROWS = 8;
	private static final int TEXTAREA_COLUMNS = 20;
	private static final int DEFAULT_SIZE = 12;
	
	private JPanel mainPanel;
	private JPanel secondPanel;
	private JPanel westPanel;
	private ButtonGroup group;
	private JLabel label;
	JComboBox<String> faceCombo;
	
	public MainFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		/*JButton yellowButton = new JButton("Yellow");
		JButton blueButton = new JButton("Blue");
		JButton redButton = new JButton("Red");*/
		
		mainPanel = new JPanel();
		secondPanel = new JPanel();
		/*mainPanel.add(yellowButton);
		mainPanel.add(blueButton);
		mainPanel.add(redButton);*/
		mainPanel.setLayout(new GridLayout(2, 2));
		final JTextField textField = new JTextField();
		final JPasswordField passwordField = new JPasswordField();
		
		mainPanel.add(new JLabel("User name: ", SwingConstants.RIGHT));
		mainPanel.add(textField);
		mainPanel.add(new JLabel("Password: ", SwingConstants.RIGHT));
		mainPanel.add(passwordField);
		add(mainPanel, BorderLayout.NORTH);
		
		makeButton("yellow", Color.YELLOW);
		makeButton("blue", Color.BLUE);
		makeButton("red", Color.RED);
		
		/*ColorAction yellowAction  = new ColorAction(Color.YELLOW);
		ColorAction blueAction = new ColorAction(Color.BLUE);
		ColorAction redAction = new ColorAction(Color.RED);
		
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);*/
		add(new MainComponent(), BorderLayout.CENTER);
		
		add(secondPanel, BorderLayout.SOUTH);
		
		JPanel eastPanel = new JPanel();
		
		final JTextArea textArea= new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(textArea);
		eastPanel.add(scrollPane);
		
		JButton insertButton = new JButton("Insert");
		eastPanel.add(insertButton);
		insertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("User name: " + textField.getText() + "  password: " + new String(passwordField.getPassword()) + "\n");
				
			}
		});
		add(eastPanel, BorderLayout.EAST);
		
		westPanel = new JPanel();
		westPanel.setSize(new Dimension(100, 100));
		group = new ButtonGroup();
		label = new JLabel("Lazy makes good success");
		label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
		westPanel.setLayout(new GridLayout(5, 1));
		westPanel.add(label);
		
		addRadioButton("Small", 8);
		addRadioButton("Medium", 12);
		addRadioButton("Large", 16);
		addRadioButton("Extra large", 20);
		
		faceCombo = new JComboBox<>();
		faceCombo.addItem("Serif");
		faceCombo.addItem("SansSerif");
		faceCombo.addItem("Monospaced");
		faceCombo.addItem("Dialog");
		faceCombo.addItem("DialogInput");
		
		faceCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				label.setFont(new Font(faceCombo.getItemAt(faceCombo.getSelectedIndex()), Font.PLAIN, DEFAULT_SIZE));
				
			}
		});
		westPanel.add(faceCombo);
		add(westPanel, BorderLayout.WEST);
		
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
		secondPanel.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setBackground(backgroundColor);				
			}
		});
	}
	
	public void addRadioButton(String name, final int size)
	{
		boolean selected = size == DEFAULT_SIZE;
		JRadioButton button = new JRadioButton(name, selected);
		group.add(button);
		westPanel.add(button);
		
		ActionListener listener = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				label.setFont(new Font("Serif", Font.PLAIN, size));
			}
		};
		button.addActionListener(listener);
	}
}

class MainComponent extends JComponent
{
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	
	private static final int DEFFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	
	private Image image;
	
	private static final int SIDELENGTH = 10;
	private ArrayList<Rectangle2D> squares;
	private Rectangle2D current;
	
	public MainComponent()
	{
		image = new ImageIcon("dog.jpg").getImage();
		
		squares = new ArrayList<>();
		current = null;
		
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
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
		
		g2.drawString(message, (int)x, (int)baseY - 250);
		g2.setPaint(Color.LIGHT_GRAY);
		
		g2.draw(new Line2D.Double(x, baseY - 250, x + bounds.getWidth(), baseY - 250));
		Rectangle2D rect2d = new Rectangle2D.Double(x, y - 250, bounds.getWidth(), bounds.getHeight());
		g2.draw(rect2d);
		
		for(Rectangle2D r: squares)
			g2.draw(r);
		
		if(image == null) return;
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		g2.drawImage(image, 250, 150, imageWidth, imageHeight, null);
	}
	
	/*
	 * finds the first square containng a point.
	 * @param a point
	 * @return the first square the contains p
	 */
	public Rectangle2D find(Point2D p)
	{
		for(Rectangle2D r: squares)
		{
			if(r.contains(p)) return r;
		}
		return null;
	}
	
	/*
	 * Add a square to the collection.
	 * @param p the center of the square
	 */
	public void add(Point2D p)
	{
		double x = p.getX();
		double y = p.getY();
		
		current = new Rectangle2D.Double(x - SIDELENGTH/2, y - SIDELENGTH/2, SIDELENGTH, SIDELENGTH);
		squares.add(current);
		repaint();
	}
	/*
	 * remove a sqaure from the collection
	 * @param s the square to remove
	 */
	public void remove(Rectangle2D s)
	{
		if(s == null) return;
		if(s == current) current = null;
		squares.remove(s);
		repaint();
	}
	
	private class MouseHandler extends MouseAdapter
	{
		public void mousePressed(MouseEvent event)
		{
			current = find(event.getPoint());
			if(current != null) add(event.getPoint());
		}
		
		public void mouseClicked(MouseEvent event)
		{
			current = find(event.getPoint());
			if(current != null && event.getClickCount() >= 2) remove(current);
		}
	}
	
	private class  MouseMotionHandler implements MouseMotionListener
	{
		public void mouseMoved(MouseEvent event)
		{
			if(find(event.getPoint()) == null) setCursor(Cursor.getDefaultCursor());
			else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		public void mouseDragged(MouseEvent event)
		{
			if(current != null)
			{
				int x = event.getX();
				int y = event.getY();
				
				current.setFrame(x- SIDELENGTH/2, y - SIDELENGTH/2, SIDELENGTH, SIDELENGTH);
				repaint();
			}
		}
	}
	
	public Dimension getPreferredSize() { return new Dimension(DEFFAULT_WIDTH, DEFAULT_HEIGHT); }
}
