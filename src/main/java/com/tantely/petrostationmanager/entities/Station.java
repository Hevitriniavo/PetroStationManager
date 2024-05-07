package com.tantely.petrostationmanager.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class Station {
    Integer id;
    String name;
    String address;
    Double maxVolumeGasoline;
    Double maxVolumePetrol;
    Double maxVolumeDiesel;

}
