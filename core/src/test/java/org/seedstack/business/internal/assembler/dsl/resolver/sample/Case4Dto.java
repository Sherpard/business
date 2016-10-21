/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.internal.assembler.dsl.resolver.sample;

import org.seedstack.business.assembler.MatchingEntityId;

/**
 * Case 4: The first name and last name are mapped to a {@code Pair&lt;String, String&gt;} in the constructor.
 */
public class Case4Dto {

    String firstName;

    String lastName;

    String orderItem;

    String orderDescription;

    public Case4Dto(String firstName, String lastName, String orderItem, String orderDescription) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderItem = orderItem;
        this.orderDescription = orderDescription;
    }

    @MatchingEntityId(index = 0, typeIndex = 0)
    public String getFirstName() {
        return firstName;
    }

    @MatchingEntityId(index = 1, typeIndex = 0)
    public String getLastName() {
        return lastName;
    }

    @MatchingEntityId(index = 0, typeIndex = 1)
    public String getOrderItem() {
        return orderItem;
    }

    @MatchingEntityId(index = 1, typeIndex = 1)
    public String getOrderDescription() {
        return orderDescription;
    }
}
