package application;

import communication.Response;
import elements.Product;

public class StarterHandlerResponse implements Runnable {
    Context context;

    public StarterHandlerResponse(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        while (true) {
            if (!context.responses.isEmpty()) {
                Response response = context.responses.poll();
                if(response.getArgument() != null) {
                    context.updateArray((Product[]) response.getArgument().getValue());
                }
                if(context.mapHandlers.containsKey(response.getNameCommand())) {
                    context.mapHandlers.get(response.getNameCommand()).processing(context, response);
                } else {
                    if(response.getNameCommand() != null && response.getResultCommand() != null) {
                        context.workWindow.updateConsole(response.getNameCommand() + ": " + response.getResultCommand());
                    }
                }
            }
        }
    }
}
