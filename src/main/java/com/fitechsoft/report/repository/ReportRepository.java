package com.fitechsoft.report.repository;

import com.fitechsoft.report.domain.FRReportTemplate;
import com.fitechsoft.repository.core.FDObjectRepository;

/**
 * Created by chun on 16/8/30.
 */
public interface ReportRepository<R extends FRReportTemplate>  extends FDObjectRepository<R> {

}
