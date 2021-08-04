package com.poortoys.examples;

import java.util.Properties;

import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import net.osgg.ConverterBeanRemote;

/**
 * Sample JAX-RS resources.
 *
 */
@Path("conv")
@RequestScoped
public class ConverterResource{
    
	//@EJB HelloBeanRemote bean;
	final Properties jndiProperties = new Properties();
	float f=0;
	
    @GET
    @Produces("application/json")
    @Path("/{celsius}")
    public Result getConv(@PathParam("celsius") Integer c ){
    	// create the context
    	try {
			final Context context = new InitialContext(jndiProperties);
			ConverterBeanRemote conv = (ConverterBeanRemote) context.lookup("ejb:/pryEJBConv/ConverterBean!net.osgg.ConverterBeanRemote");
			f = conv.convertToFahrenheit(c);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
        return new Result(String.valueOf(f));        
    }
    
}
