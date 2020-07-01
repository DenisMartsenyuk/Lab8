package handlers_responses;

import application.Context;
import communication.Response;

public interface HandlerResponse {
    public abstract void processing(Context context, Response response);
}
