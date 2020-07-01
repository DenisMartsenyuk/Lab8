package network;

import communication.Request;
import communication.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashSet;
import java.util.Set;

public class HandlerClient {
    public final int SIZE_BUFFER = 100000;

    private SocketAddress socketAddress;
    private DatagramChannel datagramChannel;
    private Set<SocketAddress> clientsAddresses;

    public void bind(int port) throws IOException {
        socketAddress = new InetSocketAddress(port);
        datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(socketAddress);
        clientsAddresses = new HashSet<>();
    }

    protected void send(byte[] bytes, SocketAddress address) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        datagramChannel.send(byteBuffer, address);
    }

    protected void sendBroadcast(byte[] bytes, SocketAddress notAddress) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        for (SocketAddress address : clientsAddresses) {
            if (notAddress.equals(address)) {
                continue;
            }
            datagramChannel.send(byteBuffer, address);
        }
    }

    public void sendResponse(Response response, SocketAddress address) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        objectOutputStream.flush();
        objectOutputStream.close();
        byte[] byteResponse = byteArrayOutputStream.toByteArray();
        if (response.getArgument() != null) {
            Response broadcastResponse = new Response(response.getArgument());
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream2);
            objectOutputStream2.writeObject(broadcastResponse);
            objectOutputStream2.flush();
            objectOutputStream2.close();
            sendBroadcast(byteArrayOutputStream2.toByteArray(), address);
        }
        send(byteResponse, address);
    }

    public void sendAddressedResponse(AddressedResponse response) throws IOException {
        sendResponse(response.getResponse(), response.getSocketAddress());
    }

    protected byte[] receive() throws IOException {
        SocketAddress receivedAddress;
        ByteBuffer byteBuffer = ByteBuffer.allocate(SIZE_BUFFER);
        do {
            receivedAddress = datagramChannel.receive(byteBuffer);
        } while (receivedAddress == null);
        clientsAddresses.add(receivedAddress);
        socketAddress = receivedAddress;
        byteBuffer.flip();
        byte[] data = new byte[byteBuffer.limit()];
        byteBuffer.get(data, 0, byteBuffer.limit());
        return data;
    }

    protected byte[] receive(int delay) throws IOException {
        SocketAddress receivedAddress;
        long startTime = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocate(SIZE_BUFFER);
        do {
            long currentTime = System.currentTimeMillis();
            if (currentTime - startTime >= delay) {
                return new byte[0];
            }
            receivedAddress =  datagramChannel.receive(byteBuffer);
        } while (receivedAddress == null);
        clientsAddresses.add(receivedAddress);
        socketAddress = receivedAddress;
        byteBuffer.flip();
        byte[] data = new byte[byteBuffer.limit()];
        byteBuffer.get(data, 0, byteBuffer.limit());
        return data;
    }

    public Request receiveRequest() throws IOException, ClassNotFoundException {
        return receiveRequest(0);
    }

    public Request receiveRequest(int delay) throws IOException, ClassNotFoundException {
        byte[] bytes;
        if (delay == 0) {
            bytes = receive();
        }
        else {
            bytes = receive(delay);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Request request = (Request) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return request;
    }

    public AddressedRequest receiveAddressedRequest() throws IOException, ClassNotFoundException {
        AddressedRequest addressedRequest = new AddressedRequest(receiveRequest());
        addressedRequest.setSocketAddress(socketAddress);
        return addressedRequest;
    }

}
