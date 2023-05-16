import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class cap1 extends JFrame {

    public cap1(){        

        ImageIcon thumbImage = new ImageIcon("Icons/thumbsup.png");
        

        //Panel
            JPanel Panel1 = new JPanel(new GridLayout(3,3)); //init with grid layout 3x3
            Panel1.setBackground(Color.lightGray);
            Panel1.setBounds(0, 0, 600, 600);
            Panel1.setLayout(getLayout());
            Panel1.setLayout(new BorderLayout()); //makes thumb image use borderlayout alignment
            //Panel1.setLayout(null); //toggle to use manual set position



        //Label
            JLabel thumb = new JLabel();
            thumb.setText("Hi");
            thumb.setIcon(thumbImage);
            thumb.setVerticalAlignment(JLabel.CENTER); //vertical alignment
            thumb.setHorizontalAlignment(JLabel.RIGHT); //horizontal alignment
            //thumb.setBounds(0, 0, 75, 75); //if setLayout(null) can use this to manually set position


        //Frame
            this.setLayout(null); //works with setBounds of label
            this.setTitle("AI Captcha"); //sets title
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //exit out of app
            this.setResizable(false); //prevent frame resize
            this.setSize(600, 800); //dimensions of this
            this.setVisible(true); //makes frame visible


        //Panel Add
            this.add(Panel1);
            //this.add(Panel2);
            //this.add(Panel3);


        //Label Add
            Panel1.add(thumb);
            //this.add(label); //must be before pack
            //label.setBounds(100, 0, 250, 250); //set x,y pos within frame as well as dimensions


        //Icon & BG Color
            ImageIcon image = new ImageIcon("Icons/icon.jpg"); //create image icon
            this.setIconImage(image.getImage()); //change icon of frame
            this.getContentPane().setBackground(new Color(255,255,255)); //change bg color


        //Random IMG
            int counter = 0;

            while (counter <= 9) { //counter stops at 9 squares

                Random random = new Random();
                int randomValue = random.nextInt() % 2; //Chooses randomly between two folders


                if (randomValue > 0) { //AI Folder

                    File folder = new File("AI");
                    File[] listOfFiles = folder.listFiles();
            
                    List<File> imageFiles = new ArrayList<File>();
                    for (File file : listOfFiles) {
                        if (file.isFile() && file.getName().endsWith(".png")) {
                            imageFiles.add(file);
                        }
                    }
            
                    int index = random.nextInt(imageFiles.size());
            
                    ImageIcon imageIcon = new ImageIcon(imageFiles.get(index).getAbsolutePath());
                    JLabel imageLabel = new JLabel(imageIcon);
                    Panel1.add(imageLabel);
            
                    setVisible(true);
                    counter++;
                    }
                    

                else { //Real Folder

                    File folder = new File("Real");
                    File[] listOfFiles = folder.listFiles();
            
                    List<File> imageFiles = new ArrayList<File>();
                    for (File file : listOfFiles) {
                        if (file.isFile() && file.getName().endsWith(".png")) {
                            imageFiles.add(file);
                        }
                    }
            
                    int index = random.nextInt(imageFiles.size());
            
                    ImageIcon imageIcon = new ImageIcon(imageFiles.get(index).getAbsolutePath());
                    JLabel imageLabel = new JLabel(imageIcon);
                    Panel1.add(imageLabel);
            
                    setVisible(true);
                    counter++;
            }
        }
    }
}