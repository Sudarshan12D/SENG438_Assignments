package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import java.security.InvalidParameterException;

public class DataUtilitiesTest extends DataUtilities {
	
	// calculateColumnTotal
	
	 @Test
	 public void testInvalidData() {
		 // set data to null
		 Values2D data = null;
	     int column = 0;
	     // test expected to InvalidParametException
	     try {
	    	 DataUtilities.calculateColumnTotal(data, column);
	     } 
	     catch (NullPointerException e) {
	    	 fail("Expected InvalidParameterException");
	     }
	 }
	 
	 @Test
	 public void testValidData() {
		 // mock Value2D
		 Mockery mockingContext = new Mockery();
		 final Values2D data = mockingContext.mock(Values2D.class);
		 final int column = 0;
	     mockingContext.checking(new Expectations() {
	     {
	    	 // so 2 rows, one with 3.5 and 3.5 summed to 7
	    	 // get row count once
	    	 one(data).getRowCount();
	    	 
	    	 // when getRow is called return 2
	    	 will(returnValue(2));
	    	 
	    	 // called once with args 0,0
	    	 one(data).getValue(0, 0);
	    	 
	    	 // return 7.5
	    	 will(returnValue(3.5));
	    	 
	    	 // args 1,0 called once, return 2.5
	    	 one(data).getValue(1, 0);
	    	 will(returnValue(3.5));
	     }
	     });
	     // expected == 7
	     try {
	    	 double result = DataUtilities.calculateColumnTotal(data, column);
	    	 assertEquals(7.0, result, 0.000000001d);
	     } 
	     catch (Exception e) {
	    	 fail("Unexpected exception: " + e);
	     }
	 }
	 
	 @Test
	 public void testValuesInDataPostive(){
		 
		 Mockery mockingContext = new Mockery();
		 final Values2D data = mockingContext.mock(Values2D.class);
		 final int column = 0;
	     mockingContext.checking(new Expectations() {
	     {
	    	 // get row count once
	    	 one(data).getRowCount();
	    	 
	    	 // when getRow is called return 2
	    	 will(returnValue(2));
	    	 
	    	 // called onces with args 0,0
	    	 one(data).getValue(0, 0);
	    	 
	    	 // return 3.5 positive
	    	 will(returnValue(3.5));
	    	 
	    	 // args 1.0 called once, return 3.5 positive
	    	 one(data).getValue(1, 0);
	    	 will(returnValue(3.5));
	     }
	     });
	     
	     try {
	    	 double result = DataUtilities.calculateColumnTotal(data, column);
	    	 assertEquals(7.0, result, 0.000000001d);
	     } 
	     catch (Exception e) {
	    	 fail("Unexpected exception: " + e);
	     }
	 }
	 
	 @Test
	 public void testValuesInDataNegative(){
		 
		 Mockery mockingContext = new Mockery();
		 final Values2D data = mockingContext.mock(Values2D.class);
		 // at first column
		 final int column = 0;
	     mockingContext.checking(new Expectations() {
	     {
	    	 // get row count once
	    	 one(data).getRowCount();
	    	 
	    	 // when getRow is called return 2
	    	 will(returnValue(2));
	    	 
	    	 // called onces with args 0,0
	    	 one(data).getValue(0, 0);
	    	 
	    	 // return -5.0
	    	 will(returnValue(-5.0));
	    	 
	    	 // args 1.0 called once, return -4
	    	 one(data).getValue(1, 0);
	    	 will(returnValue(-4.0));
	     }
	     });

	     try {
	    	 double result = DataUtilities.calculateColumnTotal(data, column);
	    	 assertEquals(-9.0, result, 0.000000001d);
	     } 
	     catch (Exception e) {
	    	 fail("Unexpected exception: " + e);
	     }
	 }
	 
	 @Test
	 public void columnOutOfRangeUnder() {
		 // set a Values2D with 1 column and 2 rows
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(3, "Row1", "Column1");
		dataset.addValue(4, "Row2", "Column1");

		Values2D values = dataset;
		// out of range column
	     try {
	         double result = DataUtilities.calculateColumnTotal(values, -1);
	    	 assertEquals(0, result, 0.000000001d);
	     } catch (Exception e) {
	    	 fail("Unexpected exception: " + e);
	     }
	 }


