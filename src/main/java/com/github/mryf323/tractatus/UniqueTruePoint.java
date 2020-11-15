package com.github.mryf323.tractatus;

import java.lang.annotation.*;

/**
 * Methods being annotated with this annotation, satisfy a unique true point test requirement (TR).
 * This annotation indicates the original predicate, using element {@code predicate}, DNF form of that predicate
 * using {@code dnf} element, the implicant being inspected using {@code implicant} element.
 * Valuations of every clause used in the predicate, are also given using {@code valuations} element.
 * <p> Example below shows a sample usage of this annotation.
 * <pre>
 *     &#64;UniqueTruePoint(
 *      predicate = "ab + cd",
 *      dnf = "ab + cd",
 *      implicant = "ab",
 *      valuations = &#123;
 *          &#64;Valuation(clause = 'a', valuation = true),
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
 * In the above example the predicate is already in DNF form, so {@code dnf} element and {@code predicate}
 * values are the same. If the predicate was something like "a (b + c)", the value of element {@code predicate} would be
 * "a (b + c)", were as the value of {@code dnf} element wouزld be "ab + ac".
  <p><em>Note:</em> If a test method covers multiple unique true point TR s,
 * you can annotate that method with {@link UniqueTruePoint} multiple times.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(UniqueTruePointContainer.class)
public @interface UniqueTruePoint {

    /**
     * String representation of a predicate.(i.e "a (b + c)")
     * @return a string consists of '+' and some alphabetical clauses.
     */
    String predicate();

    /**
     * @return DNF form of the predicate
     */
    String dnf();

    /**
     * @return the implicant being inspected
     */
    String implicant();

    /**
     * Valuation of each clause.
     * @return an array which assigns <em>every</em> clause in the predicate a value.
     */
    Valuation[] valuations();

}
