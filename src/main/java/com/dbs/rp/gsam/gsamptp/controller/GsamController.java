/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbs.rp.gsam.gsamptp.controller;

import com.dbs.rp.gsam.gsamptp.entity.LoanApportionment;
import com.dbs.rp.gsam.gsamptp.service.LoanApportionmentService;
import com.dbs.rp.gsam.gsamptp.entity.PTP;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author shoukat
 */
@RestController
//@Slf4j
public class GsamController {
    @Autowired
    LoanApportionmentService loas;
    /**
     * Method to save Apportionments for the PTP Id.
     * @param apportionments
     * @param pid
     * @return Created Apportionemnts
     */
    @RequestMapping(value = "/ptp/{pid}/apportionment", method = RequestMethod.POST)
    public List<LoanApportionment> addAportionments(@RequestBody List<LoanApportionment> apportionments,@PathVariable long pid){
       
        return(loas.addApportionment(apportionments,pid));
       
    }
    
    /**
     * Method to save PTPs
     * @param ptp
     * @return Created PTPs
     */
    @RequestMapping(method=RequestMethod.POST,value="/ptp")
    public List<PTP> addPTP(@RequestBody List<PTP> ptp){
       // log.info("Posted "+ptp.toString());
     
      
     return loas.createPTP(ptp);
    }
    
    /**
     * 
     * @param pid
     * @return PTP
     */
      @RequestMapping(method=RequestMethod.GET,value="/ptp/{pid}")
    public PTP getPTP(@PathVariable long pid){
        
     return loas.getPTP(pid);
       
    }
    
    
    /**
     * Method to get all PTPs
     * @return 
     */
     @RequestMapping(method=RequestMethod.GET,value="/ptp")
    public List<PTP> getAllPTP(){
     
               return loas.getAllPTP();
       
    }
    
    /**
     * Method to get app apportionments tied to the PTP Id.
     * @param pid
     * @return 
     */
     @RequestMapping(method=RequestMethod.GET,value="/ptp/{pid}/apportionment")
    public List<LoanApportionment> getAllApport(@PathVariable long pid){
     
               return loas.getLinkedApportionments(pid);
       
    }
    
}
