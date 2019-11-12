import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(UniqueTruePointContainer.class)
public @interface UniqueTruePoint {

    String predicate();
    String cnf();
    String implicant();
    Valuation[] valuations();

}