	 @Test
	 public void columnOutOfRangeOver(){
		// set a Values2D with 1 column and 2 rows
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.addValue(3, "Row1", "Column1");
		dataset.addValue(4, "Row2", "Column1");

		Values2D values = dataset;
		
		// calculate with out of range columns, 2 in this case
	     try {
	         double result = DataUtilities.calculateColumnTotal(values, 2);
	         // should return 0
	    	 assertEquals(0, result, 0.000000001d);
	     } catch (Exception e) {
	    	 fail("Unexpected exception: " + e);
	     }
	 }
	 
	 @Test
	 public void testColumnTotalWithLargeColumns(){
		 // create mock with high row and column count
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
		 int column = 0;
		 int rowCount = 1000000;
		 int columnCount = 1000000;

		 mockingContext.checking(new Expectations() {
			 {
				 allowing(values).getRowCount();
				 will(returnValue(rowCount));
				 allowing(values).getColumnCount();
				 will(returnValue(columnCount));

				 // Allow any getValue
				 allowing(values).getValue(with(any(Integer.class)), with(any(Integer.class)));
				 // just return any value, 1 in this case
				 will(returnValue(1));
			 }
		 });

		 try {
			 // run with 1 million rows and columns
			 double result = DataUtilities.calculateColumnTotal(values, 0);
		 }
		 // if it doesnt run in time test fails
		 catch (Exception e) {
			 fail("Unexpected exception: " + e);
		 }
	 }
	 
	 
	 @Test
	 public void testColumnTotalWithEmptyData(){
		 // instantiate empty dataset
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		 Values2D values = dataset;

		 try {
			 double result = DataUtilities.calculateColumnTotal(values, 0);
	    	 assertEquals(0.0, result, 0.000000001d);
		 } catch (Exception e) {
			 fail("unexpected exception" + e);
		 }
	 }
	 
	 
	 // calculateRowTotal tests

	 @Test
	 public void testInvalidDataRow() {
		 // set data to null
		 Values2D data = null;
	     int row = 0;
	     // test expected to throw error
	     try {
	    	 DataUtilities.calculateRowTotal(data, row);
	     } 
	     catch (NullPointerException e) {
	    	 fail("Expected InvalidParameterException");
	     }
	 }
	 
	 @Test
	 public void testValidDataRow() {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			dataset.addValue(3, "Row1", "Column1");
			dataset.addValue(4, "Row1", "Column2");

			Values2D values = dataset;
			int row = 0;
		 try { 
			 double result = DataUtilities.calculateRowTotal(values, row);
			 assertEquals(7, result, 0.000000001d);
		 }
		 catch (Exception e) {
			 fail("Unexpected exception: " + e);
		 }
	 }
	 
	 @Test
	 public void testValuesInDataPositiveRow() {
		 Mockery mockingContext = new Mockery();
		 final Values2D data = mockingContext.mock(Values2D.class);
		 // test first row
		 final int row = 0;
	     mockingContext.checking(new Expectations() {
	     {
	    	 // get column count once
	    	 one(data).getColumnCount();
	    	 
	    	 // when getColumn is called return 2
	    	 will(returnValue(2));
	    	 
	    	 // called onces with args 0,0
	    	 one(data).getValue(0, 0);
	    	 
	    	 // return 3.5 positive
	    	 will(returnValue(9.0));
	    	 
	    	 // args 1.0 called once, return 3.5 positive
	    	 one(data).getValue(0, 1);
	    	 will(returnValue(6.0));
	     }
	     });
	     
	     try {
	    	 double result = DataUtilities.calculateRowTotal(data, row);
	    	 assertEquals(15.0, result, 0.000000001d);
	     } 
	     catch (Exception e) {
	    	 fail("Unexpected exception: " + e);
	     }
	 }
	 
