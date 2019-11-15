package com.github.mryf323.tractatus;

import java.lang.annotation.*;

/**
 * This annotation can be used for defining clauses. You can annotate either your SUT method or class or your test
 * method or class with this annotation multiple times to define multiple clauses.
 * <pre>
 *     &#64;ClauseDefinition(clause = 'a', "age &lt; 23")
 *     &#64;ClauseDefinition(clause = 'b', "door.state.equals(Door.State.CLOSED)")
 *     &#64;Test
 *     public void test1() {
 *         ...
 *     }
 * </pre>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ClauseDefinitionContainer.class)
public @interface ClauseDefinition {

    /**
     * Character which represents a clause
     * @return clause to be defined.
     */
    char clause();

    /**
     * A string which describes a clause.
     * @return string defining the clause
     */
    String def();
}
