/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.fixtures.infrastructure.persistence.order;

import org.seedstack.business.fixtures.InMemoryRepository;
import org.seedstack.business.fixtures.domain.order.Order;
import org.seedstack.business.fixtures.domain.order.OrderId;
import org.seedstack.business.fixtures.domain.order.OrderRepository;

public class OrderInMemoryRepository extends InMemoryRepository<Order,OrderId> implements OrderRepository {

}

