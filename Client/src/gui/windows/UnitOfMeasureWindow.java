package gui.windows;

import application.Context;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UnitOfMeasureWindow extends JFrame {

    public static final int WIDTH = 290;
    public static final int HEIGHT = 200;

    public Context context;

    public ButtonGroup unitOfMeasureGroup;
    public JRadioButton milliliters;
    public JRadioButton meters;
    public JRadioButton milligrams;
    public JRadioButton grams;
    public JRadioButton squareMeters;
    public JButton confirmButton;

    private int rows;
    private int columns;

    public UnitOfMeasureWindow(Context context) {
        super();

        this.context = context;

        unitOfMeasureGroup = new ButtonGroup();
        milliliters = new JRadioButton("MILLILITERS", true);
        milliliters.setActionCommand(milliliters.getText());
        meters = new JRadioButton("METERS", false);
        meters.setActionCommand(meters.getText());
        milligrams = new JRadioButton("MILLIGRAMS", false);
        milligrams.setActionCommand(milligrams.getText());
        grams = new JRadioButton("GRAMS", false);
        grams.setActionCommand(grams.getText());
        squareMeters = new JRadioButton("SQUARE_METERS", false);
        squareMeters.setActionCommand(squareMeters.getText());
        unitOfMeasureGroup.add(milliliters);
        unitOfMeasureGroup.add(meters);
        unitOfMeasureGroup.add(milligrams);
        unitOfMeasureGroup.add(grams);
        unitOfMeasureGroup.add(squareMeters);
        confirmButton = new JButton("confirm");

        rows = 0;
        columns = 2;
        JPanel elements = new JPanel();
        addElement(new JLabel("unit of measure:"), milliliters, elements);
        addElement(new JPanel(), meters, elements);
        addElement(new JPanel(), milligrams, elements);
        addElement(new JPanel(), grams, elements);
        addElement(new JPanel(), squareMeters, elements);
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(rows);
        gridLayout.setColumns(columns);
        elements.setLayout(gridLayout);
        elements.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(elements, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                context.workWindow.setEnabled(true);
                setVisible(false);
                e.getWindow().dispose();
            }
        });
    }

    private void addElement(Component leftComponent, Component rightComponent, JPanel panel) {
        panel.add(leftComponent);
        panel.add(rightComponent);
        rows++;
    }
}
