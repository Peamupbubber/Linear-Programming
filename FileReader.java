//Attempting to create a scanner / parser, ignoring how to truly link it at the moment
import java.util.Scanner;
import java.io.File;

enum Token {
    ID,
    VAR,
    GREQZERO,
    OPT,
    NEG,
    ADD,
    MUL,
    DOT,
    CONST,
    ST,
    COMP,
    SEMI,
    ERR
}

//Singleton Pattern
public class FileReader {
    public static FileReader fileReader;

    private Scanner scanner;

    public FileReader(String fileName) throws Exception {
        if(fileReader == null)
            fileReader = this;
        openFile(fileName);
    }

    private void openFile(String fileName) throws Exception {
        File file = new File(fileName);
        try {
            scanner = new Scanner(file);
            scanner.useDelimiter("");
        }
        catch(Exception e) {
            System.out.println("Invalid file: " + fileName);
        }
    }

    // Returns the next char in the file stream
    // If there is nothing left in the stream, return 0 and close the scanner
    public char getNextChar() {
        if(scanner.hasNext())
            return scanner.next().charAt(0);
        else {
            scanner.close();
            return 0;
        }
    }

}