package 作业;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
public class ReadCsvFile {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		String csvFilePath="D:\\javatest.csv";//路径
        readerCsv(csvFilePath);
        //readCsv();
        writeCsv();//直接调用此函数即可，数据文件路径为D:\\javatest.csv   生成的数据文件路径为  D:\\javawrite_test.csv
	}
	
	//读文件
	public static void readerCsv(String csvFilePath) throws Exception {
	
	CsvReader reader=new CsvReader(csvFilePath,',',Charset.forName("utf-8"));
	reader.readHeaders();	//读取表头
	String[] headers=reader.getHeaders();  //把表头存入数组
	List<Object[]> list = new ArrayList<Object[]>();//建立list存数据
	while(reader.readRecord()) {
		 list.add(reader.getValues());
	}
	System.out.println(list.size());
	
	Object[][] datas=new String[list.size()][];
	for(int i=0;i<list.size();i++) {//将数据存入数组
		datas[i]=list.get(i);
	}
	//输出数据
	for(int i=0;i<headers.length;i++) {
		 System.out.print(headers[i] + "\t");
	}
	System.out.println("");//换行
	
	for(int i=0;i<datas.length;i++) {
		Object[] data=datas[i];
		for(int j=0;j<data.length;j++) {
			Object cell=data[j];
			System.out.print(cell+"\t");
		}
		System.out.println("");//换行
	}
	}
	
	public static void writeCsv(String csvFilePath,String[][] data) {
		CsvWriter writer=null;//
		try {
			writer =new CsvWriter(csvFilePath,',',Charset.forName("utf-8"));
			for(int i=0;i<data.length;i++) {
				writer.writeRecord(data[i]);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			writer.close();
		}
	}
    
	public static void readCsv() {
		ArrayList<String[]> csvList=new ArrayList<String[]>();//保存数据的ArrayList
		try {
			String csvFilePath="D:\\javatest.csv";
			CsvReader reader  = new CsvReader(csvFilePath,',',Charset.forName("utf-8"));
			reader.readHeaders();
			String[] headers=reader.getHeaders();
			while(reader.readRecord()) {
				csvList.add(reader.getValues());
			}
			//reader.close();
			//_csvData=csvList;
			int rownum=csvList.size();//行数
			int colnum=csvList.get(0).length; //列数
			
			System.out.println("共有"+rownum+"行"+colnum+"列");
			
			for(int row=0;row<rownum;row++) {
				for(int col=0;col<colnum;col++) {
					String cell=csvList.get(row)[col];
					System.out.print(cell+"\t");
				}
				System.out.println();
			}
		}catch (Exception ex) 
        {   
            System.out.println(ex);  //??????? 
      }  	
	}
    
	public static void writeCsv() throws Exception{
		String csvFilePathRead="D:\\javatest.csv";
		String csvFilePathWrite="D:\\javawrite_test.csv";
		/*读取信息*/
		ArrayList<String[]> csvList=new ArrayList<String[]>();//保存数据的ArrayList
		
		CsvReader reader  = new CsvReader(csvFilePathRead,',',Charset.forName("utf-8"));
		reader.readHeaders();
		String[] headers=reader.getHeaders();
		
		int oldSize=headers.length;//原来的特征数
		String[] newHeaders=new String[oldSize+1];
		
		System.arraycopy(headers, 0, newHeaders, 0,oldSize);
		newHeaders[oldSize]="新冠肺炎";//添加新的一列
		for(int i=0;i<oldSize+1;i++) {
			System.out.print(newHeaders[i]+"    ");
		}
		while(reader.readRecord()) {
		csvList.add(reader.getValues());
		}
		/*写入信息*/
		CsvWriter csvWriter = new CsvWriter(csvFilePathWrite,',', Charset.forName("utf-8"));
		int rownum=csvList.size();//行数
		int colnum=csvList.get(0).length; //列数
		System.out.println("共有"+rownum+"行"+colnum+"列");
		csvWriter.writeRecord(newHeaders);
		
		for(int row=0;row<rownum;row++) {//逐行写入
			String[] cell=csvList.get(row);
			String[] resultStr=insertLabel(cell);//添加标签
			csvWriter.writeRecord(resultStr);
		}
        csvWriter.close();
		
	}
	
	public static String[] insertLabel(String[] arr) {
		int size=arr.length;
		String[] temp=new String[size+1];//长度加一，用来放诊断结果
		System.arraycopy(arr, 0, temp, 0, size);
		//当发烧、咳嗽、呼吸困难同时都出现（症状为1）时，判定为冠肺炎患者。
		if(arr[1].equals("1") && arr[0].equals("1")&&arr[5].equals("1")) {
			temp[size]="1";
		}
		else {
			temp[size]="0";
		}
		return temp;		
	}
}

    

    
