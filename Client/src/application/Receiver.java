package application;

public class Receiver implements Runnable {

    Context context;

    public Receiver(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        while (true) {
            try {
                context.responses.add(context.handlerServer.receiveResponse());
            } catch (Exception e) {
                System.out.println("Ошибка при чтении ответа.");
            }
        }
    }
}
