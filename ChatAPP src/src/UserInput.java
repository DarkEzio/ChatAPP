import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;


public class UserInput extends Thread {
        private PrintWriter pr;
        private JTextField UserText;
        private JTextArea messagges;
        private String UserName;
        private JButton sendButton;
        int x=0;

       public UserInput(PrintWriter pr,JTextField UserText,JTextArea messagges,String UserName,JButton sendButton) {
        this.pr=pr;
        this.UserText=UserText;
        this.messagges=messagges;
        this.UserName=UserName;
        this.sendButton=sendButton;
        }

        public void run() {

            //Manda il messaggio al server quando premi ENTER
            UserText.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(x<1){
                        pr.println("");
                    }
                    x++;
                        if (UserText.getText().isEmpty()) {
                            //if the user input is empty, do nothing
                        } else {

                            pr.println(UserText.getText());
                            messagges.append("\n" + UserName + ": " + UserText.getText());
                            UserText.setText("");
                        }

                }
            });

            //Manda il messaggio al server quando premi il bottone SEND
            sendButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(x<1){
                        pr.println("");
                    }
                    x++;
                    if(UserText.getText().isEmpty()){
                        //if the user input is empty, do nothing
                    }else {
                        pr.println(UserText.getText());
                        messagges.append("\n" + UserName + ": " + UserText.getText());
                        UserText.setText("");
                    }


                }
            });
        }
    }