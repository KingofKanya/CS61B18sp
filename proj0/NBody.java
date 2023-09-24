public class NBody {
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        Planet[] planets = readPlanets(fileName);
        double radius = readRadius(fileName);
        double t = 0;
        int num = planets.length;

        //enable double buffering
        StdDraw.enableDoubleBuffering();
        //set up the universe
        StdDraw.setScale(-radius, radius);
        while(t <= T){
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            int i = 0;
            for (Planet planet : planets) {
                xForces[i] = planet.calcNetForceExertedByX(planets);
                yForces[i] = planet.calcNetForceExertedByY(planets);
                i++;
            }
            i = 0;
            for (Planet planet : planets) {
                planet.update(dt, xForces[i], yForces[i]);
                i++;
            }
            //draw the backgroud picture
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            //draw all the planets
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", num);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < num; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

    public static double readRadius(String address){
        In in = new In(address);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        int n = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[n];
        for (int i = 0; i < n; i++) {
            Planet p = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            planets[i] = p;
        }
        return planets;
    }
}
