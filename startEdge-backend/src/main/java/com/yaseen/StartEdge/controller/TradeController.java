package com.yaseen.StartEdge.controller;

import com.yaseen.StartEdge.model.Trade;
import com.yaseen.StartEdge.model.User;
import com.yaseen.StartEdge.repository.UserRepository;
import com.yaseen.StartEdge.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradeService tradeService;

    @PostMapping("/buy")
    public ResponseEntity<String> buyStock(@AuthenticationPrincipal User user, @RequestParam String symbol, @RequestParam int qty) {
        return ResponseEntity.ok(tradeService.tradeStock(user, symbol, qty, "BUY"));
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellStock(@AuthenticationPrincipal User user, @RequestParam String symbol, @RequestParam int qty) {
        return ResponseEntity.ok(tradeService.tradeStock(user, symbol, qty, "SELL"));
    }

    @GetMapping("/history")
    public ResponseEntity<List<Trade>> getTradeHistory(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(tradeService.getTrades(user));
    }
}
