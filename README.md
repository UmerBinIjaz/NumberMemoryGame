# Number Memory Game

## Project Overview
**Author:** Umer Bin Ijaz  
**Environment:** JavaFX, Java 11+  
**IDE:** Eclipse  

---

## Description
**Text Memory Game** is a memory-based tile clicking game built with JavaFX.

When the game starts, a 3x3 grid (9 tiles) with numbered tiles is randomly displayed on the screen. After a short period (default: 6 seconds), the tiles are hidden, and the player must click them in correct numerical order (1 to 9).

---

## How It Works

1. Click the **Start** button to begin the game.
2. A grid of 9 numbered tiles appears at random positions.
3. Tiles remain visible for **6 seconds**, then disappear.
4. The player must remember and click the tiles **in correct order** (1 to 9).
5. Correct selections are revealed; incorrect selections print a warning.
6. On successful completion, a **congratulations** message appears and the game restarts.

---

## Key Features

- **Random tile layout** across the screen
- **Stylish UI** using gradient backgrounds and CSS-style JavaFX code
- **Text-based tiles** with custom fonts
- **Concurrent timer** using `ScheduledExecutorService`
- **Interactive gameplay** with click handling
- Console messages on correct/incorrect clicks
- **Automatic restart** upon success

---

## Main Classes

### `Main.java`
- Extends `Application`
- Sets up the main scene and game logic
- Handles game start, tile creation, and game flow

### `TileView.java`
- Custom `StackPane` class
- Contains a rectangle and number text
- Controls tile visibility and styles

---

## Requirements

- Java 11 or higher
- JavaFX SDK
- Eclipse IDE (or any IDE with JavaFX support)

---

## License

This project is open-source and free to use for educational or personal use.
