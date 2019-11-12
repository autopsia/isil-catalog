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
        final String sql = "insert into city(name, countryId) values (?,?)";
        jdbcTemplate.update(sql, city.getName(), city.getCountryId());
    }

    @Override
    public void update(City city) {
        final String sql = "update city set name = ?, countryId = ? where cityId = ?";
        jdbcTemplate.update(sql, city.getName(), city.getCountryId(), city.getCityId());
    }

    @Override
    public void delete(Long id) {
        final String sql = "delete from city where cityId = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<City> findAll() {
        final String sql = "select ci.*, co.countryId, co.name AS countryName from city ci JOIN Country co ON (ci.countryId = co.countryId)";
        List<City> cities = jdbcTemplate.query(sql, JdbcCityRepository::CityRowMapper);
        return cities;
    }
    @Override
    public City findById(Long id) {
        final String sql = "select ci.*, co.countryId, co.name AS countryName from city ci JOIN Country co ON (ci.countryId = co.countryId) where cityId = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                JdbcCityRepository::CityRowMapper);
    }

    public static City CityRowMapper(ResultSet resultSet, int i)
            throws SQLException {
        Long rsId = resultSet.getLong("cityId");
        String name = resultSet.getString("name");
        Long country = resultSet.getLong("countryId");
        String countryName = resultSet.getString("countryName");
        return new City(rsId, name, country, countryName);
    }
}
