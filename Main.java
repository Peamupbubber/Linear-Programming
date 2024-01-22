public class Main {

    /* Let's assume I have the information parsed. A linear program will look like:
     * An ArrayList of variable names,
     * An ArrayList of constraints given by Constraint classes.
     *      Each Constraint class will hold a left and right LinearSum and the OP
     *      These can be standardized meaning the Left is the LinearSum, the OP is '=', and the right is a single value
     *      A LinearSum is given by a constant (positive or negative) and a variable plus any amount of additional const_vars (can be one or the other or both but not neither)
     * A Min or Max objective function boolean
     * A LinearSum objective function
     * 
     * 
     * The variable names ArrayList will be used to ensure no duplicate vars are declared and that each variable name used is valid.
     * The constraints once standardized can have their constants put into tableaus to be solved
     */

     /* Potential idea: represent tableau as hash map mapping variable names as columns to the int[] columns?
      * 
      * So, predefined matrix of 0's, and each constraints constant can be assigned the i value of its entry in the constraint list and the j value of that varible name's index in the array (or use hashmap for speedy lookup: i.e. (x1, x2, x3) -> (1, 2, 3))
      */

    public static void main(String[] args) throws Exception {
        if(args.length < 1)
            System.out.println("No file provided!");
        else {
            LP.lp =  new LP(args[0]);
            LP.lp.display();
        }
    }
}
