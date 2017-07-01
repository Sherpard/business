/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.domain;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseEntityTest2 {
    @Test
    public void check_hashcode() {
        Long entityId = 12L;
        BaseChild child = new BaseChild(entityId);
        assertThat(child.hashCode()).isEqualTo(entityId.hashCode());
    }

    @Test
    public void check_equals() {
        Long entityId = 12L;
        BaseChild child1 = new BaseChild(entityId);
        BaseChild child2 = new BaseChild(entityId);

        Set<BaseChild> children = Sets.newHashSet();
        children.add(child1);
        children.add(child2);

        assertThat(children).hasSize(1);
        assertThat(child1).isEqualTo(child2);
    }

    @Test
    public void empty_entity_can_be_added_to_collection() {
        BaseChild child = new BaseChild();
        Set<BaseChild> children = Sets.newHashSet();
        children.add(child);
    }

    static class BaseChild extends BaseEntity<Long> {
        private Long id;
        private String name;

        BaseChild() {
            id = null;
        }

        BaseChild(Long id) {
            this.id = id;
        }
    }
}
