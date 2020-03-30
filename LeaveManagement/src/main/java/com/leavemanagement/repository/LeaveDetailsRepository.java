package com.leavemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leavemanagement.entity.LeaveDetails;

@Repository
public interface LeaveDetailsRepository extends JpaRepository<LeaveDetails,Long>{
	public List<LeaveDetails> findAllByUserUserId(Long userId);

}
