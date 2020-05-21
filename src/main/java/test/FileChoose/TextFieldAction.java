package test.FileChoose;

/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2019/11/7 16:56
 */

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldAction implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub

        ButtonAjust();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        ButtonAjust();

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        ButtonAjust();

    }

    public void ButtonAjust() {
        String file = MainForm.sourcefile.getText();
        if (file.endsWith("txt")) {
            MainForm.buttonDecrypt.setEnabled(false);
            MainForm.buttonEncrypt.setEnabled(true);
        }
        if (file.endsWith("kcd")) {
            MainForm.buttonEncrypt.setEnabled(false);
            MainForm.buttonDecrypt.setEnabled(true);
        }
    }

}