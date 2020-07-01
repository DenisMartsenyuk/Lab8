package application;

import network.AddressedResponse;

import java.io.IOException;

public class Transmitter implements Runnable {

    private Context context;

    public Transmitter(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        try {
            if (!context.queueAddressedResponse.isEmpty()) {
                AddressedResponse response = context.queueAddressedResponse.poll();
                try {
                    context.handlerClient.sendAddressedResponse(response);
                } catch (IOException e) {
                    context.logger.warn("Произошла ошибка при отправке ответа: " + e.getMessage());
                }
            }
        } catch (Exception e) {

        }
    }
}
