package com.musala.drone.data.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ProjectionPaginationExecutor<T> {
  <R> Page<R> paginate(Specification<T> specificarion, Class<R> projectionClass, Pageable page);

  /**
   *
   * @author  froilan
   * @param listSpecificarion
   * @param countSpecificarion
   * @param projectionClass
   * @param page
   * @param <R>
   * @return
   */
  <R> Page<R> paginate(Specification<T> listSpecificarion, Specification<T> countSpecificarion, Class<R> projectionClass, Pageable page);
}
