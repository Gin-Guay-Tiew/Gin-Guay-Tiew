# 🍜 กกต (กินก๋วยเตี๋ยว)
This project is created for educational purposes only as part of an Object-Oriented Programming at IT Kmitl.  
It is not related to any political context.

## Concept
**กกต** is a simulation and time-management game built around the idea of *controlled chaos*.  
Players take on the role of a street food vendor, preparing noodle dishes based on customer orders under time pressure.

The challenge lies in balancing speed, accuracy, and decision-making to achieve the required revenue in each level.

---

## Gameplay Overview
- Take orders from different types of customers
- Select the correct ingredients (noodles, soup, toppings, drinks)
- Prioritize orders based on urgency and value
- Serve dishes within limited time
- Earn enough revenue before time runs out

---

## Technologies Used
- Java (OOP)
- Java Swing / JavaFX
- Figma (UI/UX Design)
- Git / GitHub
- Aseprite (Pixel Art)
- Procreate (Asset Design)
- IntelliJ IDEA

---

## Folder Structure
The repository is organized as follows:
```text
Gin-Guay-Tiew/
├── resources/            # Game assets
│   ├── audio/            # BGM and SFX sourced from Pixabay
│   ├── font/             # Custom typography (Jersey10.ttf)
│   └── images/           # Sprites and UI components
├── src/                  # Source code
│   ├── logic/            # Game mechanics and scoring systems
│   ├── main/             # Application entry point
│   ├── ui/               # Pages, components, and layouts
│   └── utilities/        # Loaders and managers (Sound, Font, Icons)
└── saveData.dat          # Local game progress data
```

## Installation
### Requirements
* JDK 21
* JavaFX 21.0.10 (included in lib/ folder for Fat JAR deployment)
* Recommended: IntelliJ IDEA for development
### Running in IntelliJ IDEA
1. Clone the repository:
```
git clone https://github.com/yourusername/gin-guay-tiew.git
cd gin-guay-tiew
```
2. Open the project in IntelliJ IDEA and Install JavaFX 21.0.10
https://gluonhq.com/products/javafx/
3. Add JavaFX SDK as a library
```
File → Project Structure → Libraries → + → Java → [select lib folder in JavaFX SDK folder]
Main ⬇️ -> Edit Configurations -> Application -> Main -> Modify options -> add Main class -> add --module-path "C:\Users\Admin\Downloads\javafx-sdk-21.0.10\lib" --add-modules javafx.controls,javafx.fxml,javafx.media,,javafx.swing (don't forget to fix path file to your path) in Program arguments
```
4. Run the main class
```
src/main/Main.java
```

---

### Running as Fat JAR (Recommended for distribution)
1. Create a Fat JAR to include all dependencies
```
File → Project Structure → Artifacts → + → JAR → From modules with dependencies → Select Main Class
Build → Build Artifacts → YourGame.jar → Build
```
2. Distribute the folder with
```
   Gin-Guay-Tiew/
   ├─ YourGame.jar
   ├─ lib/
   │   └─ javafx-sdk-21/
   └─ launch.bat / launch.sh
```
3. Example Windows launch script (launch.bat)
```
java --module-path "lib/javafx-sdk-21/lib" --add-modules javafx.controls,javafx.fxml -jar YourGame.jar
pause
```
4. Run launch.bat (Windows) or launch.sh (macOS/Linux)
---

## Audio
- Background music : resources/audio/bg/
- Sound effects : resources/audio/sfx/
- Audio resources are sourced from Pixabay and free for educational use

## Notes
- Saves progress locally in saveData.dat.
- All assets, including sprites, fonts, and sounds, are optimized for smooth gameplay.
- Fully offline playable once the JAR and libraries are distributed.
- JavaFX is required only for GUI rendering and audio playback.

## Collaborators (Team of 10)
- 68070028 ชวัลรัตน์ ศรีรัตน์
- 68070038 ณปภา ธุวสินธุ์
- 68070039 ณัชชา ธรรมเอกภาพ
- 68070044 ณัฐพล นินนาท
- 68070051 ทักษพงศ์ ใจยา
- 68070066 ธันยพร เดชพันธ์
- 68070071 ธีรเมธ ปักโคทานัง
- 68070093 บุณยาพร ชัยมงคลทรัพย์
- 68070119 พัชร คงอ่ำ
- 68070284 ปรณัฏฐ์ สุดสวาท