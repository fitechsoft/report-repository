package com.fitechsoft.report.repository;

import com.fitechsoft.report.dao.FRReportDAO;
import com.fitechsoft.report.domain.FRReportRow;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by chun on 16/8/30.
 */
@ContextConfiguration(classes = ApplicationConfig.class)
public class ReportRepositoryIntegrationTest extends AbstractIntegrationTest{

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    FRReportDAO reportDAO;

//    @Test
//    public void saveReportCorrectly() {
//        FRReportESTA estaRtp = new FRReportESTA("fitech", new Date(), "1000");
//        estaRtp.setOid("fitech");
//
//        FRReportESTA result = eastRepository.save(estaRtp);
//        assertThat(result.getId(), is(notNullValue()));
//        FRReportESTA rpt = eastRepository.findByOid("fitech");
//        assertThat(rpt, is(notNullValue()));
//
//        List<FRReportESTA> aRpt = eastRepository.findReportByAccountName("fitech");
//
//        assertThat(aRpt.size(), is(1));
//
//        try {
//            System.in.read();
//        }catch (IOException e){
//
//        }
//    }

    @Test
    public void getRowTemplate() {
        FRReportRow template = reportDAO.getRowTemplate("EMPLOYEE");

        assertThat(template, is(notNullValue()));

        try{
            FRReportRow row1 =  template.clone();


            FRReportRow row2 =  template.clone();



            reportDAO.insertRow(row1);
            reportDAO.insertRow(row2);

        }catch ( CloneNotSupportedException e){

        }


    }

}
