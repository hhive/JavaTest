package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IO {
    public static void main(String[] args) {
        String file1Path = "C:\\Users\\jax\\Desktop\\c.csv";
        String file2Path = "C:\\Users\\jax\\Desktop\\a.csv";//我的
        new OperateCSV().compareScoreTable(file1Path, file2Path);
//        String historyPath = "C:\\Users\\jax\\Desktop\\CSVtest\\history.csv";
//       // new operateCSV().writeConfig(configPath, "ddd", new operateCSV().getPath(), 30);
//        OperateCSV operateCSV = new OperateCSV();
//        operateCSV.readConfig(configPath, historyPath);
//        operateCSV.deleteAllNullRecord(configPath);
    }
}

class OperateCSV {


    public Path getPath() {
        //以当前路径来创建path对象
        Path getPathForNow = Paths.get(".");
        //获取绝对路径
        System.out.println(getPathForNow.toAbsolutePath());
        return getPathForNow.toAbsolutePath();
    }

    public void compareScoreTable(String file1Path, String file2Path) {
        int i = 0;
        File csv1 = new File(file1Path);  // CSV文件路径
        File csv2 = new File(file2Path);  // CSV文件路径
        if (csv1 == null) {
            System.out.println("文件不存在！");
        }
        String everyLine = null;
        String line1 = null;
        String line2 = null;
        try
        {
            long lastPoint = 0;
            long point = 0;
            //br = new  RandomAccessFile(configPath, "r");

            BufferedReader br1=new BufferedReader(new InputStreamReader(new FileInputStream(csv1),StandardCharsets.UTF_8));
            while ((line1 = br1.readLine()) != null)  //读取到的内容给line变量
            {
               // System.out.println("课程：" + line1);
                everyLine = line1;
                String[] temp = everyLine.split(",");
                String course = temp[3];
               // System.out.println("课程名：" + course);
                BufferedReader br2=new BufferedReader(new InputStreamReader(new FileInputStream(csv2),
                    StandardCharsets.UTF_8));
                int count = 0;
                while ((line2 = br2.readLine()) != null)  //读取到的内容给line变量
                {
                    everyLine = line2;
                    String[] temp2 = everyLine.split(",");
                    String course2 = temp2[3];
                    if (course.equals(course2)) {
                //        System.out.println("课程名2：" + course);
                        count = 1;
                        br2.close();
                        break;
                    }
                }
                if (count == 0) {
                    i++;
                    System.out.println("缺少：" + i + course);
                    br2.close();
                }
            }
            br1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void operateFile() {
        //move file
//        public static Path move(Path source, Path target, CopyOption... options)
//        throws IOException
//        {
//            FileSystemProvider provider = provider(source);
//            if (provider(target) == provider) {
//                // same provider
//                provider.move(source, target, options);
//            } else {
//                // different providers
//                CopyMoveHelper.moveToForeignTarget(source, target, options);
//            }
//            return target;
//        }

    }

    //读取配置文件并删除过期的记录
    public void readConfig(String configPath, String historyPath) {
        File csv = new File(configPath);  // CSV文件路径
        if (csv == null) {
            System.out.println("文件不存在！");
        }
        RandomAccessFile br = null;
        String everyLine = null;
        String line = null;
        try
        {
            long lastPoint = 0;
            long point = 0;
            br = new  RandomAccessFile(configPath, "r");
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                point = br.getFilePointer();
                if (line.charAt(0) == '#') {
                    continue;
                }
                everyLine = line;
                String[] temp = everyLine.split(",");
                //将读出的有效期起始时间转换成日期格式
                //System.out.println(temp[2]);
                String strDate = temp[2];
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date date=simpleDateFormat.parse(strDate);
                    Date today = new Date();
                    System.out.println(date);
                    //比较相差的天数并将毫秒转成天数
                    long compareToToday = today.getTime() - date.getTime();
                    compareToToday = compareToToday / 86400000;
                    System.out.println(compareToToday);
                    int expiryDate = Integer.parseInt(temp[3]);
                    //删除过期的文件记录加入历史记录并移动指定的文件
                    if (compareToToday >= expiryDate) {
                        System.out.println("line:" + everyLine);
                        writeHistory(historyPath, everyLine);
                        deleteOneRecord(configPath, everyLine, lastPoint);
                    }
                } catch(ParseException px) {
                    px.printStackTrace();
                }
                lastPoint = point;
                System.out.println();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除过期的一行记录
    private boolean deleteOneRecord(String path, String oldstr, long point) {
        System.out.println("modifyFileContent");
        try {
            RandomAccessFile raf = new RandomAccessFile(path, "rw");
            if (raf == null) {
                System.out.println("文件不存在！");
                return false;
            }
            int length = oldstr.length();
            while (length > 0) {
                raf.seek(point);
                raf.writeBytes("#");//将过期的那行记录用#代替
                point++;
                length--;
            }
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    //删除所有的空记录
    public void deleteAllNullRecord(String configPath) {
        try {
            File file = new File(configPath);
            if (file == null) {
                System.out.println("文件不存在！");
            }
            //读取config的全部内容
            BufferedReader configReader = new BufferedReader(new FileReader(file));
            List contentList = new ArrayList();
            String line = null;
            while ((line = configReader.readLine()) != null) {
                contentList.add(line);
            }
            //删除空记录后重新写入
            BufferedWriter configWriter = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < contentList.size(); i++) {
                if ((line = contentList.get(i).toString()).charAt(0) != '#') {
                    configWriter.write(line);
                    configWriter.newLine();
                }
            }
            configWriter.flush();
            configWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改文件的有效期
    public void modifyExpiryDate(String configPath, String filePath, String fileName, long newExpiryDate) {
        try {
            RandomAccessFile raf = new RandomAccessFile(configPath, "rw");
            if (raf == null) {
                System.out.println("文件不存在！");
            }
            String line = null;
            while ((line = raf.readLine()) != null) {
                if (line.contains(fileName) && line.contains(filePath)) {
                    long point = raf.getFilePointer();
                    while (raf.read() != ',') {
                        point = point - 2;
                        raf.seek(point);
                    }
                    //System.out.println("modifyExpiryDate:" + (raf.read() - 48));
                    //System.out.println("modifyExpiryDate:" + (raf.read() - 48));
                    String ExpiryDate = String.valueOf(newExpiryDate);
                    raf.writeBytes(ExpiryDate);
                }
            }
            raf.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    //写入新的一行
    public void writeConfig(String configPath, String fileName, Path filePath, int expiryDate) {
        File config = new File(configPath);
        try {
            RandomAccessFile raf=new RandomAccessFile(configPath,"rw");
            if (raf == null) {
                System.out.println("文件不存在！");
            }
            raf.seek(raf.length());  //将指针移动到文件末尾
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = simpleDateFormat.format(date);
            String content = fileName + "," + filePath + "," + str + "," +expiryDate + "\n";//写入的内容
            raf.writeBytes(content); //字符串末尾需要换行符
            raf.close();//关闭文件流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //将删除的记录加入历史记录
    public void writeHistory(String historyPath, String line) {
        File config = new File(historyPath);
        try {
            RandomAccessFile raf=new RandomAccessFile(historyPath,"rw");
            if (raf == null) {
                System.out.println("文件不存在！");
            }
            raf.seek(raf.length());  //将指针移动到文件末尾
            //将操作时间加入到记录中
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = simpleDateFormat.format(date);
            line = line + "," + str + "\n";
            raf.writeBytes(line); //字符串末尾需要换行符
            raf.close();//关闭文件流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //通过替换#的方式写入新的一行
}


//通过path来获取文件路径
