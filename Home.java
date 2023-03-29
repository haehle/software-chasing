import javax.swing.*;
import java.awt.*;
import java.awt.color.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Home extends JFrame {
    private JLabel imageLabel , intro, intro2, purchase, purchased, blue_cost, green_cost, red_cost, total_gold, orange_cost;
    private BufferedImage originalImage;
    private BufferedImage modifiedImage;

    private JFrame frame, frame2;

    boolean orange_bought, blue_bought, red_bought, green_bought;

    private JPanel panel;

    private JButton customization, back, custom_back, blue, red, green, orange;

    private boolean check_b, check_g, check_r, check_o;


    public Home(Player player) {
        frame = new JFrame(player.getName() + "'s" + " home!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(false);

        intro = new JLabel("Welcome to your home! Here you can customize the color of your house!");
        intro.setBounds(300, 15, 500, 20);
        frame.add(intro);

        frame2 = new JFrame("Customization Shop!");
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setSize(1080, 720);
        frame2.setLocationRelativeTo(null);
        frame2.setLayout(null);
        frame2.setVisible(false);


        customization = new JButton("Customize");
        customization.setBounds(350, 575, 150, 75);
        frame.add(customization);

        // create a label for the image
        imageLabel = new JLabel();
        imageLabel.setBounds(300, 0, 600, 600);
        frame.add(imageLabel);

        intro2 = new JLabel("Buy and customize your house color here! Purchased colors will appear at the bottom to be selected!");
        intro2.setBounds(200, 25, 700, 20);
        frame2.add(intro2);

        purchase = new JLabel("Colors to purchase:");
        purchase.setBounds(500, 90, 250, 20);
        frame2.add(purchase);


        blue = new JButton("Blue");
        blue.setBounds(100, 150, 150, 75);
        blue.setBackground(Color.blue);
        blue.setForeground(Color.white);
        frame2.add(blue);

        blue_cost = new JLabel("100 Gold");
        blue_cost.setBounds(150,250, 150, 20);
        frame2.add(blue_cost);

        green = new JButton("Green");
        green.setBounds(300, 150, 150, 75);
        green.setBackground(Color.green);
        green.setForeground(Color.white);
        frame2.add(green);

        green_cost = new JLabel("100 Gold");
        green_cost.setBounds(350,250, 150, 20);
        frame2.add(green_cost);

        red = new JButton("Red");
        red.setBounds(500, 150, 150, 75);
        red.setBackground(Color.red);
        red.setForeground(Color.white);
        frame2.add(red);

        red_cost = new JLabel("100 Gold");
        red_cost.setBounds(550,250, 150, 20);
        frame2.add(red_cost);

        orange = new JButton("Orange");
        orange.setBounds(700, 150, 150, 75);
        orange.setBackground(Color.orange);
        orange.setForeground(Color.white);
        frame2.add(orange);

        orange_cost = new JLabel("100 Gold");
        orange_cost.setBounds(750,250, 150, 20);
        frame2.add(orange_cost);

        purchased = new JLabel("Purchased Colors:");
        purchased.setBounds(500, 400, 250, 20);
        frame2.add(purchased);

        total_gold = new JLabel("Gold: " + player.getGold());
        total_gold.setBounds(25, 600, 200, 100);
        Font font1 = new Font("Arial", Font.BOLD, 25);
        total_gold.setForeground(Color.YELLOW);
        total_gold.setFont(font1);
        frame2.add(total_gold);

        customization.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    blue_cost.setText("100 Gold");
                    Font font1 = new Font("SansSerif", Font.BOLD, 12);
                    blue_cost.setForeground(Color.BLACK);
                    blue_cost.setFont(font1);
                    green_cost.setText("100 Gold");
                    green_cost.setForeground(Color.BLACK);
                    green_cost.setFont(font1);
                    red_cost.setText("100 Gold");
                red_cost.setForeground(Color.BLACK);
                red_cost.setFont(font1);
                    orange_cost.setText("100 Gold");
                orange_cost.setForeground(Color.BLACK);
                orange_cost.setFont(font1);

                frame2.setVisible(true);
            }
        });

        back = new JButton("Back");
        back.setBounds(550, 575, 150, 75);
        frame.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == back) {
                    frame.dispose();
                }
            }
        });

        custom_back = new JButton("Back");
        custom_back.setBounds(450, 575, 150, 75);
        frame2.add(custom_back);

        custom_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == custom_back) {
                    frame2.dispose();
                }
            }
        });

        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == blue) {
                    if(player.getGold() >= 100 && !blue_bought) {
                        blue_bought = true;
                        player.setGold(player.getGold() - 100);
                        setColor(Color.BLUE);
                        total_gold.setText("Gold: " + player.getGold());
                        blue.setBounds(100, 450, 150, 75);
                        blue_cost.setVisible(false);
                    }
                    else if(blue_bought) {
                        setColor(Color.blue);
                    }
                    else {
                        blue_cost.setText("Not enough gold!");
                        blue_cost.setForeground(Color.RED);
                        Font font = new Font("Arial", Font.BOLD, 14);
                        blue_cost.setFont(font);

                    }
                }
            }
        });

        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == green) {
                    if(player.getGold() >= 100 && !green_bought) {
                        green_bought = true;
                        player.setGold(player.getGold() - 100);
                        setColor(Color.GREEN);
                        total_gold.setText("Gold: " + player.getGold());
                        green.setBounds(300, 450, 150, 75);
                        green_cost.setVisible(false);
                    }
                    else if(green_bought) {
                        setColor(Color.green);
                    }
                    else {
                        green_cost.setText("Not enough gold!");
                        green_cost.setForeground(Color.RED);
                        Font font = new Font("Arial", Font.BOLD, 14);
                        green_cost.setFont(font);

                    }
                }
            }
        });

        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == red) {
                    if(player.getGold() >= 100 && !red_bought) {
                        red_bought = true;
                        player.setGold(player.getGold() - 100);
                        setColor(Color.red);
                        total_gold.setText("Gold: " + player.getGold());
                        red.setBounds(500, 450, 150, 75);
                        red_cost.setVisible(false);
                    }
                    else if(red_bought) {
                        setColor(Color.red);
                    }
                    else {
                        red_cost.setText("Not enough gold!");
                        red_cost.setForeground(Color.RED);
                        Font font = new Font("Arial", Font.BOLD, 14);
                        red_cost.setFont(font);

                    }
                }
            }
        });

        orange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == orange) {
                    if(player.getGold() >= 100 && !orange_bought) {
                        orange_bought = true;
                        player.setGold(player.getGold() - 100);
                        setColor(Color.orange);
                        total_gold.setText("Gold: " + player.getGold());
                        orange.setBounds(700, 450, 150, 75);
                        orange_cost.setVisible(false);
                    }
                    else if(orange_bought) {
                        setColor(Color.orange);
                    }
                    else {
                        orange_cost.setText("Not enough gold!");
                        orange_cost.setForeground(Color.RED);
                        Font font = new Font("Arial", Font.BOLD, 14);
                        orange_cost.setFont(font);

                    }
                }
            }
        });




    }

    public void setImage(String filename) {
        try {
            originalImage = ImageIO.read(new File(filename));
            modifiedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = modifiedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, null);
            g.dispose();
            imageLabel.setIcon(new ImageIcon(modifiedImage));
        } catch (IOException ex) {
            System.out.println("Failed to load image: " + ex.getMessage());
        }
    }

    public void setColor(Color color) {
        // create a new buffered image with the same dimensions as the original image
        modifiedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // create a color conversion filter that converts the image to grayscale
        ColorConvertOp grayscaleOp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);

        // apply the grayscale filter to the original image, and store the result in the modified image
        grayscaleOp.filter(originalImage, modifiedImage);

        // create a rescale filter that multiplies each grayscale value of the modified image by the corresponding color component of the specified color
        float[] scales = {
                (float) color.getRed() / 255f,
                (float) color.getGreen() / 255f,
                (float) color.getBlue() / 255f,
                (float) color.getAlpha() / 255f
        };
        float[] offsets = new float[4];
        RescaleOp colorizeOp = new RescaleOp(scales, offsets, null);

        // apply the colorize filter to the modified image, and store the result back in the modified image
        colorizeOp.filter(modifiedImage, modifiedImage);

        // update the image label with the modified image
        imageLabel.setIcon(new ImageIcon(modifiedImage));
    }

    public void changeVisibility(boolean check) {
        frame.setVisible(check);
    }
}

