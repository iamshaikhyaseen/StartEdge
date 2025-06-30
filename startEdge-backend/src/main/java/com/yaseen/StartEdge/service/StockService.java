package com.yaseen.StartEdge.service;

import com.yaseen.StartEdge.model.Stock;
import com.yaseen.StartEdge.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class StockService {

    @Value("${finnhub_api_key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public StockService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public String getStockQuote(String symbol){
        String url= "https://finnhub.io/api/v1/quote?symbol=" + symbol + "&token=" + apiKey;
        return restTemplate.getForObject(url,String.class);
    }

    @Autowired
    private StockRepository stockRepository;

    private Random random=new Random();

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

    public void updateStockPrices(){
        List<Stock> stocks=stockRepository.findAll();
        for (Stock stock:stocks){
            double change=(random.nextDouble() -0.5) * 5;
            double newPrice=Math.max(1,stock.getCurrentPrice()+change);
            stock.setCurrentPrice(newPrice);
        }
        stockRepository.saveAll(stocks);
    }
}
