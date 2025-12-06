 # ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white)
# Moveicoo — Movie Application

> Moveicoo is a modern and user-friendly Android movie app that allows users to browse, save, and discover movies effortlessly. The app offers features like favorites, random movie roulette, mood-based suggestions, AI chatbot, voice navigation, search by voice, adjustable font sizes, and movie scene descriptions. The app also integrates **accessibility features** to ensure a seamless experience for all users, including voice guidance, large text support, and easy navigation for users with different needs.

---

## Table of Contents
- [Overview](#overview)  
- [Tech Stack](#tech-stack)  
- [Team](#team)  
- [System Design](#system-design)  
- [Application Features](#application-features)  
- [Screenshots](#screenshots)  
- [Application Advantages](#application-advantages)  
- [Future Work](#future-work)  
- [Conclusion](#conclusion)  
- [Contact](#contact)

---

## Overview
Moveicoo helps movie lovers enjoy the latest films without going to the cinema. The app offers organized categories, random movie suggestions, and interactive features for a fun and engaging experience. It includes Firebase Authentication, notifications, and a clean Jetpack Compose UI.  

---

## Tech Stack
- **Language:** Kotlin  
- **UI:** Jetpack Compose  
- **Architecture:** MVVM  
- **Networking:** Retrofit  
- **Local Storage:** DataStore  
- **Dependency Injection:** Hilt  
- **Async Operations:** Coroutines  
- **Authentication:** Firebase Authentication  
- **Notifications:** FCM  
- **Additional Features:** Voice Navigation, Font Size Adjustment  

---

## Team
- **Basmala Mohamed**  
- **Zinab Abdelrazik**  
- **Mozn Okash**  
- **Asma**  
- **Merna**  

**Supervisor:** Seif Eldien Mohamed  

---

## System Design
- MVVM Architecture  
- Repository Pattern  
- Retrofit (API Layer)  
- Firebase Authentication  
- DataStore (Local Storage)  
- Hilt (Dependency Injection)  
- Coroutines (Async Operations)  

---

## Application Features

### 1. Splash Screen
- Shows the app logo while loading.

### 2. Login & Signup
- Secure authentication using Firebase.

### 3. Home Screen
- Featured movies, trending, and upcoming releases.
- Smooth navigation and clean UI.

### 4. Favorites Screen
- Save and access favorite movies easily.

### 5. Movie Details
- Title, overview, rating, cast.
- Favorite heart icon and save button.

### 6. Random Movie Screen
- Spin wheel to discover a movie instantly.

### 7. Mood-Based Movie
- Select mood → app suggests a matching movie.

### 8. ChatBot
- AI assistant for movie recommendations and questions.

### 9. Settings
- Welcome message, notifications toggle, font size slider, invite friends, access Terms of Service, Contact support.

### 10. Saved Screen
- Store movies for later viewing.

### 11. All Movies Screen
- Full collection of movies.

### 12. CineMystery Quiz
- Interactive movie guessing game with scoring.

---

## Screenshots

### Splash Screen
(![WhatsApp Image 2025-12-02 at 22 29 25_28ba39e5](https://github.com/user-attachments/assets/9b7ea94b-916c-450f-a805-1759de6a1de8)
)

### Login / Signup
![WhatsApp Image 2025-12-02 at 22 29 25_c13d584f](https://github.com/user-attachments/assets/123a9909-a3d4-405a-b70f-89762f4f4605)
![WhatsApp Image 2025-12-02 at 22 29 25_66a0d9d7](https://github.com/user-attachments/assets/d09664c6-4836-43a1-adaa-e70b8ddd7a7f)
![WhatsApp Image 2025-12-02 at 22 29 26_4aef2e3f](https://github.com/user-attachments/assets/a810b080-8440-41f6-b0d0-5e81d9c6ba65)

### Home Screen
![WhatsApp Image 2025-12-02 at 22 29 26_12a55177](https://github.com/user-attachments/assets/9438ab00-850d-4f3d-96e1-083ec3fcbdeb)

### Movie Details
![WhatsApp Image 2025-12-02 at 22 29 27_5ce4d631](https://github.com/user-attachments/assets/44ecb2f6-cd25-4f08-a519-b97d0ddee2f9)

### Random Movie Wheel
![WhatsApp Image 2025-12-02 at 22 39 29_d52882a2](https://github.com/user-attachments/assets/1169baa1-c962-4611-9433-9cc59cc14223)

### Favorites & Saved
![WhatsApp Image 2025-12-02 at 22 29 28_22b65740](https://github.com/user-attachments/assets/56bf126f-4971-4b6d-8fd0-3a2fdbc7592e)

### Mood-Based Movie
![WhatsApp Image 2025-12-02 at 22 29 26_45d94a8b](https://github.com/user-attachments/assets/9c841667-e817-4d11-8b9e-0f9714c0fb95)

### ChatBot
![WhatsApp Image 2025-12-02 at 22 29 29_3e170e66](https://github.com/user-attachments/assets/a234fb24-1c89-4b7c-80a2-4c670df1e862)

### Quiz Screen
![WhatsApp Image 2025-12-02 at 22 29 28_3adba9c4](https://github.com/user-attachments/assets/66cefb80-df1f-473f-8d1a-40781584a95c)

### General Chat Screen
![WhatsApp Image 2025-12-02 at 22 29 27_4615be26](https://github.com/user-attachments/assets/11129a76-d471-4ec8-b0a1-e723feba31b4)

### Watch Now Screen
![WhatsApp Image 2025-12-03 at 00 03 26_7e332cdc](https://github.com/user-attachments/assets/34f66cf6-5038-4eb4-9168-94405906a11a)

### Settings Screen
![WhatsApp Image 2025-12-02 at 22 29 30_68c09b22](https://github.com/user-attachments/assets/b0ebfd6c-2fab-4c8a-a8c2-68f05587af09)

### Search Screen
![WhatsApp Image 2025-12-02 at 22 29 29_480b38b5](https://github.com/user-attachments/assets/80154cd8-c9bd-4bba-9bc3-07c6cb0ee33e)

---

## Application Advantages
1. **No need to go to the cinema:** Enjoy latest movies from home.  
2. **Organized choices:** Movies are categorized for easy browsing.  
3. **Random movie discovery:** One-click movie roulette.  
4. **Stay up-to-date:** Latest releases delivered directly.  
5. **Fun & interactive experience:** Saved movies, notifications, engaging UI.  

---

## Implemented Features
- **Mood to Movie:** Users pick a mood, and the app suggests a movie that fits it.  
- **Voice Navigation:** Navigate through the app using voice commands.  
- **Search by Voice:** Search for movies using your voice.  
- **Gemini AI:** Integrated AI assistant for personalized recommendations.  
- **General Chat:** Built-in chat to ask questions or get movie suggestions.  
- **Random Movie:** Spin the wheel to discover a random movie instantly.  
- **Guess the Movie (CineMystery):** Interactive movie quiz with multiple-choice questions and scoring.  
- **Adjustable Font Size:** Change the font size for the entire app for better readability.  
- **Movie Scene Description via Voice:** The app describes movie scenes audibly for an enhanced experience.
  
---

## Future Work
- Streaming integration (Netflix, etc.)  
- Advanced AI-based personalization and playlists  
- Social features (ratings, reviews, list sharing, forums)  
- Enhanced existing features (improved AI chatbot and quiz game)  
- Web app, offline mode, and performance optimization  

---

## Conclusion
Moveicoo successfully delivers a modern, intuitive movie app that solves common user problems. The app improves movie discovery and enjoyment, featuring clean UI, interactive tools, and engaging content. The project strengthened skills in Android development, Kotlin, Jetpack Compose, Firebase, UI/UX design, teamwork, and problem-solving.

---

## Contact
**Email:** Basmala01067618897@gmail.com  
