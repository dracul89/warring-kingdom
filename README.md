# Warring Kingdoms

Warring Kingdoms is a 2D RPG game built with Java using the Slick2D and LWJGL libraries. Choose your kingdom—Rogues, Mages, Barbarians, or Monks—and conquer all others to rule the land.

> **Note:** This game is not ARM architecture compatible and will not run on M-series Macs due to dependencies on older native libraries (LWJGL 2).

---

## Table of Contents
- [Requirements](#requirements)
- [Setup & Installation](#setup--installation)
- [How to Run](#how-to-run)
- [Controls & Gameplay](#controls--gameplay)
- [Project Structure](#project-structure)
- [Build Scripts](#build-scripts)
- [Cheats](#cheats)
- [License](#license)

---

## Requirements

- **Java Development Kit (JDK):** Version 11 is required (as specified in `build.gradle`).
- **Operating System:** Windows, Linux, or Intel-based Mac OS X.
- **Gradle:** The project uses the Gradle wrapper, so a local installation is not strictly necessary.

## Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd warring-kingdoms
   ```

2. **Native Libraries:**
   The project includes native libraries for Windows, Linux, and macOS in the `lib/natives` directory. These are automatically configured when running via Gradle.

## How to Run

### Using Gradle Wrapper

**Windows:**
```powershell
.\gradlew.bat build run
```

**Linux and macOS (Intel):**
```bash
chmod +x gradlew
./gradlew build run
```

## Controls & Gameplay

- **Movement:** Use `W`, `A`, `S`, `D` or **Arrow Keys**.
- **Combat:** Standard RPG battle mechanics (turn-based).
- **Goal:** Defeat all other factions to control all four kingdoms.

## Project Structure

- `src/`: Java source code.
  - `game/`: Main game logic and entry point (`Game.java`).
  - `game/audio/`: Sound handling.
  - `game/character/`: Hero stats and character logic.
  - `game/enemy/`: Enemy AI and factory patterns.
  - `game/screen/`: Different game states (Menu, Map, Battle, etc.).
- `res/`: Game assets including images (`.png`, `.psd`), music (`.ogg`, `.wav`), and tilemaps (`.tmx`).
- `lib/`: Native library dependencies for LWJGL.
- `gradle/`: Gradle wrapper files.

## Build Scripts

- `build.gradle`: Main build configuration.
  - Sets Java 11 compatibility.
  - Manages dependencies for Slick2D and LWJGL.
  - Configures `jvmArgs` to point to the correct native libraries based on the OS.
- `gradlew` / `gradlew.bat`: Scripts to execute Gradle tasks without pre-installed Gradle.

## Environment Variables

No specific environment variables are required. The project uses system properties (set automatically by the Gradle `run` task) to locate native libraries:
- `java.library.path`: Points to `lib/natives/<os>/`.

## Tests

TODO: No automated tests (Unit/Integration) were found in the project. Manual testing is recommended.

## Cheats

The following cheats are available for development and testing:
- **Max Stats:** Enter the name `qwerty` during character creation.
- **Easter Egg:** Enter the name `Tom` during character creation.
- **Insta-kill:** Press `0` during combat to instantly kill the enemy.

---

## License

TODO: Add license information.
