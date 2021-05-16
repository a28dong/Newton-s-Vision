//The Calculation class used for implementing mathematics and processing data
//This class receives user input values and then calculates other values

public class Calculation {
	//Creates double variables to store data for/from calculations
	//Inclined Planes
	public static double angleP, massP, frictionP, heightP;
	public static double lengthP, hypP, accelP, timeP;
	public static double accelX, accelY;
	
	//Projectile Motion
	public static double anglePr, velocityPr, heightPr;
	public static double distancePr, timePr, time2Pr, maxPr;
	public static double velocityX, velocityY;
	
	//Creates a constant used to mimic gravity
	public final double g = 9.8;
	
	/**
	 * Initializes all the values for inclined planes by calling upon respective methods
	 * pre: all input follows the restrictions, h>=0, 90>a>0, m>0, f>=0
	 * post: All values initialized.
	 */
	public void setUp(double h, double a, double m, double f){
		//Calls on methods that calculate each of the values and stores the data
		setAngleP(a);
		setFrictionP(f);
		setMassP(m);
		setHeightP (h);
		setLengthP(h, a);
		setHypP (h, a);
		setAccelP(a, m, f);
		setTimeP();
	}
	
	/**
	 * Initializes all the values for projectile motion by calling upon respective methods
	 * pre: all input follows the restrictions, h>=0, 90>a>0, m>0, v>0
	 * post: All values initialized.
	 */
	public void setUp2(double h, double a, double v){
		//Calls on methods that calculate each of the values and stores the data
		setAnglePr(a);
		setHeightPr (h);
		setVelocityPr(a,v);
		setTimePr();
		setMax();
		setDistancePr();
	}
	
	/**
	 * Resets all varaibles by reducing the values to 0
	 * pre: none
	 * post: All values are reset to 0
	 */
	public void reset () {
		//Inclined Planes
		angleP = 0;
		massP = 0;
		frictionP = 0;
		heightP = 0;
		lengthP = 0;
		hypP = 0;
		accelP = 0;
		timeP = 0;
		accelX = 0;
		accelY = 0;

		//Projectile Motion
		anglePr = 0;
		velocityPr = 0;
		heightP = 0;
		distancePr = 0;
		maxPr = 0;
		velocityY = 0;
		velocityX = 0;
		timePr = 0;
		time2Pr = 0;
	}
	
	
	//**** INCLINED PLANES ****//
	/**
	 * Setter, sets the value of angle
	 * pre: 90>a>0
	 * post: value of angle has been set
	 */
	public void setAngleP(double a){
		angleP = a;
	}
	
	/**
	 * Setter, sets the value of mass
	 * pre: m>0
	 * post: value of mass has been set
	 */
	public void setMassP(double m){
		massP = m;
	}
	
	/**
	 * Setter, sets the value of friction
	 * pre: f>=0
	 * post: value of friction has been set
	 */
	public void setFrictionP(double f){
		frictionP = f;
	}
	
	/**
	 * Setter, sets the value of height
	 * pre: h>=0
	 * post: value of height has been set
	 */
	public void setHeightP(double h){
		heightP = h;
	}
	
	/**
	 * Calculates and sets the value of length
	 * pre: h>=0, 90>a>0
	 * post: value of length has been calculated and set
	 */
	public void setLengthP(double h, double a){
		//uses trigonometry to calculate the base of the triangle
		lengthP = h/Math.tan(Math.toRadians(a));
	}
	
	/**
	 * Calculates and sets the value of hypotenuse
	 * pre: h>=0, 90>a>0
	 * post: value of hypotenuse has been calculated and set
	 */
	public void setHypP(double h, double a){
		//uses trigonometry to calculate the base of the triangle
		hypP = h/Math.sin(Math.toRadians(a));
	}
	
	/**
	 * Calculates and sets the value of acceleration
	 * pre: f>=0, 90>a>0, m>0
	 * post: value of acceleration has been calculated and set
	 */
	public void setAccelP( double a, double m, double f){
		//Calculates the acceleration by summing the total force, and divides by mass
		accelP = (Math.sin(Math.toRadians(a)) * g * m - Math.cos(Math.toRadians(a)) * g * m * f )/m;
		if (accelP<0)
			//ensures that if the friction force is greater than the force of gravity, acceleration is 0
			accelP = 0;
		//Breaks into horizontal and vertical components using trigonometry
		accelX = accelP * Math.cos(Math.toRadians(a));
		accelY = accelP * Math.sin(Math.toRadians(a));
	}
	
	/**
	 * Calculates and sets the value of time
	 * pre: none
	 * post: value of time has been calculated and set
	 */
	public void setTimeP(){
		//uses try and catch to prevent math errors
		try {
		//Simplifies and solves using Big 5 Kinematics Equation
		timeP = Math.sqrt(hypP*2/accelP);
		} catch (Exception e){
		}
	}
	
	
	//**** PROJECTILE MOTION ****//
	
	/**
	 * Setter, sets the value of angle
	 * pre: 90>a>0
	 * post: value of angle has been set
	 */
	public void setAnglePr(double a){
		anglePr = a;
	}
	
	
	/**
	 * Setter, sets the value of velocity
	 * pre: 90>a>0, v>0
	 * post: value of velocity has been set
	 */
	public void setVelocityPr( double a, double v){
		velocityPr = v;
		
		//Breaks into horizontal and vertical components using trigonometry
		velocityX = velocityPr * Math.cos(Math.toRadians(a));
		velocityY = velocityPr * Math.sin(Math.toRadians(a));
		
		//Sets the acceleration equal to the gravity
		accelY=g;
	}
	
	/**
	 * Setter, sets the value of height
	 * pre: h>=0
	 * post: value of height has been set
	 */
	public void setHeightPr(double h){
		heightPr = h;
	}
	
	/**
	 * Calculates and sets the value of distance
	 * pre: none
	 * post: value of distance has been calculated and set
	 */
	public void setDistancePr(){
		//uses basic distance, speed and time formula
		distancePr = timePr*velocityX;
	}

	/**
	 * Calculates and sets the value of time
	 * pre: none
	 * post: value of time has been calculated and set
	 */
	public void setTimePr(){
		//Prevents any math errors
		try {
		//Simplifies and solves using Big 5 Kinematics Equation and Quadratic Formula
		timePr = ((-1*velocityY) - (Math.sqrt(Math.pow(velocityY, 2) - 4*(0.5*g*-1)*heightPr)))/(-1*g);
		//Calculates time to reach max height
		time2Pr = velocityY/g;
		} catch (Exception e){
		}
	}
	
	/**
	 * Calculates and sets the value of max height
	 * pre: none
	 * post: value of max height has been calculated and set
	 */
	public void setMax(){
		//Simplifies and solves using Big 5 Kinematics Equation
		maxPr = time2Pr * velocityY + 0.5 *-1*g * Math.pow(time2Pr, 2)+ heightPr;
	}
	
}
