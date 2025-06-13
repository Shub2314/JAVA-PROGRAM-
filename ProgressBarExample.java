import javax.swing.*;
import java.awt.*;

public class ProgressBarExample {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Progress Bar Example");
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        frame.setLayout(new BorderLayout());
        frame.add(progressBar, BorderLayout.CENTER);

        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Simulating a task
        for (int i = 0; i <= 100; i++) {
            progressBar.setValue(i);
            Thread.sleep(50);  // Simulate work
        }
    }
}
