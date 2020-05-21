// package test.FileChoose;
//
// /**
//  * @author lih@yunrong.cn
//  * @version V2.1
//  * @since 2.1.0 2019/11/7 16:55
//  */
//
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.File;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
//
// import javax.swing.JOptionPane;
//
// public class EncryptAction implements ActionListener {
//
//     @Override
//     public void actionPerformed(ActionEvent e) {
//         // TODO Auto-generated method stub
//
//         if (MainForm.sourcefile.getText().isEmpty()) {
//             JOptionPane.showMessageDialog(null, "请选择待翻译文件！");
//         } else {
//             String sourcepath = MainForm.sourcefile.getText();
//             String targetpath = MainForm.targetfile.getText();
//             File file = new File(sourcepath);
//             String filename = file.getName();
//             File dir = new File(targetpath);
//             if (file.exists() && dir.isDirectory()) {
//                 File result = new File(getFinalFile(targetpath, filename));
//                 if (!result.exists()) {
//                     try {
//                         result.createNewFile();
//                     } catch (IOException e1) {
//                         JOptionPane.showMessageDialog(null,
//                             "目标文件创建失败，请检查目录是否为只读！");
//                     }
//                 }
//
//                 try {
//                     FileReader fr = new FileReader(file);
//                     FileWriter fw = new FileWriter(result);
//                     int ch = 0;
//                     while ((ch = fr.read()) != -1) {
//                         // System.out.print(Encrypt(ch));
//                         fw.write(Encrypt(ch));
//                     }
//                     fw.close();
//                     fr.close();
//                     JOptionPane.showMessageDialog(null, "解析成功！");
//
//                 } catch (Exception e1) {
//                     JOptionPane.showMessageDialog(null, "未知错误！");
//                 }
//             }
//
//             else if (!file.exists()) {
//                 JOptionPane.showMessageDialog(null, "待解析文件不存在！");
//             } else {
//                 JOptionPane.showMessageDialog(null, "解析后文件存放目录不存在！");
//             }
//         }
//     }
//
//     public char Encrypt(int ch) {
//         int x = ch + 1;
//         return (char) (x);
//     }
//
//     public String getFinalFile(String targetpath, String filename) {
//         int length = filename.length();
//         String finalFileName = filename.substring(0, length - 4);
//         String finalFile = targetpath + "\\" + finalFileName + ".kcd";
//         return finalFile;
//     }
//
// }
