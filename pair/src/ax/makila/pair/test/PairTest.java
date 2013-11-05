package ax.makila.pair.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ax.makila.pair.Pair;

public class PairTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHashCode() {
		Pair<String, String> pair = new Pair<String, String>("A", "B");
		Pair<String, String> pair2 = new Pair<String, String>("B", "A");
		Pair<String, String> pair3 = new Pair<String, String>("A", "C");
		Pair<Integer, Integer> pair4 = new Pair<Integer, Integer>(1, 2);
		
		assertTrue(pair.hashCode() == pair2.hashCode());
		assertFalse(pair.hashCode() == pair3.hashCode());
		assertFalse(pair.hashCode() == pair4.hashCode());
	}

	@Test
	public void testPair() {
		Pair<String, String> pair = new Pair<String, String>("A", "B");
		assertTrue(pair.x.equals("A"));
		assertTrue(pair.y.equals("B"));
	}

	@Test
	public void testContains() {
		Pair<String, String> pair = new Pair<String, String>("A", "B");
		
		assertTrue(pair.contains("A"));
		assertTrue(pair.contains("B"));
		assertFalse(pair.contains("C"));
		assertFalse(pair.contains(null));
		assertFalse(pair.contains("a"));
		
		
		Pair<Integer, Integer> pair2 = new Pair<Integer, Integer>(1, 2);
		
		assertTrue(pair2.contains(1));
		assertTrue(pair2.contains(2));
		assertFalse(pair2.contains(3));
		assertFalse(pair2.contains(-1));
		
		Pair<Pair<String, String>,Pair<Integer, Integer>> pair3 = new Pair<Pair<String,String>, Pair<Integer,Integer>>(pair, pair2);
		
		assertTrue(pair3.contains(pair));
		assertTrue(pair3.contains(pair2));
		assertTrue(pair3.contains(pair2.shuffle()));
		
		Pair<Integer, Integer> pair4 = new Pair<Integer, Integer>(1, 3);
		assertFalse(pair3.contains(pair4));	
	}
	
	@Test
	public void testContainsIgnoreCase() {
		Pair<String, String> pair = new Pair<String, String>("A", "B");
		
		assertTrue(pair.containsIgnoreCase("a"));
		assertTrue(pair.containsIgnoreCase("b"));
		assertFalse(pair.containsIgnoreCase("c"));
		assertTrue(pair.containsIgnoreCase("A"));
		assertFalse(pair.containsIgnoreCase(null));
	}

	@Test
	public void testEqualsObject() {
		Pair<String, String> pair = new Pair<String, String>("A", "B");
		Pair<String, String> pair2 = new Pair<String, String>("B", "A");
		assertTrue(pair.equals(pair2));
		Pair<String, String> pair3 = new Pair<String, String>("A", "C");
		assertFalse(pair.equals(pair3));
		Pair<String, String> lower = new Pair<String, String>("a", "b");
		assertFalse(pair.equals(lower));
		
		Pair<String, Integer> pair4 = new Pair<String, Integer>("A", 1);
		Pair<Integer, String> pair5 = new Pair<Integer, String>(1, "A");
		assertTrue(pair4.equals(pair5));
		Pair<String, Integer> pair6 = new Pair<String, Integer>("B", 1);
		assertFalse(pair4.equals(pair6));
		Pair<Integer, String> pair7 = new Pair<Integer, String>(2, "A");
		assertFalse(pair4.equals(pair7));
		
		
		Pair<Pair<Pair<String, String>, String>, Pair<String, String>> pair8 = 
				new Pair<Pair<Pair<String,String>,String>, Pair<String,String>>(
						new Pair<Pair<String, String>, String>(pair, "A"), pair);
		
		
		Pair<Pair<String, String>, Pair<Pair<String, String>, String>> pair9 = 
				new Pair<Pair<String,String>, Pair<Pair<String,String>,String>>(
						pair, new Pair<Pair<String, String>, String>(pair, "A"));
		
		assertTrue(pair8.equals(pair9));
		assertFalse(pair8.equals(null));
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Pair nullPair = new Pair(null, 2);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Pair nullPair2 = new Pair(2, null);
		
		assertFalse(pair.equals(nullPair));
		assertFalse(nullPair.equals(nullPair2));
		
	}
	
	@Test
	public void testEqualsIgnoreCase() {
		Pair<String, String> pair = new Pair<String, String>("TRIAL", "ERROR");
		Pair<String, String> pairLower = new Pair<String, String>("trial", "error");
		Pair<String, String> pairLower2 = new Pair<String, String>("error", "trial");
		Pair<String, String> pairLower3 = new Pair<String, String>("ERROR", "trial");
		
		assertTrue(pair.equalsIgnoreCase(pairLower));
		assertTrue(pair.equalsIgnoreCase(pairLower2));
		assertTrue(pair.equalsIgnoreCase(pairLower3));
		assertFalse(pair.equals(pairLower));
		
		Pair<String, Integer> test = new Pair<String, Integer>("TRIAL", 1);
		Pair<String, Integer> test2 = new Pair<String, Integer>("trial", 1);
		Pair<Integer, String> test3 = new Pair<Integer, String>(1, "trial");
		Pair<Integer, String> test4 = new Pair<Integer, String>(1, "error");
		
		assertTrue(test.equalsIgnoreCase(test2));
		assertTrue(test.equalsIgnoreCase(test3));
		assertFalse(test.equalsIgnoreCase(test4));
		assertFalse(test.equals(test2));
		
		Pair<Integer, Integer> intTest = new Pair<Integer, Integer>(1, 2);
		Pair<Integer, Integer> intTest2 = new Pair<Integer, Integer>(2, 1);
		
		assertTrue(intTest.equalsIgnoreCase(intTest2));
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Pair nullPair = new Pair(null, 2);
		
		assertFalse(pair.equalsIgnoreCase(nullPair));
	}

	@Test
	public void testShuffle() {
		Pair<String, String> pair = new Pair<String, String>("A", "B");
		Pair<String, String> shuffle = pair.shuffle();
		
		assertTrue(pair.x.equals(shuffle.y));
		assertTrue(pair.y.equals(shuffle.x));
	}

	@Test
	public void testToString() {
		String expected = "<A, B>";
		Pair<String, String> pair = new Pair<String, String>("A", "B");
		assertTrue(pair.toString().equals(expected));
	}

}
