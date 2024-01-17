//Attempting to create a scanner / parser, ignoring how to truly link it at the moment
import java.util.Scanner;
import java.io.File;

//Singleton Pattern
public class FileReader {
    public static FileReader fileReader;

    public char unget;

    private Scanner scanner;

    public FileReader(String fileName) throws Exception {
        System.out.println("Init File Reader");

        
        if(fileReader == null)
            fileReader = this;
        
        LinearProgramScanner.lpScanner = new LinearProgramScanner();
        unget = 0;
        openFile(fileName);

        testScanner();
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

    public void testScanner() {
        Token tok = LinearProgramScanner.lpScanner.scanner();
        while(tok != Token.SCANEOF) {
            System.out.println(LinearProgramScanner.lpScanner.getTokenName(tok));
        }
    }

}