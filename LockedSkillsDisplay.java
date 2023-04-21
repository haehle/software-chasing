import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class LockedSkillsDisplay extends JFrame implements KeyListener {

    JLabel skillLabel = new JLabel("Locked Skills");
    List<Skill> lockedSkills;
    List<String> descriptions = new ArrayList<>();
    String selectedValue = "";
    JList<String> list;

    public LockedSkillsDisplay(Player player) {
        super(player.getName() + "Locked Skills");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
                dispose();
            }
        });
        setResizable(false);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        //Set the size
        setPreferredSize(new Dimension(500, 300));
        setBackground(Color.decode("#cfb991"));

        //Change skillLabel
        skillLabel.setFont(new Font(skillLabel.getFont().toString(), Font.PLAIN, 25));

        //Get locked skills
        lockedSkills = new ArrayList<>();

        if (!player.getSkills().contains(World.staminup))
        {
            lockedSkills.add(World.staminup);
        }

        if (!player.getSkills().contains(World.feelingLucky))
        {
            lockedSkills.add(World.feelingLucky);
        }

        if (!player.getSkills().contains(World.naturalHealer))
        {
            lockedSkills.add(World.naturalHealer);
        }

        if (!player.getSkills().contains(World.treasureHunter))
        {
            lockedSkills.add(World.treasureHunter);
        }

        //Add description text
        JLabel description = new JLabel();
        description.setLocation(10, 250);
        description.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        String item1 = "";
        if (player.getSkills().size() > 0)
        {
            //Player has one or more skills
            System.out.println("Player has one or more locked skills");

            DefaultListModel<String> l1 = new DefaultListModel<>();
            for (Skill skill : lockedSkills)
            {
                l1.addElement(skill.getName());
                descriptions.add(skill.getDescription());
            }
            list = new JList<>(l1);
            list.setVisibleRowCount(-1);
            JScrollPane scrollPane = new JScrollPane(list);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

            ListSelectionListener listListener = new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting() == false) {

                        if (list.getSelectedIndex() == -1) {
                            //No skill selected
                        } else {
                            //Skill selected
                            System.out.println("SELECTED VALUE: " + list.getSelectedValue());
                            selectedValue = list.getSelectedValue();
                            description.setText(descriptions.get(list.getSelectedIndex()));
                            //useButton.setEnabled(true);
                        }
                    }
                }
            };

            //Add listener
            list.addListSelectionListener(listListener);

            //Add components
            add(skillLabel);
            add(scrollPane);
            add(description);
            add(Box.createRigidArea(new Dimension(10, 10)));
        }
        else
        {
            //Player has unlocked all skills... impressive.
            skillLabel.setText("You have unlocked all skills. Impressive.");
            add(Box.createRigidArea(new Dimension(10, 10)));
            add(skillLabel);
            System.out.println("Player is skilled");
        }

        skillLabel.addKeyListener(this);
        skillLabel.setFocusable(true);
        pack();
        setVisible(true);
    }

    public static void exitProcedure()
    {
        World.lockedSkillsOpen = 0;
    }

    public void keyTyped(KeyEvent input) {
        char key = input.getKeyChar();
        if (key == 'l')
        {
            exitProcedure();
            dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Do nothing
    }
}
