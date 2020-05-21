package test.FileChoose;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2019/11/7 16:29
 */
public class DialogTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button = new JButton("button");

        button.addMouseListener(new ShowDialogLintener(frame));
        frame.add(button, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
}
class ShowDialogLintener extends MouseAdapter {
    JFrame frame;
    public ShowDialogLintener(JFrame frame) {
        this.frame = frame;
    }
    @Override
    public void mouseClicked(MouseEvent arg0) {
        super.mouseClicked(arg0);
        JFileChooser chooser = new JFileChooser(".");
        chooser.showOpenDialog(frame);
        String filePath = chooser.getSelectedFile().getAbsolutePath();
        System.out.println(filePath);
    }
}