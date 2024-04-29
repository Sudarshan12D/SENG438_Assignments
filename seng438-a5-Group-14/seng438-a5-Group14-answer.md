**SENG 438- Software Testing, Reliability, and Quality**

**Lab. Report \#5 – Software Reliability Assessment**

| Group 14:      |     |
| -------------- | --- |
|Thomas Mattern                |    |
|Feranmi Falade            |     |
|Alessandro Baldassarre           |     |
|Sudarshan Naicker           |     |

# Introduction
This lab introduces the analysis of integration test data using reliability assessment tools. There are two primary methods for assessing failure data seperated into 2 parts: part 1 being Reliability Growth Testing using SRTAT and part 2 being Reliability Assessment using Reliability Demonstration Chart (RDC) . 

Our goal is to gain hands-on experience with these methods. Part 1 involves Reliability Growth Testing to assess the reliability of a System Under Test (SUT) based on its failure data. Part 2 focuses on using the Reliability Demonstration Chart to determine whether the target failure rate or Mean Time To Failure (MTTF) is met. This provides us with an opportunity to decide on the adequacy of testing for a given MTTF of the SUT by plotting the test data and becoming familiar with the features and usage of an RDC tool.

# Assessment Using Reliability Growth Testing 
## Result of model comparison (selecting top two models)
For the reliability growth testing portion of the lab, our group utilized the SRTAT tool to open a text file that we had to extract from the "Failure report 4.docx" into a time between failure format. The top two models selected to fit our failure data were the Geometric model and Littlewood and Verrall's Bayesian Reliability model. These were chosen because they best fit our data and provided satisfactory graphical results. Both of these models indicate that as the number of failures increases, the time between failures also increases, suggesting that the SUT is becoming more reliable over time.

## Result of range analysis (an explanation of which part of data is good for proceeding with the analysis)
Given the analysis of the Time Between Failures data seen below, we observe a meaningful pattern indicating that all parts of the dataset provide valuable insights for reliability growth analysis over the time of a project. The data is consistent, suggesting a level of stability and predictability in the system's reliability performance. As the number of failures increases, the Time Between Failures (TBF) also increases, indicating that the SUT is becoming increasingly reliable over time. This suggests that all parts of the dataset are of high quality and ready for analysis, as all figures are consistent and do not show significant deviation.

