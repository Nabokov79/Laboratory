package ru.nabokovsg.result.models.builders;

import ru.nabokovsg.result.models.*;

import java.util.List;
import java.util.Set;

public class MeasurementBuilder {

    private final EquipmentDiagnosed equipmentDiagnosed;
    private final List<GeodesicMeasurement> geodesicMeasurements;
    private final PermissibleDeviationsGeodesy permissibleDeviations;
    private final Set<ControlPoint> controlPoints;
    private final ControlPointMeasurement controlPointMeasurement;

    public MeasurementBuilder(Builder builder) {
        this.equipmentDiagnosed = builder.equipmentDiagnosed;
        this.geodesicMeasurements = builder.geodesicMeasurements;
        this.permissibleDeviations = builder.permissibleDeviations;
        this.controlPoints = builder.controlPoints;
        this.controlPointMeasurement = builder.controlPointMeasurement;
    }

    public EquipmentDiagnosed getEquipmentDiagnosed() {
        return equipmentDiagnosed;
    }

    public List<GeodesicMeasurement> getGeodesicMeasurements() {
        return geodesicMeasurements;
    }

    public PermissibleDeviationsGeodesy getPermissibleDeviations() {
        return permissibleDeviations;
    }

    public Set<ControlPoint> getControlPoints() {
        return controlPoints;
    }

    public ControlPointMeasurement getControlPointMeasurement() {
        return controlPointMeasurement;
    }

    public static class Builder {

        private EquipmentDiagnosed equipmentDiagnosed;
        private List<GeodesicMeasurement> geodesicMeasurements;
        private PermissibleDeviationsGeodesy permissibleDeviations;
        private Set<ControlPoint> controlPoints;
        private ControlPointMeasurement controlPointMeasurement;

        public Builder equipmentDiagnosed(EquipmentDiagnosed equipmentDiagnosed) {
            this.equipmentDiagnosed = equipmentDiagnosed;
            return this;
        }

        public Builder geodesicMeasurements(List<GeodesicMeasurement> geodesicMeasurements) {
            this.geodesicMeasurements = geodesicMeasurements;
            return this;
        }

        public Builder permissibleDeviations(PermissibleDeviationsGeodesy permissibleDeviations) {
            this.permissibleDeviations = permissibleDeviations;
            return this;
        }

        public Builder controlPoints(Set<ControlPoint> controlPoints) {
            this.controlPoints = controlPoints;
            return this;
        }

        public Builder controlPointMeasurement(ControlPointMeasurement controlPointMeasurement) {
            this.controlPointMeasurement = controlPointMeasurement;
            return this;
        }

        public MeasurementBuilder build() {
            return new MeasurementBuilder(this);
        }
    }
}
