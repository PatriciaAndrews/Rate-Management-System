package com.ratemanagement.rms.repository;

import com.ratemanagement.rms.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RmsRepository extends JpaRepository <Rate,Integer>{
}
