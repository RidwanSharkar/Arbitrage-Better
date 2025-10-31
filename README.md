# 🥊 MMA Arbitrage Betting App
*Arbitrage Better is a full-stack web application that automates the discovery and analysis of arbitrage betting opportunities in MMA bouts—enabling users to identify risk-free profit scenarios by exploiting odds discrepancies across major sportsbooks in real-time.*

## 📜 Overview:

The application continuously monitors and analyzes betting odds from **6 major sportsbooks** (DraftKings, BetMGM, Caesars, BetRivers, FanDuel, and BetWay) to find situations where you can bet on both fighters in a match and guarantee a profit regardless of the outcome.

### 🛠️ Tech Stack:

**Frontend:**
- Angular 18 (Standalone Components)
- TypeScript 5.5
- RxJS (Reactive Programming)
- Angular HttpClient (REST API Integration)

**Backend:**
- Spring Boot 3.3.2
- Java 17
- Maven (Build & Dependency Management)
- Spring Mail (SMTP Email Service)
- Spring Scheduler (Automated Tasks)

**Web Scraping:**
- Selenium WebDriver 4.23
- WebDriverManager 5.9 (ChromeDriver Management)
- Headless Chrome Browser

**Architecture:**
- RESTful API Design
- Client-Server Architecture
- CORS-Enabled Cross-Origin Communication

### ⚡ Key Features:

**1. Live Odds Aggregation**
- Real-time web scraping of odds from multiple bookmakers
- Comprehensive coverage of all upcoming UFC and MMA events
- Data refreshed hourly to capture market movements

**2. Automated Arbitrage Detection**
- Sophisticated algorithm calculates implied probability across all bookmaker combinations
- Instantly identifies arbitrage opportunities when combined odds create a profit margin
- Displays exact profit percentages and optimal bookmaker pairings

**3. Scheduled Monitoring**
- Automated scraping runs every hour without manual intervention
- Tracks odds from weeks in advance up to fight day
- Captures fleeting opportunities that may only exist for narrow time windows

**4. Email Alert System**
- Subscribers receive instant notifications when arbitrage opportunities are detected
- Includes fight details, bookmaker combinations, and expected profit margins
- Ensures you never miss a profitable betting window

**5. Interactive Bet Calculator**
- Visual highlighting of profitable bets directly in the odds table
- Real-time calculation of optimal bet distribution
- Clear display of guaranteed returns on investment

### 💡 Why It Matters:

Arbitrage opportunities in sports betting are rare and typically disappear within hours as bookmakers adjust their lines. This application automates the tedious process of:
- Manually checking odds across multiple platforms
- Performing complex probability calculations
- Monitoring for market inefficiencies 24/7

By leveraging automated scraping and algorithmic analysis, **Arbitrage Better** turns sporadic manual monitoring into a systematic, efficient process for identifying risk-free profit opportunities.


