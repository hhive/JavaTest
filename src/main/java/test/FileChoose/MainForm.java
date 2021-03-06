package test.FileChoose;

/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2019/11/7 16:54
 */
import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    /**
     * 构造界面
     *
     * @author 1109030125
     */
    private static final long serialVersionUID = 1L;
    /* 主窗体里面的若干元素 */
    private JFrame mainForm = new JFrame("翻译文档"); // 主窗体，标题为“TXT文件解析”
    private JLabel label1 = new JLabel("请选择待翻译的pdf文件：");
    private JLabel label2 = new JLabel("请选择待翻译的txt文件：");
    public static JTextField sourcefile = new JTextField(); // 选择待解析或翻译文件路径的文本域
    public static JTextField targetfile = new JTextField(); // 选择解析或翻译后文件路径的文本域
    public static JButton buttonBrowseSource = new JButton("浏览"); // 浏览按钮
    public static JButton buttonBrowseTarget = new JButton("浏览"); // 浏览按钮
    public static JButton buttonEncrypt = new JButton("生成"); // 解析按钮
    public static JButton buttonDecrypt = new JButton("翻译"); // 翻译按钮

    public MainForm() {
        Container container = mainForm.getContentPane();

        /* 设置主窗体属性 */
        mainForm.setSize(400, 270);// 设置主窗体大小
        mainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 设置主窗体关闭按钮样式
        mainForm.setLocationRelativeTo(null);// 设置居于屏幕中央
        mainForm.setResizable(false);// 设置窗口不可缩放
        mainForm.setLayout(null);
        mainForm.setVisible(true);// 显示窗口

        /* 设置各元素位置布局 */
        label1.setBounds(30, 10, 300, 30);
        sourcefile.setBounds(50, 50, 200, 30);
        buttonBrowseSource.setBounds(270, 50, 60, 30);
        label2.setBounds(30, 90, 300, 30);
        targetfile.setBounds(50, 130, 200, 30);
        buttonBrowseTarget.setBounds(270, 130, 60, 30);
        buttonEncrypt.setBounds(100, 180, 60, 30);
        buttonDecrypt.setBounds(200, 180, 60, 30);

        /* 为各元素绑定事件监听器 */
        buttonBrowseSource.addActionListener(new BrowseAction()); // 为源文件浏览按钮绑定监听器，点击该按钮调用文件选择窗口
        buttonBrowseTarget.addActionListener(new BrowseAction()); // 为目标位置浏览按钮绑定监听器，点击该按钮调用文件选择窗口
        buttonEncrypt.addActionListener(new FanyiV3Demo()); // 为解析按钮绑定监听器，单击解析按钮会对源文件进行解析并输出到目标位置
        buttonDecrypt.addActionListener(new FanyiV3Demo()); // 为翻译按钮绑定监听器，单击翻译按钮会对源文件进行翻译并输出到目标位置
        sourcefile.getDocument().addDocumentListener(new TextFieldAction());// 为源文件文本域绑定事件，如果文件是.txt类型，则禁用翻译按钮；如果是.kcd文件，则禁用解析按钮。

        sourcefile.setEditable(false);// 设置源文件文本域不可手动修改
        targetfile.setEditable(false);// 设置目标位置文本域不可手动修改

        container.add(label1);
        container.add(label2);
        container.add(sourcefile);
        container.add(targetfile);
        container.add(buttonBrowseSource);
        container.add(buttonBrowseTarget);
        container.add(buttonEncrypt);
        container.add(buttonDecrypt);
    }

    public static void main(String args[]) {
        new MainForm();
    }
}
