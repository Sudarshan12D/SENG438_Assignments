**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group 14:      |     |
| -------------- | --- |
|Thomas Mattern                |     |
|Feranmi Falade            |     |
|Alessandro Baldassarre           |     |
|Sudarshan Naicker           |     |

# 1 Introduction

The purpose of this lab was to familiarize us with the process of designing test cases and subsequently implementing them using JUnit and mocking, based on the documentation of the System Under Test (SUT) through a black box approach. The objective was to create automated test code, incorporate methods within a team, and foster a collaborative environment. This involved identifying both successful and unsuccessful test cases to assess the functionality of the system.

# 2 Detailed description of unit test strategy
We began by selecting five methods for testing in the `org.jfree.data.Range` class: `combine`, `getLowerBound`, `constrain`, `expand`, and `getUpperBound`. Subsequently, we identified the domain for each input variable for each of these methods, as well as for the methods in the `org.jfree.data.DataUtilities` class (as illustrated below). We proceeded to organize these into their own input partitions. We then utilized the input partitions to develop a black-box test case design technique based on equivalence classes ensuring that each requirment is tested enough. Additionally, we collectively decided to employ mocking techniques as part of our testing approach, aiming to gain more practical experience in this aspect.

Input partions:

### DataUtilities Class

`calculateColumnTotal(Values2D data, int column)`

Data parameter:
   - C1: If the 'data' parameter is null, it is considered invalid.
   - C2: If the 'data' parameter is not null, it is considered valid.
   - C3: If the ‘data’ parameter is of invalid type, it is considered invalid.

Values in the data parameter:
   - C4: If a value in the 'data' is positive, it is considered valid.
   - C5: If a value in the 'data' is negative, it is considered valid.

Values2D structure in data:
   - C6: If there are zero columns or rows in the 'data', it is considered valid.
   - C7: If there is one to a large number of columns or rows in the 'data', it is considered valid.
   - C8: If ‘data’ is empty, it is considered valid.

Column parameter:
   - C9: If the 'column' parameter is less than 0, it is considered invalid.
   - C10: If the 'column' parameter is within bounds (0 <= column <= upper bound), it is considered valid.
   - C11: If the 'column' parameter is greater than the upper bound, it is considered invalid.

`calculateRowTotal(Values 2D data, int row)`

Data parameter:
   - C1: If the 'data' parameter is null, it is considered invalid.
   - C2: If the 'data' parameter is not null, it is considered valid.
   - C3: If the ‘data’ parameter is of invalid type, it is considered invalid.

Values in the data parameter:
   - C4: If a value in the 'data' is positive, it is considered valid.
   - C5: If a value in the 'data' is negative, it is considered valid.

Values2D structure in data:
   - C6: If there are zero rows in the 'data', it is considered valid.
   - C7: If there is one to a large number of rows in the 'data', it is considered valid.
   - C8: If ‘data’ is empty, it is considered valid.

Row parameter:
   - C9: If the 'row' parameter is less than 0, it is considered invalid.
   - C10: If the 'row' parameter is within bounds (0 <= row <= upper bound), it is considered valid.
   - C11: If the 'row' parameter is greater than the upper bound, it is considered invalid.

`createNumberArray(double[] data)`

9. Data parameter:
   - C1: If the 'data' parameter is null, it is considered invalid.
   - C2: If the 'data' parameter is not null, it is considered valid.
   - C3: If the ‘data’ parameter is of invalid type, it is considered invalid.

10. Number of elements in data:
   - C4: If there are zero elements in the 'data' array, it is considered valid.
   - C5: If there is one to a large number of elements in the 'data' array, it is considered valid.

11. Types of values in data:
   - C6: If there are positive values in the 'data' array, it is considered valid.
   - C7: If there are negative values in the 'data' array, they are considered valid.

`createNumberArray2D(double[][] data)`

12. Data parameter:
   - C30: If the 'data' parameter is null, it is considered invalid.
   - C31: If the 'data' parameter is not null, it is considered valid.
   - C32: If the ‘data’ parameter is of invalid type, it is considered invalid.

13. Number of elements in data:
   - C33: If there are zero elements in the 'data' array, it is considered valid.
   - C34: If there is one to a large number of elements in the 'data' array, it is considered valid.

