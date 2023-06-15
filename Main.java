public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length < 1)
            System.out.println("No file provided!");
        else {
            LP lp =  new LP(args[0]);
        }
        System.out.println("\n");

        LinearSum ls = new LinearSum();
        ls.setConstant("(2/3)");
        System.out.println(ls.getConstant());
    }
}
