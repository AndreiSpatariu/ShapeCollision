/**
 * Created by Andrei Spatariu on 11/16/2016.
 */
public class Triangle {
    public Point triangleA;
    public Point triangleB;
    public Point triangleC;

    Triangle(float x1, float y1, float x2, float y2, float x3, float y3){
        triangleA = new Point(x1, y1);
        triangleB = new Point(x2, y2);
        triangleC = new Point(x3, y3);
    }

    private float sign (Point a, Point b, Point c){
        return (a.x - c.x) * (b.y - c.y) - (b.x - c.x) * (a.y - c.y);
    }

    public boolean collidesWith(Point point){
        boolean sign1, sign2, sign3;

        sign1 = sign(point, triangleA, triangleB) < 0.0f;
        sign2 = sign(point, triangleB, triangleC) < 0.0f;
        sign3 = sign(point, triangleC, triangleA) < 0.0f;

        return((sign1 == sign2) && (sign2 == sign3));
    }
}
