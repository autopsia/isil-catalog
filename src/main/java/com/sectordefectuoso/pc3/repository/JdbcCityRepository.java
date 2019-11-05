package com.sectordefectuoso.pc3.repository;

import com.sectordefectuoso.pc3.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcCityRepository
        implements CityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(City city) {
        final String sql = "insert into city(name) values (?,?)";
        jdbcTemplate.update(sql, city.getName(), city.getCountryId());
    }

    @Override
    public void update(City city) {
        final String sql = "update city set name = ?, countryId = ? where id = ?";
        jdbcTemplate.update(sql, city.getName(), city.getCityId(), city.getCountryId());
    }

    @Override
    public void delete(Long id) {
        final String sql = "delete city where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<City> findAll() {
        final String sql = "select * from city";
        List<City> cities = jdbcTemplate.query(sql, JdbcCityRepository::CityRowMapper);
        return cities;

    }


    @Override
    public City findById(Long id) {
        final String sql = "select * from city where id = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                JdbcCityRepository::CityRowMapper);
    }

    private static City CityRowMapper(ResultSet resultSet, int i)
            throws SQLException {
        int rsId = resultSet.getInt("cityId");
        String name = resultSet.getString("name");
        int country = resultSet.getInt("countryId");
        return new City(rsId, name, country);
    }
}
