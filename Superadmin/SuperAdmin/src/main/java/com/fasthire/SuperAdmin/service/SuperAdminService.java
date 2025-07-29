package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.entity.SuperAdmin;

public interface SuperAdminService {
    SuperAdmin createNewSuperAdmin(SuperAdmin newSuperAdmin);
    SuperAdmin updateNewSuperAdmin(Long id, SuperAdmin newSuperAdmin);
}
