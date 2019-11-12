package com.sectordefectuoso.pc3.repository;

import com.sectordefectuoso.pc3.model.Country;
import com.sectordefectuoso.pc3.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcCountryRepository
        implements CountryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Country country) {
        final String sql = "insert into country(name) values (?)";
        jdbcTemplate.update(sql, country.getName());
    }

    @Override
    public void update(Country country) {
        final String sql = "update country set name = ? where countryId = ?";
        jdbcTemplate.update(sql, country.getName(), country.getCountryId());
    }

    @Override
    public void delete(Long id) {
        final String sql = "delete from country where countryId = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Country> findAll() {
        final String sql = "select * from country";
        return jdbcTemplate.query(sql, JdbcCountryRepository::CountryRowMapper);
    }


    @Override
    public Country findById(Long id) {
        final String sql = "select * from country where countryId = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                JdbcCountryRepository::CountryRowMapper);
    }

    public List<City> findCitiesByCountryId(Long id) {
        final String sql = "select ci.*, co.countryId, co.name AS countryName from city ci JOIN Country co ON (ci.countryId = co.countryId) where ci.countryId = ?";
        return jdbcTemplate.query(sql,
                new Object[]{id},
                JdbcCountryRepository::CityCountryRowMapper);
    }

    private static Country CountryRowMapper(ResultSet resultSet, int i)
            throws SQLException {
        Long rsId = resultSet.getLong("countryId");
        String name = resultSet.getString("name");
        return new Country(rsId, name);
    }

    public static City CityCountryRowMapper(ResultSet resultSet, int i)
            throws SQLException {
        Long rsId = resultSet.getLong("cityId");
        String name = resultSet.getString("name");
        Long country = resultSet.getLong("countryId");
        String countryName = resultSet.getString("countryName");
        return new City(rsId, name, country, countryName);
    }
}
