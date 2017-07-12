/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.internal.assembler;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;
import org.javatuples.Tuple;
import org.seedstack.business.assembler.Assembler;
import org.seedstack.business.assembler.AssemblerRegistry;
import org.seedstack.business.domain.AggregateRoot;
import org.seedstack.business.internal.BusinessErrorCode;
import org.seedstack.business.internal.Tuples;
import org.seedstack.business.BusinessException;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;


public class AssemblerRegistryImpl implements AssemblerRegistry {
    private final Injector injector;

    @Inject
    public AssemblerRegistryImpl(Injector injector) {
        this.injector = injector;
    }

    @Override
    public <A extends AggregateRoot<?>, D> Assembler<A, D> assemblerOf(Class<A> aggregateRoot, Class<D> dto) {
        return findAssemblerOf(aggregateRoot, dto, null, null);
    }

    @Override
    public <A extends AggregateRoot<?>, D> Assembler<A, D> assemblerOf(Class<A> aggregateRoot, Class<D> dto, @Nullable Annotation qualifier) {
        return findAssemblerOf(aggregateRoot, dto, qualifier, null);
    }

    @Override
    public <A extends AggregateRoot<?>, D> Assembler<A, D> assemblerOf(Class<A> aggregateRoot, Class<D> dto, @Nullable Class<? extends Annotation> qualifier) {
        return findAssemblerOf(aggregateRoot, dto, null, qualifier);
    }

    @Override
    public <T extends Tuple, D> Assembler<T, D> tupleAssemblerOf(Class<? extends AggregateRoot<?>>[] aggregateRootTuple, Class<D> dto) {
        return findAssemblerOf(classesToTupleType(aggregateRootTuple), dto, null, null);
    }

    @Override
    public <T extends Tuple, D> Assembler<T, D> tupleAssemblerOf(Class<? extends AggregateRoot<?>>[] aggregateRootTuple, Class<D> dto, Annotation qualifier) {
        return findAssemblerOf(classesToTupleType(aggregateRootTuple), dto, qualifier, null);
    }

    @Override
    public <T extends Tuple, D> Assembler<T, D> tupleAssemblerOf(Class<? extends AggregateRoot<?>>[] aggregateRootTuple, Class<D> dto, @Nullable Class<? extends Annotation> qualifier) {
        return findAssemblerOf(classesToTupleType(aggregateRootTuple), dto, null, qualifier);
    }

    private Type classesToTupleType(Class<? extends AggregateRoot<?>>[] aggregateRootClasses) {
        return Types.newParameterizedType(Tuples.classOfTuple(aggregateRootClasses.length), aggregateRootClasses);
    }

    @SuppressWarnings("unchecked")
    private <T, D> Assembler<T, D> findAssemblerOf(Type aggregateRoot, Class<D> dto, @Nullable Annotation qualifier, @Nullable Class<? extends Annotation> qualifierClass) {
        Assembler<T, D> o;
        try {
            if (qualifier != null) {
                o = (Assembler<T, D>) getInstance(Assembler.class, qualifier, aggregateRoot, dto);
            } else if (qualifierClass != null) {
                o = (Assembler<T, D>) getInstance(Assembler.class, qualifierClass, aggregateRoot, dto);
            } else {
                o = (Assembler<T, D>) getInstance(Assembler.class, aggregateRoot, dto);
            }
        } catch (ConfigurationException e) {
            BusinessException seedException = BusinessException.createNew(BusinessErrorCode.UNABLE_TO_FIND_ASSEMBLER_WITH_QUALIFIER)
                    .put("aggregateRoot", aggregateRoot)
                    .put("dto", dto);
            if (qualifier != null) {
                seedException.put("qualifier", qualifier);
                throw seedException;
            } else if (qualifierClass != null) {
                seedException.put("qualifier", qualifierClass.getSimpleName());
                throw seedException;
            } else {
                throw BusinessException.createNew(BusinessErrorCode.UNABLE_TO_FIND_ASSEMBLER)
                        .put("aggregateRoot", aggregateRoot)
                        .put("dto", dto);
            }
        }
        return o;
    }

    private Object getInstance(Type rawType, Class<? extends Annotation> qualifier, Type... typeArguments) {
        return injector.getInstance(Key.get(TypeLiteral.get(Types.newParameterizedType(rawType, typeArguments)), qualifier));
    }

    private Object getInstance(Type rawType, Annotation qualifier, Type... typeArguments) {
        return injector.getInstance(Key.get(TypeLiteral.get(Types.newParameterizedType(rawType, typeArguments)), qualifier));
    }

    private Object getInstance(Type rawType, Type... typeArguments) {
        return injector.getInstance(Key.get(TypeLiteral.get(Types.newParameterizedType(rawType, typeArguments))));
    }
}
