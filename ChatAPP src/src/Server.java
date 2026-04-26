import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Server extends Thread{
private final String UserName;
private final JTextField UserText;
private final JTextArea messagges;
private final JButton sendButton;

    public Server(JTextField UserText,JTextArea messagges,String UserName,JButton sendButton){
        this.UserText=UserText;
        this.UserName=UserName;
        this.messagges=messagges;
        this.sendButton=sendButton;
    }

    public void run(){
        double app_version =1.0;
        try (Socket s = new Socket("itzezio.giize.com", 4999)) {

            PrintWriter pr = new PrintWriter(s.getOutputStream(), true);

            pr.println("UserName:"+UserName+"AppVersion:"+ app_version);

            UserInput userinput = new UserInput(pr, UserText, messagges, UserName, sendButton);
            UsersMessages usersmessages = new UsersMessages(s, messagges,UserName);

            userinput.start();
            usersmessages.start();

            userinput.join();
            usersmessages.join();

        } catch (IOException | InterruptedException i) {
            messagges.setText("Server Offline");
        }
    }
}