	 @Test
	 public void testValuesInDataNegativeRow() {
		 Mockery mockingContext = new Mockery();
		 final Values2D data = mockingContext.mock(Values2D.class);
		 // test first row
		 final int row = 0;
	     mockingContext.checking(new Expectations() {
	     {
	    	 // get column count once
	    	 one(data).getColumnCount();
	    	 
	    	 // when getColumn is called return 2
	    	 will(returnValue(2));
	    	 
	    	 // called onces with args 0,0
	    	 one(data).getValue(0, 0);
	    	 
	    	 // return 3.5 positive
	    	 will(returnValue(-9.0));
	    	 
	    	 // args 1.0 called once, return 3.5 positive
	    	 one(data).getValue(0, 1);
	    	 will(returnValue(-6.0));
	     }
	     });
	     
	     try {
	    	 double result = DataUtilities.calculateRowTotal(data, row);
	    	 assertEquals(-15.0, result, 0.000000001d);
	     } 
	     catch (Exception e) {
	    	 fail("Unexpected exception: " + e);
	     }
	 }

	 @Test
	 public void rowOutOfRangeUnder() {
		 // set a Values2D with 1 column and 2 rows
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(3, "Row1", "Column1");
		dataset.addValue(4, "Row1", "Column2");
		dataset.addValue(4, "Row1", "Column3");


		Values2D values = dataset;
		// out of range column
	     try {
	         double result = DataUtilities.calculateRowTotal(values, -1);
	    	 assertEquals(0, result, 0.000000001d);
	     } 
	     catch (Exception e) {
	    	 fail("Unexpected exception: " + e);
	     }
	 }
	 
	 @Test
	 public void rowOutOfRangeOver() {
		 // set a Values2D with 1 column and 2 rows
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(3, "Row1", "Column1");
		dataset.addValue(4, "Row1", "Column2");
		dataset.addValue(5, "Row1", "Column3");


		Values2D values = dataset;
		// out of range column
	     try {
	         double result = DataUtilities.calculateRowTotal(values, 1);
	    	 assertEquals(0, result, 0.000000001d);
	     }
	     catch (Exception e) {
	    	 fail("Unexpected exception: " + e);
	     }
	 }
	 
	 @Test
	 public void testRowTotalWithLargeValue() {
		 // create mock with high row and column count
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
		 int column = 0;
		 int rowCount = 1000000;
		 int columnCount = 1000000;

		 mockingContext.checking(new Expectations() {
			 {
				 allowing(values).getRowCount();
				 will(returnValue(rowCount));
				 allowing(values).getColumnCount();
				 will(returnValue(columnCount));

				 // Allow any getValue
				 allowing(values).getValue(with(any(Integer.class)), with(any(Integer.class)));
				 // just return any value, 1 in this case
				 will(returnValue(1));
			 }
		 });

		 try {
			 // run with 1 million rows and columns
			 double result = DataUtilities.calculateRowTotal(values, 0);
		 }
		 // if it doesnt run in time test fails
		 catch (Exception e) {
			 fail("Unexpected exception: " + e);
		 }
	 }
	 
	 @Test
	 public void testRowTotalWithEmptyData() {
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		 Values2D values = dataset;

		 try {
			 double result = DataUtilities.calculateRowTotal(values, 0);
	    	 assertEquals(0.0, result, 0.000000001d);
		 } catch (Exception e) {
			 fail("unexpected exception" + e);
		 }
	 }

	// createNumberArray tests
	
	@Test
    public void testNullDataNuArray() {
        // Arrange
        double[] nullData = null;

        // Act
        try {
        	DataUtilities.createNumberArray(nullData);
        }
        catch (IllegalArgumentException e) {
            fail("Expected InvalidParameterException");
        }
        // Assert (Exception is expected)
    }

	@Test
    public void testValidDataNuArray() {
        // Arrange
        double[] validData = { 1.0, 2.5, 3.7 };

        // Act
        Number[] result = DataUtilities.createNumberArray(validData);

        // Assert
        assertNotNull(result);
        assertEquals(validData.length, result.length);

        for (int i = 0; i < validData.length; i++) {
        	assertNotNull("The result should not contain null at index " + i, result[i]);
            assertEquals(validData[i], result[i].doubleValue(), 0.000000001d);
        }
    }

    @Test
    public void testInvalidTypeDataNuArray() {
        // Arrange
        Object[] invalidTypeData = { "string", 2, 3.7 };

        // Act
        try {
        	DataUtilities.createNumberArray(invalidTypeData);
        }
        catch(Exception e){
        	fail("Unexpected Error" + e);
        }
        // Assert (Exception is expected)
    }

