package ru.nabokovsg.result.models.builders;

import ru.nabokovsg.result.dto.parameters.ParameterMeasurementDto;
import ru.nabokovsg.result.models.DefectMeasurement;
import ru.nabokovsg.result.models.DetectedRepairs;
import ru.nabokovsg.result.models.Parameters;

import java.util.List;
import java.util.Set;

public class ParameterMeasurementBuilder {

    private final DefectMeasurement defectMeasurement;
    private final DetectedRepairs detectedRepairs;
    private final Set<Parameters> defectParameters;
    private final List<ParameterMeasurementDto> parametersMeasurements;

    public DefectMeasurement getDefectMeasurement() {
        return defectMeasurement;
    }

    public DetectedRepairs getDetectedRepairs() {
        return detectedRepairs;
    }

    public List<ParameterMeasurementDto> getParametersMeasurements() {
        return parametersMeasurements;
    }

    public Set<Parameters> getDefectParameters() {
        return defectParameters;
    }

    public ParameterMeasurementBuilder(Builder builder) {
        this.defectMeasurement = builder.defectMeasurement;
        this.detectedRepairs = builder.detectedRepairs;
        this.defectParameters = builder.defectParameters;
        this.parametersMeasurements = builder.parametersMeasurements;
    }

    public static class Builder {

        private DefectMeasurement defectMeasurement;
        private DetectedRepairs detectedRepairs;
        private List<ParameterMeasurementDto> parametersMeasurements;
        private Set<Parameters> defectParameters;

        public Builder defectMeasurement(DefectMeasurement defectMeasurement) {
            this.defectMeasurement = defectMeasurement;
            return this;
        }

        public Builder detectedRepairs(DetectedRepairs detectedRepairs) {
            this.detectedRepairs = detectedRepairs;
            return this;
        }

        public Builder parametersMeasurements(List<ParameterMeasurementDto> parametersMeasurements) {
            this.parametersMeasurements = parametersMeasurements;
            return this;
        }

        public Builder defectParameters(Set<Parameters> defectParameters) {
            this.defectParameters = defectParameters;
            return this;
        }

        public ParameterMeasurementBuilder build() {
            return new ParameterMeasurementBuilder(this);
        }
    }
}