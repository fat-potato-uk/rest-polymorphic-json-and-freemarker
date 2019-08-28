### Polymorphic Json and Freemarker

In this (incredibly contrived) example we are going to be covering
polymorphic Json and freemarker.

We have created a service for a veterinarian whereby when we submit
either a `Dog` or `Cat`, a standard letter is returned with the
appropriate fields filled in, e.g.

```json
{
  "type":"dog",
  "name":"Rex",
  "barks-per-minute":10
}
``` 
Will return:
```text
Dear Sir/Madam

Your dog Rex is barking at 10 barks per minute.

Please use this information as you see fit.

Kind regards

The vet
```

In order to achieve this, we have a controller that takes an `Animal`:

```java
@Slf4j
@RestController
public class AnimalController {

    @Autowired
    private MailMerge mailMerge;

    @PostMapping("/animal")
    String submitAnimal(@RequestBody Animal animal) throws IOException, TemplateException {
        return mailMerge.getMessage(animal);
    }
}
```
What makes this slightly different is that `Animal` is an `abstract`
class, meaning we need some sort of concrete implementations to be
passed in for this to work. 

This is where polymorphic Json comes in:

```java
package demo.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "dog"),
        @JsonSubTypes.Type(value = Cat.class, name = "cat")
})
@Data
public abstract class Animal {
    private String name;
}
```

Here we have annotated our class so that the underlying `ObjectMapper`
bean knows how to marshal the incoming content.

All that is left is for the concrete classes to include the relevant 
annotation:

```java
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
```

You can use implicit type definitions which use the class name itself,
but for this example we have gone the slightly more obvious route.

The remaining work is fairly simple, we use `Freemarker`, a templating
language, to produce the letter:

```xhtml
Dear Sir/Madam

<#assign animalType = animal.class.simpleName>
Your <#if animalType == "Cat">cat ${animal.name} has only ${animal.lives} lives left.</#if><#if animalType == "Dog">dog ${animal.name} is barking at ${animal.barksPerMinute} barks per minute.</#if>

Please use this information as you see fit.

Kind regards

The vet
```

If you want to have a further play around with this example, feel
free to update the unit tests to be more in line with previous
examples.