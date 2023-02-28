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
    JButton b1;
    JButton b2;
    JPanel newPanel;
    JLabel userLabel, passLabel;
//    final JTextField  textField1, textField2;
    private Container c;

    LoginPage() {
        setTitle("Software Chasing Registration");
        setBounds(300, 90, 500, 500);
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

        b1 = new JButton("Login");
        b1.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        b1.setSize(100, 20);
        b1.setLocation(150, 450);
        b1.addActionListener(this);
        c.add(b1);

        b2 = new JButton("Register Here");
        b2.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        b2.setSize(100, 20);
        b2.setLocation(270, 450);
        b2.addActionListener(this);
        c.add(b2);


//        //create label for username/email
//        userLabel = new JLabel();
//        userLabel.setText("Username/Email");      //set label value for textField1
//
//        //create text field to get username from the user
//        textField1 = new JTextField(15);    //set length of the text
//
//        //create label for password
//        passLabel = new JLabel();
//        passLabel.setText("Password"); //set label value for textField2
//
//        //create text field to get password from the user
//        textField2 = new JPasswordField(15);    //set length for the password
//
//        //create login button
//        b1 = new JButton("LOGIN"); //set label to button
//
//        //create panel to put form elements
//        newPanel = new JPanel(new GridLayout(3, 1));
//        newPanel.add(userLabel);    //set username label to panel
//        newPanel.add(textField1);   //set text field to panel
//        newPanel.add(passLabel);    //set password label to panel
//        newPanel.add(textField2);   //set text field to panel
//        newPanel.add(b1);           //set button to panel
//
//        //set border to panel
//        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        b1.addActionListener(this);     //add action listener to button
        setTitle("SOFTWARE CHASING LOGIN");         //set title to the login form
    }

    //define abstract method actionPerformed() which will be called on button click
    public void actionPerformed(ActionEvent e)     //pass action listener as a parameter
    {
        if (e.getSource() == b1) {


//            String userValue = textField1.getText();        //get user entered username from the textField1
//            String passValue = textField2.getText();        //get user entered password from the textField2
            String userValue = usernameText.getText();
            String passValue = passwordText.getText();

            //check whether the credentials are authentic or not
            Profile profile = dbConnection.login(userValue, passValue);

            if (profile != null) {  //if authentic, navigate user to a new page

                //create instance of the NewPage
                NewPage page = new NewPage();

                //make page visible to the user
                page.setVisible(true);

                //create a welcome label and set it to the new page
                JLabel wel_label = new JLabel("Welcome: " + userValue);
                page.getContentPane().add(wel_label);
            }
            else
            {
                //Invalid login
                System.out.println("Invalid login credentials");
            }
        }

        else if (e.getSource() == b2) {
            RegistrationPage f = new RegistrationPage();
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
            form.setVisible(true);  //make form visible to the user
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
