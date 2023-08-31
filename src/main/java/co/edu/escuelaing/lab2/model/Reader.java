package co.edu.escuelaing.lab2.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader {
    
    /**
     * Lee el archivo y retorna el BufferedReader correspondiente
     * @param url dirección del archivo
     * @return  BufferedReader del archivo
     */
    public BufferedReader read(String url){
        BufferedReader br;
        try {
            File file = new File (url);
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            
        } catch (FileNotFoundException ex) {
            br=null;
        }
        return br;
    }
    /**
     * Lee un BufferedReader y lo convierte en una cadena de texto
     * @param br BufferedReader correspondiente al archivo
     * @return String con el contenido del archivo
     */
    public String toHtml(BufferedReader br){
        String res="";
        try {
            String line;
            
            while((line=br.readLine())!=null){
                res=res.concat(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     * Convierte una imagen a un arreglo de bytes
     * @param url ruta de la imagen
     * @return arreglo del bytes con la imagen
     */
    public byte[] readImagen(String url){
        try {
            File graphicResource= new File(url);
            FileInputStream inputImage = new FileInputStream(graphicResource);
            byte[] bytes = new byte[(int) graphicResource.length()];
            inputImage.read(bytes);
            return bytes;
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}