package com.saltmine.ocp.controller

import com.saltmine.ocp.modal.request.WorkspaceRequest
import com.saltmine.ocp.service.WorkspaceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/workspace")
class WorkspaceController(
    private val workspaceService: WorkspaceService
){

    @GetMapping
    fun getWorkspaces(
        @RequestBody() workspaceRequest: WorkspaceRequest
    ): String? {
        return workspaceService.getWorkspace(workspaceRequest)
    }
}