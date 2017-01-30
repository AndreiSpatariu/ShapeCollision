/**
 * Created by Andrei Spatariu on 11/8/2016.
 */
public class Point{
    public float x;
    public float y;

    Point(float xValue, float yValue){
        x = xValue;
        y = yValue;
    }

    public static float distanceBetween(Point A, Point B){
        return (float)Math.sqrt((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
    }
}
