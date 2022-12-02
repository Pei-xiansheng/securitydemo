package com.zap.service.socket.udp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName UdpSocketServer
 * @Author Evan
 * @Descrption
 * @create 2022/9/13 9:48
 */
@Slf4j
public class UdpSocketServer {

    private DatagramSocket socket = null;

    public void testServer(int port){
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
            log.info("端口号------>{}无法使用");
        }
    }

    public void startServer(){

        log.info("开启服务器,监听端口号为{}",socket.getPort());

        while (true){

            DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);

            try {

                socket.receive(packet);

                String str = new String(packet.getData(), 0, packet.getLength(),StandardCharsets.UTF_8).trim();

                log.info("接收到客户端消息---{}",str);

                DatagramPacket datagramPacket = new DatagramPacket(str.getBytes(StandardCharsets.UTF_8), 0, str.length(), packet.getSocketAddress());

                socket.send(datagramPacket);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        UdpSocketServer udpSocketServer = new UdpSocketServer();
        udpSocketServer.testServer(9090);
        udpSocketServer.startServer();
    }
}
