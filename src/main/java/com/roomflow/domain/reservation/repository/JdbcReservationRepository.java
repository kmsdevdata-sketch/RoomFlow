package com.roomflow.domain.reservation.repository;

import com.roomflow.domain.reservation.entity.Reservation;
import com.roomflow.domain.reservation.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class JdbcReservationRepository implements ReservationRepository{

    private final JdbcTemplate jdbcTemplate;


    @Override
    public Reservation save(Reservation reservation) {
        String sql = """
                insert into reservations
                (user_id,room_id,date,start_time,end_time,status,created_time,created_at,updated_at)
                values(?,?,?,?,?,?,?,now(),now())
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, reservation.getUserId());
            ps.setLong(2, reservation.getRoomId());
            ps.setDate(3, Date.valueOf(reservation.getDate()));
            ps.setTime(4, Time.valueOf(reservation.getStartTime()));
            ps.setTime(5, Time.valueOf(reservation.getEndTime()));
            ps.setString(6, reservation.getStatus().name());
            ps.setTime(7, Time.valueOf(reservation.getCreatedTime()));
            return ps;
        }, keyHolder);

        reservation.setId(keyHolder.getKey().longValue());
        return reservation;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        String sql = """
                select *
                from reservations
                where id = ?
                """;

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, reservationRowMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Reservation> findAll() {
        String sql = """
                select *
                from reservations
                order by id desc
                """;

        return jdbcTemplate.query(sql,reservationRowMapper());
    }

    @Override
    public List<Reservation> findReservationListByUser(Long userId) {
        //store.values().stream()
        //      .filter(r -> userId.equals(r.getUserId())) // NPE발생하지 않도록 하는게 좋은습관
        //    .toList();

        String sql = """
                select *
                from reservations
                where user_id = ?
                """;

        return jdbcTemplate.query(sql,reservationRowMapper(),userId);
    }

    public RowMapper<Reservation> reservationRowMapper() {
        return ((rs, rowNum) -> {
            Reservation reservation = new Reservation(
                    rs.getLong("room_id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("start_time").toLocalTime(),
                    rs.getTime("end_time").toLocalTime()
            );
            reservation.setId(rs.getLong("id"));
            reservation.setUserId(rs.getLong("user_id"));

            reservation.setStatus(Status.valueOf(rs.getString("status")));

            Time createdTime = rs.getTime("created_time");
            if (createdTime != null) {
                reservation.setCreatedTime(createdTime.toLocalTime());
            }

            Timestamp createdAt = rs.getTimestamp("created_at");
            if (createdAt != null) {
                reservation.setCreatedAt(createdAt.toInstant());
            }

            Timestamp updatedAt = rs.getTimestamp("updated_at");
            if (updatedAt != null) {
                reservation.setUpdatedAt(updatedAt.toInstant());
            }

            return reservation;
        } );
    }
}
