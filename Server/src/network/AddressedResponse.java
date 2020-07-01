package network;

import communication.Response;

import java.net.SocketAddress;

public class AddressedResponse {

    private SocketAddress socketAddress;
    private Response response;

    public AddressedResponse(SocketAddress socketAddress, Response response) {
        this.socketAddress = socketAddress;
        this.response = response;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public Response getResponse() {
        return response;
    }
}
