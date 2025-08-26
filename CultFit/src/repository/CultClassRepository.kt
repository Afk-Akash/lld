package repository

import data.StaticData
import entity.ScheduledCultClass
import entity.User

class CultClassRepository {
    private var scheduledCultClasses : MutableList<ScheduledCultClass> = StaticData.scheduledCultClasses

    fun getAllClasses(): List<ScheduledCultClass> {
        return scheduledCultClasses
    }

    fun fetchById(id: Long): ScheduledCultClass {
        scheduledCultClasses.forEach { scheduledCultClass ->
            if(scheduledCultClass.scheduledCultClassId == id) return scheduledCultClass
        }
        throw IllegalArgumentException("No cult class found with id - $id")
    }

    fun putNewCultClass(scheduledCultClass:ScheduledCultClass) {
        scheduledCultClasses.add(scheduledCultClass)
    }

    fun getScheduledCultClass(id: Long): ScheduledCultClass? {
         scheduledCultClasses.forEach { scheduledClass ->
            if(id == scheduledClass.scheduledCultClassId) return scheduledClass
        }
        return null
    }

    fun subscribeClass(user: User, id: Long): Boolean{
        scheduledCultClasses.forEach { scheduledClass ->
            if(id == scheduledClass.scheduledCultClassId) {
                if(scheduledClass.isFull()){
                    scheduledClass.waitlistUser.add(user)
                    return false
                }else{
                    scheduledClass.confirmedUser.add(user)
                    scheduledClass.confirmedUsersCount += 1
                    return true
                }
            }
        }
        return false
    }
}