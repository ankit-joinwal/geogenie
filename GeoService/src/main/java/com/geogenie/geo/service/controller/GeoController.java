package com.geogenie.geo.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.geogenie.data.model.exception.ServiceException;
import com.geogenie.data.model.ext.PlaceDetails;
import com.geogenie.data.model.ext.Places;
import com.geogenie.data.model.requests.NearbySearchRequest;
import com.geogenie.data.model.requests.PlaceDetailsRequest;
import com.geogenie.data.model.requests.TextSearchRequest;
import com.geogenie.geo.service.business.NearbySearchService;
import com.geogenie.geo.service.business.PlaceDetailService;
import com.geogenie.geo.service.business.TextSearchService;

@RestController
@RequestMapping("/places")
public class GeoController {

	private static final Logger logger = LoggerFactory.getLogger(GeoController.class);
	
	@Autowired
	private NearbySearchService nearbySearchService;
	
	@Autowired
	private TextSearchService textSearchService;
	
	@Autowired
	private PlaceDetailService placeDetailService;
	
	
	public void setPlaceDetailService(PlaceDetailService placeDetailService) {
		this.placeDetailService = placeDetailService;
	}


	public void setTextSearchService(TextSearchService textSearchService) {
		this.textSearchService = textSearchService;
	}


	public void setNearbySearchService(NearbySearchService nearbySearchService) {
		this.nearbySearchService = nearbySearchService;
	}


	@RequestMapping(value="/place/nearby",method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public Places getNearbyPlaces(@RequestParam(required=true,value="location" ) String location, @RequestParam(required=true,value="radius") String radius,
			@RequestParam(required=true,value="types") String types,@RequestParam(required=false,value="name") String name,@RequestParam(required=false,value="rankBy") String rankBy) throws ServiceException{
		//TODO : Auth check, Validating request
		logger.info("### Nearby search Request recieved ###");
		Places places = null;
		
		try{
			NearbySearchRequest nearbySearchRequest = new NearbySearchRequest();
			nearbySearchRequest.setLocation(location);
			nearbySearchRequest.setName(name);
			nearbySearchRequest.setRadius(radius);
			nearbySearchRequest.setRankBy(rankBy);
			nearbySearchRequest.setTypes(types);
			
			places = nearbySearchService.search(nearbySearchRequest);
		}catch(ServiceException serviceException){
			logger.info("Error occured while performing near by search ",serviceException);
			throw serviceException;
		}
		return places;
	}
	
	@RequestMapping(value="/place/tsearch",method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public Places getPlacesByTextSearch(@RequestParam(required=true,value="query" )String query,@RequestParam(required=true,value="location" ) String location, @RequestParam(required=true,value="radius") String radius,
			@RequestParam(required=true,value="types") String types,@RequestParam(required=false,value="name") String name,@RequestParam(required=false,value="rankBy") String rankBy) throws ServiceException{
		//TODO : Auth check, Validating request
		logger.info("### Text search Request recieved ###");
		Places places = null;
		
		try{
			TextSearchRequest textSearchRequest = new TextSearchRequest();
			textSearchRequest.setLocation(location);
			textSearchRequest.setName(name);
			textSearchRequest.setRadius(radius);
			textSearchRequest.setRankBy(rankBy);
			textSearchRequest.setTypes(types);
			textSearchRequest.setQuery(query);
			
			places = textSearchService.search(textSearchRequest);
		}catch(ServiceException serviceException){
			logger.info("Error occured while performing near by search ",serviceException);
			throw serviceException;
		}
		return places;
	}
	
	@RequestMapping(value="/place/detail",method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public PlaceDetails getPlaceDetails(@RequestParam(required=true,value="placeid") String placeId) throws ServiceException{
		logger.info("### Place detail request recieved ###");
		
		PlaceDetails placeDetails = null;
		try{
			PlaceDetailsRequest placeDetailsRequest = new PlaceDetailsRequest();
			placeDetailsRequest.setPlaceId(placeId);
			placeDetails = placeDetailService.getPlaceDetails(placeDetailsRequest);
		}catch(ServiceException serviceException){
			logger.info("Error occured while getting place details ",serviceException);
			throw serviceException;
		}
		
		return placeDetails;
	}
}
