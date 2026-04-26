/*To-do{
//
 salvare la chat per quando rientra l'utente riesce a vedere i vecchi messaggi
//
 Posibile soluzione:

    creare un file txt dove salvare le chat per poi quando rientra l'utente, printare tutti i messaggi sulla variabile messages

    (opzionale):
    cryptare i messaggi salvati nel file

}*/

//Leggere/Scrivere nei file
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

//Grafica
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;


public class ChatAPP extends JFrame {
    private static String UserName;

    private static JFrame LogIn;
    private static JButton Logedas;
    private static JLabel labelLogin;
    private static JTextField textlogin;
    private static JButton submitlog;
    private static JLabel empty1;
    private static JLabel empty2;
    private static JLabel empty3;
    private static JLabel empty4;
    private static JLabel empty5;

    private JTextArea messagges;
    private JLabel Logged;
    private JPanel panel;
    private JButton sendButton;
    private JTextField UserText;
    private JScrollPane messagges_scroll;
    private static File FileUser;
    private static File FileMsgs;
    private static FileWriter writer;
    private static Scanner user;


    public ChatAPP() throws InterruptedException {

        messagges.setEditable(false);
        messagges.setLineWrap(true);

    }

    public static void main(String[] args)  throws IOException, InterruptedException {

         FileUser= new File("user.txt");
         FileMsgs= new File("msgs.txt");

         if(!FileMsgs.exists()){

             FileMsgs.createNewFile();

         }

        if(!FileUser.exists()){
            try {

                do {

                    UserName = JOptionPane.showInputDialog("Enter Your Nickname \n (more than 3 characters)");

                }while(UserName.length()<=3 || UserName.equals("null"));

                writer= new FileWriter(FileUser);

                FileUser.createNewFile();

                writer.write("Nickname:"+ UserName);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try{
                user= new Scanner(FileUser);
            while(user.hasNextLine()){
                String data=user.nextLine();
                UserName=data.substring(9);
            }
            user.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }


        ChatAPP app=new ChatAPP();
        app.pack();
        app.setLocationRelativeTo(null);
        app.setContentPane(app.panel);
        app.setBackground(Color.BLACK);
        app.setTitle("ChatAPP");
        app.setSize(400,540);
        app.setResizable(false);
        app.setVisible(false);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LogIn= new JFrame();
        LogIn.pack();
        LogIn.setLocationRelativeTo(null);
        Logedas= new JButton();
        labelLogin= new JLabel();
        textlogin= new JTextField();
        submitlog= new JButton();
        empty1=new JLabel();
        empty2=new JLabel();
        empty3=new JLabel();
        empty4=new JLabel("Or",SwingConstants.CENTER);
        empty5=new JLabel();

        empty4.setForeground(Color.WHITE);

        LogIn.setResizable(false);
        LogIn.getContentPane().setBackground(Color.DARK_GRAY);
        LogIn.setVisible(true);

        LogIn.setSize(400,100);
        LogIn.setLayout(new GridLayout(3,3));

        Logedas.setText("Login as: " + UserName);
        Logedas.setBackground(Color.GREEN);
        Logedas.setForeground(Color.BLACK);

        textlogin.setBackground(Color.BLACK);
        textlogin.setForeground(Color.WHITE);
        textlogin.setBorder(new LineBorder(Color.BLUE,2));

        labelLogin.setForeground(Color.WHITE);
        labelLogin.setText("Change Username:");

        submitlog.setText("Submit");

        LogIn.add(empty1);
        LogIn.add(Logedas);

        LogIn.add(empty2);
        LogIn.add(empty3);
        LogIn.add(empty4);
        LogIn.add(empty5);

        LogIn.add(labelLogin);
        LogIn.add(textlogin);
        LogIn.add(submitlog);

        LogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Quando l'utente vuole cambiare il suo username

            submitlog.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(textlogin.getText().isEmpty()){

                    }else {
                        UserName = textlogin.getText();
                        app.Logged.setText(app.Logged.getText()+ UserName);
                        Server server=new Server(app.UserText,app.messagges,UserName,app.sendButton);
                        server.start();
                        try {

                            writer = new FileWriter(FileUser);
                            writer.write("Nickname:" + UserName);
                            writer.close();

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        LogIn.setVisible(false);
                        app.setVisible(true);

                    }
                }
            });

            //Quando l'utente vuole entrare con il suo nickname esistente
        
            Logedas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LogIn.setVisible(false);
                    app.Logged.setText(app.Logged.getText()+ UserName);
                    Server server=new Server(app.UserText,app.messagges,UserName,app.sendButton);
                    server.start();
                    app.setVisible(true);
                }
            });

    }
}