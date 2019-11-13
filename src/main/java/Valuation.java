import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a valuation for a clause.
 * Example below shows how we can show {@code a = true} using this annotation.
 * <code>
 * &#64;Valuation(clause = 'a', valuation = true)
 * </code>
 * @since 1.0
 * @author mryf
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Valuation {
    /**
     * The clause to be set .
     * @return the character of that clause
     */
    char clause();

    /**
     * The value assigned to the clause.
     * @return value being assigned to the clause
     */
    boolean valuation();
}
