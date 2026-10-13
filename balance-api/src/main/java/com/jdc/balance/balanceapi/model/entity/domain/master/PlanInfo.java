package com.jdc.balance.balanceapi.model.entity.domain.master;

import com.jdc.balance.balanceapi.model.converter.PlanInfoFeatureConverter;
import com.jdc.balance.balanceapi.model.dto.PlanInfoFeature;
import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class PlanInfo extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int maxEmployee;

    @Column(nullable = false)
    private int maxLedger;

    @Column(nullable = false)
    private int maxEntry;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Convert(converter = PlanInfoFeatureConverter.class)
    private  List<PlanInfoFeature> features;

    private boolean defaultPlan;
    private boolean disabled;
}
