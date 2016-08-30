package com.fitechsoft.report.repository;

import com.fitechsoft.report.domain.FRReportESTA;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by chun on 16/8/30.
 */
@ContextConfiguration(classes = ApplicationConfig.class)
public class ReportRepositoryIntegrationTest extends AbstractIntegrationTest{

    @Autowired
    ReportRepository<FRReportESTA> eastRepository;


    @Test
    public void saveReportCorrectly() {
        FRReportESTA estaRtp = new FRReportESTA("fitech", new Date(), "1000");
        estaRtp.setOid("fitech");

        FRReportESTA result = eastRepository.save(estaRtp);
        assertThat(result.getId(), is(notNullValue()));
        FRReportESTA rpt = eastRepository.findByOid("fitech");
        assertThat(rpt, is(notNullValue()));
    }

}
