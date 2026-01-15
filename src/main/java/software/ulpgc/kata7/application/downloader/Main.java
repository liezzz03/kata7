package software.ulpgc.kata7.application.downloader;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    public Main() throws HeadlessException {
        this.setTitle("Kata 7");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.add(new JLabel("Welcome to Kata7 application!"));
    }
}
