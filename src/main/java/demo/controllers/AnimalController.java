package demo.controllers;

import demo.mail.MailMerge;
import demo.models.Animal;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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