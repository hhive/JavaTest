package test.FileChoose;

/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2019/11/7 16:54
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BrowseAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(MainForm.buttonBrowseSource)) {
            JFileChooser fcDlg = new JFileChooser();
            fcDlg.setDialogTitle("请选择待解析的pdf文件...");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "文本文件(*.pdf)", "pdf");
            fcDlg.setFileFilter(filter);
            int returnVal = fcDlg.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filepath = fcDlg.getSelectedFile().getPath();
                MainForm.sourcefile.setText(filepath);
            }
        } else if (e.getSource().equals(MainForm.buttonBrowseTarget)) {
            JFileChooser fcDlg = new JFileChooser();
            fcDlg.setDialogTitle("请选择待翻译的txt文件...");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "文本文件(*.txt)", "txt");
            fcDlg.setFileFilter(filter);
            int returnVal = fcDlg.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filepath = fcDlg.getSelectedFile().getPath();
                MainForm.targetfile.setText(filepath);
            }
        }
    }

}
