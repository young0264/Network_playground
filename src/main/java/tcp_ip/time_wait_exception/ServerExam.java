package tcp_ip.time_wait_exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExam {

    public static void main(String[] args) {
        int port = 8888;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("서버측 소켓을 생성하였습니다 port : " + port);

            Socket clientSocket = serverSocket.accept();
            System.out.println("클라이언트와 연결되었습니다.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String receivedMessage = in.readLine();
            System.out.println("클라이언트로부터 받은 메시지 : " + receivedMessage);

            out.println("서버에서 클라이언트로 보내는 메시지입니다! 우히히");
            System.out.println("클라이언트 socket close 5초전");
            Thread.sleep(5000); /** 5초간 대기 */

            clientSocket.close(); /** 서버 -> 클라 : FIN packet 보냄 */
            System.out.println("클라이언트 socket close");

            Thread.sleep(10000); //10초간 대기 _ time-wait
            serverSocket.close();
            System.out.println("서버 측 종료 완료");

        } catch (IOException | InterruptedException e) {

            System.out.println("server에서 에러가 발생하였습니다");
            e.printStackTrace();
        }
    }
}
