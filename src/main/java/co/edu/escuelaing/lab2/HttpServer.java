package co.edu.escuelaing.lab2;

import co.edu.escuelaing.lab2.model.ConsumerPeticiones;
import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        ExecutorService pool = null;
        boolean running = true;
        int port = getPort();
        try {
            serverSocket = new ServerSocket(port);
            pool = Executors.newCachedThreadPool();
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        System.out.println("Listo para recibir ...");
        while (running) {
            Socket clientSocket;
            clientSocket = serverSocket.accept();
            ConsumerPeticiones<?> consumer = new ConsumerPeticiones<>(clientSocket);
            pool.submit(consumer);
        }

        serverSocket.close();
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }

        return 36000;
    }
}
