**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group 14:      |     |
| -------------- | --- |
|Thomas Mattern                |     |
|Feranmi Falade            |     |
|Alessandro Baldassarre           |     |
|Sudarshan Naicker           |     |

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction


This lab builds upon the black box unit tests developed in the previous assignment. Initially, we will familiarize themselves with the SUT and learn a new tool such as EclEmma, which was choosen as it is already integrated into Eclipse. Subsequently, we aim to enhance the test cases created in Assignment 2 by employing white box testing techniques tp build on the existing black box approaches.

The objectives are as follows:
1. Measure code coverage using EclEmma.
2. Perform manual data-flow coverage calculations to understand the intricacies of the code flow.
3. Develop new test cases designed to increase code coverage, ensuring a more comprehensive testing suite alongside black box testing.
   
Through these activities, we will gain deeper insights into both the theoretical and practical aspects of combining white box and black box testing methodologies to achieve optimal code coverage and test effectiveness. This will also provide us with an opportunity to learn how to improve code coverage through white box testing.

# 2 Manual data-flow coverage calculations for X and Y methods
# `calculateColumnTotal` Function Analysis

## Data Flow Graph

![Data Flow Graph](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/113630601/2b18babb-1542-4a9f-8001-5236b6f96789)

## Def-Use Sets per Statement

| Definition | Use |
|------------|-----|
| def(1) = {data, column}    | use(1) = {} |
| def(2) = {}    | use(2) = {data} |
| def(3)   = {total}    | use(3)  = {}  |
| def(4)    = {rowCount}   | use(4)  = {data} |
| def(5)  = {r}     | use(5) = {rowCount}  |
| def(6)   = {n}    | use(6)  = {data,r,column} |
| def(7)  = {}     | use(7)  = {n} |
| def(8)  = {total}     | use(8)  = {total,n} |
| def(9)  = {r2}     | use(9)  = {rowCount} |
| def(10) = {n}     | use(10) = {r2,data,column}  |
| def(11)  = {}    | use(11) = {n}  |
| def(12)   = {total}   | use(12)  = {total,n} |
| def(13)  = {}    | use(13)  = {} |



## DU-Pairs per Variable
| Variable  | Def | dcu(n)   | dpu(n) | DU-Pairs            |
|-----------|-----|----------|--------|---------------------|
| data      | 1   | {2,4,8}  |        | (1,2), (1,4), (1,8)  |
| column    | 1   | {4,8}    |        | (1,4), (1,8)        |
| total     | 2   | {6,9}    |        | (2,6), (2,9)        |
| rowCount  | 2   |          | {3,7}  | (2,3), (2,7)        |
| r         | 3   | {5}      | {4}    | (3,4), (3,5)        |
| n         | 4,8 | {6,9}    | {8}    | (4,6), (8,9)        |
| r2        | 7   | {8,10}   |        | (7,8), (7,10)       |


## for each test case show which pairs are covered
| Test                                     | Pairs Covered                 |
|------------------------------------------|-------------------------------|
| `testInvalidData()`                      | (1,2)                         |
| `testValidData()`                        | (1,2), (1,4), (2,3), (2,6), (4,6), (3,4) |
| `testValuesInDataPositive()`             | (1,2), (1,4), (2,3), (2,6), (4,6), (3,4) |
| `testValuesInDataNegative()`             | (1,2), (1,4), (2,3), (2,6), (4,6), (3,4) |
| `columnOutOfRangeUnder()`                | (1,2), (1,4), (2,3), (3,4)    |
| `columnOutOfRangeOver()`                 | (1,2), (1,4), (2,3), (3,4)    |
| `testColumnTotalWithLargeColumns()`      | (1,2), (1,4), (2,3), (2,6), (4,6), (3,4) |
| `testColumnTotalWithEmptyData()`         | (1,2), (2,3), (2,7)           |


## calculate the DU-Pair coverage

Given the unique pairs we identified earlier and the total number of DU pairs calculated, plugging these values into the formula to calculate the coverage. For instance, we covered 7 DU pairs out of a total of 13 unique DU pairs.

