/**
 * Created by Andrei Spatariu on 11/5/2016.
 */
public class GeometricalObject extends Forms{
    public Point pointA;
    public Point pointB;
    public int ID;
    public int objectType;

    GeometricalObject(float x1, float y1, float x2, float y2, int type){
        pointA = new Point(x1, y1);
        pointB = new Point(x2, y2);
        objectType = type;
    }

    public Bounds toBounds(){
        return new Bounds(pointA.x, pointA.y, pointB.x, pointB.y);
    }

    public boolean collidesWith(GeometricalObject geometricalObject){
        return Bounds.isObjectWithinBounds(geometricalObject.toBounds(), this);
    }

    public boolean containsPoint(Point point) {
        switch(objectType) {
            case Constants.circle:
                return circle.collidesWith(point);

            case Constants.diamond:
                return diamond.collidesWith(point);

            case Constants.rectangle:
                return rectangle.collidesWith(point);

            case Constants.triangle:
                return triangle.collidesWith(point);
        }

        return false;
    }
}
