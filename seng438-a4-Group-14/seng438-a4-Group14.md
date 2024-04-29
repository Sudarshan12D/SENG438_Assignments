**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group 14:      |     |
| -------------- | --- |
|Thomas Mattern                |     |
|Feranmi Falade            |     |
|Alessandro Baldassarre           |     |
|Sudarshan Naicker           |     |

# Introduction
In this lab, we build upon the test cases seen in our previous assignment, delving deeper into different testing methods with a primary focus on Mutation Testing and GUI Testing. The core objective of this exercise is to investigate the impact of introducing deliberate faults, or mutations, into a Java codebase. This process aims to uncover the robustness and vulnerabilities of our existing codebase.

Mutation Testing, discussed in the first part of our lab, serves as a tool to assess the effectiveness of our test cases, specifically dataUtilitiesTest and RangeTest. By interpreting mutation scores, we aim to refine our testing strategies and devise new tests that enhance these scores, thereby improving the overall quality of our codebase.

Additionally, the assignment will explore aspects of GUI Testing, with a particular emphasis on the 'record and replay' techniques facilitated by Selenium. This approach will help us understand the practical challenges and benefits of automating tests for graphical user interfaces.

Through these methodologies, we aim to not only strengthen our testing practices but also to gain deeper insights into the reliability and performance of our software under various conditions.


# Analysis of 10 Mutants of the Range class 


### Mutant 1 - Killed

`90: Decrement (--a) double local variable number 1 `

This mutant will decrement the value lower range, eg: 9 would be reduced to 8, and then return a wrong range.

#### Test case:
```
@Test
    public void testNegativeLowerBound() {
        Range range = new Range(-10, -1);
        Assert.assertEquals("The lower bound should be -10", -10, range.getLowerBound(), 0.00001);
    }
```
This test case will kill the mutant as the getlowerBound() will be -11 (decremented by 1) and since -10 != -11 it will kill this mutant.

### Mutant 2 - Killed

`105: negated conditional → KILLED `

The getLowerBound() code which contains a condition to check if lower > upper then pass an exception else return the lower value. This mutant changes the condition so that if lower > upper then it will return the range value instead of giving an exception.

#### Test case:
```
    @Test
    public void testPositiveLowerBound() {
        Range range = new Range(1, 10);
        Assert.assertEquals("The lower bound should be 1", 1, range.getLowerBound(), 0.00001);
    }
```
This test case will check if 1 == the range.getlowerbound(). Since 1 > 10 it should return 1 but because the mutant changes the negates the condition it go through the if loop and gives an excpetion making the test fail hence this mutant is killed. 

### Mutant 3 - Killed

`133: removed conditional - replaced comparison check with true → KILLED`

The getLength() code which contains a condition to check if lower > upper then pass an exception else return the lower value. The mutant changes it so that the if loop will always be true and pass an exception. 

#### Test case:
```
    @Test
    public void testLowerBound_RangeUnchanged() {
        Range original = new Range(10, 20);
        Range result = Range.expand(original, 0, 0); // No expansion
        assertEquals("Lower bound should remain unchanged", 10, result.getLowerBound(), DELTA);
        assertEquals("Upper bound should remain unchanged", 20, result.getUpperBound(), DELTA);
    }
```

In this test we are using expand function which calls the getLenght() function which will cause an exception thus making the test fail hence this mutant is killed.

### Mutant 4 - Survived

`90: removed conditional - replaced comparison check with false  `

This mutant replaces the if (Lower>upper) statement which checks if an invaild range is passed eg:- 10 to 5 and then pass an excepition. this mutant makes the if statement false so it never passes through the loop and returns an invalid range.

#### Test case:
```
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
```
This test case checks whether the an invalid range passes an exception or not, as the mutant has removed the condition range1 will get the value of 10 to 5 (an invalid range) which should'nt be possible hence the mutant is getting survived.

### Mutant 5 - Survived


`179: Decremented (--a) double fieldlower → SURVIVED  `

This mutant replaces modified the intersects(b0, b1) function. it will decrease the lower value by 1. and then check if the b0 <= lower.

#### Test case:
```
    @Test
    public void testIntersects_IntersectionFromBelow() {
        Range range = new Range(5.0, 10.0);
        Assert.assertTrue(range.intersects(4.0, 6.0));
    }
```
This test case checks if 4 to 6 intersects 5 to 10 which is True, the mutant however changes the value of 5 to 4 and checks if 4 to 10 intersects 4 to 6 which is also true hence the test cases passes therfore allowing the mutant to survive.

### Mutant 6 - Killed

`302: negated conditional`

This mutant negates the conditional "==" by converting it to "!=". Thus the mutation changes the check from `if (range == null)` to `if (range != null)`.

