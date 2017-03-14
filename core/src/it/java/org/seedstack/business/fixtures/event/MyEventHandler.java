/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.fixtures.event;

import org.seedstack.business.EventServiceIT;
import org.seedstack.business.event.BaseEventHandler;

public class MyEventHandler extends BaseEventHandler<MyEvent> {

    @Override
    public void handle(MyEvent event) {
        EventServiceIT.countMyEvent++;
    }
}
