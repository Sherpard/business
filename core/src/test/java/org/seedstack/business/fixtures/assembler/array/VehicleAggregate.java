/**
 * 
 */
package org.seedstack.business.fixtures.assembler.array;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.seedstack.business.domain.BaseAggregateRoot;

public class VehicleAggregate extends BaseAggregateRoot<String> {
    private String name;

    public VehicleAggregate(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof VehicleAggregate)) {
            return false;
        }
        VehicleAggregate castOther = (VehicleAggregate) other;
        return new EqualsBuilder().append(name, castOther.name).isEquals();
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).toHashCode();
    }

}
