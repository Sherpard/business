/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.internal.assembler.dsl;

import org.javatuples.Tuple;
import org.seedstack.business.assembler.dsl.AssembleMultiple;
import org.seedstack.business.assembler.dsl.AssembleMultipleWithQualifier;
import org.seedstack.business.domain.AggregateRoot;
import org.seedstack.business.internal.Tuples;
import org.seedstack.business.pagination.SimpleSlice;
import org.seedstack.business.pagination.Slice;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;


class AssembleMultipleImpl<A extends AggregateRoot<ID>, ID, T extends Tuple> implements AssembleMultipleWithQualifier {
    private final Context context;
    private final Stream<A> aggregates;
    private final Stream<T> aggregateTuples;

    AssembleMultipleImpl(Context context, Stream<A> aggregates, Stream<T> aggregateTuples) {
        this.context = checkNotNull(context, "Context must not be null");
        if (aggregates == null && aggregateTuples == null) {
            throw new NullPointerException("Cannot assemble null");
        }
        if (aggregates != null && aggregateTuples != null) {
            throw new IllegalArgumentException("Cannot specify both aggregates and tuples to assemble");
        }
        this.aggregates = aggregates;
        this.aggregateTuples = aggregateTuples;
    }

    @Override
    public <D> Stream<D> toStreamOf(Class<D> dtoClass) {
        if (aggregates != null) {
            return aggregates.map(aggregate -> context.assemblerOf(getAggregateClass(aggregate), dtoClass).assembleDtoFromAggregate(aggregate));
        } else if (aggregateTuples != null) {
            return aggregateTuples.map(tuple -> context.tupleAssemblerOf(Tuples.itemClasses(tuple), dtoClass).assembleDtoFromAggregate(tuple));
        }
        throw new IllegalStateException("Nothing to assemble");
    }

    @Override
    public <D, C extends Collection<D>> C toCollectionOf(Class<D> dtoClass, Supplier<C> collectionSupplier) {
        C collection = collectionSupplier.get();
        toStreamOf(dtoClass).forEach(collection::add);
        return collection;
    }

    @Override
    public <D> List<D> toListOf(Class<D> dtoClass) {
        return toCollectionOf(dtoClass, ArrayList::new);
    }

    @Override
    public <D> Set<D> toSetOf(Class<D> dtoClass) {
        return toCollectionOf(dtoClass, HashSet::new);
    }

    @Override
    public <D> Slice<D> toSliceOf(Class<D> dtoClass) {
        return new SimpleSlice<>(toListOf(dtoClass));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <D> D[] toArrayOf(Class<D> dtoClass) {
        return toStreamOf(dtoClass).toArray(size -> (D[]) new Object[size]);
    }

    @Override
    public AssembleMultiple with(Annotation qualifier) {
        context.setAssemblerQualifier(qualifier);
        return this;
    }

    @Override
    public AssembleMultiple with(Class<? extends Annotation> qualifier) {
        context.setAssemblerQualifierClass(qualifier);
        return this;
    }

    Context getContext() {
        return context;
    }

    @SuppressWarnings("unchecked")
    private Class<A> getAggregateClass(A aggregate) {
        return (Class<A>) aggregate.getClass();
    }
}
