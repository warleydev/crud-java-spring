package com.warleydev.desafionelio.dto;

public class VehicleUpdateDTO {
    private Integer color;
    private Long ownerId;

    public VehicleUpdateDTO(){
    }

    public VehicleUpdateDTO(Integer color, Long ownerId) {
        this.color = color;
        this.ownerId = ownerId;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
