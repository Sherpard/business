/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.internal.assembler.dsl;

import org.seedstack.business.assembler.dsl.AggregateNotFoundException;
import org.seedstack.business.assembler.dsl.MergeAggregatesWithRepoProvider;
import org.seedstack.business.assembler.dsl.MergeAggregatesWithRepoThenFactProvider;
import org.seedstack.business.domain.AggregateRoot;
import org.seedstack.business.domain.Repository;

import java.util.ArrayList;
import java.util.List;


public class MergeAggregatesWithRepoProviderImpl<A extends AggregateRoot<?>> extends BaseAggAssemblerWithRepoProviderImpl<A> implements MergeAggregatesWithRepoProvider<A>, MergeAggregatesWithRepoThenFactProvider<A> {

    private final Class<A> aggregateClass;
    private final List<?> dtos;

    public MergeAggregatesWithRepoProviderImpl(AssemblerDslContext context, Class<A> aggregateClass, List<?> dtos) {
        super(context);
        this.aggregateClass = aggregateClass;
        this.dtos = dtos;
    }

    // --------------------------- AggAssemblerWithRepoProvider

    @Override
    public MergeAggregatesWithRepoThenFactProvider<A> fromRepository() {
        // Just redirect to the expected DSL path
        return this;
    }

    @Override
    public List<A> fromFactory() {
        List<A> aggregateRoots = new ArrayList<>(dtos.size());
        for (Object dto : dtos) {
            aggregateRoots.add(fromFactory(aggregateClass, dto));
        }

        return aggregateRoots;
    }

    /**
     * Loads an aggregate roots from a repository.
     *
     * @param key the aggregate roots identity
     * @return the loaded aggregate root
     */
    @SuppressWarnings("unchecked")
    protected A loadFromRepo(Class<? extends AggregateRoot<?>> aggregateClass, Object key) {
        Repository repository = context.repositoryOf(aggregateClass);
        return (A) repository.load(key);
    }

    // --------------------------- AggAssemblerWithRepoAndFactProvider methods

    @Override
    public List<A> orFail() throws AggregateNotFoundException {
        List<A> aggregateRoots = new ArrayList<>(dtos.size());
        for (Object dto : dtos) {
            Object id = resolveId(dto, aggregateClass);
            A a = loadFromRepo(aggregateClass, id);

            if (a == null) {
                throw new AggregateNotFoundException(String.format("Unable to load aggregate %s for id: %s", aggregateClass.getName(), id));
            }

            aggregateRoots.add(assembleWithDto(a, dto));
        }
        return aggregateRoots;
    }

    @Override
    public List<A> orFromFactory() {
        boolean atLeastOneAggregateNotFound = false;
        boolean atLeastOneAggregateFound = false;
        List<A> aggregateRoots = new ArrayList<>(dtos.size());

        // load from the repository
        for (Object dto : dtos) {
            Object id = resolveId(dto, aggregateClass);
            A a = loadFromRepo(aggregateClass, id);
            if (a == null) {
                atLeastOneAggregateNotFound = true;
            } else {
                atLeastOneAggregateFound = true;
                aggregateRoots.add(a);
            }
            if (atLeastOneAggregateFound && atLeastOneAggregateNotFound) {
                throw new IllegalStateException("State non consistent some aggregate are persisted but not all.");
            }
        }

        if (atLeastOneAggregateNotFound) {
            // Then if none aggregate were persisted, fallback on factory
            return fromFactory();
        } else {
            return aggregateRoots;
        }
    }

}
