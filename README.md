# Salesforce Permissions Transformation
The goal of this project is to implement a utility that transforms and transfers relevant parts of Salesforce profiles into groups of permission sets.

## Requirements

Java 11 minimum

## User Manual

* Download the JAR executable
* Optional: Run from command line to monitor errors and messages
  * java -jar permissionset-helper-XXX.jar

### Load Profiles

#### a. Retrieve from Org

1. Confirm and connect with the pre-filled Device Code
2. Log into your Salesforce Instance
3. Allow the application to access the Org

#### b. Load from Filesystem

1. Navigate to the profiles folder in your metadata tree

---

* Click the Load Profiles button and wait until it gets enabled again
  * Known issue: Sometimes the server is unavailable and the requests get stuck in Pending state. 
In case you wait more than a minute, please restart the app and try again. 
(TBD: Kill the Pending thread and repeat request.)
* You should see the number of Profiles Loaded below the button
* TBD: Inspect and Compare feature is not supported yet

### Select permissions

* Select permission types that you want to move from selected profiles to the new permission sets
* TBD: Create stand-alone permission set option is not currently supported. 
Workaround is to run the application multiple times with different selections.

### Transform

* Review the permission sets that will be created and rename them if needed by double-click on the name

### Remove Duplicates

* In case the newly created permission sets would be exactly the same, you have the option to unify them and create just one.
* TBD: Create a report of the transformations done.

### Output Options

#### Push to Org

* The changes in permission structure will be pushed to the Salesforce Instance.
* **IMPORTANT!**: If you choose to Transform the Profiles, permissions will be erased from the profiles. 
New permission sets with these permissions will be created and deployed, however, they have to be yet assigned back to the users.
* TBD: Create and deploy assignments of the newly created permission sets to the users.

#### Save to Filesystem

* The metadata will be saved to an output folder that will be created where the jar is located. 
It should automatically open when Save Metadata is pushed.
* How to Deploy to Org with Profile cleanup -  manually
  * Deploy metadata from folders “negative profles” and “permission_sets”
  
  
## Improvements roadmap - open to contributions

1. Create a report of the transformations done. Preferably as a text or csv file.
2. Create and deploy assignments of the newly created permission sets to the users.
3. Add the User Manual as Help to the app.
4. Display error messages on the UI.
5. Allow greater granularity in the "permissions to move" selection.
   * Frontend - allow breakdown of each permission type to atomic permissions.
   * Backend - support more complex filtering.
6. Implement the Inspect and Compare feature.

## How to contribute

* Create a fork of this repository.
* There is a GitHub workflow set to verify and build the application. You can access the build in [artifacts](https://docs.github.com/en/actions/managing-workflow-runs/downloading-workflow-artifacts). 
* When you're done developing create a pull request with your changes.
* After the merge to master the build is published to packages.

If you have any questions, please approach me on barbora.sourkova@enehano.cz
