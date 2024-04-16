import java.io.*;
import java.net.*;

public class ChatClient {

    public static final String GREEN  = "\u001B[32m";
    public static final String WHITE  = "\u001B[37m";
    public static final String RED    = "\u001B[31m";

    public static void main(String[] args) throws IOException {
        String serverAddress = "172.26.2.94";
        int PORT = 7825;

        try (Socket socket = new Socket(serverAddress, PORT)) {
            System.out.println(GREEN + "connecté au serveur chat" + WHITE );

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // créé un nouveau fil pour lire les messages du serveur
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String serverMessage;
                        while ((serverMessage = in.readLine()) != null) {
                            System.out.println(serverMessage);
                        }
                    } catch (IOException e) {
                        System.out.println(RED + "Error reading from server: " + WHITE + e.getMessage());
                    }
                }
            }).start();

            // Read messages from the user and send them to the server
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String userMessage;
            while ((userMessage = userInput.readLine()) != null) {
                out.println(userMessage);
            }
        } catch (UnknownHostException e) {
            System.err.println("No server found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
