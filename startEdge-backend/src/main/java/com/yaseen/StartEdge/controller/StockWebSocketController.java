package com.yaseen.StartEdge.controller;

import com.yaseen.StartEdge.model.Stock;
import com.yaseen.StartEdge.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StockWebSocketController {

    @Autowired
    private StockRepository stockRepository;

    @Scheduled(fixedRate = 10000)
    @SendTo("/topic/stocks")
    public List<Stock> sendStockUpdates(){
        return stockRepository.findAll();
    }

}
