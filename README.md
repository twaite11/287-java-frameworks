
C. Customize the HTML User Interface
Prompt: Customize the HTML user interface for your customer’s application.

File: mainscreen.html

Change: Modified text elements to reflect the new business, "Tyler's Surfboard Shop". Headings for "Surfboard Parts" and "Surfboards."

D. Add an “About” Page
Prompt: Add an “About” page to the application to describe your chosen customer’s company and include navigation to and from the “About” page and the main screen.

Files: mainscreen.html and about.html

Change: A new file, about.html, was created with a description of the company. A new "About Us" button was added to mainscreen.html to link to this page, and a link back to the main screen was added to about.html.

E. Add a Sample Inventory
Prompt: Add a sample inventory appropriate for your chosen store.

Files: BootStrapData.java, InhousePart.java, and OutsourcedPart.java.

Changes: In BootStrapData.java, a conditional check was added to ensure the inventory is only created if the repositories are empty. Five new parts and five new products were created with names and prices appropriate for a surfboard shop. Constructors were added to both InhousePart.java and OutsourcedPart.java to accommodate the new parts.

F. Add a “Buy Now” Button
Prompt: Add a “Buy Now” button to your product list that decrements inventory by one.

Files: mainscreen.html and MainScreenControllerr.java.

Changes: A new "Buy Now" button was added to the product table in mainscreen.html. In MainScreenControllerr.java, a buyProduct method was created to handle the button's action, decrementing the product's inventory. An alert message was added to mainscreen.html to display the purchase status.

G. Modify Parts to Track Maximum and Minimum Inventory
Prompt: Modify the parts to track maximum and minimum inventory.

Files: Part.java, BootStrapData.java, InhousePart.html, OutsourcedPart.html, InhousePart.java, OutsourcedPart.java, and application.properties.

Changes: minInventory and maxInventory fields were added to the Part.java entity, along with new constructors and methods. The forms (InhousePart.html and OutsourcedPart.html) were updated with new input fields for these values. Sample parts in BootStrapData.java were updated with min/max values. The application.properties file was updated to use a new database name.

H. Add Validation for Between or at the Maximum and Minimum Fields
Prompt: Add validation for between or at the maximum and minimum fields.

Files: ValidPartInventory.java (new file), PartInventoryValidator.java (new file), ValidEnufParts.java, InhousePart.java, and OutsourcedPart.java.

Changes: A new validator was created to check if a part's inventory is within its specified min/max range. This validator was applied to the InhousePart and OutsourcedPart classes. The ValidEnufParts.java validator was modified to check that adding a product doesn't lower a part's inventory below its minimum.

I. Add Unit Tests
Prompt: Add at least two unit tests for the maximum and minimum fields to the PartTest class.

File: PartTest.java

Changes: Two new test methods, testInvBelowMin() and testInvAboveMax(), were added to PartTest.java to verify that an IllegalArgumentException is thrown when an invalid inventory value is set.

J. Remove Unused Validators
Prompt: Remove the class files for any unused validators in order to clean your code.

Files: No changes were needed.

Changes: No validator files were removed, as a review of the code indicated that all custom validators were in use.
