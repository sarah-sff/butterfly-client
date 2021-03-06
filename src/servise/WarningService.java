package servise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import staticVal.WarningVals;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:43:33 
 * @version V1.0.0
 */
public class WarningService {

	/**
	 * 获取当前报警信息
	 * 
	 * @return
	 */
	public static List<String> readCurrentWarnings() {
		List<String> warningList = new ArrayList<String>();
		for (Integer key : WarningVals.warningDataMap.keySet()) {

			warningList.add(WarningVals.warningDataMap.get(key).toString());

		}

		return warningList;
	}

	/**
	 * 获取历史报警信息
	 * 
	 * @return
	 */
	public static List<String> readHistoryWarnings() {
		List<String> warningList = new ArrayList<String>();
		String path = "logs/warning/";
		File file = new File(path);
		if (file != null) {
			File[] tempList = file.listFiles();
			if (tempList != null) {
				for (int i = tempList.length - 1; i >= 0; i--) {
					if (tempList[i].isFile()) {
						readFile(tempList[i], warningList);
					}
				}
			}
		}
		return warningList;
	}

	public static void readFile(File fileName, List<String> list) {

		FileReader reader;
		try {
			reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);

			String str = null;

			while ((str = br.readLine()) != null) {
				list.add(str);
			}
			list.add("--------------------------");
			br.close();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
