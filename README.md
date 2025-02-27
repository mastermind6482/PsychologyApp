# PsychologyApp

**PsychologyApp** — это приложение для Android, разработанное на Kotlin, которое помогает пользователям заниматься медитацией и изучать статьи по психологии. Приложение предлагает интерактивный интерфейс с плавными анимациями, успокаивающим градиентным фоном и визуальной поддержкой через Unsplash API.

---

## Функциональность

### Основные экраны

1. **Главный экран (HomeFragment)**  
   - Кнопки для перехода к медитациям, психологии, поиску и настройкам.

2. **Медитации (MeditationFragment)**  
   - Список карточек медитаций с изображениями из Unsplash.  
   - Переход к деталям медитации при клике.

3. **Детали медитации (MeditationDetailFragment)**  
   - Круговой белый таймер с анимацией истечения времени.  
   - Текст инструкции на полупрозрачном фоне с закругленными углами и тенью.  
   - Воспроизведение расслабляющей музыки (`relax_music.mp3`).  
   - Кнопки "Старт/Пауза" и "Стоп" внизу экрана.  
   - Успокаивающий переливающийся градиент пастельных тонов на фоне.

4. **Психология (PsychologyFragment)**  
   - Список карточек статей с изображениями из Unsplash.  
   - Переход к деталям статьи при клике.

5. **Детали статьи (PsychologyDetailFragment)**  
   - Отображение заголовка, текста статьи и заметок пользователя.

6. **Поиск (SearchFragment)**  
   - Поиск медитаций по названию с отображением карточек и изображений из Unsplash.

7. **Настройки (SettingsFragment)**  
   - Переключатель светлой/темной темы.

### Особенности

- **Unsplash API**: Изображения для карточек медитаций и статей загружаются из Unsplash по их названиям.  
- **Mock-сервер Postman**: Данные о медитациях и статьях (20 объектов каждого) получаются через mock-сервер.  
- **UI-дизайн**: Используется кастомный шрифт (Lora), градиенты и анимации для создания успокаивающего опыта.  
- **Музыка**: Встроенный файл `relax_music.mp3` воспроизводится во время медитации.

---

## Структура проекта

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/psychologyapp/
│   │   │   ├── MainActivity.kt                # Главная активность
│   │   │   ├── data/
│   │   │   │   ├── ApiService.kt             # Интерфейс для Retrofit
│   │   │   │   ├── UnsplashResponse.kt       # Модель данных Unsplash
│   │   │   │   ├── MeditationResponse.kt     # Модель медитаций
│   │   │   │   ├── ArticleResponse.kt        # Модель статей
│   │   │   ├── ui/
│   │   │   │   ├── HomeFragment.kt           # Главный экран
│   │   │   │   ├── MeditationFragment.kt     # Список медитаций
│   │   │   │   ├── MeditationDetailFragment.kt # Детали медитации
│   │   │   │   ├── MeditationAdapter.kt      # Адаптер медитаций
│   │   │   │   ├── AnimatedGradientView.kt   # Кастомный градиент
│   │   │   │   ├── SearchFragment.kt         # Экран поиска
│   │   │   │   ├── SettingsFragment.kt       # Настройки
│   │   │   │   ├── PsychologyFragment.kt     # Список статей
│   │   │   │   ├── PsychologyDetailFragment.kt # Детали статьи
│   │   │   │   ├── PsychologyAdapter.kt      # Адаптер статей
│   │   │   ├── viewmodel/
│   │   │   │   ├── MeditationViewModel.kt    # VM для медитаций
│   │   │   │   ├── SearchViewModel.kt        # VM для поиска
│   │   │   │   ├── PsychologyViewModel.kt    # VM для статей
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   │   ├── circular_progress_timer.xml # Белый круговой таймер
│   │   │   │   ├── rounded_instruction_background.xml # Фон текста
│   │   │   ├── font/
│   │   │   │   ├── lora_regular.ttf          # Шрифт Lora
│   │   │   │   ├── font_family.xml           # Семейство шрифтов
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml         # Главная активность
│   │   │   │   ├── fragment_home.xml         # Главный экран
│   │   │   │   ├── fragment_meditation.xml   # Список медитаций
│   │   │   │   ├── fragment_meditation_detail.xml # Детали медитации
│   │   │   │   ├── item_meditation.xml       # Карточка медитации
│   │   │   │   ├── fragment_search.xml       # Поиск
│   │   │   │   ├── fragment_settings.xml     # Настройки
│   │   │   │   ├── fragment_psychology.xml   # Список статей
│   │   │   │   ├── item_article.xml          # Карточка статьи
│   │   │   │   ├── fragment_psychology_detail.xml # Детали статьи
│   │   │   ├── navigation/
│   │   │   │   ├── nav_graph.xml             # Навигационный граф
│   │   │   ├── raw/
│   │   │   │   ├── relax_music.mp3           # Музыка для медитации
│   │   │   ├── values/
│   │   │   │   ├── colors.xml                # Цвета
│   │   │   │   ├── strings.xml               # Строки
│   │   │   │   ├── themes.xml                # Тема с шрифтом
│   │   │   │   ├── styles.xml                # Стили кнопок
│   │   ├── AndroidManifest.xml
├── build.gradle.kts                          # Зависимости модуля
```
---

## Установка и настройка

### Требования

- **Android Studio**: Koala (2024.1.1) или новее.  
- **Kotlin**: 1.9.0.  
- **Min SDK**: 24.  
- **Target SDK**: 34.

### Зависимости

- `androidx.navigation` — для навигации.  
- `retrofit2` — для API-запросов.  
- `glide` — для загрузки изображений.  
- `cardview` — для UI-элементов.  
- `okhttp3` — для кастомных запросов (если используется `x-api-key`).

### Шаги настройки

1. **Клонируй репозиторий**:  
   ```bash
   git clone <repository-url>