    @Test
    public void testZeroElementsNuArray() {
        // Arrange
        double[] zeroElementsData = {};

        // Act
        Number[] result = DataUtilities.createNumberArray(zeroElementsData);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void testLargeNumberOfElementsNuArray() {
        // Arrange
        double[] largeNumberOfElementsData = new double[1000];

        // Act
        Number[] result = DataUtilities.createNumberArray(largeNumberOfElementsData);

        // Assert
        assertNotNull(result);
        assertEquals(largeNumberOfElementsData.length, result.length);
    }

    @Test
    public void testPositiveValuesNuArray() {
        // Arrange
        double[] positiveValuesData = { 1.5, 3.0, 7.2 };

        // Act
        Number[] result = DataUtilities.createNumberArray(positiveValuesData);

        // Assert
        assertNotNull(result);
        assertEquals(positiveValuesData.length, result.length);

        for (int i = 0; i < positiveValuesData.length; i++) {
        	assertNotNull("The result should not contain null at index " + i, result[i]);
            assertEquals(positiveValuesData[i], result[i].doubleValue(), 0.000000001d);
        }
    }

    @Test
    public void testNegativeValuesNuArray() {
        // Arrange
        double[] negativeValuesData = { -2.0, -5.5, -8.3 };

        // Act
        Number[] result = DataUtilities.createNumberArray(negativeValuesData);

        // Assert
        assertNotNull(result);
        assertEquals(negativeValuesData.length, result.length);

        for (int i = 0; i < negativeValuesData.length; i++) {
        	assertNotNull("The result should not contain null at index " + i, result[i]);
            assertEquals(negativeValuesData[i], result[i].doubleValue(), 0.000000001d);
        }
    }
    
    // createNumberArray2D tests
    
    @Test
    public void testNullData2D() {
        try {
            double[][] nullData = null;
            Number[][] result = DataUtilities.createNumberArray2D(nullData);
            fail("Expected InvalidParameterException but no exception was thrown");
        } catch (InvalidParameterException e) {
            // Expected exception, test passed
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testValidData2D() {
        try {
            double[][] validData = {{1.0, 2.0}, {3.0, 4.0}};
            Number[][] result = DataUtilities.createNumberArray2D(validData);
            for (int i = 0; i < result.length; i++) {
            	for (int j = 0; j < result[i].length; j++) {
            		assertNotNull("The result should not contain null at index " + i +
            				", " + j, result[i][j]);
            	}
            }
            assertEquals(1.0, result[0][0].doubleValue(), 0.000001d);
            assertEquals(2.0, result[0][1].doubleValue(), 0.000001d);
            assertEquals(3.0, result[1][0].doubleValue(), 0.000001d);
            assertEquals(4.0, result[1][1].doubleValue(), 0.000001d);
        } catch (InvalidParameterException e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testInvalidTypeData2D() {
        try {
            String[][] invalidData = {{"a", "b"}, {"c", "d"}};
            Number[][] result = DataUtilities.createNumberArray2D(invalidData);
            fail("Expected InvalidParameterException but no exception was thrown");
        } catch (InvalidParameterException e) {
            // Expected exception, test passed
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testZeroElements2D() {
        try {
            double[][] zeroElementsData = {};
            Number[][] result = DataUtilities.createNumberArray2D(zeroElementsData);
            assertEquals(0, result.length);
        } catch (InvalidParameterException e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testLargeNumberOfElements2D() {
        try {
            double[][] largeData = new double[1000][1000];
            Number[][] result = DataUtilities.createNumberArray2D(largeData);
            // Validate the size of the result based on expectations
            assertEquals(1000, result.length);
            assertEquals(1000, result[0].length);
        } catch (InvalidParameterException e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testPositiveValues2D() {
        try {
            double[][] positiveValuesData = {{1.0, 2.0}, {3.0, 4.0}};
            Number[][] result = DataUtilities.createNumberArray2D(positiveValuesData);
            for (int i = 0; i < result.length; i++) {
            	for (int j = 0; j < result[i].length; j++) {
            		assertNotNull("The result should not contain null at index " + i +
            				", " + j, result[i][j]);
            	}
            }
            assertEquals(1.0, result[0][0].doubleValue(), 0.000001d);
            assertEquals(2.0, result[0][1].doubleValue(), 0.000001d);
            assertEquals(3.0, result[1][0].doubleValue(), 0.000001d);
            assertEquals(4.0, result[1][1].doubleValue(), 0.000001d);
        } catch (InvalidParameterException e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testNegativeValues2D() {
        try {
            double[][] negativeValuesData = {{-1.0, -2.0}, {-3.0, -4.0}};
            Number[][] result = DataUtilities.createNumberArray2D(negativeValuesData);
            for (int i = 0; i < result.length; i++) {
            	for (int j = 0; j < result[i].length; j++) {
            		assertNotNull("The result should not contain null at index " + i +
            				", " + j, result[i][j]);
            	}
            }
            assertEquals(-1.0, result[0][0].doubleValue(), 0.000001d);
            assertEquals(-2.0, result[0][1].doubleValue(), 0.000001d);
            assertEquals(-3.0, result[1][0].doubleValue(), 0.000001d);
            assertEquals(-4.0, result[1][1].doubleValue(), 0.000001d);
        } catch (InvalidParameterException e) {
            fail("Unexpected exception: " + e);
        }
    }

	// getCumulativePercentages

	private Mockery context;

    @Before
    public void setUp() {
        context = new Mockery();
    }
    
    @Test (expected=InvalidParameterException.class)
    public void testNullDataPercentages() {
        // C1: Data parameter is null
    	try {
    		DataUtilities.getCumulativePercentages(null);
    	} catch (InvalidParameterException correctException) {
    		System.out.println("Pass: Threw Invalid Param Exception");
    		correctException.printStackTrace();
    	} catch (Exception e) {
    		System.out.println("Fail: Threw wrong exception");
    		e.printStackTrace();
    	}
    }

    @Test
    public void testPositiveValues_ValidData() {
        // C2 & C4: Data parameter is valid and contains positive values
        final KeyedValues data = context.mock(KeyedValues.class);
        context.checking(new Expectations() {{
            allowing(data).getItemCount();
            will(returnValue(3));
            allowing(data).getValue(0);
            will(returnValue(5));
            allowing(data).getValue(1);
            will(returnValue(9));
            allowing(data).getValue(2);
            will(returnValue(2));
            allowing(data).getKey(0);
            will(returnValue(0));
            allowing(data).getKey(1);
            will(returnValue(1));
            allowing(data).getKey(2);
            will(returnValue(2));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals("The cumulative percentage for the first item is incorrect", 0.3125, result.getValue(0).doubleValue(), 0.0001);
        assertEquals("The cumulative percentage for the second item is incorrect", 0.875, result.getValue(1).doubleValue(), 0.0001);
        assertEquals("The cumulative percentage for the third item is 100%", 1.0, result.getValue(2).doubleValue(), 0.0001);
        context.assertIsSatisfied();
    }

    @Test
    public void testNegativeValues_ValidData() {
        // C2 & C5: Data parameter is valid and contains negative values
        final KeyedValues data = context.mock(KeyedValues.class);
        context.checking(new Expectations() {{
            allowing(data).getItemCount();
            will(returnValue(3));
            allowing(data).getValue(0);
            will(returnValue(-5));
            allowing(data).getValue(1);
            will(returnValue(-9));
            allowing(data).getValue(2);
            will(returnValue(-2));
            allowing(data).getKey(0);
            will(returnValue(0));
            allowing(data).getKey(1);
            will(returnValue(1));
            allowing(data).getKey(2);
            will(returnValue(2));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertNotNull("Result should not be null for negative values", result);
        context.assertIsSatisfied();
    }
    
    @Test(expected = InvalidParameterException.class)
    public void testInvalidKeyedValuesData() {
    	// C3: Data consists of invalid type
        final KeyedValues data = context.mock(KeyedValues.class);
        context.checking(new Expectations() {{
            allowing(data).getItemCount();
            will(returnValue(1));
            allowing(data).getValue(0);
            will(returnValue(null)); // Simulating unexpected data content
            allowing(data).getKey(0);
            will(returnValue(0));
        }});

        try {
    		DataUtilities.getCumulativePercentages(data);
    	} catch (InvalidParameterException correctException) {
    		System.out.println("Pass: Threw Invalid Param Exception");
    		correctException.printStackTrace();
    	} catch (Exception e) {
    		System.out.println("Fail: Threw wrong exception");
    		e.printStackTrace();
    	}
    
        context.assertIsSatisfied();
    }
}
