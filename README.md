________________________________________________________________________________________________________________

**MMA Arbitrage Betting App**

![Homepage1](https://github.com/user-attachments/assets/09e9595e-9688-4e34-8965-edc380edf3ef)

________________________________________________________________________________________________________________

**OVERVIEW:**

• Arbitrage-Better is a web application that helps users identify and exploit arbitrage betting opportunities in MMA fights. The application scrapes odds from various bookmakers, calculates potential arbitrage bets, and highlights them for the user. 

• Additionally, it features an email subscription service that notifies subscribers when a new arbitrage opportunity is detected.



________________________________________________________________________________________________________________
UNDERSTANDING ODDS:

(+140) Odds: <br>
&nbsp; • if you bet $100, you will win $140 -> Total Return: $240 <br>
&nbsp; • The (+) indicates the fighter is the **Underdog**.

(-166) Odds: <br>
&nbsp; • you need to bet $166 to win $100 -> Total Return: $266 <br>
&nbsp; • The (-) indicates the fighter is the **Favorite**.

________________________________________________________________________________________________________________
ARBITRAGE BETTING:

![image](https://github.com/user-attachments/assets/265fc27e-8e24-41cf-8821-596a05d621bb)

By placing bets on both fighters on different bookmakers:

&nbsp; Decimal Odds for [Ilia] at DraftKings = 1 + (100/166) ≈ 1.60 <br>
&nbsp; Reciprocal of Odds: (1/1.60) ≈ 0.625

&nbsp; Decimal Odds for [Max] at Caesars = 1 + (170/100) = 2.70 <br>
&nbsp; Reciprocal of Odds: (1/2.70) ≈ 0.370

To find an arbitrage opportunity, we must look for a combination of odds on both fighters where the sum of the reciprocals of the decimal odds is less than 1:

&nbsp; 0.625 + 0.370 = 0.995 

This is because the total cost of the bets is less than the guaranteed return from one of the outcomes.

&nbsp; • < 1: Indicates an arbitrage opportunity. The total implied probability is under 100%, suggesting that the market has underpriced the combined probability of all possible outcomes, offering a guaranteed profit scenario regardless of the outcome. 

&nbsp; • = 1: Implies that the market is perfectly balanced, i.e Payout = Risk

&nbsp; • > 1: Implies no arbitrage bet is possible and the bookmakers may have overpricing the combined probability of the outcomes.

![image](https://github.com/user-attachments/assets/097323ba-698e-49b7-b97c-2e5612299842)

However, the arbitrage opportunity disappears within hours as DraftKings odds for [Ilia] rise to -175 to close this gap. Bookmakers collaborate to resolve any arbitrage betting opportunities, allowing only small windows of time to capitalize. This is the reasoning behind why scraping live odds is scheduled for every 15 minutes and primed to alert subscribers via email when a current arbitrage bet exists.  

![Arbitrage Example 2](https://github.com/user-attachments/assets/e5ec43fc-3866-47a9-a2e0-faa94cbb624f)




________________________________________________________________________________________________________________
NOTE: 

________________________________________________________________________________________________________________
**TECH STACK:**

**Frontend:** Angular, TypeScript

**Backend:** Spring Boot, Java

**Scraping:** Selenium Webdriver, ChromeDriver, WebDriverManager
