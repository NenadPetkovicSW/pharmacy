package com.jelena.nenad.tim16.dto;

import java.util.List;

public class MedicationOrderWrapperDTO {
    private List<MedicationOrderDTO> orders;

    public List<MedicationOrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<MedicationOrderDTO> orders) {
        this.orders = orders;
    }
}
