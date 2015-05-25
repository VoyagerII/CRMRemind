package com.elearning.web.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * TODO What the class does
 * 
 * @author 邢立庭
 * @date 2014-4-3-上午11:05:40
 * @description 解析Excel并保存Excel数据，返回错误数据
 * 
 */
public class ExcelParseMain {

	private static List<String> EXCEL_SOURCE_FILE_SUFFIX_LIST = new ArrayList<String>();

	private static final String EXCEL_SOURCE_FILE_SUFFIX_XLS = "XLS";

	private static final String EXCEL_SOURCE_FILE_SUFFIX_XLSX = "XLSX";

	static {
		EXCEL_SOURCE_FILE_SUFFIX_LIST.add("XLS");
		EXCEL_SOURCE_FILE_SUFFIX_LIST.add("XLT");
		EXCEL_SOURCE_FILE_SUFFIX_LIST.add("XLSX");
	}

	/**
	 * 空值
	 * 
	 * @param obj
	 * @return
	 */
	public static String getStringValue(Object obj) {

		String value = null;
		if (obj != null && obj.toString() != null && obj.toString().trim() != null
				&& StringUtils.isNotEmpty(obj.toString()) && StringUtils.isNotBlank(obj.toString())) {
			value = obj.toString();
		}
		return value;
	}

	/**
	 * Excel解析（按模板导入数据）
	 * 
	 * @param sourceFilePath
	 * @param paramMap
	 * @param rowDataProcessType
	 * @return
	 */
	public static boolean excelParseByApachePOIAndImportData(String sourceFilePath,
			Map<String, Object> paramMap, String importExcelProcessType) {

		if (StringUtils.isEmpty(sourceFilePath)) {
			System.out.println("The Source File Path Is Null");
			return false;
		} else if (!EXCEL_SOURCE_FILE_SUFFIX_LIST.contains(FilenameUtils.getExtension(
				sourceFilePath).toUpperCase())) {
			System.out.println("This File Suffix Does Not Support");
			return false;
		} else {
			File sourceFile = new File(sourceFilePath);
			if (!(sourceFile.isFile() && sourceFile.exists())) {
				System.out.println("The Source File Does Not Exist");
				return false;
			} else {
				File sourceErrorFile = new File(sourceFilePath.substring(0, sourceFilePath.length()
						- sourceFile.getName().length())
						+ File.separator
						+ sourceFile.getName().substring(
								0,
								sourceFile.getName().length()
										- FilenameUtils.getExtension(sourceFile.getName()).length()
										- 1)
						+ ".ERROR"
						+ "."
						+ FilenameUtils.getExtension(sourceFile.getName()));
				if (FilenameUtils.getExtension(sourceFilePath).toUpperCase()
						.equals(EXCEL_SOURCE_FILE_SUFFIX_XLS.toUpperCase())) {
					//
					HSSFWorkbook errorWorkbook = null;
					//
					HSSFSheet errorSheet = null;
					//
					HSSFWorkbook workbook = WorkbookUtils.openWorkbook(new HSSFWorkbook(),
							sourceFile);
					//
					HSSFSheet sheet = null;
					//
					int i = 0;
					int errorNum = 0;
					while ((sheet = ExcelPOIUtils.getSheet(workbook, i++)) != null) {
						errorWorkbook = WorkbookUtils.newWorkbook(errorWorkbook, true,
								sheet.getSheetName());
						errorSheet = errorWorkbook.getSheetAt(i - 1);
						//
						errorNum += ExcelPOIUtils.importExcel(
								ImportExcelProcessMain.ROW_DATA_PROCESS_MAP
										.get(importExcelProcessType), workbook, sheet,
								errorWorkbook, errorSheet, paramMap);
						//
						if (sheet != null && sheet.getLastRowNum() > 0 && sheet.getRow(0) != null) {
							//
							for (int m = 0; m < sheet.getNumMergedRegions(); m++) {
								errorSheet.addMergedRegion(sheet.getMergedRegion(m));
							}
							//
							for (int c = 0; c < sheet.getRow(0).getPhysicalNumberOfCells(); c++) {
								errorSheet.setColumnWidth(c, sheet.getColumnWidth(c));
							}
						}
					}
					//
					if (errorNum < 0) {
						System.out.println("The Param Initialization Error");
						return false;
					}
					//
					if (errorNum > 0) {
						FileOutputStream fileOutputStream = null;
						try {
							sourceErrorFile.getParentFile().mkdirs();
							fileOutputStream = new FileOutputStream(sourceErrorFile);
							errorWorkbook.write(fileOutputStream);
							return true;
						} catch (Exception e) {
							e.printStackTrace();
							return false;
						} finally {
							IOUtils.closeQuietly(fileOutputStream);
						}
					}
				} else if (FilenameUtils.getExtension(sourceFilePath).toUpperCase()
						.equals(EXCEL_SOURCE_FILE_SUFFIX_XLSX.toUpperCase())) {
					//
					XSSFWorkbook errorWorkbook = null;
					//
					XSSFSheet errorSheet = null;
					//
					XSSFWorkbook workbook = WorkbookUtils.openWorkbook(new XSSFWorkbook(),
							sourceFile);
					//
					XSSFSheet sheet = null;
					//
					int i = 0;
					int errorNum = 0;
					while ((sheet = ExcelPOIUtils.getSheet(workbook, i++)) != null) {
						errorWorkbook = WorkbookUtils.newWorkbook(errorWorkbook, true,
								sheet.getSheetName());
						errorSheet = errorWorkbook.getSheetAt(i - 1);
						//
						errorNum += ExcelPOIUtils.importExcel(
								ImportExcelProcessMain.ROW_DATA_PROCESS_MAP
										.get(importExcelProcessType), workbook, sheet,
								errorWorkbook, errorSheet, paramMap);
						//
						if (sheet != null && sheet.getLastRowNum() > 0 && sheet.getRow(0) != null) {
							//
							for (int m = 0; m < sheet.getNumMergedRegions(); m++) {
								errorSheet.addMergedRegion(sheet.getMergedRegion(m));
							}
							//
							for (int c = 0; c < sheet.getRow(0).getPhysicalNumberOfCells(); c++) {
								errorSheet.setColumnWidth(c, sheet.getColumnWidth(c));
							}
						}
					}
					//
					if (errorNum < 0) {
						System.out.println("The Param Initialization Error");
						return false;
					}
					//
					if (errorNum > 0) {
						FileOutputStream fileOutputStream = null;
						try {
							sourceErrorFile.getParentFile().mkdirs();
							fileOutputStream = new FileOutputStream(sourceErrorFile);
							errorWorkbook.write(fileOutputStream);
							return true;
						} catch (Exception e) {
							e.printStackTrace();
							return false;
						} finally {
							IOUtils.closeQuietly(fileOutputStream);
						}
					}
				}
			}
		}

		return true;
	}

