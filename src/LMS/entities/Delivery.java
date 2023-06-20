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
public abstract class Delivery

		

{

	

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
