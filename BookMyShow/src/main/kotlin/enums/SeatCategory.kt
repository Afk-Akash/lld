package org.bookMyShow.enums

import java.math.BigDecimal

enum class SeatCategory(val amount: BigDecimal) {
    REGULAR(BigDecimal(100)),
    PREMIUM(BigDecimal(200)),
    VIP(BigDecimal(500))
}
