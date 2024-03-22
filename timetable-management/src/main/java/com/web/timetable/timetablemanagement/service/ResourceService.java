package com.web.timetable.timetablemanagement.service;

import com.web.timetable.timetablemanagement.model.Resource;
import com.web.timetable.timetablemanagement.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepo;

    public Resource createResource(Resource resource){

        return resourceRepo.save(resource);
    }

    public List<Resource> getAllResources(){

        return resourceRepo.findAll();
    }

    public Optional<Resource> getResourceById(String resourceId){
        return resourceRepo.findById(resourceId);
    }

    public Resource updateResource(String id, Resource updatedResource){
        Optional<Resource> optionalResource = resourceRepo.findById(id);
        if(optionalResource.isPresent()){
            Resource existingResource = optionalResource.get();
            existingResource.setName(updatedResource.getName());
            return resourceRepo.save(existingResource);
        }else{
            return null;
        }
    }

    public void deleteResource(String id) {
        resourceRepo.deleteById(id);
        System.out.println("Resource with id " + id + " deleted...");
    }
}
