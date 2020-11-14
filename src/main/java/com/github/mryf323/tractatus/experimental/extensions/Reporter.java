package com.github.mryf323.tractatus.experimental.extensions;

import com.github.mryf323.tractatus.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
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

    public static Reporter getInstance() {
        return INSTANCE;
    }

    public String format(List<ReportableTR> reportableTRs, List<String> clauseDefinitions) {
        Context context = new Context();
        context.setVariable("clause_def_list", clauseDefinitions);
        context.setVariable("reportable_list", reportableTRs);
        return templateEngine.process("template", context);
    }

    public void save(String path, String string) throws Exception {
        File file = new File(path);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(string);
        out.close();
    }

    public String title(Annotation annotation) {
        return annotation.annotationType().getSimpleName();
    }

    private ReportableTR toReportableTR(CACC annotation) {
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
                .map(valuation -> String.format("%c = %b", valuation.clause(), valuation.valuation()))
                .collect(Collectors.toList());
    }

    private ReportableTR toReportableTR(ClauseCoverage annotation) {
        return new ReportableTR(
                title(annotation),
                annotation.predicate(),
                Collections.emptyList(),
                toList(annotation.valuations())
        );
    }

    public String formatClauseDef(ClauseDefinition annotation) {
        return String.format(
                "%c := %s",
                annotation.clause(),
                annotation.def()
        );
    }

    private ReportableTR toReportableTR(NearFalsePoint annotation) {
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

    private ReportableTR toReportableTR(UniqueTruePoint annotation) {
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

    public ReportableTR toReportableTestRequirement(Annotation annotation) {
        if(annotation instanceof CACC) {
            return toReportableTR((CACC) annotation);
        }
        if(annotation instanceof ClauseCoverage) {
            return toReportableTR((ClauseCoverage) annotation);
        }
        if(annotation instanceof NearFalsePoint) {
            return toReportableTR((NearFalsePoint) annotation);
        }
        if(annotation instanceof UniqueTruePoint) {
            return toReportableTR((UniqueTruePoint) annotation);
        }
        return null;
    }

    public static class ReportableTR {
        public final String title;
        public final String predicate;
        public final List<String> explanations;
        public final List<String> valuations;

        public ReportableTR(String title, String predicate, List<String> explanations, List<String> valuations) {
            this.title = title;
            this.predicate = predicate;
            this.explanations = explanations;
            this.valuations = valuations;
        }
    }

    public static class ClauseDefinitionReport {
        final String clause;
        final String definition;


        private ClauseDefinitionReport(String clause, String definition) {
            this.clause = clause;
            this.definition = definition;
        }
    }
}