#### Test Case:
```
    @Test
    public void testExpandToInclude_NullRange() {
        Range result = Range.expandToInclude(null, 5.0);
        Assert.assertNotNull("Resulting range should not be null", result);
        Assert.assertEquals("Lower bound should be 5.0", 5.0, result.getLowerBound(), DELTA);
        Assert.assertEquals("Upper bound should be 5.0", 5.0, result.getUpperBound(), DELTA);
    }
```
This test case passes a null Range and a value of 5.0 to the method `expandToInclude` to check if the method correctly recognizes a null range and returns a range from the provided value to the provided value (5.0-5.0). The mutant changes the check to `if (range != null)` and thus the test fails since the `expandToInclude` method returns the null range and the assertNotNull will cause the test to fail.

### Mutant 7 - Killed

`303: Decremented (--a) double local variable number 1`

This mutant decrements the first value param in the Range, so instead of returning Range(5, 5) it would return Range(4, 5).

#### Test Case:
```
    @Test
    public void testExpandToInclude_NullRange() {
        Range result = Range.expandToInclude(null, 5.0);
        Assert.assertNotNull("Resulting range should not be null", result);
        Assert.assertEquals("Lower bound should be 5.0", 5.0, result.getLowerBound(), DELTA);
        Assert.assertEquals("Upper bound should be 5.0", 5.0, result.getUpperBound(), DELTA);
    }
```
This test case passes a null Range and a value of 5.0 to the method `expandToInclude`. The method is expected to return a new Range from value to value, in this case 5.0 - 5.0. The mutant changes it so that the method returns a new Range from 4.0 - 5.0, which the test case checks for in its assertions specifically the first assertEquals which causes the test to fail and thus kills the mutant.

### Mutant 8 - Killed

`306: replaced return value with null for org/jfree/data/Range::expandToInclude`

This mutant changes the return value from the `expandToInclude` method to null instead of returning a Range. This occurs after the second conditional.

#### Test Case:
```
    @Test
    public void testExpandToInclude_ValueBelowLowerBound() {
        Range original = new Range(10.0, 20.0);
        Range result = Range.expandToInclude(original, 5.0);
        Assert.assertEquals("Lower bound should be expanded to 5.0", 5.0, result.getLowerBound(), DELTA);
        Assert.assertEquals("Upper bound should remain unchanged", 20.0, result.getUpperBound(), DELTA);
    }
```
This test case passes a valid Range and a value of 5.0 into the method `expandToInclude`. The method is expected to return a new Range which expands to include that value meaning it should return a range from 5.0 - 20.0. However, the mutant changes the return value to null meaning null is returned instead of the expected Range. This test case kills that mutant through the assertions specifically the first one since the result is just equal to null it will not be equal to the lower bound 5.0.

### Mutant 9 - Survived

`190: changed conditional boundary`

This mutant changes the conditional from `>` to `>=` so the code goes from `if (value > this.upper)` to `if (value >= this.upper)`. 

#### Test Case:
```
    @Test
    public void testConstrain_ValueAboveRange() {
        Range range = new Range(1.0, 10.0);
        double result = range.constrain(11.0);
        assertEquals("Value above range should be constrained to upper boundary", 10.0, result, DELTA);
    }
```
This test case passes a valid Range to the method `constrain` with a parameter of 11.0. The method is expected to return the result which should be the upper bound. The mutant changes the code so that when the value is outside of the Range bounds and it is greater than or equal to the upper bound rather, than just greater than, it returns the upper bound. The test case uses a value of 11.0 which is greater than or equal to the upper bound and thus the test passes meaning the mutant survived the test.

### Mutant 10 - Survived

`193: changed conditional boundary`

This mutant changes the conditional boundary from `<` to `<=` on line 193 in method `constrain`. Thus the conditional goes from `else if (value < this.lower)` to `else if (value <= this.lower)`.

This mutant survives because it is a stubborn mutant. The value will never be equal to the lower bound anyway because if it was then the method called prior, `contains(value)`, would return true. Thus the mutant survives but it will not negatively affect the code. 

The following test case still will run the same

#### Test Case:
```
    @Test
    public void testConstrain_ValueBelowRange() {
        Range range = new Range(1.0, 10.0);
        double result = range.constrain(0.0);
        assertEquals("Value below range should be constrained to lower boundary", 1.0, result, DELTA);
    }
```

# Report all the statistics and the mutation score for each test class

### Initial mutation scores:

### Range.java

<img alt="image" src="https://github.com/seng438-winter-2024/seng438-a4-Group-14/assets/113817382/7c317c82-428d-4a50-be4d-edfbd50e8817" style="width:1000px;"/>

### DataUtilities.java


<img alt="image" src="https://github.com/seng438-winter-2024/seng438-a4-Group-14/assets/113817382/15a5dcc7-8eee-4070-b45e-20a97b01ab29" style="width:1000px;"/>

