import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class DrawingServer {
    private final int port;
    private final List<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private final List<DataOutputStream> chatOutputStreams = new CopyOnWriteArrayList<>();

    public DrawingServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                chatOutputStreams.add(clientHandler.getOutput());
                new Thread(clientHandler).start();
            }
        } finally {
            serverSocket.close();
        }
    }

    private class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private DataOutputStream output;

        public ClientHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            this.output = new DataOutputStream(socket.getOutputStream());
        }

        public DataOutputStream getOutput() {
            return output;
        }

        public void sendChatMessage(String message) {
            try {
                output.writeUTF("CHAT " + message);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        public void send(String message) {
            try {
                output.writeUTF(message);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try (DataInputStream input = new DataInputStream(clientSocket.getInputStream())) {
                String inputLine;
                boolean firstMessage = true;
                String userId = null;
                
                while ((inputLine = input.readUTF()) != null) {
                	if(firstMessage) {
                		userId = inputLine;
                		firstMessage = false;
                	}else if (inputLine.startsWith("CHAT")) {
                        String chatMessage = inputLine.substring(5);
                        for (DataOutputStream chatOutput : chatOutputStreams) {
                            chatOutput.writeUTF("CHAT" + userId + ": " + chatMessage);
                            chatOutput.flush();
                        }
                    } else {
                        for (ClientHandler client : clients) {
                            if (client != this) {
                                client.send(inputLine);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Client disconnected: " + e.getMessage());
            } finally {
                try {
                    clients.remove(this);
                    chatOutputStreams.remove(output);
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int port = 1000; // Use your desired port
        try {
            new DrawingServer(port).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
