/* src/main/java\com\rsharkar\arbitrager/ ScheduledScrape.java */
package com.rsharkar.Arbitrager;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledScrape {

    private final FightOddsScraper fightOddsScraper;

    public ScheduledScrape(FightOddsScraper fightOddsScraper) {
        this.fightOddsScraper = fightOddsScraper;
    }

    @Scheduled(fixedRate = 3600000) // Scrape odds every hour
    public void performScraping() {
        fightOddsScraper.scrapeOdds(); 
    }
}