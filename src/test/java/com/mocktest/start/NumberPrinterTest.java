package com.mocktest.start;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import javax.management.RuntimeErrorException;

@RunWith(MockitoJUnitRunner.class)
public class NumberPrinterTest {
	
	@Test
	public void printsCalculatorResultsHundredTimes() {
		
		Printer printer = mock(Printer.class);
		
		NumberCalculator numberCalculator = mock(NumberCalculator.class);
		
		NumberPrinter numberPrinter = new NumberPrinter(numberCalculator);
		
		//arrange
		int limit = 100;
		
		when(numberCalculator.calculate(anyInt()))
				.thenReturn("0")
				.thenReturn("1");
		
		//act
		numberPrinter.printNumbers(limit);
		
		//assert
		verify(numberCalculator, times(limit)).calculate(anyInt());
		verify(printer, times(1)).print("0");
		verify(printer, times(limit - 1)).print("1");
		verifyNoMoreInteractions(numberCalculator, printer);
		
	}
	
	/*
	@Test
	public void continuesOnCalculatorOrPrinterError() {
		
		//arrange
		int limit = 100;
		
		when(numberCalculator.calculate(anyInt()))
				.thenReturn("1")
				.thenReturn(new RuntimeException())
				.thenReturn("3");
		
		doThrow(new RuntimeException()).when(printer).print("3");
		
		//act
		numberPrinter.printNumbers(limit);
		
		//assert
		verify(numberCalculator, times(limit)).calculate(anyInt());
		verify(printer, times(1)).print("0");
		verify(printer, times(limit - 1)).print("1");
		verifyNoMoreInteractions(numberCalculator, printer);
		
	}*/
}
