package com.rest;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.common.InteractTOJMS;

@Path("/gcdcalculate")
@Stateless
@LocalBean
public class RestService {
	
		private InteractTOJMS jms = null;	
		
		
		
	    @GET
	    @Path("/list")
	    @Produces("application/json")
	    public List<Integer> list() throws Exception 
	    {
	    	jms =new InteractTOJMS();
	    	return jms.list(); 
	    }
	    
	    @POST
	    public String push(int i1,int i2) throws Exception    
	    {
	    	jms =new InteractTOJMS();
	    	return jms.Push(i1, i2);
	    }
}
