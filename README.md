________________________________________________________________________________________________________________
**MMA Arbitrage Betting App**

![Homepage1](https://github.com/user-attachments/assets/09e9595e-9688-4e34-8965-edc380edf3ef)

________________________________________________________________________________________________________________
**OVERVIEW:**

• Arbitrage-Better is a web application that helps users identify and exploit arbitrage betting opportunities in MMA fights for guaranteed profit. The application scrapes live odds from bookmakers and calculates potential arbitrage bets, highlighting them for the user in real-time. 

• Since there only exists a narrow window of time to capitalize on arbitrage bets, scheduled scraping and an email subscription service is implemented that notifies subscribers when a current arbitrage opportunity exists.

________________________________________________________________________________________________________________
**UNDERSTANDING ODDS:**

(+140) Odds: <br>
&nbsp; &nbsp; &nbsp; &nbsp; • if you bet $100, you will win $140 -> Total Return: $240 <br>
&nbsp; &nbsp; &nbsp; &nbsp; • The (+) indicates the fighter is the **Underdog**.

(-166) Odds: <br>
&nbsp; &nbsp; &nbsp; &nbsp; • you need to bet $166 to win $100 -> Total Return: $266 <br>
&nbsp; &nbsp; &nbsp; &nbsp; • The (-) indicates the fighter is the **Favorite**.

________________________________________________________________________________________________________________
**ARBITRAGE BETTING:**

![image](https://github.com/user-attachments/assets/265fc27e-8e24-41cf-8821-596a05d621bb)

By placing bets on both fighters on different bookmakers:

&nbsp; &nbsp; &nbsp; &nbsp; [Ilia] at DraftKings <br>
&nbsp; &nbsp; &nbsp; &nbsp; Decimal Odds for  = 1 + (100/166) ≈ 1.60 <br>
&nbsp; &nbsp; &nbsp; &nbsp; Reciprocal of Odds: (1/1.60) ≈ **0.625**

&nbsp; &nbsp; &nbsp; &nbsp; [Max] at Caesars <br>
&nbsp; &nbsp; &nbsp; &nbsp; Decimal Odds for  = 1 + (170/100) = 2.70 <br>
&nbsp; &nbsp; &nbsp; &nbsp; Reciprocal of Odds: (1/2.70) ≈ **0.370**

&nbsp; &nbsp; &nbsp; &nbsp; 0.625 + 0.370 = **0.995**

To find an arbitrage opportunity, we must look for a combination of odds on both fighters where the sum of the reciprocals of the decimal odds is less than 1:

• **[< 1]**: Indicates an arbitrage opportunity. The total implied probability is under 100%, suggesting that the market has underpriced the combined &nbsp;probability of all possible outcomes, offering a guaranteed profit scenario regardless of the outcome of the fight. <br>
• **[= 1]**: Implies that the market is perfectly balanced, i.e Payout = Risk.<br>
• **[> 1]**: Implies no arbitrage bet is possible and the bookmakers may have overpricing the combined probability of the outcomes.

________________________________________________________________________________________________________________

![image](https://github.com/user-attachments/assets/097323ba-698e-49b7-b97c-2e5612299842)

However, the arbitrage opportunity disappears within hours as DraftKings' odds for [Ilia] rise to -175 to close this gap. Bookmakers collaborate to resolve any arbitrage betting opportunities, allowing only small windows of time to capitalize. This is the reasoning behind why scraping live odds is scheduled for every 15 minutes and primed to alert subscribers via email when a current arbitrage bet exists.  

________________________________________________________________________________________________________________

![Arbitrage Example 2](https://github.com/user-attachments/assets/e5ec43fc-3866-47a9-a2e0-faa94cbb624f)

&nbsp; &nbsp; &nbsp;[Jose Perez] at BetMGM <br>
&nbsp; &nbsp; &nbsp; &nbsp; Decimal Odds: 1 + (100 / 165) ≈ 1.60 <br>
&nbsp; &nbsp; &nbsp; &nbsp; Reciprocal of Odds: (1 / 1.60) ≈ **0.625**

&nbsp; &nbsp; &nbsp;[Jesse Stim] at DraftKings <br>
&nbsp; &nbsp; &nbsp; &nbsp; Decimal Odds for  = 1 + (265/100) ≈ 3.65 <br>
&nbsp; &nbsp; &nbsp; &nbsp; Reciprocal of Odds: (1/3.65) ≈ **0.274**

&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 0.625 + 0.317 = **0.942**

________________________________________________________________________________________________________________

**EXAMPLE BET:**

Bet **$305.56** on Fighter [Jesse Stim] at Draftkings: <br>
&nbsp; &nbsp; &nbsp; &nbsp;• Expected Payout: $1,115.28

Bet **$694.44** on Fighter [Jose Perez] at BetMGM: <br>
&nbsp; &nbsp; &nbsp; &nbsp;• Expected Payout: $1,115.28

&nbsp; &nbsp; &nbsp; &nbsp;Total Investment: $305.56 + $694.44 = **$1,000**
&nbsp; &nbsp; &nbsp; &nbsp;Total Payout in Either Case: **$1,115.28**
&nbsp; &nbsp; &nbsp; &nbsp;__________________________________________________
&nbsp; &nbsp; &nbsp; &nbsp;Total Profit: $1,115.28 - $1,000 = **$115.28**


________________________________________________________________________________________________________________
**NOTE: **

Use at your own risk. Arbitrage betting is NOT illegal; however, bookmakers will likely ban you from their betting platform if detected.

________________________________________________________________________________________________________________
**TECH STACK:**

**Frontend:** Angular, TypeScript

**Backend:** Spring Boot, Java

**Scraping:** Selenium Webdriver, ChromeDriver, WebDriverManager
