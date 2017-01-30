/**
 * Created by Andrei Spatariu on 11/16/2016.
 */
public class Diamond {
    public Point diamondA;
    public Point diamondB;
    public Point diamondC;
    public Point diamondD;

    Diamond(float x1, float y1, float x2, float y2, float y3, float x4){
        diamondA = new Point(x1, y1);
        diamondB = new Point(x2, y2);
        diamondC = new Point(x1, y3);
        diamondD = new Point(x4, y2);
    }

    private Triangle[] split(){
        Triangle[] triangles = new Triangle[2];

        triangles[0] = new Triangle(diamondA.x, diamondA.y, diamondB.x, diamondB.y, diamondD.x, diamondD.y);
        triangles[1] = new Triangle(diamondB.x, diamondB.y, diamondD.x, diamondD.y, diamondC.x, diamondC.y);

        return triangles;
    }

    public boolean collidesWith(Point point){
        Triangle[] triangles = this.split();

        return triangles[0].collidesWith(point) || triangles[1].collidesWith(point);
    }
}
