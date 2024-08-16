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
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Service
public class FightOddsScraper 
{
    private static final String BASE_URL = "https://www.bestfightodds.com/";
    private static final List<String> requiredBookmakers = List.of("DraftKings", "BetMGM", "Caesars", "BetRivers", "FanDuel", "BetWay");

    public List<Fight> scrapeOdds() 
    {
        WebDriver driver = null; 
        List<Fight> fights = new ArrayList<>();
        try {
            WebDriverManager.chromedriver().clearDriverCache().setup();
            
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");  
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-software-rasterizer");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--proxy-server='direct://'");
            options.addArguments("--proxy-bypass-list=*");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-infobars");
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
            
            driver = new ChromeDriver(options);

            System.out.println("Navigating to URL: " + BASE_URL);
            driver.get(BASE_URL);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".odds-table")));
            
            //List<WebElement> tableRows = driver.findElements(By.cssSelector(".odds-table tbody tr"));
            //List<WebElement> tableRows = driver.findElements(By.xpath("//div[contains(@class, 'table-div')]//tr"));
            List<WebElement> eventTables = driver.findElements(By.cssSelector(".table-div"));
            
            for (WebElement table : eventTables) {
                
                //Thread.sleep(5);

                WebElement header = table.findElement(By.cssSelector(".table-header"));
                String eventTitle = header.getText();

                if (eventTitle.equals("FUTURE EVENTS")) {
                    System.out.println("Skipping 'Future Events'...");
                    break;
                }
                System.out.println("Processing event: " + eventTitle);

                List<WebElement> tableRows = table.findElements(By.cssSelector("tbody tr"));
                System.out.println("Found " + tableRows.size() + " table rows");
                //List<WebElement> tableRows = driver.findElements(By.cssSelector(".odds-table tbody tr")); 

                for (int i = 0; i < tableRows.size(); i += 2) {
                    WebElement fighterOneRow = tableRows.get(i);
                    WebElement fighterTwoRow = tableRows.get(i + 1);

                    Map<String, String> oddsFighterOne = getOddsForFighter(fighterOneRow);
                    Map<String, String> oddsFighterTwo = getOddsForFighter(fighterTwoRow);

                        String fighterOne = fighterOneRow.findElement(By.tagName("th")).getText();
                        String fighterTwo = fighterTwoRow.findElement(By.tagName("th")).getText();

                        Map<String, Map<String, String>> odds = new LinkedHashMap<>();
                        odds.put(fighterOne, oddsFighterOne);
                        odds.put(fighterTwo, oddsFighterTwo);

                        Fight fight = new Fight(fighterOne, fighterTwo, odds);
                        fights.add(fight);
                        //System.out.println("Added fight: " + fighterOne + " vs " + fighterTwo);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error during scraping", e);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
        return fights;
    }

    private boolean validateOdds(Map<String, String> oddsFighterOne, Map<String, String> oddsFighterTwo) {
        int countValidOddsOne = (int) requiredBookmakers.stream().filter(b -> oddsFighterOne.containsKey(b) && !oddsFighterOne.get(b).isEmpty()).count();
        int countValidOddsTwo = (int) requiredBookmakers.stream().filter(b -> oddsFighterTwo.containsKey(b) && !oddsFighterTwo.get(b).isEmpty()).count();
        System.out.println("Valid odds count: Fighter One: " + countValidOddsOne + ", Fighter Two: " + countValidOddsTwo);
        return countValidOddsOne >= 1 && countValidOddsTwo >= 1; // TEMP
    }
    private Map<String, String> getOddsForFighter(WebElement fighterRow) {
        Map<String, String> odds = new LinkedHashMap<>();
        List<WebElement> oddsElements = fighterRow.findElements(By.cssSelector("td span [data-bookmaker]"));
        for (WebElement element : oddsElements) {
            String bookmaker = element.getAttribute("data-bookmaker");
            if (requiredBookmakers.contains(bookmaker)) {
                odds.put(bookmaker, element.getText());
            }
        }
        return odds;
    }
}
            /*
            for (int i = 0; i < tableRows.size(); i += 2) {
                Thread.sleep(250);
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
                System.out.println("Scraped " + fights.size() + " fights");
            }

        private Map<String, String> getOddsForFighter(WebElement fighterRow) {
            Map<String, String> odds = new LinkedHashMap<>();
            List<WebElement> oddsElements = fighterRow.findElements(By.tagName("td span"));
            for (int i = 0; i < oddsElements.size(); i++) {
                String odd = oddsElements.get(i).getText();
                odds.put("Bookmaker " + (i + 1), odd); // CHECK
            }
            return odds;
        }

        private Map<String, String> getOddsForFighter(WebElement fighterRow) {
    Map<String, String> odds = new LinkedHashMap<>();
    List<WebElement> oddsElements = fighterRow.findElements(By.cssSelector("td span[data-bookmaker]"));
    System.out.println("Found " + oddsElements.size() + " odds elements"); // Check how many elements are found
    for (WebElement element : oddsElements) {
        String bookmaker = element.getAttribute("data-bookmaker");
        String odd = element.getText();
        System.out.println("Bookmaker: " + bookmaker + ", Odd: " + odd); // Log each bookmaker and odd
        if (requiredBookmakers.contains(bookmaker)) {
            odds.put(bookmaker, odd);
        }
    }
    return odds;
}

            */