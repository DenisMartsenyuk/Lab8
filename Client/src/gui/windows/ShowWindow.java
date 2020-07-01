package gui.windows;

import application.Context;
import elements.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShowWindow extends JFrame {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 515;

    private int rows = 0;
    private int columns = 2;

    public ShowWindow(Context context, int id) {
        super();

        Product product = context.getProduct(id);

        JPanel elements = new JPanel();

        JTextField text1 = new JTextField();
        text1.setEnabled(false);
        text1.setText(String.valueOf(product.getIdUser()));
        addElement(new JLabel("id user:"), text1, elements);
        JTextField text2 = new JTextField();
        text2.setEnabled(false);
        text2.setText(String.valueOf(product.getId()));
        addElement(new JLabel("id:"), text2, elements);
        JTextField text3 = new JTextField();
        text3.setEnabled(false);
        text3.setText(String.valueOf(product.getName()));
        addElement(new JLabel("name:"), text3, elements);
        JTextField text4 = new JTextField();
        text4.setEnabled(false);
        text4.setText(String.valueOf(product.getCoordinates().getX()));
        addElement(new JLabel("coordinate x:"), text4, elements);
        JTextField text5 = new JTextField();
        text5.setEnabled(false);
        text5.setText(String.valueOf(product.getCoordinates().getY()));
        addElement(new JLabel("coordinate y:"), text5, elements);
        JTextField text6 = new JTextField();
        text6.setEnabled(false);
        text6.setText(String.valueOf(product.getCreationDate()));
        addElement(new JLabel("creation date:"), text6, elements);
        JTextField text7 = new JTextField();
        text7.setEnabled(false);
        text7.setText(String.valueOf(product.getPrice()));
        addElement(new JLabel("price:"), text7, elements);
        JTextField text8 = new JTextField();
        text8.setEnabled(false);
        text8.setText(String.valueOf(product.getPartNumber()));
        addElement(new JLabel("part number:"), text8, elements);
        JTextField text9 = new JTextField();
        text9.setEnabled(false);
        text9.setText(String.valueOf(product.getManufactureCost()));
        addElement(new JLabel("manufacture cost:"), text9, elements);
        JTextField text10 = new JTextField();
        text10.setEnabled(false);
        text10.setText(String.valueOf(product.getUnitOfMeasure()));
        addElement(new JLabel("unit of measure:"), text10, elements);
        JTextField text11 = new JTextField();
        text11.setEnabled(false);
        text11.setText(String.valueOf(product.getManufacturer().getId()));
        addElement(new JLabel("organization id:"), text11, elements);
        JTextField text12 = new JTextField();
        text12.setEnabled(false);
        text12.setText(String.valueOf(product.getManufacturer().getName()));
        addElement(new JLabel("organization name:"), text12, elements);
        JTextField text13 = new JTextField();
        text13.setEnabled(false);
        text13.setText(String.valueOf(product.getManufacturer().getAnnualTurnover()));
        addElement(new JLabel("annual turnover:"), text13, elements);
        JTextField text14 = new JTextField();
        text14.setEnabled(false);
        text14.setText(String.valueOf(product.getManufacturer().getOrganizationType()));
        addElement(new JLabel("organization type:"), text14, elements);

        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(rows);
        gridLayout.setColumns(columns);
        elements.setLayout(gridLayout);
        elements.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(elements);

        setTitle("show");
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
