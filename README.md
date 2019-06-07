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
🚫This is a placeholder, replace the endpoints, access control, and description to match your project
#### User Routes
| Method | Endpoint                | Access Control      | Description                                        |
| ------ | ----------------------- | ------------------- | -------------------------------------------------- |
| GET    | `/users`                | Admin               | Returns paginated list of all users                |
| GET    | `/users/:userId`        | Admin               | Returns info for a single user.                    |
| POST   | `/newuser`              | Admin               | Creates a new user                                 |
| PUT    | `/update-user/:userId`  | Admin               | Updates user with given id                         |
| DELETE | `/users/:userId`        | Admin               | Deletes user with given id                         |

#### Farmer Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/farmers/all`          | all users      | Returns the information for an organization. |
| GET    | `/farmers/farmer/{id}`  | all users      | Returns the information for an organization. |
| POST   | `/farmers/farmer/{id}`  | all users      | Returns the information for an organization. |
| POST   | `/farmers/search`       | all users      | Returns the information for an organization. |
| PUT    | `/farmers/:orgId`       | all users      | Modify an existing organization.             |
| DELETE | `/farmers/:orgId`       | all users      | Delete an organization.                      |

#### Organization Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/organizations/:orgId` | all users      | Returns the information for an organization. |
| PUT    | `/organizatoins/:orgId` | all users      | Modify an existing organization.             |
| DELETE | `/organizations/:orgId` | all users      | Delete an organization.                      |

#### Retailer Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/organizations/:orgId` | all users      | Returns the information for an organization. |
| PUT    | `/organizatoins/:orgId` | all users      | Modify an existing organization.             |
| DELETE | `/organizations/:orgId` | all users      | Delete an organization.                      |

#### Item-Type Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/organizations/:orgId` | all users      | Returns the information for an organization. |
| PUT    | `/organizatoins/:orgId` | all users      | Modify an existing organization.             |
| DELETE | `/organizations/:orgId` | all users      | Delete an organization.                      |

#### Crop-Type Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/organizations/:orgId` | all users      | Returns the information for an organization. |
| PUT    | `/organizatoins/:orgId` | all users      | Modify an existing organization.             |
| DELETE | `/organizations/:orgId` | all users      | Delete an organization.                      |

#### Transaction Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/organizations/:orgId` | all users      | Returns the information for an organization. |
| PUT    | `/organizatoins/:orgId` | all users      | Modify an existing organization.             |
| DELETE | `/organizations/:orgId` | all users      | Delete an organization.                      |

#### Installment Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/organizations/:orgId` | all users      | Returns the information for an organization. |
| PUT    | `/organizatoins/:orgId` | all users      | Modify an existing organization.             |
| DELETE | `/organizations/:orgId` | all users      | Delete an organization.                      |

#### Transaction-Item Routes

| Method | Endpoint                | Access Control | Description                                  |
| ------ | ----------------------- | -------------- | -------------------------------------------- |
| GET    | `/organizations/:orgId` | all users      | Returns the information for an organization. |
| PUT    | `/organizatoins/:orgId` | all users      | Modify an existing organization.             |
| DELETE | `/organizations/:orgId` | all users      | Delete an organization.                      |

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




# Back-End for Tieme-Ndo Gana Agriculture Database

- docs for endpoint here

# Get All Users

    https://chasegarsee-tiemendo.herokuapp.com/users

# Create New User

    https://chasegarsee-tiemendo.herokuapp.com/newuser

# Get All Staff Members

    https://chasegarsee-tiemendo.herokuapp.com/staff

# Get Staff By ID number

    https://chasegarsee-tiemendo.herokuapp.com/staff{staffid}

# Create New Staff Member

    https://chasegarsee-tiemendo.herokuapp.com/staff

# Get All Clients

    https://chasegarsee-tiemendo.herokuapp.com/clients

# Get Client By ID

    https://chasegarsee-tiemendo.herokuapp.com/client{clientid}

# Create New Client

     https://chasegarsee-tiemendo.herokuapp.com/client

# Update Existing Client

     https://chasegarsee-tiemendo.herokuapp.com/client/{id}

# Delete Exisiting Client

     https://chasegarsee-tiemendo.herokuapp.com/client{id}
