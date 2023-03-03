import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MenuButtons implements ActionListener {
    private static JFrame frame;
    private JPanel panel;
    private JButton reportButton, feedbackButton, creditsButton, backButton, submitButton;
    private JTextArea textArea;
    private JLabel prompt, prompt2, prompt3, prompt4, prompt5, prompt6, prompt7, prompt8, prompt9;
    private String userName;
    public int identifier = 0;

    public MenuButtons(String userName) {
        this.userName = userName;



        frame = new JFrame("Buttons");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        // Set the size of the panel
        panel.setPreferredSize(new Dimension(1920, 1080));
        panel.setBackground(Color.decode("#cfb991"));

        // Create the report button
        reportButton = new JButton("Report");
        reportButton.setBounds(350, 250, 300, 100);
        reportButton.addActionListener(this);
        reportButton.setBackground(Color.decode("#9d9795"));
        panel.add(reportButton);

        feedbackButton = new JButton("Feedback");
        feedbackButton.setBounds(700, 250, 300, 100);
        feedbackButton.addActionListener(this);
        feedbackButton.setBackground(Color.decode("#daaa00"));
        panel.add(feedbackButton);

        creditsButton = new JButton("Credits");
        creditsButton.setBounds(1050, 250, 300, 100);
        creditsButton.addActionListener(this);
        creditsButton.setBackground(Color.decode("#ddb945"));
        panel.add(creditsButton);



        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reportButton) {
            identifier = 1;
            // Remove the report and feedback buttons
            panel.remove(reportButton);
            panel.remove(feedbackButton);
            panel.remove(creditsButton);

            // Create the prompt label and add it to the panel
            prompt = new JLabel("Please report any bugs that you have encountered during the game.");
            prompt.setBounds(750, 150, 600, 30);
            panel.add(prompt);

            // Create the text area and add it to the panel
            textArea = new JTextArea();
            textArea.setBounds(650, 200, 600, 300);
            textArea.setLineWrap(true);
            textArea.setBackground(Color.decode("#c4bfc0"));
            panel.add(textArea);

            // Create the back button and add it to the panel
            backButton = new JButton("Back");
            backButton.setBounds(650, 550, 100, 30);
            backButton.addActionListener(this);
            backButton.setBackground(Color.decode("#9d9795"));
            panel.add(backButton);

            // Create the submit button and add it to the panel
            submitButton = new JButton("Submit");
            submitButton.setBounds(1150, 550, 100, 30);
            submitButton.addActionListener(this);
            submitButton.setBackground(Color.decode("#daaa00"));
            panel.add(submitButton);

            // Refresh the panel to display the new components
            panel.revalidate();
            panel.repaint();
        }
        else if (e.getSource() == feedbackButton) {
            identifier = 2;
            // Remove the report button and feedback buttons
            panel.remove(feedbackButton);
            panel.remove(reportButton);
            panel.remove(creditsButton);

            // Create the prompt label and add it to the panel
            prompt = new JLabel("Please give any positive or negative feedback about the game.");
            prompt.setBounds(775, 150, 600, 30);
            panel.add(prompt);

            // Create the text area and add it to the panel
            textArea = new JTextArea();
            textArea.setBounds(650, 200, 600, 300);
            textArea.setLineWrap(true);
            textArea.setBackground(Color.decode("#c4bfc0"));
            panel.add(textArea);

            // Create the back button and add it to the panel
            backButton = new JButton("Back");
            backButton.setBounds(650, 550, 100, 30);
            backButton.addActionListener(this);
            backButton.setBackground(Color.decode("#9d9795"));
            panel.add(backButton);

            // Create the submit button and add it to the panel
            submitButton = new JButton("Submit");
            submitButton.setBounds(1150, 550, 100, 30);
            submitButton.addActionListener(this);
            submitButton.setBackground(Color.decode("#daaa00"));
            panel.add(submitButton);

            // Refresh the panel to display the new components
            panel.revalidate();
            panel.repaint();
        }
        else if (e.getSource() == creditsButton) {
            identifier = 3;
            // Remove the report button and feedback buttons
            panel.remove(feedbackButton);
            panel.remove(reportButton);
            panel.remove(creditsButton);

            // Create the prompt label and add it to the panel
            Font font = new Font("Impact", Font.BOLD, 80);
            prompt = new JLabel("Credits");
            prompt.setFont(font);
            prompt.setBounds(800, 100, 600, 90);
            panel.add(prompt);

            Font font2 = new Font("Georgia", Font.BOLD, 40);
            prompt2 = new JLabel("Developers:");
            prompt2.setFont(font2);
            prompt2.setBounds(200, 250, 600, 50);
            panel.add(prompt2);
            prompt7 = new JLabel("Special Thanks:");
            prompt7.setFont(font2);
            prompt7.setBounds(1400, 250, 600, 50);
            panel.add(prompt7);


            Font font3 = new Font("Verdana", Font.BOLD, 20);
            prompt3 = new JLabel("Saheer Ahmad (Add explanation of contribution)");
            prompt3.setFont(font3);
            prompt3.setBounds(200, 350, 600, 30);
            panel.add(prompt3);
            prompt4 = new JLabel("Jacob Brooks (Add explanation of contribution)");
            prompt4.setFont(font3);
            prompt4.setBounds(200, 400, 600, 30);
            panel.add(prompt4);
            prompt5 = new JLabel("Hunter Ehle (Add explanation of contribution)");
            prompt5.setFont(font3);
            prompt5.setBounds(200, 450, 600, 30);
            panel.add(prompt5);
            prompt6 = new JLabel("AJ Wheatley (Add explanation of contribution)");
            prompt6.setFont(font3);
            prompt6.setBounds(200, 500, 600, 30);
            panel.add(prompt6);

            prompt8 = new JLabel("Jakob Hale");
            prompt8.setFont(font3);
            prompt8.setBounds(1400, 350, 600, 30);
            panel.add(prompt8);
            prompt9 = new JLabel("Jeff Turkstra");
            prompt9.setFont(font3);
            prompt9.setBounds(1400, 400, 600, 30);
            panel.add(prompt9);


            // Create the back button and add it to the panel
            backButton = new JButton("Back");
            backButton.setBounds(800, 800, 300, 50);
            backButton.addActionListener(this);
            backButton.setBackground(Color.decode("#9d9795"));
            panel.add(backButton);

            // Refresh the panel to display the new components
            panel.revalidate();
            panel.repaint();
        }
        else if (e.getSource() == backButton) {
            // Remove the prompt, text area, back button, and submit button
            panel.remove(prompt);
            panel.remove(backButton);

            if(identifier != 3) {
                panel.remove(textArea);
                panel.remove(submitButton);
            }
            else {
                panel.remove(prompt2);
                panel.remove(prompt3);
                panel.remove(prompt4);
                panel.remove(prompt5);
                panel.remove(prompt6);
                panel.remove(prompt7);
                panel.remove(prompt8);
                panel.remove(prompt9);
            }

            // Add the report and feedback buttons back to the panel
            panel.add(reportButton);
            panel.add(feedbackButton);
            panel.add(creditsButton);

            // Refresh the panel to display the report button
            panel.revalidate();
            panel.repaint();

            identifier = 0;

        } else if (e.getSource() == submitButton) {
            // Remove the prompt, text area, back button, and submit button
            panel.remove(prompt);
            panel.remove(textArea);
            panel.remove(backButton);
            panel.remove(submitButton);

            // Add the report and feedback buttons back to the panel
            reportButton.setBounds(350, 250, 300, 100);
            panel.add(reportButton);
            panel.add(feedbackButton);
            panel.add(creditsButton);

            // Refresh the panel to display the report button
            panel.revalidate();
            panel.repaint();

            // Store response in proper feedback or reports files

            if(identifier == 1) {
                try {
                    storeReport(userName, textArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if(identifier == 2) {
                try {
                    storeFeedback(userName, textArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            identifier = 0;
        }
    }

    // Method to store reports in a text file called Reports.txt

    public static void storeReport(String name, String message) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("Reports.txt", true));

        out.write("Name: " + name + " Message: " + message + "\n");
        out.close();


    }

    // Method to store feedback in a text file called Feedback.txt

    public static void storeFeedback(String name, String message) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("Feedback.txt", true));

        out.write("Name: " + name + " Message: " + message + "\n");
        out.close();
    }

    // Method used to change the visibility of the frame such that it will only show up in certain instances

    public static void changeVisibility() {
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        MenuButtons tester = new MenuButtons("DEFAULT");
        changeVisibility();
    }
}
