/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.assembler.fixtures.book;

import org.seedstack.business.assembler.BaseAssembler;

import javax.inject.Named;


@Named("Book") // just to test the DSL with qualifier
public class BookAssembler extends BaseAssembler<StoredBook, BookDto> {

    @Override
    protected void doAssembleDtoFromAggregate(BookDto targetDto, StoredBook sourceAggregate) {
        targetDto.setTitle(sourceAggregate.getEntityId().getTitle());
        targetDto.setAuthor(sourceAggregate.getEntityId().getAuthor());
        targetDto.setPublishDate(sourceAggregate.getPublishDate());
        targetDto.setEditor(sourceAggregate.getEditor());
    }

    @Override
    protected void doMergeAggregateWithDto(StoredBook targetAggregate, BookDto sourceDto) {
        targetAggregate.setPublishDate(sourceDto.getPublishDate());
        targetAggregate.setEditor(sourceDto.getEditor());
        // identity never change
    }
}
