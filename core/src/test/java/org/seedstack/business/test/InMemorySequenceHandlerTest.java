/*
 * Copyright © 2013-2017, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.business.test;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.seedstack.business.util.inmemory.InMemorySequenceGenerator;

public class InMemorySequenceHandlerTest {

    @Test
    public void testInMemorySequenceHandler() {
        InMemorySequenceGenerator inMemorySequenceHandler = new InMemorySequenceGenerator();
        Long handle = inMemorySequenceHandler.generate(null, null);
        Assertions.assertThat(handle)
                .isEqualTo(2);
    }
}
