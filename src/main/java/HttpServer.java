import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.logging.Logger;

import static java.lang.String.*;

/**
 * JavaによるシンプルなWebサーバー
 *
 * @author ryohei.yamamoto
 */
public class HttpServer {

    private static final Logger logger = Logger.getLogger(HttpServer.class.getName());
    private static final int PORT = 8931;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        try {
            // ソケットの作成
            final InetSocketAddress address = new InetSocketAddress(HOST, PORT);
            final ServerSocket socket = new ServerSocket();
            socket.bind(address);

            // リクエストの受付開始
            final String hostName = socket.getInetAddress().getHostName();
            final int port = socket.getLocalPort();
            logger.info(format("serving HTTP on %s port %s", hostName, port));
            socket.accept();

            logger.info("Hello Request!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
