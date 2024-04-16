import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    public static final String BLUE   = "\u001B[34m";
    public static final String WHITE  = "\u001B[37m";
    public static final String RED    = "\u001B[31m";

    private static final int PORT = 7825;
    private static List<PrintWriter> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        System.out.println("serveur en cours sur le port " + PORT);
        ExecutorService pool = Executors.newFixedThreadPool(500);

        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                pool.execute(new Handler(listener.accept()));
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }

    private static class Handler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            System.out.println("client connecté : " + socket);
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // rajoute le client a la liste de partage
                clients.add(out);

                // partage des messages du client
                String input;
                while ((input = in.readLine()) != null) {
                    for (PrintWriter writer : clients) {
                        writer.println("Message de " + BLUE + socket.getInetAddress() + WHITE + ": " + input);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error handling client: " + e.getMessage());
            } finally {
                if (out != null) {
                    clients.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println(RED + "fermeture: " + socket + WHITE);
            }
        }
    }
}
