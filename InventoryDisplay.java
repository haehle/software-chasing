import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class InventoryDisplay {

    private static JFrame frame;
    private JPanel panel;
    JList<String> list;

    public InventoryDisplay(Player player) {
        frame = new JFrame("Inventory");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });

        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);

        //Set the size of the panel
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setBackground(Color.decode("#cfb991"));

        frame.getContentPane().add(panel);
        frame.pack();

        //Test
        Inventory inv = new Inventory();
        inv.addItem("sword");
        player.setInventory(inv);
        System.out.println("Inv item 1: " + player.getInventory().getItems().get(0));


        if (frame.isVisible()) {
            frame.setVisible(false);
        }
        else
        {
            frame.setVisible(true);
        }
    }

    public static void exitProcedure()
    {
        World.invOpen = 0;
        frame.dispose();
    }
}