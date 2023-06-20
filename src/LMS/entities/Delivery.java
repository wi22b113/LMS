package LMS.entities;

import java.util.HashSet;
import java.util.Set;

import LMS.provided.*;

/**
 * A delivery within the Logistics Management System.<br>
 * <br>
 * 
 * A delivery collects goods at a certain time and place and after transport
 * delivers them at another time and place. A delivery is transported by a
 * specific carrier and identified by a unique id.<br>
 * <br>
 * 
 * The usual life cycle is
 * <ul>
 * <li>create a delivery with id, pick up and drop off location</li>
 * <li>add goods</li>
 * <li>assign a carrier</li>
 * <li>collect</li>
 * <li>deliver</li>
 * </ul>
 *
 */
public abstract class Delivery implements Comparable<Delivery>{

	private Vehicle carrier;
	private DateTime collected;
	private DateTime delivered;
	private String from;
	private Set<Item> goods = new HashSet<>();

	private long id;
	private String to;

	public Delivery(long id, String from, String to){
		if (id > 0 && ensureNonNullNonEmpty(from) != null && ensureNonNullNonEmpty(to) != null){
			this.id = id;
			this.from = from;
			this.to = to;
		}else{
			throw new IllegalArgumentException();
		}
	}

	public abstract int getTotal();

	protected final float totalMass(){
		float totalMass = 0;
		for ( Item i : goods ){
			totalMass += i.totalMass();
		}
		return totalMass;
	}

	public boolean isCollected(){
		return collected != null;
	}

	public boolean isDelivered(){
		return delivered != null;
	}

	private boolean isAssigned(){
		return carrier != null;
	}

	public final boolean addGoods(Item item){
		if (!isAssigned() && !isCollected() && !isDelivered()){
			return goods.add(item);
		}
		return false;
	}

	public final boolean addGoods(Iterable<Item> items){
		if (!isAssigned() && !isCollected() && !isDelivered()) {
			for (Item i : items) {
				if (!goods.contains(i)){
					return addGoods(i);
				}
			}
		}
		return false;
	}

	public Set<Item> getGoods() {
		return goods = new HashSet<>(goods);
	}

	public final void assignCarrier(Vehicle v){
		if (v != null && totalMass() < v.getMaxLoad()) {
			carrier = v;
		}
	}

	public final boolean collect(DateTime toc){
		if (!isCollected() && carrier != null && goods != null){
			collected = new DateTime(toc);
			return true;
		}
		return false;
	}

	public final boolean deliver(DateTime tod){
		if (isCollected() && !isDelivered()){
			delivered = new DateTime(tod);
			return true;
		}
		return false;
	}

	public int compareTo(Delivery arg0){
		return Long.compare(id, arg0.id);
	}

	private final String ensureNonNullNonEmpty(String str){
		if (str != null && !str.isEmpty()){
			return str;
		}else {
			throw new IllegalArgumentException();
		}
	}

	

	/**
	 * creates a string representation of this delivery.<br>
	 * 
	 * @ProgrammingProblem.Hint provided
	 * 
	 */
	@Override
	public String toString() {
		return String.format(
				"%d from \"%10.10s\" to \"%10.10s\" " + "[%scollected, %sdelivered] (%.2fkg, EUR %.2f)\n" + "%s", id,
				from, to, isCollected() ? "" : "not ", isDelivered() ? "" : "not ", totalMass(), getTotal() / 100.,
				goods == null ? "no stock" : goods);
	}

}
