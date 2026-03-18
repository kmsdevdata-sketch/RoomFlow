package com.roomflow.domain.room.repository;

import com.roomflow.domain.room.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class JdbcRoomRepository implements RoomRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Room save(Room room) {
        String sql = """
                insert into rooms
                (name,capacity,open_time,close_time,is_available,created_at,updated_at)
                values (?,?,?,?,?,now(),now())
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, room.getName());
            ps.setInt(2, room.getCapacity());
            ps.setTime(3, Time.valueOf(room.getOpenTime()));
            ps.setTime(4, Time.valueOf(room.getCloseTime()));
            ps.setBoolean(5, room.isAvailable());
            return ps;
        }, keyHolder);

        room.setId(keyHolder.getKey().longValue());
        return room;
    }

    @Override
    public Optional<Room> findByRoomId(Long id) {
        String sql = """
                select * 
                from rooms
                where id = ?
                """;
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, roomRowMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Room> findAll() {
        String sql = """
                select *
                from rooms
                order by id desc
                """;
        return jdbcTemplate.query(sql,roomRowMapper());
    }

    private RowMapper<Room> roomRowMapper() {
        return (rs, rowNum) -> {
            Room room = new Room(
                    rs.getString("name"),
                    rs.getInt("capacity"),
                    rs.getTime("open_time").toLocalTime(),
                    rs.getTime("close_time").toLocalTime(),
                    rs.getBoolean("is_available")
            );
            room.setId(rs.getLong("id"));

            Timestamp createdAt = rs.getTimestamp("created_at");
            if (createdAt != null) {
                room.setCreatedAt(createdAt.toInstant());
            }

            Timestamp updatedAt = rs.getTimestamp("updated_at");
            if (updatedAt != null) {
                room.setUpdatedAt(updatedAt.toInstant());
            }

            return room;
        };
    }
}
