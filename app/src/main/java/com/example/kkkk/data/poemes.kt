package com.example.kkkk.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.example.kkkk.ui.theme.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File

// Classe avec des propriétés sérialisables
data class PoemeDto(
    val id: Int,
    val title: String,
    val description: String,
    val colorRed: Float,
    val colorGreen: Float,
    val colorBlue: Float,
    val colorAlpha: Float,
)

// Extension function pour convertir entre DateActivity et DateActivityDto
fun Poeme.toDto(): PoemeDto {
    return PoemeDto(
        id = this.id,
        title = this.title,
        description = this.description,
        colorRed = this.color.red,
        colorGreen = this.color.green,
        colorBlue = this.color.blue,
        colorAlpha = this.color.alpha,
    )
}

fun PoemeDto.toPoemeActivity(): Poeme {
    return Poeme(
        id = this.id,
        title = this.title,
        description = this.description,
        color = Color(this.colorRed, this.colorGreen, this.colorBlue, this.colorAlpha)
    )
}
class PoemeRepository(private val context: Context) {
    private val poemeActivity = mutableListOf<Poeme>()
    private val takenPoemIds = mutableListOf<Int>() // Liste des IDs pris
    private val gson: Gson
    private val jsonFile: File
    private val sharedPreferences = context.getSharedPreferences("PoemePrefs", Context.MODE_PRIVATE)


    init {
        // Création d'un Gson personnalisé pour gérer Color
        gson = GsonBuilder().create()
        jsonFile = File(context.filesDir, "poeme.json")
        loadDatesFromJson()
    }

    private fun loadDatesFromJson() {
        if (jsonFile.exists()) {
            try {
                val json = jsonFile.readText()
                val dtoList = gson.fromJson(json, Array<PoemeDto>::class.java)
                poemeActivity.clear()
                poemeActivity.addAll(dtoList.map { it.toPoemeActivity()})
            } catch (e: Exception) {
                e.printStackTrace()
                initializeDates() // Fallback to initialization if JSON loading fails
            }
        } else {
            initializeDates()
        }
    }

    private fun saveDatesToJson() {
        try {
            val dtoList = poemeActivity.map { it.toDto() }
            val json = gson.toJson(dtoList)
            jsonFile.writeText(json)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Initialisation des dates si elles sont vides
    private fun initializeDates() {
    if (getAllPoems().isEmpty()) {
            savePoeme(
                0,
                "Découverte",
                "Je ne te connais même pas encore \nEt tout mon être rayonne \nRien qu’à l’idée d’en savoir toujours plus \nRien qu’à l’idée de t’avoir contre moi",
                PastelBlue
            )
            savePoeme(
                1,
                "Et la mer",
                "J’aime la mer et ses yeux bleus\nJe te découvre chaque jour qui passe \nEt je veux y passer le reste de mes jours \nCe mois paraissait si long \nMais il arrive si vite \nJ’ai hâte toujours plus chaque jour",
                        PastelGreen
            )
            savePoeme(
                2,
                "Mon pti bonus",
                "Tu es le bonus, la petite touche parfaite\n" +
                        "La petite fossette, le petit truc en plus\n" +
                        "Il ne manquait absolument rien avant toi\n" +
                        "Mais tu donne un sens à tout ça ",
            PastelOrange
            )
            savePoeme(
                3,
                "Et si enfin",
                "Je veux te partager chaque pas \n" +
                        "Chaque moment si loin de moi\n" +
                        "Quel bonheur de bientôt te rencontrer \n" +
                        "J’en ai si longtemps rêvé \n" +
                        "Faire la paix avec le passé\n" +
                        "Changer, Réapprendre à m’aimer\n" +
                        "Et puis te rencontrer \n" +
                        "Ce que j’ai toujours désiré ",
            PastelRed
            )
            savePoeme(
                4,
                "Un beau jardin",
                "Une partenaire de vie \n" +
                        "Pour mettre les voiles\n" +
                        "Sous le ciel gris\n" +
                        "Sous les étoiles \n" +
                        "Un petit coin de paradis \n" +
                        "La où chaque jour fleurit ",
                        PastelYellow
            )
            savePoeme(
                5,
                "Thinking of you",
                "I’m starting to daydream about kissing you\n" +
                        "My sweat redhead \n" +
                        "Looking like a cute little fire \n" +
                        "I don’t only want to kiss you\n" +
                        "I wanna hold you\n" +
                        "I wanna touch you\n" +
                        "I wanna enjoy every step with you\n" +
                        "I want to show you all my favourite places \n" +
                        "I want to know everything, every thought \n" +
                        "I wanna to hear you moan",
                PastelBlue
            )
            savePoeme(
                6,
                "Aquarelle",
                "Je te veux, dans mes bras, dans ma vie, dans mon cœur\n" +
                        "Pour y passer toutes nos heures \n" +
                        "Et y partager toutes nos peurs \n" +
                        "Peindre nos mondes en couleurs",
                PastelPurple
            )
            savePoeme(
                7,
                "Juste la vie",
                "Comme si le ciel avait changer ses couleur\n" +
                        "Comme si le temps ne suivait plus son heure\n" +
                        "Comme si la vie c’était parée d’un voile de poésie\n" +
                        "Comme si toutes les couleurs prenaient enfin vie",
                PastelRed
            )
            savePoeme(
                8,
                "I'm glad",
                " I’ve never made such a beautiful dream\n" +
                        "That since you came into my life\n" +
                        "I did found my purpose on earth\n" +
                        "Loving you will be my gift",
                PastelYellow
            )
        }
    }

    // Ajouter une date à la liste
    fun savePoeme(id: Int, title: String, description: String, color: Color) {
        poemeActivity.add(Poeme(id, title, description, color))
        saveDatesToJson() // Sauvegarde après modification
    }

    // Récupérer toutes les dates
    fun getAllPoems(): List<Poeme> = poemeActivity

    // Récupérer une date par son ID
    fun getPoemeById(id: Int): Poeme {
        return poemeActivity.firstOrNull { it.id == id }
            ?: throw IllegalArgumentException("Date not found with id: $id")
    }

    fun getRandomAvailablePoem(): Poeme? {
        val allPoems = getAllPoems()
        val takenPoemIds = getTakenPoemIds()

        val availablePoems = allPoems.filter { it.id !in takenPoemIds }
        return availablePoems.randomOrNull()
    }

    fun markPoemeAsTaken(id: Int, date: String) {
        val takenPoems = getTakenPoemIds().toMutableSet()
        takenPoems.add(id)

        sharedPreferences.edit()
            .putStringSet("takenPoemIds", takenPoems.map { it.toString() }.toSet())
            .putString("lastDrawDate", date) // ✅ Enregistrement de la date du dernier tirage
            .apply()
    }

    fun getTakenPoemIds(): List<Int> {
        return sharedPreferences.getStringSet("takenPoemIds", emptySet())
            ?.mapNotNull { it.toIntOrNull() }
            ?: emptyList()
    }

    fun getTakenPoems(): List<Poeme> {
        val takenIds = getTakenPoemIds()
        return getAllPoems().filter { it.id in takenIds }
    }

    fun getLastDrawDate(): String? {
        return sharedPreferences.getString("lastDrawDate", null)
    }
}

data class Poeme(val id: Int, val title: String, val description: String, val color: Color)