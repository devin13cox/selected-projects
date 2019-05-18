package byow.Core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//inspo source
// http://www.avajava.com/tutorials/lessons/how-do-i-write-a-string-to-a-file.html?page=1
public class WriteString {

    public static void store(String save) {
        try {
            File file = new File("save.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(save);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
