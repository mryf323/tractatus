import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NearFalsePoint {

    String predicate();
    String cnf();
    String implicant();
    char clause();
    Valuation[] valuations();
}
