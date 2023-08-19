package com.cbu.assignment.warehouse_inventory_system.web.dto;

import java.util.ArrayList;

public class CustomerOderDto {
    // class attributes
    private ArrayList<TemporalCustomerOrderDto> temporalCustomerOrderDtos;

    // class methods
    public CustomerOderDto(ArrayList<TemporalCustomerOrderDto> temporalCustomerOrderDtos) {
        this.temporalCustomerOrderDtos = temporalCustomerOrderDtos;
    }

    public CustomerOderDto() {
    }

    public ArrayList<TemporalCustomerOrderDto> getTemporalCustomerOrderDtos() {
        return this.temporalCustomerOrderDtos;
    }

    public void setTemporalCustomerOrderDtos(ArrayList<TemporalCustomerOrderDto> temporalCustomerOrderDtos) {
        this.temporalCustomerOrderDtos = temporalCustomerOrderDtos;
    }

};
