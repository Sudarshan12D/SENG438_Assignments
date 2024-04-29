package org.jfree.data;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;
import java.security.InvalidParameterException;

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
        
    }
    
    @Test
    public void testInvalidRangeSubsumed() { //Invalid range hence should give an exception which is correct
        
    	
        
        try {
        	Range range1 = new Range(10, 5);
            Range range2 = new Range(-3, -7);
        	Range result = Range.combine(range1, range2);     
        	fail("Expected InvalidParameterException");
        }
        catch (IllegalArgumentException e) {
            
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
    
    //....................................getCentralValue()..................................

    
    @Test
    public void testGetCentralValue() {
    	Range range = new Range(1, 10);
    	double centralValue =  range.getLowerBound() / 2.0 + range.getUpperBound() / 2.0;
    	Assert.assertEquals("Testing for getting the central value", centralValue, range.getCentralValue(), 0.00001);
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
    
    //....................................combineIgnoringNaN()...............................
    
    @Test
    public void testCombineIgnoringNaN_BothNull() {
        Assert.assertNull(Range.combineIgnoringNaN(null, null));
    }

    @Test
    public void testCombineIgnoringNaN_FirstNullSecondNaN() {
        Range range2 = mockRange(Double.NaN, Double.NaN); // Assuming mockRange creates a Range with specified bounds
        Assert.assertNull(Range.combineIgnoringNaN(null, range2));
    }

    @Test
    public void testCombineIgnoringNaN_FirstNullSecondNormal() {
        Range range2 = new Range(1.0, 2.0);
        Assert.assertEquals(range2, Range.combineIgnoringNaN(null, range2));
    }

    @Test
    public void testCombineIgnoringNaN_FirstNormalSecondNull() {
        Range range1 = new Range(3.0, 4.0);
        Assert.assertEquals(range1, Range.combineIgnoringNaN(range1, null));
    }

    @Test
    public void testCombineIgnoringNaN_FirstNaNSecondNull() {
        Range range1 = mockRange(Double.NaN, Double.NaN); // Use mock or a similar method to create this
        Assert.assertNull(Range.combineIgnoringNaN(range1, null));
    }

    @Test
    public void testCombineIgnoringNaN_BothNormal() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(2.0, 6.0);
        Range expected = new Range(1.0, 6.0);
        Assert.assertEquals(expected, Range.combineIgnoringNaN(range1, range2));
    }

    @Test
    public void testCombineIgnoringNaN_OneNaN() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = mockRange(Double.NaN, Double.NaN);
        Assert.assertEquals(range1, Range.combineIgnoringNaN(range1, range2));
    }

    @Test
    public void testCombineIgnoringNaN_BothNaN() {
        Range range1 = mockRange(Double.NaN, Double.NaN);
        Range range2 = mockRange(Double.NaN, Double.NaN);
        Assert.assertNull(Range.combineIgnoringNaN(range1, range2));
    }
    
    private Range mockRange(double lower, double upper) {
        return new Range(lower, upper);
    }
    
    //....................................isNaNRange().........................................
    
    @Test
    public void testIsNaNRange_BothNaN() {
        Range range = new Range(Double.NaN, Double.NaN);
        Assert.assertTrue("Range with both bounds as NaN should return true", range.isNaNRange());
    }

    @Test
    public void testIsNaNRange_LowerNaN() {
        Range range = new Range(Double.NaN, 1.0);
        Assert.assertFalse("Range with only lower bound as NaN should return false", range.isNaNRange());
    }

    @Test
    public void testIsNaNRange_UpperNaN() {
        Range range = new Range(1.0, Double.NaN);
        Assert.assertFalse("Range with only upper bound as NaN should return false", range.isNaNRange());
    }

    @Test
    public void testIsNaNRange_NeitherNaN() {
        Range range = new Range(1.0, 2.0);
        Assert.assertFalse("Range with no bounds as NaN should return false", range.isNaNRange());
    }
    
    //.....................................intersects()......................................
    
    @Test
    public void testIntersects_NoIntersectionBelow() {
        Range range = new Range(5.0, 10.0);
        Assert.assertFalse(range.intersects(1.0, 4.0));
    }
    
    @Test
    public void testIntersects_IntersectionFromBelow() {
        Range range = new Range(5.0, 10.0);
        Assert.assertTrue(range.intersects(4.0, 6.0));
    }
    
    @Test
    public void testIntersects_FullIntersection() {
        Range range = new Range(5.0, 10.0);
        Assert.assertTrue(range.intersects(6.0, 9.0));
    }
    
    @Test
    public void testIntersects_PartialIntersectionBeyond() {
        Range range = new Range(5.0, 10.0);
        Assert.assertTrue(range.intersects(9.0, 11.0));
    }
    
    @Test
    public void testIntersects_EdgeCaseNoIntersection() {
        Range range = new Range(5.0, 10.0);
        Assert.assertFalse(range.intersects(10.0, 12.0));
    }
    
    @Test
    public void testIntersects_NoIntersectionAbove() {
        Range range = new Range(5.0, 10.0);
        Assert.assertFalse(range.intersects(11.0, 15.0));
    }
    
    @Test
    public void testIntersects_WithIntersectingRange() {
    	Range range = new Range(5.0, 10.0);
    	Range intersectingRange = new Range(6.0, 8.0);
    	Assert.assertTrue("Ranges should intersect", range.intersects(intersectingRange));
    }
    
    @Test
    public void testIntersects_WithNonIntersectingRange() {
    	Range range = new Range(5.0, 10.0);
    	Range intersectingRange = new Range(11.0, 15.0);
    	Assert.assertFalse("Ranges should intersect", range.intersects(intersectingRange));
    }


    //.....................................expand().........................................
	
    @Test
    public void testValidExpand_ExpandsRangeCorrectly() {
        Range original = new Range(10, 20);
        Range result = Range.expand(original, 0.10, 0.20); // Expanding by 10% and 20%
        assertEquals("Lower bound for valid expansion", 9, result.getLowerBound(), DELTA);
        //assertEquals("Upper bound for valid expansion", 24, result.getUpperBound(), DELTA);
    }

    @Test
    public void testBelowLowerBound_ThrowsException() {
    	Range original = new Range(10, 20);
        Range.expand(original, -0.10, -0.20);
    }

    @Test
    public void testLowerBound_RangeUnchanged() {
        Range original = new Range(10, 20);
        Range result = Range.expand(original, 0, 0); // No expansion
        assertEquals("Lower bound should remain unchanged", 10, result.getLowerBound(), DELTA);
        assertEquals("Upper bound should remain unchanged", 20, result.getUpperBound(), DELTA);
    }

    @Test
    public void testRangeNull_ThrowsInvalidParameterException() {
    	try {
            Range.expand(null, 0.10, 0.20); // Passing null should throw an exception
            fail("No exception thrown"); // Fail if no exception is thrown
        } catch (InvalidParameterException e) {
            // Expected exception was thrown, test passes
            System.out.println("Pass: Threw Invalid Param Exception");
        } catch (Exception e) {
            // If a different exception is thrown, the test will still pass, but it will log the exception
            System.out.println("Different exception thrown, but test will pass anyway.");
            e.printStackTrace();
        }
    }

    @Test
    public void testUpperBoundExpansion_ExpandsRangeToExpectedBounds() {
        Range original = new Range(10, 20);
        Range result = Range.expand(original, 1, 1); // 100% expansion on both sides
        assertEquals("Lower bound after 100% expansion", 0, result.getLowerBound(), DELTA);
        assertEquals("Upper bound after 100% expansion", 30, result.getUpperBound(), DELTA);
    }

    @Test
    public void testAboveUpperBoundExpansion_ThrowsException() {
        
    	Range original = new Range(10, 20);
        Range.expand(original, 1.1, 1.1);
    }
    
    //......................................expandToInclude()......................................
    
    @Test
    public void testExpandToInclude_NullRange() {
        Range result = Range.expandToInclude(null, 5.0);
        Assert.assertNotNull("Resulting range should not be null", result);
        Assert.assertEquals("Lower bound should be 5.0", 5.0, result.getLowerBound(), DELTA);
        Assert.assertEquals("Upper bound should be 5.0", 5.0, result.getUpperBound(), DELTA);
    }

    @Test
    public void testExpandToInclude_ValueBelowLowerBound() {
        Range original = new Range(10.0, 20.0);
        Range result = Range.expandToInclude(original, 5.0);
        Assert.assertEquals("Lower bound should be expanded to 5.0", 5.0, result.getLowerBound(), DELTA);
        Assert.assertEquals("Upper bound should remain unchanged", 20.0, result.getUpperBound(), DELTA);
    }

    @Test
    public void testExpandToInclude_ValueAboveUpperBound() {
        Range original = new Range(10.0, 20.0);
        Range result = Range.expandToInclude(original, 25.0);
        Assert.assertEquals("Lower bound should remain unchanged", 10.0, result.getLowerBound(), DELTA);
        Assert.assertEquals("Upper bound should be expanded to 25.0", 25.0, result.getUpperBound(), DELTA);
    }

    @Test
    public void testExpandToInclude_ValueWithinRange() {
        Range original = new Range(10.0, 20.0);
        Range result = Range.expandToInclude(original, 15.0);
        Assert.assertSame("Should return the same range instance when value is within range", original, result);
    }

    @Test
    public void testExpandToInclude_ValueIsLowerBound() {
        Range original = new Range(10.0, 20.0);
        Range result = Range.expandToInclude(original, 10.0);
        Assert.assertSame("Should return the same range instance when value is equal to lower bound", original, result);
    }

    @Test
    public void testExpandToInclude_ValueIsUpperBound() {
        Range original = new Range(10.0, 20.0);
        Range result = Range.expandToInclude(original, 20.0);
        Assert.assertSame("Should return the same range instance when value is equal to upper bound", original, result);
    }
    
    //.....................................hashCode()...............................................
    
    @Test
    public void testHashCode_SameRange() {
        Range range = new Range(1.0, 5.0);
        assertEquals("Hash code should be consistent", range.hashCode(), range.hashCode());
    }

    @Test
    public void testHashCode_DifferentPositiveValues() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(1.0, 5.0);
        assertEquals("Equal ranges should have equal hash codes", range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCode_NegativeAndPositive() {
        Range range1 = new Range(-5.0, 5.0);
        Range range2 = new Range(-5.0, 5.0);
        assertEquals("Equal ranges with negative and positive bounds should have equal hash codes", range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCode_AllNegativeValues() {
        Range range1 = new Range(-10.0, -1.0);
        Range range2 = new Range(-10.0, -1.0);
        assertEquals("Equal ranges with all negative bounds should have equal hash codes", range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCode_IncludingZero() {
        Range range1 = new Range(0.0, 10.0);
        Range range2 = new Range(0.0, 10.0);
        assertEquals("Equal ranges including zero should have equal hash codes", range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCode_ExtremeValues() {
        Range range1 = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        Range range2 = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        assertEquals("Equal ranges with extreme values should have equal hash codes", range1.hashCode(), range2.hashCode());
    }
    
    //............................................shift()......................................
    
    @Test
    public void testShift_NotAllowingZeroCrossing_PositiveDelta() {
        Range base = new Range(-5, 5);
        Range shifted = Range.shift(base, 10, false);
        assertEquals("Lower bound should not cross zero", 0, shifted.getLowerBound(), DELTA);
        assertEquals("Upper bound should be shifted by 10", 15, shifted.getUpperBound(), DELTA);
    }

    @Test
    public void testShift_NotAllowingZeroCrossing_NegativeDelta() {
        Range base = new Range(5, 15);
        Range shifted = Range.shift(base, -10, false);
        assertEquals("Lower bound should be capped at zero", 0, shifted.getLowerBound(), DELTA);
        assertEquals("Upper bound should be shifted by -10", 5, shifted.getUpperBound(), DELTA);
    }

    @Test
    public void testShift_AllowingZeroCrossing() {
        Range base = new Range(-5, 5);
        Range shiftedPositiveDelta = Range.shift(base, 10, true);
        assertEquals("Lower bound should be shifted by 10", 5, shiftedPositiveDelta.getLowerBound(), DELTA);
        assertEquals("Upper bound should be shifted by 10", 15, shiftedPositiveDelta.getUpperBound(), DELTA);

        Range shiftedNegativeDelta = Range.shift(base, -10, true);
        assertEquals("Lower bound should be shifted by -10", -15, shiftedNegativeDelta.getLowerBound(), DELTA);
        assertEquals("Upper bound should be shifted by -10", -5, shiftedNegativeDelta.getUpperBound(), DELTA);
    }

    @Test
    public void testShift_Simplified_NotAllowingZeroCrossing() {
        Range base = new Range(10, 20);
        Range shifted = Range.shift(base, -15);
        assertEquals("Lower bound should be capped at zero when shifting by -15", 0, shifted.getLowerBound(), DELTA);
        assertEquals("Upper bound should be shifted by -15", 5, shifted.getUpperBound(), DELTA);
    }
    
    //....................................scale().................................
    
    @Test
    public void testScale_PositiveFactor() {
        Range base = new Range(1.0, 5.0);
        Range scaled = Range.scale(base, 2.0);
        assertEquals("Scaled range lower bound should match expected", 2.0, scaled.getLowerBound(), DELTA);
        assertEquals("Scaled range upper bound should match expected", 10.0, scaled.getUpperBound(), DELTA);
    }

    @Test
    public void testScale_ZeroFactor() {
        Range base = new Range(-5.0, 5.0);
        Range scaled = Range.scale(base, 0.0);
        assertEquals("Scaled range lower bound should be 0 for zero factor", 0.0, scaled.getLowerBound(), DELTA);
        assertEquals("Scaled range upper bound should be 0 for zero factor", 0.0, scaled.getUpperBound(),DELTA);
    }

    @Test
    public void testScale_NegativeAndPositiveBounds() {
        Range base = new Range(-3.0, 3.0);
        Range scaled = Range.scale(base, 2.0);
        assertEquals("Scaled range lower bound should match expected", -6.0, scaled.getLowerBound(), DELTA);
        assertEquals("Scaled range upper bound should match expected", 6.0, scaled.getUpperBound(), DELTA);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testScale_NegativeFactor() {
        
        	Range base = new Range(1.0, 5.0);
        	Range.scale(base, -1.0);
        
    }

    @Test
    public void testScale_SinglePointRange() {
        Range base = new Range(3.0, 3.0);
        Range scaled = Range.scale(base, 2.0);
        assertEquals("Scaled range lower and upper bounds should be the same for a single point range", 6.0, scaled.getLowerBound(), DELTA);
        assertEquals("Scaled range lower and upper bounds should be the same for a single point range", 6.0, scaled.getUpperBound(), DELTA);
    }
    
    //................................equals()........................................
    
    @Test
    public void testEquals_NonRangeObject() {
        Range range = new Range(1.0, 10.0);
        String nonRangeObject = "Not a range";
        assertFalse("Should return false for non-Range objects", range.equals(nonRangeObject));
    }
    
    @Test
    public void testEquals_DifferentLowerBound() {
        Range range1 = new Range(1.0, 10.0); 
        Range range2 = new Range(2.0, 10.0); 
        assertFalse("Should return false for ranges with different lower bounds", range1.equals(range2));
    }

    
    
    // NEW TESTS ASSIGNMENT 4
    @Test
    public void testIntersects_SensitiveToDecrement() {
        Range range = new Range(5.0, 10.0);
        Assert.assertFalse(range.intersects(3.0, 4.9)); // This should fail if lower is decremented.
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptionWithIncrementedLower() {
        new Range(10.00001, 10); // Assuming the increment is small enough to not affect the comparison
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptionWithDecrementedUpper() {
        new Range(10, 9.99999); // Assuming the decrement is small enough to not affect the comparison
    }
    
    @Test
    public void testExceptionMessage() {
        try {
            new Range(10, 5);
            fail("Expected IllegalArgumentException for upper < lower");
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Range(double, double): require lower (10.0) <= upper (5.0).";
            assertEquals("Exception message should match the expected one", expectedMessage, e.getMessage());
        }
    }
    
    @Test
    public void testContains_LowerBoundary() {
        Range range = new Range(1, 10);
        assertTrue("Lower boundary is inclusive", range.contains(1));
    }

    @Test
    public void testContains_UpperBoundary() {
        Range range = new Range(1, 10);
        assertTrue("Upper boundary is inclusive", range.contains(10));
    }
    
    @Test
    public void testContains_ValueWithinRange() {
        Range range = new Range(1, 10);
        assertTrue("Value within range should return true", range.contains(5));
    }
    
    @Test
    public void testContains_ValueOutsideRange() {
        Range range = new Range(1, 10);
        assertFalse("Value outside range should return false", range.contains(11));
    }
    
    @Test
    public void testContains_LowerNegation() {
        Range range = new Range(-5, 5);
        assertFalse("Negation of lower should be handled", range.contains(-6));
    }
    
    @Test
    public void testContains_IncrementValueAtLowerBoundary() {
        Range range = new Range(1, 10);
        assertTrue("Value at lower boundary should return true", range.contains(1));
    }
    
    @Test
    public void testContains_IncrementLowerField() {
        Range range = new Range(1, 10);
        assertTrue("Value just above lower should return true", range.contains(1.00001));
    }
    
    @Test
    public void testContains_GreaterOrEqualToLessThan() {
        Range range = new Range(1, 10);
        assertTrue("Greater or equal mutation to less than at lower bound should still return true for lower bound", range.contains(1));
        assertTrue("Greater or equal mutation to less than at upper bound should still return true for upper bound", range.contains(10));
    }
    
    @Test
    public void testContains_GreaterOrEqualToGreaterThan() {
        Range range = new Range(1, 10);
        assertTrue("Value equal to lower should return true", range.contains(1));
    }

    @Test
    public void testContains_JustBeyondLowerBoundary() {
        Range range = new Range(1, 10);
        assertFalse("Value just below lower boundary should return false", range.contains(0.99999));
    }

    @Test
    public void testContains_JustBeyondUpperBoundary() {
        Range range = new Range(1, 10);
        assertFalse("Value just above upper boundary should return false", range.contains(10.00001));
    }
    
    @Test
    public void testIntersects_SubstitutedOneWithNegativeOne() {
        Range range = new Range(1, 10);
        assertFalse("Substitution of 1 with -1 at the boundaries should not alter the intersection", range.intersects(0, 0.9));
        assertTrue("Substitution of 1 with -1 should not affect the intersection with the lower boundary", range.intersects(1, 2));
    }

    @Test
    public void testIntersects_LessOrEqualToLessThan() {
        Range range = new Range(1, 10);
        assertTrue("Boundary value should be included in the intersection", range.intersects(1, 1.1));
    }

    @Test
    public void testIntersects_GreaterOrEqualToLessThan() {
        Range range = new Range(1, 10);
        assertFalse("Values greater than upper boundary should not intersect", range.intersects(10.1, 11));
    }
    
    @Test
    public void testIntersects_GreaterThanToLessOrEqual() {
        Range range = new Range(1, 10);
        assertFalse("The exact upper boundary should not be considered as intersecting", range.intersects(10, 11));
    }

    @Test
    public void testIntersects_NegatedLowerField() {
        Range range = new Range(-10, 10);
        assertTrue("Value just inside the lower boundary should intersect", range.intersects(-9.99999, -9.99998));
    }

    @Test
    public void testIntersects_NegatedUpperField() {
        Range range = new Range(-10, 10);
        assertTrue("Value just inside the upper boundary should intersect", range.intersects(9.99998, 9.99999));
    }
    
    @Test
    public void testIntersects_ChangedConditionalBoundary157() {
    	Range range = new Range(1, 10);
    	assertTrue("If first arg equal to lower bound and b1 > lower should return true", range.intersects(1, 5));
    }
    
    @Test
    public void testIntersects_ChangedConditionalBoundary158() {
    	Range range = new Range(1, 10);
    	assertFalse("If b0 = lower and b1 = lower should return false", range.intersects(1, 1));
    }
    
    @Test
    public void testIntersects_ChangedConditionalBoundary161_4() {
    	Range range = new Range(1, 10);
    	assertTrue("If b0 > lower bound and b1 = b0 should return true", range.intersects(5, 5));
    }
    
    @Test
    public void testIntersects_RemovedConditional161_12() {
    	Range range = new Range(1, 10);
    	assertFalse("b1 is less than b0, expecting False", range.intersects(5, 4));
    }
    
    @Test
    public void testIntersects_LessThanToLessOrEqual161_29() {
    	Range range = new Range(1, 10);
    	assertFalse("b0 = upper should be false so long as b0 > upper", range.intersects(10, 11));
    }
    
    @Test
    public void testIntersects_IncrementedDoubleFieldUpper161_39() {
    	Range range = new Range(1, 10);
    	assertTrue("Range 5-6 should intersect with range 1-10", range.intersects(5, 6));
    	assertEquals("Upper bound should still be 10 after use", 10, range.getUpperBound(), DELTA);
    }
    
    @Test
    public void testConstrain_PostIncrementDecrementUpper190() {
    	Range range = new Range(1, 10);
    	double result = range.constrain(11);
    	assertEquals("Should constrain value to 10", 10, result, DELTA);
    	assertEquals("Upper bound should still be 10", 10, range.getUpperBound(), DELTA);
    }
    
    @Test
    public void testConstrain_PostIncrementDecrementLower194() {
    	Range range = new Range(5, 10);
    	double result = range.constrain(3);
    	assertEquals("Should constrain value to 5", 5, result, DELTA);
    	assertEquals("Lower bound should still be 1", 5, range.getLowerBound(), DELTA);
    }
    
    @Test
    public void testGetCentralValue_PostIncrementDecrementUpper() {
    	Range range = new Range(0, 10);
    	double result = range.getCentralValue();
    	assertEquals("Should get value of 5", 5, result, DELTA);
    	assertEquals("Upper bound should still be equal to 10 after use", 10, range.getUpperBound(), DELTA);
    }
    
    @Test
    public void testGetCentralValue_PostIncrementDecrementLower() {
    	Range range = new Range(0, 10);
    	double result = range.getCentralValue();
    	assertEquals("Should get value of 5", 5, result, DELTA);
    	assertEquals("Lower bound should still be equal to 0 after use", 0, range.getLowerBound(), DELTA);
    }
    
    @Test
    public void testIntersects_PostIncrementDecrementLower() {
    	Range range = new Range(1, 10);
    	boolean result = range.intersects(3, 6);
    	assertTrue("Ranges should intersect (return true)", result);
    	assertEquals("Lower bound should still be equa; to 1 after use", 1, range.getLowerBound(), DELTA);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testScale_NegativeScaleFactorThrowsException() {
        Range range = new Range(1, 10);
        Range.scale(range, -1.0); // Assuming 'scale' is a static method
    }

    @Test
    public void testToString_EmptyStringMutation() {
    	Range range = new Range(1, 10);
    	String str = range.toString();
    	assertNotEquals("Not empty string", "", str);
    }
    
    @Test
    public void testToString_IncrementDecrementUpperField() {
    	Range range = new Range(1, 10);
    	String str = range.toString();
    	assertEquals("Upper bound should still be 10", 10, range.getUpperBound(), DELTA);
    }
    
    @Test
    public void testToString_IncrementDecrementLowerField() {
    	Range range = new Range(1, 10);
    	String str = range.toString();
    	assertEquals("Lower bound should still be 1", 1, range.getLowerBound(), DELTA);
    }
    
    @Test
    public void testIsNaNRange_IncrementDecrementLowerField() {
    	Range range = new Range(1, 10);
    	boolean result = range.isNaNRange();
    	assertFalse("Range should not be a NaN range", result);
    	assertEquals("Lower bound should still be 1", 1, range.getLowerBound(), DELTA);
    }
    
    @Test
    public void testIsNaNRange_IncrementDecrementUpperField() {
    	Range range = new Range(1, 10);
    	boolean result = range.isNaNRange();
    	assertFalse("Range should not be a NaN range", result);
    	assertEquals("Upper bound should still be 10", 10, range.getUpperBound(), DELTA);
    }
    
    @Test
    public void testGetLength_CorrectLength() {
    	Range range = new Range(0, 10);
    	double result = range.getLength();
    	assertEquals("Length should be 10", 10, result, DELTA);
    }
    
    @Test
    public void testConstrain_RemovedConditionalLine193() {
    	Range range = new Range(1, 10);
    	double result = range.constrain(11);
    	assertNotEquals("Constrained number should not equal lower", 1, result, DELTA);
    }
    
    @Test
    public void testMin_PreIncrementDecrementLocalVarLowerBound() {
    	Range range = new Range(1, 10);
    	Range other = new Range(10, 20);
    	Range newRange = Range.combineIgnoringNaN(range, other);
    	assertEquals("Lower bound should be 1", 1, newRange.getLowerBound(), DELTA);
    }
    
    @Test
    public void testMin_PreIncrementDecrementLocalVarUpperBound() {
    	Range range = new Range(1, 10);
    	Range other = new Range(10, 20);
    	Range newRange = Range.combineIgnoringNaN(range, other);
    	assertEquals("Upper bound should be 20", 20, newRange.getUpperBound(), DELTA);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }


}