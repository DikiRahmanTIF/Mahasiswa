package DR.Mahasiswa;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Pendaftaran Data Mahasiswa");
        jFrame.setContentPane(new Mahasiswa().getRootPanel());
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(640,480);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
