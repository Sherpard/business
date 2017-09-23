/*
 * Copyright © 2013-2017, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.business.internal.pagination;

import org.seedstack.business.domain.AggregateRoot;
import org.seedstack.business.pagination.Slice;
import org.seedstack.business.pagination.dsl.LimitPicker;
import org.seedstack.business.pagination.dsl.SpecificationPicker;

class LimitPickerImpl<SliceT extends Slice<AggregateRootT>, AggregateRootT extends AggregateRoot<IdT>, IdT> extends
  SpecificationPickerImpl<SliceT, AggregateRootT, IdT> implements LimitPicker<SliceT, AggregateRootT, IdT> {

  private final PaginatorContext<AggregateRootT, IdT> context;

  LimitPickerImpl(PaginatorContext<AggregateRootT, IdT> context, PaginationMode mode) {
    super(context, mode);
    this.context = context;
  }

  @Override
  public SpecificationPicker<SliceT, AggregateRootT, IdT> limit(long limit) {
    this.context.setLimit(limit);
    return this;
  }
}
