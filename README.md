# MongoDb

# MongoDB Project

This project is a MongoDB facade implementation using either of the following dependencies:

- com.googlecode.sli4j:sli4j-acl:2.0
- org.mongodb:mongodb-driver:3.12.13
- org.mongodb:mongodb-driver-sync:4.9.1

Alternatively, you can download the latest version from https://repo1.maven.org/maven2/org/mongodb/mongodb-jdbc and choose the jar file from the folder with the highest version number (e.g. `/2.0.2/mongodb-jdbc-2.0.2-all.jar`). Then add the `slf4j.jul.to2` module (from GUI).

## MongoDB API

This implementation provides a facade for all MongoDB calls. Below is an explanation of each MongoDB call:

- `find`: This call is used to query the MongoDB collection for documents that match the specified criteria.
- `insert`: This call is used to insert a new document into the MongoDB collection.
- `update`: This call is used to update one or more documents in the MongoDB collection that match the specified criteria.
- `delete`: This call is used to delete one or more documents from the MongoDB collection that match the specified criteria.
- `aggregate`: This call is used to perform aggregation operations on the MongoDB collection.
- `count`: This call is used to count the number of documents in the MongoDB collection that match the specified criteria.
- `distinct`: This call is used to retrieve the distinct values of a field in the MongoDB collection that match the specified criteria.
- `bulkWrite`: This call is used to perform multiple insert, update, or delete operations in a single request to the MongoDB server.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.
