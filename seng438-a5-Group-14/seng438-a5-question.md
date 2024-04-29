**SENG 438 - Software Testing, Reliability, and Quality**

> **Assignment #5**
> **Software Reliability Assessment**
  Instructors: 
>   -   Dr. Behrouz Far (far@ucalgary.ca)
>   -   Dr. Kangsoo Kim (kangsoo.kim@ucalgary.ca)

> Department of Electrical and Computer Engineering
> University of Calgary

Due Date: Check D2L for the submission deadline.

> **Summary:**
> - Install a reliability assessment (reliability growth and reliability demonstration chart) tool.
> - Run the tool and feed the provided data into it.
> - Display and discuss the results.

# 1 INTRODUCTION

This lab includes analysis of integration test data using reliability assessment tools. There are two ways to assess failure data:

1. Reliability growth testing
2. Reliability assessment using Reliability Demonstration Chart (RDC)

Both will be practiced in this lab.

# 2 PART 1: RELIABILITY GROWTH TESTING

## 2.1 OBJECTIVES

The purpose of this assignment is to give students hands-on experience on assessing the reliability of a hypothetical system given its failure data collected during integration testing. To do this, students will need to install a reliability growth assessment tool, such as CASRE or SRTAT, and create plots of failure rate and reliability of SUT.

After completing this part, students will:

- gain an understanding of what reliability growth testing is and why it is useful.
- be able to measure the failure rate, MTTF and reliability of the SUT through analyzing the test data.
- become familiar with the features and usage of a reliability growth testing tool.

## 2.2 TESTING TOOLS

