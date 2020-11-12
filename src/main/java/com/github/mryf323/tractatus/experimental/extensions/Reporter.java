package com.github.mryf323.tractatus.experimental.extensions;

import com.github.mryf323.tractatus.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public ReportableTR toReportable(CACC annotation) {
        return new ReportableTR(
                title(annotation),
                annotation.predicate(),
                List.of(
                        "Predicate Value: " + annotation.predicateValue(),
                        "Major Clause: " + annotation.majorClause()
                ),
                toList(annotation.valuations())
        );
    }

    private List<String> toList(Valuation[] valuations) {
        return Arrays.stream(valuations)
                .map(valuation -> "%c = %b".format(String.valueOf(valuation.clause()), valuation.valuation()))
                .collect(Collectors.toList());
    }

    public ReportableTR toReportable(ClauseCoverage annotation) {
        return new ReportableTR(
                title(annotation),
                annotation.predicate(),
                Collections.emptyList(),
                toList(annotation.valuations())
        );
    }

    private ClauseDefinitionReport toReportable(ClauseDefinition annotation) {
        return new ClauseDefinitionReport(
                String.valueOf(annotation.clause()),
                annotation.def()
        );
    }

    public ReportableTR toReportable(NearFalsePoint annotation) {
        return new ReportableTR(
                title(annotation),
                annotation.predicate(),
                List.of(
                        "CNF: " + annotation.cnf(),
                        "Implicant: " + annotation.implicant(),
                        "Clause: " + annotation.clause()
                ),
                toList(annotation.valuations())
        );
    }

    public ReportableTR toReportable(UniqueTruePoint annotation) {
        return new ReportableTR(
                title(annotation),
                annotation.predicate(),
                List.of(
                        "CNF: " + annotation.cnf(),
                        "Implicant: " + annotation.implicant()
                ),
                toList(annotation.valuations())
        );
    }

    private class ReportableTR {
        final String title;
        final String predicate;
        final List<String> explanations;
        final List<String> valuations;

        public ReportableTR(String title, String predicate, List<String> explanations, List<String> valuations) {
            this.title = title;
            this.predicate = predicate;
            this.explanations = explanations;
            this.valuations = valuations;
        }
    }

    private class ClauseDefinitionReport {
        final String clause;
        final String definition;


        private ClauseDefinitionReport(String clause, String definition) {
            this.clause = clause;
            this.definition = definition;
        }
    }
}
