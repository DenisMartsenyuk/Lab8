package gui.adapted_components.listeners;

import communication.Argument;
import elements.UnitOfMeasure;
import gui.windows.UnitOfMeasureWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterUnitListener implements ActionListener {

    private String name;
    private UnitOfMeasureWindow unitOfMeasureWindow;

    public FilterUnitListener(String name, UnitOfMeasureWindow unitOfMeasureWindow) {
        this.name = name;
        this.unitOfMeasureWindow = unitOfMeasureWindow;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        unitOfMeasureWindow.context.sendRequest(name, new Argument(UnitOfMeasure.valueOf(unitOfMeasureWindow.unitOfMeasureGroup.getSelection().getActionCommand())));
        unitOfMeasureWindow.context.workWindow.setEnabled(true);
        unitOfMeasureWindow.setVisible(false);
        unitOfMeasureWindow.dispose();
    }
}
