package com.elearning.web.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * TODO What the class does
 * 
 * @author 邢立庭
 * @date 2014-2-13-下午04:09:44
 * @description 文件移动
 * 
 */
public class FileMainUtil {

	/**
	 * 复制文件但不更改文件名
	 * 
	 * @param sourceFilePath
	 * @param targetDirectoryPath
	 * @param isCoverageFileTargetDirectoryExist
	 * @return
	 */
	public static boolean copyFile(String sourceFilePath,
			String targetDirectoryPath,
			boolean isCoverageFileTargetDirectoryExist) {

		if (StringUtils.isEmpty(sourceFilePath)) {
			System.out.println("The Source File Path Is Null");
			return false;
		} else if (StringUtils.isEmpty(targetDirectoryPath)) {
			System.out.println("The Target Directory Path Is Null");
			return false;
		} else {
			File sourceFile = new File(sourceFilePath);
			if (!(sourceFile.isFile() && sourceFile.exists())) {
				System.out.println("The Source File Does Not Exist");
				return false;
			} else {
				File targetDirectory = new File(targetDirectoryPath);
				File targetFile = new File(targetDirectoryPath + File.separator
						+ FilenameUtils.getName(sourceFilePath));
				if ((targetFile.exists() && isCoverageFileTargetDirectoryExist)
						|| !targetFile.exists()) {
					try {
						targetFile.delete();
						targetDirectory.mkdirs();
						FileUtils.copyFileToDirectory(sourceFile,
								targetDirectory);
						return true;
					} catch (IOException e) {
						System.out.println("The Source File Copy Failure");
						return false;
					} finally {
					}
				} else {
					System.out.println("The Target Directory File Is Exist");
					return false;
				}
			}
		}
	}

	/**
	 * 复制文件并更改文件名
	 * 
	 * @param sourceFilePath
	 * @param targetFilePath
	 * @param isChangeFileSuffix
	 * @param isCoverageFileTargetDirectoryExist
	 * @return
	 */
	public static boolean copyFileToRename(String sourceFilePath,
			String targetFilePath, boolean isChangeFileSuffix,
			boolean isCoverageFileTargetDirectoryExist) {

		if (StringUtils.isEmpty(sourceFilePath)) {
			System.out.println("The Source File Path Is Null");
			return false;
		} else if (StringUtils.isEmpty(targetFilePath)) {
			System.out.println("The Target File Path Is Null");
			return false;
		} else {
			File sourceFile = new File(sourceFilePath);
			if (!(sourceFile.isFile() && sourceFile.exists())) {
				System.out.println("The Source File Does Not Exist");
				return false;
			} else {
				File targetDirectory = new File(targetFilePath.substring(
						0,
						targetFilePath.length()
								- FilenameUtils.getName(targetFilePath)
										.length()));
				File targetOldFile = new File(targetDirectory.getAbsolutePath()
						+ File.separator
						+ FilenameUtils.getName(sourceFilePath));

				// 是否更改文件后缀
				String targetNewFileName = targetDirectory
						+ File.separator
						+ FilenameUtils.getName(targetFilePath).substring(
								0,
								FilenameUtils.getName(targetFilePath).length()
										- FilenameUtils.getExtension(
												targetFilePath).length());
				if (targetNewFileName.substring(targetNewFileName.length() - 1,
						targetNewFileName.length()).equals(".")) {
					targetNewFileName = targetNewFileName.substring(0,
							targetNewFileName.length() - 1);
				}
				if (isChangeFileSuffix) {
					if (FilenameUtils.getExtension(sourceFilePath) != null) {
						targetNewFileName = targetNewFileName + "."
								+ FilenameUtils.getExtension(targetFilePath);
					}
				} else {
					if (FilenameUtils.getExtension(sourceFilePath) != null) {
						targetNewFileName = targetNewFileName + "."
								+ FilenameUtils.getExtension(sourceFilePath);
					}
				}
				if (targetNewFileName.substring(targetNewFileName.length() - 1,
						targetNewFileName.length()).equals(".")) {
					targetNewFileName = targetNewFileName.substring(0,
							targetNewFileName.length() - 1);
				}
				File targetNewFile = new File(targetNewFileName);
				if ((targetNewFile.exists() && isCoverageFileTargetDirectoryExist)
						|| !targetNewFile.exists()) {
					try {
						File targetFileTmpDirectory = new File(
								targetNewFile.getPath() + ".tmp"
										+ File.separator);
						File targetFileTmpOldFile = new File(
								targetNewFile.getPath() + ".tmp"
										+ File.separator
										+ targetOldFile.getName());
						File targetFileTmpNewFile = new File(
								targetNewFile.getPath() + ".tmp"
										+ File.separator
										+ targetNewFile.getName());

						targetFileTmpDirectory.mkdirs();
						FileUtils.copyFileToDirectory(sourceFile,
								targetFileTmpDirectory);
						targetFileTmpOldFile.renameTo(targetFileTmpNewFile);

						targetNewFile.delete();
						targetDirectory.mkdirs();
						FileUtils.copyFileToDirectory(targetFileTmpNewFile,
								targetDirectory);

						targetFileTmpNewFile.delete();
						targetFileTmpDirectory.delete();
						return true;
					} catch (IOException e) {
						System.out.println("The Source File Copy Failure");
						return false;
					} finally {
					}
				} else {
					System.out.println("The Target Directory File Is Exist");
					return false;
				}
			}
		}
	}

	/**
	 * 移动文件但不更改文件名
	 * 
	 * @param sourceFilePath
	 * @param targetDirectoryPath
	 * @param isCoverageFileTargetDirectoryExist
	 * @return
	 */
	public static boolean moveFile(String sourceFilePath,
			String targetDirectoryPath,
			boolean isCoverageFileTargetDirectoryExist) {

		if (StringUtils.isEmpty(sourceFilePath)) {
			System.out.println("The Source File Path Is Null");
			return false;
		} else if (StringUtils.isEmpty(targetDirectoryPath)) {
			System.out.println("The Target Directory Path Is Null");
			return false;
		} else {
			File sourceFile = new File(sourceFilePath);
			if (!(sourceFile.isFile() && sourceFile.exists())) {
				System.out.println("The Source File Does Not Exist");
				return false;
			} else {
				File targetDirectory = new File(targetDirectoryPath);
				File targetFile = new File(targetDirectoryPath + File.separator
						+ FilenameUtils.getName(sourceFilePath));
				if ((targetFile.exists() && isCoverageFileTargetDirectoryExist)
						|| !targetFile.exists()) {
					try {
						targetFile.delete();
						targetDirectory.mkdirs();
						FileUtils.moveFileToDirectory(sourceFile,
								targetDirectory, false);
						return true;
					} catch (IOException e) {
						System.out.println("The Source File Copy Failure");
						return false;
					} finally {
					}
				} else {
					System.out.println("The Target Directory File Is Exist");
					return false;
				}
			}
		}
	}

	/**
	 * 压缩目录
	 * 
	 * @param sourceDirectoryPath
	 * @param targetFilePath
	 * @param isCoverageFileTargetDirectoryExist
	 * @return
	 */
	public static boolean zipFile(String sourceDirectoryPath,
			String targetFilePath, boolean isCoverageFileTargetDirectoryExist) {

		return false;
	}
}
