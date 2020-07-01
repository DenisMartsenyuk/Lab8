package handlers_responses;

import application.Context;
import communication.Response;

import javax.swing.*;

public class HandlerRegistration implements HandlerResponse {

    @Override
    public void processing(Context context, Response response) {
        JOptionPane.showMessageDialog(new JFrame(), response.getResultCommand(), "Информация", JOptionPane.INFORMATION_MESSAGE);
    }
}
