package com.github.mryf323.tractatus.experimental.extensions;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Optional;

public class ReportingExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback
{

    private static final Logger log = LoggerFactory.getLogger(ReportingExtension.class);


    public void postProcessTestInstance(
            Object testInstance,
            ExtensionContext context) throws Exception {
        Optional<AnnotatedElement> element = context.getElement();
        for(Annotation annotation: context.getElement().get().getAnnotations()) {
            log.info(annotation.annotationType().getSimpleName());
        }
        Logger logger = LoggerFactory.getLogger(testInstance.getClass());
        testInstance.getClass()
                .getMethod("setLogger", Logger.class)
                .invoke(testInstance, logger);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        Optional<AnnotatedElement> element = context.getElement();
        for(Annotation annotation: context.getElement().get().getAnnotations()) {
            log.info(annotation.annotationType().getSimpleName());
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Optional<AnnotatedElement> element = context.getElement();
        for(Annotation annotation: context.getElement().get().getAnnotations()) {
            log.info(annotation.annotationType().getSimpleName());
        }
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {





    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {





    }
}
