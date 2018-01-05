package com.sazboom.turboroller.unit

import org.junit.Test
import com.sazboom.turboroller.models.*;
import org.junit.Assert.assertEquals

/**
 * Created by aaronworsham on 12/27/17.
 */

class BonusRollerTest {
    @Test
    fun testBonusRoller() {
        var br: BonusRoller
        for (x in 0..9) {
            br = BonusRoller(x)
            assertEquals(br.get().toLong(), x.toLong())
        }
    }
}
