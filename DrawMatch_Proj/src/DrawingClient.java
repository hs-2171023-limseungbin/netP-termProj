import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class DrawingClient {
    private JFrame frame;
    private JPanel drawingPanel;
    private JTextField chatField;
    private JTextArea chatArea;
    private Socket socket;
    private DataOutputStream output;
    private int lastX = -1, lastY = -1;
    private String userId;

    public DrawingClient(String serverAddress, int serverPort) {
        try {
            socket = new Socket(serverAddress, serverPort);
            output = new DataOutputStream(socket.getOutputStream());
            
            userId = JOptionPane.showInputDialog("아이디를 입력해주세요");
            
            System.out.println("User ID: " + userId); // 아이디를 출력해 봅니다
            
            setupUI();
            new Thread(this::listenToServer).start();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void setupUI() {
        frame = new JFrame("Multi-client Drawing Application");
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };

        drawingPanel.setPreferredSize(new Dimension(800, 600));
        drawingPanel.setBackground(Color.WHITE);

        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (lastX != -1 && lastY != -1) {
                    Graphics g = drawingPanel.getGraphics();
                    g.drawLine(lastX, lastY, x, y);
                    sendDrawCommand(lastX, lastY, x, y);
                }
                lastX = x;
                lastY = y;
            }
        });

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                lastX = -1;
                lastY = -1;
            }
        });

        chatField = new JTextField();
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        
        chatArea.setRows(8);

        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        chatPanel.add(chatField, BorderLayout.SOUTH);

        frame.add(drawingPanel, BorderLayout.CENTER);
        frame.add(chatPanel, BorderLayout.SOUTH);

        chatField.addActionListener(e -> {
            String message = chatField.getText();
            sendChatMessage(message);
            chatField.setText("");
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void sendDrawCommand(int x1, int y1, int x2, int y2) {
        try {
            output.writeUTF(x1 + " " + y1 + " " + x2 + " " + y2);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendChatMessage(String message) {
        try {
        	String fullMessage = "CHAT" + userId + ": " + message;
            output.writeUTF(fullMessage);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenToServer() {
        try (DataInputStream input = new DataInputStream(socket.getInputStream())) {
            while (true) {
                String receivedMessage = input.readUTF();
                if (receivedMessage.startsWith("CHAT")) {
                    String chatMessage = receivedMessage.substring(5);
                    SwingUtilities.invokeLater(() -> chatArea.append(chatMessage + "\n"));
                } else {
                    String[] drawCommand = receivedMessage.split(" ");
                    int x1 = Integer.parseInt(drawCommand[0]);
                    int y1 = Integer.parseInt(drawCommand[1]);
                    int x2 = Integer.parseInt(drawCommand[2]);
                    int y2 = Integer.parseInt(drawCommand[3]);
                    SwingUtilities.invokeLater(() -> {
                        Graphics g = drawingPanel.getGraphics();
                        g.drawLine(x1, y1, x2, y2);
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 1000;
        new DrawingClient(serverAddress, serverPort);
    }
}
