import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws Exception {
        BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));
        System.out.println("сервер запущен порт:8989 ...");
        try (ServerSocket serverSocket = new ServerSocket(8989);) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    String string = in.readLine();
                    out.println(new Gson().toJson(engine.search(string)));
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}