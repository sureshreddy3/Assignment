package com.common;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
public class InteractTOJMS {
	
	private static int counter=0;
	private QueueConnection cnn = null;
	private QueueSender sender = null;
	private QueueSession session = null;
	private MessageConsumer messageConsumer = null;
	
	public InteractTOJMS() throws Exception	{

	      InitialContext ctx = new InitialContext();
	      Queue queue = (Queue) ctx.lookup("queue/UnicoQueue");
	      QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
	      cnn = factory.createQueueConnection();
	      session = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
	      sender = session.createSender(queue);
	  	  messageConsumer = session.createConsumer(queue);

	}
	
	public String Push(int i1, int i2) throws Exception
	{
		
		  String result = "falure";
		  Message msg = session.createMessage();
		  counter++;
		  try
		  {
		  msg.setIntProperty("item"+counter, i1);		  
	      sender.send(msg);
		  msg.setIntProperty("item"+counter, i2);		  
	      sender.send(msg);
	      result =  "success";
		  }
		  catch(Exception e)
		  {
			  throw e;
		  }
		  return result;
	      
	}
	
	  public List<Integer> list() throws Exception
	    {
		  List list = null;
	    
		  try{
		  MapMessage reply=(MapMessage)messageConsumer.receive(10 * 1000);
		  Enumeration  mapNames = reply.getMapNames();
		  String       eachName;
		  Object       eachValue;
		  

		  while ( mapNames.hasMoreElements() )
		    { eachName  = mapNames.nextElement().toString();
		      eachValue = reply.getObject(eachName);
		      list = new ArrayList();
		      list.add(Integer.parseInt(eachValue.toString()));
		    }
		  }
		  finally
		  {
			  session.commit();
		  }
		  return list;
		  
	    }
	  
	  public int gcd() throws Exception
	  {
		  MapMessage reply=(MapMessage)messageConsumer.receive(2);
		  
		  
		  Enumeration  mapNames = reply.getMapNames();
		  String       eachName;
		  Object       eachValue;
		  List list = null;

		  while ( mapNames.hasMoreElements() )
		    { eachName  = mapNames.nextElement().toString();
		      eachValue = reply.getObject(eachName);
		      list = new ArrayList();
		      list.add(Integer.parseInt(eachValue.toString()));
		    }
		  
		 return getGcd(list.indexOf(0),list.indexOf(1));

	  }
	  
	  private static int getGcd(int a, int b) {
		    BigInteger b1 = BigInteger.valueOf(a);
		    BigInteger b2 = BigInteger.valueOf(b);
		    BigInteger gcd = b1.gcd(b2);
		    return gcd.intValue();
		}
	  
	  public List<Integer> gcdList()
	  {
		  List list = new ArrayList();
		  //Interact with the d/b to get the list of all computed GCD.
		  return list;
	  }
	  
	  public int gcdSum()
	  {
		  int sum = 0;
		  //Interact with d/b to get the sum of all the computed GCD.
		  return sum;
		  
	  }
}
