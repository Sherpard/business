/*
 * Copyright © 2013-2017, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.business.domain;

import java.util.Map;

/**
 * A generator of identity for entities.
 *
 * @param <I> the identifier type.
 * @see IdentityService
 */
public interface IdentityGenerator<I> {

  /**
   * Generate a new identifier for an entity of the specified class.
   *
   * @param <E>              the entity type.
   * @param entityClass      the entity class to generate an identity for.
   * @param entityProperties configuration properties for the entity class.
   * @return the generated identifier.
   */
  <E extends Entity<I>> I generate(Class<E> entityClass, Map<String, String> entityProperties);
}
