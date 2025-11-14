package com.example.employee.web;

import com.example.employee.domain.Role;
import com.example.employee.repo.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

  private final RoleRepository repo;

  public RoleController(RoleRepository repo) {
    this.repo = repo;
  }

  // Create role
  @PostMapping
  public ResponseEntity<Role> create(@RequestBody Role r) {
    Role saved = repo.save(r);
    return ResponseEntity.created(URI.create("/api/roles/" + saved.getId())).body(saved);
  }

  // List all roles
  @GetMapping
  public List<Role> list() {
    return repo.findAll();
  }

  // Get one role by id
  @GetMapping("/{id}")
  public Role get(@PathVariable("id") Long id) {
    return repo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Role not found: " + id));
  }
}
