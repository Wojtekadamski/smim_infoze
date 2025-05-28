# InfoZE – Aplikacja mobilna o energii odnawialnej

## 📱 O projekcie

**InfoZE** to aplikacja mobilna na platformę Android, która dostarcza użytkownikom informacje na temat energii odnawialnej. Oferuje treści w formie:
- artykułów (tekst, zdjęcia, wideo),
- podcastów (audio),
- ciekawostek oraz profili twórców.

Użytkownicy mogą przeglądać multimedia, zapamiętywać ulubionych autorów, oceniać materiały oraz przeglądać profile twórców i ich publikacje.

---

## 🛠️ Stack technologiczny

- **Android Studio (Kotlin)**
- **Jetpack Compose** – budowa interfejsu
- **Room** – lokalna baza danych
- **Media3 (ExoPlayer)** – odtwarzanie wideo i podcastów
- **Navigation Compose** – zarządzanie ekranami
- **Material 3** – stylizacja i komponenty UI

---

## 🚧 Etapy rozwoju

1. **Projekt graficzny w Figma** – layouty ekranu głównego, artykułu, podcastów, profilu.
2. **Implementacja ekranów głównych** – HomeScreen, ArticleScreen, PodcastScreen.
3. **Dodanie modelu danych** – `Material`, `Creator`, `Rating`.
4. **Multimedia** – obsługa zdjęć (karuzele), wideo (fullscreen), audio (z paskiem postępu).
5. **Oceny i ulubione** – możliwość oceniania materiałów i zapisywania twórców.
6. **Zarządzanie stanem i błędami** – rozwiązywanie crashy (np. `ArithmeticException` przy pustych listach).

---

## ⚠️ Problemy napotkane podczas rozwoju

- Obsługa trybu pełnoekranowego zdjęć i wideo przy użyciu `Dialog` i `Pager`.
- Zapętlanie karuzeli zdjęć (`index % items.size`) wymagało dodatkowej ochrony przed dzieleniem przez zero.
- Wideo nie zatrzymywało się po wyjściu z artykułu – wymagało `DisposableEffect` i odpowiedniego zarządzania `ExoPlayer`.
- Synchronizacja stanu odtwarzacza audio z interfejsem.
- Dynamiczne rozwijanie/zwijanie pola opisu podcastu oraz automatyczne zwijanie po zakończeniu.

---

## 📂 Struktura projektu

```
- app
  - src
    - main
      - java
        - com
          - smim
            - infoze
              - MainActivity.kt              # Główna aktywność aplikacji
              - Navigation.kt                # Nawigacja między ekranami
              - ui
                - screen                     # Ekrany aplikacji
                  - ArticleDetailScreen.kt   # Szczegóły artykułu z mediami
                  - PodcastScreen.kt         # Lista podcastów
                  - ProfileScreen.kt         # Ekran profilu twórcy
                  - HomeScreen.kt            # Ekran główny aplikacji
                - component                  # Komponenty UI
                  - BottomNavigationBar.kt   # Dolna nawigacja
                  - ImageCarousel.kt         # Karuzela zdjęć
                  - PodcastItem.kt           # Pojedynczy podcast (UI + ExoPlayer)
                  - VideoPlayer.kt           # Odtwarzacz wideo (Media3)
                  - FullScreenImageViewer.kt # Tryb pełnoekranowy zdjęć
                  - FullScreenImageViewerOverlay.kt # Nakładka fullscreen
                  - MediaCarousel.kt         # Połączona karuzela zdjęć i wideo
              - model
                - Material.kt                # Model materiału (artykuł, audio, wideo)
                - Creator.kt                 # Model twórcy
                - MaterialType.kt            # Typ materiału (enum)
                - Rating.kt                  # Oceny materiałów
              - data
                - AppDatabase.kt             # Baza danych Room
                - MaterialDao.kt             # DAO dla materiałów
                - RatingDao.kt               # DAO dla ocen
      - res
        - drawable                           # Zasoby graficzne (solar1.png itd.)
        - layout                             # (niewykorzystane, Jetpack Compose)
        - values
          - colors.xml                       # Definicje kolorów
          - themes.xml                       # Styl aplikacji
      - AndroidManifest.xml                  # Konfiguracja aplikacji
  - build.gradle.kts                         # Konfiguracja modułu app
- build.gradle.kts                           # Konfiguracja główna
- settings.gradle.kts                        # Rejestracja modułów
```

---

## 📎 Jak dodać pliki audio i wideo?

- Umieść pliki `.mp3` i `.mp4` w folderze `res/raw`. Jeśli folder nie istnieje, utwórz go.
- W kodzie odwołuj się przez `R.raw.nazwa_pliku` (bez rozszerzenia).
- Przykład użycia:
```kotlin
val audioResId = R.raw.podcast1
val videoResId = R.raw.energy_video
```

---

## 🧪 Dalsze kroki

- Dodanie komentarzy do materiałów.
- Filtrowanie i wyszukiwanie treści.
- Synchronizacja danych z backendem (Firebase lub REST API).

---

## 👤 Autor

Projekt realizowany jako część pracy zaliczeniowej z przedmiotu programowania aplikacji multimedialnych (PAMSI).
