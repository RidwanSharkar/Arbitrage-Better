/* src/main/java\com\rsharkar\arbitrager/ FightOddsScraper2.java */
package com.rsharkar.Arbitrager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FightOddsScraper {
    private static final String BASE_URL = "https://www.bestfightodds.com/";

    public List<Fight> scrapeOdds() {
        WebDriver driver = null; 
        List<Fight> fights = new ArrayList<>();
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Downloads\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\Lenovo\\Downloads\\GoogleChromePortableBeta\\GoogleChromePortable.exe");
            //options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-debugging-port=9222");
            
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            //options.addArguments("--remote-debugging-port=9222");
            //options.addArguments("--disable-extensions");
            //options.addArguments("--disable-popup-blocking");
            //options.addArguments("--disable-infobars");
            //options.addArguments("--ignore-certificate-errors");
            options.addArguments("--verbose");
            options.addArguments("--log-path=chromedriver.log");
            
            driver = new ChromeDriver(options);


            driver.get(BASE_URL);
            Thread.sleep(10000); // Wait for JS render
            
            List<WebElement> tableRows = driver.findElements(By.cssSelector(".odds-table tbody tr"));
            for (int i = 0; i < tableRows.size(); i += 2) {
                WebElement fighterOneRow = tableRows.get(i);
                WebElement fighterTwoRow = tableRows.get(i + 1);

                String fighterOne = fighterOneRow.findElement(By.tagName("th")).getText();
                String fighterTwo = fighterTwoRow.findElement(By.tagName("th")).getText();

                Map<String, String> oddsFighterOne = getOddsForFighter(fighterOneRow);
                Map<String, String> oddsFighterTwo = getOddsForFighter(fighterTwoRow);

                Map<String, Map<String, String>> odds = new LinkedHashMap<>();
                odds.put(fighterOne, oddsFighterOne);
                odds.put(fighterTwo, oddsFighterTwo);

                Fight fight = new Fight(fighterOne, fighterTwo, odds);
                fights.add(fight);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit(); //close
            }
        }
        return fights;
    }

    private Map<String, String> getOddsForFighter(WebElement fighterRow) {
        Map<String, String> odds = new LinkedHashMap<>();
        List<WebElement> oddsElements = fighterRow.findElements(By.tagName("td"));
        for (int i = 0; i < oddsElements.size(); i++) {
            String odd = oddsElements.get(i).getText();
            odds.put("Bookmaker " + (i + 1), odd); // CHECK
        }
        return odds;
    }
}