The testing tool to be used in this part, is 
- START (open source, developed by Dr. Far's Lab, running on various OS including 32bit and 64bit Windows and Linux). 
 **OR** 
- C-SFRAT (an open source software developed by Python). 


## 2.3 SYSTEM UNDER TEST
The system to be tested for this part is a hypothetical system and its failure data is attached ([failure-data-set2.zip](./failure-data-set2.zip)). There will be a few test data files and the students should select one of them.

**Note**:
  - For this assignment, take a deeper look into the suitable sample input for the tools that you are supposed to use, since you need to convert your selected input data into a compatible input file based on the used tool.
  - If you are using SRTAT, you need to convert the input data of any type into a 'Failure count' or 'Time between failures' format. You can find a sample of the converted and compatible files in the 'SRTAT - SRE tool\Win64\sample_input' folder in SRTAT.
  - If you are using C-SFRAT, you need to convert the input data into the following format: (T: time interval, FC: failure count, E: execution time measured in hours, F: failure identification work measured in person hours, C: computer time failure identification measured in hours).

## 2.4 FAMILIARIZATION

### 2.4.1 INSTALL SRTAT

1. Get SRTAT-SRE-tool.zip.
2. It is a Java package that has 4 variations for different operating systems. Install the appropriate version on your system.
   ```
   java -Djava.library.path=. -jar SRTAT.jar

   ```
3. Run and verify its functionalities.
4. Submitting results using only the 'Geometric' and 'Littlewood and Varral's Bayesian Reliability' models is acceptable if some models do not function on your device.
5. If the tool does not work on your device, please note that it has been tested and functions properly on Windows 64.


### 2.4.3 Alternative: INSTALL C-SFRAT (**Suggestion** )

1. Get C-SFRAT binary from [GitHub](https://github.com/LanceFiondella/C-SFRAT/releases/tag/v1.0). There are a Windows and a Linux executable. Download and unzip the appropriate version on your system.
2. Run and verify its functionalities.


## 2.5  **INSTRUCTIONS**
### 2.5.1 Running SRTAT

Software Reliability and Testing Analysis Tool.

1. Run SRTAT
2. Import failure data
3. Select a set of models that would provide the best fit for the project data using model ranking
4. Select the range of useful data using Laplace or other tests
5. Display time-between-failures, failure intensity and reliability graphs
6. Discuss the acceptable range of failure rate for the test data

![](./media/running-srtat.png)


### 2.5.2 Running C-SFRAT

The Covariate Software Failure and Reliability Assessment Tool (C-SFRAT) is an open source application that applies covariate software reliability models to help guide model selection and test activity allocation. 

1. Run C-SFRAT.exe for Windows  
2. Execute steps 2-6 same as 2.5.1

For more information about the input format and features of the tool, you can read [A covariate software tool to guide test activity allocation](https://www.sciencedirect.com/science/article/pii/S2352711021001588)

![](./media/c-sfrat.png)

**Note:** If none of the above mentioned tools work for you, try to find other tools that may work.

1. Software Defect Estimation Tool (SweET): [https://github.com/LanceFiondella/SwEET](https://github.com/LanceFiondella/SwEET)

2. Software Failure and Reliability Assessment Tool (SFRAT): [https://github.com/LanceFiondella/srt.core](https://github.com/LanceFiondella/srt.core)

# 3 Part 2: ASSESSMENT USING RELIABILITY DEMIONSTRATION CHART

## 3.1 OBJECTIVES

Reliability Demonstration Chart (RDC) is an efficient way of checking whether the target failure rate or MTTF is met or not. It is based on collecting failure data at time points. The main objective of this part of the assignment is to familiarize students with RDC tool and its usage during reliability assessment.

We usually use RDC when failure data is limited to a few failures, time of failures are known, and we want to find out what is the trend for reliability of the system.

After completing this part, students will:

- be able to decide upon adequacy of testing for a given MTTF of the SUT through plotting thetest data.
- become familiar with the features and usage of an RDCtool.

## 3.2 TESTING TOOLS

In this assignment, you will use the following tools to analyze the test data provided.

-  SRTAT **OR** 
-  RDC-11 (an EXCEL worksheet and macro). 
## 3.3 SYSTEM UNDER TEST

The system to be tested for this part is a hypothetical system and its failure data is attached ([failure-data-set2.zip](./failure-data-set2.zip)). There will be a few test data files and the students should select one of them.

## 3.4 FAMILIARIZATION

### 3.4.1 INSTALL RDC (**Suggestion**)

1. Get Reliability-Demonstration-Chart.xls and its manual RDC-xls-Overview.pdf for the attachemnt OR download it from the [Sourceforge.net](https://sourceforge.net/projects/rdc/).
2. Open the excel sheet and verify that it works by setting various risk factors, i.e. check whether the right chart will be generated.
3. Read the document explaining its functionality.

![](install-rdc.png) 


### 3.4.2 Alternative: INSTALL SRTAT 

Install SRTAT(as in 2.4.2, RDC is included in SRTAT tool as an option tab)

![](./media/install-srtat.png)

1. Get SRTAT-SRE-tool.zip.
2. It is a Java package that has 4 variations for different operating systems. Install the appropriate version on your system.
3. Run and verify its functionalities.


**INSTRUCTIONS**

1. Make yourself familiar with the RDC. Try to understand how it works. Vertical axis is failure number (n), horizontal axis is normalized failure data (Tn), i.e., failure time divided by MTTF.
1. How to use RDC? You need to input the failure data (failure number and failure time); identify the target MTTF and anticipated confidence levels; and draw the failure points on the graph and analyze the trend. Consult with the examples in the lecture slides and RDC manual.
1. You can experiment with &quot;what-if&quot; scenarios by setting various values for MTTF and draw the plot.
1. Select the minimum MTTFmin for which the SUT becomes acceptable. Set MTTF to twice and half MTTFmin and plot the failure data.
1. Document the results at the end of your report file.

# 4 Submission

Submit the results for part 1 and part 2.

1. Document the results (plot/diagrams as well as explanation of each).
2. Comparethe results of Part1 and Part 2 and justify the case that each technique can be used.

# 5 Evaluation Criteria:

## 5.1 Demo (15%)

The objectives for the demo are a) Preparing you for technical presentations, b) an early assessment of your work to give you a second chance to submit a high-quality report, and c) making sure everybody in the team contributes evenly.

It is mandatory for all team members to attend the demo session and explain the TAs in the lab what they have done for this assignment. For this particular assignment, **Lab10** is the demo day. You are expected to almost finish the assignment by the lab hour. All the team members should attend the lab. The TAs will go through the groups and each group must demonstrate the results of each part of the assignment.


**NOTE: Student who miss the demo session or are unable to demo what is detailed above are considered as less- contributors and may lose up to the entire assignment's mark.**

## 5.2 Lab report (85%)

Students will be required to submit a report on their work in the lab as a group. To be consistent, please use the template file “Assignment5-ReportTemplate.md”. If desired, feel free to rename the sections, as long as the headings are still descriptive and accurate.



A portion of the grade for the lab report will be allocated to organization and clarity. The report marking scheme is as follows:

|                                                                                                          |     |
| -------------------------------------------------------------------------------------------------------- | --- |
| **Reliability Growth Testing (40)**                                                                      |     |
| Result of model comparison (selecting top two models)                                                    | 5   |
| Result of range analysis (an explanation of which part of data is good for proceeding with the analysis) | 5   |
| Plots for failure rate and reliability of the SUT for the test data provided                             | 15  |
|                                                                                                          |     |
| A discussion on decision making given a target failure rate                                              | 10  |
|                                                                                                          |     |
| A discussion on the advantages and disadvantages of reliability growth analysis                          | 5   |
| **RDC Testing (30)**                                                                                     |     |
| 3 plots for MTTFmin, twice and half of it for your test data                                             | 15  |
| Explain your evaluation and justification of how you decide the MTTFmin                                  | 10  |
| A discussion on the advantages and disadvantages of RDC                                                  | 5   |
| **Other (15)**                                                                                           |     |
| Comparison of the results with the Part 1                                                                | 10  |
| A discussion on similarity and difference of the two techniques. Any lessons learned in this lab?        | 5   |
# 6 REFERENCES

1. Open Channel Foundation: [http://www.openchannelsoftware.org/discipline/Reliability_Analysis](http://www.openchannelsoftware.org/discipline/Reliability_Analysis)

2. ReliaSoft (commercial software): [http://www.reliasoft.com/products.htm](http://www.reliasoft.com/products.htm)
