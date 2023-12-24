package Model.Global.MainObjects.Universal;

import Model.Global.Interfaces.Solitaire;

import java.io.*;

public class Persistence implements Serializable {

    public static void saveGame(OutputStream os, Solitaire game) {
        try {
            var bos = new ObjectOutputStream(os);
            bos.writeObject(game);
        } catch (IOException e) {
            System.err.println("Error saving game");
        }

    }

    public static Object loadGame(InputStream is) {
        try {
            var bis = new ObjectInputStream(is);
            return bis.readObject();
        } catch (IOException e) {
            System.err.println("Error loading game");
        } catch (ClassNotFoundException e) {
            System.err.println("Error, strange class");
        }
        return null;
    }

    public static OutputStream getOutputStream(String path) {
        var file = new File(path);
        OutputStream os;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return null;
        }
        return os;
    }

    public static InputStream getInputStream(String path) {
        var file = new File(path);
        InputStream os;
        try {
            os = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return null;
        }
        return os;
    }

    public static void deleteGame(String path) {
        var file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }


}

