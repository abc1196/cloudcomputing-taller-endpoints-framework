package com.cloudcomputing.dimayor.logic;

import java.util.List;

import javax.inject.Inject;

import com.cloudcomputing.dimayor.dao.TeamDAO;
import com.cloudcomputing.dimayor.model.Team;

public class TeamLogic {
	
	@Inject
	private TeamDAO teamDAO;
	
	public TeamLogic() {		
	}

	public boolean addTeam(Team team) {		
		return teamDAO.addTeam(team);		
	}
	
	public Team getTeam(Integer id) {
		return teamDAO.getTeam(id);
	}
	
	public List<Team> getAllTeams(){
		return teamDAO.getAllTeams();
	}
	
	public boolean editTeam(Integer id) {
		Team team = teamDAO.getTeam(id);
		return teamDAO.editTeam(team);
	}
	
	public boolean deleteTeam(Integer id) {
		Team team = teamDAO.getTeam(id);
		return teamDAO.deleteTeam(team);
	}
	
}
