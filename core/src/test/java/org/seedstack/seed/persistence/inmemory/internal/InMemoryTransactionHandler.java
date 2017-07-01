/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.seed.persistence.inmemory.internal;


import org.seedstack.seed.transaction.spi.TransactionHandler;
import org.seedstack.seed.transaction.spi.TransactionMetadata;

class InMemoryTransactionHandler implements TransactionHandler<Object> {
    private final InMemoryTransactionLink inMemoryTransactionLink;


    InMemoryTransactionHandler(InMemoryTransactionLink inMemoryTransactionLink) {
        this.inMemoryTransactionLink = inMemoryTransactionLink;
    }

    @Override
    public void doInitialize(TransactionMetadata transactionMetadata) {
        Object store = transactionMetadata.getMetadata("store");
        if (store != null) {
            inMemoryTransactionLink.push(store.toString());
        }

    }

    @Override
    public void doJoinGlobalTransaction() {
        // no transaction support
    }

    @Override
    public String doCreateTransaction() {
        // no transaction support
        return null;
    }

    @Override
    public void doBeginTransaction(Object currentTransaction) {
        // no transaction support
    }

    @Override
    public void doCommitTransaction(Object currentTransaction) {
        // no transaction support
    }

    @Override
    public void doMarkTransactionAsRollbackOnly(Object currentTransaction) {
        // no transaction support
    }

    @Override
    public void doRollbackTransaction(Object currentTransaction) {
        // no transaction support
    }

    @Override
    public void doReleaseTransaction(Object currentTransaction) {
        // no transaction support
    }

    @Override
    public void doCleanup() {
        inMemoryTransactionLink.pop();
    }

    @Override
    public Object getCurrentTransaction() {
        return new Object();
    }
}