package com.example.apcnl.travel.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by xts on 2017/3/9.
 * @描述 操作文件的工具类
 */
public class FileUtils {

	public static final String ROOT_DIR = "Android/data/"
			+ UIUtils.getPackageName();
	public static final String DOWNLOAD_DIR = "/download/";
	public static final String CACHE_DIR = "/EveryWhere";
	public static final String ICON_DIR = "icon";
	/**
	 * 缓存图片文件存放的目录
	 */
	private static final String IMAGE_DOWNLOAD_IMAGES_PATH = "/image/";
	/**
	 * 临时文件,可删除
	 */
	private static final String TEMPORARY = "/temporary/";
	/**
	 * glide缓存图片文件存放的目录
	 */
	private static final String GLIDE_CACHE_IMAGES_PATH = "/GlideCache/";
	private static String VIDEO_PATH = "cache/video-cache";

	/** 判断SD卡是否挂载 */
	public static boolean isSDCardAvailable() {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			return true;
		} else {
			return false;
		}
	}

	/** 获取下载目录 */
	public static String getDownloadDir() {
		return getDir(DOWNLOAD_DIR);
	}

	/** 获取缓存目录 */
	public static String getCacheDir() {
		return getDir(CACHE_DIR);
	}

	/** 获取icon目录 */
	public static String getIconDir() {
		return getDir(ICON_DIR);
	}

	/** 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录 */
	public static String getDir(String name) {
		StringBuilder sb = new StringBuilder();
		if (isSDCardAvailable()) {
			sb.append(getExternalStoragePath());
		} else {
			sb.append(getCachePath());
		}
		sb.append(name);
		sb.append(File.separator);
		String path = sb.toString();
		if (createDirs(path)) {
			return path;
		} else {
			return null;
		}
	}

	/** 获取SD下的应用目录 */
	public static String getExternalStoragePath() {
		StringBuilder sb = new StringBuilder();
		sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
		sb.append(File.separator);
		sb.append(ROOT_DIR);
		sb.append(File.separator);
		return sb.toString();
	}

	/** 获取应用的cache目录 */
	public static String getCachePath() {
		File f = UIUtils.getContext().getCacheDir();
		if (null == f) {
			return null;
		} else {
			return f.getAbsolutePath() + "/";
		}
	}

	/** 创建文件夹 */
	public static boolean createDirs(String dirPath) {
		File file = new File(dirPath);
		if (!file.exists() || !file.isDirectory()) {
			return file.mkdirs();
		}
		return true;
	}

	/** 复制文件，可以选择是否删除源文件 */
	public static boolean copyFile(String srcPath, String destPath,
			boolean deleteSrc) {
		File srcFile = new File(srcPath);
		File destFile = new File(destPath);
		return copyFile(srcFile, destFile, deleteSrc);
	}

	/** 复制文件，可以选择是否删除源文件 */
	public static boolean copyFile(File srcFile, File destFile,
			boolean deleteSrc) {
		if (!srcFile.exists() || !srcFile.isFile()) {
			return false;
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = in.read(buffer)) > 0) {
				out.write(buffer, 0, i);
				out.flush();
			}
			if (deleteSrc) {
				srcFile.delete();
			}
			ToastUtil.showShort("下载成功");
		} catch (Exception e) {
			Logger.print(e.toString());
			ToastUtil.showShort("下载失败");
			return false;
		} finally {
			IOUtils.close(out);
			IOUtils.close(in);
		}
		return true;
	}

	/** 判断文件是否可写 */
	public static boolean isWriteable(String path) {
		try {
			if (TextUtils.isEmpty(path)) {
				return false;
			}
			File f = new File(path);
			return f.exists() && f.canWrite();
		} catch (Exception e) {
			Logger.print(e.toString());
			return false;
		}
	}

	/** 修改文件的权限,例如"777"等 */
	public static void chmod(String path, String mode) {
		try {
			String command = "chmod " + mode + " " + path;
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(command);
		} catch (Exception e) {
			Logger.print(e.toString());
		}
	}

	/**
	 * 把键值对写入文件
	 * 
	 * @param filePath 文件路径
	 * @param key 键
	 * @param value 值
	 * @param comment 该键值对的注释
	 */
	public static void writeProperties(String filePath, String key,
			String value, String comment) {
		if (TextUtils.isEmpty(key) || TextUtils.isEmpty(filePath)) {
			return;
		}
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File f = new File(filePath);
		try {
			if (!f.exists() || !f.isFile()) {
				f.createNewFile();
			}
			fis = new FileInputStream(f);
			Properties p = new Properties();
			p.load(fis);// 先读取文件，再把键值对追加到后面
			p.setProperty(key, value);
			fos = new FileOutputStream(f);
			p.store(fos, comment);
		} catch (Exception e) {
			Logger.print(e.toString());
		} finally {
			IOUtils.close(fis);
			IOUtils.close(fos);
		}
	}

	/** 根据值读取 */
	public static String readProperties(String filePath, String key,
			String defaultValue) {
		if (TextUtils.isEmpty(key) || TextUtils.isEmpty(filePath)) {
			return null;
		}
		String value = null;
		FileInputStream fis = null;
		File f = new File(filePath);
		try {
			if (!f.exists() || !f.isFile()) {
				f.createNewFile();
			}
			fis = new FileInputStream(f);
			Properties p = new Properties();
			p.load(fis);
			value = p.getProperty(key, defaultValue);
		} catch (IOException e) {
			Logger.print(e.toString());
		} finally {
			IOUtils.close(fis);
		}
		return value;
	}

	/** 把字符串键值对的map写入文件 */
	public static void writeMap(String filePath, Map<String, String> map,
			boolean append, String comment) {
		if (map == null || map.size() == 0 || TextUtils.isEmpty(filePath)) {
			return;
		}
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File f = new File(filePath);
		try {
			if (!f.exists() || !f.isFile()) {
				f.createNewFile();
			}
			Properties p = new Properties();
			if (append) {
				fis = new FileInputStream(f);
				p.load(fis);// 先读取文件，再把键值对追加到后面
			}
			p.putAll(map);
			fos = new FileOutputStream(f);
			p.store(fos, comment);
		} catch (Exception e) {
			Logger.print(e.toString());
		} finally {
			IOUtils.close(fis);
			IOUtils.close(fos);
		}
	}

	/** 把字符串键值对的文件读入map */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, String> readMap(String filePath,
			String defaultValue) {
		if (TextUtils.isEmpty(filePath)) {
			return null;
		}
		Map<String, String> map = null;
		FileInputStream fis = null;
		File f = new File(filePath);
		try {
			if (!f.exists() || !f.isFile()) {
				f.createNewFile();
			}
			fis = new FileInputStream(f);
			Properties p = new Properties();
			p.load(fis);
			map = new HashMap<String, String>((Map) p);// 因为properties继承了map，所以直接通过p来构造一个map
		} catch (Exception e) {
			Logger.print(e.toString());
		} finally {
			IOUtils.close(fis);
		}
		return map;
	}

	/** 改名 */
	public static boolean copy(String src, String des, boolean delete) {
		File file = new File(src);
		if (!file.exists()) {
			return false;
		}
		File desFile = new File(des);
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(file);
			out = new FileOutputStream(desFile);
			byte[] buffer = new byte[1024];
			int count = -1;
			while ((count = in.read(buffer)) != -1) {
				out.write(buffer, 0, count);
				out.flush();
			}
		} catch (Exception e) {
			Logger.print(e.toString());
			return false;
		} finally {
			IOUtils.close(in);
			IOUtils.close(out);
		}
		if (delete) {
			file.delete();
		}
		return true;
	}




	/**
	 * 缓存图片文件存放的目录
	 */
	private static final String IMAGE_CACHE_IMAGES_PATH = "/everywhere_image";

	/**
	 * 获取缓存图片文件存放的目录
	 *
	 * @param context Context
	 * @return SDCard卡或者手机内存的根路径
	 */
	public static String getImageCacheDir(Context context) {
		return getRootDir(context) + IMAGE_CACHE_IMAGES_PATH;
	}

	/**
	 * 获取缓存图片文件存放的目录
	 *
	 * @param context Context
	 * @return SDCard卡或者手机内存的根路径
	 */
	public static String getImageDownloadDir(Context context) {
		return getRootDir(context) +CACHE_DIR+ IMAGE_DOWNLOAD_IMAGES_PATH;
	}
	/**
	 * 获取glide缓存图片文件存放的目录
	 *
	 * @param context Context
	 * @return SDCard卡或者手机内存的根路径
	 */
	public static String getGlideCachePath(Context context) {
		return getRootDir(context) +CACHE_DIR+ GLIDE_CACHE_IMAGES_PATH;
	}
	/**
	 * 获取临时文件路径
	 *
	 * @param context Context
	 * @return SDCard卡或者手机内存的根路径
	 */
	public static String getTemporaryPath(Context context) {
		String s = getRootDir(context) + CACHE_DIR + TEMPORARY;
        File file = new File(s);
        if (!file.exists()){
            file.mkdirs();
        }
        return s;
	}

	/**
	 * 获取SDCard卡或者手机内存的根路径（优先获取SDCard卡的根路径）
	 *
	 * @param context Context
	 * @return SDCard卡或者手机内存的根路径
	 */
	public static String getRootDir(Context context) {
		String rootDir = null;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			rootDir = Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			rootDir = context.getApplicationContext().getCacheDir().getAbsolutePath();
		}
		return rootDir;
	}

	/**
	 * 随机生成一个文件，用于存放下载的图片
	 * @param context Context
	 * @return
	 */
	public static String getImageDownloadPath(Context context) {
		String imageRootDir = getImageDownloadDir(context);
		File dir = new File(imageRootDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String fileName = UUID.randomUUID().toString() + ".jpg";
		return dir + File.separator + fileName;
	}
	/**
	 * 随机生成一个文件，用于存放下载的图片
	 * @param context Context
	 * @param url
	 * @return
	 */
	public static String getImageDownloadPath(Context context, String url) {
		String imageRootDir = getImageDownloadDir(context);
		File dir = new File(imageRootDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String fileName = getImageName(url);
		return dir + File.separator + fileName;
	}

	/**
	 * 随机生成一个文件，用于存放下载的文件
	 * @param context Context
	 * @param url
	 * @return
	 */
	public static String getDownloadPath(Context context, String url) {
		String fileRootDir = getRootDir(context) +CACHE_DIR+ DOWNLOAD_DIR;;
		File dir = new File(fileRootDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
        String fileName = "";
        String[] split = url.split("\\.");
        if (split != null && split.length>=2){
            fileName= Tools.encrypt(url) +"."+ split[split.length-1];
        }else {
            fileName= Tools.encrypt(url);
        }

		return dir + File.separator + fileName;
	}

	public static String getImageName(String url){
		return Tools.encrypt(url) + ".jpg";
	}

	/**
	 * 把数据写入文件
	 *
	 * @param is
	 *            数据流
	 * @param path
	 *            文件路径
	 * @param recreate
	 *            如果文件存在，是否需要删除重建
	 * @return 是否写入成功
	 */
	public static boolean writeFile(InputStream is, String path,
									boolean recreate) {
		boolean res = false;
		File f = new File(path);
		FileOutputStream fos = null;
		try {
			if (recreate && f.exists()) {
				f.delete();
			}
			if (!f.exists() && null != is) {
				File parentFile = new File(f.getParent());
				parentFile.mkdirs();
				int count = -1;
				byte[] buffer = new byte[1024];
				fos = new FileOutputStream(f);
				while ((count = is.read(buffer)) != -1) {
					fos.write(buffer, 0, count);
				}
				res = true;
			}
		} catch (Exception e) {
			Logger.print(e.toString());
		} finally {
			IOUtils.close(fos);
			IOUtils.close(is);
		}
		return res;
	}

	/**
	 * 把字符串数据写入文件
	 *
	 * @param content
	 *            需要写入的字符串
	 * @param path
	 *            文件路径名称
	 * @param append
	 *            是否以添加的模式写入
	 * @return 是否写入成功
	 */
	public static boolean writeFile(byte[] content, String path, boolean append) {
		boolean res = false;
		File f = new File(path);
		RandomAccessFile raf = null;
		try {
			if (f.exists()) {
				if (!append) {
					f.delete();
					f.createNewFile();
				}
			} else {
				f.createNewFile();
			}
			if (f.canWrite()) {
				raf = new RandomAccessFile(f, "rw");
				raf.seek(raf.length());
				raf.write(content);
				res = true;
			}
		} catch (Exception e) {
			Logger.print(e.toString());
		} finally {
			IOUtils.close(raf);
		}
		return res;
	}

	/**
	 * 把字符串数据写入文件
	 *
	 * @param content
	 *            需要写入的字符串
	 * @param path
	 *            文件路径名称
	 * @param append
	 *            是否以添加的模式写入
	 * @return 是否写入成功
	 */
	public static boolean writeFile(String content, String path, boolean append) {
		return writeFile(content.getBytes(), path, append);
	}

	public static void deleteVideoCache(){
		deleteFolder(new File(getExternalStoragePath()+VIDEO_PATH));
		//deleteFolderFile(rootDir,true);
	}

	/**
	 * 递归删除文件夹
	 * @param file
     */
	public  static void deleteFolder(File file) {
		if (!file.exists())
			return;

		if (file.isDirectory()) {
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFolder(files[i]);
			}
		}
		file.delete();
	}

	public static String getVideoSize(){
		return getFormatSize(getFolderSize(new File(getExternalStoragePath()+VIDEO_PATH)));
	}

	/**
	 * 获取文件夹大小
	 * @param file File实例
	 * @return long
	 */
	public static long getFolderSize(File file){

		long size = 0;
		try {
			if (file != null && file.exists()){
				File[] fileList = file.listFiles();
				for (int i = 0; i < fileList.length; i++)
				{
					if (fileList[i].isDirectory())
					{
						size = size + getFolderSize(fileList[i]);

					}else{
						size = size + fileList[i].length();

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return size/1048576;
		return size;
	}

	/**
	 * 格式化单位
	 * @param size
	 * @return
	 */
	public static String getFormatSize(double size) {
		double kiloByte = size/1024;
		if(kiloByte < 1) {
			return size + "B";
		}

		double megaByte = kiloByte/1024;
		if(megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
		}

		double gigaByte = megaByte/1024;
		if(gigaByte < 1) {
			BigDecimal result2  = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
		}

		double teraBytes = gigaByte/1024;
		if(teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
	}
}
