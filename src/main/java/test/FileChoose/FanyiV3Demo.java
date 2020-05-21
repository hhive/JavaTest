package test.FileChoose;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import util.GsonUtil;

/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2019/10/30 12:05
 */
public class FanyiV3Demo implements ActionListener {

    private static Logger log = Logger.getLogger(FanyiV3Demo.class);

    private static final String YOUDAO_URL = "https://openapi.youdao.com/api";

    private static final String APP_KEY = "77f6c4051ae088c3";

    private static final String APP_SECRET = "hxfjp9expBcaML8QNj65bLmgMjFd70O1";

    private static Long pointer = 0L;

    private BufferedReader bufferedReader = null;

    public static void main(String[] args) throws IOException {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!MainForm.sourcefile.getText().isEmpty()) {
            String fileName = MainForm.sourcefile.getText();
            log.info("fileName is "+ fileName);
            File txt = pdfTest(fileName);
        }
        if (!MainForm.targetfile.getText().isEmpty()) {
            String fileName = MainForm.targetfile.getText();
            log.info("fileName is "+ fileName);
            execute(fileName);
        }
    }

    /**
     * 执行
     */
    public void execute(String fileName) {
        Map<String,String> params = new HashMap<String,String>();
       // String fileName = this.getClass().getClassLoader().getResource("\\pdf\\finish\\369.pdf.txt").getPath();
       //  Path path = new WindowsPath();
        //String fileUtl = this.getClass().getResource("\\pdf\\finish\\369.pdf.txt").getFile();
        File txt = new File(fileName);
        fileName = txt.getName();
        while (pointer > -1) {
            String q1 = parseTxt(txt);
            String q = q1.replace("-\n", "").replace("\n", " ");
            String salt = String.valueOf(System.currentTimeMillis());
            /** 组装参数 */
            params.put("from", "en");
            params.put("to", "zh-CHS");
            params.put("signType", "v3");
            String curtime = String.valueOf(System.currentTimeMillis() / 1000);
            params.put("curtime", curtime);
            String signStr = APP_KEY + truncate(q) + salt + curtime + APP_SECRET;
            String sign = getDigest(signStr);
            params.put("appKey", APP_KEY);
            params.put("q", q);
            params.put("salt", salt);
            params.put("sign", sign);
            /** 处理结果 */
            String resultFile = txt.getPath().replace(txt.getName(), "") + "result_" + txt.getName();
            String compareFile = txt.getPath().replace(txt.getName(), "") + "compare_" + txt.getName();
            try {
                requestForHttp(resultFile, compareFile, YOUDAO_URL, params);
            } catch (IOException e) {
                log.error("翻译失败," + e);
            }
        }
        log.info("文件翻译完毕");

    }

    /** 将pdf文献解析成txt */
    public File pdfTest(String fileName) {
        try {
            // 是否排序
            boolean sort = false;
            // 开始提取页数
            int startPage = 1;
            // 结束提取页数
            int endPage = Integer.MAX_VALUE;
            String content = null;
            PrintWriter writer = null;
            //输出txt文本路径
            File pdf = new File(fileName);
            if (!pdf.isFile()) {
                log.error("路径%s文件为空！" + fileName);
                return null;
            }

           //File pdf = Objects.requireNonNull(file.listFiles())[0];
            String target= pdf.getPath() + ".txt";
            PDDocument document = PDDocument.load(pdf);
            PDFTextStripper pts = new PDFTextStripper();
            endPage = document.getNumberOfPages();
            log.info("Total Page: " +  endPage);
            pts.setStartPage(startPage);
            pts.setEndPage(endPage);
            try {
                //content就是从pdf中解析出来的文本
                content = pts.getText(document);
                pts.getResources();
                writer = new PrintWriter(new FileOutputStream(target));
                writer.write(content);// 写入文件内容
                writer.flush();
                writer.close();
            } catch (Exception e) {
                throw e;
            }finally {
                document.close();
            }
            log.info("Get PDF Content ...");
            return new File(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 读取解析好的txt */
    private String parseTxt(File file) {
        if (!file.isFile()) {
            log.error("文件%s为空！" + file);
            return null;
        }
        // log.info("开始解析txt");
        StringBuilder content = new StringBuilder();
        String line = null;
        try {
            boolean flag = false;
            if (bufferedReader == null) {
                 bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),StandardCharsets.UTF_8));
            }
            // RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            while ((line = bufferedReader.readLine()) != null) {

                if (line.trim().endsWith("-")) {
                    line = line.replace("-", "");
                    content.append(" ")
                        .append(line);
                    flag = true;
                } else {
                    if (flag) {
                        content.append(line);
                        flag = false;
                    } else {
                        content.append(" ")
                            .append(line);
                    }
                }
                if (line.isEmpty() || line.trim().endsWith(".")){
                    break;
                }
            }
            if (line == null) {
                pointer = -1L;
            }
        } catch (IOException e) {
            log.error("开始解析txt失败" + e);
        }

        return content.toString();
    }


    static class ShowDialogLintener extends MouseAdapter {
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

    public void requestForHttp(String result, String compare, String url,Map<String,String> params) throws IOException {

        /** 创建HttpClient */
        CloseableHttpClient httpClient = HttpClients.createDefault();

        /** httpPost */
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String,String>> it = params.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            paramsList.add(new BasicNameValuePair(key,value));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList,"UTF-8"));
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try{
            Header[] contentType = httpResponse.getHeaders("Content-Type");
            log.info("Content-Type:" + contentType[0].getValue());
            if("audio/mp3".equals(contentType[0].getValue())){
                //如果响应是wav
                HttpEntity httpEntity = httpResponse.getEntity();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(baos);
                byte[] resultByte = baos.toByteArray();
                EntityUtils.consume(httpEntity);
                if(resultByte != null){//合成成功
                    String file = "合成的音频存储路径"+System.currentTimeMillis() + ".mp3";
                    byte2File(resultByte, file);
                }
            }else{
                /** 响应不是音频流，直接显示结果 */
                HttpEntity httpEntity = httpResponse.getEntity();
                String json = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
                EntityUtils.consume(httpEntity);
                HashMap hashMap = GsonUtil.json2Obj(json, HashMap.class);
                //log.info("hashMap is " + hashMap);
                String source = "原文：" + hashMap.get("query");
                log.info(source);
                String content = hashMap.get("translation")
                    .toString()
                    .replace("[", "")
                    .replace("]", "");
                log.info(content);
                BufferedWriter out = null;
                BufferedWriter out1 = null;
                try {
                    out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(new File(result), true), StandardCharsets.UTF_8));
                    out.write("\n");
                    out.write("    ");
                    out.write(content);

                    out1 = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(new File(compare), true), StandardCharsets.UTF_8));
                    out1.write("\n");
                    out1.write("    ");
                    out1.write(source);
                    out1.write("\n");
                    out1.write("    " + "翻译：");
                    out1.write(content);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.close();
                        out1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }finally {
            try{
                if(httpResponse!=null){
                    httpResponse.close();
                }
            }catch(IOException e){
                log.error("## release resouce error ##" + e);
            }
        }
    }

    /**
     * 生成解析字段
     */
    public static String getDigest(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     *
     * @param result 音频字节流
     * @param file 存储路径
     */
    private static void byte2File(byte[] result, String file) {
        File audioFile = new File(file);
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(audioFile);
            fos.write(result);

        }catch (Exception e){
            log.info(e.toString());
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        String result;
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }
}
