package application.test;

import application.SearchForPayment;
import application.model.Message;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSearchUI extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        FXMLLoader fxmlLoader = new FXMLLoader(SearchForPayment.class.getResource("view/SearchForPayment.fxml"));

        BorderPane home = fxmlLoader.load();

        primaryStage.setTitle("Tìm khoản vay");

        primaryStage.setScene(new Scene(home));

        primaryStage.show();
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[] {});
        release(new MouseButton[] {});
    }

    public DialogPane doSearch(int type, String keyWord) {
        if (type == 1) {
            clickOn("#cbType");
        }

        if (!keyWord.equals("")) {
            clickOn("#searchKeyWord");
            write(keyWord);
        }

        clickOn("#btnSearch");

        Stage actualAlertDialog = getTopModalStage();
        if (actualAlertDialog == null)
            return null;

        return (DialogPane) actualAlertDialog.getScene().getRoot();
    }

    @Test
    public void testSearchSuccess() {
        DialogPane dialogPane = doSearch(0, "7");

        Assert.assertNull(dialogPane);
    }


    @Test
    public void testSearchFail1() {
        DialogPane dialogPane = doSearch(0, "");

        Assert.assertNotNull(dialogPane);
//        Assert.assertEquals();
    }


    private javafx.stage.Stage getTopModalStage() {
        // Get a list of windows but ordered from top[0] to bottom[n] ones.
        // It is needed to get the first found modal window.
        final List<Window> allWindows = new ArrayList<>(robotContext().getWindowFinder().listWindows());
        Collections.reverse(allWindows);

        return (javafx.stage.Stage) allWindows.stream().filter(window -> window instanceof javafx.stage.Stage)
                .filter(window -> ((javafx.stage.Stage) window).getModality() == Modality.APPLICATION_MODAL).findFirst()
                .orElse(null);
    }
}
