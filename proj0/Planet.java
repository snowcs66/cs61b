public class Planet {
	public double xxPos; // Its current x position.
	public double yyPos; // Its current y position.
	public double xxVel; // Its current velocity in the x direction.
	public double yyVel; // Its current velocity in the y direction.
	public double mass; // Its mass
	public String imgFileName; /* The name of the file that corresponds to the image 
	                           that depicts the body (for example, jupiter.gif)
	                           */
    static final double G = 6.67e-11; 

    public Planet(double xP, double yP, double xV,
    	          double yV, double m, String img) {
    	xxPos = xP;
    	yyPos = yP;
    	xxVel = xV;
    	yyVel = yV;
    	mass = m;
    	imgFileName = img;
    }
    
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double force = G * mass * p.mass / (calcDistance(p) * calcDistance(p));
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double forceX = calcForceExertedBy(p) * dx / calcDistance(p);
        return forceX;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double forceY = calcForceExertedBy(p) * dy / calcDistance(p);
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        for (Planet planet : allPlanets) {
            if (this.equals(planet))
                continue;
            netForceX = netForceX + calcForceExertedByX(planet);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        for (Planet planet : allPlanets) {
            if (this.equals(planet))
                continue;
            netForceY = netForceY + calcForceExertedByY(planet);
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        xxVel = xxVel + dt * (fX / mass);
        yyVel = yyVel + dt * (fY / mass);
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        String imgToDraw = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgToDraw);
    }
}













