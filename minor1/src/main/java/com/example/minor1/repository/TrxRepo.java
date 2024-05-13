package com.example.minor1.repository;

import com.example.minor1.model.Trx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrxRepo extends JpaRepository<Trx,Integer> {

    Trx findByTrxId(String trxId);
}
