package gui.windows;

import application.Context;
import arguments.checkers.StringSize;
import arguments.valid.ValidString;
import communication.Argument;
import gui.adapted_components.CheckedField;
import gui.adapted_components.LanguageMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class AuthorizationWindow extends JFrame {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 160;

    public Context context;

    public JMenuBar menuBar;
    public LanguageMenu languageMenu;
    public JLabel loginText;
    public JLabel passwordText;
    public CheckedField loginField;
    public CheckedField passwordField;
    public JButton signInButton;
    public JButton signUpButton;

    public BorderLayout generalLayout;
    public GridLayout elementsLayout;
    public JPanel elementsPanel;

    public AuthorizationWindow(Context context) {
        super();

        this.context = context;

        menuBar = new JMenuBar();
        languageMenu = new LanguageMenu(context);
        loginText = new JLabel("Логин");
        context.components.put("auth.loginText", loginText);
        passwordText = new JLabel("Пароль");
        context.components.put("auth.passwordText", passwordText);
        loginField = new CheckedField(new ValidString(new StringSize(1)));
        passwordField = new CheckedField(new ValidString(new StringSize(1)));
        signInButton = new JButton("Вход");
        context.components.put("auth.signInButton", signInButton);
        signUpButton = new JButton("Регистрация");
        context.components.put("auth.signUpButton", signUpButton);


        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    context.sendRequest("login", new Argument(loginField.getValue()), new Argument(passwordField.getValue()));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Некорректно введены данные!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    context.sendRequest("registration", new Argument(loginField.getValue()), new Argument(passwordField.getValue()));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Некорректно введены данные!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        menuBar.add(languageMenu);

        generalLayout = new BorderLayout();
        elementsLayout = new GridLayout();
        elementsPanel = new JPanel();

        elementsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        elementsLayout.setColumns(2);
        elementsLayout.setRows(3);
        elementsPanel.setLayout(elementsLayout);
        elementsPanel.add(loginText);
        elementsPanel.add(loginField);
        elementsPanel.add(passwordText);
        elementsPanel.add(passwordField);
        elementsPanel.add(signInButton);
        elementsPanel.add(signUpButton);


        setLayout(generalLayout);
        setSize(WIDTH, HEIGHT);
        add(elementsPanel);
        add(menuBar, generalLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Окно авторизации");
        context.components.put("auth.title", this);
//        applyLocale(languageMenu.lang);
    }

    public void applyLocale(String baseName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName);
        for (String str : context.components.keySet()) {
            Component component = context.components.get(str);
            if(component instanceof JButton) {
                ((JButton) component).setText(resourceBundle.getString(str));
            }
            if(component instanceof JLabel) {
                ((JLabel) component).setText(resourceBundle.getString(str));
            }
            if(component instanceof JFrame) {
                ((JFrame) component).setTitle(resourceBundle.getString(str));
            }
            if(component instanceof LanguageMenu) {
                ((LanguageMenu) component).setText(resourceBundle.getString(str));
                ((LanguageMenu) component).updateUI();
            }
        }
    }

}
