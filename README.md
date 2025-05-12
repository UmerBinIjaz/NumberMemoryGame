/**
 * ================================================================
 * Project Title: Text Memory Game
 * Author: Umer Bin Ijaz
 * Environment: JavaFX, Java 11+
 * IDE: Eclipse
 * ================================================================
 * Description:
 * This is a memory-based tile clicking game built with JavaFX.
 * 
 * The game displays a 9-tile grid with numbers randomly placed on the screen.
 * When the "Start" button is clicked, the tiles appear for a few seconds 
 * (default: 6 seconds) before hiding. The player's objective is to click the tiles 
 * in the correct numeric order (1 to 9).
 * 
 * Key Features:
 * - Dynamic tile layout using random positioning on a grid
 * - Custom tile styles using gradients and CSS-like styling
 * - Word-like number tiles with custom fonts and hover effects
 * - Undo mechanism not implemented but could be expanded
 * - Timer-based hiding of tiles using ScheduledExecutorService
 * - Handles correct and incorrect tile selection
 * 
 * How It Works:
 * - On clicking "Start", a grid of numbered tiles is generated and shown for 6 seconds.
 * - Tiles are hidden after 6 seconds using a background thread.
 * - The player must remember the order and click the tiles from 1 to 9.
 * - If the player clicks the correct tile in sequence, it's revealed again.
 * - If an incorrect tile is clicked, a message is printed, but the game continues.
 * - When all tiles are correctly clicked, a success message appears and the game restarts.
 * 
 * Classes:
 * - Main: Extends Application, sets up the scene and game logic
 * - TileView: A custom StackPane containing a Rectangle and Text with style and visibility logic
 *
 * Notes:
 * - Game visuals use JavaFX shapes and styling
 * - Game logic uses Java concurrency (ScheduledExecutorService) for timer
 * - All code is compatible with JavaFX in Eclipse setup
 */
