//Imports all necessary classes to utilize different functions
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


//The GUI class used for implementing graphic components to the program
//Note: Comments are oriented to prevent excessive repetition
//(specific methods are commented once per block of comments, first comment applies to following methods that are identical)
public class Gui {
	//Instantiates objects of additions to the GUI class
	private Paint panel = new Paint(); //Class controlling the simulation/animation
	private Calculation calc = new Calculation(); //Class controlling all values and calculations

	//Creates strings to be used for card layout (moves between panels)
	private final String card1 = "Title";
	private final String card2 = "Help";
	private final String card3 = "Select";
	private final String card4 = "Plane1";
	private final String card5 = "Project1";
	private final String card6 = "Calculate";
	private final String card7 = "DisplayPlanes";
	private final String card8 = "DisplayProjectile";
	
	//Number format to be used for displaying calculated values, allows up to 3 decimal places
	private NumberFormat number = NumberFormat.getNumberInstance();

	//Colour used for the background and overall theme of the program
	private final Color bg = new Color(199, 237, 249);

	//Creates the frame that contains all GUI and graphics
	public JFrame frame = new JFrame("Newton's Vision");
	
	//Card Layout, allows to swap between panels (more efficient than setting JComponents visible)
	public CardLayout layout = new CardLayout(); // Layout swaps between cards

	//Imports all images in the resource folder and converts to ImageIcons that can be used
	//on the JComponents
	public ImageIcon titleIcon = new ImageIcon("res/title.png");
	public ImageIcon beginBIcon = new ImageIcon("res/begin.png");
	public ImageIcon helpBIcon = new ImageIcon("res/help.png");
	public ImageIcon helpIcon = new ImageIcon("res/help2.png");
	public ImageIcon backBIcon = new ImageIcon("res/back.png");
	public ImageIcon selectIcon = new ImageIcon("res/select.png");
	public ImageIcon inclineBIcon = new ImageIcon("res/inclined.png");
	public ImageIcon projectBIcon = new ImageIcon("res/project.png");
	public ImageIcon planesIcon = new ImageIcon("res/planes1.png");
	public ImageIcon projectIcon = new ImageIcon("res/project1.png");
	public ImageIcon calculateBIcon = new ImageIcon("res/calc.png");
	public ImageIcon clearBIcon = new ImageIcon("res/clear.png");
	public ImageIcon menuBIcon = new ImageIcon("res/menu.png");
	public ImageIcon display1Icon = new ImageIcon("res/display1.png");
	public ImageIcon display2Icon = new ImageIcon("res/display2.png");

	//Creates JPanels for displaying different screens of the application
	public JPanel screens = new JPanel(); //The main panel that consists of the card layout
	//Different screens 
	public JPanel title = new JPanel(); 
	public JPanel helpSc = new JPanel();
	public JPanel selectSc = new JPanel();
	public JPanel planesSc = new JPanel();
	public JPanel planesDSc = new JPanel();
	public JPanel projectSc = new JPanel();
	public JPanel projectDSc = new JPanel();

	//Creates Buttons for transitions and actions on the program
	public JButton beginB = new JButton(beginBIcon);
	public JButton helpB = new JButton(helpBIcon);
	public JButton backB = new JButton(backBIcon);
	public JButton inclineB = new JButton(inclineBIcon);
	public JButton projectileB = new JButton(projectBIcon);
	public JButton calculateB = new JButton(calculateBIcon);
	public JButton calculate2B = new JButton(calculateBIcon);
	public JButton clearB = new JButton(clearBIcon);
	public JButton clear2B = new JButton(clearBIcon);
	public JButton menuB = new JButton(menuBIcon);
	public JButton menu2B = new JButton(menuBIcon);

	//Creates font to be used for headings and text displayed
	public Font heading = new Font("Calibri", Font.BOLD, 40);
	public Font text = new Font("Calibri", Font.BOLD, 30);
	public Font displayF = new Font("Calibri", Font.BOLD, 50);
	public Font small = new Font("Calibri", Font.BOLD, 15);