### New mutation scores:

### Range.java

<img alt="image" src="https://github.com/seng438-winter-2024/seng438-a4-Group-14/assets/113554225/23a879b2-7cb0-44e7-b96d-c7fa9f78877a" style="width:1000px;"/>

### DataUtilities.java

<img alt="image" src="https://github.com/seng438-winter-2024/seng438-a4-Group-14/assets/113817382/39e109ed-e96c-417a-9b9a-9e9cdaeecd52" style="width:1000px;"/>


# Analysis drawn on the effectiveness of each of the test classes
Range class add several functions which is why it took us a huge amount of time to find 10 mutants and understand each and check each and every test suite to search for the mutants being killed and one that were survived. Then increasing the coverage from 60% to 70% was also a tedious task as there were around 500 survived mutants for which we had to make the test cases by carefully looking through the source code and checking the PIT mutation summary for exact line that the mutant was modifing and what. The Data Utillities was even harder has the scores were already high at 77% this meant more chances of having a huge number of equivalent classes but we were able to increase it to 88% by adding test cases specific to killing some of the survived mutants.

# A discussion on the effect of equivalent mutants on mutation score accuracy
Equivalent mutations are mutants that change the code but do not affect the logic of the code i.e., the mutated will give the same result that the original code produces. These mutants survive the testing process because their modifications have no observable impact on the program's functionality. As a result, they lower the mutation score significantly, suggesting that the test suite is less effective than it actually is. This misrepresentation can lead to unnecessary efforts to "kill" these mutants, diverting resources from improving test coverage in areas that genuinely need it. Furthermore, the manual identification and exclusion of equivalent mutants is a tedious task, requiring deep understanding of the program and the impacts of the mutations. 

# A discussion of what could have been done to improve the mutation score of the test suites
When trying the test cases it took us a lot of time to increase the coverage as we didnt know if the test cases were actually improving the mutation coverage or not and re runing PIT mutation took 10-15 min each time which a very tedious task, we could have instead changed the source code and run the Junit test this way it would have been fast and we would have had more time to improve the scores. We could have also improved scores by removing all the equivalent mutants in the beginning before writing new test cases which is however very time-consuming job.

# Why do we need mutation testing? Advantages and disadvantages of mutation testing
Mutation testing is a method where we change the source code to add bugs in the code that is mutants. Our test cases should be able to find this code change by failing the test this way the mutant is killed. We perform mutation testing since by intentionally introducing small, faults into the code, mutation testing challenges the existing test suite to catch these faults. The process provides a direct and practical measure of test quality, revealing not just whether the tests can execute all parts of the code, but whether they can effectively detect errors. 

- Advantage:<br>
Mutation testing helps identify weaknesses in the test suite by showing which parts of the source code are not being effectively tested. This allows testers to improve test cases.

- Disadvantage:<br>
 One of the biggest challenges of mutation testing is its computational expense. Generating mutants and running tests against each is very time-consuming, especially for large codebases.<br>
 To be truly effective, mutation testing requires a reasonably good set of initial tests. If the existing tests are of poor quality, mutation testing can end up being a frustrating job.

# Explain your Selenium Test Case Design Process

We decided to design Selenium test cases for automating various commonly used functionalities of ebay.com. The process began with the team coming together to gain a understanding of the SUT's user interface, features, and potential challenges we might encounter during testing. Subsequently, we identified eight core functionalities of eBay that would greatly affect user experience if they were not functioning correctly, prioritizing both uptime and functionality testing. These functionalities were carefully selected based on their criticality to the user experience and the valuable insights they could provide regarding the system's reliability and performance.

The identified functionalities for automation included:

1. **Login:** Verifying the authentication process and user access.
2. **Logout:** Ensuring a user can logoff.
3. **Adding Items to Cart:** Testing that items can be added to cart.
4. **Removing Item from Cart:** Testing that items can be removed from cart.
5. **eBay Live Uptime:** Testing the uptime of eBay Live services.
6. **Sell:** Testing the functionality for sellers to list items.
7. **Search Items:** Testing the search functionality for user.
8. **Messages:** Verifying communication features such as messaging between users is running.

In total, we created 11 test cases for these 8 functionalities, testing different inputs.

# Explain the use of assertions and checkpoints
Assertions are used to verify expected conditions against outputs within a test case, much like a JUnit assert. If the condition isn't met, the test fails, where if condition is made, the test passes. Selenium employs this method to guarantee that specific conditions are fulfilled during the testing procedure. Checkpoints are strategically placed within test cases, not necessarily associated with each step, but rather positioned at places where it's essential to verify the application's state.


