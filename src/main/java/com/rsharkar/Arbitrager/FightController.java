package com.rsharkar.arbitrager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rsharkar.arbitrager.service.FightOddsScraper;
import java.util.List;

/*===========================================================================*/

@RestController
@RequestMapping("/api/fights")
public class FightController 
{
    @Autowired
    private FightOddsScraper fightOddsScraper;

    @GetMapping
    public List<Fight> getAllFights() {
        return fightOddsScraper.scrapeOdds();
    }
}
