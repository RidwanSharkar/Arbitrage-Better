/* src/main/java\com\rsharkar\arbitrager/ FightController.java */
package com.rsharkar.Arbitrager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@RestController
public class FightOddsController {

    private final FightOddsScraper fightOddsScraper;

    public FightOddsController(FightOddsScraper fightOddsScraper) {
        this.fightOddsScraper = fightOddsScraper;
    }

    @GetMapping("/scrape-fights") 
    public List<Fight> scrapeFights() {
        return fightOddsScraper.scrapeOdds();
    }
}