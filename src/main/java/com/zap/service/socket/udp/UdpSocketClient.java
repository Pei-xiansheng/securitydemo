package com.zap.service.socket.udp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @ClassName UdpSocketClient
 * @Author Evan
 * @Descrption
 * @create 2022/9/13 9:48
 */
@Slf4j
public class UdpSocketClient {

    private DatagramSocket socket;

    private String serverIp;

    private int serverPort;

    public UdpSocketClient(String serverIp,int serverPort) throws SocketException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.socket = new DatagramSocket();
    }

    public void testClient() throws IOException {

        log.info("开启客户端服务,监听端口号为{}",socket.getPort());

        Scanner scanner = new Scanner(System.in);
        while (true){

            System.out.println("请输入内容:");

            String nextLine = scanner.nextLine();

            log.info("是不是换行符---{}",nextLine);

            if ("exit".equals(nextLine)){
                    break;
            }

            //构造去请求发送至服务器
            DatagramPacket datagramPacket = new DatagramPacket(nextLine.getBytes(StandardCharsets.UTF_8), nextLine.length(), InetAddress.getByName(serverIp), serverPort);

            log.info("服务器地址{}",InetAddress.getByName(serverIp));

            try {

                socket.send(datagramPacket);

            } catch (IOException e) {

                e.printStackTrace();

                log.info("消息发送失败!");

            }

            //接收服务端消息
            DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);

            socket.receive(packet);

            String msg = new String(packet.getData(), 0, packet.getLength(),StandardCharsets.UTF_8);

            log.info("接收到服务端消息{}",msg);

        }
    }

    public static void main(String[] args) throws IOException {
        UdpSocketClient udpSocketClient = new UdpSocketClient("127.0.0.1", 9090);
        udpSocketClient.testClient();
    }
}
