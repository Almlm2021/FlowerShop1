package Datenhaltung;

import java.io.*;

public class SerializationUtil {

    public static void saveAppStateToFile(ApplicationState appState, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(appState);
            objectOut.close();
            fileOut.close();
            System.out.println("Application state saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ApplicationState loadAppStateFromFile(String filePath) {
        ApplicationState appState = null;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            appState = (ApplicationState) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return appState;
    }
}

