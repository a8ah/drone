package com.demo.drone.data.common.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import com.demo.drone.data.common.model.annotation.GeneratedCode;
import com.demo.drone.data.common.repository.EntityRepository;
import com.demo.drone.data.common.tools.ClassReflectionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;



public abstract class AbstractEntityService<T, ID extends Serializable, R extends EntityRepository<T, ID>> {

    @Autowired
    protected R repository;

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    protected ApplicationContext context;

    @Transactional
    public void flush(){

        repository.flush();
    }

    @Transactional
    public List<T> save(Iterable<T> entities) {

        for (T entity:  entities) {
            this.applyFieldSpecialAnnotation(entity);
        }

        return repository.saveAll(entities);
    }

    @Transactional
    public T saveAndFlush(T entity) {

        this.applyFieldSpecialAnnotation(entity);

        return repository.saveAndFlush(entity);
    }

    @Transactional
    public T save(T entity) {

        this.applyFieldSpecialAnnotation(entity);

        return repository.save(entity);
    }

    public long count(Specification<T> spec) {
        return repository.count(spec);
    }

    public long count() {
        return repository.count();
    }

    public List<T> findAll(Specification<T> spec, Sort sort) {
        return repository.findAll(spec, sort);
    }

    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    public List<T> findAll(Specification<T> spec) {
        return repository.findAll(spec);
    }

    public T findOne(Specification<T> spec) {
        Optional<T> optionalT = repository.findOne(spec);

        return optionalT.get();
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T getOne(ID id) {
        return repository.getOne(id);
    }

    public T findOne(ID id) {
        Optional<T> optionalT = repository.findById(id);

        return optionalT.get();
    }

    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional
    public void deleteInBatch(Iterable<T> entities) {
        repository.deleteInBatch(entities);
    }

    @Transactional
    public void delete(Iterable<? extends T> entities) {
        repository.deleteAll(entities);
    }

    @Transactional
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Transactional
    public void delete(ID id) {
        repository.deleteById(id);
    }


    /**
     * Genera el Codigo Estandar (String Code) para un campos especifico
     *
     * @param entity
     * @param field
     * @param lengthCode
     * @return
     */
    protected Object generateCode(T entity, String field, int lengthCode){

        try {

            //Contruyendo Query para Devolver el ultimo Codigo Generado (estandar)
            String entityClass = entity.getClass().getName();

            String sql = "SELECT E FROM " + entityClass + " as E ";

            sql += " WHERE (E." + field + " IS NOT NULL AND E." + field + " <> '')";

            sql += " ORDER BY E." + field + " DESC";

            Query query = this.em.createQuery(sql).setMaxResults(1);

            T result = null;
            Object resultLastCode = null;

            try{

                result = (T)query.getSingleResult();
                resultLastCode = ClassReflectionUtil.getProperty(result, field);
            }
            catch (Exception e){

            }

            Integer lastCode = null;
            if(null == resultLastCode){

                lastCode = 0;
            }
            else{

                lastCode = Integer.parseInt((String)resultLastCode);
            }

            //Retornado/Generando Codigo Estandar
            return this.generateStringCode((Integer)lastCode, lengthCode);
        }
        catch (Exception e){

            e.printStackTrace();
            return null;
        }
    }


    /**
     * Genera un Codigo Estandar de la Forma (000X),
     * a partir del Ultimo codigo Generado y la Logintud del Codigo
     *
     * @param lastCode
     * @param length
     * @return
     */
    protected String generateStringCode(Integer lastCode, int length) {

        Integer integerLastCode = (null != lastCode) ? (Integer) lastCode : 0;

        integerLastCode += 1;

        String generateCode = integerLastCode.toString();


        while (generateCode.length() < length) {

            generateCode = "0" + generateCode;
        }

        return generateCode;
    }


    private String prepareStringSearch(String search) {

        return "%" + search.toLowerCase().replace(' ', '%') + "%";
    }

    /**
     * Aplica Anotaciones Especiales a la entidad y sus campos
     *
     * @param entity
     */
    private void applyFieldSpecialAnnotation(T entity) {

        try {

            Field[] fieldList = entity.getClass().getDeclaredFields();

            for (Field field : fieldList) {


                //For Generate Code: Aplica Generar Codigo si tiene la Anotacion
                final var generatedCode = field.getAnnotation(GeneratedCode.class);
                if (generatedCode != null) {

                    //Obteniendo el valor del Campo
                    var fieldValue = ClassReflectionUtil.getProperty(entity, field.getName());

                    //Genera el Codigo si es Null
                    if (null == fieldValue || ((String)fieldValue).isEmpty()) {

                        //Obteniendo el Codigo
                        Object generateCode = this.applyForGeneratedCode(entity, field.getName(), generatedCode);

                        //Insertando el Codigo
                        ClassReflectionUtil.setPropertyValue(entity, field.getName(), generateCode);
                    }
                }


            }

        } catch (Exception e) {

            String he = "Excepcion no Manejada";
        }
    }

    /**
     * Aplica el Metodo para Generar el Codigo.
     * Especial para la Anotacion GeneratedCode
     *
     * @param entity
     * @param field
     * @param generatedCodeAnnotation
     * @return
     */
    private Object applyForGeneratedCode(T entity, String field, GeneratedCode generatedCodeAnnotation) {

        //Longitud del Codigo (tomado de la Anotacion)
        int lengthCode = generatedCodeAnnotation.length();

        //Generando el Codigo
        return this.generateCode(entity, field, lengthCode);
    }
}
