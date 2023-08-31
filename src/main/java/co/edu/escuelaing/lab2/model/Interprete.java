package co.edu.escuelaing.lab2.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

public class Interprete {

    private Interprete() {
    }

    private static class helper {

        private static final Interprete INSTANCE = new Interprete();
    }

    public static Interprete getInstance() {
        return helper.INSTANCE;
    }
    /**
     * Usando la dirección de un archivo lo interpreta y lo comvierte a String
     * @param url dirección del archivo
     * @param clientSocket Socket del servidor cliente
     * @throws IOException Si hay un error del I/O
     */
    public void recursoToString(String url, Socket clientSocket) throws IOException {
        Reader r = new Reader();
        String type = getType(url);
        
        if ("css".equals(type) || "js".equals(type) || "html".equals(type)) {
            PrintWriter out;
            String pagina = "HTTP/1.1 200 OK\r\n";
            pagina += "Content-Type: text/" + type + "\n";
            pagina += "\r\n";
            BufferedReader br = r.read(url);
            if(br==null){
                pagina = "No encontrado";
            }else{
                pagina += r.toHtml(br);
            }
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            out.println(pagina);
            out.close();

        } else if("jpg".equals(type)||"png".equals(type)||"jpeg".equals(type)) {

            byte[] bytes = r.readImagen(url);
            DataOutputStream binaryOut;
            binaryOut = new DataOutputStream(clientSocket.getOutputStream());
            binaryOut.writeBytes("HTTP/1.1 200 OK \r\n");
            binaryOut.writeBytes("Content-Type: image/" + type + "\r\n");
            binaryOut.writeBytes("Content-Length: " + bytes.length);
            binaryOut.writeBytes("\r\n\r\n");
            binaryOut.write(bytes);
            binaryOut.close();

        }else{
            PrintWriter out;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            String pagina = "No encontrado";
            out.println(pagina);
            out.close();
        }

    }
    /**
     * Obtiene el tipo del archivo dada su url
     * @param url dirección del archivo
     * @return String con el tipo del archivo
     */
    public String getType(String url) {
        String type = null;
        if (url.contains("css")) {
            type = "css";
        } else if (url.contains("js")) {
            type = "js";
        } else if (url.contains("html")) {
            type = "html";
        } else if (url.contains("jpg")) {
            type = "jpg";
        } else if (url.contains("png")) {
            type = "png";
        } else if (url.contains("jpeg")) {
            type = "jpeg";
        }
        return type;
    }

}
