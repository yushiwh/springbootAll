package com.ys.service;

import com.ys.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yushi on 2017/2/25.
 */
public interface Repository extends JpaRepository<Person, Long> {
}
