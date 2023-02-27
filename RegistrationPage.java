// Code adapted from https://www.geeksforgeeks.org/java-swing-simple-user-registration-form/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class RegistrationPage extends JFrame implements ActionListener {

    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel em;
    private JTextField tem;
    private JLabel use;
    private JTextField tuse;
    private JLabel pas;
    private JTextField tpas;
    private JLabel cpa;
    private JTextField tcpa;
    private JLabel par;
    private JLabel par1;
    private JLabel par2;
    private JLabel par3;
    private JLabel par4;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

    // Components of the profile
    private String data1;
    private String data2;
    private String data3;



    public RegistrationPage()
    {
        setTitle("Software Chasing Registration");
        setBounds(300, 90, 500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Software Chasing Registration");
        title.setFont(new Font("Acumin Pro", Font.BOLD, 25));
        title.setSize(400, 30);
        title.setLocation(50, 30);
        c.add(title);

        em = new JLabel("Email");
        em.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        em.setSize(100, 25);
        em.setLocation(50, 100);
        c.add(em);

        tem = new JTextField();
        tem.setFont(new Font("Acumin Pro", Font.PLAIN, 12));
        tem.setSize(250, 25);
        tem.setLocation(200, 100);
        c.add(tem);

        use = new JLabel("Username");
        use.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        use.setSize(100, 25);
        use.setLocation(50, 150);
        c.add(use);

        tuse = new JTextField();
        tuse.setFont(new Font("Acumin Pro", Font.PLAIN, 12));
        tuse.setSize(250, 25);
        tuse.setLocation(200, 150);
        c.add(tuse);

        pas = new JLabel("Password");
        pas.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        pas.setSize(100, 25);
        pas.setLocation(50, 200);
        c.add(pas);

        tpas = new JPasswordField();
        tpas.setFont(new Font("Acumin Pro", Font.PLAIN, 12));
        tpas.setSize(250, 25);
        tpas.setLocation(200, 200);
        c.add(tpas);

        par = new JLabel("Password Requirements");
        par.setFont(new Font("Acumin Pro", Font.ITALIC, 12));
        par.setSize(350, 25);
        par.setLocation(200, 225);
        c.add(par);

        par1 = new JLabel("At least 6 characters");
        par1.setFont(new Font("Acumin Pro", Font.ITALIC, 12));
        par1.setSize(350, 25);
        par1.setLocation(200, 240);
        c.add(par1);

        par2 = new JLabel("At least 1 capital letter");
        par2.setFont(new Font("Acumin Pro", Font.ITALIC, 12));
        par2.setSize(350, 25);
        par2.setLocation(200, 255);
        c.add(par2);

        par3 = new JLabel("At least 1 numeric character");
        par3.setFont(new Font("Acumin Pro", Font.ITALIC, 12));
        par3.setSize(350, 25);
        par3.setLocation(200, 270);
        c.add(par3);

        par4 = new JLabel("Please Enter Matching Passwords!");
        par4.setFont(new Font("Acumin Pro", Font.ITALIC, 12));
        par4.setSize(350, 25);
        par4.setLocation(200, 325);


        cpa = new JLabel("Confirm Password");
        cpa.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        cpa.setSize(150, 25);
        cpa.setLocation(50, 300);
        c.add(cpa);

        tcpa = new JPasswordField();
        tcpa.setFont(new Font("Acumin Pro", Font.PLAIN, 12));
        tcpa.setSize(250, 20);
        tcpa.setLocation(200, 300);
        c.add(tcpa);

        sub = new JButton("Submit");
        sub.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        reset.addActionListener(this);
        c.add(reset);

        tout = new JTextArea();
        tout.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);

        res = new JLabel("");
        res.setFont(new Font("Acumin Pro", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        resadd = new JTextArea();
        resadd.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        resadd.setLineWrap(true);
        c.add(resadd);

        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            data1 = tem.getText();
            data2 = tuse.getText();
            data3 = tpas.getText();
            if (!tpas.getText().equals(tcpa.getText())) {
                System.out.println("Passwords do not match");
            }

            Profile p = new Profile(data1, data2, data3);

            tout.setEditable(false);
            res.setText("Registration Successfully..");
        }


        else if (e.getSource() == reset) {
            String def = "";
            tuse.setText(def);
            tpas.setText(def);
            tcpa.setText(def);
            res.setText(def);
            tout.setText(def);
            resadd.setText(def);
        }
    }
}


class Registration {

    public static void main(String[] args) throws Exception
    {
        RegistrationPage f = new RegistrationPage();
    }
}

