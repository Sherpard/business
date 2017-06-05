/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.spi.specification;

import org.seedstack.business.specification.Specification;

public interface SpecificationConverter<S extends Specification, B, C> {

    C convert(S specification, B builder, SpecificationTranslator<B, C> translator);

}
