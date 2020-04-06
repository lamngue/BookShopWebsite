package com.bookstore.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator calc = new Calculator();
		int a = 1234;
		int b = 5678;
		int res = calc.add(a, b);
		int expected = 6912;
		assertEquals(res, expected);
	}

}