DU-Pair Coverage = (Total number of DU pairs in the program/ Number of DU pairs covered by test cases = (7/13)×100 = 53.85%

The DU-Pair coverage is approximately 53.85%

---

# `combine` Function Analysis

## Data Flow Graph

![inal](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/113630601/44e5ae8d-f700-4666-9d14-cc76041e8631)

## Def-Use Sets per Statement
| Definition | Use |
|------------|-----|
| def(1) = {range1, range2}    | use(1) = {} |
| def(2) = {}    | use(2) = {range1} |
| def(3)   = {}    | use(3)  = { range2}  |
| def(4)    = {}   | use(4)  = {range1} |
| def(5) = {l}    | use(5) = {range1,range2} |
| def(6) = {}    | use(6) = {range2} |
| def(7) = {u}    | use(7) = {range1,range2} |
| def(8) = {}    | use(8) = {l,u} |



## DU-Pairs per Variable
| Variable | Def | dcu(n)    | dpu(n) | DU-Pairs                          |
|----------|-----|-----------|--------|-----------------------------------|
| range1   | 1   | {4,5,7}   | {2}    | (1,2), (1,4), (1,5), (1,7)         |
| range2   | 1   |  {5,6,7}  | {3}    | (1,5), (1,6), (1,7)               |
| l        | 5   | {8}       |        | (5,8)                             |
| u        | 7   | {8}       |        | (7,8)                             |




## for each test case show which pairs are covered
| Test                                  | Pairs Covered         |
|---------------------------------------|-----------------------|
| `testRange1NullRange2Valid()`           | (1,2), (1,4)          |
| `testRange1ValidRange2Null()`           | (1,2), (1,6)          |
| `testBothRangesNull()    `              | (1,2), (1,4)          |
| `testBothRangesValidSubsumedRange() `   | (7,8), (5,8), (1,5), (1,7) |
| `testBothRangesValidSameValues() `      | (7,8), (5,8), (1,5), (1,7) |
| `testBothRangesValidNegatives()  `      | (7,8), (5,8), (1,5), (1,7) |
| `testBothRangesValidPositvies()   `     | (7,8), (5,8), (1,5), (1,7) |
| `testBothRangesValidNeg&pos()     `     | (7,8), (5,8), (1,5), (1,7) |
| `testBothRangesOverlapping()    `       | (7,8), (5,8), (1,5), (1,7) |
| `testLargeValue()     `                 | (7,8), (5,8), (1,5), (1,7) |
| `testInvaliedRangeSubsumed()   `        | (7,8), (5,8), (1,5), (1,7) |
| `testRangeInsideValid() `               | (7,8), (5,8), (1,5), (1,7) |
| `testDouble()`                          | (7,8), (5,8), (1,5), (1,7) |


## calculate the DU-Pair coverage
All the 7 unique DU-pairs where covered by our tests. 
DU-Pair Coverage = (Total number of DU pairs in the program/ Number of DU pairs covered by test cases = (7/7)×100 = 100%




# 3 A detailed description of the testing strategy for the new unit test

## 1. Test Plan Overview

**Objective**: Increase the code coverage of `org.jfree.data.DataUtilities` and `org.jfree.data.Range` classes to meet the minimum coverage requirements: 90% statement coverage, 70% branch coverage, and 60% condition coverage.

## 2. Test Development Strategy

**Test Case Design**:

- Follow a systematic approach to create test cases that cover positive scenarios, negative scenarios, edge cases, and failure conditions.
- Design test cases to verify each method's behavior against its Javadoc specifications.
- Use parameterized tests where applicable to efficiently cover a wide range of inputs.

**Coverage Goals**:

- **Statement Coverage**: Ensure 90% statement coverage (minimum).
- **Branch Coverage**: Ensure 70% branch coverage (minimum).
- **Condition Coverage**: Ensure 60% condition coverage (minimum). -> replace with method coverage for EclEmma

**Test Case Organization**:

- Organize test cases logically by method and functionality.
- Make sure to give tests descriptive names to be clear about what is being tested.

## 3. Test Environment Setup

- **JUnit**: Utilize JUnit for writing and executing test cases.
- **Coverage Tool**: Use a code coverage tool (in this case EclEmma) to calculate the code coverage for the SUT.

## 4. Test Case Development and Execution

- **Individual Task Assignment**: Divide the methods in `DataUtilities` and `Range` classes among team members for test case development.
- **Implementation**: Develop test cases according to the designated assignments, following the test case design guidelines.
- **Execution**: Run the test suite using JUnit. Verify that all tests pass and meet the expected outcomes as per the specifications. Identify and document any failing tests that indicate defects in the SUT.

## 5. Review and Refinement

- **Peer Review**: Exchange test cases among team members for peer review. Look for missed scenarios, redundancy, potential simplifications, and adherence to the test plan.
- **Refinement**: Update test cases based on feedback from peer reviews. Aim to eliminate gaps in coverage and improve test effectiveness.

## 6. Coverage Measurement and Reporting

- **Measurement**: Use the selected coverage tool to measure the test suite's coverage (line, branch, method)
- **Analysis**: Analyze coverage reports to identify any areas lacking coverage. Develop additional test cases as needed to address these gaps.
- **Documentation**: Document detailed coverage information for each class and method in a tabular format. Include observations and insights gained from the coverage analysis.

## 7. Conclusion and Submission

- **Final Review**: Perform a final review of the test suite and coverage reports. Ensure all objectives have been met and documentation is complete.
- **Submission**: Compile the test suite, coverage reports, and lab report detailing the testing process, test cases, coverage analysis, and any defects identified in the SUT. Submit the complete package for evaluation.

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

## Test Case 1: 
**Coverage**: Boundary Value Analysis and Statement Coverage

**Test**: `testBothRangesValidNegAndPos()`

This test case checks the behavior of the Range.combine() method when combining a negative range with a positive range, ensuring it correctly handles ranges that span across zero, which is a boundary condition. The test also uses two valid ranges thus ignoring the first two if statements and ensuring statement coverage for the rest of the method (the prior two if statements are covered in other tests). It increases code coverage by testing a critical edge case that might not be apparent during initial implementation. The assertion validates that the resulting range correctly spans from the negative lower bound to the positive upper bound, demonstrating the method's ability to handle ranges that cross the zero boundary.

## Test Case 2:
**Coverage**: Branch Coverage

**Test**: `testConstrain_ValueBelowRange()`

This test case focuses on the Range.constrain() method, specifically targeting the scenario where a value is below the range's lower bound. It increases code coverage by exercising a code branch that constrains the input value to the range's lower bound, ensuring that the method correctly handles inputs outside the range. By covering this branch, the test ensures the method's robustness in maintaining range integrity when faced with out-of-bound values.

## Test Case 3: 
**Coverage**: Branch Coverage and Statement Coverage

**Test**: `testIntersects_FullIntersection()`

This test case focuses on the scenario of the intersects method where the range provided resides inside the range that called the method. This
test increases code coverage by entering the second branch of the method resulting in full statement coverage (along with another test that checks the first branch) and full branch coverage since both branches are entered. This ensures the method correctly handles intersection of a range within the existing range. 

## Test Case 4: 
**Coverage**: Branch Coverage and line coverage

**Test**: `testCalculateColumnTotalWithNegativeRowCount()`

This test case covers the for (int r2 = 0; r2 > rowCount; r2++) loop in the calculateColumnTotal(Values2D data, int column) method, which will check weather the r2 > rowcount and will add the value in the (rowcount, column) to the total variable to find the total sum of values in the entire column, but since r2 = 0 this means the the rowcount should be negative this is theoretically not possible as we cant have negative row indexes. However, we have made the test to cover the statements. 

## Test Case 5: 
**Coverage**: Branch Coverage and line coverage

**Test**: `testCalculateRowTotalForCoverage()`

This test covers the entrite code for the method calculateRowTotal(Values2D data, int row, int[] validCols). this method is used to get the sum of all the values in a specific set of columns from a row instead of finding the total for all the columns, we can do so by passing an int array of columns to be considered in the int[] validcols argument.


# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

(A2) Initial instruction (statement) coverage using EcLEmma coverage tool:
![image](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/114002112/c3966275-f4e9-4ee5-80d9-ccc7bdebf78f)

(A2) Initial branch (decision) coverage using EcLEmma coverage tool:
![image](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/114002112/5404492f-f9d7-4253-b935-1854231817e5)

(A2) Initial method coverage using EcLEmma coverage tool:
![image](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/114002112/ff296190-6428-4c34-9a49-d7f96be6fbd8)

(A2) Initial statement coverage for Range class using EcLEmma coverage tool:
![image](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/113554225/36b65539-4a3d-471f-bcd8-fc705bae97cd)

(A2) Initial branch coverage for Range class using EcLEmma coverage tool:
![image](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/113554225/3b804257-50bb-4b8d-885e-4d3f2ab4c344)

(A2) Initial method coverage for Range class using EcLEmma coverage tool:
![image](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/113554225/b190bf50-56de-498f-9471-767519bd25b4)

## Range Statement Coverage (Line Counters):
![image](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/113554225/c285d7a3-ab3c-478f-81b1-bffdfd9f5ecb)

Note: Some methods could not achieve higher statement coverage due to the constructor restricting certain conditions (lower bound could never be greater than upper bound).

## Range Branch Coverage (Branch Counters):
![image](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/113554225/2d09a580-3fe8-4d5f-9630-9044ea5223b7)

## Range Method Coverage (Method Counters):
![image](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/113554225/0fe9d93f-b018-4a97-a88d-7dd9903ded73)

## DataUtilities Statement Coverage
![as3p1](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/113630601/5f04e173-9a6a-491d-87e3-4a8933d6546e)
## DataUtilities Brance  Coverage
![as3p2](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/113630601/16c17754-793e-412d-87c0-39662da95689)

## DataUtilities Method Coverage
![method](https://github.com/seng438-winter-2024/seng438-a3-Group-14/assets/113630601/7e0d2c31-7feb-4ab9-9f6a-1eb715a08d0f)


# 6 Pros and Cons of coverage tools used and Metrics you report
In our project, we employed EclEmma, a code coverage tool integrated with Eclipse, notable for its user-friendly interface and the simplicity of setup. It allows for the accurate calculation of coverage metrics effortlessly with just a click, presenting the results in an intuitive manner using a color coding system of green, yellow, and red. This feature made it particularly user-friendly, and throughout its use, EclEmma consistently reported accurate coverage measurements without any crashes.

Despite its advantages, such as an abundance of online resources and its straightforward functionality, EclEmma does have some limitations. Specifically, its lack of condition coverage functionality restricted our ability to fully assess our test coverage. This limitation meant that we had to learn to integrate additional strategy method coverage.

Thus, while EclEmma excels in ease of use, accessibility, and reliability in reporting coverage metrics, its deficiency in providing condition coverage required us to seek supplementary solutions to ensure thorough test coverage analysis.

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

A comparison on the advantages and disadvantages of requirements-based test generation versus coverage-based test generation is many benefits and limitations for each method. Requirements-based test generation focuses on creating tests that align with the predefined requirements of the system, ensuring that each functionality specified by the user or stakeholders is adequately tested. This method is advantageous because it directly targets the expected behaviors and outcomes, making sure that the software meets the users' needs. However, it may overlook errors that fall outside the specified requirements, potentially missing bugs that could affect system performance or security.

On the other hand, coverage-based test generation aims to ensure that every line of code is executed during testing, thereby identifying errors that may not be covered by the requirements. This approach is thorough, as it operates under the principle that untested code is a common source of errors. By striving for complete coverage, this method can uncover issues that requirements-based testing might miss, providing a more comprehensive evaluation of the software's reliability. Despite achieving high code coverage metrics, a method can still pass without actually identifying all bugs present in the program. This occurs because coverage-based testing focuses on executing as much of the codebase as possible without necessarily understanding the business logic or possible interactions between different parts of the application.

The integration of both approaches can offer a robust testing strategy. Requirements-based testing ensures the software fulfills its intended purpose, while coverage-based testing enhances error detection outside of the pre-defined requirments. Together, they provide a balanced and effective means to achieve high-quality software.


# 8 A discussion on how the team work/effort was divided and managed

We initially met to familiarize ourselves with the System Under Test (SUT) and ensured that everyone had a functional testing environment in Eclipse. We then discussed how to split our work effectively, Thomas and Feranmi completed tasks 3.1 and 3.2, while Alessandro and Sudarshan took on task 3.3. This arrangement ensured an equal distribution of work while maintaining open communication for assistance when needed.

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab
Initially, we faced challenges in grasping the functionality of EclEmma and comprehending which lines were utilized or unused for each test case. However, through experimentation and exploration of the running tests and the eclipse itself, we gradually deciphered the significance of colors - green, yellow, and red. This enabled us to effectively utilize these indicators. Additionally, we acquired proficiency in performing manual data-flow coverage analysis and assessing code coverage metrics such as branch, line, and method coverage.

# 10 Comments/feedback on the lab itself

We found the lab to be a very informative and helpful resource for setting up our test environment using Eclipse and for support on how to use EclEmma. However, we believe that providing more examples or details on the manual data-flow coverage portion would be beneficial, as we have never completed any of these types of tasks before. Overall, it was a good experience that gave us the opportunity to learn more about white-box testing techniques.

