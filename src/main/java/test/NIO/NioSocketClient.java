package test.NIO;

//客户端client类

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * description:
 *
 * @author wkGui
 */
public class NioSocketClient {
    public void start() {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            //连接服务端socket
            SocketAddress socketAddress = new InetSocketAddress("localhost", 8888);
            socketChannel.connect(socketAddress);

            int sendCount = 0;

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //这里最好使用selector处理   这里只是为了写的简单
            while (sendCount < 10) {
                buffer.clear();
                //向服务端发送消息
                buffer.put(("current time : " + System.currentTimeMillis()).getBytes());
                //读取模式
                buffer.flip();
                while (buffer.hasRemaining()) {
                    socketChannel.write(buffer);
                }
                buffer.clear();

                //从服务端读取消息
                int readLenth = socketChannel.read(buffer);
                //读取模式
                buffer.flip();
                byte[] bytes = new byte[readLenth];
                buffer.get(bytes);
                System.out.println(new String(bytes, StandardCharsets.UTF_8));
                buffer.clear();

                sendCount++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}