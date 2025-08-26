package enums

enum class MembershipType {
    PLATINUM,
    GOLD,
    SILVER;

    companion object {
        fun getMembership(type: String): MembershipType? {
            if (type == "platinum") return MembershipType.PLATINUM
            if (type == "gold") return MembershipType.GOLD
            if (type == "silver") return MembershipType.SILVER
            return null
        }
    }
}