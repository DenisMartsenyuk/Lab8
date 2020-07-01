package network;

import communication.Request;

import java.net.SocketAddress;

public class AddressedRequest {
    private SocketAddress socketAddress;
    private Request request;

    public AddressedRequest(Request request) {
        this.request = request;
    }


    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public Request getRequest() {
        return request;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }
}
