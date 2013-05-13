import java.util.UUID;

import de.fraunhofer.fokus.fuzzing.fuzzino.FuzzedValue;
import de.fraunhofer.fokus.fuzzing.fuzzino.StringRequestProcessor;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.java.RequestFactory;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.java.StringEncoding;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.java.StringRequest;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.java.StringSpecification;
import de.fraunhofer.fokus.fuzzing.fuzzino.request.java.StringType;
import de.fraunhofer.fokus.fuzzing.fuzzino.response.java.FuzzedValuesByGenerators;
import de.fraunhofer.fokus.fuzzing.fuzzino.response.java.GeneratorSpecificFuzzedValues;
import de.fraunhofer.fokus.fuzzing.fuzzino.response.java.StringResponse;


public class JavaRequestClassesExample {
	
	public static void main(String[] args) {
		StringRequest stringRequest = RequestFactory.INSTANCE.createStringRequest();
		stringRequest.setName("MyFirstRequest");
		stringRequest.setMaxValues(10);
		
		StringSpecification stringSpecification = RequestFactory.INSTANCE.createStringSpecification();
		stringSpecification.setEncoding(StringEncoding.UTF8);
		stringSpecification.setType(StringType.FILENAME);
		stringSpecification.setIgnoreLengths(true);
		
		stringRequest.setSpecification(stringSpecification);
		
		StringRequestProcessor stringRequestProcessor = new StringRequestProcessor(stringRequest, UUID.randomUUID());
		StringResponse stringResponse = stringRequestProcessor.getResponse();
		FuzzedValuesByGenerators<String> byGenerators = stringResponse.getGeneratorBasedSection();
		for (GeneratorSpecificFuzzedValues<String> generatorSpecificValues : byGenerators) {
			for (FuzzedValue<String> fuzzedValue : generatorSpecificValues) {
				System.out.println(fuzzedValue);
			}
			
		}
		
	}

}