	/**
	 * 解析Excel（按模板导出数据）
	 * 
	 * @param templateFilePath
	 * @param sourceFilePath
	 * @param paramMap
	 * @param rowDataProcessType
	 * @return
	 */
	public static boolean excelParseByApachePOIAndExportData(String templateFilePath,
			String sourceFilePath, Map<String, Object> paramMap) {

		if (StringUtils.isEmpty(templateFilePath)) {
			System.out.println("The Template File Path Is Null");
			return false;
		} else if (StringUtils.isEmpty(sourceFilePath)) {
			System.out.println("The Source File Path Is Null");
			return false;
		} else if (!EXCEL_SOURCE_FILE_SUFFIX_LIST.contains(FilenameUtils.getExtension(
				sourceFilePath).toUpperCase())) {
			System.out.println("This File Suffix Does Not Support");
			return false;
		} else {
			File templateFile = new File(templateFilePath);
			//
			File sourceFile = new File(sourceFilePath);
			//
			if (!(templateFile.isFile() && templateFile.exists())) {
				System.out.println("The Template File Does Not Exist");
				return false;
			} else {
				if (FilenameUtils.getExtension(templateFilePath).toUpperCase()
						.equals(EXCEL_SOURCE_FILE_SUFFIX_XLS.toUpperCase())) {
					//
					HSSFWorkbook templateWorkbook = WorkbookUtils.openWorkbook(new HSSFWorkbook(),
							templateFile);
					//
					HSSFSheet templateSheet = null;
					//
					int i = 0;
					while ((templateSheet = ExcelPOIUtils.getSheet(templateWorkbook, i++)) != null) {
						//
						ExcelPOIUtils.exportExcel(templateWorkbook, templateSheet, paramMap);
					}
					//
					FileOutputStream fileOutputStream = null;
					try {
						sourceFile.getParentFile().mkdirs();
						fileOutputStream = new FileOutputStream(sourceFile);
						templateWorkbook.write(fileOutputStream);
						return true;
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					} finally {
						IOUtils.closeQuietly(fileOutputStream);
					}
				} else if (FilenameUtils.getExtension(templateFilePath).toUpperCase()
						.equals(EXCEL_SOURCE_FILE_SUFFIX_XLSX.toUpperCase())) {

				}
			}
		}

		return true;
	}

