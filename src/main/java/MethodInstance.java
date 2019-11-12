import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class MethodInstance {

    private Method method;
    private Object instance;
    private Annotation annotation;

    public MethodInstance(Method method, Object instance, Annotation annotation) {
        this.method = method;
        this.instance = instance;
        this.annotation = annotation;
    }

    public Method getMethod() {
        return method;
    }

    public Object getInstance() {
        return instance;
    }

    public Annotation getAnnotation() {
        return annotation;
    }
}
