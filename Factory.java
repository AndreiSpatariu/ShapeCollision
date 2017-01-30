/**
 * Created by Andrei Spatariu on 11/12/2016.
 */
public class Factory {

    public static GeometricalObject createRectangle(int type, float x1, float y1, float x2, float y2) {

        GeometricalObject geometricalObject = new GeometricalObject(x1, y1, x2, y2, type);
        geometricalObject.rectangle = new Rectangle(x1, y1, x2, y2);

        return geometricalObject;
    }

    public static GeometricalObject createTriangle(int type, float x1, float y1, float x2, float y2, float x3){
        //GeometricalObject geometricalObject = new GeometricalObject(Math.min(x1, x2), y2, Math.max(x2, x3), y1, type);
        GeometricalObject geometricalObject = new GeometricalObject(x2, y2, x3, y1, type);
        geometricalObject.triangle = new Triangle(x1, y1, x2, y2, x3, y2);

        return geometricalObject;
    }

    public static GeometricalObject createCircle(int type, float R, float x, float y){
        GeometricalObject geometricalObject = new GeometricalObject(x - R, y - R, x + R, y + R, type);
        geometricalObject.circle = new Circle(R, x, y);

        return geometricalObject;
    }

    public static GeometricalObject createDiamond(int type, float x1, float y1, float x2, float y2, float y3, float x4){
        GeometricalObject geometricalObject = new GeometricalObject(x2, y1, x4, y3, type);
        geometricalObject.diamond = new Diamond(x1, y1, x2, y2, y3, x4);

        return geometricalObject;
    }
}
