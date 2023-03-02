//import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.lang.Exception;


public class ForgotPage extends JFrame implements ActionListener {

    private JLabel title;
    private Container c;
    private JLabel email;
    private JTextField emailText;
    private JButton sendEmail;
    private JLabel confirmation;

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
//            try {
//                EmailUtil.sendEmail("a", "b", "c", "d", "e", "f", "g", "h");
//                confirmation = new JLabel("Check your email for a forgot password message");
//                confirmation.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
//                confirmation.setSize(350, 25);
//                confirmation.setLocation(50, 250);
//            } catch (MessagingException | UnsupportedEncodingException messagingException) {
//                messagingException.printStackTrace();
//            }
        }

    }
}



