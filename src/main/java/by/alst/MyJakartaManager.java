package by.alst;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class MyJakartaManager {

    private static MyJakarta myJakarta;

    public static MyJakarta getMyJakarta() {
        return myJakarta;
    }

    public static void setMyJakarta(MyJakarta myJakarta) {
        MyJakartaManager.myJakarta = myJakarta;
    }

    private static File getFile(String path) throws IOException {
        File dir = new File(path).getParentFile();
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static void writeToJson(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(getFile(path), myJakarta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MyJakarta readFromJson(String path) throws IOException {
        getFile(path);
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(getFile(path)))) {
            byte[] jsonData = inputStream.readAllBytes();
            ObjectMapper objectMapper = new ObjectMapper();
            myJakarta = jsonData.length == 0 ? null : objectMapper.readValue(jsonData, MyJakarta.class);
        }
        return myJakarta;
    }

    public static void updateTechnology(Technology technology, String path) {
        myJakarta.getTechnologyList().remove(technology);
        myJakarta.getTechnologyList().add(technology);
        writeToJson(path);
    }
}
