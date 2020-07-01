package gui.windows;

import application.Context;
import arguments.checkers.*;
import arguments.valid.ValidDouble;
import arguments.valid.ValidInteger;
import arguments.valid.ValidLong;
import arguments.valid.ValidString;
import gui.adapted_components.CheckedField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProductWindow extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 515;

    public Context context;
    public ProductWindowType type;

    public CheckedField id;
    public CheckedField name;
    public CheckedField coordinateX;
    public CheckedField coordinateY;
    public CheckedField price;
    public CheckedField partNumber;
    public CheckedField manufactureCost;
    public ButtonGroup unitOfMeasureGroup;
    public JRadioButton milliliters;
    public JRadioButton meters;
    public JRadioButton milligrams;
    public JRadioButton grams;
    public JRadioButton squareMeters;
    public CheckedField organizationName;
    public CheckedField annualTurnover;
    public ButtonGroup organizationType;
    public JRadioButton publicButton;
    public JRadioButton government;
    public JRadioButton trust;
    public JButton confirmButton;

    private int rows;
    private int columns;

    public ProductWindow(Context context, ProductWindowType type) {
        super();

        this.context = context;
        this.type = type;

        id = new CheckedField(new ValidInteger(new IntegerLargerValue(0)));
        name = new CheckedField(new ValidString(new StringSize(1)));
        coordinateX = new CheckedField(new ValidDouble(new DoubleLargerValue(0.0), new DoubleLessValue(context.workWindow.visualPanel.HEIGHT * 1.0)));
        coordinateY = new CheckedField(new ValidInteger(new IntegerLargerValue(0), new IntegerLessValue(context.workWindow.visualPanel.WIDTH)));
        price = new CheckedField(new ValidDouble(new DoubleLargerValue(0.0), new DoubleLessValue(101.0)));
        partNumber = new CheckedField(new ValidString(new StringSize(1)));
        manufactureCost = new CheckedField(new ValidLong());
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
        organizationName = new CheckedField(new ValidString(new StringSize(1)));
        annualTurnover = new CheckedField(new ValidLong(new LongLargerValue(0L)));
        organizationType = new ButtonGroup();
        publicButton = new JRadioButton("PUBLIC", true);
        publicButton.setActionCommand(publicButton.getText());
        government = new JRadioButton("GOVERNMENT", false);
        government.setActionCommand(government.getText());
        trust = new JRadioButton("TRUST", false);
        trust.setActionCommand(trust.getText());
        organizationType.add(publicButton);
        organizationType.add(government);
        organizationType.add(trust);
        confirmButton = new JButton("confirm");

        columns = 2;
        rows = 0;
        JPanel elements = new JPanel();
        if(type == ProductWindowType.UPDATE) {
            addElement(new JLabel("id:"), id, elements);
        }
        if(type == ProductWindowType.SHOW) {

        }
        addElement(new JLabel("name:"), name, elements);
        addElement(new JLabel("coordinate X:"), coordinateX, elements);
        addElement(new JLabel("coordinate Y:"), coordinateY, elements);
        addElement(new JLabel("price:"), price, elements);
        addElement(new JLabel("part number:"), partNumber, elements);
        addElement(new JLabel("manufacture cost:"), manufactureCost, elements);
        addElement(new JLabel("unit of measure:"), milliliters, elements);
        addElement(new JPanel(), meters, elements);
        addElement(new JPanel(), milligrams, elements);
        addElement(new JPanel(), grams, elements);
        addElement(new JPanel(), squareMeters, elements);
        addElement(new JLabel("organization name:"), organizationName, elements);
        addElement(new JLabel("annual turnover:"), annualTurnover, elements);
        addElement(new JLabel("organization type:"), publicButton, elements);
        addElement(new JPanel(), government, elements);
        addElement(new JPanel(), trust, elements);

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
