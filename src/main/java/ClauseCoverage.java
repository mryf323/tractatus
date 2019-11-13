import java.lang.annotation.*;

/**
 * Indicates that the {@code @Test} method being annotated, is a test requirement (TR) for clause coverage.
 * <p> Example below implies that the test method is a TR for clause coverage of
 * predicate "a + b" which assigns 'a' to {@code true} and 'b' to {@code false}.
 * <pre>
 *     &#64;ClauseCoverage(
 *      predicate = "a + b",
 *      valuations = &#123;
 *          &#64;Valuation(clause = 'a', valuation = true),
 *          &#64;Valuation(clause = 'b', valuation = false)
 *      &#125;
 *     )
 *     &#64;Test
 *     public void test1() {
 *         ...
 *     }
 * </pre>
 * <p> <em>Note:</em> If a test method covers multiple clause coverage TR s,
 * you can annotate that method with {@code @ClauseCoverage} multiple times. See example below:
 * <pre>
 *     &#64;ClauseCoverage(
 *      predicate = "a + b",
 *      valuations = &#123;
 *          &#64;Valuation(clause = 'a', valuation = true),
 *          &#64;Valuation(clause = 'b', valuation = false)
 *      &#125;
 *     )
 *     &#64;ClauseCoverage(
 *      predicate = "c + d",
 *      valuations = &#123;
 *          &#64;Valuation(clause = 'c', valuation = true),
 *          &#64;Valuation(clause = 'd', valuation = true)
 *      &#125;
 *     )
 *     &#64;Test
 *     public void test2() {
 *         ...
 *     }
 * </pre>
 * In the example above, as test2 assigns 'a' to {@code true} and 'b' to {@code false}
 * and 'c' and 'd' to {@code true} it satisfies multiple clause coverage TR s, so it is annotated
 * with {@code ClauseCoverage} multiple times.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ClauseCoverageContainer.class)
public @interface ClauseCoverage {

    /**
     * String representation of a predicate.(i.e "a (b + c)")
     * @return a string consists of '+' and some alphabetical clauses.
     */
    String predicate();

    /**
     * Valuation of each clause.
     * @return an array which assigns <em>every</em> clause in the predicate a value.
     */
    Valuation[] valuations();
}
