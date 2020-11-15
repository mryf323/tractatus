package com.github.mryf323.tractatus.experimental.extensions;

import com.github.mryf323.tractatus.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(ReportingExtension.class)
@ClauseDefinition(clause = 'a', def = "x == 0")
@ClauseDefinition(clause = 'b', def = "y == 0")
@ClauseDefinition(clause = 'c', def = "z == 0")
@ClauseDefinition(clause = 'd', def = "w == 0")
class ReportingExtensionSampleTest  {

    public boolean predicate(int x, int y, int z, int w) {
        if(x == 0 && (y == 0 || z == 0 && w == 0)) {
            return true;
        } else {
            return false;
        }
    }

    @UniqueTruePoint(
            predicate = "ab",
            dnf = "ab + acd",
            implicant = "a(b + cd)",
            valuations = {
                    @Valuation(clause = 'a', valuation = true),
                    @Valuation(clause = 'b', valuation = true),
                    @Valuation(clause = 'c', valuation = false),
                    @Valuation(clause = 'd', valuation = true)
            }
    )
    @Test
    public void testSampleOne() {
        assertTrue(predicate(0,0,1,0));
    }

    @NearFalsePoint(
            predicate = "2",
            dnf = "(c2 & c3) | (~c2 & c5)",
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
