/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.fixtures.interfaces.multi;

import org.seedstack.business.assembler.MatchingEntityId;


public class MultiRepresentation {

    private String id;

    public MultiRepresentation(String id) {
        super();
        this.id = id;
    }

    public MultiRepresentation() {
        super();
    }

    /**
     * Getter id
     *
     * @return the id
     */
    @MatchingEntityId
    public String getId() {
        return id;
    }

    /**
     * Setter id
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }


}
