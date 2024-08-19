/* src/main/java\com\rsharkar\arbitrager/ FightOddsScraper.java */
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
    private WebDriver driver;
    
    public List<Fight> scrapeOdds() 
    {
        driver = null; 
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


            List<WebElement> eventTables = driver.findElements(By.cssSelector(".table-div"));
            
            for (WebElement table : eventTables) 
            {
                WebElement header = table.findElement(By.cssSelector(".table-header"));
                String eventTitle = header.getText();

                if (eventTitle.equals("FUTURE EVENTS")) {
                    System.out.println("Skipping 'Future Events'...READY");
                    break;
                }
                System.out.println("Processing event: " + eventTitle);

                List<WebElement> tableRows = table.findElements(By.cssSelector("tbody tr"));
                System.out.println("Found " + tableRows.size() + " table rows");
                
                for (int i = 0; i < tableRows.size(); i += 2) {
                    if (i + 1 >= tableRows.size()) break; 
                    WebElement fighterOneRow = tableRows.get(i);
                    WebElement fighterTwoRow = i + 1 < tableRows.size() ? tableRows.get(i + 1) : null;

                    if (fighterOneRow != null && fighterTwoRow != null && 
                        !fighterOneRow.getText().isEmpty() && !fighterTwoRow.getText().isEmpty()) {
                        
                        String fighterOne = fighterOneRow.findElement(By.tagName("th")).getText();
                        String fighterTwo = fighterTwoRow.findElement(By.tagName("th")).getText();

                        Map<String, String> oddsFighterOne = getOddsForFighter(fighterOneRow);
                        Map<String, String> oddsFighterTwo = getOddsForFighter(fighterTwoRow);

                        Map<String, Map<String, String>> odds = new LinkedHashMap<>();
                        odds.put(fighterOne, oddsFighterOne);
                        odds.put(fighterTwo, oddsFighterTwo);

                        Fight fight = new Fight(eventTitle, fighterOne, fighterTwo, odds);
                        fights.add(fight);
                    }
                }
                System.out.println("Finished Scraping");
            }
        } 
        catch (Exception e) { throw new RuntimeException("Error during scraping", e); } 
        finally { if (driver != null) { driver.quit(); } }
        return fights;
    }

    private Map<String, String> getOddsForFighter(WebElement fighterRow) 
    {
        Map<String, String> odds = new LinkedHashMap<>();
        List<WebElement> oddsElements = fighterRow.findElements(By.cssSelector("span[id^='oID']")); // "td span.but-sg" "td span.ard.arage-1"
        int bookmakerIndex = 0;
        for (WebElement oddsElement : oddsElements) {
            String bookmakerName = requiredBookmakers.get(bookmakerIndex);
            String odd = oddsElement.getText();
            odds.put(bookmakerName, odd);
            bookmakerIndex++;
            if (bookmakerIndex >= requiredBookmakers.size()) {
                break;
            }
        }
        return odds;
    }

}
