package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.Resource;
import com.web.timetable.timetablemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping("/createResource")
    public Resource addResource(@RequestBody Resource resource){
        resourceService.createResource(resource);
        return resource;
    }

    @GetMapping
    public ResponseEntity<List<Resource>> getAllResource(){
        return new ResponseEntity<List<Resource>>(resourceService.getAllResources(), HttpStatus.OK);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<Optional<Resource>> getResourceById(@PathVariable String resourceId){
        return new ResponseEntity<Optional<Resource>>(resourceService.getResourceById(resourceId), HttpStatus.OK);
    }

    @PutMapping("/updateResource/{id}")
    public Resource updateResource(@PathVariable String id,@RequestBody Resource updatedResource){
        resourceService.updateResource(id, updatedResource);
        return updatedResource;
    }

    @DeleteMapping("/deleteResource/{id}")
    public void deleteResource(@PathVariable String id){
        resourceService.deleteResource(id);
    }
}
