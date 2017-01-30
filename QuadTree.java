import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Andrei Spatariu on 11/5/2016.
 */
public class QuadTree {
    public int Level;
    public ArrayList<GeometricalObject> GeometricalObjects;
    public QuadTree[] Nodes;
    public Bounds Bounds;

    QuadTree (int level, Bounds bounds) {
        Bounds = bounds;
        Nodes = new QuadTree[4];
        Level = level;
        GeometricalObjects = new ArrayList<GeometricalObject>();
    }

    public QuadTree() {
    }

    public void splitQuadrant() {
        Bounds[] bounds = Bounds.split();

        Nodes[0] = new QuadTree(Level + 1, bounds[0]);
        Nodes[1] = new QuadTree(Level + 1, bounds[1]);
        Nodes[2] = new QuadTree(Level + 1, bounds[2]);
        Nodes[3] = new QuadTree(Level + 1, bounds[3]);
    }

    public void insertObject(GeometricalObject geometricalObject){
        boolean collidesWithObject = false;
        boolean[] isWithinQuadrant = new boolean[4];

        for(int i = 0; i < 4; i++)
            isWithinQuadrant[i] = false;

        if(!GeometricalObjects.isEmpty())
            for(GeometricalObject tempObject: GeometricalObjects){
                if(geometricalObject.collidesWith(tempObject))
                    collidesWithObject = true;
            }

        boolean insertInCurrentNode = isIntoFather(hasSons(), GeometricalObjects.isEmpty(), collidesWithObject);
        boolean insertInSubNodesDeleteFirst = isIntoEmptyFather(hasSons(), GeometricalObjects.isEmpty(), collidesWithObject);
        boolean insertInSubNodesWithoutDelete = hasSons();

        if(insertInCurrentNode){
            GeometricalObjects.add(geometricalObject);
            return;
        }

        if(insertInSubNodesDeleteFirst){
            ArrayList<GeometricalObject> geometricalObjects = (ArrayList<GeometricalObject>) GeometricalObjects.clone();

            geometricalObjects.add(geometricalObject);
            splitQuadrant();

            GeometricalObjects.clear();

            for(GeometricalObject tempObject: geometricalObjects) {
                isWithinQuadrant = Bounds.getQuadrants(Bounds, tempObject);

                for(int i = 0; i < 4; i++)
                    if(isWithinQuadrant[i])
                        Nodes[i].insertObject(tempObject);
            }
            return;
        }

        if(insertInSubNodesWithoutDelete){
            isWithinQuadrant = Bounds.getQuadrants(Bounds, geometricalObject);

            for(int i = 0; i < 4; i++)
                if(isWithinQuadrant[i])
                    Nodes[i].insertObject(geometricalObject);
        }
    }

    public void removeObject(int ID) throws CloneNotSupportedException {

        ArrayList<GeometricalObject> objectsClone = new ArrayList<GeometricalObject>();

        if(!GeometricalObjects.isEmpty()){
            for(GeometricalObject tempObject : GeometricalObjects) {
                if(tempObject.ID == ID){


                    objectsClone = (ArrayList<GeometricalObject>) GeometricalObjects.clone();
                    objectsClone.remove(tempObject);

                    GeometricalObjects = (ArrayList<GeometricalObject>) objectsClone.clone();
                }
            }
            return;
        }

        if(hasSons()){
            for(int i = 0; i < 4; i++)
                    Nodes[i].removeObject(ID);
        }
    }

    private boolean hasSons() {
        for(int i = 0; i < 4; i++)
            if(Nodes[i] != null)
                return true;

        return false;
    }

    private boolean isIntoFather(boolean hasSons, boolean isEmpty, boolean collidesWithObject) {
        return !hasSons && (isEmpty || collidesWithObject);
    }

    private boolean isIntoEmptyFather(boolean hasSons, boolean isEmpty, boolean collidesWithObject){
        return !hasSons && !collidesWithObject && !isEmpty;
    }

    private int getOccupiedSonsCount() {
        int count = 0;

        for(int i = 0; i < 4; i++) {
            if(!Nodes[i].GeometricalObjects.isEmpty())
                count++;
        }

        return count;
    }

    private void getOnlySonWithDelete() throws CloneNotSupportedException {
        QuadTree son = new QuadTree();

        for(int i = 0; i < 4; i++) {
            if(!Nodes[i].GeometricalObjects.isEmpty()) {
                son = (QuadTree) Nodes[i].clone();
            }

            Nodes[i] = null;
        }

        GeometricalObjects = (ArrayList<GeometricalObject>) son.GeometricalObjects.clone();
    }

    public void printCollisionsWithPoint(Point point, PrintWriter writer, ArrayList<Integer> IDList){
        if(hasSons()) {
            for(int i = 0; i < 4; i++){
                if(Nodes[i].Bounds.isPointInside(point)){
                    Nodes[i].printCollisionsWithPoint(point, writer, IDList);
                    break;
                }
            }
            return;
        }

        if (!GeometricalObjects.isEmpty()) {
            for (GeometricalObject tempObject : GeometricalObjects) {
                if(tempObject.containsPoint(point)) {
                    IDList.add(tempObject.ID);
                }
            }
        }
    }

    public void printCollisionsWithObject(GeometricalObject geometricalObject, ArrayList<Integer> IDList, PrintWriter writer){
        if(hasSons()) {
            boolean[] isWithinBounds = new boolean[4];

            isWithinBounds = Bounds.getQuadrants(Bounds, geometricalObject);

            for(int i = 0; i < 4; i++){
                if(isWithinBounds[i])
                    Nodes[i].printCollisionsWithObject(geometricalObject, IDList, writer);
            }

            return;
        }

        if(!GeometricalObjects.isEmpty()){
            for(GeometricalObject tempObject : GeometricalObjects){
                if(!IDList.contains(new Integer(tempObject.ID))
                        && tempObject.collidesWith(geometricalObject))
                    IDList.add(new Integer(tempObject.ID));
            }
        }
    }
}
