package gui.adapted_components.listeners;

import arguments.exceptions.CheckerException;
import arguments.exceptions.TypeException;
import gui.adapted_components.CheckedField;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CheckListener implements FocusListener {

    private CheckedField checkedField;

    public CheckListener(CheckedField checkedField) {
        this.checkedField = checkedField;
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        checkedField.value = null;
        checkedField.setBackground(Color.WHITE);
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        try {
            checkedField.setValue();
        } catch (CheckerException | TypeException e) {
            checkedField.setBackground(Color.RED);
        }
    }
}
