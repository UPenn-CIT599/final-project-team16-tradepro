Welcome To Serenity Investments


Quick Start

To start, go to TradePro1.0/src/application/ and run the main() class to launch the GUI. You will see the intro scene. 
The intro scene allows you to either access the Risk Assessment Test or the Portfolio Builder. It also scrapes live data for the DJIA and S&P500. 
It is recommended that you take the Risk Assessment Test before you go to the Portfolio Builder. 
The Risk Assessment Test will return you a risk rating of either "low", "medium" or "high" along with a recommended investment timeline.
The risk rating will be used in the Portfolio Builder for the timeline that it shows. 
In the Portfolio Builder you can click on whatever stocks you want to put in your portfolio. 
Once you do that please double click the visualize button. 
Once that graph has loaded, hit the visualize button again and the timeline will appear. 
If you want to see the future weights of your stocks, please click on the "weights" button.


For JUnit tests, go to TradePro1.0/scr/test/


Details about what's running on the backend

API class
The main function of this class is to construct the requesting url and do https request to get and parse information of a stock. It can do both real time quote and history price data request with customized time range and interval. Then it parses the raw data to structured data type to store in the Stock object.

Stock class
This class invoke the functions of the API class to get the stock information, convert to needed data type, and store in corresponding instance variables. To get real time price, it's suggested to call update() method first to make sure the info is fresh, and then call corresponding getters. For memory efficiency, the history price data won't be requested and stored until getHistory() is called, unlike the other variables requested and stored in constructor.
Available variables by calling getters: symbol, name, currency, price, marketTime, change, changePercent, volumn, dayHigh, dayLow, previousClose, todaysOpen, fiftyTwoWeekLow, fiftyTwoWeekHigh, history

Analysis Class 
The analysis class takes into the daily adjustment close price matrix from API and computes its mean and covariance. Then based on the score the user has been assessed, a strategy will be chosen to adjust the weights between stocks that the user is interested in. A backtesting will be performed for a certain amount of period and will be presented by GUI. 
There are three Strategies involved in this class: the first one is Mean-Variance strategy. The second one is Momentum strategy which is based on the price momentum. The third one is equal weighted strategy and may be chosen as a benchmark. However, SPY is taken as the main benchmark in our presentation. 

Optimizer class
The quadratic optimization problem that happens in Mean-Variance strategy is achieved by adopting Primal Dual Method.

Risk Valuator class
Three major risk metrics have been calculated from the performance of our backtested portfolio in this class. They are drawdown ratio, sharpe ratio and risk rating. Risk rating is the internal defined rating which is based on the variance of our portfolio.

AnalysisRunner Class 
This class will take the score from a risk assessment test to set up frequency of portfolio weights adjustment, longitude of  historical data, and window size for calculating variance of each period. Then the Risk runner will take the stocks that the user inputed in GUI and run the Analysis and risk valuator. 