import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class InventoryDisplay implements ActionListener {

    private static JFrame frame;
    private JPanel panel;

    public InventoryDisplay() {
        frame = new JFrame("Inventory");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        // Set the size of the panel
        panel.setPreferredSize(new Dimension(1920, 1080));
        panel.setBackground(Color.decode("#cfb991"));

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    }
}
