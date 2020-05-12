package com.pig.product.util;

import java.util.Random;

/**
 * @category 生成token值
 */
public class Token {

	private static String baseNumLetter = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghjklmnopqrstuvwxyz";

	public static String getToken() {

		int random = new Random().nextInt(52);
		String randomStr = baseNumLetter.substring(random, random + 9) + System.currentTimeMillis();
		String token = MD5Util.getMD5(randomStr);

		return token;
	}
}
