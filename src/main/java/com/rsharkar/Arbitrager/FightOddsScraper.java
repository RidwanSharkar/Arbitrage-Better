/* src/main/java\com\rsharkar\arbitrager/ FightOddsScraper.java */
package com.rsharkar.Arbitrager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FightOddsScraper {

    private static final String BASE_URL = "https://www.bestfightodds.com/";

    public List<Fight> scrapeOdds() {
        List<Fight> fights = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(BASE_URL).get();
            Elements fightRows = doc.select("tbody tr");  // Select each row in the table
            for (Element fightRow : fightRows) {
                Fight fight = new Fight();

                // Select fighter names
                fight.setFighterOne(fightRow.select("td:nth-child(1)").text());  // Adjust if needed
                fight.setFighterTwo(fightRow.select("td:nth-child(2)").text());  // Adjust if needed

                // Select odds for each bookmaker
                Map<String, String> odds = new HashMap<>();
                odds.put("DraftKings", fightRow.select("td:nth-child(3) span.bestbet").text());
                odds.put("BetMGM", fightRow.select("td:nth-child(4) span.bestbet").text());
                odds.put("Caesars", fightRow.select("td:nth-child(5) span.bestbet").text());
                odds.put("BetRivers", fightRow.select("td:nth-child(6) span.bestbet").text());
                odds.put("FanDuel", fightRow.select("td:nth-child(7) span.bestbet").text());
                odds.put("BetWay", fightRow.select("td:nth-child(8) span.bestbet").text());

                fight.setOdds(odds);
                fights.add(fight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fights;
    }
}

public class Fight {
    private String fighterOne;
    private String fighterTwo;
    private Map<String, String> odds;

    public String getFighterOne() {
        return fighterOne;
    }

    public void setFighterOne(String fighterOne) {
        this.fighterOne = fighterOne;
    }

    public String getFighterTwo() {
        return fighterTwo;
    }

    public void setFighterTwo(String fighterTwo) {
        this.fighterTwo = fighterTwo;
    }

    public Map<String, String> getOdds() {
        return odds;
    }

    public void setOdds(Map<String, String> odds) {
        this.odds = odds;
    }
}
