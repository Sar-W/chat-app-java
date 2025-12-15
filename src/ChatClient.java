import java.io.*;
import java.net.*;

public class ChatClient {

    public static void main(String[] args) throws Exception {

        Socket s = new Socket("127.0.0.1", 5555);

        BufferedReader br =
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        BufferedReader user =
                new BufferedReader(new InputStreamReader(System.in));

        new Thread(() -> {
            try {
                String m;
                while ((m = br.readLine()) != null) {
                    System.out.println(m);
                }
            } catch (Exception e) {
                System.out.println("server closed");
            }
        }).start();

        String msg;
        while ((msg = user.readLine()) != null) {
            pw.println(msg);
        }
    }
}
