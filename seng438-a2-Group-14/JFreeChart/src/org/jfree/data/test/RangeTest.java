package org.jfree.data.test;

import static org.junit.Assert.*;
import org.junit.*;
import org.jfree.data.Range;
import org.junit.Test;

public class RangeTest{

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    }

    //.....................................Combine()......................................
    
    @Test
    public void testRange1NullRange2Valid() {
        Range result = Range.combine(null, new Range(5, 10));
        Assert.assertEquals("Combining null range1 with a valid range2 should return range2", new Range(5, 10), result);
    }

    @Test
    public void testRange1ValidRange2Null() {
        Range result = Range.combine(new Range(-5, 10), null);
        
        Assert.assertEquals("Combining a valid range1 with null range2 should return range1", new Range(-5, 10), result);
    }
    
    @Test
    public void testBothRangesNull() {
        Range result = Range.combine(null, null);
        Assert.assertNull("Combining two null ranges should return null", result);
        
    }
    
    @Test
    public void testBothRangesValidSubsumedRange() {
    	
        try {
        	Range range1 = new Range(5, 10);
            Range range2 = new Range(12, 14);
            Range result = Range.combine(range1, range2);
            Assert.assertEquals("Valid non-overlapping ranges should be combined", new Range(5, 14), result);
        }
        catch (IllegalArgumentException e) {
            fail("Expected InvalidParameterException");
        }
        
    }
    
    @Test
    public void testBothRangesValidSameValues() {
        Range result = Range.combine(new Range(1, 3), new Range(1, 3));
        Assert.assertEquals("Ranges with same values should return an equal range", new Range(1, 3), result);
    }
    
    @Test
    public void testBothRangesValidNegatives() {
        
        try {
        	
        	Range result = Range.combine(new Range(-10, -5), new Range(-4, -3));
            Assert.assertEquals("Combining negative ranges should return subsumed range", new Range(-10, -3), result);
            
        }
        catch (Exception e) {
            fail("Unexpected Exception");
        }
        
    }
    
    @Test
    public void testBothRangesValidPositives() {
        
        try {
        	Range result = Range.combine(new Range(1, 5), new Range(3, 7));
            Assert.assertEquals("Combining positive ranges should return subsumed range", new Range(1, 7), result);
            
        }
        catch (Exception e) {
            fail("Unexpected Exception");
        }
    }
    
    @Test
    public void testBothRangesValidNegAndPos() {
        
        try {
        	Range result = Range.combine(new Range(-5, 0), new Range(-1, 5));
            Assert.assertEquals("Combining negative and positive ranges should return subsumed range", new Range(-5, 5), result);
            
        }
        catch (Exception e) {
            fail("Unexpected Exception");
        }
    }
    
    @Test
    public void testBothRangesOverlapping() {
        
        try {
        	Range result = Range.combine(new Range(5, 15), new Range(10, 20));
            Assert.assertEquals("Overlapping ranges should return subsumed range", new Range(5, 20), result);
            
        }
        catch (Exception e) {
            fail("Unexpected Exception");
        }
    }
    
    @Test
    public void testLargeValue() {
        Range result = Range.combine(new Range(5, 1000000000), null);
        Assert.assertEquals("Combining with large range values should return subsumed range", new Range(5, 10000000), result);
    }
    
    @Test
    public void testInvalidRangeSubsumed() { //Invalid range hence should give an exception which is correct
        
    	
        
        try {
        	Range range1 = new Range(10, 5);
            Range range2 = new Range(-3, -7);
        	Range result = Range.combine(range1, range2);     
        }
        catch (IllegalArgumentException e) {
            fail("Expected InvalidParameterException");
        }
    }
    
    @Test
    public void testRangeInsideValid() {
        
        try {
        	Range result = Range.combine(new Range(0, 20), new Range(1, 4));
            Assert.assertEquals("A range fully inside another should return the outer range", new Range(0, 20), result);
            
        }
        catch (Exception e) {
            fail("Unexpected Exception");
        }
    }
    
    @Test
    public void testDouble() {
        Range result = Range.combine(new Range(1.4, 2.6), null);
        Assert.assertEquals("Combining Double range1 with a null range2 should return range1", new Range(1.4, 2.6), result);
    }
    
    
    
    
    // ............................................getLowerBound()......................................
    
    
    @Test
    public void testNegativeLowerBound() {
        Range range = new Range(-10, -1);
        Assert.assertEquals("The lower bound should be -10", -10, range.getLowerBound(), 0.00001);
    }

    @Test
    public void testPositiveLowerBound() {
        Range range = new Range(1, 10);
        Assert.assertEquals("The lower bound should be 1", 1, range.getLowerBound(), 0.00001);
    }

    @Test
    public void testNegativeDoubleLowerBound() {
        Range range = new Range(-52.96, 78.99);
        Assert.assertEquals("The lower bound of a range with negative double should be -52.96", -52.96, range.getLowerBound(), 0.00001);
    }

    @Test
    public void testPositiveDoubleLowerBound() {
        Range range = new Range(1.58, 10.78);
        Assert.assertEquals("The lower bound of a range with positive double should be 1.58", 1.58, range.getLowerBound(), 0.00001);
    }

    @Test
    public void testSmallLowerBound() {
        Range range = new Range(0.00001, 10);
        Assert.assertEquals("The lower bound of a range with small value should be 0.00001", 0.00001, range.getLowerBound(), 0.00001);
    }

    @Test
    public void testLargeLowerBound() {
        Range range = new Range(1000000000, 1000000000);
        Assert.assertEquals("The lower bound of a range with large value should be 1000000000", 1000000000, range.getLowerBound(), 0.00001);
    }

    @Test
    public void testZeroLowerBound() {
        Range range = new Range(0, 100);
        Assert.assertEquals("The lower bound of a range with zero should be 0", 0, range.getLowerBound(), 0.00001);
    }

    
    @Test(expected = IllegalArgumentException.class) //Should throw an error since the range is invalid
    public void testInvalidLowerBound() {
    	
        Range range = new Range(10, 5);
        double lowerBound = range.getLowerBound();
        
        Assert.assertEquals("The lower bound of an invalid range should be the first parameter", 10, lowerBound, 0.00001);
    }
    
    
    // ............................................getUpperBound()......................................
    
    @Test
    public void testNegativeUpperBound() {
        Range range = new Range(-10, -1);
        
        Assert.assertEquals("The Upper bound should be -1", -1, range.getUpperBound(), 0.00001);
    }

    @Test
    public void testPositiveUpperBound() {
        Range range = new Range(1, 10);
        Assert.assertEquals("The Upper bound should be 10", 10, range.getUpperBound(), 0.00001);
    }

    @Test
    public void testNegativeDoubleUpperBound() {
        Range range = new Range(-102.96, -78.99);
        Assert.assertEquals("The Upper bound of a range with negative double should be 78.99", -78.99, range.getUpperBound(), 0.00001);
    }

    @Test
    public void testPositiveDoubleUpperBound() {
        Range range = new Range(1.58, 10.78);
        Assert.assertEquals("The Upper bound of a range with positive double should be 10.78", 10.78, range.getUpperBound(), 0.00001);
    }

    @Test
    public void testSmallUpperBound() {
        Range range = new Range(-1, 0.00001);
        Assert.assertEquals("The Upper bound of a range with small value should be 0.00001", 0.00001, range.getUpperBound(), 0.00001);
    }

    @Test
    public void testLargeUpperBound() {
        Range range = new Range(0, 1000000000);
        Assert.assertEquals("The Upper bound of a range with large value should be 1000000000", 1000000000, range.getUpperBound(), 0.00001);
    }

    @Test
    public void testZeroUpperBound() {
        Range range = new Range(-1, 0);
        Assert.assertEquals("The Upper bound of a range with zero should be 0", 0, range.getUpperBound(), 0.00001);
    }
    
    
    
  //.....................................constrain()......................................
    private static final double DELTA = 0.0000001; // Precision for comparing double values

    @Test
    public void testConstrain_ValueWithinRange() {
        Range range = new Range(1.0, 10.0);
        double result = range.constrain(5.0);
        assertEquals("Value within range should be unchanged", 5.0, result, DELTA);
    }

    @Test
    public void testConstrain_ValueEqualsLowerBoundary() {
        Range range = new Range(1.0, 10.0);
        double result = range.constrain(1.0);
        assertEquals("Value equal to lower boundary should be unchanged", 1.0, result, DELTA);
    }

    @Test
    public void testConstrain_ValueEqualsUpperBoundary() {
        Range range = new Range(1.0, 10.0);
        double result = range.constrain(10.0);
        assertEquals("Value equal to upper boundary should be unchanged", 10.0, result, DELTA);
    }

    @Test
    public void testConstrain_ValueBelowRange() {
        Range range = new Range(1.0, 10.0);
        double result = range.constrain(0.0);
        assertEquals("Value below range should be constrained to lower boundary", 1.0, result, DELTA);
    }

    @Test
    public void testConstrain_ValueAboveRange() {
        Range range = new Range(1.0, 10.0);
        double result = range.constrain(11.0);
        assertEquals("Value above range should be constrained to upper boundary", 10.0, result, DELTA);
    }


    //.....................................expand().........................................
	
    @Test
    public void testValidExpand_ExpandsRangeCorrectly() {
        Range original = new Range(10, 20);
        Range result = Range.expand(original, 0.10, 0.20); // Expanding by 10% and 20%
        assertEquals("Lower bound for valid expansion", 9, result.getLowerBound(), DELTA);
        assertEquals("Upper bound for valid expansion", 24, result.getUpperBound(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class) // Adjusting based on the actual exception thrown by the library
    public void testBelowLowerBound_ThrowsException() {
        Range original = new Range(10, 20);
        Range.expand(original, -0.10, -0.20); // This case's expected behavior is unclear; assuming an exception for invalid expansion
    }

    @Test
    public void testLowerBound_RangeUnchanged() {
        Range original = new Range(10, 20);
        Range result = Range.expand(original, 0, 0); // No expansion
        assertEquals("Lower bound should remain unchanged", 10, result.getLowerBound(), DELTA);
        assertEquals("Upper bound should remain unchanged", 20, result.getUpperBound(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class) // Assuming IllegalArgumentException for null input
    public void testRangeNull_ThrowsInvalidParameterException() {
        Range.expand(null, 0.10, 0.20); // Passing null should throw an exception
    }

    @Test
    public void testUpperBoundExpansion_ExpandsRangeToExpectedBounds() {
        Range original = new Range(10, 20);
        Range result = Range.expand(original, 1, 1); // 100% expansion on both sides
        assertEquals("Lower bound after 100% expansion", 0, result.getLowerBound(), DELTA);
        assertEquals("Upper bound after 100% expansion", 30, result.getUpperBound(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class) // Adjusting based on actual behavior; assuming an exception for over-expansion
    public void testAboveUpperBoundExpansion_ThrowsException() {
        Range original = new Range(10, 20);
        Range.expand(original, 1.1, 1.1); // Assuming this results in an exception for invalid expansion parameters
    }




	 @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
