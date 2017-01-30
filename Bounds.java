/**
 * Created by Andrei Spatariu on 11/6/2016.
 */
public class Bounds
{
    public Point boundsA;
    public Point boundsB;

    Bounds(float x1, float y1, float x2, float y2) {
        boundsA = new Point(x1, y1);
        boundsB = new Point(x2, y2);
    }

    public Bounds[] split() {
        Bounds[] BoundsArray = new Bounds[4];

        float xm = (boundsA.x + boundsB.x) / 2;
        float ym = (boundsA.y + boundsB.y) / 2;

        BoundsArray[0] = new Bounds(boundsA.x, boundsA.y, xm, ym);
        BoundsArray[1] = new Bounds(boundsA.x, ym, xm, boundsB.y);
        BoundsArray[2] = new Bounds(xm, ym, boundsB.x, boundsB.y);
        BoundsArray[3] = new Bounds(xm, boundsA.y, boundsB.x, ym);

        return BoundsArray;
    }

    public static boolean isObjectWithinBounds(Bounds bounds, GeometricalObject geometricalObject) {
        if(geometricalObject.pointA.x > bounds.boundsB.x
                || bounds.boundsA.y > geometricalObject.pointB.y
                || bounds.boundsA.x > geometricalObject.pointB.x
                || geometricalObject.pointA.y > bounds.boundsB.y)
            return false;

        return true;
    }

    public static boolean[] getQuadrants(Bounds bounds, GeometricalObject geometricalObject){
        boolean[] isWithinQuadrants = new boolean[4];
        Bounds[] quadrants = bounds.split();

        for(int i = 0; i < 4; i++){
            if(isObjectWithinBounds(quadrants[i], geometricalObject))
                isWithinQuadrants[i] = true;
        }

        return isWithinQuadrants;
    }

    public boolean isPointInside(Point point){
        return (boundsA.x <= point.x && point.x <= boundsB.x)
                && (boundsA.y <= point.y && point.y <= boundsB.y);
    }
}
