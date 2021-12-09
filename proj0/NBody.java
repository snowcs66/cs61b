public class NBody {
    

	public static double readRadius(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
	    double r = in.readDouble();
        return r;
	}

	public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        double r = in.readDouble();
        Planet[] planets = new Planet[n];
        int i = 0;
        while (!in.isEmpty()) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = "images/" + in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, 
                yyVel, mass, imgFileName);
            i++;
            if (i >= planets.length) {
                break;
            }
        } 
        return planets;
	}

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet p : planets) {
            p.draw();
        }

        StdDraw.enableDoubleBuffering();

        for (double time = 0; time <= T; time += dt) {
            Double[] xForces = new Double[planets.length];
            Double[] yForces = new Double[planets.length];

            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(time, xForces[i], yForces[i]);
            }
        
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}