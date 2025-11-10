package cont;

import algorithmFirstProj.LCS;
import pres.PLT102Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PowerLT10Control {

    private Label maxl;
    private Button show;
    private Button connect;
    private Button disconnect;
    private Button cancel;
    private PLT102Scene scene;

    public PowerLT10Control(Stage stage, LCS sol) {
        // Initialize scene and UI components
        scene = new PLT102Scene(stage, sol.getLeds(), sol.getPowers());
        maxl = scene.getMaxl();
        show = scene.getShow();
        connect = scene.getConnect();
        disconnect = scene.getDisconnect();
        cancel = scene.getCancel();

        // Attach handlers
        handleCancel(cancel, stage);
        handleConnect(connect, sol);
        handleDisconnect(disconnect, sol);
        handleShow(show, sol.getLeds(), sol.getPowers());
    }

    private void handleShow(Button showBtn, int[] leds, int[] powers) {
        showBtn.setOnAction(e -> new TableControl(leds, powers));
    }

    private void handleDisconnect(Button disconnectBtn, LCS sol) {
        disconnectBtn.setOnAction(e -> {
            Image off = new Image(getClass().getResource("/images/off.png").toExternalForm());
            Image offLamb = new Image(getClass().getResource("/images/offlamb.jpg").toExternalForm());

            for (Button bat : scene.getBatts()) {
                ((ImageView) bat.getGraphic()).setImage(off);
            }

            for (Button led : scene.getLedds()) {
                ((ImageView) led.getGraphic()).setImage(offLamb);
            }
        });
    }

    private void handleConnect(Button connectBtn, LCS sol) {
        System.out.println(getClass().getResource("/images/onlamb.jpg"));
        System.out.println(getClass().getResource("/images/on.png"));

        connectBtn.setOnAction(e -> {
            Image on = new Image(getClass().getResource("/images/on.png").toExternalForm());
            Image onLamb = new Image(getClass().getResource("/images/onlamb.jpg").toExternalForm());

            
            
            int[] powers = sol.getPowers();
            int[] leds = sol.getLeds();
            String[] lcss = sol.getLcss();

            for (String valStr : lcss) {
                int val = Integer.parseInt(valStr);

                // Match battery index
                for (int i = 0; i < powers.length; i++) {
                    if (powers[i] == val) {
                        ((ImageView) scene.getBatts().get(i).getGraphic()).setImage(on);
                    }
                }

                // Match LED index
                for (int i = 0; i < leds.length; i++) {
                    if (leds[i] == val) {
                        ((ImageView) scene.getLedds().get(i).getGraphic()).setImage(onLamb);
                    }
                }
            }
        });
    }

    private void handleCancel(Button cancelBtn, Stage stage) {
        cancelBtn.setOnAction(e -> stage.close());
    }

    // Getters
    public Label getMaxl() { return maxl; }
    public Button getShow() { return show; }
    public Button getConnect() { return connect; }
    public Button getDisconnect() { return disconnect; }
    public Button getCancel() { return cancel; }
    public PLT102Scene getScene() { return scene; }
}
