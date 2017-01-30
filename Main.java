import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws IOException, CloneNotSupportedException {

        PrintWriter writer = new PrintWriter("quadtree.out");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Andrei Spatariu\\IdeaProjects\\TemaFinala\\src\\input\\quadtree9.in"))) {
            String line;

            line = bufferedReader.readLine();

            String[] arguments = line.split(" ");

            Bounds bounds = new Bounds(
                    Float.valueOf(arguments[0]),
                    Float.valueOf(arguments[1]),
                    Float.valueOf(arguments[2]),
                    Float.valueOf(arguments[3])
            );

            QuadTree quadTree = new QuadTree(1, bounds);

            while ((line = bufferedReader.readLine()) != null) {
                quadTree = InputOutput.ReadLine(line, quadTree, writer);
            }
        }
        writer.close();
    }
}
