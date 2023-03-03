//Code adapted from javatpoint
 
 
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.lang.Exception;
 
 public class LoginPage extends JFrame implements ActionListener {


    private JLabel title;
    private JLabel username;
    private JTextField usernameText;
    private JLabel password;
    private JTextField passwordText;
    private JLabel invalidLogin;
    JButton b1;
    JButton b2;
    JButton b3;
    private Container c;
 
    LoginPage() {
        setTitle("Software Chasing Login");
        setBounds(300, 90, 500, 525);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
 
        c = getContentPane();
        c.setLayout(null);
 
 
        title = new JLabel("Software Chasing Login");
        title.setFont(new Font("Acumin Pro", Font.BOLD, 25));
        title.setSize(400, 30);
        title.setLocation(50, 30);
        c.add(title);
 
        username = new JLabel("Username/Email");
        username.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        username.setSize(150, 25);
        username.setLocation(50, 150);
        c.add(username);
 
        usernameText = new JTextField();
        usernameText.setFont(new Font("Acumin Pro", Font.PLAIN, 12));
        usernameText.setSize(250, 25);
        usernameText.setLocation(200, 150);
        c.add(usernameText);
 
 
        password = new JLabel("Password");
        password.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        password.setSize(100, 25);
        password.setLocation(50, 200);
        c.add(password);
 
        passwordText = new JPasswordField();
        passwordText.setFont(new Font("Acumin Pro", Font.PLAIN, 12));
        passwordText.setSize(250, 25);
        passwordText.setLocation(200, 200);
        c.add(passwordText);
 
        invalidLogin = new JLabel("Invalid Login Credentials");
        invalidLogin.setFont(new Font("Acumin Pro", Font.BOLD, 12));
        invalidLogin.setForeground(Color.red);
        invalidLogin.setSize(150, 30);
        invalidLogin.setLocation(50, 300);
 
        b1 = new JButton("Login");
        b1.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        b1.setSize(100, 20);
        b1.setLocation(200, 250);
        b1.addActionListener(this);
        c.add(b1);
 
        b2 = new JButton("Register Here");
        b2.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        b2.setSize(150, 20);
        b2.setLocation(275, 450);
        b2.addActionListener(this);
        c.add(b2);
 
        b3 = new JButton("Forgot Password");
        b3.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        b3.setSize(150, 20);
        b3.setLocation(100, 450);
        b3.addActionListener(this);
        c.add(b3);
 
 
 
 
    }
 
    //define abstract method actionPerformed() which will be called on button click
    public void actionPerformed(ActionEvent e)      //pass action listener as a parameter
    {
        if (e.getSource() == b1) {
 
 
            String userValue = usernameText.getText();
            String passValue = passwordText.getText();
 
             //check whether the credentials are authentic or not
            Profile profile = dbConnection.login(userValue, passValue);
 
            if (profile != null) {   //if authentic, navigate user to a new page
                dispose();
                CharSelect.GeneratePage(profile);
            }
            else
            {
                 //Invalid login
                String def = "";
                passwordText.setText(def);
                c.add(invalidLogin);
                c.revalidate();
                c.repaint();
            }
        }
 
        else if (e.getSource() == b2) {
            dispose();
            RegistrationPage f = new RegistrationPage();
        }
 
        else if (e.getSource() == b3) {
            dispose();
            //ForgotPage a = new ForgotPage();
            //a.setVisible(true);
        }
 
        else {
             //show error message
            System.out.println("Please enter valid username and password");
        }
    }
 
    public class NewPage extends JFrame{
 
 
        NewPage() {
            setDefaultCloseOperation(javax.swing.
                    WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("Welcome");
            setSize(400, 200);
        }
 
    }
 }
 
 
 
 
 class LoginForm {
    public static void main(String arg[]) {
        try {
             //create instance of the CreateLoginForm
            LoginPage form = new LoginPage();
            form.setVisible(true);   //make form visible to the user
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
 }
