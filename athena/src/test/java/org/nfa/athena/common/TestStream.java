package org.nfa.athena.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.junit.Test;
import org.nfa.athena.model.User;

public class TestStream {

	@Test
	public void testStream() {
		Random ran = new Random();
		Arrays.asList("A", "B", "C").stream().map(genUnary(ran.nextInt())).forEach(genCons(ran.nextInt()));
		Arrays.asList("D", "E", "F").forEach(genCons(ran.nextInt()));
	}

	private UnaryOperator<String> genUnary(int n) {
		return s -> {
			System.out.println(s + "-" + n);
			return s;
		};
	}

	private Consumer<String> genCons(int n) {
		return s -> {
			System.out.println(s + "-" + n);
		};
	}

	@Test
	public void testStreamFunction() {
		List<User> users = new ArrayList<>();
		Stream.of("A01", "A02", "A03").map(process(new User())).forEach(u -> users.add(u));
		Stream.of("B01", "B02", "B03").map(s -> process(new User()).apply(s)).forEach(u -> users.add(u));
		users.forEach(u -> System.out.println(u.getId()));
	}

	private Function<String, User> process(User user) {
		return s -> {
			user.setId(s);
			return user;
		};
	}

	@Test
	public void testPerformance() {
		int size = 100000;
		long forMethod = 0L;
		long streamMethod = 0L;
		for (int i = 0; i < 30; i++) {
			List<Integer> data = initial(size);
			forMethod = forMethod + forMethod(data);
		}
		for (int i = 0; i < 30; i++) {
			List<Integer> data = initial(size);
			streamMethod = streamMethod + streamMethod(data);
		}
		System.out.println(" forMethod total is " + forMethod);
		System.out.println(" streamMethod total is " + streamMethod);
	}

	private List<Integer> initial(int size) {
		Random random = new Random();
		List<Integer> list = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			list.add(random.nextInt());
		}
		return list;
	}

	private long forMethod(List<Integer> list) {
		long t = System.currentTimeMillis();
		for (Integer one : list) {
			System.err.println(one);
		}
		long n = System.currentTimeMillis();
		return n - t;
	}

	private long streamMethod(List<Integer> list) {
		long t = System.currentTimeMillis();
		list.stream().forEach(one -> System.err.println(one));
		long n = System.currentTimeMillis();
		return n - t;
	}

	@Test
	public void testSpliterator() {
		print(Arrays.asList(new ArrayList<>(), new LinkedList<>(), new HashMap<>().keySet(),
				new LinkedHashMap<>().keySet(), new TreeMap<>().keySet()));
	}

	private void print(List<Collection<?>> collections) {
		collections.forEach(collection -> {
			System.out.println(collection.getClass() + ":" + collection.spliterator().characteristics());
		});
	}

	@Test
	public void testPeek() {
		Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e))
				.forEach(e -> System.out.println("Each value: " + e));
	}

}