1. **testSearchItemValid** - 1/8: Used automatic verification to check that items were returned on valid search using a assertion.
2. **testSearchItemInValid** - 1/8: Used automatic verification to check that no items were returned on invalid search using a assertion.
3. **testInvalidLogin** - 2/8: Used automatic verification to check if an error message popped up using a assertion.
4. **testValidLogin** - 2/8: Used automatic verification to check that the system correctly signed in using a assertion.
5. **testListValidCategory** - 3/8: Used assertion to test if the correct category is returned using a assertion.
6. **testLogout** - 4/8: Used automatic verification to test if the logout message popped up using a assertion.
7. **testEbayLive** - 5/8: Used automatic verification to check if live streams are present using a assertion.
8. **testDeleteFromCart** - 6/8: Used automatic verification to test if items are returned using a checkpoint and the item is deleted correctly using a assertion.
9. **testAddToCart** - 7/8: Used automatic verification to test if items are correctly returned with a checkpoint.
10. **testMessageReceived** - 8/8: Used automatic verification to show that the correct screen shows up for received messages using a assertion.
11. **testMessageSent** - 8/8: Used automatic verification to show that the correct screen shows up for sent messages using a assertion.

   
## How did you test each functionality with different test data?

1. **Login:** Two different datasets were used for testing login functionality:
   - Valid email and password combination.
   - Invalid email.

2. **Logout:** Only one test was conducted as only one account can be logged in at a time.

3. **Adding Items to Cart:** Two distinct sets of data were employed for adding items to the cart:
   - Two different items were added to the cart in test.

4. **Removing Item from Cart:** Only one dataset was utilized since all scenarios were covered.

5. **eBay Live Uptime:** Single test was executed as there's no alternative method to invoke this functionality.

6. **Sell:** Only one test was necessary because regardless of the input, the same categories are generated.

7. **Search Items:** Two different datasets were used for testing item search functionality:
   - Valid item name "laptop".
   - Invalid item name "{}][[[][][][[]][][]][][]]".

8. **Messages:** Two distinct sets of tests.
   - Sent
   - Received

# Discuss advantages and disadvantages of Selenium vs. Sikulix

Selenium is used and known for its browser compatibility, allowing testers to automate web applications across various browsers like Chrome, Firefox, Safari, and more. Being open-source and free, Selenium has a large community of users who contribute to its development. It is accessible to anyone with a compatible browser, and it efficiently tests across different environments and devices. However, Selenium faces limitations in automating non-browser GUI applications tests. Also, Selenium scripts can be sensitive to load times, leading to timing-related issues in test cases, especially with dynamically loading web pages. On the other hand, SikuliX stands out with its image-based automation, utilizing image recognition techniques to automate GUI-based applications effectively. Additionally, SikuliX offers cross-platform support and flexibility in automating various types of applications, including desktop, os's,mobile, and web applications. However, SikuliX's image-based approach can consume significant system resources, impacting performance, particularly in large-scale automation causing severe slow down of execution. Maintenance overhead is also a concern, as SikuliX scripts may require frequent updates due to changes in UI elements or resolutions. When deciding between Selenium and SikuliX, the choice depends on specific testing requirements. Selenium is preferable for web application testing across different browsers and websites. Conversely, SikuliX is better when automating non-web GUI applications or dealing with complex UI elements, prioritizing ease of scripting.

# How the team work/effort was divided and managed
Initially, our team met to get understand both of the SUT's, first being the mutation section, which included 'range' mutation tests which was worked on by Alessandro and Sudarshan, while Thomas and Feranmi 'dataUtilities' mutation tests. We then strategized for the GUI testing phase, each downloading and setting up Selenium. Following that, we verified that each team member had a properly configured testing environment in Eclipse, with Selenium installed. For the GUI tests, we decided that each member would be responsible for testing two functionalities, as specified in the laboratory instructions.

# Difficulties encountered, challenges overcome, and lessons learned
In the initial stages of our project, we encountered significant challenges while setting up Eclipse to run PIT, resulting in numerous errors and lots of time wasted. To navigate through these difficulties, we sought assistance from tas and utilized videos on YouTube to setup, which proved to be invaluable resources. This hurdle was perhaps the most substantial obstacle we faced. Also faced issues with ebay.com baning our test emails from being logged in too frequently. 

The most significant lesson we learned from this experience was the intricacies of GUI testing with Selenium and the principles of mutation testing using PIT. This knowledge not only helped us overcome the initial challenges but also enriched our understanding of software testing techniques, demonstrating the importance of perseverance and resourcefulness in problem-solving.

# Comments/feedback on the lab itself
We encountered more challenges with the setup of this lab compared to previous assignments. This assignment setup was significantly more complex and time-consuming. However, the teaching assistants were very helpful throughout the process solving some errors. This lab provided an excellent opportunity to deepen our understanding of mutation and GUI testing. It would have been beneficial to have additional examples specifically for GUI testing to gain a bit more understanding before the assignment.
