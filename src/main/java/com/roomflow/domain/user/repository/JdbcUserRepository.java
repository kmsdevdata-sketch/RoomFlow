package com.roomflow.domain.user.repository;

import com.roomflow.domain.user.entity.Role;
import com.roomflow.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        String sql = """
                insert into users
                (login_id, password, name, birth_date, phone_number, email, role, created_at, updated_at)
                values (?,?,?,?,?,?,?,now(),now())
                """;

        //AUTO_INCREMENT 로 생성된 id가져오기 위해서 필요함
        KeyHolder keyHolder = new GeneratedKeyHolder();

        //SQL 실행시작
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLoginId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setDate(4, Date.valueOf(user.getBirthDate()));
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getRole().name());
            return ps; // jdbc한테 실행할 Statement전달
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = """
                select *
                from users
                where id = ?
                """;
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper(), id));
        } catch (EmptyResultDataAccessException e) { // 데이터베이스 조회가 0건일때 1건을 예상하고 queryForObject 호출하면
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        // 대부분의 ux가 최신정보를 보고싶어함 => 내림차순
        String sql = """
                select * 
                from users
                order by id desc 
                """;

    return jdbcTemplate.query(sql, userRowMapper());
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        String sql = """
                select * 
                from users
                where login_id = ?
                """;

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper(), loginId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User(
                    rs.getString("login_id"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getDate("birth_date").toLocalDate(),
                    rs.getString("phone_number"),
                    rs.getString("email")
            );
            user.setId(rs.getLong("id"));
            user.setRole(Role.valueOf(rs.getString("role")));

            Timestamp createdAt = rs.getTimestamp("created_at");
            if (createdAt != null) {
                user.setCreatedAt(createdAt.toInstant());
            }

            Timestamp updatedAt = rs.getTimestamp("updated_at");
            if (updatedAt != null) {
                user.setUpdatedAt(updatedAt.toInstant());
            }

            return user;
        };
    }
}
