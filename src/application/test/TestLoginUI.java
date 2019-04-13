package application.test;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import application.LoginUI;
import application.model.Message;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class TestLoginUI extends ApplicationTest {
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		AnchorPane home = (AnchorPane) FXMLLoader.load((LoginUI.class.getResource("view/Login.fxml")));

		primaryStage.setTitle("Đăng nhập");

		primaryStage.setScene(new Scene(home));

		primaryStage.show();
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		FxToolkit.hideStage();
		release(new KeyCode[] {});
		release(new MouseButton[] {});
	}
	
	public void testUI(String username, String password, String expectedHeader, String expectedContent) {
		if (!username.equals("")) {
			clickOn("#txtUsername");
			write(username);
		}

		if (!password.equals("")) {
			clickOn("#txtPassword");
			write(password);
		}

		clickOn("#btnLogin");

		Stage actualAlertDialog = getTopModalStage();
		DialogPane dialogPane = (DialogPane) actualAlertDialog.getScene().getRoot();

		Assert.assertEquals(expectedHeader, dialogPane.getHeaderText());
		Assert.assertEquals(expectedContent, dialogPane.getContentText());
	}

	@Test
	public void testLoginSuccess() {
		testUI("chinhvu", "123", Message.loginSuccessMsg, Message.helloMsg + "chinhvu");
	}

	@Test
	public void testLoginFail1() {
		testUI("", "", Message.loginFailMsg, Message.emptyUsernamePasswordMsg);
	}

	@Test
	public void testLoginFail2() {
		testUI("chinhvu", "", Message.loginFailMsg, Message.emptyUsernamePasswordMsg);
	}

	@Test
	public void testLoginFail3() {
		testUI("", "1234", Message.loginFailMsg, Message.emptyUsernamePasswordMsg);
	}

	@Test
	public void testLoginFail4() {
		testUI("chinhvu", "1234", Message.loginFailMsg, Message.wrongUsernamePasswordMsg);
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
