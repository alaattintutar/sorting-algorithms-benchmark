<div align="center">
  
# 📊 Sorting Algorithm Benchmarks

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Algorithms](https://img.shields.io/badge/Data%20Structures-Sorting-purple?style=for-the-badge)
![Data Analysis](https://img.shields.io/badge/Analysis-Time%20Complexity-teal?style=for-the-badge)

*An empirical performance analysis of classical sorting algorithms across various data distribution states using real market datasets.*

</div>

## 📌 Overview
This project benchmarks and visualizes the runtime complexities of 5 fundamental sorting algorithms (`Quick Sort`, `Insertion Sort`, `Merge Sort`, `Shell Sort`, and `Radix Sort`). It ingests financial stock volume statistics (`all_stocks_5yr.csv`) and evaluates the latency and scalability of each algorithm under three strict array environments: Random, Fully Sorted, and Reversely Sorted distributions.

## ✨ Core Features
- ⏱️ **Extensive Benchmarking:** Measures sort execution time from array sizes `500` up to `250,000`, smoothly averaging the times across 10 simulation runs natively.
- 🧮 **Algorithm Diversity:** Showcases varying Big-O bounds between quadratic algorithms (Insertion), divide-and-conquer logic (Merge, Quick), and specialized methodologies (Radix, Shell).
- 📈 **GUI Visualizations:** Leverages the `XChart` graphics library to render an automated analytical Line Chart directly showcasing algorithm efficiency curves on completion.
- 📁 **Real World I/O:** Parses and cleanses raw market sequences dynamically to simulate true-scale computational limits.

## ⚙️ Technical Architecture
- **Language Stack:** Java 8+
- **Suite Engine:** A modular test driver (`Main.java`) cleanly imports, deep-copies, reverses, and delegates arrays into pure OOP algorithm instances dynamically.
- **Dependencies:** Requires the `org.knowm.xchart` library implementation to generate the swing charts dynamically.

## 🚀 Getting Started
Ensure the CSV dataset (`all_stocks_5yr.csv`) and `xchart` dependency are within your classpath.

```bash
# Compile the components with XChart
javac -cp "path/to/xchart.jar:." *.java

# Execute the benchmarking suite
java -cp "path/to/xchart.jar:." Main
```
