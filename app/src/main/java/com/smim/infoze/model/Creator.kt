package com.smim.infoze.model
import com.smim.infoze.R


data class Creator(
    val id: String,
    val name: String,
    val role: String,
    val imageRes: Int,
) {
    companion object {
        fun sampleCreators(): List<Creator> = listOf(
            Creator("1", "Marc Lawrence", "Geopolityka", R.drawable.marc),
            Creator("2", "Alisa May", "Energia jądrowa", R.drawable.alisa),
            Creator("3", "Hanah N.", "Wodór", R.drawable.hanah),
            Creator("4", "Alex P.", "Fotowoltaika", R.drawable.alex),
            Creator("5", "Kalia N.", "Węgiel", R.drawable.kalia),
            // Dodaj resztę
        )
    }
}
