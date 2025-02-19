# eZmoney App - DMD Project by Jercau Hadasa-Stefana & Finichiu Eduard-Adelin

## Overview
This project is an Android application developed using Kotlin. 
It includes functionalities for managing and storing recurrent expenses, a text editor with sharing capabilities, fetching exchange rates from an API,
checking connection of the device to the internet and a special section that allows the user to educate himself about the importance of saving money through music and a
motivational quote.

## Features
- **Recurrent Expense Management**: Add, view, store and delete recurrent expenses. Total amount of expenses is displayed, 
user is notified when the total amount exceeds a certain value.
- **Text Editor**: Edit and share text with other applications, such as messaging apps or email.
- **Exchange Rate API Integration**: Fetch the latest exchange rates using Retrofit. Internet connection is mandatory, exchange rates being updated constantly from the API.
- **Internet Connection Check**: Check if the device status of the airplane mode is on or off. 
- **Educational Section**: Listen to music when your mood is down and you need some cheering up.

## Mandatory Components Used

- **Foreground Service**
- **Background Service**
- **Intents**
- **Activities**
- **Broadcast Receivers**
- **Shared Preferences**
- **Content Providers**
- **Usage of external API**
- **Notifications**

## Members of the team`s contribution
- **Jercau Hadasa-Stefana**: Foreground Service, Background Service, Content Providers, Notifications, Toast Messages, Readme.md
- **Finichiu Eduard-Adelin**:  Usage of external API, Layouts, Intents, Activities, Broadcast Receivers, Shared Preferences, Readme.md

## Technologies Used

- **Kotlin**
- **Gradle**
- **Retrofit**: For making API requests.
- **RecyclerView**: For displaying lists of items.
- **MediaPlayer**: For playing music.

## Project Structure

- `ConnectionStatusActivity.kt`: Activity for checking airplane mode status.
- `ExchangeRateActivity.kt`: Activity for fetching exchange rates.
- `ExchangeRateApi.kt`: Interface for fetching exchange rates from an API.
- `ExchangeRateResponse.kt`: Data class for storing.
- `InfoActivity.kt`: Activity for displaying educational content, listening to music.
- `MainActivity.kt`: Main activity of the application.
- `MyBackgroundService.kt`: Background service for first music player.
- `MyForegroundService.kt`: Foreground service for second music player.
- `ReccurentExpenseAdapter.kt`: Activity for managing recurrent expenses. (sorry for the typo, it should be RecurrentExpenseAdapter, will be reformatted in the future)
- `RecurentActivity.kt`: Activity for managing recurrent expenses. (sorry for the typo, it should be RecurrentActivity, will be reformatted in the future)
- `RecurrentExpense.kt`: Data class for storing
- `RetrofitInstance.kt`: Retrofit instance for fetching exchange rates.
- `TextEditorActivity.kt`: Activity for editing and sharing text.
- `Resources`: Contains layout files, drawables, strings, colors, etc.
- `AndroidManifest.xml`: Contains the configuration of the application.

## Getting Started

### Prerequisites
- Android Studio 
- Gradle

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/EdwardAdelin/eZmoney.git
    ```
2. Open the project in Android Studio.
3. Build the project to download dependencies.

### Running the Application
1. Connect an Android device or start an emulator.
2. Click on the "Run" button in Android Studio.

## Usage and Mandatory Components Integration

### Main Activity
- The main activity of the application.
- Contains buttons for navigating to different sections of the application.
- **Intents** are used for navigating to different activities.
- **Activities** are used for displaying different sections of the application.

### Recurrent Expense 
- For saving the recurrent expenses, we used **SharedPreferences**. Data can be stored and retrieved. Deleting an expense will update the total amount of expenses.
- For displaying the list of expenses, we used a RecyclerView.
- **Notifications** are displayed when the total amount of expenses exceeds a certain value.
1. Add a new recurrent expense.
2. View the list of recurrent expenses.
3. Delete an expense by clicking the delete button.

### Notes 
- Used **Content Providers**.
1. Add text in the text editor.
2. Click the "Share" button to share the text with other applications.

### Exchange Rates
- Used an **external API** for fetching exchange rates.
- For fetching exchange rates, we used Retrofit.
1. Fetch the latest exchange rates.
2. View the exchange rates in a list.

### Check Connection Status
- Used a **BroadcastReceiver** to check the airplane mode status.
1. Check airplane mode status.
2. Status is updated in real-time. A good indicator to check if the device is connected to the internet.

### Info
- Used a **Background Service** for playing the first song (invisible service process running in the background).
- Used a **Foreground Service** for playing the second song (visible notification makes user aware of the service process running).
- A _toast message_ is displayed when the user clicks the play and stop button of the first song, containing a motivational quote.
1. Listen to music.
2. Read a motivational quote.
3. Analyze the importance of saving money while listening to music via a background or foreground service.

## Acknowledgements
- [Retrofit](https://square.github.io/retrofit/)
- [Android RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [Android MediaPlayer](https://developer.android.com/reference/android/media/MediaPlayer)
- [Android Shared Preferences](https://developer.android.com/training/data-storage/shared-preferences)
- [Android Content Providers](https://developer.android.com/guide/topics/providers/content-providers)
- [Android Notifications](https://developer.android.com/guide/topics/ui/notifiers/notifications)
- [Android Services](https://developer.android.com/guide/components/services)
- [Android Broadcast Receivers](https://developer.android.com/guide/components/broadcasts)
- [Android Intents](https://developer.android.com/guide/components/intents-filters)
- [Android Activities](https://developer.android.com/guide/components/activities)
- [Android Foreground Service](https://developer.android.com/guide/components/services#Foreground)
- [Android Background Service](https://developer.android.com/guide/components/services#Background)
- [Android API Guides](https://developer.android.com/guide)
- [Android Developer Documentation](https://developer.android.com/docs)
- [Android Developer Training](https://developer.android.com/courses)
