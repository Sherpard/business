/**
 * 
 */
package org.seedstack.business.fixtures.assembler.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Pair;
import org.seedstack.business.assembler.BaseTupleAssembler;
import org.seedstack.business.fixtures.assembler.array.VehicleSignalDto.SignalDto;

public class VehicleSignalArrayAssembler
        extends BaseTupleAssembler<Pair<VehicleAggregate, RoadSignalAggregate[]>, VehicleSignalDto> {

    @Override
    public void mergeAggregateIntoDto(Pair<VehicleAggregate, RoadSignalAggregate[]> sourceAggregate,
            VehicleSignalDto targetDto) {

        List<SignalDto> sheets = Arrays.stream(sourceAggregate.getValue1()).map(
                (RoadSignalAggregate x) -> new VehicleSignalDto.SignalDto(x.getId(), x.getContent()))
                .collect(Collectors.toList());

        targetDto.setName(sourceAggregate.getValue0().getName());
        targetDto.setSheets(sheets);

    }

    @Override
    public void mergeDtoIntoAggregate(VehicleSignalDto sourceDto,
            Pair<VehicleAggregate, RoadSignalAggregate[]> targetAggregate) {
        throw new UnsupportedOperationException("Cannot convert dto to pair of aggregates");

    }

}
