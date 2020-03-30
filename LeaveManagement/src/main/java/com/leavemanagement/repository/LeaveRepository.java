package com.leavemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leavemanagement.entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long>{
	
	public Leave findByLeaveTypeLeaveTypeIdAndUserUserId(Long leaveTypeId, Long userId);

}
