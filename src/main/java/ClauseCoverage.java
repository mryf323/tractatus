import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ClauseCoverageContainer.class)
public @interface ClauseCoverage {

    String predicate();
    Valuation[] valuations();
}
