package club.pbreakers.training.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Parent::class, Enfant::class, Ecole::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun getParentDAO() : ParentDAO
    abstract fun getEcoleDAO() : EcoleDAO
    abstract fun getEnfant() : EnfantDAO
}