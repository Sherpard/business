/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.business.fixtures.inmemory.internal;

import org.aopalliance.intercept.MethodInvocation;
import org.seedstack.business.fixtures.inmemory.Store;
import org.seedstack.seed.core.internal.guice.ProxyUtils;
import org.seedstack.seed.transaction.Propagation;
import org.seedstack.seed.transaction.spi.TransactionMetadata;
import org.seedstack.seed.transaction.spi.TransactionMetadataResolver;

import java.lang.reflect.Method;

public class InMemoryMetadataResolver implements TransactionMetadataResolver {

    @Override
    public TransactionMetadata resolve(MethodInvocation methodInvocation, TransactionMetadata defaults) {

        Method method = methodInvocation.getMethod();

        TransactionMetadata transactionMetadata = null;

        // Get Store from method
        // =====================
        Store annotation = method.getAnnotation(Store.class);

        // if null, getting information from class
        if (annotation == null) {
            Class<?> methodClass = ProxyUtils.cleanProxy(methodInvocation.getThis().getClass());
            annotation = methodClass.getAnnotation(Store.class);
        }

        // if annotation always null

        if (annotation != null) {
            transactionMetadata = new TransactionMetadata();
            transactionMetadata.setHandler(InMemoryTransactionHandler.class);
            transactionMetadata.setPropagation(Propagation.REQUIRES_NEW);
            transactionMetadata.addMetadata("store", annotation.value());
        }


        return transactionMetadata;
    }

}