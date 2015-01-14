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
public class MainBuluchevPI {

    public static final int PORT_WORK = 8071;
    public static final int PORT_STOP = 9001;

    public static void main(String[] args) {
        MultiThreadedServerBulychevPI server = new MultiThreadedServerBulychevPI(PORT_WORK);
        new Thread(server).start();
        try {
            Thread monitor = new StopMonitorBulychevPI(PORT_STOP);
            monitor.start();
            monitor.join();
            System.out.println("Right after join.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
    }
}
