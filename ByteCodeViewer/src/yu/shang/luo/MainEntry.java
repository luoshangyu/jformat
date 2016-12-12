package yu.shang.luo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;

public class MainEntry extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("JSON Formater");
		TextArea textArea = new TextArea();
		textArea.setPrefSize(600, 600);
		Button button = new Button("Format JSON");
		button.setMinWidth(50);
		button.setOnAction(action -> {
			Viewer v = new Viewer();
			textArea.setText(v.formatJson(textArea.getText()));
		});
		StackPane root = new StackPane();
		root.getChildren().add(textArea);
		root.getChildren().add(button);
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}