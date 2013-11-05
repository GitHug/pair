package ax.makila.pair;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Class for creating pairs. A pair is considered to have two members,
 * <tt>X</tt> and <tt>Y</tt> which can be of any type. The ordering of the
 * parameters are considered irrelevant and pair(X, Y) = pair(Y, X).
 * 
 * @author fredrik
 * 
 * @param <X>
 *            The first member of the pair
 * @param <Y>
 *            The second member of the pair
 */
public class Pair<X, Y> {
	public X x;
	public Y y;

	/**
	 * Constructor. Stores the values <tt>x</tt> and <tt>y</tt> in the object.
	 * 
	 * @param x
	 *            The first member of the new pair
	 * @param y
	 *            The second member of the new pair
	 */
	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Checks if the pair contains <tt>o</tt>. The pair contains <tt>o</tt> if
	 * either the first or the second member of the pair matches o.
	 * 
	 * @param o
	 *            The object to be checked against the members of the pair
	 * @return True if o matches one of the members of the pair, else false.
	 */
	public boolean contains(Object o) {
		if (o == null) {
			return false;
		} else if (o.getClass() == x.getClass() || o.getClass() == y.getClass()) {
			return x.equals(o) || y.equals(o);
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the pair contains <tt>aString</tt>. The pair contains <tt>aString</tt> if
	 * either the first or the second member of the pair matches o while ignoring the case.
	 * 
	 * @param aString
	 *            The String to be checked against the members of the pair
	 * @return True if aString matches one of the members of the pair, else false.
	 */
	public boolean containsIgnoreCase(String aString) {
		boolean boolX = false;
		boolean boolY = false;
		if (aString == null) {
			return false;
		} 
		
		if (x instanceof String) {
			String xs = (String) x;
			
			boolX = aString.equalsIgnoreCase(xs);	
		}
		
		if(y instanceof String) {
			String ys = (String) y;
			
			boolY = aString.equalsIgnoreCase(ys);
		}
		
		return boolX || boolY;
		
			
	}

	/**
	 * Equals method. The ordering of the input doesn't matter. The pair pair(1,
	 * 2) is considered to be equal to pair(2, 1).
	 * 
	 * @param o The object to be compared with this object.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (getClass() != o.getClass()) {
			return false;
		} 

		@SuppressWarnings("unchecked")
		Pair<X, Y> pair = (Pair<X, Y>) o;
		
		if(pair.x == null || pair.y == null || x == null || y == null) {
			return false;
		}

		EqualsBuilder eq0 = new EqualsBuilder();
		EqualsBuilder eq1 = new EqualsBuilder();
				
		eq0.append(x, pair.x);
		eq0.append(y, pair.y);
		
		eq1.append(y, pair.x);
		eq1.append(x, pair.y);
		
		return eq0.isEquals() || eq1.isEquals();

	}
	
	/**
	 * Equals method ignoring the case of the members of <tt>o</tt>. In the case that <tt>o</tt>
	 * doesn't contain any String and/or this object doesn't contain any String, it is treated as
	 * the normal equals method. Here is an example:
	 * 
	 * Pair<String, String> p0 = new Pair<String, String>("A", "B");
	 * Pair<String, String> p1 = new Pair<String, String>("a", "b");
	 * p0.equals(p1) //true
	 * 
	 * @param o The object to be compared with this object
	 * @return true if o is equal to this object while ignoring the case of potential Strings.
	 */
	public boolean equalsIgnoreCase(Object o) {
		if (o == null) {
			return false;
		} else if (getClass() != o.getClass()) {
			return false;
		} 
		
		@SuppressWarnings("unchecked")
		Pair<X, Y> pair = (Pair<X, Y>) o;
		
		if(pair.x == null || pair.y == null || x == null || y == null) {
			return false;
		}
		
		EqualsBuilder eq0 = new EqualsBuilder();
		EqualsBuilder eq1 = new EqualsBuilder();
		
		if(!(x instanceof String || y instanceof String) || 
				!(pair.x instanceof String || pair.y instanceof String)) {
			return equals(pair);
		}
		
		if(x instanceof String) {
			String xs = (String) x;
			if(pair.x instanceof String) {
				String ps = (String) pair.x;
				eq0.append(xs.toLowerCase(), ps.toLowerCase());
			}
			else {
				eq0.append(x, pair.x);
			}
			if(pair.y instanceof String) {
				String ps = (String) pair.y;
				eq1.append(xs.toLowerCase(), ps.toLowerCase());
			} else {
				eq1.append(x, pair.y);
			}
		}
		
		if(y instanceof String) {
			String ys = (String) y;
			if(pair.x instanceof String) {
				String ps = (String) pair.x;
				eq1.append(ys.toLowerCase(), ps.toLowerCase());
				}
			else {
				eq1.append(y, pair.x);
			}
			
			if(pair.y instanceof String) {
				String ps = (String) pair.y;
				eq0.append(ys.toLowerCase(), ps.toLowerCase());
			}
			else {
				eq0.append(y, pair.y);
			}
		}
		return eq0.isEquals() || eq1.isEquals();
	}

	/**
	 * Generates a hashcode for the object.
	 */
	@Override
	public int hashCode() {
		int hash0 = new HashCodeBuilder(17, 31). // two randomly chosen prime
													// numbers
				// if deriving: appendSuper(super.hashCode()).
				append(x).append(y).toHashCode();
		int hash1 = new HashCodeBuilder(17, 31).append(y).append(x)
				.toHashCode();
		return hash0 + hash1;
	}

	public Pair<Y, X> shuffle() {
		return new Pair<Y, X>(y, x);
	}

	/**
	 * To string method. Gives some fancy output.
	 */
	@Override
	public String toString() {
		return "<" + x.toString() + ", " + y.toString() + ">";
	}
}
