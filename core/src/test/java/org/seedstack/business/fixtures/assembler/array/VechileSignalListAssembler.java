/**
 * 
 */
package org.seedstack.business.fixtures.assembler.array;

import java.util.List;

import org.javatuples.Pair;
import org.seedstack.business.assembler.BaseTupleAssembler;

public class VechileSignalListAssembler
        extends BaseTupleAssembler<Pair<VehicleAggregate, List<RoadSignalAggregate>>, VehicleSignalDto> {

    private final VehicleSignalArrayAssembler asm = new VehicleSignalArrayAssembler();

    @Override
    public void mergeAggregateIntoDto(Pair<VehicleAggregate, List<RoadSignalAggregate>> sourceAggregate,
            VehicleSignalDto targetDto) {

        asm.mergeAggregateIntoDto(
                Pair.with(sourceAggregate.getValue0(),
                        sourceAggregate.getValue1().toArray(new RoadSignalAggregate[] {})),
                targetDto);

    }

    @Override
    public void mergeDtoIntoAggregate(VehicleSignalDto sourceDto,
            Pair<VehicleAggregate, List<RoadSignalAggregate>> targetAggregate) {
        throw new UnsupportedOperationException("Cannot convert dto to pair of aggregates");

    }

}
