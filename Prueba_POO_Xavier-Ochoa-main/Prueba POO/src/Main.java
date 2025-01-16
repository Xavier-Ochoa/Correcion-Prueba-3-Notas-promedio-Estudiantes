import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("LOGIN");
        frame.setContentPane(new login1().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
