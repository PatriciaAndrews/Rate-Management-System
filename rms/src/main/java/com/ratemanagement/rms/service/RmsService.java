package com.ratemanagement.rms.service;

import com.ratemanagement.rms.exception.NotFoundException;
import com.ratemanagement.rms.model.Rate;
import com.ratemanagement.rms.model.RateSurcharge;

import java.util.List;

public interface RmsService {
    public List<Rate> getAllRates();
    public RateSurcharge getRateById(int rateId);
    public Rate saveRate(Rate rate);
    public Rate updateRate(Rate rate);
    public String deleteRate(int rateId);
}
