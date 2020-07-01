package gui.adapted_components;

import application.Context;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageMenu extends JMenu {

    public Context context;
    public String lang;

    public LanguageMenu(Context context) {
        super("Язык");

        this.context = context;
        this.lang = "resources/ru_RU";


        JRadioButtonMenuItem language1 = new JRadioButtonMenuItem("Русский");
        language1.setActionCommand("resources/ru_RU");
        JRadioButtonMenuItem language2 = new JRadioButtonMenuItem("Deutsche");
        language2.setActionCommand("resources/de_DE");
        JRadioButtonMenuItem language3 = new JRadioButtonMenuItem("Schwedisch");
        language3.setActionCommand("resources/sc_SC");
        JRadioButtonMenuItem language4 = new JRadioButtonMenuItem("English");
        language4.setActionCommand("resources/en_EN");

        ButtonGroup group = new ButtonGroup();
        language1.setSelected(true);
        group.add(language1);
        group.add(language2);
        group.add(language3);
        group.add(language4);
        add(language1);
        add(language2);
        add(language3);
        add(language4);

        language1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                context.authorizationWindow.applyLocale(language1.getActionCommand());
                lang = language1.getActionCommand();
            }
        });

        language2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                context.authorizationWindow.applyLocale(language2.getActionCommand());
                lang = language2.getActionCommand();
            }
        });

        language3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                context.authorizationWindow.applyLocale(language3.getActionCommand());
                lang = language3.getActionCommand();
            }
        });

        language4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                context.authorizationWindow.applyLocale(language4.getActionCommand());
                lang = language4.getActionCommand();
            }
        });

        context.components.put("menu.title", this);


    }
}
