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
    val content: String,
    val type: MaterialType,
    val thumbnailRes: Int,
    val publishDate: LocalDate,
    val imageResList: List<Int> = emptyList(),
    val videoResId: Int? = null,
    val audioResId: Int? = null

) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun sampleMaterials(): List<Material> = listOf(
            Material(
                id = "m1",
                creatorId = "1",
                title = "Jak działa energia słoneczna",
                description = "Wprowadzenie do zasad działania ogniw fotowoltaicznych.",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sit amet aliquet massa. Aliquam nec orci sit amet dolor porttitor faucibus sit amet ut tortor. Donec magna leo, molestie sit amet arcu vel, gravida sagittis ante. Integer consequat lobortis arcu vitae fermentum. Praesent felis nibh, suscipit a nibh sit amet, tempus aliquet quam. Sed mi purus, condimentum nec lorem ac, placerat pulvinar lorem. Suspendisse tincidunt cursus suscipit. Phasellus arcu sem, cursus quis hendrerit at, lobortis at nunc. Curabitur iaculis iaculis tempus. Proin tempus nibh vitae turpis vestibulum mollis. Mauris hendrerit ut mi nec sagittis. Ut eu mi nec lectus ultricies tempus ac at felis.\n" +
                        "\n" +
                        "Ut eu varius lectus. Morbi enim lorem, mattis non ex vitae, condimentum lobortis quam. Duis eget lorem tempor, imperdiet mauris id, rutrum ante. Nulla enim elit, elementum sit amet lorem at, aliquam dapibus mauris. Integer sollicitudin luctus rutrum. Ut finibus massa ut accumsan molestie. Cras sit amet euismod ipsum.\n" +
                        "\n" +
                        "Nunc eleifend condimentum odio quis tempor. Sed porttitor ante at justo tristique tincidunt. Aenean pulvinar lacus et nulla scelerisque, eget malesuada mi placerat. Nulla interdum justo nec cursus lobortis. Proin lorem enim, tempus vitae eros ut, aliquet ultricies est. Duis consectetur nunc mattis metus vestibulum, ac posuere erat vulputate. Nunc venenatis mollis lorem nec hendrerit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent varius sapien blandit lorem semper iaculis. Morbi fringilla enim non pharetra rhoncus. Proin odio augue, pretium non dolor ullamcorper, mollis facilisis felis. In et sodales nunc.",
                type = MaterialType.ARTICLE,
                thumbnailRes = R.drawable.solar1,
                publishDate = LocalDate.of(2025, 5, 13),
                imageResList = listOf(R.drawable.solar1, R.drawable.solar2, R.drawable.solar3),
            ),
            Material(
                id = "m2",
                creatorId = "1",
                title = "Podcast o geopolityce",
                description = "Rozmowa z ekspertem o wpływie energii na politykę międzynarodową.",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sit amet aliquet massa. Aliquam nec orci sit amet dolor porttitor faucibus sit amet ut tortor. Donec magna leo, molestie sit amet arcu vel, gravida sagittis ante. Integer consequat lobortis arcu vitae fermentum. Praesent felis nibh, suscipit a nibh sit amet, tempus aliquet quam. Sed mi purus, condimentum nec lorem ac, placerat pulvinar lorem. Suspendisse tincidunt cursus suscipit. Phasellus arcu sem, cursus quis hendrerit at, lobortis at nunc. Curabitur iaculis iaculis tempus. Proin tempus nibh vitae turpis vestibulum mollis. Mauris hendrerit ut mi nec sagittis. Ut eu mi nec lectus ultricies tempus ac at felis.\n" +
                        "\n" +
                        "Ut eu varius lectus. Morbi enim lorem, mattis non ex vitae, condimentum lobortis quam. Duis eget lorem tempor, imperdiet mauris id, rutrum ante. Nulla enim elit, elementum sit amet lorem at, aliquam dapibus mauris. Integer sollicitudin luctus rutrum. Ut finibus massa ut accumsan molestie. Cras sit amet euismod ipsum.\n" +
                        "\n" +
                        "Nunc eleifend condimentum odio quis tempor. Sed porttitor ante at justo tristique tincidunt. Aenean pulvinar lacus et nulla scelerisque, eget malesuada mi placerat. Nulla interdum justo nec cursus lobortis. Proin lorem enim, tempus vitae eros ut, aliquet ultricies est. Duis consectetur nunc mattis metus vestibulum, ac posuere erat vulputate. Nunc venenatis mollis lorem nec hendrerit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent varius sapien blandit lorem semper iaculis. Morbi fringilla enim non pharetra rhoncus. Proin odio augue, pretium non dolor ullamcorper, mollis facilisis felis. In et sodales nunc.",
                type = MaterialType.AUDIO,
                thumbnailRes = R.drawable.solar2,
                publishDate = LocalDate.of(2025, 5, 11),
                audioResId = R.raw.podcast2

            ),
            Material(
                id = "m3",
                creatorId = "2",
                title = "Wideo o reaktorach",
                description = "Nowoczesne reaktory jądrowe w Europie.",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sit amet aliquet massa. Aliquam nec orci sit amet dolor porttitor faucibus sit amet ut tortor. Donec magna leo, molestie sit amet arcu vel, gravida sagittis ante. Integer consequat lobortis arcu vitae fermentum. Praesent felis nibh, suscipit a nibh sit amet, tempus aliquet quam. Sed mi purus, condimentum nec lorem ac, placerat pulvinar lorem. Suspendisse tincidunt cursus suscipit. Phasellus arcu sem, cursus quis hendrerit at, lobortis at nunc. Curabitur iaculis iaculis tempus. Proin tempus nibh vitae turpis vestibulum mollis. Mauris hendrerit ut mi nec sagittis. Ut eu mi nec lectus ultricies tempus ac at felis.\n" +
                        "\n" +
                        "Ut eu varius lectus. Morbi enim lorem, mattis non ex vitae, condimentum lobortis quam. Duis eget lorem tempor, imperdiet mauris id, rutrum ante. Nulla enim elit, elementum sit amet lorem at, aliquam dapibus mauris. Integer sollicitudin luctus rutrum. Ut finibus massa ut accumsan molestie. Cras sit amet euismod ipsum.\n" +
                        "\n" +
                        "Nunc eleifend condimentum odio quis tempor. Sed porttitor ante at justo tristique tincidunt. Aenean pulvinar lacus et nulla scelerisque, eget malesuada mi placerat. Nulla interdum justo nec cursus lobortis. Proin lorem enim, tempus vitae eros ut, aliquet ultricies est. Duis consectetur nunc mattis metus vestibulum, ac posuere erat vulputate. Nunc venenatis mollis lorem nec hendrerit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent varius sapien blandit lorem semper iaculis. Morbi fringilla enim non pharetra rhoncus. Proin odio augue, pretium non dolor ullamcorper, mollis facilisis felis. In et sodales nunc.",
                type = MaterialType.VIDEO,
                thumbnailRes = R.drawable.placeholder,
                publishDate = LocalDate.of(2025, 4, 13),
                videoResId = R.raw.reactor1
            ),
            Material(
                id = "m4",
                creatorId = "2",
                title = "Podcast o OZE",
                description = "Rozmowa z ekspertem o Energii odnawialnej.",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sit amet aliquet massa. Aliquam nec orci sit amet dolor porttitor faucibus sit amet ut tortor. Donec magna leo, molestie sit amet arcu vel, gravida sagittis ante. Integer consequat lobortis arcu vitae fermentum. Praesent felis nibh, suscipit a nibh sit amet, tempus aliquet quam. Sed mi purus, condimentum nec lorem ac, placerat pulvinar lorem. Suspendisse tincidunt cursus suscipit. Phasellus arcu sem, cursus quis hendrerit at, lobortis at nunc. Curabitur iaculis iaculis tempus. Proin tempus nibh vitae turpis vestibulum mollis. Mauris hendrerit ut mi nec sagittis. Ut eu mi nec lectus ultricies tempus ac at felis.\n" +
                        "\n" +
                        "Ut eu varius lectus. Morbi enim lorem, mattis non ex vitae, condimentum lobortis quam. Duis eget lorem tempor, imperdiet mauris id, rutrum ante. Nulla enim elit, elementum sit amet lorem at, aliquam dapibus mauris. Integer sollicitudin luctus rutrum. Ut finibus massa ut accumsan molestie. Cras sit amet euismod ipsum.\n" +
                        "\n" +
                        "Nunc eleifend condimentum odio quis tempor. Sed porttitor ante at justo tristique tincidunt. Aenean pulvinar lacus et nulla scelerisque, eget malesuada mi placerat. Nulla interdum justo nec cursus lobortis. Proin lorem enim, tempus vitae eros ut, aliquet ultricies est. Duis consectetur nunc mattis metus vestibulum, ac posuere erat vulputate. Nunc venenatis mollis lorem nec hendrerit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent varius sapien blandit lorem semper iaculis. Morbi fringilla enim non pharetra rhoncus. Proin odio augue, pretium non dolor ullamcorper, mollis facilisis felis. In et sodales nunc.",
                type = MaterialType.AUDIO,
                thumbnailRes = R.drawable.placeholder,
                publishDate = LocalDate.of(2025, 4, 13),
                audioResId = R.raw.podcast3

            ),
            Material(
                id = "m5",
                creatorId = "3",
                title = "Energia wiatrowa ",
                description = "Rozmowa z ekspertem o wpływie energii na politykę międzynarodową.",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sit amet aliquet massa. Aliquam nec orci sit amet dolor porttitor faucibus sit amet ut tortor. Donec magna leo, molestie sit amet arcu vel, gravida sagittis ante. Integer consequat lobortis arcu vitae fermentum. Praesent felis nibh, suscipit a nibh sit amet, tempus aliquet quam. Sed mi purus, condimentum nec lorem ac, placerat pulvinar lorem. Suspendisse tincidunt cursus suscipit. Phasellus arcu sem, cursus quis hendrerit at, lobortis at nunc. Curabitur iaculis iaculis tempus. Proin tempus nibh vitae turpis vestibulum mollis. Mauris hendrerit ut mi nec sagittis. Ut eu mi nec lectus ultricies tempus ac at felis.\n" +
                        "\n" +
                        "Ut eu varius lectus. Morbi enim lorem, mattis non ex vitae, condimentum lobortis quam. Duis eget lorem tempor, imperdiet mauris id, rutrum ante. Nulla enim elit, elementum sit amet lorem at, aliquam dapibus mauris. Integer sollicitudin luctus rutrum. Ut finibus massa ut accumsan molestie. Cras sit amet euismod ipsum.\n" +
                        "\n" +
                        "Nunc eleifend condimentum odio quis tempor. Sed porttitor ante at justo tristique tincidunt. Aenean pulvinar lacus et nulla scelerisque, eget malesuada mi placerat. Nulla interdum justo nec cursus lobortis. Proin lorem enim, tempus vitae eros ut, aliquet ultricies est. Duis consectetur nunc mattis metus vestibulum, ac posuere erat vulputate. Nunc venenatis mollis lorem nec hendrerit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent varius sapien blandit lorem semper iaculis. Morbi fringilla enim non pharetra rhoncus. Proin odio augue, pretium non dolor ullamcorper, mollis facilisis felis. In et sodales nunc.",
                type = MaterialType.ARTICLE,
                thumbnailRes = R.drawable.placeholder,
                publishDate = LocalDate.of(2025, 5, 11),
                imageResList = listOf(R.drawable.solar1, R.drawable.solar3),
            ),



        )
    }
}
