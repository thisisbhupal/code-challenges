package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CompensationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompensationController.class);

	@Autowired
	CompensationService compensationService;

	@Operation(summary = "Get a compensation details by employee id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the compensation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Compensation.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Compensation not found", content = @Content) })
	@GetMapping("/compensation/{id}")
	public Compensation read(@PathVariable String id) {
		LOGGER.debug("Received compensation create request for id [{}]", id);
		return compensationService.read(id);
	}

	@Operation(summary = "Create compensation details with employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee and compensation created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Compensation.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input supplied", content = @Content) })
	@PostMapping("/compensation")
	public Compensation create(@RequestBody Compensation compensation) {
		LOGGER.debug("Received compensation create request for [{}]", compensation);
		return compensationService.create(compensation);
	}

}
