package com.cubes4.CUBES4.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Long id;

    private Date orderDate;
    private boolean isSupplierOrder;

    public enum OrderDTOStatus {
        PREPARATION,
        READY,
        SENT,
        DELIVERED,
        RECEIVED,
    }

    private OrderDTOStatus status;

    private CustomerDTO customer;

    private SupplierDTO supplier;

    private List<OrderItemDTO> orderItems;
}
