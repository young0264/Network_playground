package tcp_ip.time_wait_exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientExam {

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 8888;

        try {
            /** 클라이언트측 소켓 생성*/
            Socket clientSocket = new Socket(serverAddress, serverPort);
            System.out.println("클라이언트측 소켓을 생성하였습니다 : " + serverPort);

            /** 서버와 클라이언트 연결 */
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String messageToSend = scanner.nextLine();

            out.println("(클라이언트 to 서버)" + messageToSend); /** 사용자가 입력한 데이터 */

            /** 첫번째 FIN 패킷이 넘어오면서 ACK패킷을 생성해서 보냄 */
            String receivedMessage = in.readLine(); /** 서버로부터 읽어온 데이터 출력*/
            System.out.println("서버로부터 받은 메시지 : " + receivedMessage);


            System.out.println("클라이언트 time sleep 전");
            Thread.sleep(10000); /** 10초간 대기. close-wait 시작 */
            System.out.println("클라이언트 time sleep 10초 후");

            System.out.println("클라에서 서버로 FIN 패킷 전송");
            clientSocket.close(); /** 양방향 종료 _ FIN 패킷 보냄. close-wait 종료 */
            System.out.println("클라이언트 측 종료 완료");

        } catch (IOException | InterruptedException e) {
            System.out.println("client에서 에러가 발생하였습니다");
            e.printStackTrace();
        }
    }
}
