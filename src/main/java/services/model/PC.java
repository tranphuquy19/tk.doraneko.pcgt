package services.model;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class PC {
    private GT gt1;
    private  GT gt2;
    private PT pt;

    public PC(GT gt1, GT gt2, PT pt) {
        this.gt1 = gt1;
        this.gt2 = gt2;
        this.pt = pt;
    }

    public GT getGt1() {
        return gt1;
    }

    public void setGt1(GT gt1) {
        this.gt1 = gt1;
    }

    public GT getGt2() {
        return gt2;
    }

    public void setGt2(GT gt2) {
        this.gt2 = gt2;
    }

    public PT getPt() {
        return pt;
    }

    public void setPt(PT pt) {
        this.pt = pt;
    }
}
