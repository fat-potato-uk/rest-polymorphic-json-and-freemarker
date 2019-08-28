package demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName("dog")
class Dog extends Animal{
    @JsonAlias("barks-per-minute")
    private int barksPerMinute;
}
