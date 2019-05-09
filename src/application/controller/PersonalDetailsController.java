package application.controller;

import application.model.PersonalDetails;
import application.model.SearchResult;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PersonalDetailsController implements Initializable{
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    private TextField hotenText, taikhoanText, diachiText, coquanText, gioitinhText, hochieuText, cmndText, ngaysinhText, dienthoaiText;

    public PersonalDetailsController(){
    }

    public void setTextDetails(SearchResult sr){
//        hotenText.setEditable(false);
//        taikhoanText.setEditable(false);
//        diachiText.setEditable(false);
//        coquanText.setEditable(false);
//        gioitinhText.setEditable(false);
//        hochieuText.setEditable(false);
//        cmndText.setEditable(false);
//        ngaysinhText.setEditable(false);
//        dienthoaiText.setEditable(false);

        try {
            PersonalDetails pd = Providers.getPersonalDetails(sr);
            pd.print();

            hotenText.setText(pd.getHotenText());
            taikhoanText.setText(pd.getTaikhoanText());
            diachiText.setText(pd.getDiachiText());
            coquanText.setText(pd.getCoquanText());
            gioitinhText.setText(pd.getGioitinhText());
            hochieuText.setText(pd.getHochieuText());
            cmndText.setText(pd.getCmndText());
            ngaysinhText.setText(pd.getNgaysinhText());
            dienthoaiText.setText(pd.getDienthoaiText());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void okBtn(){
//        System.out.println("clickkkk");
//    }
}
