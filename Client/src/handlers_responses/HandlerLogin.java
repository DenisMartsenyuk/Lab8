package handlers_responses;

import application.Context;
import communication.Response;

import javax.swing.*;
import java.util.StringTokenizer;

public class HandlerLogin implements HandlerResponse {

    @Override
    public void processing(Context context, Response response) {
        StringTokenizer stringTokenizer = new StringTokenizer(response.getResultCommand());
        if(stringTokenizer.countTokens() == 3) {
            context.login = stringTokenizer.nextToken();
            context.password = stringTokenizer.nextToken();
            context.id = Integer.parseInt(stringTokenizer.nextToken());
            context.startMainWorks();
        }
        else {
            JOptionPane.showMessageDialog(new JFrame(), response.getResultCommand(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
