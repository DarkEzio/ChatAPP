import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

 class UsersMessages extends Thread {
    private Socket s;
    private JTextArea messagges;
    private String UserName;
    private String must_version;

    public UsersMessages(Socket s,JTextArea messagges,String UserName) {
        this.s = s;
        this.messagges=messagges;
        this.UserName=UserName;
    }

    @Override
    public void run() {
        String str;
        try {
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            while (true) {
                str = bf.readLine();

                if(str.startsWith(UserName+"OLD_VERSION_TO_")){

                    int x=UserName.length()+str.indexOf("OLD_VERSION_TO_");
                    must_version=str.substring(x+8);
                    //System.out.println(must_version);
                    messagges.append("\n"+"Update your app to version:"+must_version);

                }else{

                    if(str.equals("null")){

                    }else{
                        messagges.append("\n" + str);
                    }
                }

            }
        } catch (IOException e) {
            e.addSuppressed(e);
        }
    }
}