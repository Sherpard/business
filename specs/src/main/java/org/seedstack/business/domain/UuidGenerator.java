/*
 * Copyright © 2013-2017, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.business.domain;

import java.util.UUID;

/**
 * Interface for generating random UUID to be used as identity of entities.
 */
public interface UuidGenerator extends IdentityGenerator<UUID> {

}
