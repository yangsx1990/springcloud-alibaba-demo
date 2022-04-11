package com.consumer.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-04-06
 */
public class Receive {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket=new DatagramSocket(6000);
        byte[] data=new byte[1024];
        DatagramPacket datagramPacket=new DatagramPacket(data,data.length);
        socket.receive(datagramPacket);
        System.out.println(new String(data,0,datagramPacket.getLength())+" ip:"
                +datagramPacket.getAddress().getHostAddress()+" port:"+datagramPacket.getPort());
        socket.close();

    }
}
