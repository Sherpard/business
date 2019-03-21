/**
 * 
 */
package org.seedstack.business.fixtures.assembler.array;

import java.util.List;

public class VehicleSignalDto {

    private String name;

    private List<SignalDto> signals;

    public VehicleSignalDto() {
    }

    public String getName() {
        return name;
    }

    public List<SignalDto> getSheets() {
        return signals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSheets(List<SignalDto> signals) {
        this.signals = signals;
    }

    public static class SignalDto {
        private String content;
        private Long signalId;

        public SignalDto(Long signalId, String content) {

            this.signalId = signalId;
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public Long getsignalId() {
            return signalId;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setsignalId(Long signalId) {
            this.signalId = signalId;
        }
    }

}
