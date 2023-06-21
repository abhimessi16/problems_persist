package problems.scrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.Duration;
import java.util.Properties;

public class ProblemsScrape {

    public static void main(String[] args) {

        try{

            Properties properties = new Properties();
            properties.load(new FileReader("src/main/resources/scrape.properties"));

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    properties.getProperty("db_url"),
                    properties.getProperty("mysql_user"),
                    properties.getProperty("mysql_pwd")
            );
            Statement statement = connection.createStatement();
            String insertQuery = "";

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            WebDriver webDriver = new ChromeDriver(chromeOptions);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            webDriver.get(properties.getProperty("url"));

            String pageSource = webDriver.getPageSource();
//            System.out.println(pageSource);

            Document document = Jsoup.parse(pageSource);



        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

}
