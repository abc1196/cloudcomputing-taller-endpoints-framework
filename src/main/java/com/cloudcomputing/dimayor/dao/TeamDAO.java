package com.cloudcomputing.dimayor.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.cloudcomputing.dimayor.model.Team;

public class TeamDAO {

	public boolean addTeam(Team team) {

		boolean added = true;

		EntityManager em = PersistenceManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(team); // cascades the tool & skill relationships
		em.getTransaction().commit();
		em.close();

		return added;
	}

	public List<Team> getAllTeams() {
		
		EntityManager em = PersistenceManager.getEntityManager();
		em.getTransaction().begin();		

		List<Team> teams = null;
		TypedQuery<Team> query = em.createNamedQuery("Team.findAll", Team.class);

		try {
			teams = query.getResultList();
		} catch (NoResultException e) {
			teams = new ArrayList<Team>();
		}

		em.getTransaction().commit();
		em.close();

		return teams;
	}

	public Team getTeam(Integer id) {
		EntityManager em = PersistenceManager.getEntityManager();
		em.getTransaction().begin();

		Team team = null;
		TypedQuery<Team> query = em.createNamedQuery("Team.findById", Team.class);
		query.setParameter("id", id);

		try {
			team = query.getSingleResult();
		} catch (NoResultException e) {
			team = null;
		}

		em.getTransaction().commit();
		em.close();

		return team;
	}

	public boolean editTeam(Team team) {

		boolean edited = true;

		EntityManager em = PersistenceManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(team); // cascades the tool & skill relationships
		em.getTransaction().commit();
		em.close();

		return edited;
	}
	
	public boolean deleteTeam(Team team) {

		boolean deleted = true;

		EntityManager em = PersistenceManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(team); // cascades the tool & skill relationships
		em.getTransaction().commit();
		em.close();

		return deleted;
	}

}
