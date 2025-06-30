package com.yaseen.StartEdge.repository;

import com.yaseen.StartEdge.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    Stock findBySymbol(String symbol);

}
