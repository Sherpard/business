/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.specification.dsl;

public interface PropertySelector<T, SELECTOR extends BaseSelector<T, SELECTOR>> extends BaseSelector<T, SELECTOR> {
    SpecificationPicker<T, SELECTOR> property(String path);
}
