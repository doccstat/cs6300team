# Design Document

**Author**: \<person or team name\>

## 1 Design Considerations

### 1.1 Assumptions

1. Any user will access one cryptogram at the same time
2. Any user will save the current state of the cryptogram before log off
3. Any user or administrator will login on one machine at the same time
4. Any user will not do conflict operations, for example, under certain circumstances, he/she successfully logs in at different machines and do the same operation at the same time
5. The machine users run our app on should have all the libraries required for the application to run successfully
6. Any administrator won’t make changes to any user that is currently active
7. Any administrator won’t make changes to any cryptogram that is current actively used by some user

### 1.2 Constraints

1. Any user can access only one cryptogram at the same time
2. Current state of cryptogram is stored when users sign off
3. Any user or administrator remain active on only one machine at the same time
4. Any administrator can’t make changes to any user that is currently active
5. Any administrator can’t make changes to any cryptogram that is current actively used by some user

### 1.3 System Environment

* Android phones running on API 23 or higher.
* RAM >= 512MB
* Resolution >= 720p
* CPU frequency >= 1.5 GHz
* Network access

## 2 Architectural Design

*The architecture provides the high-level design view of a system and provides a basis for more detailed design work. These subsections describe the top-level components of the system you are building and their relationships.*

### 2.1 Component Diagram

*This section should provide and describe a diagram that shows the various components and how they are connected. This diagram shows the logical/functional components of the system, where each component represents a cluster of related functionality. In the case of simple systems, where there is a single component, this diagram may be unnecessary; in these cases, simply state so and concisely state why.*

### 2.2 Deployment Diagram

*This section should describe how the different components will be deployed on actual hardware devices. Similar to the previous subsection, this diagram may be unnecessary for simple systems; in these cases, simply state so and concisely state why.*

## 3 Low-Level Design

*Describe the low-level design for each of the system components identified in the previous section. For each component, you should provide details in the following UML diagrams to show its internal structure.*

### 3.1 Class Diagram

*In the case of an OO design, the internal structure of a software component would typically be expressed as a UML class diagram that represents the static class structure for the component and their relationships.*

### 3.2 Other Diagrams

*<u>Optionally</u>, you can decide to describe some dynamic aspects of your system using one or more behavioral diagrams, such as sequence and state diagrams.*

## 4 User Interface Design
*For GUI-based systems, this section should provide the specific format/layout of the user interface of the system (e.g., in the form of graphical mockups).*

