package com.sdsoft.model.communication;

import java.io.StringReader;

import org.apache.log4j.Logger;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;

/**
 * User: DIZEM Time: 11-3-25 上午12:49
 */
public class JSONUtil {

	/**
	 * logger instance
	 */
	private static Logger log = Logger.getLogger(JSONUtil.class);

	/**
	 * convert object to JSON string
	 * 
	 * @param object
	 *            to converted
	 * @return JSON string
	 */
	public static String convertToString(final Object object) {
		try {
			return JSONMapper.toJSON(object).render(false);

		} catch (final MapperException e) {
			e.printStackTrace();
			throw new RuntimeException("Error when convert vo to string ");
		}
	}

	/**
	 * Convert JSON string to object
	 * 
	 * @param message
	 *            JSON string
	 * @param destClass
	 *            object class
	 * @return object
	 */
	public static Object convertToVO(final String message, final Class destClass) {
		try {
			final JSONValue value = new JSONParser(new StringReader(message)).nextValue();
			return JSONMapper.toJava(value, destClass);

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(final String[] args) {
		final SGSPacket testPacket = new SGSPacket(Action.ConnectToHost, "testName", 1234);
		final String s = convertToString(testPacket);
		System.out.println(s);
		final SGSPacket p = (SGSPacket) convertToVO(s, SGSPacket.class);
		System.out.println(p);
	}
}
