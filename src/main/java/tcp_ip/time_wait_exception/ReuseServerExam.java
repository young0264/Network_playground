package tcp_ip.time_wait_exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class ReuseServerExam {
    public static void main(String[] args) {
        int port = 8888;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("No2 서버측 소켓을 생성하였습니다 port : " + port);

            serverSocket.close();
//        } catch (IOException e) {
        } catch (IOException e) {

            System.out.println("server에서 에러가 발생하였습니다");
            e.printStackTrace();
        }
    }
}
