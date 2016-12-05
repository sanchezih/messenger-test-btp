package org.koushik.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.koushik.javabrains.messenger.dao.MessageDAO;
import org.koushik.javabrains.messenger.dao.ProfileDAO;
import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.model.Profile;
import org.koushik.javabrains.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value ={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})

public class ProfileResource {	
		
		@GET 
		public List<Profile> getProfiles(){
			ProfileDAO dao = new ProfileDAO();
			List profiles = dao.getAllProfiles();
			return profiles;
		}
			
		@GET
		@Path("/{profileName}")
		public Profile getProfile(@PathParam("profileName") String profileName){
			ProfileDAO profileDAO = new ProfileDAO();
			return profileDAO.getProfile(profileName);
		}
				
		@POST
		public Response addProfile(Profile profile){
			/*profile.setFirstName(profile.getFirstName());
			profile.setLastName(profile.getLastName());
			profile.setCreated(profile.getCreated());*/
			
			ProfileDAO prf = new ProfileDAO();
			prf.addProfile(profile);
			return Response.ok().build();
		}
		
		
		@PUT
		@Path("/{profileName}")
		public int updateProfile(@PathParam("profileName")String profileName,Profile profile){
			ProfileDAO profileDAO = new ProfileDAO();
			profile.setProfileName(profileName);
			return profileDAO.updateProfile(profileName, profile);
		}
			
		@DELETE
		@Path("/{profileName}")
		public Response deleteProfile(@PathParam("profileName")String profileName){
			ProfileDAO dao = new ProfileDAO();
			int count = dao.deleteProfile(profileName);
			if(count == 0){
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			return Response.ok().build();
		}

	}
