import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FightOddsScraper {

    private static final String BASE_URL = "https://www.bestfightodds.com/";

    public List<Fight> scrapeOdds() {
        List<Fight> fights = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(BASE_URL).get();
            Elements fightElements = doc.select("CSS selector for fight listings");  // Adjust selector based on actual HTML
            for (Element fightElement : fightElements) {
                Fight fight = new Fight();
                fight.setFighterOne(fightElement.select("CSS selector for fighter one").text());
                fight.setFighterTwo(fightElement.select("CSS selector for fighter two").text());
                // Assuming each bookmaker odds is within a sub-element
                Elements oddsElements = fightElement.select("CSS selector for bookmakers' odds");
                Map<String, String> odds = new HashMap<>();
                for (Element oddsElement : oddsElements) {
                    String bookmaker = oddsElement.select("CSS selector for bookmaker name").text();
                    String odd = oddsElement.select("CSS selector for the odds value").text();
                    odds.put(bookmaker, odd);
                }
                fight.setOdds(odds);
                fights.add(fight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fights;
    }


}

public class Fight 
{
    private String fighterOne;
    private String fighterTwo;
    private Map<String, String> odds;

    public void setOdds(odds)
    {
        this.odds = odds;
    }
}