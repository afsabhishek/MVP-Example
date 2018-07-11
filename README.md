# MVP-Example

A Sample Weather Application using apixu library for weather with MVP Design.

The Model-View-Presenter Pattern

Here are the roles of every component:

    Model —  Data layer. Responsible for handling the business logic and communication with the network and database layers.
    
    View —   UI layer. Displays the data and notifies the Presenter about user actions.
    
    Presenter — Retrieves the data from the Model, applies the UI logic and manages the state of the View, decides what to display and reacts to user input notifications from the View.

View and the Presenter work closely together, they need to have a reference to one another. 
To make the Presenter unit testable with JUnit, the View is abstracted and an interface for it used. 

Relationship between the Presenter and its corresponding View is defined in a Contract interface class, making the code more readable and the connection between the two easier to understand.
Reference https://medium.com/upday-devs/android-architecture-patterns-part-2-model-view-presenter-8a6faaae14a5


Feel Free to do the changes :)
