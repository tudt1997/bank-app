package application.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.model.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import application.LoanRegistration;
import application.controller.LoanRegistrationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextFlowMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;
import org.testfx.matcher.control.TextMatchers;

public class TestRegistrationUI extends ApplicationTest {
	private LoanRegistrationController loanRegistrationController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader fxmlLoader = new FXMLLoader(LoanRegistration.class.getResource("view/LoanRegistration.fxml"));

		AnchorPane home = (AnchorPane) fxmlLoader.load();

		loanRegistrationController = fxmlLoader.getController();

		primaryStage.setTitle("Đăng ký vay");

		primaryStage.setScene(new Scene(home));

		primaryStage.show();
	}

	@After
	public void tearDown() throws Exception {
		FxToolkit.hideStage();
		release(new KeyCode[] {});
		release(new MouseButton[] {});
	}

	private void doSearch(String idCard) {

		if (!idCard.equals("")) {
			clickOn("#txtIdCard");
			write(idCard);
		}

		clickOn("#btnSearch");
	}

	private void checkDialogFail() {
		Stage actualAlertDialog = getTopModalStage();
		DialogPane dialogPane = (DialogPane) actualAlertDialog.getScene().getRoot();

		Assert.assertEquals(Message.INVALID_PERSON_ID, dialogPane.getHeaderText());
		Assert.assertEquals(Message.ENTER_AGAIN, dialogPane.getContentText());
	}
	@Test
	public void testTab1Success() {
		doSearch("123456");
		FxAssert.verifyThat("#txtName", TextInputControlMatchers.hasText("Nguyễn Đức Anh"));
	}

	@Test
	public void testTab1Fail1() {
		doSearch("");
		checkDialogFail();
	}

	@Test
	public void testTab1Fail2() {
		doSearch("a");
		checkDialogFail();
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
