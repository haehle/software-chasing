 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.io.UnsupportedEncodingException;
 import java.lang.Exception;
 import java.nio.charset.*;
 import java.util.*;
 
 
 public class ForgotPage extends JFrame implements ActionListener {
 
     private JLabel title;
     private Container c;
     private JLabel email;
     private JTextField emailText;
     private JButton sendEmail;
     private JLabel confirmation;
     private JButton login;
 
 
     ForgotPage() {
         setTitle("Software Chasing Forgot Password");
         setBounds(300, 90, 500, 500);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         setResizable(false);
 
         c = getContentPane();
         c.setLayout(null);
 
         title = new JLabel("Software Chasing Forgot Password");
         title.setFont(new Font("Acumin Pro", Font.BOLD, 25));
         title.setSize(450, 30);
         title.setLocation(20, 30);
         c.add(title);
 
         email = new JLabel("Email");
         email.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
         email.setSize(150, 25);
         email.setLocation(50, 150);
         c.add(email);
 
         emailText = new JTextField();
         emailText.setFont(new Font("Acumin Pro", Font.PLAIN, 12));
         emailText.setSize(250, 25);
         emailText.setLocation(200, 150);
         c.add(emailText);
 
         sendEmail = new JButton("Send Email");
         sendEmail.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
         sendEmail.setSize(150, 20);
         sendEmail.setLocation(200, 200);
         sendEmail.addActionListener(this);
         c.add(sendEmail);

         login = new JButton("Return to login page");
         login.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
         login.setSize(150, 20);
         login.setLocation(200, 300);
         login.addActionListener(this);
         c.add(login);
 
         confirmation = new JLabel("Check Email to reset password");
         confirmation.setFont(new Font("Acumin Pro", Font.BOLD, 15));
         confirmation.setSize(300, 35);
         confirmation.setLocation(50, 250);
 
 
     }
 
     public void actionPerformed(ActionEvent e) {
         if (e.getSource() == sendEmail) {
             c.add(confirmation);
             c.revalidate();
             c.repaint();

             //Generate a random 10 digit one time password using numbers and letters
             String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                     + "0123456789"
                     + "abcdefghijklmnopqrstuvxyz";

             //create StringBuffer size of AlphaNumericString
             StringBuilder sb = new StringBuilder(10);

             for (int i = 0; i < 10; i++) {

                 //generate a random number between
                 //0 to AlphaNumericString variable length
                 int index
                         = (int) (AlphaNumericString.length()
                         * Math.random());

                 //add Character one by one in end of sb
                 sb.append(AlphaNumericString
                         .charAt(index));
             }

         } else if (e.getSource() == login) {
             dispose();
             LoginPage l = new LoginPage();
             l.setVisible(true);
 
               //Send an email from us (developers) to the user's email specifying a one time password to be used to login
 
             /*try {
                 EmailUtil.sendEmail("smtp.gmail.com", "25", "CHANGE THIS TO A DEVELOPER EMAIL", "SoftwareChasing", "CHANGE THIS TO A DEVELOPER PASSWORD", emailText.getText(), "Password Reset", "Here is a one time password, please use this to login: " + sb);
                 confirmation = new JLabel("Check your email for a forgot password message");
                confirmation.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
                 confirmation.setSize(350, 25);
                 confirmation.setLocation(50, 250);
             } catch (UnsupportedEncodingException | MessagingException messagingException) {
                 messagingException.printStackTrace();
             } */
         }
 
     }
 }
 
 
 
