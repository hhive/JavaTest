package test.FileChoose;

import java.awt.FileDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2019/11/7 16:31
 */
public class ChooseFile extends MouseAdapter {
    private JTextField filePathFild;
    private JFrame frame;
    private FileDialog fileDialog;
    private String filePath;
    private String fileName;

    public ChooseFile(JTextField filePathFild, JFrame frame) {
        this.filePathFild = filePathFild;
        this.frame = frame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        fileDialog = new FileDialog(frame);
        fileDialog.show();
        filePath = fileDialog.getDirectory();
        fileName = fileDialog.getFile();
        if(filePath == null  || fileName == null){
        }else{
            filePathFild.setText(filePath + fileName);
        }
    }
}