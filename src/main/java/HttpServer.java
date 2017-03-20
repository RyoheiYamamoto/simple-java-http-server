import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import static java.lang.String.format;

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
            final ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(address);

            // リクエストの受付開始
            final String hostName = serverSocket.getInetAddress().getHostName();
            final int port = serverSocket.getLocalPort();
            logger.info(format("serving HTTP on %s port %s", hostName, port));
            final Socket socket = serverSocket.accept();

            // リクエストの読込
            final InputStream in = socket.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            final StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (!line.isEmpty()) {
                sb.append(line).append("\n");
                line = reader.readLine();
            }
            final String msg = sb.toString();
            logger.info(msg);

            // レスポンスの生成
            final OutputStream out = socket.getOutputStream();
            out.write(msg.getBytes());
            out.close();

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
