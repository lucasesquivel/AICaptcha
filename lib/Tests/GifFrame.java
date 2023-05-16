import javax.swing.*;
import java.awt.*;

public class GifFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My Animated Gif");
        JLabel label = new JLabel(new ImageIcon("lib/wolf.gif"));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
