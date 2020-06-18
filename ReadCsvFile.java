package ��ҵ;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
public class ReadCsvFile {

	public static void main(String[] args) throws Exception {
		// TODO �Զ����ɵķ������
		String csvFilePath="D:\\javatest.csv";//·��
        readerCsv(csvFilePath);
        //readCsv();
        writeCsv();//ֱ�ӵ��ô˺������ɣ������ļ�·��ΪD:\\javatest.csv   ���ɵ������ļ�·��Ϊ  D:\\javawrite_test.csv
	}
	
	//���ļ�
	public static void readerCsv(String csvFilePath) throws Exception {
	
	CsvReader reader=new CsvReader(csvFilePath,',',Charset.forName("utf-8"));
	reader.readHeaders();	//��ȡ��ͷ
	String[] headers=reader.getHeaders();  //�ѱ�ͷ��������
	List<Object[]> list = new ArrayList<Object[]>();//����list������
	while(reader.readRecord()) {
		 list.add(reader.getValues());
	}
	System.out.println(list.size());
	
	Object[][] datas=new String[list.size()][];
	for(int i=0;i<list.size();i++) {//�����ݴ�������
		datas[i]=list.get(i);
	}
	//�������
	for(int i=0;i<headers.length;i++) {
		 System.out.print(headers[i] + "\t");
	}
	System.out.println("");//����
	
	for(int i=0;i<datas.length;i++) {
		Object[] data=datas[i];
		for(int j=0;j<data.length;j++) {
			Object cell=data[j];
			System.out.print(cell+"\t");
		}
		System.out.println("");//����
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
		ArrayList<String[]> csvList=new ArrayList<String[]>();//�������ݵ�ArrayList
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
			int rownum=csvList.size();//����
			int colnum=csvList.get(0).length; //����
			
			System.out.println("����"+rownum+"��"+colnum+"��");
			
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
		/*��ȡ��Ϣ*/
		ArrayList<String[]> csvList=new ArrayList<String[]>();//�������ݵ�ArrayList
		
		CsvReader reader  = new CsvReader(csvFilePathRead,',',Charset.forName("utf-8"));
		reader.readHeaders();
		String[] headers=reader.getHeaders();
		
		int oldSize=headers.length;//ԭ����������
		String[] newHeaders=new String[oldSize+1];
		
		System.arraycopy(headers, 0, newHeaders, 0,oldSize);
		newHeaders[oldSize]="�¹ڷ���";//����µ�һ��
		for(int i=0;i<oldSize+1;i++) {
			System.out.print(newHeaders[i]+"    ");
		}
		while(reader.readRecord()) {
		csvList.add(reader.getValues());
		}
		/*д����Ϣ*/
		CsvWriter csvWriter = new CsvWriter(csvFilePathWrite,',', Charset.forName("utf-8"));
		int rownum=csvList.size();//����
		int colnum=csvList.get(0).length; //����
		System.out.println("����"+rownum+"��"+colnum+"��");
		csvWriter.writeRecord(newHeaders);
		
		for(int row=0;row<rownum;row++) {//����д��
			String[] cell=csvList.get(row);
			String[] resultStr=insertLabel(cell);//��ӱ�ǩ
			csvWriter.writeRecord(resultStr);
		}
        csvWriter.close();
		
	}
	
	public static String[] insertLabel(String[] arr) {
		int size=arr.length;
		String[] temp=new String[size+1];//���ȼ�һ����������Ͻ��
		System.arraycopy(arr, 0, temp, 0, size);
		//�����ա����ԡ���������ͬʱ�����֣�֢״Ϊ1��ʱ���ж�Ϊ�ڷ��׻��ߡ�
		if(arr[1].equals("1") && arr[0].equals("1")&&arr[5].equals("1")) {
			temp[size]="1";
		}
		else {
			temp[size]="0";
		}
		return temp;		
	}
}

    

    
