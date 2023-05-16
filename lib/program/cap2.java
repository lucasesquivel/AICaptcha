import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.Font;
import javax.sound.sampled.*;

public class cap2 extends JFrame implements ActionListener{

    private String folderName;
    Boolean checkBoxKey;


    public cap2(){


        // Read parent folder and randomly choose a subfolder
        File[] folders = new File("lib/image").listFiles(File::isDirectory);
        if (folders != null && folders.length > 0) {
            folderName = folders[(int) (Math.random() * folders.length)].getName();
        } else {
            JOptionPane.showMessageDialog(this, "No folders found!");
            System.exit(1);
        }
        System.out.println("Selected folder: " + folderName);


        // Load the images
        File aiFolder = new File("lib/image/" + folderName + "/AI");
        File realFolder = new File("lib/image/" + folderName + "/Real");
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


        // Find and store Real file index
        String key = "Checkbox "; // Initialize the key to Checkbox  as a default value
        for (int i = 0; i < allFiles.length; i++) {
            File file = allFiles[i];
            String filename = file.getName();
            if (filename.startsWith("R")) {
                key = key + (i+1); // Save the index of the matching file to the key variable
                break; // Stop searching after the first match is found
            }
        }
        if (key == "Checkbox ") {
            JOptionPane.showMessageDialog(this, "No real file detected.");
            System.exit(1);
        }
        System.out.println(key);


        // JFrame creation
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setTitle("AI Captcha");

        // Program Icon
        frame.setIconImage(new ImageIcon("lib/icon/ProgramIcon.png").getImage());

        // Main JPanel
        JPanel contentPane = new JPanel(new BorderLayout());


        // 3x3 Panel creation
        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        
        // Checkboxes names array creation
        String[] checkBoxNames = {"Checkbox 1", "Checkbox 2", "Checkbox 3", 
                                  "Checkbox 4", "Checkbox 5", "Checkbox 6", 
                                  "Checkbox 7", "Checkbox 8", "Checkbox 9"};


        for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();

            // TODO: Replace checkBox with MouseListener ??
            //panel.addMouseListener(new MouseAdapter() {
            //     public void mouseClicked(MouseEvent e) {
            //         System.out.println("Mouse Clicked");
            //     }
            // });
            
            panel.setBorder(BorderFactory.createEtchedBorder());
            panel.setLayout(new BorderLayout()); // set layout to border layout
            gridPanel.add(panel);
        
            // Add images to panel grid
            if (i < allFiles.length) {
                try {
                    BufferedImage image = ImageIO.read(allFiles[i]);

                    JLabel label = new JLabel(new ImageIcon(image));
                    panel.add(label, BorderLayout.CENTER); // add label to center


                    // Create final variable for checkbox index
                    final int index = i;
                    final String cKey = key;


                    //Add checkbox to panel
                    JCheckBox checkBox = new JCheckBox();
                    checkBox.addActionListener(e -> {

                        //format popup
                        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 16));

                        if (checkBox.isSelected() && checkBoxNames[index].equals(cKey)) {

                            // Checkmark gif
                            JLabel check = new JLabel(new ImageIcon("lib/icon/check.gif"));
                            label.setPreferredSize(new Dimension(200, 200));
                            label.add(check, BorderLayout.CENTER);

                            // Play correct sound in a separate thread
                            Thread soundThread = new Thread(() -> playSound("lib/sound/correct.wav"));
                            soundThread.start();

                            // Set border, printCorrect
                            label.setBorder(BorderFactory.createLineBorder(Color.GREEN, 7));
                            checkBoxKey = true;
                            printCorrect();

                            // Close
                            frame.dispose();


                        } else {

                            // Xmark gif
                            JLabel xmark = new JLabel(new ImageIcon("lib/icon/x.gif"));
                            label.setPreferredSize(new Dimension(200, 200));
                            label.add(xmark, BorderLayout.CENTER);

                            // Play wrong sound in a separate thread
                            Thread soundThread = new Thread(() -> playSound("lib/sound/wrong.wav"));
                            soundThread.start();

                            // Set border, printWrong
                            label.setBorder(BorderFactory.createLineBorder(Color.RED, 7));
                            checkBoxKey = false;
                            printWrong();

                            // Close
                            frame.dispose();


                        }
                    });

                panel.add(checkBox, BorderLayout.NORTH);

                // Adjust checkbox rendering
                checkBox.setBorder(BorderFactory.createEmptyBorder());
                checkBox.setContentAreaFilled(false);
                checkBox.setFocusPainted(false);
                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                checkBox.setVerticalAlignment(SwingConstants.BOTTOM);
                label.setLayout(new BorderLayout());
                label.add(checkBox, BorderLayout.CENTER);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}      

        // Adds 3x3 panel to panel
        contentPane.add(gridPanel, BorderLayout.CENTER);


        // Top Panel creation
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(74,151,255));
        //buttonPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        buttonPanel.setPreferredSize(new Dimension(600, 50));
  

        // Top Label creation
        JLabel titleLabel = new JLabel("Please select the one real " + folderName + ".");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);


        contentPane.add(buttonPanel, BorderLayout.NORTH);
        buttonPanel.add(titleLabel);


        // Final Pack
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
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


    // Create dialogue box when choice is correct
    private static void printCorrect(){
        final JOptionPane optionPane = new JOptionPane("    Correct choice, proceeding...", JOptionPane.PLAIN_MESSAGE);
        final JDialog dialog = optionPane.createDialog("AI Captcha");
        optionPane.setOptions(new Object[0]); // remove "OK" button 

        new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        }).start();
        dialog.setVisible(true);     
    }


    // Create dialogue box when choice is wrong
    private static void printWrong(){
        final JOptionPane optionPane = new JOptionPane("Incorrect choice, please try again.", JOptionPane.PLAIN_MESSAGE);
        final JDialog dialog = optionPane.createDialog("AI Captcha");

        new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        }).start();
        dialog.setVisible(true);    
        new cap2();
    }


    // Sound player
    public static void playSound(String filePath) {
        try {
            // Open the audio file
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
    
            // Get the audio format
            AudioFormat audioFormat = audioInputStream.getFormat();
    
            // Create a data line info object with the audio format
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
    
            // Open a data line using the data line info
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
    
            // Start playing the audio
            sourceDataLine.start();
    
            // Read audio data from the input stream and write it to the data line
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesRead;
            while ((bytesRead = audioInputStream.read(buffer, 0, bufferSize)) != -1) {
                sourceDataLine.write(buffer, 0, bytesRead);
            }
    
            // Stop and close the data line and input stream
            sourceDataLine.drain();
            sourceDataLine.stop();
            sourceDataLine.close();
            audioInputStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }


    // Unimplented Method
    @Override
    public void actionPerformed(ActionEvent e) {
        // Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


    public static void main(String[] args) {
        new cap2();
    }
}