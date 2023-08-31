/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.lab2.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsumerPeticiones<T> implements Runnable {

    private final Socket clientSocket;

    public ConsumerPeticiones(Socket s) {

        clientSocket = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            String recurso;
            while ((inputLine = in.readLine()) != null) {

                if (inputLine.contains("GET")) {
                    recurso = inputLine.split(" ")[1];
                    recurso = recurso.substring(1);
                    System.out.println(recurso);
                    Interprete.getInstance().recursoToString(recurso, clientSocket);
                }
                if (!in.ready()) {
                    break;
                }
                if (inputLine.contains("bye")) {
                    System.exit(1);
                }
            }
            in.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConsumerPeticiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