14. Types of values in data:
   - C35: If there are positive values in the 'data' array, it is considered valid.
   - C36: If there are negative values in the 'data' array, they are considered valid.

`getCumulativePercentages(KeyedValues data)`

15. Data parameter:
   - C1: If the 'data' parameter is null, it is considered invalid.
   - C2: If the 'data' parameter is not null, it is considered valid.
   - C3: If the ‘data’ parameter is of invalid type, it is considered invalid.

16. Types of values in data:
   - C4: If there are positive values in the 'data' array, it is considered valid.
   - C5: If there are negative values in the 'data' array, they are considered valid.


### Range Class

`combine(Range range1, Range range2)`

Range1 parameter:
   - C1: If range 1 is null, it is considered valid and range 2  is returned
   - C2: range1 is not null, it is considered valid.
   - C3: range1 has a large value and is considered valid
   - C4: range1 has a higher value for lower and less value for higher eg: 7 to 1, not valid

Range2 parameter:
   - C5: If  range2 is null, it is considered valid and range 1 is returned
   - C6: range2 is not null, it is considered valid.
   - C7: range2 has a large value and is considered valid
   - C8: range2 has a higher value for lower and less value for higher eg: 7 to 1, not valid

Range1 and Range2:
   - C9:  range1 and range2 are null, it is considered valid, and null is returned
   - C10: range1 and range2 are not null, it is considered valid and returns subsumed range.
   - C11: range1 and range2 have same values, it is considered valid and returns same range.
   - C12: range1 and range2 have Overlapping values, it is valid and returns subsumed range.
   - C13: range1 and  range2 contain negative values, it is valid and returns subsumed range.
   - C14: range1 and  range2 contain positive values, it is valid and returns subsumed range.
   - C15: range1 contains +ve values and  range2 contains -ve values, returns new range
   - C16: range2 is inside of range1, returns subsumed (range1) 
   - C17: range1 or range2 are doubles, is valid returns new range

`getLowerBound()`

 Range Parameter:
    - C1: Lower range value is negative, return the lower range.
   - C2: Lower range value is Positive.
   - C3: Lower range value is negative Double.
   - C4: Lower range value is Positive Double.
   - C5: Lower range value is a very small positive number.
   - C6: Lower range value is a Very large number
   - C7: Lower range value is 0 

`getUpperBound()`

Range Parameter:
   - C1: Upper range value is negative, return the Upper range.
   - C2: Upper range value is Positive.
   - C3: Upper range value is negative Double.
   - C4: Upper range value is Positive Double.
   - C5: Upper range value is a very small positive number.
   - C6: Upper range value is a Very large number
   - C7: Upper range value is 0

`constrain()`

Value Parameter:
   - C1: value within range
   - C2: value above range
   - C3: value below range
   - C4: value is upper bound
   - C5: value is lower bound

   Equivalence Classes:
   - Value within range: The value is between upper and lower bounds of range
   - Value below the range: The value is less than the lower bound of the range
   - Value above the range: The value is greater than the upper bound of the range

   Boundary Value Analysis:
   - Lower Boundary of the range
   - Upper boundary of the range
   - Just below lower boundary
   - Just above upper boundary
   - A value within the range (not on boundaries)

`expand()`

Range parameter:
   - C1: If the 'range' parameter is null, it is considered invalid.
   - C2: If the 'range' parameter is not null, it is considered valid.

Lower Margin:
   - C3: If the 'lowerMargin' is less than 0, it is considered invalid.
   - C4: If the 'lowerMargin' is within bounds (0 <= lowerMargin <= 1), it is considered valid.
   - C5: If the 'lowerMargin' is greater than 1, it is considered invalid.

Upper Margin:
   - C6: If the 'upperMargin' is less than 0, it is considered invalid.
   - C7: If the 'upperMargin' is within bounds (0 <= upperMargin <= 1), it is considered valid.
   - C8: If the 'upperMargin' is greater than 1, it is considered invalid.

Equivalence Classes:
   - Valid range with positive margins: Both margins are positive, leading to an expansion.
   - Valid range with zero margins: both margins are zero, resulting in no change to range
   - Invalid range (null): the range param is null, which should throw an ‘InvalidParameterException’
   - Extreme Margin Values: margins are extremely high, testing the method’s robustness

