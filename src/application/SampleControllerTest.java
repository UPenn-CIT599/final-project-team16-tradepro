package application;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import javafx.collections.ObservableList;

public class SampleControllerTest {
	
	/**
	 * Testing whether or not the Sample Controller classes' method "mainListViewGetter" contains the elements I think that it does
	 */
	@Test
	public void mainListViewGetterTest() {
		
		SampleController SC = new SampleController();
		ObservableList<String>testCase = SC.mainListViewGetter();
		Assert.assertEquals("SPY", testCase.get(0));
		Assert.assertEquals("QQQ", testCase.get(1));
		Assert.assertEquals("Rus2000", testCase.get(2));
	}

}
