# City Park

City Park is a feature-rich, modular Android application that streamlines parking reservations, vehicle management, and payment processing. Powered by Jetpack Compose and built on a robust Clean Architecture + MVI foundation, it delivers a seamless and modern user experience.

---
## Auth

<img src="https://github.com/user-attachments/assets/57515e4a-4e39-49cd-90f2-641b3f23060f" width="230px"/>
<img src="https://github.com/user-attachments/assets/49d1ba34-6467-4342-80c5-4df48c0e563b" width="230px"/>
<img src="https://github.com/user-attachments/assets/d50db821-ad5b-4b8a-8e50-74d71a94f06e" width="230px"/>

---
## Parking

<img src="https://github.com/user-attachments/assets/7f22f41d-f645-40b8-8937-0b7646c53315" width="230px"/>
<img src="https://github.com/user-attachments/assets/c8242980-3699-4704-a778-faf19d469795" width="230px"/>
<img src="https://github.com/user-attachments/assets/fd6e2cfa-b232-46ab-899c-b4b082e92445" width="230px"/>
<img src="https://github.com/user-attachments/assets/1d299ef7-1461-4fb0-9252-886a35e9fe00" width="230px"/>
<img src="https://github.com/user-attachments/assets/0760eb51-7d42-448a-9da9-87e136d25258" width="230px"/>

---

## More
<img src="https://github.com/user-attachments/assets/a496c0e3-b877-492d-b29f-1c1f46bf48fe" width="230px"/>
<img src="https://github.com/user-attachments/assets/e15fceb4-386b-4b59-bb0a-84dc3b7ec14a" width="230px"/>
<img src="https://github.com/user-attachments/assets/6ba21a44-63a3-49f0-922d-d2ef72326c75" width="230px"/>
<img src="https://github.com/user-attachments/assets/5d155df5-20e8-4d11-b987-199d9255752e" width="230px"/>
<img src="https://github.com/user-attachments/assets/7018c81c-dd35-4526-a54c-062a349b8455" width="230px"/>


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
- **Foreground Service** to track active parking reservations

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
