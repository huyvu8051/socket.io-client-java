package io.socket.client.executions;

import com.github.nkzawa.emitter.Emitter;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;

public class ConnectionFailure {

    public static void main(String[] args) throws URISyntaxException {
        int port = Integer.parseInt(System.getenv("PORT"));
        port++;
        IO.Options options = new IO.Options();
        options.forceNew = true;
        options.reconnection = false;
        final Socket socket = IO.socket("http://localhost:" + port, options);
        socket.on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("connect timeout");
            }
        }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("connect error");
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("disconnect");
            }
        });
        socket.open();
    }
}