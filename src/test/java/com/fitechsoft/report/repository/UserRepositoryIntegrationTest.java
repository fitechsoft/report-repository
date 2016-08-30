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

import com.fitechsoft.report.domain.FRUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Integration tests for {@link UserRepository}.
 *
 * @author Chun Cao
 */
@ContextConfiguration(classes = ApplicationConfig.class)
public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    UserRepository userRepository;


    @Test
    public void savesUserCorrectly() {
        FRUser alice = new FRUser("Alice","Zhang");

        FRUser result = userRepository.save(alice);
        assertThat(result.getId(), is(notNullValue()));
    }

    @Test
    public void readsUserByIdentifier() {

        FRUser fitech = new FRUser("Fitech","Com");

        userRepository.save(fitech);

        FRUser result = userRepository.findByIdentifier(fitech.getIdentifier());

        assertThat(result, is(fitech));
    }

}