	//****Creates Labels****
	//Labels for background images of each screen
	public JLabel logo = new JLabel(titleIcon);
	public JLabel help = new JLabel(helpIcon);
	public JLabel select = new JLabel(selectIcon);
	public JLabel planes = new JLabel(planesIcon);
	public JLabel project = new JLabel(projectIcon);
	public JLabel display1 = new JLabel(display1Icon);
	public JLabel display2 = new JLabel(display2Icon);
	//Labels for displaying all text for the Planes Display Screen
	public JLabel angleL = new JLabel();
	public JLabel heightL = new JLabel();
	public JLabel frictionL = new JLabel();
	public JLabel massL = new JLabel();
	public JLabel length = new JLabel();
	public JLabel acceleration = new JLabel();
	public JLabel time = new JLabel();
	//Labels for displaying all text for the Projectiles Display Screen
	public JLabel angle2L = new JLabel();
	public JLabel height2L = new JLabel();
	public JLabel velocityL = new JLabel();
	public JLabel distance = new JLabel();
	public JLabel max = new JLabel();
	public JLabel time2 = new JLabel();
	
	//Creates Textfields to used for input values from the user and analyzing data
	//Planes
	public JTextField angle = new JTextField(10);
	public JTextField friction = new JTextField(10);
	public JTextField mass = new JTextField(10);
	public JTextField height = new JTextField(10);
	//Projectiles
	public JTextField angle2 = new JTextField(10);
	public JTextField velocity = new JTextField(10);
	public JTextField height2 = new JTextField(10);
	
	//Creates strings for conversion from JTextfields into usable data
	//Planes
	public String angleS;
	public String frictionS;
	public String massS;
	public String heightS;
	//Projectiles
	public String angle2S;
	public String velocityS;
	public String height2S;

	//Creates Doubles for storing values after parsing the strings
	//Planes
	public double angleD;
	public double frictionD;
	public double massD;
	public double heightD;
	//Projectiles
	public double angle2D;
	public double velocityD;
	public double height2D;

	public static int source; //Value for keeping track of planes vs projectiles

	
	/**
	 * constructor
	 * pre: none
	 * post: A Gui object created. All graphic components are initialized
	 */
	public Gui() {
		//Calls upon methods initialize all components
		frameInit();
		titleInit();
		helpInit();
		selectInit();
		inputInit();
		displayInit();
		layoutInit();
		
		layout.show(screens, card1); //Selects the title screen to be shown
	}

	/**
	 * Initiates the program by setting the Frame visible
	 * pre: none
	 * post: The Frame is visible, program is activated
	 */
	public void start() {
		frame.setVisible(true);
	}

	/**
	 * Initializes the frame
	 * pre: none
	 * post: Frame is initialized
	 */
	public void frameInit() {
		//Frame Adjustments
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets the close operation
		frame.setLocation(200, 200); //Picks the location of the frame
		frame.setSize(new Dimension(900, 600)); //Determines the size of the frame
		frame.setResizable(false); //Does not allow the user to change the resolution of the frame
		frame.getContentPane().add(screens); //Adds the main card layout screen to the frame
	}

	/**
	 * Initializes the title screen components
	 * pre: none
	 * post: All components of the title screen are initialized.
	 */
	public void titleInit() {
		//Panel Settings
		title.setLayout(null); //Absolute Positioning
		title.setOpaque(true); //Allows for background
		title.setBackground(bg); //Changes the colour of the background

		//Background Image Settings
		logo.setBorder(null); //Removes borders
		logo.setBounds(0, 0, 900, 600); //Sets the size and location for absolute positioning

		//Button Settings
		beginB.setBorder(null);
		beginB.setBounds(339, 470, 224, 94);
		beginB.addActionListener(new Begin()); //Attaches action listeners to the button for usability

		helpB.setBorder(null);
		helpB.setBounds(128, 480, 158, 83);
		helpB.addActionListener(new Help());

		//Adds components to the panel
		title.add(logo);  
		title.add(beginB);
		title.add(helpB);
	}

