package cont;

import algorithmFirstProj.LCS;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pres.PB10to20Scene;

/*
 * Controller for when the power sources are more than 10 and less than or equal to 20.
 * Shows the DP table and the LCS solution.
 */
public class PLT20Control {
    // fields
    private PB10to20Scene powerMT10LT20Scene;
    private Label powerl;
    private Label maxl;
    private Label lcss;
    private Button btCancel;
    private Button show;

    public PLT20Control(Stage stage, LCS sol) {

        // ✅ Create and link scene
        setPowerMT10LT20Scene(new PB10to20Scene(stage));

        // ✅ Link scene elements to controller fields
        setBtCancel(powerMT10LT20Scene.getBtCancel());
        setLcss(powerMT10LT20Scene.getLcss());
        setMaxl(powerMT10LT20Scene.getMaxl());
        setPowerl(powerMT10LT20Scene.getPowerl());
        setShow(powerMT10LT20Scene.getShow());

        // ✅ Button handlers
        handleCancel(btCancel, stage);
        handleShow(getShow(), sol);

        // ✅ Fix DP index (matches updated LCS)
        getMaxl().setText("Maximum LEDs powered: " +
                sol.getC()[sol.getPowers().length][sol.getLeds().length]);

        getPowerl().setText("You have " + sol.getPower() + " Power Sources");

        // ✅ Build sequence string
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < sol.getLcss().length; i++) {
            text.append(sol.getLcss()[i]);
            if (i < sol.getLcss().length - 1) {
                text.append(", ");
            }
        }
        getLcss().setText(text.toString());
    }

    private void handleShow(Button table, LCS sol) {
        table.setOnAction(e -> new TableControl(sol.getLeds(), sol.getPowers()));
    }

    private void handleCancel(Button cancButton, Stage stage) {
        cancButton.setOnAction(e -> stage.close());
    }

    /*
     * Getters and Setters
     */
    public Label getPowerl() {
        return powerl;
    }

    public void setPowerl(Label powerl) {
        this.powerl = powerl;
    }

    public Label getMaxl() {
        return maxl;
    }

    public void setMaxl(Label maxl) {
        this.maxl = maxl;
    }

    public Label getLcss() {
        return lcss;
    }

    public void setLcss(Label lcss) {
        this.lcss = lcss;
    }

    public Button getBtCancel() {
        return btCancel;
    }

    public void setBtCancel(Button btCancel) {
        this.btCancel = btCancel;
    }

    public Button getShow() {
        return show;
    }

    public void setShow(Button show) {
        this.show = show;
    }

    public PB10to20Scene getPowerMT10LT20Scene() {
        return powerMT10LT20Scene;
    }

    public void setPowerMT10LT20Scene(PB10to20Scene powerMT10LT20Scene) {
        this.powerMT10LT20Scene = powerMT10LT20Scene;
    }
}
