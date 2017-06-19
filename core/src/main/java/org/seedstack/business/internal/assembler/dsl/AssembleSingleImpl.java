/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.internal.assembler.dsl;

import com.google.common.collect.Lists;
import org.javatuples.Tuple;
import org.seedstack.business.assembler.dsl.AssembleSingle;
import org.seedstack.business.assembler.dsl.AssembleSingleWithQualifier;
import org.seedstack.business.domain.AggregateRoot;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;


public class AssembleSingleImpl implements AssembleSingleWithQualifier {
    private final AssembleMultipleImpl dtosAssemblerProvider;

    AssembleSingleImpl(AssemblerDslContext context, AggregateRoot<?> aggregate) {
        List<? extends AggregateRoot<?>> aggregates = Lists.newArrayList(aggregate);
        this.dtosAssemblerProvider = new AssembleMultipleImpl(context, aggregates, null);
    }

    AssembleSingleImpl(AssemblerDslContext context, Tuple aggregateTuple) {
        List<Tuple> aggregateTuples = new ArrayList<>();
        aggregateTuples.add(aggregateTuple);
        this.dtosAssemblerProvider = new AssembleMultipleImpl(context, null, aggregateTuples);
    }

    @Override
    public <D> D to(Class<D> dtoClass) {
        List<D> ds = dtosAssemblerProvider.to(dtoClass);
        if (!ds.isEmpty()) {
            return ds.get(0);
        } else {
            return null;
        }
    }

    @Override
    public AssembleSingle with(Annotation qualifier) {
        this.dtosAssemblerProvider.getContext().setAssemblerQualifier(qualifier);
        return this;
    }

    @Override
    public AssembleSingle with(Class<? extends Annotation> qualifier) {
        this.dtosAssemblerProvider.getContext().setAssemblerQualifierClass(qualifier);
        return this;
    }
}
