package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.entity.Plan;
import com.fasthire.SuperAdmin.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping("/createPlan")
    public Plan createPlan(@RequestBody Plan plan) {
        return planService.createPlan(plan);
    }

    @GetMapping("/getAllPlans")
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/getPlanById/{id}")
    public Optional<Plan> getPlanById(@PathVariable Long id) {
        return planService.getPlanById(id);
    }

    @GetMapping("/getPlanByName/{name}")
    public Optional<Plan> getPlanByName(@PathVariable String name) {
        return planService.getPlanByName(name);
    }

    @GetMapping("/getPlansByMaxPrice/{maxPrice}")
    public List<Plan> getPlansByMaxPrice(@PathVariable double maxPrice) {
        return planService.getPlansByMaxPrice(maxPrice);
    }
}
