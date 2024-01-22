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

// class Constraint {
//     private LinearSum left;
//     private LinearSum right;
//     String op;

//     public Constraint(String con) {
//         left = null;
//         right = null;
//     }

//     public void setLeftLinearSum(LinearSum ls) {
//         left = ls;
//     }

//     public void setRightLinearSum(LinearSum ls) {
//         right = ls;
//     }

//     //Simplify the two sides of the equation
//     private void simplify() {
//         System.out.println("Simplifying constraint");
//     }
// }

public class LP {
    public static LP lp;

    private List<String> variables;
    //private HashMap<String, Integer> var_indicies;
    private List<Constraint> constraints;
    private LinearSum objectiveFunction;

    // private List<String> variableIDs;
    // private List<String> constraintIDs;
    // private List<String> ofMinIDs;
    // private List<String> ofMaxIDs;

    public LP(String file) throws Exception {
        System.out.println("\nInit Linear Program");

        if(lp == null)
            lp = this;

        variables = new ArrayList<String>();
        constraints = new ArrayList<Constraint>();
        objectiveFunction = null;

        FileReader.fileReader = new FileReader(file);
    }

    public void addConstraint(Constraint constraint) {
        constraints.add(constraint);
    }

    public void displayConstraints() {
        for(Constraint c : constraints) {
            c.display();
        }
    }

    // private void initializeIDNames() {
    //     variableIDs = new ArrayList<String>();
    //     constraintIDs = new ArrayList<String>();
    //     ofMinIDs = new ArrayList<String>();
    //     ofMaxIDs = new ArrayList<String>();

    //     variableIDs.add("var");
    //     variableIDs.add("variable");

    //     constraintIDs.add("st");
    //     constraintIDs.add("s.t.");
    //     constraintIDs.add("subject to");

    //     ofMinIDs.add("min");
    //     ofMinIDs.add("minimize");

    //     ofMaxIDs.add("max");
    //     ofMaxIDs.add("maximize");
    // }

    // private void parse(String fileName) throws Exception {
    //     File file = new File(fileName);
    //     try {
    //         Scanner scanner = new Scanner(file);
    //         scanner.useDelimiter("");
    
    //         while(scanner.hasNext()) {
    //             String next = scanner.next();
    //             System.out.println(next);
    //         }
    //         scanner.close();
    //     }
    //     catch(Exception e) {
    //         System.out.println("Invalid file: " + fileName);
    //     }
    // }

    // Type type(String identifier) {
    //     identifier = identifier.toLowerCase();

    //     if(variableIDs.contains(identifier))
    //         return Type.VARIABLE;
    //     else if(constraintIDs.contains(identifier))
    //         return Type.CONSTRAINT;
    //     else if(ofMinIDs.contains(identifier))
    //         return Type.OF_MIN;
    //     else if(ofMaxIDs.contains(identifier))
    //         return Type.OF_MAX;
    //     else
    //         return Type.ERROR;
    // }
}