### Добавление ключей API

- В файлах `MeditationViewModel.kt`, `PsychologyViewModel.kt`, `SearchViewModel.kt` замени:  
  - `YOUR_UNSPLASH_API_KEY` на ключ Unsplash (получи на [Unsplash Developers](https://unsplash.com/developers)).  
  - `https://your-postman-mock-server-id.mock.pstmn.io/` на URL mock-сервера Postman.  
- Если mock-сервер приватный:  
  - Добавь `x-api-key` в Retrofit (см. код `MeditationViewModel.kt` с использованием `OkHttpClient`).

### Добавление музыки

- Помести файл `relax_music.mp3` в папку `res/raw`.

### Синхронизация проекта

- Открой проект в Android Studio и нажми *Sync Project with Gradle Files*.

### Сборка и запуск

- Выполни *Build > Rebuild Project* и запусти приложение на эмуляторе или устройстве.

---

## Mock-сервер Postman

1. Создай коллекцию с запросами:  
   - `GET /meditations`  
   - `GET /articles`  
2. Вставь JSON с 20 объектами для каждой категории (см. код проекта).  
3. Настрой mock-сервер и скопируй сгенерированный URL.

---

## UI-улучшения (планы)

- **Таймер**:  
  - Добавить более сложную анимацию (например, пульсацию).  
  - Реализовать кастомный прогресс-бар.  
- **Градиент**:  
  - Экспериментировать с дополнительными пастельными оттенками.  
  - Добавить эффект волн.  
- **Текст**:  
  - Использовать разные шрифты для заголовков и основного текста.  
  - Увеличить контраст для лучшей читаемости.  
- **Кнопки**:  
  - Добавить анимацию нажатия (ripple).  
  - Использовать закругленные иконки.  
- **Карточки**:  
  - Улучшить дизайн карточек медитаций и статей:  
    - Добавить тени.  
    - Реализовать hover-эффекты.

---

## Известные ограничения

- **Unsplash API**:  
  - Лимит 50 запросов в час.  
  - Возможны проблемы при частом запуске приложения.  
- **Производительность**:  
  - Анимация градиента может нагружать слабые устройства.

---