	/**
	 * Initializes the selection screen components
	 * pre: none
	 * post: All components of the selection screen are initialized.
	 */
	public void selectInit() {
		//Panel Settings
		selectSc.setLayout(null); //Absolute Positioning
		selectSc.setOpaque(true); //Allows for background
		selectSc.setBackground(bg); //Changes the colour of the background

		//Background Image Settings
		select.setBorder(null); //Removes borders
		select.setBounds(0, 0, 900, 600); //Sets the size and location for absolute positioning

		//Button Settings
		inclineB.setBorder(null);
		inclineB.setBounds(90, 200, 286, 286);
		inclineB.addActionListener(new Incline()); //Attaches action listeners to the button for usability

		projectileB.setBorder(null);
		projectileB.setBounds(515, 200, 286, 286);
		projectileB.addActionListener(new Projectile());

		//Adds components to the panel
		selectSc.add(select);
		selectSc.add(inclineB);
		selectSc.add(projectileB);
	}

	/**
	 * Initializes the help screen components
	 * pre: none
	 * post: All components of the help screen are initialized.
	 */
	public void helpInit() {
		//Panel Settings
		helpSc.setLayout(null); //Absolute Positioning
		helpSc.setOpaque(true); //Allows for background
		helpSc.setBackground(bg); //Changes the colour of the background

		//Background Image Settings
		help.setBorder(null); //Removes borders
		help.setBounds(0, 0, 900, 450); //Sets the size and location for absolute positioning

		//Button Settings
		backB.setBorder(null);
		backB.setBounds(364, 470, 158, 83);
		backB.addActionListener(new Back()); //Attaches action listeners to the button for usability

		//Adds components to the panel
		helpSc.add(help);
		helpSc.add(backB);
	}

	/**
	 * Initializes the input screen components
	 * pre: none
	 * post: All components of the input screen are initialized.
	 */
	public void inputInit() {

		//**** INCLINED PLANES ****//
		//Panel Settings
		planesSc.setLayout(null); //Absolute Positioning
		planesSc.setOpaque(true); //Allows for background
		planesSc.setBackground(bg); //Changes the colour of the background
		
		//Background Image Settings
		planes.setBorder(null); //Removes borders
		planes.setBounds(0, 0, 900, 600); //Sets the size and location for absolute positioning
		
		//Button Settings
		calculateB.setBorder(null);
		calculateB.setBounds(290, 440, 328, 114);
		calculateB.addActionListener(new Calculate()); //Attaches action listeners to the button for usability

		clearB.setBorder(null);
		clearB.setBounds(100, 460, 158, 77);
		clearB.addActionListener(new Clear());

		//Textfield Settings
		angle.setBorder(null);
		angle.setFont(text); //Sets the font to be used
		angle.setBounds(400, 155, 200, 40);

		friction.setBorder(null);
		friction.setFont(text);
		friction.setBounds(430, 220, 170, 40);

		mass.setBorder(null);
		mass.setFont(text);
		mass.setBounds(390, 285, 210, 40);

		height.setBorder(null);
		height.setFont(text);
		height.setBounds(510, 350, 90, 40);

		//Adds components to the panel
		planesSc.add(planes);
		planesSc.add(calculateB);
		planesSc.add(clearB);
		planesSc.add(angle);
		planesSc.add(friction);
		planesSc.add(mass);
		planesSc.add(height);
		
		
		
		//**** PROJECTILE MOTION ****//
		//Panel Settings
		projectSc.setLayout(null); //Absolute Positioning
		projectSc.setOpaque(true); //Allows for background
		projectSc.setBackground(bg); //Changes the colour of the background
		
		//Background Image Settings
		project.setBorder(null); //Removes borders
		project.setBounds(0, 0, 900, 600); //Sets the size and location for absolute positioning
		
		//Button Settings
		calculate2B.setBorder(null);
		calculate2B.setBounds(290, 440, 328, 114);
		calculate2B.addActionListener(new Calculate()); //Attaches action listeners to the button for usability

		clear2B.setBorder(null);
		clear2B.setBounds(100, 460, 158, 77);
		clear2B.addActionListener(new Clear());

		//Textfield Settings
		angle2.setBorder(null);
		angle2.setFont(text); //Sets the font to be used
		angle2.setBounds(430, 145, 130, 40);

		velocity.setBorder(null);
		velocity.setFont(text);
		velocity.setBounds(350, 210, 210, 40);

		height2.setBorder(null);
		height2.setFont(text);
		height2.setBounds(310, 275, 250, 40);

		//Adds components to the panel
		projectSc.add(project);
		projectSc.add(calculate2B);
		projectSc.add(clear2B);
		projectSc.add(angle2);
		projectSc.add(velocity);
		projectSc.add(height2);
	}
	
