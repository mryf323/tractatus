import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NearFalsePointContainer.class)
public @interface NearFalsePoint {

    String predicate();
    String cnf();
    String implicant();
    char clause();
    Valuation[] valuations();
}
