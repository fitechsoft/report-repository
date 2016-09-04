package com.fitechsoft.report.repository;

import com.fitechsoft.report.domain.FRReportESTA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chun on 16/8/31.
 */
public interface ESTARepository extends ReportRepository<FRReportESTA> {

//    @Query("select r from FRReportESTA r join r.accountName a where (a.fieldValue = :accountName)")
//    public List<FRReportESTA> findReportByAccountName(@Param("accountName")String accountName);
}
