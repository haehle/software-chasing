import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReportProblem implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton reportButton, backButton, submitButton;
    private JTextArea textArea;
    private JLabel prompt;
    private String userName;

    public ReportProblem(String userName) {
        this.userName = userName;

        frame = new JFrame("Report");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        // Set the size of the panel
        panel.setPreferredSize(new Dimension(1920, 1080));

        // Create the report button
        reportButton = new JButton("Report");
        reportButton.setBounds(350, 250, 300, 100);
        reportButton.addActionListener(this);
        panel.add(reportButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reportButton) {
            // Remove the report button
            panel.remove(reportButton);

            // Create the prompt label and add it to the panel
            prompt = new JLabel("Please report any bugs that you have encountered during the game.");
            prompt.setBounds(100, 50, 600, 30);
            panel.add(prompt);

            // Create the text area and add it to the panel
            textArea = new JTextArea();
            textArea.setBounds(100, 100, 600, 300);
            textArea.setLineWrap(true);
            panel.add(textArea);

            // Create the back button and add it to the panel
            backButton = new JButton("Back");
            backButton.setBounds(150, 450, 100, 30);
            backButton.addActionListener(this);
            panel.add(backButton);

            // Create the submit button and add it to the panel
            submitButton = new JButton("Submit");
            submitButton.setBounds(550, 450, 100, 30);
            submitButton.addActionListener(this);
            panel.add(submitButton);

            // Refresh the panel to display the new components
            panel.revalidate();
            panel.repaint();
        } else if (e.getSource() == backButton) {
            // Remove the prompt, text area, back button, and submit button
            panel.remove(prompt);
            panel.remove(textArea);
            panel.remove(backButton);
            panel.remove(submitButton);

            // Add the report button back to the panel
            panel.add(reportButton);

            // Refresh the panel to display the report button
            panel.revalidate();
            panel.repaint();
        } else if (e.getSource() == submitButton) {
            // Remove the prompt, text area, back button, and submit button
            panel.remove(prompt);
            panel.remove(textArea);
            panel.remove(backButton);
            panel.remove(submitButton);

            // Add the report button back to the panel
            reportButton.setBounds(350, 250, 300, 100);
            panel.add(reportButton);

            // Refresh the panel to display the report button
            panel.revalidate();
            panel.repaint();

            // Store response
            try {
                storeResponse(userName, textArea.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    public static void storeResponse(String name, String message) throws IOException {
        System.out.println("Name: " + name + " Message: " + message);
        BufferedWriter out = new BufferedWriter(new FileWriter("Reports.txt", true));

        out.write("Name: " + name + " Message: " + message + "\n");
        out.close();


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        ReportProblem problem = new ReportProblem(userName);

    }
}
