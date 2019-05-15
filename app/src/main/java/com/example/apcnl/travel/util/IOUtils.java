package com.example.apcnl.travel.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by xts on 2017/3/9.
 * @描述 IO操作的工具类
 */

public class IOUtils {

	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				Logger.print(e.toString());
			}
		}
		return true;
	}
}
