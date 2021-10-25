package com.ratemanagement.rms.service;

import com.ratemanagement.rms.exception.InternalServerException;
import com.ratemanagement.rms.exception.NoDataFoundException;
import com.ratemanagement.rms.exception.NotFoundException;
import com.ratemanagement.rms.model.Rate;
import com.ratemanagement.rms.model.RateSurcharge;
import com.ratemanagement.rms.model.Surcharge;
import com.ratemanagement.rms.repository.RmsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class RmsServiceImpl implements RmsService {

    @Autowired
    RmsRepository rmsRepository;

    RestTemplate restTemplate = new RestTemplate();
    String surchargeUrl="https://surcharge.free.beeceptor.com/surcharge";

    @Override
    public List<Rate> getAllRates() {
        List<Rate> rates=rmsRepository.findAll();
        if(rates.isEmpty())
            throw new NoDataFoundException("No Data Found");
        else
            return rmsRepository.findAll();
    }

    @Override
    public RateSurcharge getRateById(int rateId) {

        Rate fetchedRate=rmsRepository.findById(rateId).orElse(null);

        if(fetchedRate==null)
            throw new NotFoundException("Not Found");
        else{
            Surcharge surcharge
                    = restTemplate.getForObject(surchargeUrl , Surcharge.class);
            log.info("surcharge::"+surcharge);
            RateSurcharge rateSurcharge= new RateSurcharge();
            rateSurcharge.setRateId(fetchedRate.getRateId());
            rateSurcharge.setRateDescription(fetchedRate.getRateDescription());
            rateSurcharge.setRateEffectiveDate(fetchedRate.getRateEffectiveDate());
            rateSurcharge.setRateExpirationDate(fetchedRate.getRateExpirationDate());
            rateSurcharge.setAmount(fetchedRate.getAmount());
            rateSurcharge.setSurcharge(surcharge.getSurcharge());
            rateSurcharge.setTax(surcharge.getTax());
            return  rateSurcharge;
        }

    }

    @Override
    public Rate saveRate(Rate rate) {
        try {
           return rmsRepository.save(rate);
        }catch(Exception e){
            throw new InternalServerException("Error while saving");
        }
    }

    @Override
    public Rate updateRate(Rate rate) {
        Rate fetchedRate= rmsRepository.findById(rate.getRateId()).orElseThrow(()-> new NotFoundException("Not Found"));
        log.info("Value in fetchedRate:: "+fetchedRate);
        if (fetchedRate!=null) {
            fetchedRate.setRateDescription(rate.getRateDescription());
            fetchedRate.setRateEffectiveDate(rate.getRateEffectiveDate());
            fetchedRate.setRateExpirationDate(rate.getRateExpirationDate());
            fetchedRate.setAmount(rate.getAmount());
            try {
                rmsRepository.save(fetchedRate);
            }catch(Exception e){
                throw new InternalServerException("Error while saving");
            }
        }
        return fetchedRate;
    }

    @Override
    public String deleteRate(int rateId) {
        Rate rate=rmsRepository.findById(rateId).orElseThrow(()-> new NotFoundException("Not Found"));
        rmsRepository.deleteById(rate.getRateId());
        return  "Rate Id: "+ rateId + " is deleted ";
    }
}
