package application;

import java.io.EOFException;
import java.io.IOException;

public class Receiver implements Runnable {

    private Context context;

    public Receiver(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        while (true) {
            try {
                context.queueAddressedRequests.add(context.handlerClient.receiveAddressedRequest());
            } catch (EOFException e) {
                context.logger.warn("Не удалось принять запрос, т.к. он слишком объемный и не помещается в буффер.");
            } catch (ClassNotFoundException | ClassCastException e) {
                context.logger.warn("Пришел запрос, который не удалось дессериализовать.");
            } catch (IOException e) {
                context.logger.warn("Произошла ошибка при чтении запроса: " + e.getMessage());
            } catch (Exception e) {
                context.logger.warn(e.getMessage());
            }
        }
    }
}
