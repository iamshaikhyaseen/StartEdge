package com.yaseen.StartEdge.repository;

import com.yaseen.StartEdge.model.Trade;
import com.yaseen.StartEdge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {

    List<Trade> findByUser(User user);
}
