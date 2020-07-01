import application.Context;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        context.initialization(args);
        context.run();
    }
}
