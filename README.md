# API Documentation

#### Backend deployed at [tieme-ndo-backend](https://tieme-ndo-backend.herokuapp.com/) <br>

## Getting started

To get the server running locally:

- Clone this repo
- Set up PostgreSQL database locally with name, username, and password "tiemendo" 
- Alternatively, change name, username, and password values to your liking in applications.properties file
- Local testing is currently configured using localhost port 4040

### The Stack
Java Rest Api Built with Spring Framework on a postgres database deployed to Heroku

-    `Spring` allows for amazing customization and extensibility
-    `Hibernate` makes for intuitive and easy access to our database and works so smoothly with spring
-    `Postgresql` is an amazing relational database that can handle our extensive models and integrates great with Herok
-    `Heroku` is such a great platform that effortless integrates with our spring application and maven build. and a smooth CI pipeline directly plugged into github

## Endpoints

All Endpoints that return lists take pageable query paramaters `page, size, & sort`

#### User Routes

| Method | Endpoint                | Access Control      | Description                                        |
| ------ | ----------------------- | ------------------- | -------------------------------------------------- |
| GET    | `/users/users`                | Admin               | Returns paginated list of all users                |
| GET    | `/users/users/:userId`        | Admin               | Returns info for a single user.                    |
| GET    | `/users/usertype`        | Admin               | Returns roles of current user.                    |
| GET    | `/users/username/:username`        | Admin               | Returns a list of users with name starting with :username   |
| POST   | `/users/newuser`              | Admin               | Creates a new user                                 |
| PUT    | `/users/update-user/:userId`  | Admin               | Updates user with given id                         |
| DELETE | `/users/users/:userId`        | Admin               | Deletes user with given id                         |

#### Farmer Routes - refers to client model with type "farmer"

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/farmers/all`          | all users      | Returns a paginated list of all farmers      |
| GET   | `/farmers/search`       | all users      | Returns paginated list of farmers with given criteria, takes query params `page, size, sort, name, location` |
| GET    | `/farmers/farmer/{id}`  | all users      | Returns farmer object with given ID           |
| POST   | `/farmers/add`  | all users      | Creates and returns a new farmer object |
| PUT    | `/farmers/farmer/{id}`       | all users      | Modify and return updated farmer object with given id             |
| DELETE | `/farmers/farmer/{id}`       | all users      | Delete a farmer with given id.                      |

#### Organization Routes

| Method | Endpoint                                     | Access Control | Description                                    |
| ------ | -------------------------------------------- | -------------- | ---------------------------------------------- |
| GET    | `/organizations/:orgId`                       | all users      | Returns the information for an organization.   |
| GET    | `/organizations/organizations-list`         | all users      | Returns the information for all organizations. |
| GET   | `/organizations/search`       | all users      | Returns paginated list of organizations with given criteria, takes query params `page, size, sort, name, location` |
| PUT    | `/organizations/update-organization/:orgId` | all users      | Modify an existing organization.               |
| POST   | `/organizations/new-organization`             | all users      | Creates a new organization.                    |
| DELETE | `/organizations/:orgId`                       | all users      | Delete an organization.                        |
|Branches|
| GET    | `/organizations/contacts/:orgId`                | all users      | Returns a list of all branches for given organization|
| POST   | `/organizations/branch/:orgId`             | all users      | Adds a new branch to given organization. |
| PUT   | `/organizations/branch/:id`             | all users      | Updates branch with given :id   |
| DELETE | `/organizations/contact/:contactId`         | all users      | Delete a branch.  |

#### Installment Routes

| Method | Endpoint                             | Access Control | Description                                   |
| ------ | ------------------------------------ | -------------- | --------------------------------------------- |
| GET    | `/installment/:installmentId`        | all users      | Returns the information for an installment.   |
| GET    | `/installment/installment-list`      | all users      | Returns a list of all installments. |
| PUT    | `/update-installment/:installmentId` | all users      | Modify an existing installment. Returns a list of all installments.   |
| POST   | `/new-installment/:clientId`         | all users      | Creates a new installment. Returns a list of all installments. |
| DELETE | `/installment/:installmentId`        | all users      | Delete an installment. Returns a list of all installments. |

#### Retailer Routes - refers to client model with type "retailer"

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/retailer/reatailers`  | all users  | Returns a list of all retailers |
| GET    | `/retailer/search`  | all users  | Returns a list of all retailers matching search criteria |
| GET    | `/retailer/{id}`  | all users | Returns the retailer with given {id} |
| POST    | `/retailer/add`  | all users | Creates and Returns a new retailer based on given information |
| PUT    | `/retailer/update/{id}` | all users | Returns and modifies an existing retailer. |
| DELETE | `/retailer/delete/{id}` | all users | Deletes retailer with given id.                      |

