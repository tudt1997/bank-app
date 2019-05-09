package application.model;

public class PersonalDetails {
    private String hotenText, taikhoanText, diachiText, coquanText, gioitinhText, hochieuText, cmndText, ngaysinhText, dienthoaiText;

    public void print(){
        System.out.println(hotenText);
        System.out.println(taikhoanText);
        System.out.println(diachiText);
        System.out.println(coquanText);
        System.out.println(gioitinhText);
        System.out.println(hochieuText);
        System.out.println(cmndText);
        System.out.println(ngaysinhText);
        System.out.println(dienthoaiText);
    }

    public void setHotenText(String hotenText) {
        this.hotenText = hotenText;
    }

    public void setTaikhoanText(String taikhoanText) {
        this.taikhoanText = taikhoanText;
    }

    public void setDiachiText(String diachiText) {
        this.diachiText = diachiText;
    }

    public void setCoquanText(String coquanText) {
        this.coquanText = coquanText;
    }

    public void setGioitinhText(String gioitinhText) {
        this.gioitinhText = gioitinhText;
    }

    public void setHochieuText(String hochieuText) {
        this.hochieuText = hochieuText;
    }

    public void setCmndText(String cmndText) {
        this.cmndText = cmndText;
    }

    public void setNgaysinhText(String ngaysinhText) {
        this.ngaysinhText = ngaysinhText;
    }

    public void setDienthoaiText(String dienthoaiText) {
        this.dienthoaiText = dienthoaiText;
    }

    public String getHotenText() {
        return hotenText;
    }

    public String getTaikhoanText() {
        return taikhoanText;
    }

    public String getDiachiText() {
        return diachiText;
    }

    public String getCoquanText() {
        return coquanText;
    }

    public String getGioitinhText() {
        return gioitinhText;
    }

    public String getHochieuText() {
        return hochieuText;
    }

    public String getCmndText() {
        return cmndText;
    }

    public String getNgaysinhText() {
        return ngaysinhText;
    }

    public String getDienthoaiText() {
        return dienthoaiText;
    }
}
