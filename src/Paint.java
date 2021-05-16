//Imports all necessary classes to utilize different functions
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//The Paint class used for implementing paint graphics to the program
//Allows for easy manipulation and animation to simulate the concept

public class Paint extends JPanel {
	private static final long serialVersionUID = 7444713863850890570L; //Used for paint graphics
	
	//Creates colours to be used for graphics
	private final Color bg = new Color(199, 237, 249); //Colour of background 
	private final Color reg = new Color(39, 170, 225); //Colour of Objects
	
	//Creates fonts to be used for text
	private Font heading = new Font("Calibri", Font.BOLD, 60);
	private Font text = new Font("Calibri", Font.BOLD, 20);
	
	//Creates timers to be used for animation
	private Timer timer = new Timer();
	private Timer timer2 = new Timer();
	
	//Numberformat to display acceleration with up to 3 decimal points
	private NumberFormat number = NumberFormat.getNumberInstance();

	//Creates variables for location of the moving object
	public double x, y, i = 0, j = 0, c1 = 0, c2 = 0, vx, vy;
	private double accelX, accelY; //used to take value from calculation class
	
	private int delay = 50;

	//Imports image and converts to imageicon to be used for JComponents
	public ImageIcon skipIcon = new ImageIcon("res/skip.png");
	
	//Creates JLabel for displaying text
	public JLabel accel = new JLabel();
	
	//Creates button
	public JButton skip = new JButton(skipIcon);
	 
	/**
	 * constructor
	 * pre: none
	 * post: A Paint object created. All JComponents are initialized
	 */
	public Paint() {
		//Panel Setting
		this.setLayout(null); //Absolute Positioning
		
		//Button Settings
		skip.setBorder (null); //Removes border
		skip.setBounds (680,40,160,83); //Sets size and location (absolute positioning)
		skip.addActionListener(new Skip()); //Adds actionlistener for function
		
		//Text Label Settings
		accel.setBorder(null);
		accel.setFont(text); //Sets the font for text
		accel.setBounds(20,120,400,30);
		
		//Adds JComponents
		this.add(skip);
		this.add(accel);
	}

	/**
	 * Resets all values
	 * pre: none
	 * post: Values are reset
	 */
	public void reset() {
		i = 0;
		j = 0;
		c1 = 0;
		c2 = 0;
		x = 0;
		y = 0;
		vy = 0;
		vx = 0;
		accelY = 0;
		accelX = 0;
	}
	