Boundary Value Analysis:
   - Margins at 0% - no change
   - Margins below lower bound - margins are negative values aka margin < 0
   - Margins within valid range - margins are both a valid num between 0 <= margin <= 1
   - Margins at upper bound - margins equal 1
   - Margins above upper bound - margins > 1

# 3 Test cases developed
#### ALL IMPLEMENTED TEST CASES ARE IN THE DataUtilitiesTest.java and RangeTest.java IN THE REPO 
### DataUtilities Class
`calculateColumnTotal(Values2D data, int column)`
| Case # | Class name      | Method               | Relevant Conditions             | Test Name                           | Pass/Fail |
|--------|-----------------|----------------------|----------------------------------|-------------------------------------|-----------|
| 1      | DataUtilities   | calculateColumnTotal | C1,C3,C10                        | testInvalidData()                   | fail      |
| 2      | DataUtilities   | calculateColumnTotal | C2                               | testValidData()                     | pass      |
| 3      | DataUtilities   | calculateColumnTotal | C2, C4, C10                      | testValuesInDataPositive()          | pass      |
| 4      | DataUtilities   | calculateColumnTotal | C2, C5, C10                      | testValuesInDataNegative()          | pass      |
| 5      | DataUtilities   | calculateColumnTotal | C4,C5,C9                         | columnOutOfRangeUnder()             | fail      |
| 6      | DataUtilities   | calculateColumnTotal | C4,C5,C11                        | columnOutOfRangeOver()              | fail      |
| 7      | DataUtilities   | calculateColumnTotal | C4,C5,C10,C7                     | testColumnTotalWithLargeColumns()   | pass      |
| 8      | DataUtilities   | calculateColumnTotal | C4,C5,C10,C6, C8                 | testColumnTotalWithEmptyData()       | pass      |

<br>

`calculateRowTotal(Values 2D data, int row)`
| Case # | Class name      | Method               | Relevant Conditions        | Test Name                     | Pass/Fail |
|--------|-----------------|----------------------|-----------------------------|-------------------------------|-----------|
| 1      | DataUtilities   | calculateRowTotal    | C1,C3,C10                   | testInvalidDataRow()          | fail      |
| 2      | DataUtilities   | calculateRowTotal    | C2                          | testValidDataRow()            | fail      |
| 3      | DataUtilities   | calculateRowTotal    | C2, C4, C10                 | testValuesInDataPositiveRow() | fail      |
| 4      | DataUtilities   | calculateRowTotal    | C2, C5, C10                 | testValuesInDataNegativeRow() | fail      |
| 5      | DataUtilities   | calculateRowTotal    | C4,C5,C9                    | rowOutOfRangeUnder()          | fail      |
| 6      | DataUtilities   | calculateRowTotal    | C4,C5,C11                   | rowOutOfRangeOver()           | fail      |
| 7      | DataUtilities   | calculateRowTotal    | C4,C5,C10,C7                | testRowTotalWithLargeValue()  | pass      |
| 8      | DataUtilities   | calculateRowTotal    | C4,C5,C10,C6, C8            | testRowTotalWithEmptyData()    | pass     |

<br>

`createNumberArray(double[] data)`
| Case # | Class name      | Method               | Relevant Conditions        | Test Name                     | Pass/Fail |
|--------|-----------------|----------------------|----------------------------|-------------------------------|-----------|
| 1      | DataUtilities   | createNumberArray    | C1                         | testNullDataNuArray()          | fail      |
| 2      | DataUtilities   | createNumberArray    | C2                         | testValidDataNuArray()            | fail      |
| 3      | DataUtilities   | createNumberArray    | C3                         | testInvalidTypeDataNuArray() | error      |
| 4      | DataUtilities   | createNumberArray    | C4                         | testZeroElementsNuArray() | pass      |
| 5      | DataUtilities   | createNumberArray    | C5                         | testLargeNumberofElementsNuArray()          | pass      |
| 6      | DataUtilities   | createNumberArray    | C6                         | testPositiveValuesNuArray()           | fail      |
| 7      | DataUtilities   | createNumberArray    | C7                         | testNegativeValuesNuArray()  | fail      |

<br>

