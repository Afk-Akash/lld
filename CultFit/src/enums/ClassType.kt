package enums

enum class ClassType {
    GYM,
    YOGA,
    ZOOMBA;

    companion object {
        fun getClassType(id: Int): ClassType {
            if(id == 1) return ClassType.GYM
            if(id == 2) return ClassType.YOGA
            if(id == 3) return ClassType.ZOOMBA
            throw IllegalArgumentException("invalid argument")
        }
    }
}