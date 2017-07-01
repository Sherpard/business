/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.pagination.dsl;

import org.seedstack.business.domain.AggregateRoot;
import org.seedstack.business.pagination.Slice;

public interface PaginationTypePicker<A extends AggregateRoot<ID>, ID> {
    SizePicker<A, ID> byPage(long pageIndex);

    LimitPicker<Slice<A>, A, ID> byOffset(long startingOffset);

    KeyValuePicker<A, ID> byAttribute(String attributeName);
}
