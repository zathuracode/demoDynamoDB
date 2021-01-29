package com.vortexbird.demo.dynamodb;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;

public class BorrarUsuario {

	static AmazonDynamoDBClient dynamoDB;

	private static void init() throws Exception {

		BasicAWSCredentials credentials = null;
		credentials = new BasicAWSCredentials(VortexAWSCredentials.AWSAccessKeyId, VortexAWSCredentials.AWSSecretKey);

		dynamoDB = new AmazonDynamoDBClient(credentials);
		Region usWest2 = Region.getRegion(Regions.US_EAST_1);
		dynamoDB.setRegion(usWest2);
	}

	public static void main(String[] args) throws Exception {
		init();
		try {
			String tableName = "usuarios";

			DynamoDB dyDb = new DynamoDB(dynamoDB);

			Table table = dyDb.getTable(tableName);

			String email = "jperez";

			DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("email", email);

			System.out.println("Delete the item...");
			table.deleteItem(deleteItemSpec);
			System.out.println("Delete succeeded:\n");

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to AWS, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with AWS, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

	}

}
