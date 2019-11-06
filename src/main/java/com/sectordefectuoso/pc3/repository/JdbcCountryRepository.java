package com.sectordefectuoso.pc3.repository;

import com.sectordefectuoso.pc3.model.Country;
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

    private static Country CountryRowMapper(ResultSet resultSet, int i)
            throws SQLException {
        Long rsId = resultSet.getLong("countryId");
        String name = resultSet.getString("name");
        return new Country(rsId, name);
    }
}
