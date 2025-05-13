package com.smim.infoze.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.smim.infoze.R
import java.time.LocalDate

enum class MaterialType {
    ARTICLE,
    IMAGE,
    VIDEO,
    AUDIO
}

data class Material(
    val id: String,
    val creatorId: String,
    val title: String,
    val description: String,
    val type: MaterialType,
    val thumbnailRes: Int,
    val publishDate: LocalDate
) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun sampleMaterials(): List<Material> = listOf(
            Material(
                id = "m1",
                creatorId = "1",
                title = "Jak działa energia słoneczna",
                description = "Wprowadzenie do zasad działania ogniw fotowoltaicznych.",
                type = MaterialType.ARTICLE,
                thumbnailRes = R.drawable.placeholder,
                publishDate = LocalDate.of(2025, 5, 13)
            ),
            Material(
                id = "m2",
                creatorId = "1",
                title = "Podcast o geopolityce",
                description = "Rozmowa z ekspertem o wpływie energii na politykę międzynarodową.",
                type = MaterialType.AUDIO,
                thumbnailRes = R.drawable.placeholder,
                publishDate = LocalDate.of(2025, 5, 11)
            ),
            Material(
                id = "m3",
                creatorId = "2",
                title = "Wideo o reaktorach",
                description = "Nowoczesne reaktory jądrowe w Europie.",
                type = MaterialType.VIDEO,
                thumbnailRes = R.drawable.placeholder,
                publishDate = LocalDate.of(2025, 4, 13)
            )
        )
    }
}
