/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.fixtures.event;

import org.seedstack.business.Event;
import org.seedstack.business.EventHandler;

public class MyHandler2 implements EventHandler<Event> {
    @Override
    public void handle(Event event) {
        // do nothing
    }

    @Override
    public Class<Event> getEventClass() {
        return Event.class;
    }
}
