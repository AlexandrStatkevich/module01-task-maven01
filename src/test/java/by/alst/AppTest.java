package by.alst;

import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppTest {

    @Test
    public void test01WriteToJson() {
        Technology activation = new Technology();
        activation.setTechnologyName("jakarta.activation-api");
        activation.setTechnologyDescription("Jakarta Activation API 2.1 Specification");

        Technology mail = new Technology();
        mail.setTechnologyName("jakarta.mail-api");
        mail.setTechnologyDescription("Jakarta Mail API 2.1 Specification API");

        List<Technology> technologyList = new ArrayList<>();
        technologyList.add(activation);
        technologyList.add(mail);

        MyJakarta jakarta10 = new MyJakarta();
        jakarta10.setVersion("10");
        jakarta10.setDescription("Eclipse Enterprise for Java (EE4J) is an open source initiative to create " +
                "standard APIs, implementations of those APIs, and technology compatibility kits for Java " +
                "runtimes that enable development, deployment, and management of server-side and cloud-native " +
                "applications.");
        jakarta10.setTechnologyList(technologyList);

        String path = System.getProperty("path");
        try {
            MyJakartaManager.setMyJakarta(jakarta10);
            MyJakartaManager.writeToJson(path);
            System.out.println("Записано в файл: " + path);
        } catch (Exception e) {
            fail("Not found");
        }
    }

    @Test
    public void test02ReadFromJson() {
        String path = System.getProperty("path");
        try {
            MyJakartaManager.readFromJson(path);
            System.out.println(MyJakartaManager.getMyJakarta());
        } catch (Exception e) {
            fail("Not found");
        }
    }

    @Test
    public void test03UpdateTechnology() {
        String path = System.getProperty("path");
        try {
            MyJakarta myJakarta = MyJakartaManager.readFromJson(path);
            Technology updateTechnology = myJakarta.getTechnologyList()
                    .get(new Random().nextInt(0, myJakarta.getTechnologyList().size()));
            updateTechnology.setTechnologyDescription(updateTechnology.getTechnologyDescription() + " 2024");
            MyJakartaManager.updateTechnology(updateTechnology, path);
            System.out.println("Обновленная технология записана в файл: " + path);
        } catch (Exception e) {
            fail("Not found");
        }
    }
}
