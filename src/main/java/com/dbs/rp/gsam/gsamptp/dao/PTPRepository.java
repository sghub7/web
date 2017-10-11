/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbs.rp.gsam.gsamptp.dao;

import com.dbs.rp.gsam.gsamptp.entity.PTP;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author shoukat
 */
public interface PTPRepository extends JpaRepository<PTP,Long>{
    
   
    
}
