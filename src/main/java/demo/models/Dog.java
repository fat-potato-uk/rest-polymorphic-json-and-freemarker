package demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class Dog extends Animal{
    @JsonAlias("barks-per-minute")
    private int barksPerMinute;
}
