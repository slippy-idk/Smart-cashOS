# Smart-cashOS

Smart-cashOS  is a software application, demonstrating a cash register system running on iPad, it helps enhance financial management, designed for smaller businesses, as larger companies have the privilege of utilising similar existing systems.
It monitors and tracks the structure of said smaller businesses based on employee and sales statistics. 


## Features in this repo:
* User Management: Safe and secure way to log in and manage users.
* Transaction Handling: quick efficient processing of transactions.
* Personalisation: Easy and flexible interface for personalisation.
* Financial Analytics: Detailed financial reports and insights.


## Getting Started
You will need:
* An OS (operating system): Linux, Windows, or macOS
* Python 3.8+
* Git
* A MySql Database

* More documentation about the software you might find helpfull are included in the project folder such as api doccumentation which can be helpfull for using javafx


## Installation process:
Follow these steps to get a copy of the Smart-cashOS up and running on your local machine for development and testing purposes.

1. Fork and Clone the repository on your github account
2. Open the software in a IDE and make sure the lib folder is selected as a libary in the IDE


**Fork the Repository:**
Firstly you will need to fork this repository to your own github account. Aftwards, you can clone the forked repository to access it on your local machine. 

- Go to the original repository on GitHub.
- Click the "Fork" button at the top-right corner of the page.


**Clone the Repository:**
- Open your terminal (or Git Bash, or Command Prompt).
- Execute the following commands:
    ```bash
    # Clone your forked repository
    git clone https://github.com/your-username/Smart-cashOS.git
    # Navigate into the cloned directory
    cd Smart-cashOS
    ```



2. Install packages/requirements 
After forking and cloning the repository, you need to install the necessary modules.
Check you have [Node.js](https://nodejs.org/) installed on your machine, this is essential when working on this project.

You will also need a Mysql dtaabse to run the software once the mysql databse is downloaded you will also need to download a mysql connector for establshing a connection between the software and the database 
[Mysql](https://dev.mysql.com/downloads/installer/)
[Mysql Connector](https://www.mysql.com/products/connector/) you have to include this in the libary of the project structure

the test database used for testing is currently as of writing included in the realeses and should allow you to import it into mysql allowing you to have access to the database

the software UsesJavaFX for the GUI this is included in the libary folder of the code and you may need to add it into the libary folder in your IDE



While not needed javaFX scenebuilder is reccomended as this software includes javafx code that was made in scene builder and this helps with the creation of the GUI


**Install packages:**
- In your terminal, run:
    ```bash
    npm install
    ```


3. Run the application
Once the packages are installed, you can run the application.

**Run the Application:**
- In your terminal, execute:
    ```bash
    npm start
    ```

This should now be able to run on your local machine. 
Follow any additional instructions displayed in the terminal for the  best set up and accessibility. 


## Contributing
 We welcome contributions! Please follow any specified contribution guidelines shown in issues from this GitHub repository to get started.

## Contact
 For any questions or concerns, please open an issue on GitHub or contact the maintainers.
