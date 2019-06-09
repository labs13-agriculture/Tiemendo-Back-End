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

#### User Routes

| Method | Endpoint                | Access Control      | Description                                        |
| ------ | ----------------------- | ------------------- | -------------------------------------------------- |
| GET    | `/users/users`                | Admin               | Returns paginated list of all users                |
| GET    | `/users/users/:userId`        | Admin               | Returns info for a single user.                    |
| POST   | `/users/newuser`              | Admin               | Creates a new user                                 |
| PUT    | `/users/update-user/:userId`  | Admin               | Updates user with given id                         |
| DELETE | `/users/users/:userId`        | Admin               | Deletes user with given id                         |

#### Farmer Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/farmers/all`          | all users      | Returns a paginated list of all farmers      |
| GET    | `/farmers/farmer/{id}`  | all users      | Returns Famer object with given ID           |
| POST   | `/farmers/add`  | all users      | Creates and returns a new farmer object |
| POST   | `/farmers/search`       | all users      | Returns paginated list of farmers with given criteria |
| PUT    | `/farmers/farmer/{id}`       | all users      | Modify and return updated farmer object with given id             |
| DELETE | `/farmers/farmer/{id}`       | all users      | Delete a farmer with given id.                      |

#### Organization Routes

| Method | Endpoint                                     | Access Control | Description                                    |
| ------ | -------------------------------------------- | -------------- | ---------------------------------------------- |
| GET    | `/organizations/:orgId`                      | all users      | Returns the information for an organization.   |
| GET    | `/organizations/contact-list`                | all users      | Returns the information for a contact.         |
| GET    | `/organizations/locations-list`              | all users      | Returns the information for a location.        |
| GET    | `/organizations/:organizations-list`         | all users      | Returns the information for all organizations. |
| PUT    | `/organizations/:update-organization/:orgId` | all users      | Modify an existing organization.               |
| POST   | `/organizations/new-organization`            | all users      | Creates a new organization.                    |
| DELETE | `/organizations/:orgId`                      | all users      | Delete an organization.                        |
| DELETE | `/organizations/:contact/:contactId`         | all users      | Delete a contact.                              |
| DELETE | `/organizations/:location/:locationId`       | all users      | Delete a location.                             |

#### Installment Routes

| Method | Endpoint                             | Access Control | Description                                   |
| ------ | ------------------------------------ | -------------- | --------------------------------------------- |
| GET    | `/installment/:installmentId`        | all users      | Returns the information for an installment.   |
| GET    | `/installment/installment-list`      | all users      | Returns the information for all installments. |
| PUT    | `/update-installment/:installmentId` | all users      | Modify an existing installment.               |
| POST   | `/new-installment/:clientId`         | all users      | Creates a new installment.                    |
| DELETE | `/installment/:installmentId`        | all users      | Delete an installment.                        |

#### Retailer Routes

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
| GET    | `/itemtype/all` | all users      | Returns the information for an organization. |
| POST    | `/itemtype/add` | all users      | Returns and creates an ItemType with given information. |
| PUT    | `/itemtype/update/{itemtypeid}` | all users      | Modify and return an existing ItemType with id matching {itemtypeid}. |
| DELETE | `/itemtype/delete/{itemtypeid}` | all users      | Delete item type with id matching {itemtypeid}|

#### Crop-Type Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/croptypes/all` | all users      | Returns a list of all CropTypes |
| GET    | `/croptypes/crop/{id}` | all users      | Returns the information for CropType with given {id}, also takes a boolean queary param `yields` that includes yields of given crop |
| POST   | `/croptypes/new/{id}/` | all users      | Returns and creates a CropType with given information |
| PUT    | `/croptypes/update/{id}/` | all users      | Modify and return an existing CropType with given {id}.|
| DELETE | `/croptypes/delete/{id}` | all users      | Delete CropType with given id.  |

#### Transaction Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/transaction/all` | all users      | Returns a pageable list of all transaction |
| GET    | `/transaction/{id}` | all users      | Returns the transaction with given id |
| GET    | `/transaction/client/{id}` | all users      | Returns a list of transactions for client with given id |
| POST   | `/transaction/add/{clientId}` | all users      | Adds a new transaction to client with given id |
| PUT    | `/transaction/update/{transactionId}` | all users      | Modify an existing transaction.             |
| DELETE | `/transaction/delete/{transactionId}` | all users      | Delete an existing transaction.                      |

