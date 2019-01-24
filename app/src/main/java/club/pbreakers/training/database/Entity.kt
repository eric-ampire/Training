package club.pbreakers.training.database

import androidx.room.*

@Entity(tableName = "parent")
data class Parent(
    @PrimaryKey val id: Int,
    val nom: String,
    val postNom: String
)

@Entity(tableName = "enfant")
data class Enfant(
    @PrimaryKey val id: Int,
    val idParent: Int,
    val idEcole: Int,
    val nom: String,
    val postnom: String
)

@Entity(tableName = "ecole")
data class Ecole(
    @PrimaryKey val id: Int,
    val nom: String,
    val adresse: String
)