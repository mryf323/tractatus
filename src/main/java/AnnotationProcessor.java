import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

interface AnnotationProcessor {

    void process(MethodInstance methodInstance);

    boolean canProcess(Annotation annotation);

    void commit();
}
