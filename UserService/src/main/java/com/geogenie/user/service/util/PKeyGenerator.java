package com.geogenie.user.service.util;

import java.util.UUID;

/**
 * @author Ankit.Joinwal
 *
 */
public class PKeyGenerator {

	public static String generateKey() {
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		return randomUUIDString;
	}
}
