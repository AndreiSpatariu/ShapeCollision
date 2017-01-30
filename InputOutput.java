import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrei Spatariu on 11/12/2016.
 */
public class InputOutput {
    public static QuadTree ReadLine (String string, QuadTree quadTree, PrintWriter writer) throws CloneNotSupportedException {
        String[] arguments = string.split(" ");

        int operationType = Integer.valueOf(arguments[0]);

        switch(operationType) {
            case 11:
                return ReadAdd(arguments, quadTree);

            case 22:
                int ID = Integer.valueOf(arguments[1]);
                quadTree.removeObject(ID);
                return quadTree;

            case 33:
                Point point = new Point(
                        Float.valueOf(arguments[1]),
                        Float.valueOf(arguments[2])
                );

                if(!quadTree.Bounds.isPointInside(point)){
                    writer.println("NIL");
                    return quadTree;
                }

                ArrayList<Integer> PointIDList = new ArrayList<Integer>();

                quadTree.printCollisionsWithPoint(point, writer, PointIDList);

                Collections.sort(PointIDList);

                if(!PointIDList.isEmpty())
                    writer.println(listToString(PointIDList));
                else
                    writer.println("NIL");

                return quadTree;

            case 44:
                ArrayList<Integer> ObjectIDList = new ArrayList<Integer>();

                GeometricalObject geometricalObject = ReadGeometricalShapeForCollision(arguments);

                quadTree.printCollisionsWithObject(geometricalObject, ObjectIDList, writer);

                Collections.sort(ObjectIDList);

                if(!ObjectIDList.isEmpty())
                    writer.println(listToString(ObjectIDList));
                else
                    writer.println("NIL");

                return quadTree;

        }
        return null;
    }

    private static QuadTree ReadAdd(String[] arguments, QuadTree quadTree){
        int ID = Integer.valueOf(arguments[2]);

        GeometricalObject geometricalObject = ReadGeometricalShapeForInsert(arguments);
        geometricalObject.ID = ID;

        quadTree.insertObject(geometricalObject);

        return quadTree;
    }

    private static GeometricalObject ReadGeometricalShapeForCollision(String[] arguments){
        int geometricalShape = Integer.valueOf(arguments[1]);

        switch (geometricalShape){

            case Constants.rectangle:
                return Factory.createRectangle(
                        geometricalShape,
                        Float.valueOf(arguments[2]),
                        Float.valueOf(arguments[3]),
                        Float.valueOf(arguments[4]),
                        Float.valueOf(arguments[5]));

            case Constants.triangle:
                return Factory.createTriangle(
                        geometricalShape,
                        Float.valueOf(arguments[2]),
                        Float.valueOf(arguments[3]),
                        Float.valueOf(arguments[4]),
                        Float.valueOf(arguments[5]),
                        Float.valueOf(arguments[6]));

            case Constants.circle:
                return Factory.createCircle(
                        geometricalShape,
                        Float.valueOf(arguments[2]),
                        Float.valueOf(arguments[3]),
                        Float.valueOf(arguments[4]));

            case Constants.diamond:
                return Factory.createDiamond(
                        geometricalShape,
                        Float.valueOf(arguments[2]),
                        Float.valueOf(arguments[3]),
                        Float.valueOf(arguments[4]),
                        Float.valueOf(arguments[5]),
                        Float.valueOf(arguments[7]),
                        Float.valueOf(arguments[8]));
        }

        return null;
    }

    private static GeometricalObject ReadGeometricalShapeForInsert(String[] arguments) {
        int geometricalShape = Integer.valueOf(arguments[1]);

        switch (geometricalShape){

            case Constants.rectangle:
                return Factory.createRectangle(
                        geometricalShape,
                        Float.valueOf(arguments[3]),
                        Float.valueOf(arguments[4]),
                        Float.valueOf(arguments[5]),
                        Float.valueOf(arguments[6]));

            case Constants.triangle:
                return Factory.createTriangle(
                        geometricalShape,
                        Float.valueOf(arguments[3]),
                        Float.valueOf(arguments[4]),
                        Float.valueOf(arguments[5]),
                        Float.valueOf(arguments[6]),
                        Float.valueOf(arguments[7]));

            case Constants.circle:
                return Factory.createCircle(
                        geometricalShape,
                        Float.valueOf(arguments[3]),
                        Float.valueOf(arguments[4]),
                        Float.valueOf(arguments[5]));

            case Constants.diamond:
                return Factory.createDiamond(
                        geometricalShape,
                        Float.valueOf(arguments[3]),
                        Float.valueOf(arguments[4]),
                        Float.valueOf(arguments[5]),
                        Float.valueOf(arguments[6]),
                        Float.valueOf(arguments[8]),
                        Float.valueOf(arguments[9]));
        }

        return null;
    }

    public static String listToString(ArrayList<Integer> list) {
        String result = "";

        for (int i = 0; i < list.size() - 1; i++) {
            result += list.get(i) + " ";
        }

        if(!list.isEmpty())
            result += list.get(list.size() - 1);

        return result;
    }
}