	/**
	 * Initializes the display screen components
	 * pre: none
	 * post: All components of the display screen are initialized.
	 */
	public void displayInit() {
		//**** INCLINED PLANES ****//
		//Panel Settings
		planesDSc.setLayout(null); //Absolute Positioning
		planesDSc.setOpaque(true); //Allows for background
		planesDSc.setBackground(bg); //Changes the colour of the background

		//Background Image Settings
		display1.setBorder(null); //Removes borders
		display1.setBounds(0, 0, 900, 600); //Sets the size and location for absolute positioning

		//Button Settings
		menuB.setBorder(null);
		menuB.setBounds(668, 38, 174, 77);
		menuB.addActionListener(new Menu()); //Attaches action listeners to the button for usability
		
		//Text Label Settings
		//Input Display
		heightL.setBorder(null);
		heightL.setForeground(Color.white); //Sets the colour of the text
		heightL.setFont(small); //Sets the font to be used
		heightL.setBounds(460, 97, 150, 20);
		
		angleL.setBorder(null);
		angleL.setForeground(Color.white);
		angleL.setFont(small);
		angleL.setBounds(460, 35, 150, 20);
		
		massL.setBorder(null);
		massL.setForeground(Color.white);
		massL.setFont(small);
		massL.setBounds(460, 75, 150, 20);
		
		frictionL.setBorder(null);
		frictionL.setForeground(Color.white);
		frictionL.setFont(small);
		frictionL.setBounds(460, 55, 150, 20);
		
		//Calculation Display
		acceleration.setBorder(null);
		acceleration.setForeground(Color.white); 
		acceleration.setFont(text); 
		acceleration.setBounds(640, 310, 300, 40);

		length.setBorder(null);
		length.setForeground(Color.white);
		length.setFont(displayF);
		length.setBounds(190, 290, 300, 40);

		time.setBorder(null);
		time.setForeground(Color.white);
		time.setFont(displayF);
		time.setBounds(600, 480, 300, 40);

		//Adds components to the panel
		planesDSc.add(menuB);
		planesDSc.add(heightL);
		planesDSc.add(angleL);
		planesDSc.add(massL);
		planesDSc.add(frictionL);
		planesDSc.add(length);
		planesDSc.add(acceleration);
		planesDSc.add(time);
		planesDSc.add(display1);
		

		
		
		//**** PROJECTILE MOTION ****//
		//Panel Settings
		projectDSc.setLayout(null); //Absolute Positioning
		projectDSc.setOpaque(true); //Allows for background
		projectDSc.setBackground(bg); //Changes the colour of the background

		//Background Image Settings
		display2.setBorder(null); //Removes borders
		display2.setBounds(0, 0, 900, 600); //Sets the size and location for absolute positioning

		//Button Settings
		menu2B.setBorder(null);
		menu2B.setBounds(668, 38, 174, 77);
		menu2B.addActionListener(new Menu()); //Attaches action listeners to the button for usability
		
		//Text Label Settings
		//Input Display
		height2L.setBorder(null);
		height2L.setForeground(Color.white); //Sets the colour of the text
		height2L.setFont(small); //Sets the font to be used
		height2L.setBounds(460, 75, 150, 20);
		
		angle2L.setBorder(null);
		angle2L.setForeground(Color.white);
		angle2L.setFont(small);
		angle2L.setBounds(460, 35, 150, 20);
		
		velocityL.setBorder(null);
		velocityL.setForeground(Color.white);
		velocityL.setFont(small);
		velocityL.setBounds(460, 55, 150, 20);
		
		//Calculation Display
		distance.setBorder(null);
		distance.setForeground(Color.white);
		distance.setFont(displayF);
		distance.setBounds(620, 310, 300, 40);

		time2.setBorder(null);
		time2.setForeground(Color.white);
		time2.setFont(displayF);
		time2.setBounds(190, 310, 300, 40);

		max.setBorder(null);
		max.setForeground(Color.white);
		max.setFont(displayF);
		max.setBounds(640, 450, 300, 40);
		
		//Adds components to the panel
		projectDSc.add(menu2B);
		projectDSc.add(height2L);
		projectDSc.add(angle2L);
		projectDSc.add(velocityL);
		projectDSc.add(distance);
		projectDSc.add(max);
		projectDSc.add(time2);
		projectDSc.add(display2);
	}

