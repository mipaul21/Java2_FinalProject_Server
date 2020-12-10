/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paul;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mitchell Paul
 */
public class RequestHandler implements Runnable {

    private Socket socket;

    public RequestHandler(Socket socket) {
        if (null == socket) {
            throw new IllegalArgumentException("Socket cannot be null.");
        }
        this.socket = socket;
    }

    @Override
    public void run() {
        try(
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        ) {
            String animalName = null;
            InetAddress inetAddress = socket.getInetAddress();
            String clientAddress = inetAddress.getHostAddress();
            System.out.println("Connection from " + clientAddress);
            BufferedReader d = new BufferedReader(new InputStreamReader(in));
            outputStream.writeUTF("1"); //id
            outputStream.writeUTF("Lucky"); // name
            outputStream.writeInt(2); // age
            outputStream.writeUTF("dog"); // species
            outputStream.writeUTF("are"); // fixed
            outputStream.writeDouble(26.25); // weight
            outputStream.writeInt(4);
            outputStream.writeUTF("10-01-20");
           
            outputStream.flush();
            ;
            
        } catch (SocketTimeoutException ste) {
            System.out.println("\tSocket connection timed out: "
                    + ste.getMessage());
        } catch (IOException ioe) {
            System.out.println("\tIO Error: " + ioe.getMessage());
        } catch (Exception ex) {
            System.out.println("\tERROR: " + ex.getMessage());
        }
    }

}