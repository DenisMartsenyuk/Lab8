package gui.adapted_components.listeners;

import communication.Argument;
import gui.windows.ParameterWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveParamListener implements ActionListener {

    private String name;
    private ParameterWindow parameterWindow;

    public RemoveParamListener(String name, ParameterWindow parameterWindow) {
        this.name = name;
        this.parameterWindow = parameterWindow;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            parameterWindow.context.sendRequest(name, new Argument(parameterWindow.checkedField.getValue()));
            parameterWindow.context.workWindow.setEnabled(true);
            parameterWindow.setVisible(false);
            parameterWindow.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Некорректно введены данные!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
