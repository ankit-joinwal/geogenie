package com.geogenie.geo.service.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.geogenie.Constants;
import com.geogenie.data.model.exception.ServiceErrorCodes;
import com.geogenie.data.model.exception.ServiceException;
import com.geogenie.data.model.ext.PlaceDetails;
import com.geogenie.data.model.requests.NearbySearchRequest;
import com.geogenie.data.model.requests.PlaceDetailsRequest;
import com.geogenie.geo.service.config.GAPIConfig;

public class PlaceDetailsHelper {

	private static final Logger logger = LoggerFactory.getLogger(PlaceDetailsHelper.class);
	
	public static PlaceDetails executeSearch(RestTemplate restTemplate,PlaceDetailsRequest placeDetailsRequest,GAPIConfig gapiConfig) throws ServiceException{
		StringBuilder url = new StringBuilder(gapiConfig.getPlaceDetailsURL());
		url.append(gapiConfig.getDataExchangeFormat() + Constants.QUESTIONMARK);
		url.append(PlaceDetailsRequest.PlaceDetailsRequestParams.PLACEID.getName()
				+ Constants.EQUAL
				+ placeDetailsRequest.getPlaceId());
		url.append(Constants.AMP
				+ NearbySearchRequest.NearbySearchRequestParamNames.KEY
						.getName() + Constants.EQUAL + gapiConfig.getGapiKey());
		logger.info("### Inside PlaceDetailsHelper.executeSearch | URL : {} "
				+ url.toString());

		logger.info("### Executing Search ###");
		ResponseEntity<PlaceDetails> placesResponse = restTemplate.exchange(
				url.toString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<PlaceDetails>() {
				});
		HttpStatus returnStatus = placesResponse.getStatusCode();
		boolean isSuccess = returnStatus.is2xxSuccessful();
		if(isSuccess){
			logger.info("### Search successful for url : {} "+url.toString());
			
		}else{
			if(returnStatus.is4xxClientError()){
				throw new ServiceException(ServiceErrorCodes.ERR_010.toString(),"Invalid Client Request");
			}else if (returnStatus.is5xxServerError()){
				throw new ServiceException(ServiceErrorCodes.ERR_010.toString(),"Error with backend services");
			}
		}
		
		
		return placesResponse.getBody();
	}
}
