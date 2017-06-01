/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.domain.specification;

import org.seedstack.business.domain.AggregateRoot;

public class LessThanSpecification<A extends AggregateRoot<?>> extends ComparableSpecification<A> {
    public LessThanSpecification(String path, Comparable<?> expectedValue) {
        super(path, expectedValue, -1);
    }

    @Override
    public String toString() {
        return String.format("%s < %s", path, expectedValue.toString());
    }
}
