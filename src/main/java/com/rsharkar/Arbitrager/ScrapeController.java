/* src/main/java\com\rsharkar\arbitrager/ ScrapeController.java */
package com.rsharkar.Arbitrager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ScrapeController {

    @Autowired
    private FightOddsScraper fightOddsScraper;

    @GetMapping("/scrape")
    public String scrapeOdds() {
        try {
            fightOddsScraper.scrapeOdds();
            return "Scrape initiated successfully!";
        } catch (Exception e) {
            return "Error during scraping: " + e.getMessage();
        }
    }
}