![iler](https://github.com/seng438-winter-2024/seng438-a5-Group-14/assets/113630601/7dab2175-6027-4b36-a218-8384d7bdb8dc)


Laplace Results:
![image](https://github.com/seng438-winter-2024/seng438-a5-Group-14/assets/114002112/19e7fb5e-70b4-476d-a5f2-12403287ea91)

This Laplace function graph shows how our `report4.txt` file demonstrates the relationship between the failure count and the time between failures. As the Laplace factor increases (or becomes more positive), this indicates an increasing failure intensity and a decrease in reliability. But when the number of failures reaches 4, the Laplace factor reaches it's peak. From then on, we can see that the Laplace factor and slope of the graph decreases (or becomes more negative) as the number of failures increases. This reinforces how the data in our `report4.txt` file becomes more reliable as the number of failures increases, and we can see that the time between failures increases as a result as well.

## Plots for failure rate and reliability of the SUT for the test data provided

### Geometric Reliability Graph:

![geo1](https://github.com/seng438-winter-2024/seng438-a5-Group-14/assets/113817382/f6eb54fc-6fdd-42a2-a7c7-e92c61cd81e0)

<br/>

### Littlewood and Verrall's Bayesian Reliability Graph:

![ittle1](https://github.com/seng438-winter-2024/seng438-a5-Group-14/assets/113817382/4f1df287-c7e3-4bf8-adf6-2a4086f727f5)


## A discussion on decision making given a target failure rate
To make a decision regarding the reliability of a system, it's essential first to determine whether the SUT meets or exceeds the target failure rate. If the failure rate surpasses the target, this indicates a higher-than-acceptable level of failures, suggesting that further bug-fixing or troubleshooting efforts are necessary to improve the system's reliability. Conversely, if the system's failure rate is at or below the target, it implies that the SUT is functioning as intended, indicating adequate system reliability. This assessment helps in deciding whether additional interventions are needed to enhance the system's performance. 

This can be calculated by determining the failure rate, which is done using the formula: failure rate = Total failures / Total time under test. In this instance, the failure rate is calculated as follows: 14 failures / 402 minutes = 0.034 failures per minute or 0.0006 failures per second for our failure data. This data can then be compared to the target failure rate to find if our SUT is properly functioning. For instance, consider a hypothetical scenario where the target failure rate is set at 0.5 failures per minute. In this case, observing only 0.0306 failures per minute indicates that our SUT is performing well, as it falls significantly below the established target rate, and no further fixing is necessary.

## A discussion on the advantages and disadvantages of reliability growth analysis	
The main advantages is that reliability growth analysis provides a systematic means to detect and rectify defects early in the development cycle, facilitating improved system quality overtime through analysis of our graph. By continuously monitoring reliability metrics, teams can swiftly address weaknesses in our system, saving time and resources while enhancing overall quality. Moreover, it enables data-driven decision-making, allowing for informed resource allocation and risk management. This culminates in a way to see if our system is performing by checking the Mean Time To Failure (MTTF) easily with visual data.

The main disadvantages is that reliability growth analysis poses challenges due to its hyper-specific data input requirements, reliance on high-quality data, and resource-intensive nature. Obtaining and maintaining data in precise formats can be difficult, limiting data source diversity is exacerbated by the restrictive nature of the test tool file requirements. Ensuring data quality is crucial, as inaccuracies can skew results and decision-making. Moreover, implementing analysis demands substantial resources for infrastructure, expertise, and personnel. Addressing these challenges is vital for maximizing the benefits of reliability growth analysis in enhancing system quality and performance.

# Assessment Using Reliability Demonstration Chart 

## Plots for MTTFmin, Twice, and Half

### MinMTTF

<img alt="image" src="https://github.com/seng438-winter-2024/seng438-a5-Group-14/assets/113817382/603f5849-2816-4ce5-82a4-f3a6cbebb68f" style="width:1000px;"/>

The above plot demonstrates the MTTFmin value since it lies right on the acceptance boundary.

### HalfMTTF

<img alt="image" src="https://github.com/seng438-winter-2024/seng438-a5-Group-14/assets/113817382/c80ada4c-037c-4655-8f9e-e2f6334921c9" style="width:1000px;"/>

The above plot demonstrates half of the MTTFmin value and it lies to the left of the acceptance boundary meaning testing must be continued.

### DoubleMTTf

<img alt="image" src="https://github.com/seng438-winter-2024/seng438-a5-Group-14/assets/113817382/1e8613e8-96ed-4ed8-9706-25f430bf4677" style="width:1000px;"/>

The above plot demonstrates double the MTTFmin value and it lies to the right of the acceptance boundary meaning the failure data is accepted.

## Explanation and Justification for MTTFmin

In the RDC graph, any failure data points falling between the "Accept" and "Reject" lines indicate a stable and acceptable level of system reliability. For the MTTF_min, the value of 0.57 failures per second represents the minimum acceptable failure rate because it lies exactly on the border of the acceptability line. This MTTF_min ensures that the system is just meeting the threshold of acceptable performance, signifying a balance between reliability and risk as specified in the assignment.

## Advantages and Disadvantages of RDC

Advantage:
  - RDC provides a clear visual representation of whether a system meets reliability requirements.
  - It allowes users to plot various failure points against predetermined accept and reject criteria to find the current reliablity of the SUT based on the MTTF and also find the minimum
MTTF need to make the SUT reliable .

Disadvantage:
  - RDC often assumes a constant failure rate, which may not be accurate for all systems, especially during early life or wear-out phases.
  - The method requires precise failure data, and if data quality is poor or the quantity of data is insufficient, the RDC can lead to incorrect conclusions.

# Comparison of Results

(see the paragraph "A discussion on decision making given a target failure rate" to understand the formula) 14 failures / 402 minutes = 0.034 failures per minute or 0.0006 failures per second for our failure data. when we choose this we get this graph:

<img alt="image" src="https://github.com/seng438-winter-2024/seng438-a5-Group-14/assets/113817382/22923fe4-100d-4dbb-80eb-d200fa1e1722" style="width:500px;"/>

With this value, it can be seen that the failure data lies within the "continue testing" range meaning that we must continue testing our SUT but it is not rejected. According to our MTTFmin if the failure rate passes 0.57 (our MTTFmin value) then the SUT is accepted. This means there is a greater average amount of time the SUT operates before it fails meaning the SUT is more reliable.

# Discussion on Similarity and Differences of the Two Techniques

Similarities:
  - They both share a common goal of ensuring and demonstrating that a product or SUT meets reliability requirements.
  - They both rely on data collected from testing to make decisions.

Differences:
  - Reliability growth testing focuses on improving product/SUT reliability through iterative testing and fixes while RDC focuses on demonstrating that a product has achieved a certain level of reliability at a specific point in time.
  - Reliability growth testing involves continuous testing and failure analysis aimed to enhance reliability so it's an ongoing process while RDC is used to visualize the demonstration of reliability through statistical methods so it is more of a validation tool.

In conclusion, they both aim to improve reliability of a product or SUT however they do so in different ways and at different times.

# How the team work/effort was divided and managed
Initially, our team convened to assess two tools and determine which one to utilize. We opted for the tool SRTAT due to its compatibility with all of our systems, as we encountered difficulties getting CASRE to function properly. Subsequently, we divided the workload into two parts for this lab. Thomas and Feranmi collaborated on part 1, while Alessandro and Sudarshan tackled part 2. Following completion of both of the parts, we compared our respective results within the teams, discussing the similarities and differences of our results.

# Difficulties encountered, challenges overcome, and lessons learned
One of the most significant challenges we encountered revolved around setting up the tools and ensuring they operated effectively with the provided input data. This was particularly pronounced when trying to obtain usable results from the SRTAT tool, which consumed a considerable amount of our time. Through this long process, we gained valuable insights into troubleshooting failures within the SRTAT tool and its setup, especially in areas such as data analysis, including reliability growth testing and the assessment of reliability through demonstration charts. Furthermore, we honed our skills in documenting and evaluating the results, identifying both similarities and differences.

# Comments/feedback on the lab itself
We encountered issues with setting up the tools, especially with installing and getting the SRTAT tool to run. However, the assignment was thorough and explained the process well and the TAs were helpful and fixed any issues we had.
