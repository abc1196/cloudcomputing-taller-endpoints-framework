package com.cloudcomputing.dimayor.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.cloudcomputing.dimayor.model.Team;

public class TeamDAO {

	public boolean addTeam(Team team) {

		boolean added = true;

		Session session = PersistenceManager.openSession();
		session.getTransaction().begin();
		session.persist(team); // cascades the tool & skill relationships
		session.getTransaction().commit();
		session.close();

		return added;
	}

	public List<Team> getAllTeams() {
		Session session = PersistenceManager.openSession();
		session.getTransaction().begin();

		List<Team> teams = null;
		TypedQuery<Team> query = session.createNamedQuery("Team.findAll", Team.class);

		try {
			teams = query.getResultList();
		} catch (NoResultException e) {
			teams = new ArrayList<Team>();
		}

		session.getTransaction().commit();
		session.close();

		return teams;
	}

	public Team getTeam(Integer id) {
		Session session = PersistenceManager.openSession();
		session.getTransaction().begin();

		Team team = null;
		TypedQuery<Team> query = session.createNamedQuery("Team.findById", Team.class);
		query.setParameter("id", id);

		try {
			team = query.getSingleResult();
		} catch (NoResultException e) {
			team = null;
		}

		session.getTransaction().commit();
		session.close();

		return team;
	}

	public boolean editTeam(Team team) {

		boolean edited = true;

		Session session = PersistenceManager.openSession();
		session.getTransaction().begin();
		session.merge(team); // cascades the tool & skill relationships
		session.getTransaction().commit();
		session.close();

		return edited;
	}
	
	public boolean deleteTeam(Team team) {

		boolean deleted = true;

		Session session = PersistenceManager.openSession();
		session.getTransaction().begin();
		session.remove(team); // cascades the tool & skill relationships
		session.getTransaction().commit();
		session.close();

		return deleted;
	}

}
