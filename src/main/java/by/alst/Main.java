package by.alst;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Main {

    private static MyJakarta myJakarta;
    private static String path;

    static {
        try {
            Properties properties = new Properties();
            InputStream is = Main.class.getClassLoader().getResourceAsStream("conf.properties");
            properties.load(is);
            path = properties.getProperty("path");
            System.out.println(path);

            myJakarta = MyJakartaManager.readFromJson(path) != null
                    ? MyJakartaManager.readFromJson(path) : new MyJakarta();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println(myJakarta);

        Technology activation = new Technology();
        activation.setTechnologyName("jakarta.activation-api");
        activation.setTechnologyDescription("Jakarta Activation API 2.1 Specification");

        Technology mail = new Technology();
        mail.setTechnologyName("jakarta.mail-api");
        mail.setTechnologyDescription("Jakarta Mail API 2.1 Specification API");

        List<Technology> technologyList = new ArrayList<>();
        technologyList.add(activation);
        technologyList.add(mail);

        myJakarta.setVersion("10");
        myJakarta.setDescription("Eclipse Enterprise for Java (EE4J) is an open source initiative to create " +
                "standard APIs, implementations of those APIs, and technology compatibility kits for Java " +
                "runtimes that enable development, deployment, and management of server-side and cloud-native " +
                "applications.");
        myJakarta.setTechnologyList(technologyList);

        MyJakartaManager.setMyJakarta(myJakarta);
        MyJakartaManager.writeToJson(path);
        System.out.println("Записано в файл: " + path);

        myJakarta = MyJakartaManager.readFromJson(path);
        System.out.println(MyJakartaManager.getMyJakarta());

        Technology updateTechnology = myJakarta.getTechnologyList()
                .get(new Random().nextInt(0, myJakarta.getTechnologyList().size()));
        updateTechnology.setTechnologyDescription(updateTechnology.getTechnologyDescription() + " 2024");
        MyJakartaManager.updateTechnology(updateTechnology, path);
        System.out.println("Обновленная технология записана в файл: " + path);
    }
}
