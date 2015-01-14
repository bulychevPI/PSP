/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverBulychevPI;

/**
 *
 * @author HP
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class MultiThreadedServerBulychevPI implements Runnable{
 
    protected int          serverPort   = 8071;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
 
    public MultiThreadedServerBulychevPI(int port){
        this.serverPort = port;
    }
 
    @Override
    public void run(){
        try {
            openServerSocket();
            System.out.println("InetAddress: "+InetAddress.getLocalHost().getHostAddress());
            while(! isStopped()){
                Socket clientSocket = null;
                try {
                    clientSocket = this.serverSocket.accept();
                    
                } catch (IOException e) {
                    if(isStopped()) {
                        System.out.println("Server Stopped.") ;
                        return;
                    }
                    throw new RuntimeException("Error accepting client connection", e);
                }
                new Thread(
                        new WorkerBulychevPI(clientSocket)
                ).start();
            }
            System.out.println("Server Stopped.") ;
        } catch (UnknownHostException ex) {
            Logger.getLogger(MultiThreadedServerBulychevPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
 
    private synchronized boolean isStopped() {
        return this.isStopped;
    }
 
    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }
 
    private void openServerSocket() {
        System.out.println("Opening server socket...");
     try {
            this.serverSocket = new ServerSocket(this.serverPort);
            this.serverSocket.setReceiveBufferSize(32768);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port"  + this.serverPort, e);
        }
    }
 
}