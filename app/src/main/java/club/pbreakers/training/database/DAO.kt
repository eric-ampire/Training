package club.pbreakers.training.database

import androidx.room.*

@Dao
interface ParentDAO {
    @Insert fun add(vararg data: Parent)
    @Delete fun delete(vararg data: Parent)
    @Update fun update(vararg data: Parent)

    @Query("SELECT * FROM parent WHERE nom = :name")
    fun getElementByName(name: String) : List<Parent>

    @Query("SELECT * FROM parent WHERE nom = :id")
    fun getElementById(id: String) : Parent

    @Query("SELECT * FROM parent")
    fun getAll() : List<Parent>
}


@Dao
interface EcoleDAO {
    @Insert fun add(vararg data: Ecole)
    @Delete fun delete(vararg data: Ecole)
    @Update fun update(vararg data: Ecole)

    @Query("SELECT * FROM ecole WHERE nom = :name")
    fun getElementByName(name: String) : List<Ecole>

    @Query("SELECT * FROM ecole WHERE nom = :id")
    fun getElementById(id: String) : Ecole

    @Query("SELECT * FROM ecole")
    fun getAll() : List<Ecole>
}

@Dao
interface EnfantDAO {
    @Insert fun add(vararg data: Enfant)
    @Delete fun delete(vararg data: Enfant)
    @Update fun update(vararg data: Enfant)

    @Query("SELECT * FROM enfant WHERE nom = :name")
    fun getElementByName(name: String) : List<Enfant>

    @Query("SELECT * FROM enfant WHERE nom = :id")
    fun getElementById(id: String) : Enfant

    @Query("SELECT * FROM enfant")
    fun getAll() : List<Enfant>
}