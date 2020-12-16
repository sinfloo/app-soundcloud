package com.app.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.model.Usuario;
import com.app.utils.HibernateUtil;

public class UsuarioService implements IUsuario {

	Transaction transaction = null;
	
	@Override
	public List<Usuario> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario listId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Usuario user) {
		int r = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            r=(Integer)session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Errror:"+e.getMessage());
        }

		return r;
	}

	@Override
	public int edit(Usuario user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
