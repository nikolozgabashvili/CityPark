# City Park

City Park is a feature-rich, modular Android application that streamlines parking reservations, vehicle management, and payment processing. Powered by Jetpack Compose and built on a robust Clean Architecture + MVI foundation, it delivers a seamless and modern user experience.

---

## 🚀 Features

- **Parking Reservations**: Browse, select, and reserve available parking spots.
- **Vehicle Management**: Add and remove vehicles linked to your account.
- **Payment Methods**: Securely add and store credit cards for fast checkout.
- **User Profiles**: View and update personal information and preferences.
- **Notifications**: Receive real-time notifications and see their history.
- **Custom Settings**: Toggle between system, dark or light modes and choose your preferred language 🇬🇪 or 🇺🇸.

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** Clean Architecture + MVI
- **Dependency Injection:** Dagger Hilt
- **Serialization:** Kotlinx Serialization
- **Data Persistence:** Preferences DataStore
- **Background Work:** WorkManager
- **Authentication & Maps:** Google Play Services
- - **Foreground Service** to track active parking reservations

---

## 🏗️ Project Structure

Organized into feature modules (each split into `data`, `domain`, and `presentation` layers) plus shared core modules:

```text
:app
:build-logic
:core
  ├─ designsystem
  ├─ ui
  ├─ data
  └─ domain
:datastore
:feature
  ├─ auth
  ├─ cars
  ├─ home
  ├─ more
  ├─ messaging
  ├─ parking
  ├─ payment
  ├─ reservation
  ├─ settings
  └─ user
```

---

## 📄 API & Data

City Park integrates with a custom-built **Ktor backend application** that powers all core server-side functionality. The backend is responsible for securely handling:

- **Vehicle & Profile Management**
- **Parking Reservation Logic**
- **Payment Handling & Card Storage**
- **Notification Delivery**

All data exchanges between the client and server are performed via RESTful APIs using **Ktor** with **Kotlinx Serialization** for request/response mapping.

> 🔐 **Authentication** is managed by **Firebase Authentication**, providing secure login and identity services.

---

### 🔄 Data Sync

City Park uses:
- **DataStore** for local preference persistence  
- **WorkManager** for background sync and retries

---

## 👥 Contributors

- **Nikoloz Gabashvili** - [GitHub](https://github.com/nikolozgabashvili)
- **Davit Beradze** - [GitHub](https://github.com/davidbera1)
