/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.fixtures.assembler.customer;

import org.seedstack.business.assembler.MatchingEntityId;
import org.seedstack.business.assembler.MatchingFactoryParameter;

import java.util.Date;


//@DtoOf(Order.class) // specify the link to the aggregate root
public class OrderDto {
    String orderId;
    String product;
    String customerName;
    int price;
    Date orderDate;

    public OrderDto() {
    }

    public OrderDto(String id, String product) {
        this.orderId = id;
        this.product = product;
    }

    public OrderDto(String id, String product, int price) {
        this.orderId = id;
        this.product = product;
        this.price = price;
    }

    @MatchingEntityId // no need for index because id is primitive
    @MatchingFactoryParameter(index = 0)
    public String getOrderId() {
        return orderId;
    }

    @MatchingFactoryParameter(index = 1)
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