	/**
	 * TODO main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		System.out.println(excelParseByApachePOIAndImportData("D:\\1.xls", paramMap, "1"));
	}
}

/**
 * 
 * @author 邢立庭
 * @date 2014-4-3-下午02:36:02
 * @description Excel公共类
 * 
 */
class WorkbookUtils {

	/**
	 * 在Excel中新建Sheet
	 * 
	 * @param createSheet
	 * @return
	 */
	public static HSSFWorkbook newWorkbook(HSSFWorkbook workbook, boolean isCreateSheet,
			String sheetName) {

		if (isCreateSheet && StringUtils.isNotEmpty(sheetName)) {
			if (workbook != null) {
				workbook.createSheet(sheetName);
			} else {
				workbook = new HSSFWorkbook();
				workbook.createSheet(sheetName);
			}
		} else if (isCreateSheet) {
			if (workbook != null) {
				workbook.createSheet();
			} else {
				workbook = new HSSFWorkbook();
				workbook.createSheet();
			}
		} else {
			return null;
		}
		return workbook;
	}

	public static XSSFWorkbook newWorkbook(XSSFWorkbook workbook, boolean isCreateSheet,
			String sheetName) {

		if (isCreateSheet && StringUtils.isNotEmpty(sheetName)) {
			if (workbook != null) {
				workbook.createSheet(sheetName);
			} else {
				workbook = new XSSFWorkbook();
				workbook.createSheet(sheetName);
			}
		} else if (isCreateSheet) {
			if (workbook != null) {
				workbook.createSheet();
			} else {
				workbook = new XSSFWorkbook();
				workbook.createSheet();
			}
		} else {
			return null;
		}
		return workbook;
	}

	/**
	 * 获取Excel数据
	 * 
	 * @param sourceFilePath
	 * @return
	 */
	public static HSSFWorkbook openWorkbook(HSSFWorkbook workbook, String sourceFilePath) {

		InputStream in = null;
		try {
			in = new FileInputStream(sourceFilePath);
			workbook = new HSSFWorkbook(in);
			return workbook;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) {
				IOUtils.closeQuietly(in);
			}
		}
	}

	public static XSSFWorkbook openWorkbook(XSSFWorkbook workbook, String sourceFilePath) {

		InputStream in = null;
		try {
			in = new FileInputStream(sourceFilePath);
			workbook = new XSSFWorkbook(in);
			return workbook;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) {
				IOUtils.closeQuietly(in);
			}
		}
	}

	public static HSSFWorkbook openWorkbook(HSSFWorkbook workbook, File sourceFile) {

		InputStream in = null;
		try {
			in = new FileInputStream(sourceFile);
			workbook = new HSSFWorkbook(in);
			return workbook;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) {
				IOUtils.closeQuietly(in);
			}
		}
	}

	public static XSSFWorkbook openWorkbook(XSSFWorkbook workbook, File sourceFile) {

		InputStream in = null;
		try {
			in = new FileInputStream(sourceFile);
			workbook = new XSSFWorkbook(in);
			return workbook;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) {
				IOUtils.closeQuietly(in);
			}
		}
	}
}

/**
 * 
 * @author 邢立庭
 * @date 2014-4-3-下午03:05:14
 * @description Excel公共类
 * 
 */
class ExcelPOIUtils {

	private static final String CELL_MARK_START = "${";

	private static final String CELL_MARK_END = "}";

	public static Pattern PARAM_PATTERN = Pattern.compile("\\$\\{(\\w+)\\}");

