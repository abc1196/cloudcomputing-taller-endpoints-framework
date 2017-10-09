/*
 * Copyright (c) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not  use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.cloudcomputing.dimayor.controller;

import java.util.ArrayList;
import java.util.List;

import com.cloudcomputing.dimayor.dto.AddTeamDTO;
import com.cloudcomputing.dimayor.dto.TeamDTO;
import com.cloudcomputing.dimayor.dto.TeamsDTO;
import com.cloudcomputing.dimayor.echo.Message;
import com.cloudcomputing.dimayor.logic.TeamLogic;
import com.cloudcomputing.dimayor.model.Team;
import com.cloudcomputing.dimayor.utils.ModelUtils;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

@Api(name = "team", version = "v1", namespace = @ApiNamespace(ownerDomain = "dimayor.surge.sh", ownerName = "dimayor.surge.sh", packagePath = "com.cloudcomputing.dimayor"), issuers = {
		@ApiIssuer(name = "firebase", issuer = "https://securetoken.google.com/YOUR-PROJECT-ID", jwksUri = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com") })
public class TeamApiV1 {

	private TeamLogic teamLogic;

	@ApiMethod(name = "test", path = "test", httpMethod = ApiMethod.HttpMethod.GET)
	public Message test() {
		return new Message().setMessage("Is working!");
	}

	@ApiMethod(name = "test_with_parameter", path = "test/{n}", httpMethod = ApiMethod.HttpMethod.GET)
	public Message echoPathParameter(@Named("n") String n) {
		return new Message().setMessage(n);
	}

	@ApiMethod(name = "get_all_teams", path = "teams", httpMethod = ApiMethod.HttpMethod.GET)
	public TeamsDTO getAllTeams() {

		teamLogic = new TeamLogic();

		List<Team> teams = teamLogic.getAllTeams();
		List<TeamDTO> teamsListDTO = new ArrayList<TeamDTO>();

		for (Team actTeam : teams) {
			teamsListDTO.add(ModelUtils.mapObjectToDTO(actTeam, TeamDTO.class));
		}

		TeamsDTO teamsDTO = new TeamsDTO();
		teamsDTO.setTeams(teamsListDTO);
		return teamsDTO;
	}

	@ApiMethod(name = "get_team_by_id", path = "teams/{id}", httpMethod = ApiMethod.HttpMethod.GET)
	public TeamDTO getTeamById(@Named("id") int id) {

		teamLogic = new TeamLogic();

		Team team = teamLogic.getTeam(id);
		TeamDTO teamDTO = ModelUtils.mapObjectToDTO(team, TeamDTO.class);
		return teamDTO;
	}

	@ApiMethod(name = "addTeam", path = "team", httpMethod = ApiMethod.HttpMethod.POST)
	public Message addTeam(AddTeamDTO addTeamDTO) {

		teamLogic = new TeamLogic();

		Team team = ModelUtils.mapObjectToDTO(addTeamDTO, Team.class);
		boolean added = teamLogic.addTeam(team);
		return new Message().setMessage("" + added);
	}

	@ApiMethod(name = "editTeam", path = "team", httpMethod = ApiMethod.HttpMethod.PUT)
	public Message editTeam(TeamDTO teamDTO) {

		teamLogic = new TeamLogic();
		Team team = ModelUtils.mapObjectToDTO(teamDTO, Team.class);
		boolean edited = teamLogic.editTeam(team);
		return new Message().setMessage("" + edited);
	}

	@ApiMethod(name = "deleteTeam", path = "team/{id}", httpMethod = ApiMethod.HttpMethod.DELETE)
	public Message deleteTeam(@Named("id") Integer id) {

		teamLogic = new TeamLogic();

		boolean deleted = teamLogic.deleteTeam(id);
		return new Message().setMessage("" + deleted);
	}

}
