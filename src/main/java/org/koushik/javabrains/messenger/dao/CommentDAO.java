package org.koushik.javabrains.messenger.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.koushik.javabrains.messenger.model.Comment;

 
public class CommentDAO {
    
    public void addComment(Comment beanc){
    	try {
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addComment(session,beanc);        
        tx.commit();
        session.close();
    } catch (Exception e){
    	
    }
    }
    
    private void addComment(Session session, Comment beanc){
        Comment comment = new Comment();
        
        comment.setMessage(beanc.getMessage());
        comment.setCreated(beanc.getCreated());
        comment.setAuthor(beanc.getAuthor());
        
        session.save(comment);
    }
    
    public List<Comment> getAllComments(long messageId){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Comment where messageId = :messageId");
        query.setLong("messageId",messageId);
        List<Comment> comment =  query.list();
        return comment;
    }
    
/*    __**_**
    _**___**
    _**___**_________****
    _**___**_______**___****
    _**__**_______*___**___**
    __**__*______*__**__***__**
    ___**__*____*__**_____**__*
    ____**_**__**_**________**
    ____**___**__**
    ___*___________*
    __*_____________*
    _*____0_____0____*
    _*_______@_______*
    _*_______________*
    ___*_____U_____*
    _____**_____** 
  */
 
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
    
    public int updateComment(long id, Comment cmt){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update Comment set message=:message where id = :id";
            Query query = session.createQuery(hql);
            query.setString("message",cmt.getMessage());
            query.setLong("id",id);  
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }
}

    // agregado del commentservice
/*	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
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
*/