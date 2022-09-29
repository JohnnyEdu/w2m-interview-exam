package org.w2m.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@Builder
@Data
@JsonPropertyOrder({"id", "name", "super_powers"})
public class SuperHeroDto implements Serializable {

    @NotNull(message = "id must not be null")
    @JsonProperty("id")
    private long id;

    @NotBlank(message = "name must not be blank")
    @JsonProperty("name")
    private String name;

    @NotEmpty(message = "super_powers must not be empty")
    @JsonProperty("super_powers")
    private Set<SuperPowerDto> superPowers;

    public void update() {
        //this
    }

    public void delete() {
        //this
    }
}