`createNumberArray2D(double[][] data)`
| Case # | Class name      | Method               | Relevant Conditions        | Test Name                     | Pass/Fail |
|--------|-----------------|----------------------|----------------------------|-------------------------------|-----------|
| 1      | DataUtilities   | createNumberArray    | C1                         | testNullData2D()          | fail      |
| 2      | DataUtilities   | createNumberArray    | C2                         | testValidData2D()            | fail      |
| 3      | DataUtilities   | createNumberArray    | C3                         | testInvalidTypeData2D() | error      |
| 4      | DataUtilities   | createNumberArray    | C4                         | testZeroElements2D() | pass      |
| 5      | DataUtilities   | createNumberArray    | C5                         | testLargeNumberofElements2D()          | pass      |
| 6      | DataUtilities   | createNumberArray    | C6                         | testPositiveValues2D()           | fail      |
| 7      | DataUtilities   | createNumberArray    | C7                         | testNegativeValues2D()  | fail      |

<br>

`getCumulativePercentages(KeyedValues data)`
| Case # | Class name      | Method               | Relevant Conditions        | Test Name                     | Pass/Fail |
|--------|-----------------|----------------------|----------------------------|-------------------------------|-----------|
| 1      | DataUtilities   | getCumulativePercentages    | C1                         | testNullDataPercentages()          | fail      |
| 2      | DataUtilities   | getCumulativePercentages    | C2, C4                         | testPositiveValues_ValidData()       | fail      |
| 3      | DataUtilities   | getCumulativePercentages    | C2, C5                         | testNegativeValues_ValidData() | pass      |
| 4      | DataUtilities   | getCumulativePercentages    | C3                         | testInvalidKeyedValueData() | fail      |

### Range Class

`combine(Range range1, Range range2)`
| Case # | Test Name                         | Relevant Conditions | Input Values               | Pass/Fail |
|--------|-----------------------------------|---------------------|----------------------------|-----------|
| 1      | `testRange1NullRange2Valid()`     | C1, C6              | range1: null; range2: 5 to 10 | Pass      |
| 2      | `testRange1ValidRange2Null()`     | C5, C2              | range1: -5 to 10; range2: null | Pass      |
| 3      | `testBothRangesNull()`            | C9                  | range1: null; range2: null | Pass      |
| 4      | `testBothRangesValidSubsumedRange()` | C10, C14          | range1: 5 to 10, range2: 12 to 14 | Fail     |
| 5      | `testBothRangesValidSameValues()` | C11, C14            | range1: 1 to 3, range2: 1 to 3 | Pass      |
| 6      | `testBothRangesValidNegatives()`  | C13, C10            | range1: -10 to -5, range2: -4 to -3 | Fail     |
| 7      | `testBothRangesValidPositives()`  | C14, C12, C10       | range1: 1 to 5, range2: 3 to 7 | Fail     |
| 8      | `testBothRangesValidNeg&pos()`    | C15, C12, C10       | range1: -5 to 0, range2: -1 to 5 | Fail     |
| 9      | `testBothRangesOverlapping()`     | C12, C14, C10       | range1: 5 to 15, range2: 10 to 20 | Fail     |
| 10     | `testLargeValue()`                | C3, C7, C14, C10    | range1: 5 to 1000000000, range2: null | Pass      |
| 11     | `testInvalidRangeSubsumed()`      | C4, C8, C10         | range1: 10 to 5, range2: -3 to -7 | Pass      |
| 12     | `testRangeInsideValid()`          | C16, C14, C10       | range1: 0 to 20, range2: 1 to 4 | Fail     |
| 13     | `testDouble()`                    | C17                 | range1: 1.4 to 2.6, range2: null | Pass      |

Test Case 4, 6, 7, 8, 9, 12 all Gives an Illegal Error since Combine() method is not able to work properly with both non-null range arguments.
<br><br>

`getLowerBound()`
| Case # | Test Name                   | Relevant Conditions | Input Values               | Pass/Fail |
|--------|-----------------------------|---------------------|----------------------------|-----------|
| 1      | `testNegativeLowerBound`    | C1                  | -10 to -1                  | Pass      |
| 2      | `testPositiveLowerBound`    | C2                  | 1 to 10                    | Pass      |
| 3      | `testNegativeDoubleLowerBound` | C3               | -52.96 to 78.99            | Pass      |
| 4      | `testPositiveDoubleLowerBound` | C4               | 1.58 to 10.78              | Pass      |
| 5      | `testSmallLowerBound`       | C5                  | 0.00001 to 10              | Pass      |
| 6      | `testLargeLowerBound`       | C6                  | 100000000 to 1000000000    | Pass      |
| 7      | `testZeroLowerBound`        | C7                  | 0 to 100                   | Pass      |

