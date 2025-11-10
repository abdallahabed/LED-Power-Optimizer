
# 💡 LED Power Optimizer – Original Version

This is the **original functional version** of the LED Power Optimizer project.  
It uses **Dynamic Programming (Longest Common Subsequence – LCS)** to calculate the maximum number of LEDs that can be powered and displays the results in a simple JavaFX interface.

---

## 📁 Project Structure

```text
Original-Version/
└─ src/
   ├─ algorithmFirstProj/
   │  └─ LCS.java
   ├─ cont/
   │  ├─ PowerLT10Control.java
   │  ├─ PLT20Control.java
   │  └─ TableControl.java
   └─ pres/
      ├─ OpeningScene.java
      ├─ PB10to20Scene.java
      └─ PLT102Scene.java
````

---

## 🧮 Features

* Computes the maximum number of LEDs that can be powered using **LCS**.
* Visualizes LED-power connections for ≤10 and 10–20 power sources.
* Shows the DP table for educational purposes.
* Simple functional interface (no advanced styling).

---

## ⚙️ How to Run

1. Open the project in **Eclipse**, **IntelliJ**, or **VS Code** with JavaFX support.
2. Run `OpeningScene.java`.
3. Load an input file (e.g., `../data/input_example.txt`).
4. Explore the simulation and DP table.

---

## 🧠 Algorithm

* **Longest Common Subsequence (LCS)** – finds the optimal LED-power matches.
* **2D Dynamic Programming Table (`C[i][j]`)** – stores intermediate computation results.

---

## 🧑‍💻 Author

**Abdallah Aabed**
💻 [GitHub Profile](https://github.com/abdallahabed)

---

## 📄 License

MIT License – free to use, modify, and distribute.




