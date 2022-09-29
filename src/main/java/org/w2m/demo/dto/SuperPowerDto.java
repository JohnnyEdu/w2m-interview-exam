package org.w2m.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class SuperPowerDto implements Serializable {

    private long id;

    private String name;
}
