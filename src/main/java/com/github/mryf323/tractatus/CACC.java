package com.github.mryf323.tractatus;

import java.lang.annotation.*;

/**
 * Methods being annotated with this annotation, satisfy a correlated active clause coverage test requirement (TR).
 * This annotation indicates the predicate, using element {@code predicate}, the major clause using {@code majorClause}
 * element and the value of the predicate with {@code predicateValue}.
 * Valuations of every clause used in the predicate, are also given using {@code valuations} element.
 * <p> Example below shows a sample usage of this annotation.
 * <pre>
 *     &#64;NearFalsePoint(
 *      predicate = "(a + (bc)) d",
 *      majorClause = 'a',
 *      valuations = &#123;
 *          &#64;Valuation(clause = 'a', valuation = true),
 *          &#64;Valuation(clause = 'b', valuation = true),
 *          &#64;Valuation(clause = 'c', valuation = false),
 *          &#64;Valuation(clause = 'd', valuation = true)
 *      &#125;,
 *      predicateValue = true
 *     )
 *     &#64;Test
 *     public void test1() {
 *         ...
 *     }
 * </pre>
 * <p><em>Note:</em> If a test method covers correlated active clause coverage TR s,
 * you can annotate that method with {@code @@CACC} multiple times.
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CaccContainer.class)
public @interface CACC {

    /**
     * String representation of a predicate.(i.e "a (b + c)")
     * @return a string consists of '+' and some alphabetical clauses.
     */
    String predicate();

    /**
     * @return major clause of given predicate
     */
    char majorClause();
    /**
     * Valuation of each clause.
     * @return an array which assigns <em>every</em> clause in the predicate a value.
     */
    Valuation[] valuations();

    /**
     * @return the value of the predicate according to the valuations of major clauses.
     */
    boolean predicateValue();
}