import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

public class testPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);

        JPanel contentPane = new JPanel(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEtchedBorder());
            panel.setPreferredSize(new Dimension(200, 200));
            gridPanel.add(panel);
        }
        contentPane.add(gridPanel, BorderLayout.CENTER);

        JPanel panel10 = new JPanel();
        panel10.setBorder(BorderFactory.createEtchedBorder());
        panel10.setPreferredSize(new Dimension(600, 100));
        contentPane.add(panel10, BorderLayout.SOUTH);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