#### Item-Type Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/itemtype/all` | all users      | Returns A list of all Items sorted alphabetically |
| POST    | `/itemtype/add` | all users      | Creates an ItemType with given information. Returns A list of all Items sorted alphabetically |
| PUT    | `/itemtype/update/{itemtypeid}` | all users      | Modify an existing ItemType with id matching {itemtypeid}. Returns A list of all Items sorted alphabetically |
| DELETE | `/itemtype/delete/{itemtypeid}` | all users      | Delete item type with id matching {itemtypeid}. Returns A list of all Items sorted alphabetically |

#### Transaction Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/transaction/all` | all users | Returns a pageable list of all transaction |
| GET    | `/transaction/{id}` | all users | Returns the transaction with given id |
| GET    | `/transaction/client/{id}` | all users | Returns a list of transactions for client with given id |
| POST   | `/transaction/add/{clientId}` | all users | Adds a new transaction to client with given id, Returns a list of transactions |
| PUT    | `/transaction/update/{transactionId}` | all users | Modify an existing transaction. Returns a list of transactions  |
| DELETE | `/transaction/delete/{transactionId}` | all users | Delete an existing transaction. Returns a list of transactions  |


# Data Model

#### CLIENT

---

```
{
    id: long, // (generated value) maps directly to id of subclasses
    type: string, // ["FARMER", "RETAILER"] 
    startyear: long,
    lead: boolean,
    transactions: [transaction, ...],
    installments: [installment, ...],
    
    // Client Contact and Demographic Info
    title: string,
    name: string,
    gender: string,
    nationality: string,
    dateofbirth: string,
    educationlevel: string,
    position: string,
    phone: string,
    email: string,
    
    // Client location Info
    address: string,
    region: string,
    district: string,
    community: string,
    landmark: string,
}
```


#### ORGANIZATION

---

```
{
  id: long, // (generated value) relates directly to id of associated client
  beneficiaries: int,
  headquarters: string,
  name: string,
  lead: boolean,
  organizationbranches: [organizationbranch, ...]
}
```

#### ORGANIZATIONBRANCH

---

```
{
  id: long, // (generated value)
  organization: organization,
  
  // Branch Location Info
  address: string,
  district: string,
  landmark: string,
  region: string,
  
  // Branch Contact Info
  email: string,
  name: string,
  organization: Organization,
  phone: string,
  position: string
}
```

#### INSTALLMENT

---

```
{
    id: long, // (generated value)
    amountPaid: double,
    datePaid: Date,
    mode: string,
    officer: string,
    client: Client,
}
```

#### TRANSACTION

---

```
{
  id: long, (generated unique id)
  type: string, // ["CASH", "CREDIT"]
  date: date,
  personnel: string,
  inputs: [transactionItems, ...],
  total: double, // total cost of transaction based on input price and qty
  client: client
}
```

#### TRANSACTIONITEM

---

```
{
  id: long, //(generated unique id)
  quantity: int,
  unitPrice: double,
  item: itemType,
  transaction: transaction
}
```

#### ITEMTYPE

---

```
// This is used for keeping track of inventory as well as generating the drop downs for transactions
{
    id: long, // (generated value)
    name: string,
    transactions: [transactionItem, ...],
    active: boolean,
    quantity: int,
}
```

#### USERS

---

```
{
  id: long, // (generated value)
  username: string, // (unique)
  authority: ["ADMIN", "USER", ...]
}
```
    
## Contributing

When contributing to this repository, please first discuss the change you wish to make via issue, email, or any other method with the owners of this repository before making a change.

### Issue/Bug Request

 **If you are having an issue with the existing project code, please submit a bug report under the following guidelines:**
 - Check first to see if your issue has already been reported.
 - Check to see if the issue has recently been fixed by attempting to reproduce the issue using the latest master branch in the repository.
 - Create a live example of the problem.
 - Submit a detailed bug report including your environment & browser, steps to reproduce the issue, actual and expected outcomes,  where you believe the issue is originating from, and any potential solutions you have considered.

### Feature Requests

We would love to hear from you about new features which would improve this app and further the aims of our project. Please provide as much detail and information as possible to show us why you think your new feature should be implemented.

### Pull Requests

If you have developed a patch, bug fix, or new feature that would improve this app, please submit a pull request. It is best to communicate your ideas with the developers first before investing a great deal of time into a pull request to ensure that it will mesh smoothly with the project.

Remember that this project is licensed under the MIT license, and by submitting a pull request, you agree that your work will be, too.

#### Pull Request Guidelines

- Ensure any install or build dependencies are removed before the end of the layer when doing a build.
- Update the README.md with details of changes to the interface, including new plist variables, exposed ports, useful file locations and container parameters.
- Ensure that your code conforms to our existing code conventions and test coverage.
- Include the relevant issue number, if applicable.
- You may merge the Pull Request in once you have the sign-off of two other developers, or if you do not have permission to do that, you may request the second reviewer to merge it for you.

### Attribution

These contribution guidelines have been adapted from [this good-Contributing.md-template](https://gist.github.com/PurpleBooth/b24679402957c63ec426).

## Documentation

See [Frontend Documentation](https://github.com/labs13-agriculture/front-end) for details on the fronend of our project.
