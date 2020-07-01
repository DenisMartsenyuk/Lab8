package application;

import communication.Request;
import communication.Response;

import java.io.*;
import java.net.*;


public class HandlerServer {
    public final int SIZE_BUFFER = 100000;

    private SocketAddress socketAddress;
    private DatagramSocket datagramSocket;

    public void connect(String host, int port) throws SocketException {
        socketAddress = new InetSocketAddress(host, port);
        datagramSocket = new DatagramSocket();
    }

    private void send(byte[] bytes) throws IOException {
        datagramSocket.send(new DatagramPacket(bytes, bytes.length, socketAddress));
    }

    public void sendRequest(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();
        objectOutputStream.close();
        send(byteArrayOutputStream.toByteArray());
    }

    private byte[] receive() throws IOException {
        byte[] bytes = new byte[SIZE_BUFFER];
        datagramSocket.receive(new DatagramPacket(bytes, bytes.length));
        return bytes;
    }

    public Response receiveResponse() throws IOException, ClassNotFoundException {
        byte[] bytes = receive();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Response response = (Response) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return response;
    }

}