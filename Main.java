public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length < 1)
            System.out.println("No file provided!");
        else {
            LP lp =  new LP(args[0]);
        }
    }
}
