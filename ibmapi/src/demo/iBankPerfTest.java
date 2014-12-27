package demo;

import ibankapi.Transaction;
import ibankapi.XLog;
import ibankapi.ibankapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class iBankPerfTest extends Thread
{
	private static final String perfTestFile = "perfcase.txt";
	private static Vector<String> asciiCase;
	private static Vector<Object> ebcdicCase;
	private static Random rand;
	
	private int transNum  = 0;
	private byte [] testCase = null;
	

	
	public static void main(String[] args)
	{
		if (args.length < 2 || args.length > 3)
		{
			System.out.println("Usage: perftest.sh threadNum transNum [loopNum]");
			return;
		}
		
		int threadNum = 100;
		int transNum  = 100;
		int loopNum   = 0;
		
		threadNum = Integer.parseInt(args[0]);
		transNum  = Integer.parseInt(args[1]);
		
		if (args.length == 3)
		{
			loopNum = Integer.parseInt(args[2]);
		}
		
		if (threadNum < 0 || transNum < 0 || loopNum < 0)
		{
			System.out.println("Invalid input parameters");
			return;
		}
		
		ibankapi.Init("12345");
		iBankPerfTest.InitTestCase();
		
		if (iBankPerfTest.GetCaseNumber() == 0)
		{
			System.out.println("Error: no case to test");
			return;
		}
		
		System.out.println("Loop number: " + loopNum + " Thread number: " + threadNum + " Transaction number per thread: " + transNum);
			
		ArrayList<iBankPerfTest> testThreads = new ArrayList<iBankPerfTest>();
		
		boolean forever = loopNum == 0 ? true : false;
		
		int count = 0;
		
		while (forever || loopNum > 0)
		{
			
			long startTime = System.nanoTime();

			for (int i = 0; i < threadNum; ++ i)
			{
				iBankPerfTest tt = new iBankPerfTest(transNum);
				tt.start();
				testThreads.add(tt);
			}
			for (int i = 0; i < testThreads.size(); ++ i)
			{
				try
				{
					testThreads.get(i).join();
				}
				catch (final InterruptedException e)
				{
					System.exit(1);
				}
			}
			
			long endTime = System.nanoTime();
			
			float time = (float)(endTime - startTime)/1000000000;
			
			int totalTransNum = threadNum * transNum;
			
			
			
			float transRatio = totalTransNum/time;
			
			loopNum--;
			count++;
			
			System.out.println("Loop count: " + count + " Total time: " + time + " s" + " Trans Ratio: " + transRatio + " per second");
			
			
		}
		
		//ibankapi.Release();
	}
	
	iBankPerfTest(int transNumber)
	{
		transNum = transNumber;
		testCase = GetTestCase();
	}
	
	public void run()
	{
		runTest();
	}
	
	public void runTest()
	{
		
		long [] time = new long[transNum];
		long startTime, endTime;
		int failedNum = 0;
		boolean bRet;
		
		for (int i = 0; i < transNum; ++i)
		{
			startTime = System.nanoTime();
			bRet = TransTest();
			if (!bRet)
			{
				failedNum++;
				System.out.println("Thread: " + getId() + " New Failed Trasaction, Total Failed: " + failedNum);
			}
			endTime = System.nanoTime();
			time[i] = (endTime - startTime)/1000000;
		}
		
		
		PerfReport(transNum, failedNum, time);
	}
	
	public void PerfReport(int total, int failed, long [] time)
	{
		long avgTime = 0;
		for (int i = 0; i < time.length; ++ i)
		{
			avgTime += time[i];
		}
		avgTime = (avgTime/time.length);
		double failedRatio;
		failedRatio = (double)failed/total;
		System.out.println("Thread: " + getId() + " Failed Ratio: " + failedRatio*100 + "%" + " Avg response time: " + avgTime + " ms");
		
	}
	
	private boolean TransTest()
	{
		return TransTest3();
	}
	
	private boolean TransTest2()
	{
		boolean bRet;
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		Transaction Trans = new Transaction("900001");
		data.put("ACCTOUT",  "1101123456789");
		data.put("ACCTIN",   "1101987654321");
		data.put("TRSAMT",   Double.toString(12345.789));
		
		bRet = Trans.Init();
		
		if (!bRet)
			return bRet;
		
		bRet = Trans.SendMessage(data);
		if (!bRet)
			return bRet;
		/*
		
		String nameIn  = Trans.GetResponseValue("NAMEIN");
		String nameOut = Trans.GetResponseValue("NAMEOUT"); 
		String fee     = Trans.GetResponseValue("TRSFEE");
		// */
		
		Trans.Release();
		
		return true;
	}
	
	private boolean TransTest1()
	{
		
		boolean bRet;
				
		String [] tableNames  = {"OTFDY3"};
		
		for (int i = 0; i < tableNames.length; ++i)
		{
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("FILENAME",  tableNames[i]);
	
			Transaction Trans = new Transaction("900002", tableNames[i]);
			
			bRet = Trans.Init();
			
			if (!bRet)
				return bRet;
			
			bRet = Trans.SendMessage(data);
			if (!bRet)
				return bRet;
			
			//bRet = Trans.SyncData();
			
			/*
			
			String num  = Trans.GetResponseValue("TOTNUM");
			
			// */
						
					
			Trans.Release();
		
		}
		return true;
	}
	

	private boolean TransTest3()
	{
		
		boolean bRet;
			
		Transaction Trans = new Transaction("900001");
		
		bRet = Trans.Init();
		if (!bRet)
			return bRet;
		
		bRet = Trans.SendRawData(testCase);
		if (!bRet)
			return bRet;
		
		Trans.Release();

		return true;
		
	}
	
	private byte [] GetTestCase()
	{
		int index;
		int caseNum = ebcdicCase.size();
		index = rand.nextInt(caseNum);
		return (byte [])ebcdicCase.get(index);
	}
	
	public static void InitTestCase()
	{
		rand = new Random();
		LoadTestCase();
		CovertCase2Ebcdic();
	}
	
	public static int GetCaseNumber()
	{
		if (ebcdicCase == null)
			return 0;
		
		return ebcdicCase.size();
	}
	
    private static void LoadTestCase() 
    {
    	asciiCase = new Vector<String>();
    	
        File file = new File(perfTestFile);
        BufferedReader reader = null;
        try 
        {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) 
            {
            	asciiCase.add(tempString);
            	System.out.println("Test Case: No " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            if (reader != null) 
            {
                try 
                {
                    reader.close();
                } 
                catch (IOException e)
                {
                	System.out.println(e.getMessage());
                }
            }
        }
    }
    
    private static void CovertCase2Ebcdic()
    {
    	if (asciiCase == null || asciiCase.size() == 0)
    		return;
    	ebcdicCase = new Vector<Object>();
    	
    	for (int i = 0; i < asciiCase.size(); ++i)
    	{
    		byte [] testcase = ContructEbcdicCase(asciiCase.get(i));
    		if (testcase != null)
    			ebcdicCase.add(testcase);
    	}
    	
    }
    
    private static byte [] ContructEbcdicCase(String asciiCase)
    {
    	int length = asciiCase.length();
    	
    	if (asciiCase == null || length <= 0|| length > 9999)
    		return null;
    	
		byte [] data      = Transaction.Convert2Ebcdic(asciiCase);
		length = data.length;
	
		String strLength = Integer.toString(length);
		
		if (strLength.length() == 1)
		{
			strLength = "000" + strLength;
		}
		else if (strLength.length() == 2)
		{
			strLength = "00" + strLength;
		}
		else if (strLength.length() == 3)
		{
			strLength = "0" + strLength;
		}
		
		byte [] ebcdicLen = Transaction.Convert2Ebcdic(strLength);

		
		int totalLengh = data.length + ebcdicLen.length;
		
		byte [] rawData = new byte[totalLengh];
		
		System.arraycopy(ebcdicLen, 0, rawData, 0,                ebcdicLen.length);
		System.arraycopy(data,      0, rawData, ebcdicLen.length, length);
		return rawData;
    }


}
