/* src/main/java\com\rsharkar\arbitrager/ ArbitragerApplication.java */
package com.rsharkar.Arbitrager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArbitragerApplication {

    @Autowired
    private FightOddsScraper fightOddsScraper;

    public static void main(String[] args) {
        SpringApplication.run(ArbitragerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        fightOddsScraper.scrapeOdds();
    }
}
