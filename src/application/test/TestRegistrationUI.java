//package application.test;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Test;
//import org.junit.Assert.*;
//import org.testfx.api.FxToolkit;
//import org.testfx.framework.junit.ApplicationTest;
//
//import application.LoginUI;
//import application.Registration;
//import application.controller.LoginController;
//import application.controller.RegisterControll;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.DialogPane;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.MouseButton;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.Window;
//
//public class TestRegistrationUI extends ApplicationTest {
//	RegisterControll registerControll;
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		// TODO Auto-generated method stub
//		FXMLLoader fxmlLoader = new FXMLLoader(Registration.class.getResource("view/LoanRegistration.fxml"));
//
//		AnchorPane home = (AnchorPane) fxmlLoader.load();
//
//		registerControll = fxmlLoader.getController();
//
//		primaryStage.setTitle("Đăng ký vay");
//
//		primaryStage.setScene(new Scene(home));
//
//		primaryStage.show();
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		FxToolkit.hideStage();
//		release(new KeyCode[] {});
//		release(new MouseButton[] {});
//	}
//
//	public void testTab1(String name, String gender, String idCard,
//			Date birthday, String addressPerson, String phoneNumber) {
//		if (!name.equals("")) {
//			clickOn("#txtName");
//			write(name);
//		}
//
//		if (true) {
//			clickOn("#cbGender");
//
//		}
//
//		if (!idCard.equals("")) {
//			clickOn("#txtIdCard");
//			write(idCard);
//		}
//
//		if (!addressPerson.equals("")) {
//			clickOn("#txtAddressPerson");
//			write(addressPerson);
//		}
//
//		clickOn("#dpBirthday");
//
//		if (!phoneNumber.equals("")) {
//			clickOn("#txtPhoneNumber");
//			write(phoneNumber);
//		}
//
//		clickOn("#btnNext");
//	}
//
//	@Test
//	public void testTab1Success() {
//		testTab1("Nguyễn Đức Anh", "Male", "123456", new Date(119, 2, 11), "42 Trần Phú Hà Đông Hà Nội", "123456789");
//
//		assertFalse(registerControll.getBtnPrev().isDisable());
//		assertFalse(registerControll.getBtnNext().isDisable());
//		assertTrue(registerControll.getBtnFinish().isDisable());
//
//		assertTrue(registerControll.getTp1().isDisable());
//		assertFalse(registerControll.getTp2().isDisable());
//		assertTrue(registerControll.getTp3().isDisable());
//	}
//
//	@Test
//	public void testTab1Fail() {
//		testTab1("", "", "", new Date(119, 2, 11), "", "");
//
//		Stage actualAlertDialog = getTopModalStage();
//		DialogPane dialogPane = (DialogPane) actualAlertDialog.getScene().getRoot();
//
//		assertTrue(registerControll.getBtnPrev().isDisable());
//		assertFalse(registerControll.getBtnNext().isDisable());
//		assertTrue(registerControll.getBtnFinish().isDisable());
//
//		assertFalse(registerControll.getTp1().isDisable());
//		assertTrue(registerControll.getTp2().isDisable());
//		assertTrue(registerControll.getTp3().isDisable());
//	}
//
//	private javafx.stage.Stage getTopModalStage() {
//		// Get a list of windows but ordered from top[0] to bottom[n] ones.
//		// It is needed to get the first found modal window.
//		final List<Window> allWindows = new ArrayList<>(robotContext().getWindowFinder().listWindows());
//		Collections.reverse(allWindows);
//
//		return (javafx.stage.Stage) allWindows.stream().filter(window -> window instanceof javafx.stage.Stage)
//				.filter(window -> ((javafx.stage.Stage) window).getModality() == Modality.APPLICATION_MODAL).findFirst()
//				.orElse(null);
//	}
//}
