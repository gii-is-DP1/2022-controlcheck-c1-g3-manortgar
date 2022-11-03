package org.springframework.samples.petclinic.recoveryroom;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RecoveryRoom extends NamedEntity{
    @NotNull
    
    @Min(value=0)
    double size;
    
    @NotNull
    boolean secure;
    
    @Transient
    RecoveryRoomType roomType;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name="room_type")
    private RecoveryRoomType type;
}
