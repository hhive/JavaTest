package test.NIO;//server端启动类
/**
 * description:
 *
 * @author wkGui
 */
public class ServerMain {
    public static void main(String[] args) {
        NioSocketServer server = new NioSocketServer();
        new Thread(() -> {
            try {
                Thread.sleep(10*60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                server.setFlag((byte) 0);
            }
        }).start();
        server.start();
    }
}