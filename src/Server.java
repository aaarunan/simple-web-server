import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private PrintWriter out;
    private BufferedReader in;

    private boolean run = false;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        run = true;
        while (run) {
            Socket client;
            ClientConnection clientConnection;
            try {
                client = serverSocket.accept();
                clientConnection = new ClientConnection(client);
            } catch (IOException e) {
                throw new RuntimeException("Error on client connection.\n" + e.getMessage());
            }
            System.out.println("New client connected from: " + client.getInetAddress());

            new Thread(clientConnection).start();
        }
    }


    public void stop() throws IOException {
        run = false;
        serverSocket.close();
    }
}
