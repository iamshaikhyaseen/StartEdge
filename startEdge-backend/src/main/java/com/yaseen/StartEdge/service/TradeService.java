package com.yaseen.StartEdge.service;

import com.yaseen.StartEdge.model.Portfolio;
import com.yaseen.StartEdge.model.Stock;
import com.yaseen.StartEdge.model.Trade;
import com.yaseen.StartEdge.model.User;
import com.yaseen.StartEdge.repository.PortfolioRepository;
import com.yaseen.StartEdge.repository.StockRepository;
import com.yaseen.StartEdge.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private StockRepository stockRepository;

    public String tradeStock(User user,String symbol, int quantity,String type){
        Stock stock=stockRepository.findBySymbol(symbol);
        if (stock==null) return "Stock not found";

        Portfolio portfolio=portfolioRepository.findByUserAndStockSymbol(user, symbol);
        double price=stock.getCurrentPrice();

        if (type.equalsIgnoreCase("BUY")){
            if (portfolio==null)
                portfolio=new Portfolio(null,user,symbol,quantity,price);
            else {
                double newAvg=(portfolio.getAvgBuyPrice() * portfolio.getQuantity() + price * quantity) /
                        (portfolio.getQuantity() + quantity);
                portfolio.setQuantity(portfolio.getQuantity() + quantity);
                portfolio.setAvgBuyPrice(newAvg);
            }
            portfolioRepository.save(portfolio);
        } else if (type.equalsIgnoreCase("SELL")) {
            if (portfolio==null || portfolio.getQuantity() < quantity)
                return "Insufficient Holdings";
            portfolio.setQuantity(portfolio.getQuantity()-quantity);
            portfolioRepository.save(portfolio);
        }
        tradeRepository.save(new Trade(null,user,symbol,quantity,price,type.toUpperCase(), LocalDateTime.now()));
        return "Trade successful";
    }

    public List<Trade> getTrades(User user){
        return tradeRepository.findByUser(user);
    }

}
