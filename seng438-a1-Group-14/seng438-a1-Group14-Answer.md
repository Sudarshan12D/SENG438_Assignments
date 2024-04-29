> **SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#1 – Introduction to Testing and Defect Tracking**

| Group: 14              |
| ---------------------- |
| Sudarshan Naicker      |
| Thomas Mattern         |
| Feranmi Falade         |
| Alessandro Baldassarre |

**Table of Contents**

(When you finish writing, update the following list using right click, then
“Update Field”)

[1 Introduction 1](#_Toc439194677)

[2 High-level description of the exploratory testing plan 1](#_Toc439194678)

[3 Comparison of exploratory and manual functional testing 1](#_Toc439194679)

[4 Notes and discussion of the peer reviews of defect reports 1](#_Toc439194680)

[5 How the pair testing was managed and team work/effort was
divided 1](#_Toc439194681)

[6 Difficulties encountered, challenges overcome, and lessons
learned 1](#_Toc439194682)

[7 Comments/feedback on the lab and lab document itself 1](#_Toc439194683)

# Introduction

We are Group 14 (Alessandro Baldassarre, Sudarshan Naicker, Feranmi Falade, Thomas Mattern), and in this lab we are focusing on testing an ATM system first being version 1.0 and 1.1. Prior to undertaking this lab, none of our group members has had any practical experience with manual or exploratory testing, having gained knowledge primarily through lectures. This lab will serve as an opportunity for us to enhance our skills in System Under Test (SUT) analysis and defect tracking.

# High-level description of the exploratory testing plan

We will concentrate on testing functions aligned with the fundamental requirements outlined in Appendix B. These functions encompass depositing money into any account and facilitating money transfers between accounts. Our testing will involve inputting varying amounts of $20 bills and testing with both provided credit card numbers. Subsequently, we will conduct tests for each function, including withdrawals, deposits, transfers, and balance checking, to ensure the system behaves in accordance with our expectations. Additionally, we will verify the proper functioning of the logs and receipt system, ensuring they accurately reflect the system's expected behavior. We will conduct testing on the most common functions, considering their high traffic volume. This testing aims to enhance the user experience as these functions are used the most. As this testing is exploratory in nature, we will not document specific physical test cases with input/output but instead explore the system to validate the functionality of the requirements. The overarching goal of this plan is to test the fundamental requirements, outlined in Appendix B, of the system for version 1.0.

# Comparison of exploratory and manual functional testing

The exploratory functions were collectively tested, with a focus on critical features in the ATM banking application, specifically depositing, withdrawals, transfers, and balance checks. This testing approach provided us with the flexibility to devise our own tests within the group, all following the same plan. This method facilitated a comprehensive exploration of the software, contributing to a deeper understanding of the system. By employing a common testing plan, we identified numerous common bugs, confirming their existence. However, we also discovered many distinct bugs. For instance, both Team 1 and Team 2 intended to test the balance inquiry for all accounts. While both teams uncovered issues when inquiring about the money market account, Team 2 used a different card number, leading to the identification of a unique bug. This approach allowed us to uncover a greater number of bugs within the assigned time frame, enhancing the overall effectiveness of the testing process.

Manual testing involved a comprehensive and scripted test plan outlined in Appendix 3 of the assignment. We were assigned 40 test cases, distributed across two sets for completion by our respective teams, each set consisting of 20 test cases. This partitioning facilitated an efficient testing process. The test results revealed reproducible instances of bugs and provided a systematic approach for reporting them. Upon completing the initial tests, we implemented a collaborative and thorough bug-checking process. Each team member took turns trying failed test cases to ensure a bug acutally occured. This collaborative approach ensured a more efficient and systematic testing of the system, helping us identify and address bugs effectively. For example, Test Case 14 failed, so we promptly communicated the failure to the other team so that they could address and verify the issue.

These aspects together enabled us to comprehend the strengths and weaknesses inherent in both exploratory and manual functional testing methodologies. The exploratory testing approach furnished us with the flexibility to adapt and improvise our tests on the fly, resulting in the discovery of a diverse range of bugs, some of which may not have been identified through a rigid, pre-defined plan. This method proved highly effective in uncovering unique issues and enhancing our understanding of the ATM banking application. However, manual testing demonstrated greater efficiency and effectiveness as it led to the detection of more bugs in less time compared to exploratory testing. The primary tradeoff between the two lies in the flexibility of exploratory testing versus the efficiency and effectiveness of manual testing.


# Notes and discussion of the peer reviews of defect reports

Upon reviewing each other's defect reports, we identified several overlaps in our exploratory testing. Examples include issues with withdrawal problems, deposit problems, and bugs in the money balance inquiries function, confirming these as bugs in the system. The peer review of defect reports proved invaluable in uncovering additional errors during our exploratory testing. For instance, one team discovered a bug related to the balance inquiry function when using another valid card number that had not been found by the other group. The division of exploratory testing proved to be a time-saving strategy, resulting in the discovery of more errors than if only one team had conducted the examination. This successfully doubled the number of bugs found within the allotted time of exploratory testing. Furthermore, the well-written defect reports in Jira facilitated efficient collaboration, allowing each team to quickly compare reports and accurately identify differences in our reported bugs. Our team also benefited from splitting the manual testing, which ensured faster and more detailed bug reports based on the given test cases. Finally, peer reviewing allowed us to enhance the overall quality of our defect reports by incorporating diverse perspectives and insights, ensuring that our documentation was thorough and comprehensive. This collaborative approach not only improved the accuracy of bug identification but also fostered a culture of knowledge sharing and continuous improvement within our testing teams more efficiently.

# How the pair testing was managed and team work/effort was divided

We divided into two distinct mini-teams to carry out pair testing. Thomas and Feranmi formed one team, while Alessandro and Sudarshan comprised the other. Each team meticulously went through the lab requirements, performing exploratory and manual testing and reporting any identified bugs. Upon completion of testing, we regrouped to discuss and cross-reference our respective bug findings. This collaborative effort ensured that our work was evenly distributed, thoroughly reviewed, and scrutinized from different perspectives. Additionally, by cross-referencing our identified bugs, we maximized the detection of issues in both manual and exploratory testing.

# Difficulties encountered, challenges overcome, and lessons learned

One main challenge we encountered as a team was devising an effective high-level approach for exploratory testing of each bug and establishing a standardized method for reporting errors. Despite being in training as software engineers, we acknowledged our human susceptibility to errors, making it easy for any team member to overlook bugs and potentially compromise our results. To address this issue, we implemented pair testing as our primary solution. Pair testing involved two teams simultaneously working on testing and completing the same task. This approach allowed us to cross-check bug reports with each other, ensuring a more comprehensive coverage and minimizing discrepancies. Additionally, we tackled this challenge by meticulously recording all our progress and findings. Leveraging tools such as Atlassian Jira, Google Docs, and GitHub, we created "issues" to report bugs as soon as they were encountered. These platforms facilitated effective communication among team members, enabling us to discuss findings, actions taken, and the expected versus actual outputs. This systematic approach not only enhanced our testing efficiency but also contributed to a more thorough and accurate bug reporting process.

# Comments/feedback on the lab and lab document itself

As a group, we agreed that this was a good lab to introduce us to the component and importance of testing in software engineering. As upcoming programmers, it's important to know how to develop and run tests for the code we generate so we are sure the most optimal product is being released. In terms of documentation, we felt like this lab was a good way to highlight the importance of keeping track of our progress through documentation and keeping logs on our progress through jira. This way, it's easy to track back if we make an error, and also it will be easy to understand for others reading our code and reports and monitoring our testing procedures. However, we wished that there were some more examples of a correct bug report as we have never seen how to write these.
