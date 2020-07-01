package application;

import network.AddressedRequest;
import network.AddressedResponse;

public class HandlerRequest implements Runnable {

    public Context context;

    public HandlerRequest(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        try {
            if (!context.queueAddressedRequests.isEmpty()) {
                AddressedRequest addressedRequest = context.queueAddressedRequests.poll();
                context.queueAddressedResponse.add(new AddressedResponse(addressedRequest.getSocketAddress(), context.handlerCommands.executeCommand(addressedRequest.getRequest())));
            }
        } catch (Exception e) {

        }
    }
}
