package com.github.mryf323.tractatus.experimental.extensions;

import com.github.mryf323.tractatus.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ClauseDefinitionContainer(value = {
        @ClauseDefinition(clause = '1', def = "last.isPresent()"),
        @ClauseDefinition(clause = '2', def = "age>3"),
        @ClauseDefinition(clause = '3', def = "daysFromLastVisit>364"),
        @ClauseDefinition(clause = '4', def = "age<=3"),
        @ClauseDefinition(clause = '5', def = "daysFromLastVisit>182"),
        @ClauseDefinition(clause = '6', def = "notVisited.size()>0")
})
@ExtendWith(ReportingExtension.class)
class ReportingExtensionSampleTest  {

    @BeforeEach
    public void setup() {
    }

    @UniqueTruePoint(
            predicate = "2",
            cnf = "(c2 & c3) | (~c2 & c5)",
            implicant = "c2 & c3",
            valuations = {
                    @Valuation(clause = '2', valuation = true),
                    @Valuation(clause = '3', valuation = true),
                    @Valuation(clause = '4', valuation = false),
                    @Valuation(clause = '5', valuation = true)
            }
    )
    @CACC(
            predicate = "2",
            majorClause = '2',
            valuations = {
                    /*LIKE ABOVE*/
            },
            predicateValue = true
    )
    @Test
    public void visitOwnerPetsTP1() {
    }

    @NearFalsePoint(
            predicate = "2",
            cnf = "(c2 & c3) | (~c2 & c5)",
            implicant = "c2 & c3",
            clause = '3',
            valuations = {
                    @Valuation(clause = '2', valuation = true),
                    @Valuation(clause = '3', valuation = false),
                    @Valuation(clause = '4', valuation = false),
                    @Valuation(clause = '5', valuation = false)
            }
    )
    @CACC(
            predicate = "2",
            majorClause = '3',
            valuations = {
                    /*LIKE ABOVE*/
            },
            predicateValue = false
    )
    @Test
    public void visitOwnerPetsTP2() {
    }

}
