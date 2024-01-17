import java.util.HashMap;

//Singleton pattern
public class LinearProgramScanner {
    public static LinearProgramScanner lpScanner;

    public String lastString;

    private HashMap<String, Token> reservedWords;

    public LinearProgramScanner() {
        if(lpScanner == null)
            lpScanner = this;

        initResWords();
    }
    
    private void initResWords() {
        reservedWords = new HashMap<String, Token>();

        reservedWords.put("var", Token.VAR);
        reservedWords.put("variable", Token.VAR);

        reservedWords.put("min", Token.OPT);
        reservedWords.put("minimize", Token.OPT);
        reservedWords.put("max", Token.OPT);
        reservedWords.put("maximize", Token.OPT);

        reservedWords.put("st", Token.ST);
        reservedWords.put("s.t.", Token.ST);
        reservedWords.put("subject to", Token.ST);
    }

    public Token scanner() {
        char ch = '0';
        while(ch != 0) {
            ch = FileReader.fileReader.getNextChar();

            //Ignore whitespace and newlines
            if(ch == 0 || ch == '\n' || ch == '\t' || ch == ' ')
                continue;

            // Reserved word or ID case
            else if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                boolean containsInvalidChar = false;
                String id = "";
                while((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || ch == '_' || ch == '.' || ch == ' ') {
                    //Check if the id contains '.' or ' ' for the "s.t." and "subject to" reserved words
                    if(ch == '.' || ch == ' ')
                        containsInvalidChar = true;

                    id += ch;
                    ch = FileReader.fileReader.getNextChar();
                }
                
                //check for res words, if not and invalid ERR
                //Report the type of error and line number?
                Token res = reservedWords.get(id);
                if(res != null)
                    return res;
                else if(containsInvalidChar)
                    return Token.ERR;
                else {
                    //The id is stored in lastString for use in the parser
                    lastString = id;
                    return Token.ID;
                }
                
            }
        }
        return Token.ERR;
    }
}