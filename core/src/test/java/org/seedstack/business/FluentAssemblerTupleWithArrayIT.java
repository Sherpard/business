/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.assertj.core.api.Assertions;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.assembler.Assembler;
import org.seedstack.business.assembler.dsl.FluentAssembler;
import org.seedstack.business.fixtures.assembler.array.RoadSignalAggregate;
import org.seedstack.business.fixtures.assembler.array.VehicleAggregate;
import org.seedstack.business.fixtures.assembler.array.VehicleSignalDto;
import org.seedstack.seed.testing.junit4.SeedITRunner;

@RunWith(SeedITRunner.class)
public class FluentAssemblerTupleWithArrayIT {

    @Inject
    private FluentAssembler fluently;

    @Inject
    private Assembler<Pair<VehicleAggregate, RoadSignalAggregate[]>, VehicleSignalDto> carSignalArrayAssembler;

    @Inject
    private Assembler<Pair<VehicleAggregate, List<RoadSignalAggregate>>, VehicleSignalDto> carSignalListAssembler;

    private VehicleAggregate vehicle;
    private RoadSignalAggregate[] roadSignalArray;

    private List<RoadSignalAggregate> roadSignalList;

    @Before
    public void setUp() {
        vehicle = new VehicleAggregate("X-Wing");
        roadSignalArray = new RoadSignalAggregate[] {
                new RoadSignalAggregate(1L, "Obey Yoda"),
                new RoadSignalAggregate(2L, "Do not anger the wookie"),
                new RoadSignalAggregate(3L, "Do not trust the dark side") };
        roadSignalList = Arrays.asList(roadSignalArray);
    }

    @Test
    public void testArrayFluentAssembler() throws Exception {

        Pair<VehicleAggregate, RoadSignalAggregate[]> pair = Pair.with(vehicle, roadSignalArray);
        VehicleSignalDto dto = fluently.assembleTuple(pair).to(VehicleSignalDto.class);
        assertThatDtoHasBeenMerged(dto);
    }

    @Test
    public void testListFluentAssembler() throws Exception {
        Pair<VehicleAggregate, List<RoadSignalAggregate>> pair = Pair.with(vehicle, roadSignalList);
        VehicleSignalDto dto = fluently.assembleTuple(pair).to(VehicleSignalDto.class);
        assertThatDtoHasBeenMerged(dto);

    }

    @Test
    public void testArrayAssembler() throws Exception {
        VehicleSignalDto dto = new VehicleSignalDto();
        Pair<VehicleAggregate, RoadSignalAggregate[]> pair = Pair.with(vehicle, roadSignalArray);
        carSignalArrayAssembler.mergeAggregateIntoDto(pair, dto);
        assertThatDtoHasBeenMerged(dto);

    }

    @Test
    public void testListAssembler() throws Exception {
        VehicleSignalDto dto = new VehicleSignalDto();
        Pair<VehicleAggregate, List<RoadSignalAggregate>> pair = Pair.with(vehicle, roadSignalList);
        carSignalListAssembler.mergeAggregateIntoDto(pair, dto);
        assertThatDtoHasBeenMerged(dto);
    }

    @Test
    public void testRawArrayLookupSuccess() throws Exception {

        List<RoadSignalAggregate> list = new ArrayList<>();
        list.addAll(this.roadSignalList);
        VehicleSignalDto dto = fluently.assembleTuple(Pair.with(vehicle, list))
                .to(VehicleSignalDto.class);
        assertThatDtoHasBeenMerged(dto);

    }

    private void assertThatDtoHasBeenMerged(VehicleSignalDto dto) {
        Assertions.assertThat(dto).isNotNull();
        Assertions.assertThat(dto.getName()).isEqualTo(vehicle.getName());
        Assertions.assertThat(dto.getSheets()).isNotNull();
        Assertions.assertThat(dto.getSheets().size()).isEqualTo(3);
    }

}
