package demo.mail;

import demo.models.Animal;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Component
public class MailMerge {

    public String getMessage(Animal animal) throws IOException, TemplateException {
        var cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(MailMerge.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        var template = cfg.getTemplate("mail.ftl");

        try (var out = new StringWriter()) {
            template.process(Map.of("animal", animal), out);
            return out.getBuffer().toString();
        }
    }
}
