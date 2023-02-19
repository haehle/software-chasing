// Code adapted from https://www.geeksforgeeks.org/java-swing-simple-user-registration-form/


// Java program to implement
// a Simple Registration Form
// using Java Swing

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
//    private JLabel gender;
//    private JRadioButton male;
//    private JRadioButton female;
//    private ButtonGroup gengp;
//    private JLabel dob;
//    private JComboBox date;
//    private JComboBox month;
//    private JComboBox year;
//    private JLabel add;
//    private JTextArea tadd;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

//    private String dates[]
//            = { "1", "2", "3", "4", "5",
//            "6", "7", "8", "9", "10",
//            "11", "12", "13", "14", "15",
//            "16", "17", "18", "19", "20",
//            "21", "22", "23", "24", "25",
//            "26", "27", "28", "29", "30",
//            "31" };
//    private String months[]
//            = { "Jan", "feb", "Mar", "Apr",
//            "May", "Jun", "July", "Aug",
//            "Sup", "Oct", "Nov", "Dec" };
//    private String years[]
//            = { "1995", "1996", "1997", "1998",
//            "1999", "2000", "2001", "2002",
//            "2003", "2004", "2005", "2006",
//            "2007", "2008", "2009", "2010",
//            "2011", "2012", "2013", "2014",
//            "2015", "2016", "2017", "2018",
//            "2019" };

    // constructor, to initialize the components
    // with default values.
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

//        gender = new JLabel("Gender");
//        gender.setFont(new Font("Arial", Font.PLAIN, 20));
//        gender.setSize(100, 20);
//        gender.setLocation(100, 200);
//        c.add(gender);
//
//        male = new JRadioButton("Male");
//        male.setFont(new Font("Arial", Font.PLAIN, 15));
//        male.setSelected(true);
//        male.setSize(75, 20);
//        male.setLocation(200, 200);
//        c.add(male);
//
//        female = new JRadioButton("Female");
//        female.setFont(new Font("Arial", Font.PLAIN, 15));
//        female.setSelected(false);
//        female.setSize(80, 20);
//        female.setLocation(275, 200);
//        c.add(female);
//
//        gengp = new ButtonGroup();
//        gengp.add(male);
//        gengp.add(female);

//        dob = new JLabel("DOB");
//        dob.setFont(new Font("Acumin Pro", Font.PLAIN, 20));
//        dob.setSize(100, 20);
//        dob.setLocation(100, 300);
//        c.add(dob);
//
//        date = new JComboBox(dates);
//        date.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
//        date.setSize(50, 20);
//        date.setLocation(200, 300);
//        c.add(date);
//
//        month = new JComboBox(months);
//        month.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
//        month.setSize(60, 20);
//        month.setLocation(250, 300);
//        c.add(month);
//
//        year = new JComboBox(years);
//        year.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
//        year.setSize(60, 20);
//        year.setLocation(320, 300);
//        c.add(year);

//        add = new JLabel("Address");
//        add.setFont(new Font("Acumin Pro", Font.PLAIN, 20));
//        add.setSize(100, 20);
//        add.setLocation(100, 350);
//        c.add(add);
//
//        tadd = new JTextArea();
//        tadd.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
//        tadd.setSize(200, 75);
//        tadd.setLocation(200, 350);
//        tadd.setLineWrap(true);
//        c.add(tadd);
//
//        term = new JCheckBox("Accept Terms And Conditions.");
//        term.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
//        term.setSize(250, 20);
//        term.setLocation(150, 400);
//        c.add(term);

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

//        tout = new JTextArea();
//        tout.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
//        tout.setSize(300, 400);
//        tout.setLocation(500, 100);
//        tout.setLineWrap(true);
//        tout.setEditable(false);
//        c.add(tout);
//
//        res = new JLabel("");
//        res.setFont(new Font("Acumin Pro", Font.PLAIN, 20));
//        res.setSize(500, 25);
//        res.setLocation(100, 500);
//        c.add(res);
//
//        resadd = new JTextArea();
//        resadd.setFont(new Font("Acumin Pro", Font.PLAIN, 15));
//        resadd.setSize(200, 75);
//        resadd.setLocation(580, 175);
//        resadd.setLineWrap(true);
//        c.add(resadd);

        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == sub) {
            String data1;
            String data
                    = "Username : "
                    + tuse.getText() + "\n"
                    + "Password : "
                    + tpas.getText() + "\n";
//                if (male.isSelected())
//                    data1 = "Gender : Male"
//                            + "\n";
//                else
//                    data1 = "Gender : Female"
//                            + "\n";
//                String data2
//                        = "DOB : "
//                        + (String)date.getSelectedItem()
//                        + "/" + (String)month.getSelectedItem()
//                        + "/" + (String)year.getSelectedItem()
//                        + "\n";

//                String data3 = "Address : " + tadd.getText();
//                tout.setText(data + data1 + data2 + data3);
                tout.setEditable(false);
                res.setText("Registration Successfully..");
            }
//            else {
//                tout.setText("");
//                resadd.setText("");
//                res.setText("Please accept the"
//                        + " terms & conditions..");
//            }
//        }

        else if (e.getSource() == reset) {
            String def = "";
            tuse.setText(def);
//            tadd.setText(def);
            tpas.setText(def);
            tcpa.setText(def);
            res.setText(def);
            tout.setText(def);
            term.setSelected(false);
//            date.setSelectedIndex(0);
//            month.setSelectedIndex(0);
//            year.setSelectedIndex(0);
            resadd.setText(def);
        }
    }
}

// Driver Code
class Registration {

    public static void main(String[] args) throws Exception
    {
        RegistrationPage f = new RegistrationPage();
    }
}

