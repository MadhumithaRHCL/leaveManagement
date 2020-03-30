package com.leavemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leavemanagement.entity.LeaveType;
@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType,Long>{

}
