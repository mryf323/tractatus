import java.lang.annotation.*;

/**
 * Methods being annotated with this annotation, satisfy a near false point test requirement (TR).
 * This annotation indicates the original predicate, using element {@code predicate}, CNF form of that predicate
 * using {@code cnf} element, the implicant and the clause being inspected using {@code implicant} and {@code clause}
 * elements respectively. Valuations of every clause used in the predicate, are also given using {@code valuations}
 * element.
 * <p> Example below shows a sample usage of this annotation.
 * <pre>
 *     &#64;NearFalsePoint(
 *      predicate = "ab + cd",
 *      cnf = "ab + cd",
 *      implicant = "ab",
 *      clause = 'a',
 *      valuations = &#123;
 *          &#64;Valuation(clause = 'a', valuation = false),
 *          &#64;Valuation(clause = 'b', valuation = true),
 *          &#64;Valuation(clause = 'c', valuation = false),
 *          &#64;Valuation(clause = 'd', valuation = false)
 *      &#125;
 *     )
 *     &#64;Test
 *     public void test1() {
 *         ...
 *     }
 * </pre>
 * In the above example the predicate is already in CNF form, so {@code cnf} element and {@code predicate}
 * values are the same. If the predicate was something like "a (b + c)", the value of element {@code predicate} would be
 * "a (b + c)", were as the value of {@code cnf} element would be "ab + ac".
 <p><em>Note:</em> If a test method covers multiple near false point TR s,
 * you can annotate that method with {@code @NearFalsePoint} multiple times.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NearFalsePointContainer.class)
public @interface NearFalsePoint {

    /**
     * String representation of a predicate.(i.e "a (b + c)")
     * @return a string consists of '+' and some alphabetical clauses.
     */
    String predicate();

    /**
     * @return CNF form of the predicate
     */
    String cnf();

    /**
     * @return the implicant being inspected
     */
    String implicant();

    /**
     * @return the clause being inspected
     */
    char clause();

    /**
     * Valuation of each clause.
     * @return an array which assigns <em>every</em> clause in the predicate a value.
     */
    Valuation[] valuations();
}
