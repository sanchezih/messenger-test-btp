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

import org.koushik.javabrains.messenger.dao.CommentDAO;
import org.koushik.javabrains.messenger.model.Comment;
import org.koushik.javabrains.messenger.model.Profile;
import org.koushik.javabrains.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CommentResource {
	
	private CommentService commentService = new CommentService();
	
	@GET
	public List<Profile> getAllProfiles(@PathParam("messageId") long messageId) {
		CommentDAO dao = new CommentDAO();
		List comments = dao.getAllComments(messageId);
		return comments;
	}
	

	
	@POST 
	public Response addComment (Comment comment){
		CommentDAO dao = new CommentDAO();
		dao.addComment(comment);
		return Response.ok().build();
	}
	
	
	@PUT
	@Path("/{commentId}")
	public Response updateComment(@PathParam("commentId") long commentId, Comment comment){
		CommentDAO dao = new CommentDAO();
		comment.setId(commentId);
		int count = dao.updateComment(commentId, comment);
		if (count==0){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{commentId}")
	public Response deleteComment(@PathParam("commentId") long commentId){
		CommentDAO dao = new CommentDAO();
		int count = dao.deleteComment(commentId);
		if (count==0){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
	
	
	
	
	@GET
	@Path("/{commentId}")
	public Comment getMessage(@PathParam("messageId") long messageId,@PathParam ("commentId") long commentId) {
		return commentService.getComment(messageId, commentId);
	}
	
}

 