import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String string = "save me";

        try {
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter("./test.txt"));

            writer.write(string);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
