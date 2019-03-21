/**
 * 
 */
package org.seedstack.business.fixtures.assembler.array;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.seedstack.business.assembler.BaseTupleAssembler;

@SuppressWarnings("rawtypes")
public class VechileSignalRawListAssembler
        extends BaseTupleAssembler<Pair<VehicleAggregate, ArrayList>, VehicleSignalDto> {

    private final VehicleSignalArrayAssembler asm = new VehicleSignalArrayAssembler();

    @Override
    public void mergeAggregateIntoDto(Pair<VehicleAggregate, ArrayList> sourceAggregate,
            VehicleSignalDto targetDto) {

        //Using arrayList is done so fluent is able to locate this assembler
        @SuppressWarnings("unchecked")
        List<RoadSignalAggregate> list = sourceAggregate.getValue1();

        Pair<VehicleAggregate, RoadSignalAggregate[]> pair = Pair
                .with(sourceAggregate.getValue0(), list.toArray(new RoadSignalAggregate[] {}));
        asm.mergeAggregateIntoDto(pair, targetDto);
    }

    @Override
    public void mergeDtoIntoAggregate(VehicleSignalDto sourceDto,
            Pair<VehicleAggregate, ArrayList> targetAggregate) {
        throw new UnsupportedOperationException("Cannot convert dto to pair of aggregates");

    }

}
