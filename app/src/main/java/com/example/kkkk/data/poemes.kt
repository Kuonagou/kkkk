package com.example.kkkk.data

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.graphics.Color
import com.example.kkkk.ui.theme.*

class PoemRepository(private val context: Context) {
    private val poems = mutableListOf<Poeme>()
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("PoemPrefs", Context.MODE_PRIVATE)

    init {
        initializePoems()
    }
    private fun initializePoems() {
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

fun getAllPoems(): List<Poeme> = poems

fun savePoeme(id: Int, title: String, content: String, color: Color) {
    poems.add(Poeme(id, title, content, color))
}

fun getRandomPoem(): Poeme? {
    if (poems.isEmpty()) return null

    val availablePoems = poems.filter { it.id !in getDrawnPoems() }
    if (availablePoems.isEmpty()) return null // Tous les poèmes ont été tirés

    val randomPoem = availablePoems.random()
    addPoemToDrawnList(randomPoem.id)
    return randomPoem
}
    fun getThisPoeme(id:Int): Poeme {
        return poems[id];
    }

private fun addPoemToDrawnList(poemId: Int) {
    val drawnList = getDrawnPoems().toMutableSet()
    drawnList.add(poemId)
    sharedPreferences.edit().putStringSet("drawnPoems", drawnList.map { it.toString() }.toSet()).apply()
}

fun getDrawnPoems(): List<Int> {
    return sharedPreferences.getStringSet("drawnPoems", emptySet())
        ?.mapNotNull { it.toIntOrNull() }
        ?: emptyList()
}
}

data class Poeme(val id: Int, val title: String, val content: String, val color: Color)