/* src/main/java\com\rsharkar\arbitrager/ Fight.java */
package com.rsharkar.Arbitrager;
import java.util.Map;

public class Fight 
{
    private String eventTitle;
    private String fighterOne;
    private String fighterTwo;
    private Map<String, Map<String, String>> odds;  // Nested Map

    public Fight(String eventTitle, String fighterOne, String fighterTwo, Map<String, Map<String, String>> odds) 
    {
        this.eventTitle = eventTitle;
        this.fighterOne = fighterOne;
        this.fighterTwo = fighterTwo;
        this.odds = odds;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

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

    public Map<String, Map<String, String>> getOdds() {
        return odds;
    }

    public void setOdds(Map<String, Map<String, String>> odds) {
        this.odds = odds; 
    }
}
