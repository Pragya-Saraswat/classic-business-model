package com.businessmodel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@SuppressWarnings("serial")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentId implements Serializable {

    @Column(name = "customerNumber")
    private Integer customerNumber;

    @Column(name = "checkNumber")
    private String checkNumber;
}