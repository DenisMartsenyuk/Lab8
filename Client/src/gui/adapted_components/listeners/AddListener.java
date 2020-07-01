package gui.adapted_components.listeners;

import communication.Argument;
import elements.*;
import gui.windows.ProductWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddListener implements ActionListener {

    private String name;
    private ProductWindow productWindow;

    public AddListener(String name, ProductWindow productWindow) {
        this.name = name;
        this.productWindow = productWindow;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Product product = new Product((String) productWindow.name.getValue(), new Coordinates((Double) productWindow.coordinateX.getValue(),
                    (Integer) productWindow.coordinateY.getValue()), (Double) productWindow.price.getValue(), (String) productWindow.partNumber.getValue(),
                    (Long) productWindow.manufactureCost.getValue(), UnitOfMeasure.valueOf(productWindow.unitOfMeasureGroup.getSelection().getActionCommand()),
                    new Organization((String) productWindow.organizationName.getValue(), (Long) productWindow.annualTurnover.getValue(),
                            OrganizationType.valueOf(productWindow.organizationType.getSelection().getActionCommand())));
            productWindow.context.sendRequest(name, new Argument(product));
            productWindow.setVisible(false);
            productWindow.context.workWindow.setEnabled(true);
            productWindow.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Некорректно введены данные!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
