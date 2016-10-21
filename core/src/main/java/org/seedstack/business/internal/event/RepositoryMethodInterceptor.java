/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.internal.event;

import org.seedstack.business.EventService;
import org.seedstack.business.domain.AggregateRoot;
import org.seedstack.business.domain.Repository;
import org.seedstack.business.domain.Delete;
import org.seedstack.business.domain.Persist;
import org.seedstack.business.domain.Read;
import org.seedstack.business.domain.events.AggregateDeletedEvent;
import org.seedstack.business.domain.events.AggregatePersistedEvent;
import org.seedstack.business.domain.events.AggregateReadEvent;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.seedstack.business.domain.events.EventErrorCodes;
import org.seedstack.seed.SeedException;

import javax.inject.Inject;

/**
 * Intercepts repositories and fire an event depending on action type, eg. Read, Persist, Delete.
 */
class RepositoryMethodInterceptor implements MethodInterceptor {

    @Inject
    private EventService eventService;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object obj = invocation.proceed();
        if (!Repository.class.isAssignableFrom(invocation.getThis().getClass().getSuperclass())) {
            throw SeedException.createNew(EventErrorCodes.INTERCEPTED_CLASS_SHOULD_BE_A_REPOSITORY);
        }
        Class<? extends AggregateRoot<?>> aggregateRoot = ((Repository<?,?>) invocation.getThis()).getAggregateRootClass();
        if (EventModule.checkAnnotation(invocation.getMethod(), Read.class)) {
            eventService.fire(new AggregateReadEvent(invocation.getMethod(), invocation.getArguments(), aggregateRoot));
        } else if (EventModule.checkAnnotation(invocation.getMethod(), Persist.class)) {
            eventService.fire(new AggregatePersistedEvent(invocation.getMethod(), invocation.getArguments(), aggregateRoot));
        } else if (EventModule.checkAnnotation(invocation.getMethod(), Delete.class)) {
            eventService.fire(new AggregateDeletedEvent(invocation.getMethod(), invocation.getArguments(), aggregateRoot));
        }
        return obj;
    }
}