	/**
	 * Attaches the panel components onto the main card layout panel
	 * pre: none
	 * post: All components are attached
	 */
	public void layoutInit() {
		screens.setLayout(layout);
		screens.setBounds(0, 0, 900, 600);
		screens.add(title, card1);
		screens.add(helpSc, card2);
		screens.add(selectSc, card3);
		screens.add(planesSc, card4);
		screens.add(projectSc, card5);
		screens.add(panel, card6);
		screens.add(planesDSc, card7);
		screens.add(projectDSc, card8);
	}
	
	/**
	 * Determines which display to choose based on source
	 * pre: none
	 * post: Display screen is shown
	 */
	public void displayPlanes() {
		//Checks the source of the input 
		if (source == 1 ){ //Inclined Planes
			layout.show(screens, card7); //Changes the panel displayed
			
			//Adjusts the text of the labels based upon the values from the calculation class
			//Numbers are formatted
			massL.setText(number.format(Calculation.massP)+ " kg"); 
			frictionL.setText(number.format(Calculation.frictionP)+ " mu");
			heightL.setText(number.format(Calculation.heightP)+ " m");
			angleL.setText(number.format(Calculation.angleP)+ " degrees");
			acceleration.setText(number.format(Calculation.accelP)+ " m/s^2");
			length.setText(number.format(Calculation.hypP) + " m");
			
			//Checks the acceleration 
			if (Calculation.accelP == 0){
				time.setText("Never"); //Object will not move
			} else{
				time.setText(number.format(Calculation.timeP) + " s");
			}	
			
		} else { //Projectile Motion
			layout.show(screens, card8); //Changes the panel displayed
		
			//Adjusts the text of the labels based upon the values from the calculation class
			//Numbers are formatted
			velocityL.setText(number.format(Calculation.velocityPr)+ " m/s");
			height2L.setText(number.format(Calculation.heightPr)+ " m");
			angle2L.setText(number.format(Calculation.anglePr)+ " degrees");
			max.setText(number.format(Calculation.maxPr)+ " m");
			distance.setText(number.format(Calculation.distancePr) + " m");
			time2.setText(number.format(Calculation.timePr) + " s");
		}
	}

