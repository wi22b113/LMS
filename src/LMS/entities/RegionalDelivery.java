package LMS.entities;

public class RegionalDelivery extends Delivery {

    private boolean express;

    public RegionalDelivery(long id, String from, String to) {
        super(id, from, to);
    }
    public RegionalDelivery(long id, String from, String to, boolean express) {
        super(id, from, to);
        this.express = express;
    }

    @Override
    public int getTotal() {
        int total = 0;
        float mass = 0.0F;
        if (getGoods() != null){
            for (Item i : getGoods()){
               mass = i.totalMass();
               for (float j = mass; j < 1; j--){
                   total += 100;
               }
            }
        if (express){
            total *= 1.2;
            return total ;
        }else {
            return total;
        }
        }
        return 0;
    }

}
