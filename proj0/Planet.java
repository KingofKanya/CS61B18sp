import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class Planet {
    public static final double G = 6.67e-11;
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        return sqrt(pow(this.xxPos - p.xxPos, 2) + pow(this.yyPos - p.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p){
        return G * this.mass * p.mass / pow(this.calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p){
        double dx = this.xxPos - p.xxPos;
        double res = calcForceExertedBy(p) * dx / calcDistance(p);
        if(res > 0){
            return res;
        }
        return -res;
    }

    public double calcForceExertedByY(Planet p){
        double dy = this.yyPos - p.yyPos;
        double res = calcForceExertedBy(p) * dy / calcDistance(p);
        if(res > 0){
            return res;
        }
        return -res;
    }

    public double calcNetForceExertedByX(Planet[] arr){
        double sum = 0;
        for (Planet planet : arr) {
            if(!this.equals(planet)){
                sum += this.calcForceExertedByX(planet);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] arr){
        double sum = 0;
        for (Planet planet : arr) {
            if(!this.equals(planet)){
                sum += this.calcForceExertedByY(planet);
            }
        }
        return sum;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}
