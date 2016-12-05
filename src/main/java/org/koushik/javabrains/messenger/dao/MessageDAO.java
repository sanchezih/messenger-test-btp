package org.koushik.javabrains.messenger.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.koushik.javabrains.messenger.exception.DataNotFoundException;
import org.koushik.javabrains.messenger.model.Message;

public class MessageDAO {
	public void addMessage(Message beanm) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		addMessage(session, beanm);
		tx.commit();
		session.close();
	}

	private void addMessage(Session session, Message beanm) {
		Message message = new Message();
		message.setMessage(beanm.getMessage());
		message.setCreated(beanm.getCreated());
		message.setAuthor(beanm.getAuthor());
		session.save(message);
	}

	public Message getMessage(long id) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Message where id = :id");
		Message message = (Message) query.uniqueResult();
		if (message == null) {
			throw new DataNotFoundException("El mensaje " + id + " no ha sido encontrado.");
		}
		return message;
	}

	public List<Message> getAllMessages() {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Message");
		List<Message> messages = query.list();
		return messages;
	}

	public int deleteMessage(long id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Message where id = :id";
		Query query = session.createQuery(hql);
		query.setLong("id", id);
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}

	public int updateMessage(long id, Message msj) {
		if (id <= 0)
			return 0;
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "update Message set message = :message, author= :author, created = :created where id = :id";
		Query query = session.createQuery(hql);
		query.setLong("id", id);
		query.setString("message", msj.getMessage());
		query.setDate("created", msj.getCreated());
		query.setString("author", msj.getAuthor());
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}
}