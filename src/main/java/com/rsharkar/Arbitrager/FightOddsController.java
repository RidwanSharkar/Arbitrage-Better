/* src/main/java\com\rsharkar\arbitrager/ FightOddsController.java */
package com.rsharkar.Arbitrager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Configuration;
import java.util.List;

/*
@RestController
public class FightOddsController {

    private final FightOddsScraper fightOddsScraper;

    public FightController(FightOddsScraper fightOddsScraper) {
        this.fightOddsScraper = fightOddsScraper;
    }

    @GetMapping("/scrape")
    public ResponseEntity<?> scrapeFights() {
        try {
            List<Fight> fights = fightOddsScraper.scrapeOdds();
            return ResponseEntity.ok(fights);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error during scraping: " + e.getMessage());
        }
    }
}
*/

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