	/**
	 * Implements all paint graphics
	 * pre: none
	 * post: Paint graphics are implemented
	 */
	public void paintComponent(Graphics g) {
		//Preparation needed for graphics
		super.paintComponent(g);	
		Graphics2D g2 = (Graphics2D) g.create();
		
		g2.setColor(bg); //Sets the colour
		//Draws a rectangle to cover the entire panel (acts as background)
		g2.fillRect(0, 0, 900, 600);
		
		g2.setColor(Color.black); //Sets the colour
		g2.setFont(heading); //Sets the font
		g2.drawString("Simulating... ", 120, 100); //Draws the heading
		
		//Checks the source, Inclined Planes vs Projectile Motion
		if (Gui.source == 1) { //Inclined Planes
			//Sets the acceleration text
			accel.setText("Acceleration: " + number.format(Calculation.accelP)+ " m/s^2");
			
			g2.setColor(reg); //Sets the colour
			
			//Checks ratios to determine size division
			if (Calculation.heightP / 400 > Calculation.lengthP / 700) {
				//Sets location of object
				x = 95;
				y = 150;
				//Sets the acceleration and follows ratio
				accelX = Calculation.accelX * 400 / Calculation.heightP;
				accelY = Calculation.accelY * 400 / Calculation.heightP;
				
				//Uses double drawing for precision
				//Draws 3 lines to form a ramp, follows ratios
				g2.draw(new Line2D.Double(100, 160, 100, 560));
				g2.draw(new Line2D.Double(100, 560,
						100 + (int) (400 / Calculation.heightP * Calculation.lengthP), 560));
				g2.draw(new Line2D.Double(100, 160,
						100 + (int) (400 / Calculation.heightP * Calculation.lengthP), 560));
				
				//Prevents object from moving infinitely and sets location
				if (y+(c2+j)/20>550){
					g2.fill(new Ellipse2D.Double( 400 / Calculation.heightP * Calculation.lengthP + 95, 550, 10, 10));
					timer.cancel(); //stops the animation
				} else {
				//Redraws the object and follows its movement
				g2.fill(new Ellipse2D.Double(x+(c1+i)/20, y +(c2+ j)/20, 10, 10));
				}
				
				//Increases such that it's exponentially moving, demonstrates acceleration
				c1+=i;
				c2+=j;

			} else { //other ratio
				//Sets location of object
				x = 95;
				y = 550 - 700 / Calculation.lengthP * Calculation.heightP;
				//Sets the acceleration and follows ratio
				accelX = Calculation.accelX * 700 / Calculation.lengthP;
				accelY = Calculation.accelY * 700 / Calculation.lengthP;
				
				//Uses double drawing for precision
				//Draws 3 lines to form a ramp, follows ratios
				g2.draw(new Line2D.Double(100, 560 - (int) (700 / Calculation.lengthP * Calculation.heightP), 100, 560));
				g2.draw(new Line2D.Double(100, 560, 800, 560));
				g2.draw(new Line2D.Double(100, 560 - (int) (700 / Calculation.lengthP * Calculation.heightP), 800, 560));
				
				//Prevents object from moving infinitely and sets location
				if (y+(c2+j)/20>550){
					g2.fill(new Ellipse2D.Double(795, 550, 10, 10));
					timer.cancel(); //stops the animation
				} else {
					//Redraws the object and follows its movement
					g2.fill(new Ellipse2D.Double(x+(c1+i)/20, y+(c2+j)/20, 10, 10));
				}
				
				//Increases such that it's exponentially moving, demonstrates acceleration
				c1+=i;
				c2+=j;
			}
		} else if (Gui.source == 2) { //Projectile Motion
			//Sets the acceleration text
			accel.setText("Acceleration: " + -9.8 + " m/s^2");
			
			g2.setColor(reg); //Sets the colour
			
			//Checks ratios to determine size division
			if (Calculation.maxPr / 400 > Calculation.distancePr / 700) { 
				//Sets location of object
				x = 95;
				y = 555-Calculation.heightPr * 400 / Calculation.maxPr ;
				//Sets the acceleration and follows ratio
				vx = Calculation.velocityX * 400 / Calculation.maxPr;
				vy = Calculation.velocityY * 400 / Calculation.maxPr;
				accelY = Calculation.accelY * 400 / Calculation.maxPr;
				
				//Uses double drawing for precision
				//Draws ground and platform
				g2.fill(new Rectangle2D.Double(0,560,900,40));
				g2.fill(new Rectangle2D.Double(80, 560 - Calculation.heightPr * 400 / Calculation.maxPr, 20, Calculation.heightPr * 400 / Calculation.maxPr));
				
				//Prevents object from moving infinitely and sets location
				if (y+(c2+j)/20>550){
					g2.fill(new Ellipse2D.Double(x+(i)/20, 550, 10, 10));
					timer2.cancel(); //stops the animation
				} else {
					g2.fill(new Ellipse2D.Double(x+(i)/20, y+(c2+j)/20, 10, 10));
				}
				
				//Increases such that it's exponentially moving, demonstrates acceleration
				c2+=j;
				

			} else {
				//Sets location of object
				x = 95;
				y = 550 -  Calculation.heightPr * 700 / Calculation.distancePr;
				//Sets the acceleration and follows ratio
				vx = Calculation.velocityX * 700 / Calculation.distancePr;
				vy = Calculation.velocityY * 700 / Calculation.distancePr;
				accelY = Calculation.accelY * 700 / Calculation.distancePr;
				
				//Uses double drawing for precision
				//Draws ground and platform
				g2.fill(new Rectangle2D.Double(0,560,900,40));
				g2.fill(new Rectangle2D.Double(80, 560 - Calculation.heightPr * 700 / Calculation.distancePr , 20, Calculation.heightPr * 700 / Calculation.distancePr));
				
				//Prevents object from moving infinitely and sets location
				if (y+(c2+j)/20>550){
					g2.fill(new Ellipse2D.Double(x+(i)/20, 550, 10, 10));
					timer2.cancel(); //stops the animation
				} else {
					g2.fill(new Ellipse2D.Double(x+(i)/20, y+(c2+j)/20, 10, 10));
				}
				
				//Increases such that it's exponentially moving, demonstrates acceleration
				c2+=j;
				
			}
		}
	}

	/**
	 * Initiates the timer for animation of inclined planes
	 * pre: none
	 * post: Animation has begun
	 */
	public void incline1() {
		//redraws the panel
		repaint();
		//Sets the values of variables
		i= 0;
		j = 0;
		c1 = 0;
		c2 = 0;
		//Creates the task
		TimerTask task = new TimerTask() {
			public void run() {
				i += accelX;
				j += accelY;
				repaint();
			}
		};
		//Assigns task
		timer = new Timer();
		timer.schedule(task, 0, delay);
	}
	
	
	/**
	 * Initiates the timer for animation of projectile motion
	 * pre: none
	 * post: Animation has begun
	 */
	public void project1() {
		//redraws the panel
		repaint();
		//Sets the values of variables 
		//Size Ratio Comparison
		if (Calculation.maxPr / 400 > Calculation.distancePr / 700) {
			vy = Calculation.velocityY * 400 / Calculation.maxPr;
		} else {
			vy = Calculation.velocityY * 700 / Calculation.distancePr;
		}
		i = 0;
		j= -vy;
		c2 = 0;
		//Creates the task
		TimerTask task2 = new TimerTask() {
			public void run() {
				i += vx*1.2;
				j += (accelY * 0.2);
				repaint();
			}
		};
		//Assigns task
		timer2 = new Timer();
		timer2.schedule(task2, 0, delay);
	}
	
	
	//Skip Action Listener
	class Skip implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timer.cancel(); //Cancels the last scheduled task
			Main.visual.displayPlanes(); //Calls upon a method to show the display panels
		}
	}
}