	/**
	 * @description 执行过程
	 * 
	 */
	public static interface ImportExcelProcess {

		public abstract boolean saveDataBefore(Map<String, Object> initParamMap);

		public abstract String saveDataByOneLine(int rowNumber, Map<String, Object> paramMap);
	}

	/**
	 * 获取Excel中Sheet（HSSF格式）
	 * 
	 * @param workbook
	 * @param index
	 * @return
	 */
	public static HSSFSheet getSheet(HSSFWorkbook workbook, int sheetIndex) {

		try {
			return workbook.getSheetAt(sheetIndex);
		} catch (Exception e) {
			return null;
		} finally {}
	}

	/**
	 * 获取Excel中Sheet（XSSF格式）
	 * 
	 * @param workbook
	 * @param sheetIndex
	 * @return
	 */
	public static XSSFSheet getSheet(XSSFWorkbook workbook, int sheetIndex) {

		try {
			return workbook.getSheetAt(sheetIndex);
		} catch (Exception e) {
			return null;
		} finally {}
	}

	/**
	 * 导入Excel（HSSF格式）
	 * 
	 * @param rowDataProcess
	 * @param workbook
	 * @param sheet
	 * @param errorWorkbook
	 * @param errorSheet
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static synchronized int importExcel(ImportExcelProcess importExcelProcess,
			HSSFWorkbook workbook, HSSFSheet sheet, HSSFWorkbook errorWorkbook,
			HSSFSheet errorSheet, Map<String, Object> paramMap) {

		int errorNumber = 0;
		int startRowNumber = 0;
		int endRowNumber = sheet.getLastRowNum();

		//
		int titleRowNumber = Integer.parseInt(paramMap.get("TITLE_ROW_NUMBER").toString()) - 1;
		startRowNumber = titleRowNumber + 1;
		//
		Map<Integer, String> titleMap = new HashMap<Integer, String>();
		if (sheet.getLastRowNum() > 0 && titleRowNumber >= 0
				&& titleRowNumber <= sheet.getLastRowNum()) {
			titleMap.clear();
			HSSFRow titleRow = sheet.getRow(titleRowNumber);
			if (titleRow != null) {
				HSSFRow errorRow = errorSheet.createRow(titleRowNumber);
				copyRow(workbook, sheet.getRow(titleRowNumber), errorWorkbook, errorRow);
				//
				for (Iterator<?> titleCellIterator = titleRow.cellIterator(); titleCellIterator
						.hasNext();) {
					HSSFCell titleCell = (HSSFCell) titleCellIterator.next();
					if (titleCell != null && StringUtils.isNotEmpty(getCellValue(titleCell))) {
						titleMap.put(titleCell.getColumnIndex(),
								StringUtils.trim(getCellValue(titleCell)));
					}
				}
			}
		}
		//
		if (!importExcelProcess.saveDataBefore(paramMap)) {
			return -1;
		}
		//
		copyRow(workbook, sheet.getRow(0), errorWorkbook, errorSheet.createRow(0));
		//
		for (int i = startRowNumber; i <= endRowNumber; i++) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			HSSFRow dataRow = sheet.getRow(i);
			if (dataRow != null && dataRow.getRowNum() > 0) {
				for (Iterator it = titleMap.entrySet().iterator(); it.hasNext();) {
					Map.Entry ME = (Entry) it.next();
					if (ME != null && ME.getKey() != null && ME.getKey().toString() != null
							&& Integer.parseInt(ME.getKey().toString()) >= 0) {
						HSSFCell dataCell = dataRow.getCell(Integer
								.parseInt(ME.getKey().toString()));
						if (dataCell != null
								&& Integer.parseInt(ME.getKey().toString()) < titleMap.size()
								&& StringUtils.isNotBlank(getCellValue(dataCell))
								&& StringUtils.isNotEmpty(getCellValue(dataCell))) {
							dataMap.put(titleMap.get(Integer.parseInt(ME.getKey().toString())),
									getCellValue(dataCell));
						} else {
							dataMap.put(titleMap.get(Integer.parseInt(ME.getKey().toString())),
									null);
						}
					}
				}
				//
				if (paramMap != null && paramMap.size() > 0) {
					dataMap.putAll(paramMap);
				}
				//
				String result = importExcelProcess.saveDataByOneLine(i, dataMap);
				if (StringUtils.isNotBlank(result) && StringUtils.isNotEmpty(result)) {
					HSSFRow errorRow = errorSheet.createRow(i);
					copyRow(workbook, sheet.getRow(i), errorWorkbook, errorRow);
					//
					HSSFCell errorContent = errorRow.createCell(titleMap.size());
					errorContent.setCellType(HSSFCell.CELL_TYPE_STRING);
					errorContent.setCellValue(result);

					errorNumber++;
				}
			}
		}

		return errorNumber;
	}

	/**
	 * 导入Excel（XSSF格式）
	 * 
	 * @param rowDataProcess
	 * @param workbook
	 * @param sheet
	 * @param errorWorkbook
	 * @param errorSheet
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static synchronized int importExcel(ImportExcelProcess importExcelProcess,
			XSSFWorkbook workbook, XSSFSheet sheet, XSSFWorkbook errorWorkbook,
			XSSFSheet errorSheet, Map<String, Object> paramMap) {

		int errorNumber = 0;
		int startRowNumber = 0;
		int endRowNumber = sheet.getLastRowNum();

		//
		int titleRowNumber = Integer.parseInt(paramMap.get("TITLE_ROW_NUMBER").toString()) - 1;
		startRowNumber = titleRowNumber + 1;
		//
		Map<Integer, String> titleMap = new HashMap<Integer, String>();
		if (sheet.getLastRowNum() > 0 && titleRowNumber >= 0
				&& titleRowNumber <= sheet.getLastRowNum()) {
			titleMap.clear();
			XSSFRow titleRow = sheet.getRow(titleRowNumber);
			if (titleRow != null) {
				XSSFRow errorRow = errorSheet.createRow(titleRowNumber);
				copyRow(workbook, sheet.getRow(titleRowNumber), errorWorkbook, errorRow);
				//
				for (Iterator<?> titleCellIterator = titleRow.cellIterator(); titleCellIterator
						.hasNext();) {
					XSSFCell titleCell = (XSSFCell) titleCellIterator.next();
					if (titleCell != null && StringUtils.isNotEmpty(getCellValue(titleCell))) {
						titleMap.put(titleCell.getColumnIndex(),
								StringUtils.trim(getCellValue(titleCell)));
					}
				}
			}
		}
		//
		if (!importExcelProcess.saveDataBefore(paramMap)) {
			return -1;
		}
		//
		copyRow(workbook, sheet.getRow(0), errorWorkbook, errorSheet.createRow(0));
		//
		for (int i = startRowNumber; i <= endRowNumber; i++) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			XSSFRow dataRow = sheet.getRow(i);
			if (dataRow != null && dataRow.getRowNum() > 0) {
				for (Iterator it = titleMap.entrySet().iterator(); it.hasNext();) {
					Map.Entry ME = (Entry) it.next();
					if (ME != null && ME.getKey() != null && ME.getKey().toString() != null
							&& Integer.parseInt(ME.getKey().toString()) >= 0) {
						XSSFCell dataCell = dataRow.getCell(Integer
								.parseInt(ME.getKey().toString()));
						if (dataCell != null
								&& Integer.parseInt(ME.getKey().toString()) < titleMap.size()
								&& StringUtils.isNotBlank(getCellValue(dataCell))
								&& StringUtils.isNotEmpty(getCellValue(dataCell))) {
							dataMap.put(titleMap.get(Integer.parseInt(ME.getKey().toString())),
									getCellValue(dataCell));
						} else {
							dataMap.put(titleMap.get(Integer.parseInt(ME.getKey().toString())),
									null);
						}
					}
				}
				//
				if (paramMap != null && paramMap.size() > 0) {
					dataMap.putAll(paramMap);
				}
				//
				String result = importExcelProcess.saveDataByOneLine(i, dataMap);
				if (StringUtils.isNotBlank(result) && StringUtils.isNotEmpty(result)) {
					XSSFRow errorRow = errorSheet.createRow(i);
					copyRow(workbook, sheet.getRow(i), errorWorkbook, errorRow);
					//
					XSSFCell errorContent = errorRow.createCell(titleMap.size());
					errorContent.setCellType(XSSFCell.CELL_TYPE_STRING);
					errorContent.setCellValue(result);

					errorNumber++;
				}
			}
		}

		return errorNumber;
	}

	/**
	 * 复制列数据至错误表中（HSSF格式）
	 * 
	 * @param workbook
	 * @param row
	 * @param errorWorkbook
	 * @param errorRow
	 */
	public static void copyRow(HSSFWorkbook workbook, HSSFRow row, HSSFWorkbook errorWorkbook,
			HSSFRow errorRow) {

		//
		HSSFCellStyle errorCellStyle = errorWorkbook.createCellStyle();
		HSSFFont errorFont = errorWorkbook.createFont();

		if (row != null) {
			//
			errorRow.setHeight(row.getHeight());
			errorRow.setHeightInPoints(row.getHeightInPoints());
			//
			for (Iterator<?> cellIterator = row.cellIterator(); cellIterator.hasNext();) {
				HSSFCell cell = (HSSFCell) cellIterator.next();
				HSSFCell errorCell = errorRow.createCell(cell.getColumnIndex());
				errorCell.setCellType(cell.getCellType());
				errorCell.setCellValue(getCellValue(cell));
				//
				HSSFCellStyle cellStyle = cell.getCellStyle();
				HSSFFont font = cell.getCellStyle().getFont(workbook);
				//
				errorCellStyle.setAlignment(cellStyle.getAlignment());
				errorCellStyle.setBorderBottom(cellStyle.getBorderBottom());
				errorCellStyle.setBorderLeft(cellStyle.getBorderLeft());
				errorCellStyle.setBorderRight(cellStyle.getBorderRight());
				errorCellStyle.setBorderTop(cellStyle.getBorderTop());
				errorCellStyle.setBottomBorderColor(cellStyle.getBottomBorderColor());
				errorCellStyle.setDataFormat(cellStyle.getDataFormat());
				errorCellStyle.setFillBackgroundColor(cellStyle.getFillBackgroundColor());
				errorCellStyle.setFillForegroundColor(cellStyle.getFillForegroundColor());
				errorCellStyle.setFillPattern(cellStyle.getFillPattern());
				errorCellStyle.setHidden(cellStyle.getHidden());
				errorCellStyle.setIndention(cellStyle.getIndention());
				errorCellStyle.setLeftBorderColor(cellStyle.getLeftBorderColor());
				errorCellStyle.setLocked(cellStyle.getLocked());
				errorCellStyle.setRightBorderColor(cellStyle.getRightBorderColor());
				errorCellStyle.setRotation(cellStyle.getRotation());
				// errorCellStyle.setShrinkToFit(cellStyle.getShrinkToFit());
				errorCellStyle.setTopBorderColor(cellStyle.getTopBorderColor());
				// errorCellStyle.setUserStyleName(cellStyle.getUserStyleName());
				errorCellStyle.setVerticalAlignment(cellStyle.getVerticalAlignment());
				errorCellStyle.setWrapText(cellStyle.getWrapText());

				//
				errorFont.setBoldweight(font.getBoldweight());
				errorFont.setCharSet(font.getCharSet());
				errorFont.setColor(font.getColor());
				errorFont.setFontHeight(font.getFontHeight());
				errorFont.setFontHeightInPoints(font.getFontHeightInPoints());
				errorFont.setFontName(font.getFontName());
				errorFont.setItalic(font.getItalic());
				errorFont.setStrikeout(font.getStrikeout());
				errorFont.setTypeOffset(font.getTypeOffset());
				errorFont.setUnderline(font.getUnderline());
				//
				errorCellStyle.setFont(errorFont);
				errorCell.setCellStyle(errorCellStyle);
			}
		}
	}

