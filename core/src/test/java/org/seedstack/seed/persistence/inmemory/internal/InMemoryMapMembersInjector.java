/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.seed.persistence.inmemory.internal;

import com.google.inject.MembersInjector;

import java.lang.reflect.Field;


class InMemoryMapMembersInjector<T> implements MembersInjector<T> {

    private final Field field;
    private InMemoryTransactionLink inMemoryTransactionLink;

    InMemoryMapMembersInjector(Field field, InMemoryTransactionLink inMemoryTransactionLink) {
        this.field = field;
        this.inMemoryTransactionLink = inMemoryTransactionLink;
        field.setAccessible(true);
    }

    @Override
    public void injectMembers(T instance) {
        try {
            field.set(instance, new InMemoryMap<>(inMemoryTransactionLink));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}