#### Transaction-Item Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/transaction-item/all` | all users  | Returns a pageable list of all Transaction-Items |
| GET    | `/transaction-item/{id}`| all users  | Returns Transaction Item with given Id       |
| POST   | `/transaction-item/add` | all users  | Adds a new Transaction Item with given Info     |
| PUT    | `/transaction-item/update/{titemId}` | all users | Modify an existing Transaction Item.     |
| DELETE | `/transaction-item/delete/{titemId}` | all users | Delete a Transaction Item.         |

#### Yield Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/organizations/:orgId` | all users      | Returns the information for an organization. |
| PUT    | `/organizatoins/:orgId` | all users      | Modify an existing organization.             |
| DELETE | `/organizations/:orgId` | all users      | Delete an organization.                      |



# Data Model

🚫This is just an example. Replace this with your data model

#### CLIENT

---

```
{
    id: long (generated value)
    name: string
    isLead: boolean
    type: string
    transactions: [transaction]
    installments: [installment]
}
```


#### ORGANIZATION (extends CLIENT)

---

```
{
  id: long (generated value)
  installments: [installment]
  name: STRING
  type: STRING
  transactions: [transaction]
  beneficiaries: int
  headquarters: STRING
  isLead: boolean
  organizationcontacts: [organizationcontact]
  organizationlocations: [organizationlocations]
}
```

#### ORGANIZATIONLOCATION

---

```
{
  address: string
  district: string
  landmark: string
  organization: organization
  region: string
  ogranizationlocationid: long (generated value)
}
```

#### ORGANIZATIONCONTACTS

---

```
{
    organizationcontactid: long (generated value)
    email: string
    name: string
    organization: Organization
    phone: string
    position: string
}
```

#### CROPTYPE

---

```
{
    id: long (generated value)
    cropName: string
    yields: [yield]
    active: boolean
}
```

#### INSTALLMENT

---

```
{
    id: long (generated value)
    amountPaid: double
    datePaid: Date
    mode: string
    officer: string
    client: Client
}
```

#### USERS

---

```
{
  id: UUID
  organization_id: UUID foreign key in ORGANIZATIONS table
  first_name: STRING
  last_name: STRING
  role: STRING [ 'owner', 'supervisor', 'employee' ]
  email: STRING
  phone: STRING
  cal_visit: BOOLEAN
  emp_visit: BOOLEAN
  emailpref: BOOLEAN
  phonepref: BOOLEAN
}
```

## 2️⃣ Actions

🚫 This is an example, replace this with the actions that pertain to your backend

`getOrgs()` -> Returns all organizations

`getOrg(orgId)` -> Returns a single organization by ID

`addOrg(org)` -> Returns the created org

`updateOrg(orgId)` -> Update an organization by ID

`deleteOrg(orgId)` -> Delete an organization by ID
<br>
<br>
<br>
`getUsers(orgId)` -> if no param all users

`getUser(userId)` -> Returns a single user by user ID

`addUser(user object)` --> Creates a new user and returns that user. Also creates 7 availabilities defaulted to hours of operation for their organization.

`updateUser(userId, changes object)` -> Updates a single user by ID.

`deleteUser(userId)` -> deletes everything dependent on the user

## 3️⃣ Environment Variables

In order for the app to function correctly, the user must set up their own environment variables.

create a .env file that includes the following:

🚫 These are just examples, replace them with the specifics for your app
    
    *  STAGING_DB - optional development db for using functionality not available in SQLite
    *  NODE_ENV - set to "development" until ready for "production"
    *  JWT_SECRET - you can generate this by using a python shell and running import random''.join([random.SystemRandom().choice('abcdefghijklmnopqrstuvwxyz0123456789!@#\$%^&amp;*(-*=+)') for i in range(50)])
    *  SENDGRID_API_KEY - this is generated in your Sendgrid account
    *  stripe_secret - this is generated in the Stripe dashboard
    
## Contributing

When contributing to this repository, please first discuss the change you wish to make via issue, email, or any other method with the owners of this repository before making a change.

Please note we have a [code of conduct](./code_of_conduct.md). Please follow it in all your interactions with the project.

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

See [Frontend Documentation](🚫link to your frontend readme here) for details on the fronend of our project.
🚫 Add DS iOS and/or Andriod links here if applicable.
