package com.soap;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.common.InteractTOJMS;

@Stateless
@WebService(serviceName = "GCDService", targetNamespace = "urn:GCDService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)

public class SoapService {

	private InteractTOJMS jms = null;	

	public SoapService()
	{
		
	}
	public int gcd() throws Exception
	{
		jms =new InteractTOJMS();
		return jms.gcd();
	}
	public List<Integer> gcdList() throws Exception
	{
		jms =new InteractTOJMS();
		return jms.gcdList();
	}
	public int gcdSum()throws Exception
	{
		jms =new InteractTOJMS();
		return jms.gcdSum();
	}
	

}
