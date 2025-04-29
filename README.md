# City Park

City Park is a modular Android app designed for effortless parking reservations, vehicle tracking, and secure payment handling. Built with Jetpack Compose and structured using Clean Architecture and MVI, it offers a modern and intuitive user experience.

---

## 🚀 Features

- **Parking Reservations**: Browse, select, and reserve available parking spots.
- **Vehicle Management**: Add, edit, and remove vehicles linked to your account.
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
- **Secrets Management:** Maps Secrets Plugin
- **Testing:** JUnit, MockK

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
- **Caching strategies** to reduce API calls where applicable  
- **WorkManager** for background sync and retries

---

## 👥 Contributors

- **Nikoloz Gabashvili** - [GitHub](https://github.com/nikolozgabashvili)
- **Davit Beradze** - [GitHub](https://github.com/davidbera1)