	/**
	 * 复制列数据至错误表中（XSSF格式）
	 * 
	 * @param workbook
	 * @param row
	 * @param errorWorkbook
	 * @param errorRow
	 */
	public static void copyRow(XSSFWorkbook workbook, XSSFRow row, XSSFWorkbook errorWorkbook,
			XSSFRow errorRow) {

		//
		XSSFCellStyle errorCellStyle = errorWorkbook.createCellStyle();
		XSSFFont errorFont = errorWorkbook.createFont();

		if (row != null) {
			//
			errorRow.setHeight(row.getHeight());
			errorRow.setHeightInPoints(row.getHeightInPoints());
			//
			for (Iterator<?> cellIterator = row.cellIterator(); cellIterator.hasNext();) {
				XSSFCell cell = (XSSFCell) cellIterator.next();
				XSSFCell errorCell = errorRow.createCell(cell.getColumnIndex());
				errorCell.setCellType(cell.getCellType());
				errorCell.setCellValue(getCellValue(cell));
				//
				XSSFCellStyle cellStyle = cell.getCellStyle();
				XSSFFont font = cell.getCellStyle().getFont();
				//

				errorCellStyle.setAlignment(cellStyle.getAlignment());
				errorCellStyle.setBorderBottom(cellStyle.getBorderBottom());
				errorCellStyle.setBorderLeft(cellStyle.getBorderLeft());
				errorCellStyle.setBorderRight(cellStyle.getBorderRight());
				errorCellStyle.setBorderTop(cellStyle.getBorderTop());
				errorCellStyle.setBottomBorderColor(cellStyle.getBottomBorderColor());
				errorCellStyle.setDataFormat(cellStyle.getDataFormat());
				errorCellStyle.setFillBackgroundColor(cellStyle.getFillBackgroundColor());
				errorCellStyle.setFillForegroundColor(cellStyle.getFillForegroundColor());
				errorCellStyle.setFillPattern(cellStyle.getFillPattern());
				errorCellStyle.setHidden(cellStyle.getHidden());
				errorCellStyle.setIndention(cellStyle.getIndention());
				errorCellStyle.setLeftBorderColor(cellStyle.getLeftBorderColor());
				errorCellStyle.setLocked(cellStyle.getLocked());
				errorCellStyle.setRightBorderColor(cellStyle.getRightBorderColor());
				errorCellStyle.setRotation(cellStyle.getRotation());
				// errorCellStyle.setShrinkToFit(cellStyle.getShrinkToFit());
				errorCellStyle.setTopBorderColor(cellStyle.getTopBorderColor());
				// errorCellStyle.setUserStyleName(cellStyle.getUserStyleName());
				errorCellStyle.setVerticalAlignment(cellStyle.getVerticalAlignment());
				errorCellStyle.setWrapText(cellStyle.getWrapText());

				//
				errorFont.setBoldweight(font.getBoldweight());
				errorFont.setCharSet(font.getCharSet());
				errorFont.setColor(font.getColor());
				errorFont.setFontHeight(font.getFontHeight());
				errorFont.setFontHeightInPoints(font.getFontHeightInPoints());
				errorFont.setFontName(font.getFontName());
				errorFont.setItalic(font.getItalic());
				errorFont.setStrikeout(font.getStrikeout());
				errorFont.setTypeOffset(font.getTypeOffset());
				errorFont.setUnderline(font.getUnderline());
				//
				errorCellStyle.setFont(errorFont);
				errorCell.setCellStyle(errorCellStyle);
			}
		}
	}

