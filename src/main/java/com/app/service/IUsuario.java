package com.app.service;

import java.util.List;

import com.app.model.Usuario;

public interface IUsuario{
	public List<Usuario>list();
	public Usuario listId(int id);
	public int add(Usuario user);
	public int edit(Usuario user);
	public int remove(int id);
}
