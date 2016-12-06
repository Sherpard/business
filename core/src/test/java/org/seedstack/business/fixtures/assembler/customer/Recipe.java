/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.fixtures.assembler.customer;

import org.seedstack.business.assembler.DtoOf;
import org.seedstack.business.assembler.MatchingEntityId;
import org.seedstack.business.assembler.MatchingFactoryParameter;

/**
 * The recipe assembled based on an order and the customer who passed the order.
 */
// As no assembler implementation is provided the following annotation is required
@DtoOf({Order.class, Customer.class})
public class Recipe {

    private String customerId;

    private String customerName;

    private String product;

    private String orderId;

    public Recipe(String customerId, String customerName, String orderId, String product) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.product = product;
        this.orderId = orderId;
    }

    // The order of the typeIndex depends on the position in @DtoOf
    @MatchingEntityId(typeIndex = 0) // Don't specify the index as it is not a value object id
    @MatchingFactoryParameter(typeIndex = 0, index = 0)
    public String getOrderId() {
        return orderId;
    }

    @MatchingFactoryParameter(typeIndex = 0, index = 1)
    public String getProduct() {
        return product;
    }

    @MatchingEntityId(typeIndex = 1)
    @MatchingFactoryParameter(typeIndex = 1, index = 0)
    public String getCustomerId() {
        return customerId;
    }

    // No annotation require here as the customer name is not part of
    // the customer id or factory method
    public String getCustomerName() {
        return customerName;
    }
}
