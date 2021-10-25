package com.ratemanagement.rms.controller;

import com.ratemanagement.rms.model.Rate;
import com.ratemanagement.rms.model.RateSurcharge;
import com.ratemanagement.rms.service.RmsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
//@RequestMapping("rates")
@RequiredArgsConstructor
@Slf4j
public class RmsController {

    @Autowired
    private RmsService rmsService;

//    @GetMapping("/")
//    public String welcome2User(Principal principal) {
//        return "Hi "+principal.getName()+" Welcome";
//    }

    @Operation(summary = "Gets All Rates from RMS", operationId = "getAllRates",tags={"Fetch All Rates"})
    @GetMapping(value = "rates/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Rate> getAllRates(){
        return rmsService.getAllRates();
    }

    @Operation(summary = "Gets Rate for given rateid from RMS", operationId = "getRateById",tags={"Fetch a Rate"})
    @GetMapping(value = "rates/{rateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RateSurcharge getRateById(@PathVariable int rateId){
        return rmsService.getRateById(rateId);
    }

    @Operation(summary = "Create Rate from RMS", operationId = "saveRate",tags={"Create a Rate"})
    @PostMapping(value = "rates/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Rate saveRate(@RequestBody Rate rate){
        return rmsService.saveRate(rate);
    }

    @Operation(summary = "Update a given rateid from RMS", operationId = "updateRate",tags={"Update a Rate"})
    @PutMapping(value = "rates/")
    public Rate updateRate(@RequestBody Rate rate){
        return rmsService.updateRate(rate);
    }

    @Operation(summary = "Delete a given rateid from RMS", operationId = "deleteRate",tags={"Delete a Rate"})
    @DeleteMapping(value = "rates/{rateId}")
    public String deleteRate(@PathVariable int rateId){
        return rmsService.deleteRate(rateId);
    }
}
