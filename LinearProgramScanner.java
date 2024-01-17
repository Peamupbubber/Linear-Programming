import java.io.File;
import java.util.HashMap;

//Singleton pattern
public class LinearProgramScanner {
    public static LinearProgramScanner lpScanner;

    public String lastText;
    
    private HashMap<String, Token> reservedWords;
    private HashMap<Token, String> tokenNames;

    public LinearProgramScanner() {
        System.out.println("Init Linear Program Scanner");

        if(lpScanner == null)
            lpScanner = this;

        initTokenNames();
        initResWords();
    }

    private void initTokenNames() {
        tokenNames = new HashMap<Token, String>();

        tokenNames.put(Token.ID, "ID");
        tokenNames.put(Token.VAR, "VAR");
        tokenNames.put(Token.OPT, "OPT");
        tokenNames.put(Token.NEG, "NEG");
        tokenNames.put(Token.ADD, "ADD");
        tokenNames.put(Token.MUL, "MUL");
        tokenNames.put(Token.DOT, "DOT");
        tokenNames.put(Token.CONST, "CONST");
        tokenNames.put(Token.ST, "ST");
        tokenNames.put(Token.COMP, "COMP");
        tokenNames.put(Token.SEMI, "SEMI");
        tokenNames.put(Token.ERR, "ERR");
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

    public String getTokenName(Token tok) {
        return tokenNames.get(tok);
    }

    public Token scanner() {
        char ch = '0';
        while(ch != 0) {

            //Emulates the ungetc method which doesn't exist for the java scanner
            if(FileReader.fileReader.unget == 0)
                ch = FileReader.fileReader.getNextChar();
            else {
                ch = FileReader.fileReader.unget;
                FileReader.fileReader.unget = 0;
            }

            //Ignore whitespace and newlines, continue to return on EOF
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

                FileReader.fileReader.unget = ch;
                
                //check for res words, if not and invalid ERR
                //Report the type of error and line number?
                Token res = reservedWords.get(id);
                if(res != null)
                    return res;
                else if(containsInvalidChar)
                    return Token.ERR;
                else {
                    //The id is stored in lastString for use in the parser
                    lastText = id;
                    return Token.ID;
                }
            }

            // CONST case
            else if(ch >= '0' && ch <= '9') {
                String cons = "";
                while(ch >= '0' && ch <= '9') {
                    cons += ch;
                    ch = FileReader.fileReader.getNextChar();
                }

                FileReader.fileReader.unget = ch;

                lastText = cons;
                return Token.CONST;
            }

            // ADD case
            else if(ch == '+' || ch == '-') {
                return Token.ADD;
            }

            // MUL case
            else if(ch == '*') {
                return Token.MUL;
            }

            // DOT case
            else if(ch == '.') {
                return Token.DOT;
            }

            // COMP case
            else if(ch == '=') {
                ch = FileReader.fileReader.getNextChar();
                if(ch != '=') {
                    FileReader.fileReader.unget = ch;
                }
                return Token.COMP;
            }

            // SEMI case
            else if(ch == ';') {
                return Token.SEMI;
            }

            else if(ch == '<' || ch == '>') {
                ch = FileReader.fileReader.getNextChar();
                if(ch != '=') {
                    FileReader.fileReader.unget = ch;
                }
                return Token.COMP;
            }
        }
        return Token.SCANEOF;
    }
}