package LMS.entities;

public class InterationalDelivery extends Delivery {

    private float custom;

    public InterationalDelivery(long id, String from, String to) {
        super(id, from, to);
    }

    public InterationalDelivery(long id, String from, String to, float custom) {
        super(id, from, to);
        if (custom >= 1) {
            this.custom = custom;
        }else this.custom = 1;
    }
    @Override
    public int getTotal() {
        int total = 0;
        if (getGoods() != null){
            for (Item i : getGoods()){
                total += i.totalValue();
            }
            return total;
        }
        return -1;
    }
}
