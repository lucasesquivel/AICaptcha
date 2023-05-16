import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckBoxPanelExample extends JFrame {
    private JCheckBox checkBox;
    private JPanel panel;
    
    public CheckBoxPanelExample() {
        setTitle("Checkbox Panel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the checkbox and add an item listener
        checkBox = new JCheckBox("Add Border");
        checkBox.addItemListener(new CheckBoxListener());
        
        // Create the panel and set its layout
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Add some components to the panel
        panel.add(new JLabel("This is a panel"), BorderLayout.CENTER);
        panel.add(new JButton("Click me!"), BorderLayout.SOUTH);
        
        // Add the checkbox and panel to the frame
        add(checkBox, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private class CheckBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            } else {
                panel.setBorder(null);
            }
        }
    }
    
    public static void main(String[] args) {
        new CheckBoxPanelExample();
    }
}
