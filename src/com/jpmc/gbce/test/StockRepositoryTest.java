package com.jpmc.gbce.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.jpmc.gbce.enums.StockType;
import com.jpmc.gbce.modal.Stock;
import com.jpmc.gbce.repository.StockRepository;

/**
 * This class is to test StockRepository
 * 
 * @author Sukumar
 *
 */
public class StockRepositoryTest {

	private static final String STOCK_SYMBOL_POP = "POP";

	private static final String STOCK_SYMBOL_INVAILD = "AAA";

	private static final String STOCK_SYMBOL_NEW = "ABC";

	private StockRepository stockRepository;

	@Before
	public void setUp() {
		this.stockRepository = new StockRepository();
	}

	@Test
	public void testIsValidStock() {
		assertTrue(this.stockRepository.isValidStock(STOCK_SYMBOL_POP));
	}

	@Test
	public void testIsInvalidStock() {
		assertFalse(this.stockRepository.isValidStock(STOCK_SYMBOL_INVAILD));
	}

	@Test
	public void addStock() throws Exception {
		Stock stock = new Stock(STOCK_SYMBOL_NEW, StockType.COMMON, 8.0, 0.0, 100.0);
		this.stockRepository.addStock(stock);
		assertEquals(stock, this.stockRepository.getStock(STOCK_SYMBOL_NEW));
	}

	@Test
	public void testGetStock() throws Exception {
		Stock stock = this.stockRepository.getStock(STOCK_SYMBOL_POP);
		assertEquals(TestUtils.getAllStocks().get(STOCK_SYMBOL_POP), stock);
	}

	
}
