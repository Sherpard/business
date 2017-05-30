/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.domain.specification;

import org.seedstack.business.domain.AggregateRoot;

public class AndSpecification<A extends AggregateRoot<?>> implements Specification<A> {
    private final Specification<A> lhs;
    private final Specification<A> rhs;

    public AndSpecification(Specification<A> lhs, Specification<A> rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Specification<A> getLhs() {
        return lhs;
    }

    public Specification<A> getRhs() {
        return rhs;
    }

    @Override
    public boolean isSatisfiedBy(A candidate) {
        return lhs.isSatisfiedBy(candidate) && rhs.isSatisfiedBy(candidate);
    }

    @Override
    public String toString() {
        return String.format("(%s) ∧ (%s)", lhs.toString(), rhs.toString());
    }
}
