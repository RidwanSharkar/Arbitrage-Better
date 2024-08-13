/* src/main/java\com\rsharkar\arbitrager/ FightOddsScraper.java */
package com.rsharkar.Arbitrager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FightOddsScraper {
    private static final String BASE_URL = "https://www.bestfightodds.com/";
    private static final List<String> BOOKMAKERS = List.of("DraftKings", "BetMGM", "Caesars", "BetRivers", "FanDuel", "BetWay");

    public List<Fight> scrapeOdds() {
        List<Fight> fights = new ArrayList<>();
        try 
        {
            Thread.sleep(10000);
            Document doc = Jsoup.connect("https://www.bestfightodds.com/")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                                .header("Accept-Encoding", "gzip, deflate, br")
                                .header("Accept-Language", "en-US,en;q=0.5")
                                .header("Connection", "keep-alive")
                                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0")
                                .referrer("http://www.google.com")
                                .timeout(8000)
                                .get();
            Elements tableRows = doc.select(".odds-table tbody tr");
            
            for (int i = 0; i < tableRows.size(); i += 2) {
                Element fighterOneRow = tableRows.get(i);
                Element fighterTwoRow = tableRows.get(i + 1);

                String fighterOne = fighterOneRow.select("th").text();
                String fighterTwo = fighterTwoRow.select("th").text();

                Map<String, String> oddsFighterOne = getOddsForFighter(fighterOneRow);
                Map<String, String> oddsFighterTwo = getOddsForFighter(fighterTwoRow);

                Map<String, Map<String, String>> odds = new LinkedHashMap<>();
                odds.put(fighterOne, oddsFighterOne);
                odds.put(fighterTwo, oddsFighterTwo);

                Fight fight = new Fight(fighterOne, fighterTwo, odds);
                fights.add(fight);
            }
        } 
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted: " + e.getMessage());
        } 
        catch (IOException e) {
            System.out.println("Failed to fetch data: " + e.getMessage());
        }
        return fights;
    }

    private Map<String, String> getOddsForFighter(Element fighterRow) {
        Map<String, String> odds = new LinkedHashMap<>();
        Elements oddsElements = fighterRow.select("td");
        for (int i = 0; i < BOOKMAKERS.size() && i < oddsElements.size(); i++) {
            String odd = oddsElements.get(i).text();
            odds.put(BOOKMAKERS.get(i), odd);
        }
        return odds;
    }


}

