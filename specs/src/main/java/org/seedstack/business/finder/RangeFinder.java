/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.finder;

/**
 * High Level interface for finders that handle ranged result.
 *
 * @param <Item>     the representation type
 * @param <Criteria> the criteria
 */
@Finder
public interface RangeFinder<Item, Criteria> {

    /**
     * Find items according a range and a criteria.
     *
     * @param range    the range
     * @param criteria the criteria
     * @return a result object containing the items
     */
    Result<Item> find(Range range, Criteria criteria);
}