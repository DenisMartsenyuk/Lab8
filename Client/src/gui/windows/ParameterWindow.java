package gui.windows;

import application.Context;
import arguments.valid.ValidArgument;
import gui.adapted_components.CheckedField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ParameterWindow extends JFrame {

    public static final int WIDTH = 250;
    public static final int HEIGHT = 90;

    public Context context;
    public JLabel jLabel;
    public CheckedField checkedField;
    public JButton confirmButton;

    public ParameterWindow(Context context, String label, ValidArgument validArgument) {
        super();

        this.context = context;
        jLabel = new JLabel(label);
        checkedField = new CheckedField(validArgument);
        confirmButton = new JButton("confirm");
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout());

        panel.add(jLabel, BorderLayout.WEST);
        panel.add(checkedField, BorderLayout.CENTER);
        panel.add(confirmButton, BorderLayout.SOUTH);

        add(panel);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                context.workWindow.setEnabled(true);
                e.getWindow().dispose();
            }
        });

    }

    public void setWidth(int width) {
        super.setSize(width, HEIGHT);
    }
}
