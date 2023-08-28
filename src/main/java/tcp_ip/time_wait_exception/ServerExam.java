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
            /** 서버측 소켓 생성*/
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("서버측 소켓을 생성하였습니다 port : " + port);

            /** 서버와 클라이언트 연결 */
            Socket clientSocket = serverSocket.accept();
            System.out.println("클라이언트와 연결되었습니다.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String receivedMessage = in.readLine();
            System.out.println("클라이언트로부터 받은 메시지 : " + receivedMessage);

            Thread.sleep(4000); /** 4초간 대기 */

            /** 서버에서 클라이언트로 보내는 데이터 메시지 */
            out.println("서버에서 클라이언트로 보내는 메시지입니다! 우히히");

            System.out.println("클라이언트 socket close 5초전");
            clientSocket.close(); /** 서버 -> 클라 : FIN packet 보냄 */
            System.out.println("클라이언트 socket close 시작 ");
            System.out.println("클라이언트 socket close 끝 ");

            /** client로부터 FIN패킷을 받고나서 time-wait 시작 */
            Thread.sleep(11000); //11초간 대기 _ time-wait상태는 일반적으로 1-2분정도 유지
            serverSocket.close();
            System.out.println("서버 측 종료 완료");

        } catch (IOException | InterruptedException e) {

            System.out.println("server에서 에러가 발생하였습니다");
            e.printStackTrace();
        }
    }
}
