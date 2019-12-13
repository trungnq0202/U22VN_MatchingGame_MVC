import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML
    private MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/view_fxml/main.fxml"));
        primaryStage.setTitle("Matching those U22 Vietnam bitches");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
//import javafx.animation.*;
//import javafx.application.Application;
//import javafx.scene.*;
//import javafx.scene.image.*;
//import javafx.scene.layout.StackPane;
//import javafx.scene.transform.Rotate;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//public class Main extends Application {
//    @Override
//    public void start(Stage stage) throws Exception {
//        Node card = createCard();
//
//        stage.setScene(createScene(card));
//        stage.show();
//
//        RotateTransition rotator = createRotator(card);
//        rotator.play();
//    }
//
//    private Scene createScene(Node card) {
//        StackPane root = new StackPane();
//        root.getChildren().addAll(card);
//
//        Scene scene = new Scene(root, 600, 700, true, SceneAntialiasing.BALANCED);
//        scene.setCamera(new PerspectiveCamera());
//
//        return scene;
//    }
//
//    private Node createCard() {
//        return new ImageView(
//                new Image(
//                        "http://www.ohmz.net/wp-content/uploads/2012/05/Game-of-Throne-Magic-trading-cards-2.jpg"
//                )
//        );
//    }
//
//    private RotateTransition createRotator(Node card) {
//        RotateTransition rotator = new RotateTransition(Duration.millis(1000), card);
//        rotator.setAxis(Rotate.Y_AXIS);
//        rotator.setFromAngle(0);
//        rotator.setToAngle(360);
//        rotator.setInterpolator(Interpolator.LINEAR);
//        rotator.setCycleCount(10);
//
//        return rotator;
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}