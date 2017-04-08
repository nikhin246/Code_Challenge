package com.example.search;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearchTest {

	@Test
	public void testSearch() {
		String filename = "D:\\input.txt";
		Search.inputData(filename);
		int lengthResult = Search.getResultLength();
		assertEquals(12, lengthResult );

		String stringResult = Search.getResultString();
		assertEquals("YES", stringResult);
		
		String pathResult = Search.getResultPath();
		assertEquals("[4 1 4 4 3 4]", pathResult );
	}

}