<br>

`getUpperBound()`
| Case # | Test Name                     | Relevant Conditions | Input Values               | Pass/Fail |
|--------|-------------------------------|---------------------|----------------------------|-----------|
| 1      | `testNegativeUpperBound`      | C1                  | -10 to -1                  | Fail      |
| 2      | `testPositiveUpperBound`      | C2                  | 1 to 10                    | Fail      |
| 3      | `testNegativeDoubleUpperBound`| C3                  | -102.96 to -78.99          | Fail      |
| 4      | `testPositiveDoubleUpperBound`| C4                  | 1.58 to 10.78              | Fail      |
| 5      | `testSmallUpperBound`         | C5                  | -1 to 0.00001              | Fail      |
| 6      | `testLargeUpperBound`         | C6                  | 0 to 1000000000            | Fail      |
| 7      | `testZeroUpperBound`          | C7                  | -1 to 0                    | Fail      |

The Function returns lower Bound value of the range instead of Upper bound hence all tests failed
<br><br>

`constrain()`
| Case # | Test Name                               | Relevant Conditions | Input Values | Pass/Fail |
|--------|-----------------------------------------|---------------------|--------------|-----------|
| 1      | `testConstrain_ValueWithinRange`        | C1                  | 1-10, 5      | Pass      |
| 2      | `testConstrain_ValueEqualsLowerBoundary`| C5                  | 1-10, 1      | Pass      |
| 3      | `testConstrain_ValueEqualsUpperBoundary`| C4                  | 1-10, 10     | Pass      |
| 4      | `testConstrain_ValueBelowRange`         | C3                  | 1-10, 0      | Fail      |
| 5      | `testConstrain_ValueAboveRange`         | C2                  | 1-10, 11     | Pass      |

<br>

`expand()`
| Case # | Test Name                                      | Relevant Conditions | Input Values      | Pass/Fail |
|--------|------------------------------------------------|---------------------|-------------------|-----------|
| 1      | `testValidExpand_ExpandedRangeCorrectly`       | C2,C4,C7            | 10-20, 0.1, 0.2   | Fail      |
| 2      | `testUpperBoundExpand_ThrowsException`         | C2,C4,C7            | 10-20, 1, 1       | Fail      |
| 3      | `testLowerBound_RangeUnchanged`                | C2,C4,C7            | 10-20, 0, 0       | Fail      |
| 4      | `testRangeNull_ThrowsInvalidParameterException`| C1,C4,C7            | null, 0.1, 0.2    | Fail      |
| 5      | `testAboveUpperBoundExpansion_ThrowsException` | C2,C5,C8            | 10-20, 1.1, 1.1   | Fail      |
| 6      | `testBelowLowerBound_ThrowsException`          | C2,C3,C6            | 10-20, -0.1, -0.2 | Fail      |

# 4 How the team work/effort was divided and managed

The team efficiently managed and divided the workload for this assignment. We initially met to familiarize ourselves with the System Under Test (SUT) and ensured that everyone had a functional testing environment in Eclipse. We then discussed how to split our work effectively. Half of our team concentrated on the input partitions, designed relevant test cases, and successfully implemented them in code. Alessandro and Sudarshan collaborated on the functionalities related to `org.jfree.data.Range`, while Thomas and Feranmi collaborated  to handle the methods within `org.jfree.data.DataUtilities`. Throughout the design process, we consistently reviewed our test cases to ensure comprehensive and correct coverage.

# 5 Difficulties encountered, challenges overcome, and lessons learned

At first, we struggled with defining input partitions but resolved it through collaboration and sharing examples. We also learned to effectivly review documentation. Dealing with mocks was challenging due to our unfamiliarity, but we overcame it with examples from each other and researching how they work. This experience taught us the significance of thorough testing and using black-box methods.

# 6 Comments/feedback on the lab itself

We found the lab to be a very informative and helpful resource for setting up our test environment using Eclipse. However, we believe that providing more examples or details on the expected test case format would have been beneficial. Overall, it was a good experience that gave us the opportunity to learn more about mocking, JUnit, and designing test cases.
