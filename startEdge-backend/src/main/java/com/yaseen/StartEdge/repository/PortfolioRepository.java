package com.yaseen.StartEdge.repository;

import com.yaseen.StartEdge.model.Portfolio;
import com.yaseen.StartEdge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {

    List<Portfolio> findByUser(User user);
    Portfolio findByUserAndStockSymbol(User user, String symbol);
}
