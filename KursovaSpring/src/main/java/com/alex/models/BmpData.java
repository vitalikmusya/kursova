package com.alex.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "bmp_data", schema = "bmp_sensor")
public class BmpData extends GeneralModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "altitudeValue")
    @NotNull(message = "Missing altitude Value")
    private String altitudeValue;

    @Basic
    @Column(name = "temperatureValue")
    @NotNull(message = "Missing temperature value")
    private String temperatureValue;

    @Basic
    @Column(name = "pressureValue")
    @NotNull(message = "Missing pressure Value")
    private String pressureValue;

    @Basic
    @Column(name = "relativePressureValue")
    @NotNull(message = "Missing relative pressure value")
    private String relativePressureValue;


    public BmpData(final String altitudeValue, final String temperatureValue, final String pressureValue,
                   final String relativePressureValue) {
        this.altitudeValue = altitudeValue;
        this.temperatureValue = temperatureValue;
        this.pressureValue = pressureValue;
        this.relativePressureValue = relativePressureValue;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BmpData bmpData = (BmpData) o;
        return Objects.equals(id, bmpData.id) && Objects.equals(altitudeValue, bmpData.altitudeValue)
                && Objects.equals(temperatureValue, bmpData.temperatureValue)
                && Objects.equals(pressureValue, bmpData.pressureValue)
                && Objects.equals(relativePressureValue, bmpData.relativePressureValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, altitudeValue, temperatureValue, pressureValue, relativePressureValue);
    }
}