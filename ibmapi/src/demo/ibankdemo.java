package demo;

import java.util.HashMap;
import ibankapi.Transaction;
import ibankapi.XLog;
import ibankapi.ibankapi;

public class ibankdemo 
{
	public static void main(String[] args)
	{
		ibankapi.Init("12345");
		
		boolean bRet;
				
		String [] tableNames  = {"DBFDZ0", "OTFDY1", "OTFDY2", "OTFDY3"};
		
		for (int i = 0; i < tableNames.length; ++i)
		{
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("FILENAME",  tableNames[i]);
			
			
			
			Transaction Trans = new Transaction("900002", tableNames[i]);
			
			bRet = Trans.Init();
			
			if (!bRet)
				return;
			
			bRet = Trans.SendMessage(data);
			if (!bRet)
				return;
			
			bRet = Trans.SyncData();
			
			String num  = Trans.GetResponseValue("TOTNUM");
						
			XLog.finest("TOTNUM: " + num);
			
				
			Trans.Release();
		
		}
		
		Transaction.UpdateTransFmt();
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		Transaction Trans = new Transaction("900001");
		data.put("ACCTOUT",  "1101123456789");
		data.put("ACCTIN",   "1101987654321");
		data.put("TRSAMT",   Double.toString(12345.789));
		
		bRet = Trans.Init();
		
		if (!bRet)
			return;
		
		bRet = Trans.SendMessage(data);
		if (!bRet)
			return;
		
		String nameIn  = Trans.GetResponseValue("NAMEIN");
		String nameOut = Trans.GetResponseValue("NAMEOUT"); 
		String fee     = Trans.GetResponseValue("TRSFEE");
		
		XLog.finest("NAMEIN: " + nameIn + " NAMEOUT: " + nameOut + " TRSFEE: " + fee);
		
		/*
		ArrayList< HashMap<String, String> > msg = Trans.GetMessage();
		
		
		for (int i = 0; i < msg.size(); ++i)
		{
			Iterator<Entry<String, String>> iter = msg.get(i).entrySet().iterator();
			while (iter.hasNext()) 
			{
				Map.Entry entry = (Map.Entry) iter.next();
				String key = entry.getKey().toString();
			    String val = entry.getValue().toString();
	
				XLog.finest("Packet data: " + key + " = [" + val + "]");
			}
		}
		// */
			
		Trans.Release();
		

		ibankapi.Release();
		
	}

}
