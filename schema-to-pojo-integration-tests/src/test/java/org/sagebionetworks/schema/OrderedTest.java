package org.sagebionetworks.schema;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;
import org.sagebionetworks.schema.adapter.JSONObjectAdapterException;
import org.sagebionetworks.schema.adapter.org.json.EntityFactory;

/**
 * Test that checks that the order of the properties in the JSON schema are preserved.
 * @author jmhill
 *
 */
public class OrderedTest {
	private static final String ORDERED_SCHEMA = "{\r\n" + 
			"	\"name\":\"Ordered\",\r\n" + 
			"	\"properties\":{\r\n" + 
			"		\"a0\":{\r\n" + 
			"			\"type\":\"integer\",\r\n" + 
			"			\"description\":\"Product identifier\",\r\n" + 
			"			\"required\":true,\r\n" + 
			"		},\r\n" + 
			"		\"a1\":{\r\n" + 
			"			\"description\":\"Name of the product\",\r\n" + 
			"			\"type\":\"string\",\r\n" + 
			"			\"required\":true\r\n" + 
			"		},\r\n" + 
			"		\"a2\":{\r\n" + 
			"			\"required\":true,\r\n" + 
			"			\"type\": \"number\",\r\n" + 
			"			\"minimum\":0\r\n" + 
			"		},\r\n" + 
			"		\"a3\":{\r\n" + 
			"			\"type\":\"array\",\r\n" + 
			"			\"items\":{\r\n" + 
			"				\"type\":\"string\"\r\n" + 
			"			}\r\n" + 
			"		},\r\n" + 
			"		\"a4\":{\r\n" + 
			"			\"type\":\"boolean\",\r\n" + 
			"			\"description\":\"I'm a random boolean prop\",\r\n" + 
			"		},\r\n" + 
			"    },\r\n" + 
			"}";
	
	@Test
	public void testOrder() throws JSONObjectAdapterException{
		// Get the schema
		ObjectSchema schema = EntityFactory.createEntityFromJSONString(ORDERED_SCHEMA, ObjectSchema.class);
		Iterator<String> it = schema.getProperties().keySet().iterator();
		int index = 0;
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key);
			String expectedKey = "a"+index;
			assertEquals("The property order was not preserved!", expectedKey, key);
			index++;
		}
	}

}
