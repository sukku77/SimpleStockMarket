package com.jpmc.gbce.client;

import java.util.Scanner;

import com.jpmc.gbce.enums.TradeType;
import com.jpmc.gbce.modal.Trade;
import com.jpmc.gbce.repository.StockRepository;
import com.jpmc.gbce.repository.TradeRepository;
import com.jpmc.gbce.stock.utils.StockUtils;
import com.jpmc.gbce.stockservice.StockService;

/**
 * This class is client program which we need to execute and expects user inputs
 * based on the options user choose
 * 
 * @author Sukumar
 *
 */
public class Main {

	public static void main(String[] args) {
		StockService stockOperations = new StockService(new StockRepository(), new TradeRepository());
		String operation = "e";

		while (!operation.equals("c")) {
			System.out.println("Please enter either a or b or c");
			System.out.println("a for stock opertaions");
			System.out.println("b for GBCE operation");
			System.out.println("c to exit the program");

			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			operation = sc.next();
			if (operation.equalsIgnoreCase("a")) {

				boolean isValid = false;
				String stockSymbol = null;
				System.out.println("Please enter one of the valid below Stock Symbol");
				System.out.println("TEA or POP or ALE or GIN or JOE");
				while (!isValid) {
					sc = new Scanner(System.in);
					stockSymbol = sc.next();
					if (stockOperations.isValidStock(stockSymbol.toUpperCase())) {
						isValid = true;
					} else {
						System.out.println("Please enter valid stock symbol");
					}
				}
				int option = 0;
				while (option != 5) {
					System.out.println("Please enter one of the below option");
					System.out.println("1 for calculate the dividend yield");
					System.out.println("2 for calculate the P/E Ratio");
					System.out.println("3 for record a trade");
					System.out
							.println("4 for Calculate Volume Weighted Stock Price based on trades in past 15 minutes");
					System.out.println("5 for Previous menu");
					sc = new Scanner(System.in);
					option = sc.nextInt();
					if (option == 1) {
						System.out.println("Please enter Price to calculate dividend yield");
						sc = new Scanner(System.in);
						Double price = sc.nextDouble();
						try {
							System.out.println(stockOperations.dividendYield(stockSymbol, price));
						} catch (Exception e) {
							System.out.println("Error while calculating dividend yield");
						}
					} else if (option == 2) {
						System.out.println("Please enter Price to calculate the P/E Ratio");
						sc = new Scanner(System.in);
						Double price = sc.nextDouble();
						try {
							System.out.println(stockOperations.priceEarningRatio(stockSymbol, price));
						} catch (Exception e) {
							System.out.println("Error while calculating P/E ratio");
						}
					} else if (option == 3) {
						boolean tradeTypeFlag = false;
						String tradeType = null;
						System.out.println("Please enter Trade Type(BUY/SELL)");
						while(!tradeTypeFlag){							
							sc = new Scanner(System.in);
							tradeType = sc.next();
							tradeTypeFlag = StockUtils.isValidTradeType(tradeType);
							if(!tradeTypeFlag){
								System.out.println("Please enter valid Trade Type(BUY/SELL)");
							}							
						}
						System.out.println("Please enter Bid Price");
						sc = new Scanner(System.in);
						Double bidPrice = sc.nextDouble();
						
						int quant = -1;
						Integer quantity = 0;
						System.out.println("Please enter Quantity");
						while(quant<=0){							
							sc = new Scanner(System.in);
							 quantity = sc.nextInt();
							 if(quantity > 0){
								 quant = quantity;
							 }
							 System.out.println("Please enter Quantity greater than zero");
						}
					
						Trade trade = new Trade(TradeType.valueOf(tradeType.toUpperCase()), bidPrice, quantity);
						try {
							stockOperations.addTrade(stockSymbol, trade);
							System.out.println("Trade is successful!!");
						} catch (Exception e) {
							System.out.println("Trade is declined due to error");
						}
					} else if (option == 4) {
						try {
							System.out.println(stockOperations.volumeWeightedAveragePrice(stockSymbol));
						} catch (Exception e) {
							System.out.println("Error while calculating volumeWeightedAveragePrice");
						}
					} else if (option != 5) {
						System.out.println("Invalid option entered.");
					}
				}

			} else if (operation.equalsIgnoreCase("b")) {
				Double gbceAllShareIndex = stockOperations.gbceAllShareIndex();
				if(gbceAllShareIndex == 0.0){
					System.out.println("No Trades happened on any stock.");
				}else{
					System.out.println(gbceAllShareIndex);
				}
			}
		}
		System.out.println("Exiting the program");
		System.exit(1);
	}
}
