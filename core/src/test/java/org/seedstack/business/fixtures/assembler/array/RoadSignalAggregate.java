package org.seedstack.business.fixtures.assembler.array;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.seedstack.business.domain.BaseAggregateRoot;

public class RoadSignalAggregate extends BaseAggregateRoot<Long> {

    private String content;
    private Long roadSignalId;

    public RoadSignalAggregate(Long roadSignalId, String content) {
        this.roadSignalId = roadSignalId;
        this.content = content;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof RoadSignalAggregate)) {
            return false;
        }
        RoadSignalAggregate castOther = (RoadSignalAggregate) other;
        return new EqualsBuilder().append(content, castOther.content)
                .append(roadSignalId, castOther.roadSignalId)
                .isEquals();
    }

    public String getContent() {
        return content;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(content).append(roadSignalId).toHashCode();
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("content", content)
                .append("roadSignalId", roadSignalId)
                .toString();
    }

    @Override
    public Long getId() {
        return roadSignalId;
    }

}
