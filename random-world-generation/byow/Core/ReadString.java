package byow.Core;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//@source http://www.avajava.com/tutorials/lessons/how-do-i-read-a-string-from-a-file.html
public class ReadString {
    private String loaded;

    public String getLoaded() {
        return loaded;
    }

    public void load() {
        try {
            File file = new File("save.txt");
            FileReader fileReader = new FileReader(file);
            StringBuffer stringBuffer = new StringBuffer();
            int numCharsRead;
            char[] charArray = new char[1024];
            while ((numCharsRead = fileReader.read(charArray)) > 0) {
                stringBuffer.append(charArray, 0, numCharsRead);
            }
            fileReader.close();
            loaded = (stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
