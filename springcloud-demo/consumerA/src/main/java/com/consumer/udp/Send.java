package com.consumer.udp;

import java.io.IOException;
import java.net.*;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-04-06
 */
public class Send {

    public static void main(String[] args) {
        byte[] data="hello world111".getBytes();
        try {
            DatagramPacket datagramPacket=new DatagramPacket(data,data.length,
                    InetAddress.getByName("127.0.0.1"),6000);
            DatagramSocket socket=new DatagramSocket();
            socket.send(datagramPacket);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
