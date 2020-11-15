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

}
