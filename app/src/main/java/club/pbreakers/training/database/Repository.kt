package club.pbreakers.training.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.concurrent.thread

class ParentRepo(val ctx: Context, val db: RoomDatabase) {
    fun addParent(parent: Parent) {
        Room.databaseBuilder(ctx, AppDatabase::class.java, "training-db").build()
        thread {

        }
    }
}