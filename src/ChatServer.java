import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    static ArrayList<PrintWriter> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        ServerSocket s = new ServerSocket(5555);
        System.out.println("server started...");

        while (true) {

            Socket c = s.accept();
            PrintWriter pw = new PrintWriter(c.getOutputStream(), true);
            list.add(pw);

            new Thread(() -> {
                try {
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(c.getInputStream()));

                    String m;
                    while ((m = br.readLine()) != null) {
                        for (PrintWriter p : list)
                            p.println(m);
                    }
                } catch (Exception e) {
                    System.out.println("client disconnected");
                }
            }).start();
        }
    }
}
