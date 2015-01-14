/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverBulychevPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class WorkerBulychevPI implements Runnable {

    protected Socket clientSocket = null;

    public WorkerBulychevPI(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        Serializator ser = new Serializator();
        String name = new String();
        String fun = new String();
        BufferedReader br = null;
        try {
            InputStream input = clientSocket.getInputStream();
            clientSocket.setReceiveBufferSize(30000);
            OutputStream output = clientSocket.getOutputStream();
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            long time = System.currentTimeMillis();
            //output.write("jdevnotes multithreaded server runs\n".getBytes());

            fun=br.readLine();
            name=br.readLine();
            if (fun.equalsIgnoreCase("save")) {
                ser.serialization(input, name);
            }
            else if(fun.equalsIgnoreCase("load")){
                ser.deserialization(output, name);
            }
            input.close();
            output.close();
            //System.out.println("Request processed" + time);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WorkerBulychevPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
