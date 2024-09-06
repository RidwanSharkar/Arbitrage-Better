
**MMA Arbitrage Betting App**
________________________________________________________________________________________________________________

**OVERVIEW:**

• Arbitrage-Better is a web application that helps users identify and exploit arbitrage betting opportunities in MMA bouts for guaranteed profit. 

• The application scrapes live odds from bookmakers and calculates potential arbitrage bets, highlighting them for the user in real-time. 

• Since an arbitrage bet may only exist for a narrow window of time, scheduled scraping and an email notification service are implemented to notify subscribers when a current arbitrage opportunity exists.

________________________________________________________________________________________________________________

![Homepage1](https://github.com/user-attachments/assets/09e9595e-9688-4e34-8965-edc380edf3ef)

________________________________________________________________________________________________________________
**UNDERSTANDING ODDS:**

**(+140) Odds:** <br>
&nbsp; &nbsp; &nbsp; &nbsp; • if you bet $100, you will win $140 -> Total Return: $240 <br>
&nbsp; &nbsp; &nbsp; &nbsp; • The (+) indicates the fighter is the **Underdog**.

**(-166) Odds:** <br>
&nbsp; &nbsp; &nbsp; &nbsp; • you need to bet $166 to win $100 -> Total Return: $266 <br>
&nbsp; &nbsp; &nbsp; &nbsp; • The (-) indicates the fighter is the **Favorite**.

________________________________________________________________________________________________________________
**ARBITRAGE BETTING:**

![image](https://github.com/user-attachments/assets/265fc27e-8e24-41cf-8821-596a05d621bb)

By placing bets on both fighters on different bookmakers:

&nbsp; &nbsp; &nbsp; &nbsp; [Ilia] at DraftKings <br>
&nbsp; &nbsp; &nbsp; &nbsp; Decimal Odds: 1 + (100/166) ≈ 1.60 <br>
&nbsp; &nbsp; &nbsp; &nbsp; Reciprocal: (1/1.60) ≈ **0.625**

&nbsp; &nbsp; &nbsp; &nbsp; [Max] at Caesars <br>
&nbsp; &nbsp; &nbsp; &nbsp; Decimal Odds: 1 + (170/100) = 2.70 <br>
&nbsp; &nbsp; &nbsp; &nbsp; Reciprocal: (1/2.70) ≈ **0.370** <br>
&nbsp; &nbsp; &nbsp; &nbsp; ______________________________________ <br>
&nbsp; &nbsp; &nbsp; &nbsp; 0.625 + 0.370 = **0.995**

To find an arbitrage opportunity, we must look for a combination of odds on both fighters where the sum of the reciprocals of the decimal odds is less than 1:

• **[< 1]** Indicates an arbitrage opportunity. The total implied probability is under 100%, suggesting that the market has underpriced the combined probability of all possible outcomes - offering a guaranteed profit scenario regardless of the outcome of the fight.

• **[= 1]** Implies that the market is perfectly balanced, i.e Payout = Risk.

• **[> 1]** Implies no arbitrage bet is possible and the bookmakers may be overpricing the combined probability of the outcomes.

________________________________________________________________________________________________________________

![image](https://github.com/user-attachments/assets/097323ba-698e-49b7-b97c-2e5612299842)

However, this arbitrage opportunity disappears within hours, as DraftKings' odds for [Ilia] rise to -175 to close this gap. Bookmakers collaborate to resolve wide discrepencies in odds, yielding only small windows of time to capitalize. Consequently, the closer the date to the actual bout, the less likely an arbitrage bet exists. To combat this, scraping live odds is scheduled for every 15 minutes, priming the app to alert subscribers via email when a current arbitrage opportunity exists, from up to months in advance.  

________________________________________________________________________________________________________________

![Arbitrage Example 2](https://github.com/user-attachments/assets/e5ec43fc-3866-47a9-a2e0-faa94cbb624f)

&nbsp; &nbsp; &nbsp; &nbsp; [Jose Perez] at BetMGM <br>
&nbsp; &nbsp; &nbsp; &nbsp; Decimal Odds: 1 + (100 / 165) ≈ 1.60 <br>
&nbsp; &nbsp; &nbsp; &nbsp; Reciprocal: (1 / 1.60) ≈ **0.625**

&nbsp; &nbsp; &nbsp; &nbsp; [Jesse Stirn] at DraftKings <br>
&nbsp; &nbsp; &nbsp; &nbsp; Decimal Odds: 1 + (265/100) ≈ 3.65 <br>
&nbsp; &nbsp; &nbsp; &nbsp; Reciprocal: (1/3.65) ≈ **0.274** <br>
&nbsp; &nbsp; &nbsp; &nbsp; ______________________________________ <br>
&nbsp; &nbsp; &nbsp; &nbsp; 0.625 + 0.317 = **0.942**

________________________________________________________________________________________________________________

**EXAMPLE BET:**

Bet **$305.56** on Fighter [Jesse Stirn] at Draftkings: <br>
&nbsp; &nbsp; &nbsp; &nbsp;• Expected Payout: $1,115.28

Bet **$694.44** on Fighter [Jose Perez] at BetMGM: <br>
&nbsp; &nbsp; &nbsp; &nbsp;• Expected Payout: $1,115.28

**Total Investment:** $305.56 + $694.44 = **$1,000** <br>
**Total Payout in Either Case:** **$1,115.28** <br>
_____________________________________________ <br>
**Total Guaranteed Profit:** $1,115.28 - $1,000 = **$115.28**

________________________________________________________________________________________________________________

![image](https://github.com/user-attachments/assets/46800a8c-291a-4527-8ad8-f591320d5cb8)<br>
• 24 hours later, the odds for the [Jesse Stirn] vs [Jose Perez] fight have normalized, but another arbitrage opportunity presents itself.

________________________________________________________________________________________________________________
**NOTE:**

Use at your own risk. Arbitrage betting is NOT illegal; however, bookmakers will likely ban you from their betting platform if detected.

________________________________________________________________________________________________________________
**TECH STACK:**

**Frontend:** Angular, TypeScript

**Backend:** Spring Boot, Java

**Scraping:** Selenium Webdriver, ChromeDriver, WebDriverManager
