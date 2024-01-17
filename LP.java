import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

enum Type {
    VARIABLE,
    CONSTRAINT,
    OF_MIN,
    OF_MAX,
    ERROR
}

class Constraint {
    LinearSum left;
    LinearSum right;
    String op;

    public Constraint(String con) {
        left = null;
        right = null;
        parseConstraint(con);
    }

    private void parseConstraint(String con) {
        String[] items = con.split(" ");

        LinearSum currentNode = left;

        for(int i = 0; i < items.length; i++) {
            int j = 0;
            while(j < items[i].length() && constChar(items[i].charAt(j))) {
                j++;
            }
            left = new LinearSum(items[i].substring(0, j), items[i].substring(j));

            System.out.println("Item: " + con.substring(0, j));
        }
    }

    private boolean constChar(char c) {
        return c == '(' || c == ')' || c == '.' || (c >= '0' && c <= '9');
    }
}

public class LP {
    private List<String> variables;
    private HashMap<String, Integer> var_indicies;
    private List<String> constraints;
    private String objectiveFunction;

    private List<String> variableIDs;
    private List<String> constraintIDs;
    private List<String> ofMinIDs;
    private List<String> ofMaxIDs;

    public LP(String file) throws Exception {
        variables = new ArrayList<String>();
        constraints = new ArrayList<String>();
        objectiveFunction = null;

        initializeIDNames();
        parse(file);
    }

    private void initializeIDNames() {
        variableIDs = new ArrayList<String>();
        constraintIDs = new ArrayList<String>();
        ofMinIDs = new ArrayList<String>();
        ofMaxIDs = new ArrayList<String>();

        variableIDs.add("var");
        variableIDs.add("variable");

        constraintIDs.add("st");
        constraintIDs.add("s.t.");
        constraintIDs.add("subject to");

        ofMinIDs.add("min");
        ofMinIDs.add("minimize");

        ofMaxIDs.add("max");
        ofMaxIDs.add("maximize");
    }

    private void parse(String fileName) throws Exception {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("");
    
            while(scanner.hasNext()) {
                String next = scanner.next();
                System.out.println(next);
            }
            scanner.close();
        }
        catch(Exception e) {
            System.out.println("Invalid file: " + fileName);
        }
    }

    Type type(String identifier) {
        identifier = identifier.toLowerCase();

        if(variableIDs.contains(identifier))
            return Type.VARIABLE;
        else if(constraintIDs.contains(identifier))
            return Type.CONSTRAINT;
        else if(ofMinIDs.contains(identifier))
            return Type.OF_MIN;
        else if(ofMaxIDs.contains(identifier))
            return Type.OF_MAX;
        else
            return Type.ERROR;
    }
}
