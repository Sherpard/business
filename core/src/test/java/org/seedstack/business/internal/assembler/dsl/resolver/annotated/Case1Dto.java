/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.internal.assembler.dsl.resolver.annotated;

import org.seedstack.business.assembler.MatchingEntityId;


public class Case1Dto {
    private int id;
    private String name;

    public Case1Dto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @MatchingEntityId
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
