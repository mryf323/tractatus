package com.github.mryf323.tractatus.experimental.extensions;

import com.github.mryf323.tractatus.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.lang.annotation.Annotation;
import java.util.List;

public enum Reporter {

    INSTANCE;

    private final ClassLoaderTemplateResolver templateResolver;
    private final TemplateEngine templateEngine;

    Reporter() {
        templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public String format(Annotation annotation) {
        Context context = new Context();
        context.setVariable("to", "Baeldung");
        return templateEngine.process("template", context);
    }

    public String title(Annotation annotation) {
        return annotation.annotationType().getSimpleName();
    }

    private Context body(CACC annotation) {
        Context context = new Context();
        context.setVariable("to", "Baeldung");
        templateEngine.process("template", context);
        return null;
    }

    private Context body(ClauseCoverage annotation) {
        return null;
    }

    private Context body(ClauseDefinition annotation) {
        return null;
    }

    private Context body(NearFalsePoint annotation) {
        return null;
    }

    private Context body(UniqueTruePoint annotation) {
        return null;
    }
}
