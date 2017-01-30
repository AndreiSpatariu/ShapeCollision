/**
 * Created by Andrei Spatariu on 11/16/2016.
 */
public class Circle {
    public float radius;
    public Point center;

    Circle(float r, float x, float y){
        radius = r;
        center = new Point(x, y);
    }

    public boolean collidesWith(Point point){
        if(Point.distanceBetween(center, point) > radius)
            return false;

        return true;
    }
}