![Homepage1](https://github.com/user-attachments/assets/09e9595e-9688-4e34-8965-edc380edf3ef)

---

# 🏛️ System Architecture & Design

## Application Architecture:

The MMA Arbitrage Betting App follows a **client-server architecture** with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────────┐
│                    Angular Frontend (Port 4200)              │
│  • Displays fights & odds in real-time                       │
│  • Calculates and highlights arbitrage opportunities         │
│  • Provides interactive UI for bet calculations              │
└────────────────────┬────────────────────────────────────────┘
                     │ HTTP REST API
                     │
┌────────────────────▼────────────────────────────────────────┐
│              Spring Boot Backend (Port 8080)                 │
│  • RESTful API endpoints                                     │
│  • Scheduled scraping service                                │
│  • Email notification service                                │
│  • CORS configuration                                        │
└────────────────────┬────────────────────────────────────────┘
                     │
                     │
┌────────────────────▼────────────────────────────────────────┐
│              Selenium Web Scraper                            │
│  • Headless Chrome browser automation                        │
│  • Scrapes live odds from BestFightOdds.com                  │
│  • Extracts data for 6 major bookmakers                      │
└──────────────────────────────────────────────────────────────┘
```

---

##  🔩 How the Application Works:

### **1. Application Initialization**
When the Spring Boot application starts:
- The `@EnableScheduling` annotation activates the scheduling framework
- An `ApplicationReadyEvent` listener triggers an immediate scrape of current fight odds
- The scheduled task is configured to run every hour (3,600,000 milliseconds)

### **2. Web Scraping Process** (`FightOddsScraper.java`)
The scraping engine uses Selenium WebDriver to extract live betting odds:

**Step 1: Browser Setup**
- Initializes a headless Chrome browser with optimized configurations
- Sets user-agent to avoid detection
- Navigates to BestFightOdds.com

**Step 2: Data Extraction**
- Waits for the odds table to load dynamically (up to 30 seconds)
- Identifies event tables on the page (e.g., "UFC 310", "UFC Fight Night")
- Iterates through each event until reaching "FUTURE EVENTS"

**Step 3: Fight Parsing**
For each event:
- Reads fighter pairs (two rows = one fight)
- Extracts fighter names from table headers
- Scrapes odds from 6 major bookmakers:
  - DraftKings
  - BetMGM
  - Caesars
  - BetRivers
  - FanDuel
  - BetWay

**Step 4: Data Structure**
Each fight is stored as a `Fight` object containing:
```java
{
  "eventTitle": "UFC 310 - Alexandre Pantoja vs Kai Asakura",
  "fighterOne": "Alexandre Pantoja",
  "fighterTwo": "Kai Asakura",
  "odds": {
    "Alexandre Pantoja": {
      "DraftKings": "-175",
      "BetMGM": "-180",
      "Caesars": "-170",
      ...
    },
    "Kai Asakura": {
      "DraftKings": "+145",
      "BetMGM": "+150",
      "Caesars": "+140",
      ...
    }
  }
}
```

### **3. Frontend Data Flow** (`app.component.ts`)

**Step 1: Data Fetching**
- On component initialization, Angular sends HTTP GET request to `/scrape-fights`
- Backend returns the complete list of scraped fights with odds

**Step 2: Arbitrage Calculation**
The frontend calculates arbitrage opportunities for every fight:

```typescript
For each fight:
  For each bookmaker combination (e.g., DraftKings vs BetMGM):
    1. Get odds for Fighter A from Bookmaker 1
    2. Get odds for Fighter B from Bookmaker 2
    3. Convert American odds to decimal odds
    4. Calculate: impliedProbability = (1/decimal1) + (1/decimal2)
    5. If impliedProbability < 1:
       - Arbitrage opportunity found!
       - Calculate profit percentage
       - Store bookmaker combination
```

**Conversion Formula:**
- **Positive odds (+150):** `decimal = (150 / 100) + 1 = 2.50`
- **Negative odds (-166):** `decimal = (100 / 166) + 1 ≈ 1.60`

**Step 3: Visual Display**
- Groups fights by event title
- Highlights rows with arbitrage opportunities in green
- Displays profit percentage and optimal bookmaker combination
- Organizes data in an interactive, responsive table

### **4. Scheduled Automation** (`ScheduledScrape.java`)
- Spring's `@Scheduled` annotation runs scraping task hourly
- Ensures odds data is continuously updated
- Prepares system for email notifications when arbitrage is detected

### **5. Email Notification System** (`EmailService.java`)
Configured to send alerts when arbitrage opportunities are found:
- Uses Spring Mail with SMTP configuration
- Notifies subscribers of profitable betting opportunities
- Includes fight details, bookmakers, and expected profit

---

## ⚙️ Backend Components:

### **Core Java Classes:**

**1. `ArbitragerApplication.java`**
- Main Spring Boot application entry point
- Enables scheduling with `@EnableScheduling`
- Triggers initial scrape on application startup

**2. `Fight.java`** (Data Model)
- POJO representing a single MMA bout
- Stores event title, fighter names, and nested odds map
- Structure: `Map<FighterName, Map<Bookmaker, Odds>>`

**3. `FightOddsScraper.java`** (Service Layer)
- Core scraping logic using Selenium WebDriver
- Headless Chrome configuration for server deployment
- Parses DOM structure to extract odds from multiple bookmakers
- Returns list of `Fight` objects

**4. `FightOddsController.java`** (REST Controller)
- Exposes `/scrape-fights` endpoint
- Triggers scraping and returns JSON response
- Called by Angular frontend to fetch current odds

**5. `ScheduledScrape.java`** (Scheduled Task)
- Executes scraping automatically every hour
- Ensures data freshness without manual intervention

**6. `ScrapeController.java`** (Manual Trigger)
- Provides `/scrape` endpoint for manual scraping
- Useful for testing or on-demand updates

**7. `EmailService.java`** (Notification Service)
- Sends email alerts to subscribers
- Configured via `application.properties`

**8. `WebConfig.java`** (CORS Configuration)
- Enables Cross-Origin Resource Sharing
- Allows Angular frontend (localhost:4200) to communicate with backend (localhost:8080)
- Permits GET, POST, PUT, DELETE methods

---

## ⚒️ Frontend Components:

### **Angular Architecture:**

**1. `app.component.ts`** (Main Component)
- **Data Fetching:** HTTP client calls backend API
- **Business Logic:** Arbitrage calculation algorithms
- **Data Transformation:** Groups fights by event, converts odds formats
- **State Management:** Manages fights array and event groups

**2. `app.component.html`** (Template)
- Displays odds in tabular format
- Dynamically highlights arbitrage opportunities
- Responsive design for various screen sizes

**3. `app.component.css`** (Styling)
- Modern, clean UI design
- Color-coded arbitrage indicators
- Professional table styling

---

## 🔌 API Endpoints:

| Endpoint | Method | Description | Response |
|----------|--------|-------------|----------|
| `/scrape-fights` | GET | Scrapes current odds and returns all fights | `List<Fight>` (JSON) |
| `/scrape` | GET | Manually triggers scraping process | Status message |
| `/api/test` | GET | Health check endpoint | "API is working!" |

---

## ♻️ Data Flow Diagram:

```
User Opens App
     │
     ▼
Angular Frontend Loads
     │
     ▼
HTTP GET /scrape-fights
     │
     ▼
Backend Triggers Scraper
     │
     ▼
Selenium Opens Headless Chrome
     │
     ▼
Navigates to BestFightOdds.com
     │
     ▼
Waits for Dynamic Content
     │
     ▼
Extracts Event Tables
     │
     ▼
Parses Fighter Names & Odds
     │
     ▼
Constructs Fight Objects
     │
     ▼
Returns JSON to Frontend
     │
     ▼
Frontend Calculates Arbitrage
     │
     ▼
Displays Results with Highlighting
     │
     ▼
User Views Profitable Opportunities
```

---

## Key Design Decisions:

### **1. Headless Browser Architecture**
- **Why Selenium?** BestFightOdds.com uses dynamic JavaScript rendering. Traditional HTTP scraping (JSoup, HTTP clients) cannot access content loaded after page initialization.
- **Headless Mode:** Reduces resource consumption and enables deployment on servers without GUI.

### **2. Client-Side Arbitrage Calculation**
- **Why Frontend?** Reduces backend load and provides instant visual feedback.
- **Real-time Updates:** Users see arbitrage opportunities immediately without additional API calls.

### **3. Scheduled Scraping**
- **Why Hourly?** Balances data freshness with server resource usage and avoids rate limiting.
- **Startup Scrape:** Ensures data availability immediately after deployment.

### **4. Nested Map Data Structure**
- **Efficient Lookups:** Quickly access odds for any fighter/bookmaker combination: `fight.getOdds().get(fighterName).get(bookmaker)`
- **Maintainable:** Easy to add new bookmakers or fighters

### **5. CORS Configuration**
- **Development Setup:** Allows local frontend (port 4200) to communicate with backend (port 8080)
- **Production Ready:** Can be configured for deployed frontend domain

---

## Development & Deployment:

### **Running the Backend:**
```bash
# Navigate to project root
cd /Users/ridwan/Arbitrage-Better

# Run Spring Boot application
./mvnw spring-boot:run

# Application starts on http://localhost:8080
```

### **Running the Frontend:**
```bash
# Navigate to frontend directory
cd frontend

# Install dependencies
npm install

# Start Angular development server
ng serve

# Application accessible at http://localhost:4200
```

### **Environment Configuration:**
Edit `src/main/resources/application.properties` for email notifications:
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

---

## 🎲 Understanding Odds:

**(+140) Odds:** <br>
&nbsp; &nbsp; &nbsp; &nbsp; • if you bet $100, you will win $140 -> Total Return: $240 <br>
&nbsp; &nbsp; &nbsp; &nbsp; • The (+) indicates the fighter is the **Underdog**.

**(-166) Odds:** <br>
&nbsp; &nbsp; &nbsp; &nbsp; • you need to bet $166 to win $100 -> Total Return: $266 <br>
&nbsp; &nbsp; &nbsp; &nbsp; • The (-) indicates the fighter is the **Favorite**.

---

## 🎰 Arbitrage Betting:

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


![image](https://github.com/user-attachments/assets/097323ba-698e-49b7-b97c-2e5612299842)

However, this arbitrage opportunity disappears within hours, as DraftKings' odds for [Ilia] rise to -175 to close this gap. Bookmakers collaborate to resolve wide discrepencies in odds, yielding only small windows of time to capitalize. Consequently, the closer the date to the actual bout, the less likely an arbitrage bet exists. To combat this, scraping live odds is scheduled for every 15 minutes, priming the app to alert subscribers via email when a current arbitrage opportunity exists, from up to weeks in advance.  



![Arbitrage Example 2](https://github.com/user-attachments/assets/e5ec43fc-3866-47a9-a2e0-faa94cbb624f)

&nbsp; &nbsp; &nbsp; &nbsp; [Jose Perez] at BetMGM <br>
&nbsp; &nbsp; &nbsp; &nbsp; Decimal Odds: 1 + (100 / 165) ≈ 1.60 <br>
&nbsp; &nbsp; &nbsp; &nbsp; Reciprocal: (1 / 1.60) ≈ **0.625**

&nbsp; &nbsp; &nbsp; &nbsp; [Jesse Stirn] at DraftKings <br>
&nbsp; &nbsp; &nbsp; &nbsp; Decimal Odds: 1 + (265/100) ≈ 3.65 <br>
&nbsp; &nbsp; &nbsp; &nbsp; Reciprocal: (1/3.65) ≈ **0.274** <br>
&nbsp; &nbsp; &nbsp; &nbsp; ______________________________________ <br>
&nbsp; &nbsp; &nbsp; &nbsp; 0.625 + 0.317 = **0.942**

---

## 💸 Example Bet: 

Bet **$305.56** on Fighter [Jesse Stirn] at Draftkings: <br>
&nbsp; &nbsp; &nbsp; &nbsp;• Expected Payout: $1,115.28

Bet **$694.44** on Fighter [Jose Perez] at BetMGM: <br>
&nbsp; &nbsp; &nbsp; &nbsp;• Expected Payout: $1,115.28

**Total Investment:** $305.56 + $694.44 = **$1,000** <br>
**Total Payout in Either Case:** **$1,115.28** <br>
_____________________________________________________ <br>
**Total Guaranteed Profit:** $1,115.28 - $1,000 = **$115.28**

---

## Note:

Use at your own risk. Arbitrage betting is NOT illegal; however, bookmakers will likely ban you from their betting platform if detected.

