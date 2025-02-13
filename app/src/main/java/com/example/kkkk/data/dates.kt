package com.example.kkkk.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.example.kkkk.ui.theme.*
import com.google.gson.*
import java.io.File
import java.lang.reflect.Type
import java.util.Date

// Classe avec des propriétés sérialisables
data class DateActivityDto(
    val id: Int,
    val title: String,
    val description: String,
    val colorRed: Float,
    val colorGreen: Float,
    val colorBlue: Float,
    val colorAlpha: Float,
    val dateDudate: Long?
)

// Extension function pour convertir entre DateActivity et DateActivityDto
fun DateActivity.toDto(): DateActivityDto {
    return DateActivityDto(
        id = this.id,
        title = this.title,
        description = this.description,
        colorRed = this.color.red,
        colorGreen = this.color.green,
        colorBlue = this.color.blue,
        colorAlpha = this.color.alpha,
        dateDudate = this.dateDudate?.time
    )
}

fun DateActivityDto.toDateActivity(): DateActivity {
    return DateActivity(
        id = this.id,
        title = this.title,
        description = this.description,
        color = Color(this.colorRed, this.colorGreen, this.colorBlue, this.colorAlpha),
        dateDudate = this.dateDudate?.let { Date(it) }
    )
}

class DateRepository(private val context: Context) {
    private val dateActivities = mutableListOf<DateActivity>()
    private val gson: Gson
    private val jsonFile: File

    init {
        // Création d'un Gson personnalisé pour gérer Color
        gson = GsonBuilder().create()
        jsonFile = File(context.filesDir, "dates.json")
        loadDatesFromJson()
    }

    private fun loadDatesFromJson() {
        if (jsonFile.exists()) {
            try {
                val json = jsonFile.readText()
                val dtoList = gson.fromJson(json, Array<DateActivityDto>::class.java)
                dateActivities.clear()
                dateActivities.addAll(dtoList.map { it.toDateActivity() })
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
            val dtoList = dateActivities.map { it.toDto() }
            val json = gson.toJson(dtoList)
            jsonFile.writeText(json)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Initialisation des dates si elles sont vides
    private fun initializeDates() {
        if (dateActivities.isEmpty()) {
            // Même code d'initialisation que tu avais déjà
            saveDate(
                1,
                "Un date peinture 🎨",
                "- Une petite toile\n- De beaux pinceaux\n- Des numéros ?\n- Des couleurs en rab\n- Une belle palette\n- Attention à ne pas s'en mettre partout"
                , PastelRed,null
            )
            saveDate(
                2,
                "Un date pour danser 💃",
                "- Toi\n- Moi\n- Nos musiques pref\n- Un pti plat qui mijote\n- Un soirée parfaite finalement"
                , PastelOrange,null
            )
            saveDate(
                3,
                "Un date pêche à pied 🦀",
                "- Tes bottes\n- Ta règle pour les tailles ( très important )\n- Un grand soleil\n- Un beau bonnet\n- Une grande marée (indipensable )\n- Un sac plein de coquilles (faudra repérer leur petits yeux)"
                , PastelYellow,null
            )
            saveDate(
                4,
                "Un date sieste 😴",
                "- Un grand lit\n- Un coucher de soleil\n- Des montagnes de doudou\n- Des câlins"
                , PastelGreen,null
            )
            saveDate(
                5,
                "Un date pour regarder les étoiles \uD83C\uDF1F",
                "- Un grand plaid\n- Le meilleur spot\n- Une belle nuit noire\n- Un bord de mer\n- Des heures devant nous"
                , PastelBlue,null
            )
            saveDate(
                6,
                "Un date balade guidée 🗺️",
                "- J'espère que le guide aura la liste ^^"
                , PastelPurple,null
            )
            saveDate(
                7,
                "Un date / des milliers pour regarder Arcane 🔥",
                "- Pour chanter, rire, pleurer\n- Pour questionner sa vie encore et encore\n- Pour profiter de cette oeuvre d'art une fois de plus"
                , PastelPink,null
            )
            saveDate(
                8,
                "Un date jeux vidéo 🎮",
                "- Reste à trouver lequel ! Stardew ?"
                , PastelRed,null
            )
            saveDate(
                9,
                "Un date bracelet 📿",
                "- Du joli fil\n- Beaucoup de couleur\n- Un soupson de patience\n- Pour un superbe object à emporter partout"
                , PastelOrange,null
            )
            saveDate(
                10,
                "Un date fabrication de tarte au citron 🍋",
                "- Recette secrète avec autant d'amour que de citron\n- Un tout petit peu de sucre\n- Une belle pate fait maison\n- Un lemon curd à tomber par terre\n- Y'a pas de recette je sais pas les suivre"
                , PastelYellow,null
            )
            saveDate(
                11,
                "Un date jeux de société 🎲",
                "- Autant que tu en as dans ta musette et au moins autant qu'il y en a chez moi\n- Une attention toute particulière pour un sevenWonders dans les règles de l'art"
                , PastelGreen,null
            )
            saveDate(
                12,
                "Un date puzzle 🧩",
                "- Des ptits bouts, des ptits bouts encore des ptits bouts\n- En concour ou en coopération\n- Attention j'ai des yeux de linx "
                , PastelBlue,null
            )
            saveDate(
                13,
                "Un date spectacle d'humour 🎤",
                "- Tellement de nom faut qu'on aille direct à Montreux"
                , PastelPurple,null
            )
            saveDate(
                14,
                "Un date déco (IKEA ?) 🛠️",
                "- Des arc en ciel\n- Des petits vases\n- Des jolis miroir\n- Plein de plantes partout\n- De la belle vaisselle"
                , PastelPink,null
            )
            saveDate(
                15,
                "Un date règlement de compte blind test Disney 🏰",
                "- Plutôt Mulan\n- La reine des neiges\n- Peter pan\n- La petite sirène\n- Rebelle\n- Ou encore Le livre de le jungle"
                , PastelRed,null
            )
            saveDate(
                16,
                "Un date concert Girl in Red 🎸",
                "- Le concert c'est le jeudi 3 juillet ! ;)"
                , PastelOrange,null
            )
            saveDate(
                17,
                "Un date mouillettes 🥚",
                "- Vivre de beurre et d'oeuf frai\n- Bon et de baguettes"
                , PastelYellow,null
            )
        }
    }

    // Ajouter une date à la liste
    fun saveDate(id: Int, title: String, description: String, color: Color, dateDudate: Date?) {
        dateActivities.add(DateActivity(id, title, description, color, dateDudate))
        saveDatesToJson() // Sauvegarde après modification
    }

    fun saveDateDate(id: Int, title: String, description: String, color: Color, dateDudate: Date?) {
        val existingActivity = dateActivities.firstOrNull { it.id == id }
        System.out.println("appel de save date "+existingActivity)
        if (existingActivity != null) {
            val index = dateActivities.indexOf(existingActivity)
            val updatedActivity = existingActivity.copy(dateDudate = dateDudate)
            dateActivities[index] = updatedActivity
            System.out.println("mise à jour date " + updatedActivity)
            saveDatesToJson() // Sauvegarde après modification
        }
    }

    // Récupérer toutes les dates
    fun getAllDates(): List<DateActivity> = dateActivities

    // Récupérer une date par son ID
    fun getDateById(id: Int): DateActivity {
        return dateActivities.firstOrNull { it.id == id }
            ?: throw IllegalArgumentException("Date not found with id: $id")
    }
}

// La classe DateActivity reste inchangée
data class DateActivity(
    val id: Int,
    val title: String,
    val description: String,
    val color: Color,
    val dateDudate: Date?
)