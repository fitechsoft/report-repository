/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fitechsoft.report.repository;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test case bootstrapping both JavaConfig and XML configuration to validate configuration.
 *
 * @author Chun Cao
 */
@ContextConfiguration(classes = ApplicationConfig.class)
public class DatabaseMetaDataTest extends AbstractIntegrationTest {

    @Test
    public void testDbMetaData() {


        Connection connection = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);

            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = "EMPLOYEE";
            String columnNamePattern = null;


            DatabaseMetaData databaseMetaData = connection.getMetaData();

            ResultSet result = databaseMetaData.getColumns(
                    catalog, schemaPattern, tableNamePattern, columnNamePattern);

            while (result.next()) {
                System.out.println(
                        "  " + result.getString("TABLE_SCHEM")
                                + ", " + result.getString("TABLE_NAME")
                                + ", " + result.getString("COLUMN_NAME")
                                + ", " + result.getString("TYPE_NAME")
                                + ", " + result.getInt("COLUMN_SIZE")
                                + ", " + result.getInt("NULLABLE"));
            }
        } catch (Exception e) {
        } finally {

        }

    }


}
