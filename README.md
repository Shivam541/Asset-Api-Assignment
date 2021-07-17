# Asset-Api-Assignment

Any company has assets like laptops, keyboard, stationary items, furniture etc. It is important for a company to keep a track of these assets, their condition,
and assignment to people to ensure rightful use of these assets and keep a track of them.  This is a REST based app which exposes the following services : 

<b>Go to the docs folder of the project for documentation of project classes.</b>

Project Endpoints - Below URI's will work for localhost 

<i><b>Categories resource</b></i>. 
  
A category has a unique ID, name and description 

*<b>List all Categories</b> - Get Http://localhost:8081/categories<br>

*<b>Add Category</b> - Post Http://localhost:8081/categories<br>
It requires a json object with category data to be passed in request body on a post request. 
<pre>sample request body - {
        "name": "electronics",
        "description": "electronics items"
    }
</pre>
*<b>Update Category</b> - Put Http://localhost:8081/categories/{categoryId}<br>
It requires a json object with updated category data to be passed in request body on a put request. 
<pre>sample request body - {
        "name": "electronics",
        "description": "electronics items"
    }
</pre>

<i><b>Assets resource</b></i>.
Asset has name, purchase date, condition notes, a category, assignment status - Available, Assigned, Recovered.

*<b>List all Assets</b> - Get Http://localhost:8081/assets<br>

*<b>Find Asset Based On Name</b> - Get Http://localhost:8081/assets/{assetName}<br>

*<b>Add Asset</b> - Post Http://localhost:8081/assets<br>
It requires a json object with asset data to be passed in request body on a post request. 
<pre>Sample Request body - {
        "name": "pen",
        "conditionNotes": "cannot be used more",
        "purchaseDate": "2021-04-05",
        "category": {
            "id": 11
        },
        "assignmentStatus": "recovered"
    }
</pre>

*<b>Update Asset</b> - Put Http://localhost:8081/assets/{assetName}<br>
It requires a json object with updated asset data to be passed in request body on a put request. 
<pre>Sample Request body - {
        "conditionNotes": "cannot be used more",
        "purchaseDate": "2021-04-05",
        "category": {
            "id": 11
        },
        "assignmentStatus": "recovered"
    }
</pre>

*<b>Delete Asset</b> - Delete Http://localhost:8081/assets/{assetName}<br>

*<b>Assign an Asset to an Employee </b> - Put Http://localhost:8081/assets/{assetName}/assign/{employeeId}<br>

*<b>Recover an Asset from an Employee </b> - Delete Http://localhost:8081/assets/{assetName}/assign<br>
