/* src/main/java\com\rsharkar\arbitrager/ FightOddsController.java */
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
        List<Fight> fights = fightOddsScraper.scrapeOdds();
        System.out.println("Returning " + fights.size() + " fights to frontend");
        return fights;
    }
}
