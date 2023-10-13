import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

public class GreetingServer extends Thread {
    private final ServerSocket serverSocket;


    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000000);
        String path = "C:\\Users";
    }

    public void run() {
        while(true) {
            try {
                System.out.println("Ожидание клиента на порт " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Подключилсь" + server.getLocalSocketAddress());
                RequestHandler requestHandler = new RequestHandler();
                //Читаем от clent
                while (true){
                    DataInputStream in = new DataInputStream(server.getInputStream());
                    String com = in.readUTF();
                    if (com.equals("exit")) {
                        server.close();
                        break;
                    }
                    //ОТвечаем clent
                    System.out.println(com);
                    DataOutputStream out = new DataOutputStream(server.getOutputStream());
                    String response = requestHandler.reqHand(com);
                    out.writeUTF(response);
                    out.flush();
                }

            } catch (SocketTimeoutException s) {
                System.out.println("Время сокета истекло!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String [] args) {
        int port = 6677;
        try {
            Thread t = new GreetingServer(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}