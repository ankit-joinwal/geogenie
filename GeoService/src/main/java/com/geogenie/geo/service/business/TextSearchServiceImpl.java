package com.geogenie.geo.service.business;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.geogenie.data.model.exception.ServiceErrorCodes;
import com.geogenie.data.model.exception.ServiceException;
import com.geogenie.data.model.ext.Places;
import com.geogenie.data.model.requests.TextSearchRequest;
import com.geogenie.geo.service.config.GAPIConfig;
import com.geogenie.geo.service.helper.TextSearchHelper;

/**
 * Service for Performing "Near by" search.
 * <a href="https://developers.google.com/places/web-service/search#PlaceSearchRequests">
 * @author ajoinwal
 *
 */
@Service
@Transactional
public class TextSearchServiceImpl implements TextSearchService{

	private static final Logger logger = LoggerFactory.getLogger(TextSearchServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private GAPIConfig gapiConfig;
	
	
	
	public GAPIConfig getGapiConfig() {
		return gapiConfig;
	}


	public void setGapiConfig(GAPIConfig gapiConfig) {
		this.gapiConfig = gapiConfig;
	}


	public RestTemplate getRestTemplate() {
		return restTemplate;
	}


	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	@Override
	public Places search(TextSearchRequest textSearchRequest) throws ServiceException{
		Places places= null;
		try{
			places = TextSearchHelper.executeSearch(restTemplate, textSearchRequest, gapiConfig);
		}catch(ServiceException exception){
			logger.error("Error occurred while performing text search",exception);
			throw exception;
		}catch(Exception exception){
			logger.error("Error occurred while performing text search",exception);
			throw new ServiceException(ServiceErrorCodes.ERR_050.toString(), exception.getMessage());
		}
		return places;
	}

}
