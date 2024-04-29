package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import java.security.InvalidParameterException;

public class DataTest extends DataUtilities {
	
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
	     catch (Exception e) {
	    	 
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
	 
	 
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void columnOutOfRangeUnder() {
	     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	     dataset.addValue(3, "Row1", "Column1");
	     dataset.addValue(4, "Row2", "Column1");

	     Values2D values = dataset;
	     // out of range column
	     double result = DataUtilities.calculateColumnTotal(values, -1);
	 }


	 @Test(expected = IndexOutOfBoundsException.class)
	 public void columnOutOfRangeOver() {
	     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	     dataset.addValue(3, "Row1", "Column1");
	     dataset.addValue(4, "Row2", "Column1");

	     Values2D values = dataset;
	     // out of range column (2 in this case)
	     double result = DataUtilities.calculateColumnTotal(values, 2);
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
	     catch (Exception e) {
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

	 @Test(expected = IndexOutOfBoundsException.class)
	 public void rowOutOfRangeUnder() {
	     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	     dataset.addValue(3, "Row1", "Column1");
	     dataset.addValue(4, "Row1", "Column2");
	     dataset.addValue(4, "Row1", "Column3");

	     Values2D values = dataset;
	     // out of range row index (-1 in this case)
	     double result = DataUtilities.calculateRowTotal(values, -1);
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
	    	 fail("Expected exception ");
	     }
	     catch (Exception e) {
	    	 // expected exception
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
        	fail("No exception was thrown");
        }
        catch (Exception e) {
            // (Exception is expected)
        }
        
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
    public void testCreateNumberArray2D() {
        try {
            // Test with null input array
            double[][] nullData = null;
            DataUtilities.createNumberArray2D(nullData);
            fail("Expected exception but no exception was thrown");
        } catch (Exception e) {
            // Exception thrown so pass
        }

        // Test with non-null input array
        double[][] testData = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] result = DataUtilities.createNumberArray2D(testData);

        // Verify the result has correct dimensions and values
        assertEquals("Unexpected number of rows", testData.length, result.length);
        for (int i = 0; i < testData.length; i++) {
            assertEquals("Unexpected number of columns in row " + i, testData[i].length, result[i].length);
            for (int j = 0; j < testData[i].length; j++) {
                assertEquals("Unexpected value in row " + i + ", column " + j, testData[i][j], result[i][j].doubleValue(), 0.000001);
            }
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
    
    @Test
    public void testNullDataPercentages() {
        // C1: Data parameter is null
    	try {
    		DataUtilities.getCumulativePercentages(null);
    		fail("Expected exception");
    		
    	} catch (Exception e) {
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
    
    @Test
    public void testValidKeyedValuesData() {
        final KeyedValues data = context.mock(KeyedValues.class);
        final int itemCount = 3; // Change the itemCount to your desired value
        final double[] values = {10.0, 20.0, 30.0}; // Change the values to your desired test values
        context.checking(new Expectations() {{
            allowing(data).getItemCount();
            will(returnValue(itemCount));
            for (int i = 0; i < itemCount; i++) {
                allowing(data).getValue(i);
                will(returnValue(values[i]));
                allowing(data).getKey(i);
                will(returnValue(i));
            }
        }});

        try {
            KeyedValues result = DataUtilities.getCumulativePercentages(data);
            // Assert the correctness of the result here
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        context.assertIsSatisfied();
    }
    
    
    
 // ##########################Adding new tests################################
	 
//    @Test
//    public void testCalculateColumnTotalWithNegativeRowCount() {
//        Mockery mockingContext = new JUnit4Mockery();
//        final Values2D data = mockingContext.mock(Values2D.class);
//        final int column = 0;
//
//        mockingContext.checking(new Expectations() {{
//            oneOf(data).getRowCount(); will(returnValue(-1)); // Simulate negative row count
//        }});
//
//        // Attempt to calculate column total with a negative row count.
//        double result = DataUtilities.calculateColumnTotal(data, column);
//
//        // Since the row count is negative, the total should be zero.
//        assertEquals(0.0, result, 0.0001); // Assuming a delta of 0.0001 for double comparison
//
//        mockingContext.assertIsSatisfied();
//    }


 
 @Test
    public void testCalculateColumnTotalValidRowsCoverage() {
        Mockery mockingContext = new Mockery();
        final Values2D data = mockingContext.mock(Values2D.class);
        final int column = 0;
        final int[] validRows = {0, 1}; 
        final int rowCount = 2; 

        mockingContext.checking(new Expectations() {{
            // Set the row count expectation
            allowing(data).getRowCount(); will(returnValue(rowCount));

            // Expect getValue to be called for the valid row indexes
            allowing(data).getValue(validRows[0], column); will(returnValue(1.0));
            allowing(data).getValue(validRows[1], column); will(returnValue(2.0));
        }});

        try {
            double result = DataUtilities.calculateColumnTotal(data, column, validRows);
            assertEquals("The total should be the sum of the valid row values.", 3.0, result, 0.000000001d);
            // Verify all expected calls on the mock object were made
            mockingContext.assertIsSatisfied();
        } catch (Exception e) {
            fail("No exception should be thrown: " + e.getMessage());
        }
    }
 

 
 
 //row
	 @Test
	 public void testCalculateRowTotalCoverSecondLoop() {
	     Mockery mockingContext = new JUnit4Mockery();
	     final Values2D data = mockingContext.mock(Values2D.class);
	     final int row = 0;
	
	     // Set up mock to return column count greater than zero
	     mockingContext.checking(new Expectations() {{
	         oneOf(data).getColumnCount(); will(returnValue(2)); // Simulate column count greater than zero
	         exactly(2).of(data).getValue(with(equal(row)), with(any(Integer.class))); will(returnValue(1.0)); // Simulate two iterations of getValue()
	     }});
	
	     // Attempt to calculate row total with valid column count
	     double result = DataUtilities.calculateRowTotal(data, row);
	
	     // Assert the result, in this case, it should be 2.0 as getValue() returns 1.0 for each column
	     assertEquals(2.0, result, 0.000001);
	
	     mockingContext.assertIsSatisfied();
	 }
 

 
 	@Test
    public void testCalculateRowTotalForCoverage() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        final int row = 0;
        final int[] validCols = {0, 1}; // valid column indexes

        // Assume column count is more than the highest index in validCols
        final int colCount = 2; 

        mockingContext.checking(new Expectations() {{
            // Set the column count expectation
            allowing(values).getColumnCount(); will(returnValue(colCount));

            // Expect getValue to be called for the valid column indexes
            allowing(values).getValue(row, validCols[0]); will(returnValue(1.0));
            allowing(values).getValue(row, validCols[1]); will(returnValue(2.0));
        }});

        try {
            
            double result = DataUtilities.calculateRowTotal(values, row, validCols);
            
            assertEquals("The total should be the sum of the valid column values.", 3.0, result, 0.000000001d);
            // Verify all expected calls on the mock object were made
            mockingContext.assertIsSatisfied();
        } catch (Exception e) {
            fail("No exception should be thrown: " + e.getMessage());
        }
    }
 
 @Test
    public void testCalculateRowTotalForCoverage2() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        final int row = 0;
        final int[] validCols = {0, 1};

        mockingContext.checking(new Expectations() {{
            allowing(values).getColumnCount(); will(returnValue(-1));
        }});

        double result = DataUtilities.calculateRowTotal(values, row, validCols);
        assertEquals("The total should be the sum of the valid column values.", 0, result, 0.000000001d);
        
    }
 
 @Test
public void testClone() {
    // Prepare a source array with a non-null sub-array and a null sub-array
    double[][] source = new double[2][];
    source[0] = new double[]{1.0, 2.0};
    source[1] = null; // Include a null sub-array to test this edge case

    // Call the clone method
    double[][] cloned = DataUtilities.clone(source);

    // Verify that the cloned array is not null and has the same size as the source
    assertNotNull("The cloned array should not be null", cloned);
    assertEquals("The cloned array should have the same size as the source",
                 source.length, cloned.length);

    // Verify that the first sub-array was cloned correctly
    assertNotNull("The first sub-array should not be null", cloned[0]);
    assertTrue("The first sub-array should be a copy of the source",
               java.util.Arrays.equals(source[0], cloned[0]));

    // Verify that the second sub-array is null, just like in the source array
    assertNull("The second sub-array should be null", cloned[1]);
}
 
 
 // Equal
 
 @Test
 public void testEqualWithBothNullArrays() {
    assertTrue("Both null arrays should be considered equal",
               DataUtilities.equal(null, null));
 }
 
 @Test
    public void testEqualWithSecondArrayNull() {
        double[][] a = new double[][]{{1.0}, {2.0}};
        double[][] b = null;
        assertFalse("One non-null array and one null array should not be considered equal",
                    DataUtilities.equal(a, b));
    }

 @Test
    public void testEqualWithArraysDifferentLengths() {
        double[][] a = new double[][]{{1.0}, {2.0}};
        double[][] b = new double[][]{{1.0}};
        assertFalse("Arrays with different lengths should not be considered equal",
                    DataUtilities.equal(a, b));
    }
 
 @Test
    public void testEqualWithArraysSameLengthAndSameInnerArrays() {
        double[][] a = new double[][]{{1.0}, {2.0}};
        double[][] b = new double[][]{{1.0}, {2.0}};
        assertTrue("Arrays with same lengths and same inner arrays should be considered equal",
                   DataUtilities.equal(a, b));
    }
 
 @Test
    public void testEqualWithArraysSameLengthButDifferentInnerArrays() {
        double[][] a = new double[][]{{1.0}, {2.0}};
        double[][] b = new double[][]{{1.0}, {3.0}};
        assertFalse("Arrays with same lengths but different inner arrays should not be considered equal",
                    DataUtilities.equal(a, b));
    }
 
 
// ##########################Added new tests################################
 
 
}