import java.lang.annotation.Annotation;

interface AnnotationProcessor {

    void process(MethodInstance methodInstance);

    boolean canProcess(Annotation annotation);

    void commit();
}
