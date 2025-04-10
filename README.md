# AI Chatbot with JavaFX

A simple AI chatbot application built with JavaFX and Gemini AI.

## Setup Instructions

### Prerequisites
- Java 17 or higher
- JavaFX SDK
- Gemini API key

### Configuration

1. **Set your Gemini API key** using one of these methods:
   - Set an environment variable: `GEMINI_API_KEY=your_api_key_here`
   - Edit the `config.properties` file and replace `your_api_key_here` with your actual API key

2. **JavaFX Configuration**
   - Ensure JavaFX SDK is properly installed and configured in your IDE
   - The application is configured to use JavaFX SDK 24

### Running the Application

1. Compile the application:
   ```
   javac -cp lib/jackson-core-2.15.2.jar:lib/jackson-databind-2.15.2.jar:lib/jackson-annotations-2.15.2.jar:lib/javafx-sdk-21.0.2/lib/javafx.controls.jar:lib/javafx-sdk-21.0.2/lib/javafx.fxml.jar:lib/javafx-sdk-21.0.2/lib/javafx.graphics.jar:lib/javafx-sdk-21.0.2/lib/javafx.base.jar -d bin src/App.java src/controllers/ChatController.java src/config/Config.java
   ```

2. Run the application with VM arguments:
   ```
   java --module-path lib/javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.base -Djava.library.path=lib/javafx-sdk-21.0.2/lib -cp bin:lib/jackson-core-2.15.2.jar:lib/jackson-databind-2.15.2.jar:lib/jackson-annotations-2.15.2.jar App
   ```

## Features

- Simple chat interface built with JavaFX
- Integration with Gemini AI
- Asynchronous message processing

## Troubleshooting

- If you see a 401 error with "Incorrect API key provided", check that your API key is correctly set in either the environment variable or the config.properties file
- Make sure all required JAR files are in the classpath when compiling and running
