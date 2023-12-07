package Modelo.Global.ObjetosPrincipales.Universales;

import Modelo.Global.Interfaces.Solitario;

import java.io.*;

public class Persistencia implements Serializable{

    public static void guardarPartida(OutputStream os, Solitario objetoAGuardar) {
        try {
            var bos = new ObjectOutputStream(os);
            bos.writeObject(objetoAGuardar);
        } catch (IOException e) {
            System.err.println("Error al guardar la partida");
        }

    }

    public static Object cargarPartida(InputStream is) {
        try {
            var bis = new ObjectInputStream(is);
            return bis.readObject();
        }catch (IOException e) {
            System.err.println("Error al cargar la partida en lectura de archivos");
        }catch (ClassNotFoundException e) {
            System.err.println("Error, clase rara");
        }
        return null;
    }
}

