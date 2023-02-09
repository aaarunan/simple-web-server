import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        int port = 6666;

        try {
            server.start(port);
            System.out.println("Server started on port " + port + ".");
        } catch (IOException e) {
            System.err.println("Could not start server!");
            System.err.println(e.getMessage());
        }

        server.run();
    }
}