/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.util.inmemory;

import com.google.inject.assistedinject.Assisted;
import org.seedstack.business.domain.AggregateRoot;
import org.seedstack.business.spi.GenericImplementation;

import javax.inject.Inject;

/**
 * Default repository for in-memory persistence.
 * <p>
 * When no specific repository exist for the aggregate, this repository will be injected for
 * {@link org.seedstack.business.domain.Repository} with the qualifier {@literal @}InMemory.
 * </p>
 */
@GenericImplementation
@InMemory
public class DefaultInMemoryRepository<A extends AggregateRoot<ID>, ID> extends BaseInMemoryRepository<A, ID> {
    /**
     * Constructs a DefaultInMemoryRepository.
     *
     * @param genericClasses the resolved generics for the aggregate root class and the key class
     */
    @SuppressWarnings("unchecked")
    @Inject
    public DefaultInMemoryRepository(@Assisted Object[] genericClasses) {
        super((Class<A>) genericClasses[0], (Class<ID>) genericClasses[1]);
    }
}
