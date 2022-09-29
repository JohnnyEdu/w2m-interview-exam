package org.w2m.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Builder
@Data
public class UpdateSuperHeroRequestDto implements Serializable {

    @Min(value = 1, message = "id must be greater than zero")
    @JsonProperty("id")
    private long id;

    @NotBlank(message = "name must not be blank")
    @JsonProperty("name")
    private String name;

    @NotEmpty(message = "super_powers must not be empty")
    @JsonProperty("super_powers")
    private Set<String> superPowers;
}
