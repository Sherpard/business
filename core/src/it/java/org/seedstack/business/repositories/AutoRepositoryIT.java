/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.repositories;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.domain.Repository;
import org.seedstack.business.fixtures.domain.customer.Customer;
import org.seedstack.business.fixtures.domain.customer.CustomerId;
import org.seedstack.business.fixtures.infrastructure.persistence.customer.CustomerInMemoryRepository;
import org.seedstack.business.repositories.fixtures.*;
import org.seedstack.seed.it.SeedITRunner;

import javax.inject.Inject;
import javax.inject.Named;


@RunWith(SeedITRunner.class)
public class AutoRepositoryIT {

    @Inject
    @Named("DefaultRepo")
    Repository<TestAggregate, String> testRepo;

    @Inject @Named("mock")
    Repository<TestAggregate, String> testRepo2;

    @Inject
    Repository<Customer, CustomerId> customerRepo;

    @Inject
    Repository<TestAggregate, String> testRepo3;

    @Inject
    Repository<TestAggregate2, String> testRepo4;

    @Test
    public void test() {
        Assertions.assertThat(testRepo).isNotNull();
        Assertions.assertThat(testRepo).isInstanceOf(DefaultRepoSample.class);

        Assertions.assertThat(testRepo2).isNotNull();
        Assertions.assertThat(testRepo2).isInstanceOf(DefaultRepoSample2.class);

        Assertions.assertThat(customerRepo).isNotNull();
        Assertions.assertThat(customerRepo).isInstanceOf(CustomerInMemoryRepository.class);

        Assertions.assertThat(testRepo3).isNotNull();
        Assertions.assertThat(testRepo3).isInstanceOf(DefaultRepoSample.class);

        Assertions.assertThat(testRepo4).isNotNull();
        Assertions.assertThat(testRepo4).isInstanceOf(DefaultRepoSample3.class);
    }
}
