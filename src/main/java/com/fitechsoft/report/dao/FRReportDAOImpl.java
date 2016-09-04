package com.fitechsoft.report.dao;

import com.fitechsoft.report.domain.FRFieldFactory;
import com.fitechsoft.report.domain.FRReportField;
import com.fitechsoft.report.domain.FRReportRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by chun on 16/9/4.
 */
@Repository
public class FRReportDAOImpl extends NamedParameterJdbcDaoSupport implements FRReportDAO {

    private static final Logger log = Logger.getLogger(FRReportDAOImpl.class.getName());



    @Autowired
    public FRReportDAOImpl(DataSource dataSource){
        setDataSource(dataSource);
    }

    @Override
    public FRReportRow getRowTemplate(String reportTblName) {

        Connection connection = null;

        FRReportRow row = new FRReportRow(reportTblName);
        FRFieldFactory factory = new FRFieldFactory();

        try {
            connection = DataSourceUtils.getConnection(this.getDataSource());

            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = reportTblName;
            String columnNamePattern = null;


            DatabaseMetaData databaseMetaData = connection.getMetaData();

            ResultSet result = databaseMetaData.getColumns(
                    catalog, schemaPattern, tableNamePattern, columnNamePattern);

            while (result.next()) {
                log.log(Level.INFO,
                        "  " + result.getString("TABLE_SCHEM")
                                + ", " + result.getString("TABLE_NAME")
                                + ", " + result.getString("COLUMN_NAME")
                                + ", " + result.getString("TYPE_NAME")
                                + ", " + result.getInt("COLUMN_SIZE")
                                + ", " + result.getInt("NULLABLE"));


                row.addField(factory.getFieldInstance(result.getString("COLUMN_NAME"), result.getString("TYPE_NAME")));
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        return row;

    }

    @Override
    public void insertRow(FRReportRow row) {

        Map<String, Object> parameters = new HashMap<>();

        String sql = "INSERT INTO " + row.getTemplateName() + "(";
        String paraStr = " VALUES (";

        for (Map.Entry<String, FRReportField> entry : row.getFields().entrySet()) {

            if (!entry.getKey().equals("ID")) {
                sql += entry.getKey() + ", ";
                paraStr += ":" + entry.getKey() + ", ";

                parameters.put(entry.getKey(), entry.getValue().getFieldValue());
            }
        }

        sql = sql.substring(0, sql.length() - 2) + ")";
        paraStr = paraStr.substring(0, paraStr.length() - 2) + ")";

        this.getNamedParameterJdbcTemplate().update(sql + paraStr, parameters);

    }

    @Override
    public void updateRow(FRReportRow row) {

    }

    @Override
    public FRReportRow getRow(Long id) {
        return null;
    }

    @Override
    public List<FRReportRow> getAllRows() {
        return null;
    }

    @Override
    public void deleteRow(FRReportRow row) {

    }
}
