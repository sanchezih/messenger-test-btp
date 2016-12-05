package org.koushik.javabrains.messenger.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.model.Comment;
import org.koushik.javabrains.messenger.model.ErrorMessage;
import org.koushik.javabrains.messenger.model.Message;

 
public class CommentDAO {
    
    public void addComment(Comment beanc){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addComment(session,beanc);        
        tx.commit();
        session.close();
        
    }
    
    private void addComment(Session session, Comment beanc){
        Comment comment = new Comment();
        
        comment.setMessage(beanc.getMessage());
        comment.setCreated(beanc.getCreated());
        comment.setAuthor(beanc.getAuthor());
        
        session.save(comment);
    }
    
    public List<Comment> getComment(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Comment");
        List<Comment> comment =  query.list();
        return comment;
    }
 
    public int deleteComment(long id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Comment where id = :id";
        Query query = session.createQuery(hql);
        query.setLong("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateComment(int id, Comment cmt){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update Comment set message=:message, author=:author, created=:created where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            query.setString("message",cmt.getMessage());
            query.setString("author",cmt.getAuthor());
            query.setDate("created", cmt.getCreated());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }
    // agregado del commentservice
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId,long commentId){
		ErrorMessage errorMessage = new ErrorMessage("Not found",404,"http://javabrains.koushik.org");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		
		Message message = messages.get(messageId);
		if(message == null){
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if(comment == null){
			throw new NotFoundException(response);
		}
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(),comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0){
			return null;
		}
		comments.put(comment.getId(),comment);
		return comment;
	}
	
	public Comment removeComment(long messageId,long commentId){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
