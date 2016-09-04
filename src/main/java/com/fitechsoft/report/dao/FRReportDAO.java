package com.fitechsoft.report.dao;

import com.fitechsoft.report.domain.FRReportRow;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by chun on 16/9/4.
 */
public interface FRReportDAO {


    public FRReportRow getRowTemplate(String reportTblName);


    //C
    public void insertRow(FRReportRow row);

    //U
    public void updateRow(FRReportRow row);

    //R
    public FRReportRow getRow(Long id);

    //R
    public List<FRReportRow> getAllRows();

    //D
    public void deleteRow(FRReportRow row);


}
