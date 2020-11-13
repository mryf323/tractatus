package com.github.mryf323.tractatus.experimental.extensions;

import com.github.mryf323.tractatus.ClauseDefinition;
import com.github.mryf323.tractatus.ClauseDefinitionContainer;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReportingExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    private static final Reporter reporter = Reporter.getInstance();
    private static final Logger log = LoggerFactory.getLogger(ReportingExtension.class);
    private final List<Reporter.ReportableTR> reportableTRS = new ArrayList<>();
    private final List<String> clauseDefinitions = new ArrayList<>();

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        String report = reporter.format(reportableTRS, clauseDefinitions);
        reporter.save(context.getUniqueId(), report);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Optional<AnnotatedElement> element = context.getElement();
        for(Annotation annotation: context.getElement().get().getAnnotations()) {
            Reporter.ReportableTR reportableTR = reporter.toReportableTR(annotation);
            if(reportableTR != null) {
                reportableTRS.add(reportableTR);
            }
        }
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Optional<AnnotatedElement> element = context.getElement();
        for(Annotation annotation: context.getElement().get().getAnnotations()) {
            if(annotation instanceof ClauseDefinition) {
                clauseDefinitions.add(reporter.formatClauseDef((ClauseDefinition) annotation));
            }
            if(annotation instanceof ClauseDefinitionContainer) {
                Arrays.stream(((ClauseDefinitionContainer) annotation).value())
                        .map(reporter::formatClauseDef)
                        .forEach(clauseDefinitions::add);
            }
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {

    }
}