	//******* Action Listeners *******//
	//Help Button Listener
	class Help implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			layout.show(screens, card2); //Changes to the help screen
		}
	}

	//Back Button Listener
	class Back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			layout.show(screens, card1); //Changes to the menu screen from help
		}
	}
	
	//Begin Button Listener
	class Begin implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			layout.show(screens, card3); //Changes to the selection screen from help
		}
	}

	//Incline Planes Button Listener
	class Incline implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			layout.show(screens, card4); //Changes to the Inclined Planes Input screen
			source = 1; //Adjusts the source
		}
	}

	//Projectile Motion Button Listener
	class Projectile implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			layout.show(screens, card5); //Changes to the Inclined Planes Input screen
			source = 2; //Adjusts the source
		}
	}

	//Clear Button Listener
	//Clears all textfields
	class Clear implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Sets all textfields back to empty.
			//Inclined Planes
			angle.setText("");
			friction.setText("");
			mass.setText("");
			height.setText("");
			
			//Projectile Motion
			angle2.setText("");
			velocity.setText("");
			height2.setText("");
		}
	}
	
	//Menu Button Listener
	class Menu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			layout.show(screens, card1); //Changes to the Menu screen
			
			//Calls upon methods to reset values
			calc.reset();
			panel.reset();
			
			//Sets all textfields empty
			//Inclined Planes
			angle.setText("");
			friction.setText("");
			mass.setText("");
			height.setText("");
			//Projectile Motion
			angle2.setText("");
			velocity.setText("");
			height2.setText("");
			
			//Sets all Strings empty
			//Inclined Planes
			angleS =("");
			frictionS = ("");
			massS = ("");
			heightS = ("");
			//Projectile Motion
			angle2.setText("");
			velocity.setText("");
			height2.setText("");
		}
	}
	
	//Calculate Button Listener
	//Changes to the simulation screen, passes values to other classes and checks for invalid inputs
	class Calculate implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Checks if preceding input was Inclined Planes or Projectile Motion
			if (source == 1) { //Inclined Planes
				
				//Checks whether any of the textfields have been left blank
				if (angle.getText().equals("") || friction.getText().equals("") || mass.getText().equals("") || height.getText().equals("")) {
					//Creates a JOptionPane to inform the user
					JOptionPane.showMessageDialog(null, "A field is empty. Please fill everything!");
				} else {
					//Receives the values stored in the textfields
					angleS = angle.getText();
					frictionS = friction.getText();
					massS = mass.getText();
					heightS = height.getText();
					
					//Try and Catch to parse (in case there are any letters)
					try {
						//Parses all the strings
						angleD = Double.parseDouble(angleS);
						frictionD = Double.parseDouble(frictionS);
						massD = Double.parseDouble(massS);
						heightD = Double.parseDouble(heightS);

						//Checks whether the values follow the restrictions
						if (angleD >= 90 || angleD <= 0 || frictionD < 0 || massD <= 0 || heightD < 0) {
							//Creates a JOptionPane to inform the user
							JOptionPane.showMessageDialog(null, "An invalid value has been entered.");
						} else {
							//Transmits values to the calculation class
							calc.setUp(heightD, angleD, massD, frictionD);

							layout.show(screens, card6); //Changes to the simulation panel
							panel.incline1(); //Calls upon the method for the animation
						}
					} catch (Exception ex) {
						//Creates a JOptionPane to inform the user
						JOptionPane.showMessageDialog(null, "A letter or character has been entered.");
					}
				}
				
			} else if (source == 2){ //Projectile Motion
				
				//Checks whether any of the textfields have been left blank
				if (angle2.getText().equals("") || velocity.getText().equals("") || height2.getText().equals("")) {
					//Creates a JOptionPane to inform the user
					JOptionPane.showMessageDialog(null, "A field is empty. Please fill everything!");
				} else {
					//Receives the values stored in the textfields
					angle2S = angle2.getText();
					velocityS = velocity.getText();
					height2S = height2.getText();
					
					//Try and Catch to parse (in case there are any letters)
					try {
						//Parses all the strings
						angle2D = Double.parseDouble(angle2S);
						velocityD = Double.parseDouble(velocityS);
						height2D = Double.parseDouble(height2S);

						//Checks whether the values follow the restrictions
						if (angle2D >= 90 || angle2D <= 0 || velocityD < 0 || height2D < 0) {
							//Creates a JOptionPane to inform the user
							JOptionPane.showMessageDialog(null, "An invalid value has been entered.");
						} else {
							//Transmits values to the calculation class
							calc.setUp2(height2D, angle2D, velocityD);
							
							layout.show(screens, card6); //Changes to the simulation panel
							panel.project1(); //Calls upon the method for the animation
						}
					} catch (Exception ex) {
						//Creates a JOptionPane to inform the user
						JOptionPane.showMessageDialog(null,
								"A letter or character has been entered.");
					}
				}
			}
		}
	}
}
