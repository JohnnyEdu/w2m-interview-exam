package org.w2m.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
public class SaveSuperHeroRequestDto implements Serializable {

    @NotBlank(message = "name must not be blank")
    @JsonProperty("name")
    private String name;

    @NotEmpty(message = "super_powers must not be empty")
    @JsonProperty("super_powers")
    private Set<String> superPowers;
}
