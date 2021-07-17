# Asset-Api-Assignment

Any company has assets like laptops, keyboard, stationary items, furniture etc. It is important for a company to keep a track of these assets, their condition,
and assignment to people to ensure rightful use of these assets and keep a track of them.  This is a REST based app which exposes the following services : 

Below URI's will work for localhost 

<i><b>Categories resource</b></i>. 
A category has a unique ID, name and description 

*<b>List all Categories</b> - Get Http://localhost:8081/categories<br>

*<b>Add Category</b> - Post Http://localhost:8081/categories<br>
It requires a json object with category data to be passed in request body on a post request. 

*<b>Update Category</b> - Put Http://localhost:8081/categories/{categoryId}<br>
It requires a json object with updated category data to be passed in request body on a put request. 

<i><b>Assets resource</b></i>.
Asset has name, purchase date, condition notes, a category, assignment status - Available, Assigned, Recovered.

*<b>List all Assets</b> - Get Http://localhost:8081/assets<br>

*<b>Find Asset Based On Name</b> - Get Http://localhost:8081/assets/{assetName}<br>

*<b>Add Asset</b> - Post Http://localhost:8081/assets<br>
It requires a json object with asset data to be passed in request body on a post request. 

*<b>Update Asset</b> - Put Http://localhost:8081/assets/{assetName}<br>
It requires a json object with updated asset data to be passed in request body on a put request. 

*<b>Delete Asset</b> - Delete Http://localhost:8081/assets/{assetName}<br>

*<b>Assign an Asset to an Employee </b> - Put Http://localhost:8081/assets/{assetName}/assign/{employeeId}<br>

*<b>Recover an Asset from an Employee </b> - Delete Http://localhost:8081/assets/{assetName}/assign<br>
