/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.internal;

import org.seedstack.business.fixtures.application.IndexService;
import org.seedstack.business.fixtures.application.SingletonService;
import org.seedstack.business.fixtures.domain.activation.ActivationException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.seedstack.seed.it.AbstractSeedIT;

import javax.inject.Inject;

/**
 * This class checks the injection of new instances or singletons.
 */
public class BusinessCorePluginSingletonIT extends AbstractSeedIT {

    @Inject
    private SingletonService singletonService;

    @Inject
    private SingletonService singletonService2;

    @Inject
    private IndexService indexService;

    @Inject
    private IndexService indexService2;

    @Test
    public void singleton_injection_should_work() throws ActivationException {
        Assertions.assertThat(singletonService).isEqualTo(singletonService2);
    }

    @Test
    public void default_scope_injection_should_work() throws ActivationException {
        Assertions.assertThat(indexService).isNotEqualTo(indexService2);
    }
}
