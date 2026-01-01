# Arkanoid
# Arkanoid Game

## Project Description
This project is a Java-based implementation of the classic Arkanoid arcade game. The game features a paddle controlled by the player, multiple balls, and rows of destructible blocks. It is built using a modular architecture that emphasizes object-oriented principles, including design patterns such as the **Observer pattern** for hit detection and the **Composite pattern** for sprite management.

## Key Features
* **Collision Detection:** A system that handles interactions between the ball, paddle, blocks, and screen boundaries.
* **Score Management:** Real-time score tracking displayed on screen, with bonuses for clearing the entire screen.
* **Event Listeners:** Automated removal of blocks and balls using `HitListener` interfaces to manage game state.
* **Death Region:** A specific logic block at the bottom of the screen that removes balls and updates the ball counter.

## Project Structure
The source code is organized into several specialized packages:
* **`Game`**: Contains the core engine (`Game`), the environment coordinator (`GameEnvironment`), and the player-controlled `Paddle`.
* **`Geometry`**: Defines the mathematical foundation, including `Point`, `Line`, `Rectangle`, `Velocity`, and the `Ball` class.
* **`Collidables`**: Interfaces and classes for objects that can be hit, such as the `Block`.
* **`Sprites`**: Manages all drawable objects and the `SpriteCollection` for batch rendering.
* **`Listeners`**: Implements the notification system for game events like `BlockRemover` and `BallRemover`.

## How to Run
The project uses **Apache Ant** for automated building and execution.

### Prerequisites
* Java Development Kit (JDK).
* Apache Ant installed.
* The `biuoop-1.4.jar` library (included in the root directory).

### Commands
1.  **Compile the project:**
    ```bash
    ant compile
    ```

2.  **Run the game:**
    ```bash
    ant run
    ```

## Controls
* **Movement:** Use the arrow keys (Left/Right) to move the paddle.
* **Objective:** Break all the blocks to win. The game ends if all balls fall below the paddle.
* 
