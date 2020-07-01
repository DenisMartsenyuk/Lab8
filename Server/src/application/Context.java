package application;

import network.AddressedRequest;
import network.AddressedResponse;
import network.HandlerClient;
import collection.ProductList;
import commands.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.BindException;
import java.sql.SQLException;
import java.util.concurrent.*;

public class Context {
    public final int quantityThread = 1;

    public HandlerClient handlerClient;
    public HandlerCommands handlerCommands;
    public ProductList productList;
    public HandlerDatabase handlerDatabase;

    public Logger logger;

    public BlockingQueue<AddressedRequest> queueAddressedRequests;
    public BlockingQueue<AddressedResponse> queueAddressedResponse;

    public Context() {
        handlerClient = new HandlerClient();
        handlerCommands = new HandlerCommands(this);
        handlerDatabase = new HandlerDatabase(this);

        logger = LoggerFactory.getLogger(Context.class);

        queueAddressedRequests = new LinkedBlockingQueue<>();
        queueAddressedResponse = new LinkedBlockingQueue<>();

        handlerCommands.setCommand(new CommandAdd())
                       .setCommand(new CommandAddIfMin())
                       .setCommand(new CommandClear())
                       .setCommand(new CommandHelp())
                       .setCommand(new CommandInfo())
                       .setCommand(new CommandLogin())
                       .setCommand(new CommandPrintAscending())
                       .setCommand(new CommandPrintLessUnitOfMeasure())
                       .setCommand(new CommandRegistration())
                       .setCommand(new CommandRemoveById())
                       .setCommand(new CommandRemoveByManufactCost())
                       .setCommand(new CommandRemoveFirst())
                       .setCommand(new CommandUpdateById());
    }

    public void initialization(String[] args) {
        if(args.length != 1) {
            logger.error("Некорректный ввод порта!");
            System.exit(1);
        }

        int port = 0;
        try {
           port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            logger.error("Некорректный ввод порта!");
            System.exit(1);
        }
        try {
            handlerDatabase.initialization("jdbc:postgresql://localhost:5432/postgres", "postgres", "1"); //handlerDatabase.initialization("jdbc:postgresql://localhost:5432/lab6_prob");
        } catch (Exception e) {
            logger.error("Не удалось подключиться к базе данных!");
            System.exit(1);
        }

        try {
            productList = handlerDatabase.getProductList();
        } catch (SQLException e) {
            logger.error("Не удалось прочесть коллекцию из базы данных!");
            System.exit(0);
        }
        logger.info("Коллекция заполнена.");

        try {
            handlerClient.bind(port);
            logger.info("Сервер инициализирован.");
        }
        catch (BindException e) {
            logger.error("Этот порт занят! Выберите другой порт и перезапустите программу.");
            System.exit(0);
        }
        catch (IOException e) {
            logger.error("Запустить сервер не удалось: " + e.getMessage());
            System.exit(0);
        }
    }

    private void execution() {
        logger.info("Работа сервера запущенна.");

        ThreadPoolExecutor executorReceiver = (ThreadPoolExecutor) Executors.newFixedThreadPool(quantityThread);
        for (int i = 0; i < quantityThread; ++i) {
            executorReceiver.submit(new Receiver(this));
        }

        ThreadPoolExecutor executorTransmitter = (ThreadPoolExecutor) Executors.newFixedThreadPool(quantityThread);
        while (true) {

            if(!queueAddressedRequests.isEmpty()) {
                new Thread(new HandlerRequest(this)).start();
            }

            if(!queueAddressedResponse.isEmpty()) {
                executorTransmitter.submit(new Transmitter(this));
            }
        }
    }

    public void run() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                logger.info("Завершение программы.");
            }
        });
        execution();
    }
}
