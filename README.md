# SwitchButton Showcase

A demo application showcasing the proposed `SwitchButton` control for JavaFX.

![Showcase Screenshot](screens/screenshot.png)

## About

This project demonstrates a new `SwitchButton` control proposed for inclusion in JavaFX core. The showcase includes:

- **JavaFX Themes**: Modena and Caspian built-in styles
- **Platform Styles**: iOS, Material Design, Fluent (Windows), macOS
- **Creative Styles**: Theme toggle, Neon glow, Minimal, Square
- **Interactive Demo**: Real-world usage examples

## Requirements

- JDK 24+
- Gradle 8.x

## Quick Start

```bash
# Clone the repository
git clone https://github.com/leewyatt/SwitchButtonShowcase.git
cd SwitchButtonShowcase

# Run the application
./gradlew run
```

On Windows:
```cmd
.\gradlew.bat run
```

## Project Structure

```
SwitchButtonShowcase/
├── libs/
│   ├── win/                    # Windows: JAR + DLL files
│   └── mac/                    # macOS: JAR + dylib files
├── screens/
│   └── screenshot.png          # Showcase screenshot
├── src/main/java/com/test/
│   └── SwitchButtonShowcase.java
├── src/main/resources/com/test/
│   └── showcase.css
├── build.gradle.kts
└── README.md
```

## Building JavaFX from Source

This project uses a locally built JavaFX with the `SwitchButton` control. To build it yourself:

```bash
# Clone the OpenJFX fork with SwitchButton
git clone https://github.com/leewyatt/jfx.git
cd jfx

# Switch to the feature branch
git checkout add-switch-button

# Build (requires JDK 24+)
./gradlew sdk

# Copy the built files to this project's libs folder
cp -r build/sdk/lib/* ../SwitchButtonShowcase/libs/<platform>/
```

## Related Links

- [SwitchButton Showcase](https://github.com/leewyatt/SwitchButtonShowcase)
- [OpenJFX Fork (SwitchButton branch)](https://github.com/leewyatt/jfx/tree/add-switch-button)
- [OpenJFX](https://github.com/openjdk/jfx)

## License

This project is for demonstration purposes as part of a JavaFX enhancement proposal.