	/**
	 * 获取列值（HSSF格式）
	 * 
	 * @param dataCell
	 * @return
	 */
	public static String getCellValue(HSSFCell cell) {

		if (cell != null) {
			switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC:
					return String.valueOf(cell.getNumericCellValue() + "");
				case HSSFCell.CELL_TYPE_STRING:
					return cell.getStringCellValue();
				case HSSFCell.CELL_TYPE_FORMULA:
					return cell.getCellFormula();
				case HSSFCell.CELL_TYPE_BLANK:
					return cell.getStringCellValue();
				case HSSFCell.CELL_TYPE_BOOLEAN:
					return String.valueOf(cell.getBooleanCellValue() + "");
				case HSSFCell.CELL_TYPE_ERROR:
					return String.valueOf(cell.getErrorCellValue() + "");
			}
		}
		return null;
	}

	/**
	 * 获取列值（XSSF格式）
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(XSSFCell cell) {

		if (cell != null) {
			switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC:
					return String.valueOf(cell.getNumericCellValue() + "");
				case HSSFCell.CELL_TYPE_STRING:
					return cell.getStringCellValue();
				case HSSFCell.CELL_TYPE_FORMULA:
					return cell.getCellFormula();
				case HSSFCell.CELL_TYPE_BLANK:
					return cell.getStringCellValue();
				case HSSFCell.CELL_TYPE_BOOLEAN:
					return String.valueOf(cell.getBooleanCellValue() + "");
				case HSSFCell.CELL_TYPE_ERROR:
					return String.valueOf(cell.getErrorCellValue() + "");
			}
		}
		return null;
	}

	/**
	 * 根据模板及数据生成Excel（HSSF格式）
	 * 
	 * @param templateWorkbook
	 * @param templateSheet
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public static HSSFSheet exportExcel(HSSFWorkbook templateWorkbook, HSSFSheet templateSheet,
			Map<String, Object> paramMap) {

		int startRowNumber = 0;
		int endRowNumber = templateSheet.getLastRowNum();
		//
		int titleRowNumber = Integer.parseInt(paramMap.get("TITLE_ROW_NUMBER").toString()) - 1;
		startRowNumber = titleRowNumber + 1;
		//
		templateSheet.getPrintSetup().setLandscape(true);
		//
		HSSFRow templateRow = templateSheet.getRow(startRowNumber);
		//
		String listName = templateSheet.getSheetName();
		HSSFSheet templateListSheet = templateWorkbook.getSheet(listName);
		List<Map<String, Object>> dataMapList = (List<Map<String, Object>>) paramMap.get(listName);

		// Title
		Map<String, Integer> templateTitleMap = new HashMap<String, Integer>();
		if (null != templateRow) {
			for (Iterator<Cell> templateCellIterator = templateRow.cellIterator(); templateCellIterator
					.hasNext();) {
				Cell templateCell = templateCellIterator.next();
				String templateCellValue = templateCell.getStringCellValue();
				//
				if (StringUtils.isNotBlank(templateCellValue)
						&& StringUtils.isNotEmpty(templateCellValue)
						&& templateCellValue.startsWith(CELL_MARK_START)
						&& templateCellValue.endsWith(CELL_MARK_END)) {
					templateCellValue = templateCellValue.substring(CELL_MARK_START.length(),
							templateCellValue.indexOf(CELL_MARK_END));

					if (StringUtils.isNotBlank(templateCellValue)
							&& StringUtils.isNotEmpty(templateCellValue)
							&& templateCell.getColumnIndex() >= 0) {
						templateTitleMap.put(templateCellValue, templateCell.getColumnIndex());
					}
				}
			}
		}

		// Data
		if (null != dataMapList && dataMapList.size() > 0 && null != templateTitleMap
				&& templateTitleMap.size() > 0) {
			templateRow = templateSheet.getRow(startRowNumber);

			//
			int dataRowNumber = startRowNumber;
			for (int j = 0; j < dataMapList.size(); j++) {
				HSSFRow dataRow = templateSheet.createRow(dataRowNumber);
				copyRow(templateWorkbook, templateRow, templateWorkbook, dataRow);
				//
				for (Iterator templateTitleIterator = templateTitleMap.entrySet().iterator(); templateTitleIterator
						.hasNext();) {
					Map.Entry templateTitleME = (Entry) templateTitleIterator.next();

					if (null != templateTitleME
							&& null != templateTitleME.getKey()
							&& null != templateTitleME.getValue()
							&& StringUtils.isNotEmpty(ExcelParseMain.getStringValue(templateTitleME
									.getKey()))
							&& StringUtils.isNotEmpty(ExcelParseMain.getStringValue(templateTitleME
									.getValue()))) {

						String templateTitle = (String) templateTitleME.getKey();
						int templateTitleColumnIndex = (Integer) templateTitleME.getValue();
						//
						HSSFCell dataCell = dataRow.createCell(templateTitleColumnIndex);
						dataCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						//
						if (StringUtils.isNotBlank(templateTitle)
								&& templateTitleColumnIndex >= 0
								&& null != dataMapList.get(j).get(templateTitle)
								&& StringUtils.isNotBlank(ExcelParseMain.getStringValue(dataMapList
										.get(j).get(templateTitle)))) {
							dataCell.setCellValue(ExcelParseMain.getStringValue(dataMapList.get(j)
									.get(templateTitle)));
						} else {
							dataCell.setCellValue("");
						}
					}
				}

				//
				dataRowNumber++;
			}
		}

		return templateSheet;
	}
}
