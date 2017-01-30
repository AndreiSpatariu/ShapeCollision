/**
 * Created by Andrei Spatariu on 11/16/2016.
 */
public class Rectangle {
    public Point rectangleA;
    public Point rectangleB;

    Rectangle(float x1, float y1, float x2, float y2){
        rectangleA = new Point(x1, y1);
        rectangleB = new Point(x2, y2);
    }

    public boolean collidesWith(Point point) {
        return (rectangleA.x <= point.x && point.x <= rectangleB.x)
                && (rectangleA.y <= point.y && point.y <= rectangleB.y);
    }
}
