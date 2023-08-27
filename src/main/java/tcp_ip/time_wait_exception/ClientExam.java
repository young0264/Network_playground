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
            Socket clientSocket = new Socket(serverAddress, serverPort);
            System.out.println("클라이언트측 소켓을 생성하였습니다 : " + serverPort);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String messageToSend = scanner.nextLine();

            out.println("(클라이언트 to 서버)" + messageToSend); /** 사용자가 입력한 데이터 */

            String receivedMessage = in.readLine(); /** 서버로부터 읽어온 데이터 */
            System.out.println("서버로부터 받은 메시지 : " + receivedMessage);

            Thread.sleep(10000); /** 10초간 대기 _ close wait */

            clientSocket.close();
            System.out.println("클라이언트 측 종료 완료");

        } catch (IOException | InterruptedException e) {
            System.out.println("client에서 에러가 발생하였습니다");
            e.printStackTrace();
        }
    }
}
