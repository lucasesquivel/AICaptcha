import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class ImageGrid extends JFrame {

    private final JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5));
    private final JLabel messageLabel = new JLabel("Select the correct image!");

    private File[] imageFiles1, imageFiles2;
    private ImageIcon[] imageIcons1, imageIcons2;
    private JCheckBox[] checkBoxes = new JCheckBox[9];
    private JCheckBox correctCheckBox;

    public ImageGrid() {
        super("Image Grid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Set up the panel for the grid of images
        gridPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        for (int i = 0; i < 9; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBoxes[i] = checkBox;
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(new LineBorder(Color.BLACK));
            panel.add(checkBox, BorderLayout.CENTER);
            gridPanel.add(panel);
        }

        // Set up the label for the message
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add the panels to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(gridPanel, BorderLayout.CENTER);
        getContentPane().add(messageLabel, BorderLayout.SOUTH);

        // Load the images from the folders
        File folder1 = new File("AI");
        File folder2 = new File("Real");
        imageFiles1 = folder1.listFiles();
        imageFiles2 = folder2.listFiles();
        imageIcons1 = new ImageIcon[8];
        imageIcons2 = new ImageIcon[1];
        for (int i = 0; i < 8; i++) {
            imageIcons1[i] = new ImageIcon(imageFiles1[i].getAbsolutePath());
        }
        imageIcons2[0] = new ImageIcon(imageFiles2[0].getAbsolutePath());

        // Randomly select images for the grid
        ArrayList<ImageIcon> icons = new ArrayList<>(Arrays.asList(imageIcons1));
        icons.addAll(Arrays.asList(imageIcons1));
        icons.add(imageIcons2[0]);
        Collections.shuffle(icons);
        for (int i = 0; i < 9; i++) {
            JPanel panel = (JPanel) gridPanel.getComponent(i);
            panel.add(new JLabel(icons.get(i)), BorderLayout.CENTER);
            if (icons.get(i) == imageIcons2[0]) {
                correctCheckBox = checkBoxes[i];
            }
        }

        // Add action listeners to the checkboxes
        for (JCheckBox checkBox : checkBoxes) {
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkBox.isSelected() && checkBox == correctCheckBox) {
                        messageLabel.setText("Correct");
                    } else {
                        messageLabel.setText("Select the REAL Firetruck");
                    }
                }
            });
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ImageGrid();
            }
        });
    }
}
