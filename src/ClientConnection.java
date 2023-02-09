import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientConnection(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(this.socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    @Override
    public void run() {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println("\r\n");
        out.println("<p> Hello world </p>");
        out.flush();
        try {
            this.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() throws IOException {
        out.flush();
        out.close();
        in.close();
        socket.close();
    }

}
