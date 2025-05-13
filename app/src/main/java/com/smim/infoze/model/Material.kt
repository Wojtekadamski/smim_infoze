package com.smim.infoze.model

import com.smim.infoze.R

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
    val thumbnailRes: Int
) {
    companion object {
        fun sampleMaterials(): List<Material> = listOf(
            Material(
                id = "m1",
                creatorId = "1",
                title = "Jak działa energia słoneczna",
                description = "Wprowadzenie do zasad działania ogniw fotowoltaicznych.",
                type = MaterialType.ARTICLE,
                thumbnailRes = R.drawable.placeholder
            ),
            Material(
                id = "m2",
                creatorId = "1",
                title = "Podcast o geopolityce",
                description = "Rozmowa z ekspertem o wpływie energii na politykę międzynarodową.",
                type = MaterialType.AUDIO,
                thumbnailRes = R.drawable.placeholder
            ),
            Material(
                id = "m3",
                creatorId = "2",
                title = "Wideo o reaktorach",
                description = "Nowoczesne reaktory jądrowe w Europie.",
                type = MaterialType.VIDEO,
                thumbnailRes = R.drawable.placeholder
            )
        )
    }
}
