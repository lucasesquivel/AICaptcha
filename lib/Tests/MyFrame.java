import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

    MyFrame(){
        ImageIcon labelImage = new ImageIcon("labelImage.png");
        ImageIcon thumbImage = new ImageIcon("thumbsup.png");

        JLabel title = new JLabel();
        title.setText("Hi");
        title.setIcon(thumbImage);
        title.setVerticalAlignment(JLabel.TOP); //sets title to top
        title.setHorizontalAlignment(JLabel.CENTER); //sets title to center
        //title.setBounds(0, 0, 75, 75); //if setLayout(null) can use this to manually set position

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setBounds(0, 0, 250, 250);
        redPanel.setLayout(getLayout());
        redPanel.setLayout(new BorderLayout()); //makes thumb image use borderlayout alignment
        //redPanel.setLayout(null); //toggle to use manual set position

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue);
        bluePanel.setBounds(250, 0, 250, 250);
        bluePanel.setLayout(new BorderLayout()); 
        //bluePanel.setLayout(null);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.green);
        greenPanel.setBounds(0, 250, 500, 250);
        greenPanel.setLayout(new BorderLayout());
        //greenPanel.setLayout(null);

        // Border border = BorderFactory.createLineBorder(Color.green, 3); //creates colored border

        // JLabel label = new JLabel(); //creates label
        // label.setText("captcha"); //set text of label
        // label.setIcon(labelImage);
        // label.setHorizontalTextPosition(JLabel.CENTER); //set text left center or right relative to labelImage
        // //label.setVerticalTextPosition(JLabel.TOP); //set text top center or bottom relative to labelImage
        // label.setForeground(new Color(200,200,200)); //set text color
        // label.setFont(new Font("Times New Roman",Font.PLAIN,30)); //set font of text
        // //label.setIconTextGap(100); //set gap of text to image
        //     //label.setBackground(Color.black); //sets bg color
        //     //label.setOpaque(true); //allows bg color change
        // label.setBorder(border); // sets border
        // label.setVerticalAlignment(JLabel.CENTER); //sets image and text to center (vert)
        // label.setHorizontalAlignment(JLabel.CENTER); //sets image and text to center (horiz)


        //label.setBounds(100, 0, 250, 250); //set x,y pos within frame as well as dimensions
        this.setLayout(null); //works with setBounds of label


        // JFrame frame = new JFrame(); //creates frame, not needed in extend
        this.setTitle("AI Captcha"); //sets title
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //exit out of app
        //this.setResizable(false); //prevent frame resize
        this.setSize(600, 600); //dimensions of this
        this.setVisible(true); //makes frame visible
        //this.add(label); //must be before pack


        this.add(bluePanel); //adds bluePanel
        this.add(redPanel); //adds redPanel
        this.add(greenPanel); //adds redPanel

        bluePanel.add(title); //adds label (image + text) inside the panel


        //this.pack(); //program will automatically size the window based off of images/texts
        ImageIcon image = new ImageIcon("icon.jpg"); //create image icon
        this.setIconImage(image.getImage()); //change icon of frame
        this.getContentPane().setBackground(new Color(150,150,150)); //change bg color
    }
}