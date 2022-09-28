package org.w2m.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"id", "name", "super_powers"})
public class SuperHeroDto implements Serializable {
    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("super_powers")
    private Set<String> superPowers;

    public void update() {
        //this
    }

    public void delete() {
        //this
    }
}
