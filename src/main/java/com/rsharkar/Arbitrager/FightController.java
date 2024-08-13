/* src/main/java\com\rsharkar\arbitrager/ FightController.java */
package com.rsharkar.Arbitrager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class FightOddsController {

    private final FightOddsScraper fightOddsScraper;

    // Spring automatically injects FightOddsScraper using constructor injection
    public FightOddsController(FightOddsScraper fightOddsScraper) {
        this.fightOddsScraper = fightOddsScraper;
    }

    @GetMapping("/scrape-fights")  // Specific annotation for HTTP GET
    public List<Fight> scrapeFights() {
        return fightOddsScraper.scrapeOdds();
    }
}
