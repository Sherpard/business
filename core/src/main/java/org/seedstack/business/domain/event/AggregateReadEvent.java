/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.domain.event;

import org.seedstack.business.domain.AggregateRoot;

import java.lang.reflect.Method;

/**
 * Event fired when a method of a subtype of Repository annotated by {@code @Read} is called.
 * This event will contains the method called of the repository and its arguments.
 * <p>
 * To enable this interception add the following line in a props file:
 * </p>
 * <pre>
 * [org.seedstack.business.event]
 * domain.watch=true
 * </pre>
 */
public class AggregateReadEvent extends BaseAggregateEvent {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param methodCalled  intercepted method
     * @param args          arguments of the intercepted method
     * @param aggregateRoot aggregate root class concern by the event
     */
    public AggregateReadEvent(Method methodCalled, Object[] args, Class<? extends AggregateRoot<?>> aggregateRoot) {
        super(methodCalled, args, aggregateRoot);
    }
}
