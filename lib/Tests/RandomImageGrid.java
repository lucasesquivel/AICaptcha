import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class RandomImageGrid extends JFrame {
    
    private JPanel imagePanel;
    private JLabel[][] imageLabels;
    private String folderName;

    public RandomImageGrid() {
        setTitle("AI Captcha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Choose a random folder
        File[] folders = new File("lib/Images").listFiles(File::isDirectory);
        if (folders != null && folders.length > 0) {
            folderName = folders[(int) (Math.random() * folders.length)].getName();
        } else {
            JOptionPane.showMessageDialog(this, "No folders found!");
            System.exit(1);
        }
        
        // Create the image panel
        imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(3, 3));
        add(imagePanel, BorderLayout.CENTER);

        // Load the images
        File aiFolder = new File("lib/Images/" + folderName + "/AI");
        File realFolder = new File("lib/Images/" + folderName + "/Real");
        File[] aiFiles = aiFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        File[] realFiles = realFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));

        // Shuffle the AI files and pick 8 random ones
        shuffleArray(aiFiles);
        File[] selectedAiFiles = new File[8];
        System.arraycopy(aiFiles, 0, selectedAiFiles, 0, 8);

        // Pick a random Real file
        File selectedRealFile = realFiles[(int) (Math.random() * realFiles.length)];

        // Shuffle the array of files (including the Real file)
        File[] allFiles = new File[9];
        System.arraycopy(selectedAiFiles, 0, allFiles, 0, 8);
        allFiles[8] = selectedRealFile;
        shuffleArray(allFiles);

        // Create the image labels and add them to the panel
        imageLabels = new JLabel[3][3];
        for (int i = 0; i < 9; i++) {
            try {
                JLabel label = new JLabel(new ImageIcon(ImageIO.read(allFiles[i])));
                imageLabels[i / 3][i % 3] = label;
                imagePanel.add(label);
            } catch (IOException e) {
                System.out.println("Error loading image: " + e.getMessage());
            }
        }

        addCheckboxesToPanel(imagePanel);
        // Show the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addCheckboxesToPanel(JPanel panel) {
        Component[] components = panel.getComponents();
    
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                JCheckBox checkBox = new JCheckBox();
                checkBox.setBorder(BorderFactory.createEmptyBorder());
                checkBox.setContentAreaFilled(false);
                checkBox.setFocusPainted(false);
                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                checkBox.setVerticalAlignment(SwingConstants.BOTTOM);
                label.setLayout(new BorderLayout());
                label.add(checkBox, BorderLayout.CENTER);
            }
        }
    }
    

    // Shuffle array
    private static void shuffleArray(Object[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = (int) (Math.random() * (i + 1));
            Object temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }


    
    
    
    

    public static void main(String[] args) {
        new RandomImageGrid();
    }
}
