package test.NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2019/8/28 14:28
 */
public class FileChannelTest {
    public static void method1(){
        RandomAccessFile aFile = null;
        try{
            Path path = Paths.get("C:\\study\\Java\\JavaTest\\src\\main\\java\\NIO\\src\\nio.txt");
            aFile = new RandomAccessFile(path.toFile(),"rw");
            System.out.println("aFile.length()--" + aFile.length());
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //从Channel中读取到buffer
            int bytesRead = fileChannel.read(buf);
            int count = 0;
            while(bytesRead != -1)
            {
                System.out.println("bytesRead--" + bytesRead);
                buf.flip();
                while(buf.hasRemaining())
                {
                   String receivedStr = StandardCharsets.UTF_8.decode(buf).toString();
                    //System.out.println(receivedStr);
                    System.out.println("count--" + count++);
                }
                buf.clear();
                bytesRead = fileChannel.read(buf);
            }
            buf.clear();
            //从buffer中写入到Channel
            String newData = new Date().toString();
            System.out.println("newData--" + newData);
            while (buf.hasRemaining()) {
                buf.put(newData.getBytes());
                System.out.println(StandardCharsets.UTF_8.decode(buf).toString());
                int bytesWriter = fileChannel.write(buf);
                System.out.println("bytesWriter--" + bytesWriter);
            }
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        method1();
    }
}
