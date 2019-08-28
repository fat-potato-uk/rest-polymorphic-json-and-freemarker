package demo.models;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName("dog")
class Cat extends Animal{
    private int lives;
}
