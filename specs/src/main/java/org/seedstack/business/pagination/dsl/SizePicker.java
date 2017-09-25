/*
 * Copyright © 2013-2017, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.business.pagination.dsl;

import org.seedstack.business.domain.AggregateRoot;
import org.seedstack.business.pagination.Page;

/**
 * An element of the {@link Paginator} DSL allowing to specify the size of the page in the case of
 * page-based pagination.
 *
 * @param <A> the aggregate root type that is paginated.
 * @param <I> the aggregate root identifier type.
 */
public interface SizePicker<A extends AggregateRoot<I>, I> {

  /**
   * Specify the size of the page.
   *
   * @param size the size of a page.
   * @return the next operation of the paginator DSL, allowing to pick a specification for selecting
   *     objects returned from the repository.
   */
  SpecificationPicker<Page<A>, A, I> ofSize(long size);
}
