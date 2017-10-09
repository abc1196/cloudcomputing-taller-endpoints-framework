package com.cloudcomputing.dimayor.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.cloudcomputing.dimayor.model.Team;
import com.cloudcomputing.dimayor.dao.PersistenceManager;

public class TeamDAO {
	
	private EntityManager em;
	
	public TeamDAO() {
		em = PersistenceManager.getEntityManager();
	}

	public boolean addTeam(Team team) {

		boolean added = true;

		
		em.getTransaction().begin();
		em.persist(team); // cascades the tool & skill relationships
		em.flush();
		em.getTransaction().commit();

		return added;
	}

	public List<Team> getAllTeams() {
		
		
		em.getTransaction().begin();		

		List<Team> teams = null;
		TypedQuery<Team> query = em.createNamedQuery("Team.findAll", Team.class);

		try {
			teams = query.getResultList();
		} catch (NoResultException e) {
			teams = new ArrayList<Team>();
		}

		em.flush();
		em.getTransaction().commit();

		return teams;
	}

	public Team getTeam(Integer id) {
		
		em.getTransaction().begin();

		Team team = null;
		TypedQuery<Team> query = em.createNamedQuery("Team.findById", Team.class);
		query.setParameter("id", id);

		try {
			team = query.getSingleResult();
		} catch (NoResultException e) {
			team = null;
		}

		em.flush();
		em.getTransaction().commit();

		return team;
	}

	public boolean editTeam(Team team) {

		boolean edited = true;
		
		em.getTransaction().begin();
		em.merge(team); // cascades the tool & skill relationships
		em.flush();
		em.getTransaction().commit();

		return edited;
	}
	
	public boolean deleteTeam(Team team) {

		boolean deleted = true;
		

	    em.getTransaction().begin();
		
		em.remove(team); // cascades the tool & skill relationships
		em.flush();
		em.getTransaction().commit();

		return deleted;
	}

}
