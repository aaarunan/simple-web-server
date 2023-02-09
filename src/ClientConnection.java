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
        while (true) {
            String response = null;
            try {
                response = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] split = response.split("-");

            if (split.length > 2) {
                throw new IllegalArgumentException("To many operator signs, only one allowed.");
            }
            if (split.length < 2) {
                throw new IllegalArgumentException("Wrong format, too few arguments");
            }

            int sum;

            try {
                int num1 = Integer.parseInt(split[0]);
                int num2 = Integer.parseInt(split[1]);
                sum = num1 - num2;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Input must be integers.");
            }
            out.println(sum);
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

}
