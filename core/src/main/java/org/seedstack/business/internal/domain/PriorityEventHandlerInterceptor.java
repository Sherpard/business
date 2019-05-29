/*
 * Copyright © 2013-2019, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.internal.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.seedstack.business.domain.DomainEventHandler;
import org.seedstack.business.domain.DomainEventInterceptor;

public class PriorityEventHandlerInterceptor implements DomainEventInterceptor {

    @SuppressWarnings("rawtypes")
    @Override
    public List<DomainEventHandler> interceptDomainHandler(
            Collection<DomainEventHandler> handlers) {

        if (handlers == null) {
            return Collections.emptyList();
        }
        return handlers.stream().sorted((x, y) -> Long.compare(x.getPriority(), y.getPriority()))
                .collect(Collectors.toList());

    }

}
