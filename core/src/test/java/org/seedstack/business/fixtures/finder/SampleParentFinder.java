/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.fixtures.finder;

import org.seedstack.business.finder.Finder;

import java.util.List;

@Finder
public interface SampleParentFinder<ITEM> {

    List<ITEM> findAll();

}
