package application;

import communication.Argument;
import communication.Request;
import communication.Response;
import elements.Product;
import gui.Circle;
import gui.windows.AuthorizationWindow;
import gui.windows.WorkWindow;
import handlers_responses.HandlerLogin;
import handlers_responses.HandlerRegistration;
import handlers_responses.HandlerResponse;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Context {
    public HandlerServer handlerServer;
    public BlockingQueue<Response> responses;
    public HashMap<String, HandlerResponse> mapHandlers;

    public String login;
    public String password;
    public Integer id;

    public AuthorizationWindow authorizationWindow;
    public WorkWindow workWindow;

    public Product[] arrayProduct;
    public HashMap<Integer, Color> colorsUsers;
    public HashMap<Integer, Circle> circles;
    public HashMap<String, Component> components;

    public Context() {
        handlerServer = new HandlerServer();
        responses = new LinkedBlockingQueue<>();
        mapHandlers = new HashMap<>();

        colorsUsers = new HashMap<>();
        circles = new HashMap<>();
        components = new HashMap<>();


        mapHandlers.put("login", new HandlerLogin());
        mapHandlers.put("registration", new HandlerRegistration());
    }

    public void initialization(String[] args) {

        try {
            SwingUtilities.invokeAndWait(() -> guiInitialization());
        } catch (InterruptedException | InvocationTargetException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Неудалось загрузить gui:" + e.getMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }

        if(args.length != 2) {
            JOptionPane.showMessageDialog(new JFrame(), "Некорректно введены данные о хосте и порте!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        String host = "";
        int port = 0;
        try {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Некорректно введены данные о хосте и порте!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        try {
            handlerServer.connect(host, port);
        } catch (SocketException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Невозможно подключиться к серверу: " + e.getMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void guiInitialization() {
        authorizationWindow = new AuthorizationWindow(this);
        workWindow = new WorkWindow(this);
    }

    public void sendRequest(String name) {
        sendRequest(name, null);
    }

    public void sendRequest(String name, Argument ... arguments) {
        try {
            handlerServer.sendRequest(new Request(name, arguments).setLogin(login).setPassword(password));
        } catch (IOException e) {
            System.out.println("не отправил");
        }
    }

    public void authorization() {
        authorizationWindow.setVisible(true);
    }


    public void startMainWorks() {
        authorizationWindow.setVisible(false);
        workWindow.setUserSettings(login, colorsUsers.get(id));
        workWindow.setVisible(true);
    }

    public Color getRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }

    public Color getUniqueColor() {
        Color randomColor = getRandomColor();
        while (colorsUsers.containsValue(randomColor)) {
            randomColor = getRandomColor();
        }
        return randomColor;
    }

    public void updateResources() {
        workWindow.updateTable(arrayProduct);
        circles = new HashMap<>();
        for (int i = 0; i < arrayProduct.length; ++i) {
            Product product = arrayProduct[i];

            if(!colorsUsers.containsKey(product.getIdUser())) {
                colorsUsers.put(product.getIdUser(), getUniqueColor());
            }
            Circle circle = new Circle(product.getCoordinates().getX(), product.getCoordinates().getY(),
                    product.getPrice(), colorsUsers.get(product.getIdUser()));
            circles.put(product.getId(), circle);

        }
        workWindow.visualPanel.visualization(circles);
        workWindow.setUserSettings(login, colorsUsers.get(id));
    }

    public Product getProduct(int id) {
        for (int i = 0; i < arrayProduct.length; ++i) {
            if (arrayProduct[i].getId() == id) {
                return arrayProduct[i];
            }
        }
        return null;
    }

    public void updateArray(Product[] arrayProduct) {
        this.arrayProduct = arrayProduct;
        updateResources();
    }

    public void run() {
        authorization();
        new Thread(new Receiver(this)).start();
        new Thread(new StarterHandlerResponse(this)).start();
    }
}
